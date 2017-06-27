package com.heitian.ssm.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.heitian.ssm.model.TbLogDO;
import com.heitian.ssm.model.TbRepositoryDO;
import com.heitian.ssm.model.info.BaseInfo;
import com.heitian.ssm.service.ShareService;
import com.heitian.ssm.utils.enums.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lxb on 2017/5/6.
 */
@Controller
public class ShareController {
    private Logger log= Logger.getLogger(ShareController.class);

    @Autowired
    private ShareService shareService;
    /**
     * @param request
     *
     */
    @RequestMapping("/getShareData")
//    @ResponseBody
    public @ResponseBody
    Map<String, Object> getShareData(HttpServletRequest request, TbRepositoryDO repositoryDO){
        String pageNo=request.getParameter("page");
        String pageSize=request.getParameter("rows");
        String phone=request.getParameter("phone");
        BaseInfo<List<TbRepositoryDO>> info=shareService.queryshare(phone,repositoryDO,Integer.valueOf(pageNo),Integer.valueOf(pageSize));

        request.setAttribute("phone",phone);
        Long total=shareService.count();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rows", info.getT());
        map.put("total", total);
        return map;
    }
    @RequestMapping("/addShare")
    @ResponseBody
    public String addShare(HttpServletRequest request, TbRepositoryDO repositoryDO){
        BaseInfo info=shareService.addShare(repositoryDO);
        String json=null;
        if(info!=null && info.getCode()== ResultEnum.SUCCESS.getCode()){
            JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
            json=JSON.toJSONString(info, SerializerFeature.WriteDateUseDateFormat);
            System.out.println(json);
        }
        return json;
    }

}
