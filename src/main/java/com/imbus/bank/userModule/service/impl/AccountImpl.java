package com.imbus.bank.userModule.service.impl;

import com.imbus.bank.common.UserCommon;
import com.imbus.bank.userModule.bo.TokenBo;
import com.imbus.bank.userModule.bo.UserBo;
import com.imbus.bank.userModule.dao.AccountDao;
import com.imbus.bank.userModule.type.AccountResult;
import com.imbus.bank.userModule.service.IAccount;
import com.imbus.bank.utils.EncodeUtil;
import com.imbus.bank.utils.JsonUtil;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhong on 2019-12-3.
 */
@Service
public class AccountImpl implements IAccount {

    @Autowired
    AccountDao accountDao;

    /**
     * 登录
     * @param username 用户名
     * @param pwd 密码
     * @return 是否成功
     */
    @Override
    public String login(String username, String pwd) {

        // 对密码二次加密
        pwd = UserCommon.encodePwd(pwd);

        // 添加用户认证信息
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, pwd);

        // 进行验证，这里可以捕获异常，然后返回对应信息
        try {
            SecurityUtils.getSubject().login(usernamePasswordToken);

            // 用户验证通过，生成token
            UserBo userBo = new UserBo();
            userBo.setUsername(username);
            userBo.setPwd(pwd);
            userBo.setUserID(accountDao.findUserIDByName(username).getUserID());
            String token = UserCommon.createToken(userBo);

            // 将token放入缓存
            CacheManager cacheManager = CacheManager.create();
            Cache cache = cacheManager.getCache("ehcache");
            cache.put(new Element("token:"+username,token,false,60*30,60*30));
            cache.put(new Element("pwd:"+username,pwd));

            return token;
        }
        catch (DisabledAccountException ex)
        {
            return AccountResult.USER_LOGIN_BAN.toString();
        }
        catch (Exception ex)
        {
            return AccountResult.USER_LOGIN_FAILED.toString();
        }
    }

    /**
     * 更换密码
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @return 新token/失败
     */
    @Override
    public String changePwd(String oldPwd, String newPwd) {

        // 对密码二次加密
        oldPwd = UserCommon.encodePwd(oldPwd);
        newPwd = UserCommon.encodePwd(newPwd);

        // 获取当前用户Token
        TokenBo tokenBo = UserCommon.getUserBo();

        int userID = tokenBo.getUserID();

        String userOldPwd = accountDao.getUserPwd(userID);
        if(!userOldPwd.equals(oldPwd)){
            return AccountResult.PWD_CHANGE_FAILED.toString();
        }

        // 更新数据库内的密码
        accountDao.updateUserPwd(userID,newPwd);

        // 生成新token
        UserBo newUserBo = new UserBo();
        newUserBo.setUsername(tokenBo.getUsername());
        newUserBo.setPwd(newPwd);
        String token = UserCommon.createToken(newUserBo);

        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("ehcache");

        // 删除缓存中的旧token
        cache.remove("token:"+tokenBo.getUsername());

        // 向缓存中添加新token
        cache.put(new Element("token:"+tokenBo.getUsername(),token,false,60*5,60*30));

        return token;
    }

    /**
     * 用户登出
     * @param token token
     * @return 是否成功
     */
    @Override
    public String logout(String token) {

        // 获取用户信息
        String json = EncodeUtil.decodeBase64(token);
        TokenBo tokenBo = (TokenBo) JsonUtil.jsonToObject(json,TokenBo.class);

        // 判断缓存中是否存在token
        CacheManager cacheManager = CacheManager.create();
        Cache cache = cacheManager.getCache("ehcache");
        Element element = cache.get("token:" + tokenBo.getUsername());
        if(!element.getValue().toString().equals(token)){
           return AccountResult.TOKEN_CHECK_FAILED.toString();
        }

        // 从缓存中删除token
        cache.remove("token:"+tokenBo.getUsername());

        return AccountResult.LOG_OUT_SUCCESS.toString();
    }
}
