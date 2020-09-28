package com.imbus.bank.componet;

import com.imbus.bank.componet.Entity.ClientEntity;
import com.imbus.bank.componet.type.AddClientResult;

/**
 * Created by zhong on 2020-8-11.
 *
 * 客户业务接口
 * 所有客户业务Controller层，都必须实现此接口
 */
public interface IClientService {
    AddClientResult addClient(ClientEntity clientEntity);
}
