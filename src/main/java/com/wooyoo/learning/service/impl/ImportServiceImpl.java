package com.wooyoo.learning.service.impl;

import java.util.List;
import java.util.Map;

import com.wooyoo.learning.dao.domain.Mvalidate;
import com.wooyoo.learning.dao.mapper.MvalidateMapper;
import com.wooyoo.learning.service.ImportService;
import com.wooyoo.learning.util.ExportExcel;
import com.wooyoo.learning.util.ReadExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class ImportServiceImpl implements ImportService {
    @Autowired(required = true)
    private MvalidateMapper mvalidateMapper;


    @Override
    public String readExcelFile(MultipartFile file) {
        String result = "";
        //创建处理EXCEL的类
        ReadExcel readExcel = new ReadExcel();
        //解析excel，获取上传的事件单+
        List<Map<String, Object>> mvalidates = readExcel.getExcelInfo(file);

        //每次上传前清空数据库
        mvalidateMapper.truncateTable();
        //批量增加数据
        mvalidateMapper.insertBatch(mvalidates);
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,

//        for(Map<String, Object> user:mvalidates){
//            int ret = userDao.insertUser(user.get("name").toString(), user.get("sex").toString(), Integer.parseInt(user.get("age").toString()));
//            if(ret == 0){
//                result = "插入数据库失败";
//            }
//        }
//        if(userList != null && !userList.isEmpty()){
//            result = "上传成功";
//        }else{
//            result = "上传失败";
//        }
        result = "上传成功";
        return result;
    }

    @Override
    public HSSFWorkbook exportExcel() {
        List<Mvalidate> list = mvalidateMapper.selectAllList();
        ExportExcel exportExcel = new ExportExcel();
        HSSFWorkbook hb = exportExcel.exportExcel(list);
        return hb;
    }

    @Override
    public Mvalidate selectE(String name, String validateCode) {
        return mvalidateMapper.selectValidate(name,validateCode);
    }

    @Override
    public List<Mvalidate> selectList(String name, String validateCode) {
        List<Mvalidate> list = mvalidateMapper.selectValidateList(name,validateCode);
        //查询成功就修改
        if(list!=null&&list.size()!=0){
            mvalidateMapper.updateSelectList(list);
        }
        return list;
    }

}