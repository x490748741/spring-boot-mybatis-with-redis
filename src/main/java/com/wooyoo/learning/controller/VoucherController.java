package com.wooyoo.learning.controller;

import com.wooyoo.learning.dao.domain.User;
import com.wooyoo.learning.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 充值
 */
@RestController
@RequestMapping("/voucher")
public class VoucherController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/list")
    public List<Map> getNoSendList(){
        List<Map> list = userMapper.selectAll();
        return list;
    }

    /**
     * 加金额
     * @return
     */
    @GetMapping("/add/{userName}/{balance}")
    public Map addMoney(@PathVariable("userName")String userName,@PathVariable("balance")String balance){
        Map<String, String> map = new HashMap<String,String>();
        User user = userMapper.selectByUserName(userName);
        userMapper.updateBalance(userName,user.getBalance()+Double.valueOf(balance));
        map.put("message", "success");
        return map;
    }

    /**
     * 减金额
     * @return
     */
    @GetMapping("/subtract/{userName}/{balance}")
    public Map subtractMoney(@PathVariable("userName")String userName,@PathVariable("balance")String balance){
        Map<String, String> map = new HashMap<String,String>();
        User user = userMapper.selectByUserName(userName);
        Double balances = user.getBalance();
        Double m = balances-Double.valueOf(balance);
        if(m<0){
            map.put("message", "余额不足");
        }else{
            userMapper.updateBalance(userName,m);
            map.put("message", "success");
        }
        return map;
    }

}
