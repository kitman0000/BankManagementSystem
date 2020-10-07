package com.imbus.bank.logModule.aspect;


import com.imbus.bank.common.UserCommon;
import com.imbus.bank.logModule.dao.LogDao;
import com.imbus.bank.utils.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by zhong on 2020-7-2.
 */
@Component
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Pointcut("execution(public * com.imbus.bank.*.controller.*.*(..))")
    public void logPointCut() {}

    @Autowired
    private LogDao logDao;

    @Before("logPointCut()")
    public void doBeforePointCut(JoinPoint joinPoint) {

        try {
            int userID = UserCommon.getUserBo().getUserID();
            String methodName = joinPoint.getSignature().getName();
            String args = JsonUtil.objectToJson(joinPoint.getArgs());
            logDao.addLogInfo(userID,methodName,args);
            logger.info("用户ID:" + userID + "    执行:" + methodName + "   参数:" + args);
        }catch (Exception ex){
            String methodName = joinPoint.getSignature().getName();
            String args = JsonUtil.objectToJson(joinPoint.getArgs());

            logDao.addLogInfo(-1,methodName,args);
        }
    }

    @AfterReturning(value = "logPointCut()",returning = "keys")
    public void doAfterPointCut(JoinPoint joinPoint, Object keys){
        try {
            int userID = UserCommon.getUserBo().getUserID();
            String methodName = joinPoint.getSignature().getName();
            String keysJson = JsonUtil.objectToJson(keys);
            logger.info("用户ID:" + userID + "    执行:" + methodName + "   返回:" + keysJson);
        }catch (Exception ex){

        }
    }
}
