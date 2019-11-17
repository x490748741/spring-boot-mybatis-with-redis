package com.wooyoo.learning.controller;

import com.wooyoo.learning.config.CustomConfig;
import com.wooyoo.learning.dao.domain.Mvalidate;
import com.wooyoo.learning.dao.domain.User;
import com.wooyoo.learning.dao.mapper.UserMapper;
import com.wooyoo.learning.service.ImportPhoneService;
import com.wooyoo.learning.service.ImportService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登录
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired(required=true)
     private ImportService importService;

    @Autowired(required=true)
    private ImportPhoneService importPhoneService;

    @Autowired
    private CustomConfig customConfig;

     //导入excel
     @RequestMapping(value = "/import", method= RequestMethod.POST)
     @ResponseBody
     public Map<String, Object> importExcel(@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
                 Map<String, Object> map = new HashMap<String, Object>();
                 String result = importService.readExcelFile(file);
                 map.put("message", result);
                 return map;
     }

    //导入电话号码excel
    @RequestMapping(value = "/importPhone/{userName}", method= RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> importPhoneExcel(@PathVariable("userName")
                                                            String userName,@RequestParam(value="file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        String result = importPhoneService.readExcelFile(file,userName);
        map.put("message", result);
        return map;
    }

    //导出excel
    @GetMapping(value = "/export")
    @ResponseBody
    public void exportExcel(HttpServletRequest request, HttpServletResponse response){
        HSSFWorkbook wb = importService.exportExcel();
        try {
            String fileName = new String(( "导出结果.xls").getBytes(), "UTF-8");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception var8) {
            var8.printStackTrace();
        }
    }

    //导出excel
    @GetMapping(value = "/exportPhoneTemplate")
    @ResponseBody
    public String exportPhoneTemplate(HttpServletRequest request,
                               HttpServletResponse response) throws UnsupportedEncodingException {
//        String fileName = "phonetemplate.xls";
//        String excelPath = getClass().getResource("/template/" + fileName).getPath();
//        logger.info("src："+excelPath);
//        String excelPath = "D://ACrazyLionet/phoneManage/号码上传部署相关文件/模板.xls";

        String excelPath = customConfig.getExcelSrc();
        // 获取指定目录下的第一个文件
        File file = new File(excelPath);
        //String fileName = file.getName(); //下载的文件名
            // 如果文件名存在，则进行下载
            if (file.exists()) {
                // 配置文件下载
                response.setHeader("content-type", "application/octet-stream");
                response.setContentType("application/octet-stream");
                // 下载文件能正常显示中文
                response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(), "UTF-8"));
                // 实现文件下载
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("Download the song successfully!");
                }
                catch (Exception e) {
                    System.out.println("Download the song failed!");
                }
                finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                logger.info("文件下载成功");
            }else{
                logger.info("文件不存在");
            }
        return null;
    }




//      @RequestMapping("/excelUpload")
//      public String excelUploadPage(){
//            return "excelUpload";
//      }
    @RequestMapping("/excelUpload")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("/excelUpload"); //设置对应JSP的模板文件
        return modelAndView;
    }


    /**
     * 验证
     * @param name
     * @param validateCode
     * @return
     */
    @PostMapping("/{name}/{validateCode}")
    public Map<String, Object> login(@PathVariable("name")
                              String name, @PathVariable("validateCode") String validateCode){
        String str = "";
        List<Mvalidate> list = importService.selectList(name,validateCode);
        Map<String, Object> map = new HashMap<String, Object>();
        if(list==null||list.size()==0) {
            map.put("message", "验证失败");
            map.put("result", "验证不通过，请确认信息");
        }else{
            StringBuffer sbf = new StringBuffer();
            for (Mvalidate temp: list) {
                sbf.append(temp.getResult()+"\n");

            }
            map.put("message", "验证成功\n结果:\n");
            map.put("result", sbf);
        }
        return map;
    }
}
