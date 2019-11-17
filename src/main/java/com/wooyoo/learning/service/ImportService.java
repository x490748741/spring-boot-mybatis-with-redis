package com.wooyoo.learning.service;

import com.wooyoo.learning.dao.domain.Mvalidate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportService {

    /**
     * 读取excel中的数据,生成list
     */
    String readExcelFile(MultipartFile file);
    HSSFWorkbook exportExcel();
    Mvalidate selectE(String name,String validateCode);
    List<Mvalidate> selectList(String name, String validateCode);

}