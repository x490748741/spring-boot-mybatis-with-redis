package com.wooyoo.learning.service;

import com.wooyoo.learning.dao.domain.Mvalidate;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImportPhoneService {

    /**
     * 读取excel中的数据,生成list
     */
    String readExcelFile(MultipartFile file,String userName);
}