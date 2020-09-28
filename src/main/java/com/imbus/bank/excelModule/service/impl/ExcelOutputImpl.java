package com.imbus.bank.excelModule.service.impl;

import com.imbus.bank.common.DateUtil;
import com.imbus.bank.excelModule.dao.ExcelOutputDao;
import com.imbus.bank.excelModule.service.IExcelOutput;
import com.imbus.bank.utils.FileUtil;
import com.imbus.bank.utils.JsonUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by zhong on 2020-9-14.
 */
@Service
public class ExcelOutputImpl implements IExcelOutput {
    @Autowired
    private ExcelOutputDao excelOutputDao;

    static private String fileSavePath;

    @Value("${fileSavePath}")
    public void setFileSavePath(String fileSavePath) {
        ExcelOutputImpl.fileSavePath = fileSavePath;
    }

    @Override
    public String getExcelOutput(String sql) {


        List<LinkedHashMap<String,Object>> result = excelOutputDao.executeSql(sql);

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet();

        // 生成表头
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("命令：");
        cell = row.createCell(1);
        cell.setCellValue(sql);
        cell = row.createCell(2);
        cell.setCellValue("时间");
        cell = row.createCell(3);
        cell.setCellValue(DateUtil.getTimestamp());

        // 列名
        Set<String> col = result.get(0).keySet();
        row = sheet.createRow(1);
        int colN = 0;
        for (String s : col){
            cell = row.createCell(colN);
            cell.setCellValue(s);
            colN++;
        }

        // 内容
        int rowNumber = 2;
        for (LinkedHashMap<String,Object> resultRow:result){
            row = sheet.createRow(rowNumber);
            int columnNumber = 0;
            for (Object o:resultRow.values()){
                cell = row.createCell(columnNumber);
                cell.setCellValue(o.toString());
                columnNumber++;
            }
            rowNumber++;
        }

        String fileWebPath = "FAILED";
        // 输出到文件
        try {
            String filename;
            fileWebPath = "excelOutput\\" + DateUtil.getTimestamp() + ".xls";
            filename = fileSavePath + fileWebPath;

            OutputStream os = new FileOutputStream(filename);
            hssfWorkbook.write(os);
            os.flush();
            os.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return fileWebPath;

    }
}
