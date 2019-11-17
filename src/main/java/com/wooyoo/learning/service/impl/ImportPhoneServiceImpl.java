package com.wooyoo.learning.service.impl;

import com.wooyoo.learning.dao.domain.Mvalidate;
import com.wooyoo.learning.dao.domain.SendMessage;
import com.wooyoo.learning.dao.mapper.MvalidateMapper;
import com.wooyoo.learning.dao.mapper.SendMessageMapper;
import com.wooyoo.learning.service.ImportPhoneService;
import com.wooyoo.learning.service.ImportService;
import com.wooyoo.learning.util.ExportExcel;
import com.wooyoo.learning.util.ReadExcel;
import com.wooyoo.learning.util.ReadPhoneExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


@Service
public class ImportPhoneServiceImpl implements ImportPhoneService {
    @Autowired(required = true)
    private SendMessageMapper sendMessageMapper;


    @Override
    public String readExcelFile(MultipartFile file,String userName) {
        String result = "";
        //创建处理EXCEL的类
        ReadPhoneExcel readExcel = new ReadPhoneExcel();
        //解析excel，获取上传的事件单+
        List<Map<String, Object>> phones = readExcel.getExcelInfo(file);
        SendMessage sm = new SendMessage();
        sm.setMessage(phones.get(0).get("msg").toString());
        sm.setIsSend(0);
        sm.setUserName(userName);
        StringBuffer sbf = new StringBuffer();
        for (int i=1;i<phones.size();i++){
            String phoneTemp = phones.get(i).get("phone").toString();
            sbf.append(phoneTemp);
            sbf.append(",");
        }
        sm.setPhones(sbf.toString());

        //增加数据
        sendMessageMapper.insert(sm);
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

}