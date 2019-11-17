package com.wooyoo.learning.controller;

import com.wooyoo.learning.dao.domain.Message;
import com.wooyoo.learning.dao.domain.Send;
import com.wooyoo.learning.dao.domain.SendMessage;
import com.wooyoo.learning.dao.domain.User;
import com.wooyoo.learning.dao.mapper.MessageMapper;
import com.wooyoo.learning.dao.mapper.SendMapper;
import com.wooyoo.learning.dao.mapper.SendMessageMapper;
import com.wooyoo.learning.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户上传手机号码及信息
 */
@RestController
@RequestMapping("/uploadMessage")
public class UploadMessageController {
    @Autowired
    private SendMapper sendMapper;

    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private SendMessageMapper sendMessageMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{phones}/{message}/{userName}")
    public String save(@PathVariable("phones")String phones,@PathVariable("message")String msg,@PathVariable("userName")String userName){
        String success = "true";

       User user =  userMapper.selectByUserName(userName);
                if(user == null){
                    success = "false";
                    return success;
                }
        try {
            Message message = new Message();
            message.setMessage(msg);
            Long count = messageMapper.insert(message);
            String[] splits = phones.split("\n");
            List<Send> sends = new ArrayList<>();
            for (String str : splits) {
                Send send = new Send();
                send.setPhone(str);
                send.setUserName(userName);
                send.setMessageId(message.getId());
                sends.add(send);
            }
            sendMapper.insertBatch(sends);
        }catch (Exception e) {
            e.printStackTrace();
            success = "false";
        }
        return success;
    }

    @GetMapping("save/{phones}/{message}/{userName}")
    private Map<String, Object> saveO(@PathVariable("phones")String phones, @PathVariable("message")String msg, @PathVariable("userName")String userName){
        Map<String, Object> map = new HashMap<String, Object>();
        User user =  userMapper.selectByUserName(userName);
        if(user == null){
            map.put("message", "上传失败");
            return map;
        }

        SendMessage temp = new SendMessage();
        temp.setUserName(userName);
        temp.setPhones(phones);
        temp.setMessage(msg);
        temp.setIsSend(0);
        sendMessageMapper.insert(temp);
        map.put("message", "上传成功");
        return map;
    }
}
