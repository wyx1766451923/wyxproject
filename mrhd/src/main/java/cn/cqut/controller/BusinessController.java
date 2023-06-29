package cn.cqut.controller;

import cn.cqut.domain.Business;
import cn.cqut.domain.Project;
import cn.cqut.query.BusinessQuery;
import cn.cqut.query.ProjectQuery;
import cn.cqut.service.IBusinessService;
import cn.cqut.service.IProjectService;
import cn.cqut.service.ITechnicianService;
import cn.cqut.utils.JsonResult;
import cn.cqut.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("business")
public class BusinessController {
    @Autowired
    private IBusinessService businessService ;
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public JsonResult getList(){
        try {

            List<Business> list =  businessService.getList();
            return JsonResult.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }
}

