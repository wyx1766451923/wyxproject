package cn.cqut.controller;


import cn.cqut.domain.Image;
import cn.cqut.domain.Project;
import cn.cqut.domain.Technician;
import cn.cqut.query.ProjectQuery;
import cn.cqut.query.TechnicianQuery;
import cn.cqut.service.ITechnicianService;
import cn.cqut.utils.JsonResult;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("technician")
public class TechnicianController {

    @Autowired
    private ITechnicianService technicianService;
    @RequestMapping(value = "getTechnicians",method = RequestMethod.POST)
    public JsonResult getTechnicians(@RequestBody TechnicianQuery query){
        try {
            List<Technician> list =  technicianService.getTechnicians(query);
            return JsonResult.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }

    @RequestMapping(value = "getTechniciansPage",method = RequestMethod.POST)
    public JsonResult getTechniciansPage(@RequestBody TechnicianQuery query){
        try {
            PageList<Technician> pageList =  technicianService.getTechniciansPage(query);
            return JsonResult.success(pageList);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }
    @PostMapping("/save")
    public JsonResult save(@RequestBody Technician technician){
        try {
            if(technician.getId() == null){
                //新增
                technicianService.save(technician);
            }else{
                //修改
                technicianService.updateById(technician);
            }
            return JsonResult.success();
        }catch(Exception e){
            e.printStackTrace();
            if(technician.getId() == null){
                //新增
                return JsonResult.error("新增操作失败！");
            }else{
                //修改
                return JsonResult.error("修改操作失败！");
            }
        }
    }

    //    删除数据
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Technician technician){
        try {
            technicianService.deleteById(technician);
            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现一个错误，请联系管理员。"+e.getMessage());
        }
    }

}
