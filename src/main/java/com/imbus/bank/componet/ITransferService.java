package com.imbus.bank.componet;

import com.imbus.bank.componet.Entity.TransferEntity;
import com.imbus.bank.componet.type.TransferResult;

/**
 * Created by zhong on 2020-8-25.
 * 转账业务接口，所有定期存款业务Control层必须实现此接口
 */
public interface ITransferService {
    TransferResult transfer(TransferEntity transferEntity);

}
