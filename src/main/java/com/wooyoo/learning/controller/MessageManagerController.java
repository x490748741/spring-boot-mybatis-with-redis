package com.wooyoo.learning.controller;

import com.wooyoo.learning.dao.domain.Send;
import com.wooyoo.learning.dao.mapper.SendMapper;
import com.wooyoo.learning.dao.mapper.SendMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 管理员操作上传的手机号码及信息
 */
@RestController
@RequestMapping("/messageManager")
public class MessageManagerController {

    @Autowired
    private SendMapper sendMapper;

    @Autowired
    private SendMessageMapper sendMessageMapper;

    @GetMapping("/list")
    public List<Map> getNoSendList(){
        List<Map> list = sendMessageMapper.selectNoSendAll();
        return list;
    }

    @GetMapping("/list/{userName}/{isSend}")
    public List<Map> getSendList(@PathVariable("userName")String userName,@PathVariable("isSend")Integer isSend){
        List<Map> list = sendMessageMapper.selectByIsSend(isSend,userName);
        return list;
    }

    @GetMapping("/update/{id}")
    public String sendMsg(@PathVariable("id")Integer id){
        String str = "true";
        sendMessageMapper.updateById(id);
        return str;
    }
}
