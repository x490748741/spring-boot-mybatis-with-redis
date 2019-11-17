package com.wooyoo.learning.util;

import com.wooyoo.learning.dao.domain.Mvalidate;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExportExcel {
    public HSSFWorkbook exportExcel(List<Mvalidate> list){
        //创建HSSFWorkbook对象(excel的文档对象)
        HSSFWorkbook wb = new HSSFWorkbook();
        //建立新的sheet对象（excel的表单）
        HSSFSheet sheet = wb.createSheet("sheet1");

        //在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
        HSSFRow row1 = sheet.createRow(0);
        //创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
        //设置单元格内容
        row1.createCell(0).setCellValue("账号");
        row1.createCell(1).setCellValue("手机号");
        row1.createCell(2).setCellValue("结果");
        row1.createCell(3).setCellValue("是否查询成功");
        //合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
        //sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
        //在sheet里创建第二行
//        HSSFRow row2 = sheet.createRow(1);
        /*HSSFCell cell1 = row2.createCell(0);
        HSSFCell cell2 = row2.createCell(1);
        HSSFCell cell3 = row2.createCell(2);
        HSSFCell cell4 = row2.createCell(3);
        HSSFCell cell5 = row2.createCell(4);
        HSSFCell cell6 = row2.createCell(5);
        //设置列宽
        sheet.setColumnWidth( cell1.getColumnIndex(),256 * 25);
        sheet.setColumnWidth( cell2.getColumnIndex(),256 * 20);
        sheet.setColumnWidth( cell3.getColumnIndex(),256 * 20);
        sheet.setColumnWidth( cell4.getColumnIndex(),256 * 20);
        sheet.setColumnWidth( cell5.getColumnIndex(),256 * 20);
        sheet.setColumnWidth( cell6.getColumnIndex(),256 * 20);*/
        //设置缺省列宽
//        sheet.setDefaultColumnWidth(20);
        //设置缺省列高
//        sheet.setDefaultRowHeightInPoints(30);
        //创建单元格并设置单元格内容
//        row2.createCell(0).setCellValue("报告日期");
//
//        HSSFCellStyle style = wb.createCellStyle();
//        // 实例化样式对象
//        row2.createCell(1).setCellValue("报告项目名称");
//        // 垂直居中
//        style.setAlignment(HorizontalAlignment.CENTER);
//        //将样式应用到行
//        cell.setCellStyle(style);
//
//
//        row2.createCell(2).setCellValue("结果");
//
//        row2.createCell(3).setCellValue("单位");
//
//        row2.createCell(4).setCellValue("异常");
//
//        row2.createCell(5).setCellValue("正常参考值");

        for (int i = 0; i < list.size(); i++) {
            Mvalidate temp = list.get(i);
            HSSFRow rows = sheet.createRow(i + 1);
            rows.createCell(0).setCellValue(temp.getName());
            rows.createCell(1).setCellValue(temp.getValidateCode());
            rows.createCell(2).setCellValue(temp.getResult());
            rows.createCell(3).setCellValue(temp.getIsSelectSuccess());
        }
        return wb;
    }
}
