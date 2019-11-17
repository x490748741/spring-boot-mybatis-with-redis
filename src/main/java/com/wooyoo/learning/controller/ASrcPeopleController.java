package com.wooyoo.learning.controller;

import com.wooyoo.learning.config.CustomConfig;
import com.wooyoo.learning.dao.domain.*;
import com.wooyoo.learning.dao.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 小人动态
 */
@RestController
@RequestMapping("/aspc")
public class ASrcPeopleController {

    @Autowired
    private AonetypeMapper aonetypeMapper;

    @Autowired
    private AtwotypeMapper atwotypeMapper;

    @Autowired
    private AthreetypeMapper athreetypeMapper;

    @Autowired
    private AprojectMapper aprojectMapper;

    @Autowired
    private AprojectDesignMapper aprojectDesignMapper;

    @Autowired
    private AimgMapper aimgMapper;

    @Autowired
    private CustomConfig customConfig;

    @GetMapping("/aonetype")
    public List<Map> getAonetypeList() {

        return aonetypeMapper.selectAll();
    }

    @GetMapping("/atwotype/{ocode}")
    public List<Map> getAtwotypeList(
            @PathVariable("ocode")String ocode) {
        return atwotypeMapper.selectByOcode(ocode);
    }
    @GetMapping("/athreetype/{ocode}")
    public List<Map> getAthreetypeList(
            @PathVariable("ocode")String ocode) {
        return athreetypeMapper.selectByOcode(ocode);
    }

    @GetMapping("/imgSrc/{ocode}")
    public Map getImgSrc(
            @PathVariable("ocode")String ocode) {
        Map map = new HashMap();
        Aimg aImg = aimgMapper.selectByCode(ocode);
        map.put("imgSrc",aImg.getImgSrc());
        return map;
    }

    //获取方案列表
    @GetMapping("/aproject")
    public List<Map> getAprojectList() {
        return aprojectMapper.selectAll();
    }

    @RequestMapping(value = "/aproject/save", method= RequestMethod.POST)
    public Map insertAproject(@RequestParam("fanganName")String name,@RequestBody String adjson){
        Map<String, String> map = new HashMap<String,String>();
        map.put("message", "success");
        //保存
        try {
            Aproject aobj = new Aproject();
            aobj.setName(name);
            aobj.setCreateDate(new Date());
            aobj.setUpdateDate(new Date());
            List<AprojectDesign> listObjectFir = JSONObject.parseArray(adjson,AprojectDesign.class);//(List<AprojectDesign>) JSONArray.parse(adjson);
            Long id = aprojectMapper.insert(aobj);
            for(int i=0;i<listObjectFir.size();i++){
                //listObjectFir.get(i).setAprojectId(id);
                AprojectDesign temp = listObjectFir.get(i);
                temp.setAprojectId(aobj.getId());
            }
            aprojectDesignMapper.insertBatch(listObjectFir);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "error");
        }
        return map;
    }

    @RequestMapping(value = "/aproject/update", method= RequestMethod.POST)
    public Map updateAproject(@RequestParam("id")Long id,@RequestParam("fanganName")String name,@RequestBody String adjson){
        Map<String, String> map = new HashMap<String,String>();
        map.put("message", "success");
        //保存
        try {
            Aproject aobj = new Aproject();
            aobj.setId(id);
            aobj.setName(name);
            aobj.setCreateDate(new Date());
            aobj.setUpdateDate(new Date());
            List<AprojectDesign> listObjectFir = JSONObject.parseArray(adjson,AprojectDesign.class);//(List<AprojectDesign>) JSONArray.parse(adjson);
            aprojectMapper.update(aobj);
            for(int i=0;i<listObjectFir.size();i++){
                //listObjectFir.get(i).setAprojectId(id);
                AprojectDesign temp = listObjectFir.get(i);
                temp.setAprojectId(aobj.getId());
            }
            aprojectDesignMapper.truncateTableByPid(id);
            aprojectDesignMapper.insertBatch(listObjectFir);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "error");
        }

        return map;
    }


    //获取方案详情
    @GetMapping("/aproject/{aprojectId}")
    public List<Map> getAprojectDesignByPid(@PathVariable("aprojectId")String aprojectId){
       return aprojectDesignMapper.selectByPid(aprojectId);
    }

    //删除方案
    @GetMapping("/deleteAproject/{aprojectId}")
    public Map deleteAproject(@PathVariable("aprojectId")Long aprojectId){
        Map<String, String> map = new HashMap<String,String>();
        map.put("message", "success");
        //删除
        try {
            aprojectMapper.deleteByPid(aprojectId);
            aprojectDesignMapper.truncateTableByPid(aprojectId);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("message", "error");
        }

        return map;
    }

    //图片
    @RequestMapping(value = "/import", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, String> importImg(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, String> map = new HashMap<String,String>();
        if (file.isEmpty()) {
            System.out.println("文件为空");
        }
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = customConfig.getImgSrc(); // 上传后的路径
        fileName = UUID.randomUUID() + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String filename = "/temp-rainy/" + fileName;
        map.put("imgUrl", dest.getAbsolutePath());
        return map;
    }
}
