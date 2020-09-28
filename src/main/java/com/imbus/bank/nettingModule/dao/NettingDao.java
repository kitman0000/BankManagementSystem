package com.imbus.bank.nettingModule.dao;

import com.imbus.bank.nettingModule.bo.NettingBo;
import com.imbus.bank.nettingModule.bo.NettingWarningBo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by zhong on 2020-9-27.
 */
@Mapper
@Repository
public interface NettingDao {
    int getNettingAmount();

    List<NettingBo> getNettingList(@Param("startRow") int startRow, @Param("rowsOnePage") int rowsOnePage);

    void addNettingResult(@Param("netDate") Date netDate, @Param("result") boolean result, @Param("netCount") int netCount, @Param("amount") BigDecimal amount, @Param("startTime") Date startTime, @Param("endTime") Date endTime, @Param("nettingSign") String nettingSign);

    void addNettingWarning(@Param("target") String target, @Param("theoryAmount") BigDecimal theoryAmount, @Param("actualAmount") BigDecimal actualAmount, @Param("nettingSign") String nettingSign);

    List<NettingWarningBo> getNettingWarning(@Param("nettingSign") String nettingSign);
}
