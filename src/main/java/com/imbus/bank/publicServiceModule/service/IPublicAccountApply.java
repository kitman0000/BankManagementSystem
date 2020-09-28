package com.imbus.bank.publicServiceModule.service;

import com.imbus.bank.publicServiceModule.bo.PublicAccountApplyBo;
import com.imbus.bank.publicServiceModule.type.HandlePublicAccountApplyResult;

import java.util.List;

/**
 * Created by zhong on 2020-8-13.
 */
public interface IPublicAccountApply {
    int getApplyPage();

    List<PublicAccountApplyBo> getApply(int page);

    HandlePublicAccountApplyResult handleAccountApply(int id, int result);
}
