package cn.cqut.controller;

import cn.cqut.domain.Project;
import cn.cqut.query.ProjectQuery;
import cn.cqut.service.IProjectService;
import cn.cqut.service.ImageService;
import cn.cqut.utils.JsonResult;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
    @Autowired
    private IProjectService projectService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "getProinfos",method = RequestMethod.POST)
    public JsonResult getProinfos(@RequestBody ProjectQuery query){
        try {

            List<Project> list =  projectService.getProinfos(query);
            return JsonResult.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }
    @RequestMapping(value = "getProinfosPage",method = RequestMethod.POST)
    public JsonResult getProinfosPage(@RequestBody ProjectQuery query){
        try {

            PageList<Project> pageList =  projectService.getProinfosPage(query);
            return JsonResult.success(pageList);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现了一个问题，请联系管理员"+e.getMessage());
        }
    }

    @PostMapping("/save")
    public JsonResult save(@RequestBody Project project){
        try {
            if(project.getId() == null){
                //新增
                projectService.save(project);
            }else{
                //修改
                projectService.updateById(project);
            }
            return JsonResult.success();
        }catch(Exception e){
            e.printStackTrace();
            if(project.getId() == null){
                //新增
                return JsonResult.error("新增操作失败！");
            }else{
                //修改
                return JsonResult.error("修改操作失败！");
            }
        }
    }
    @GetMapping("/List")
    public List<Project> List(Long tecid){
        return projectService.listByIsOwn(tecid);
    }

//    删除数据
    @PostMapping("/delete")
    public JsonResult delete(@RequestBody Project project){
        try {
            System.out.println(project.getImageid());
            imageService.removeById(project.getImageid());
            projectService.deleteById(project);
            return JsonResult.success();
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("出现一个错误，请联系管理员。"+e.getMessage());
        }
    }
}
