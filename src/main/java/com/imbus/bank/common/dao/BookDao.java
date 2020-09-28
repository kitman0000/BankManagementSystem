package com.imbus.bank.common.dao;

import com.imbus.bank.common.bo.BookBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-8-22.
 */
@Mapper
@Repository
public interface BookDao {
    void addCashBill(@Param("amount") BigDecimal amount, @Param("target") String target, @Param("remark") String remark, @Param("agencyID") int agencyID, @Param("tellerID") int tellerID, @Param("trunkID") int trunkID, @Param("cashObject") int cashObject);

    void addFundBill(@Param("amount") BigDecimal amount, @Param("target") String target, @Param("remark") String remark, @Param("agencyID") int agencyID, @Param("tellerID") int tellerID, @Param("tag") int tag);

    List<BookBo> getFundBookList(@Param("tag") int tag, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    List<BookBo> getCashBookList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
