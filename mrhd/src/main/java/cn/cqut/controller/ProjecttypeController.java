package cn.cqut.controller;

import cn.cqut.domain.Business;
import cn.cqut.domain.ProjectType;
import cn.cqut.service.IBusinessService;
import cn.cqut.service.IprojecttypeService;
import cn.cqut.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("projecttype")
public class ProjecttypeController {
    @Autowired
    private IprojecttypeService projecttypeService ;
    @RequestMapping(value = "getList",method = RequestMethod.GET)
    public JsonResult getList(){
        try {

            List<ProjectType> list =  projecttypeService.getList();
            return JsonResult.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }
}