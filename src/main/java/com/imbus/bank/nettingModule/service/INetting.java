package com.imbus.bank.nettingModule.service;

import com.imbus.bank.nettingModule.bo.NettingBo;
import com.imbus.bank.nettingModule.bo.NettingWarningBo;

import java.util.List;

/**
 * Created by zhong on 2020-9-28.
 */
public interface INetting {
    int getNettingPage();

    List<NettingBo> getNettingList(int page);

    List<NettingWarningBo> getNettingWarning(String nettingSign);

}
