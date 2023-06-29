package cn.cqut.controller;

import cn.cqut.domain.Image;
import cn.cqut.query.ImageQuery;
import cn.cqut.service.ImageService;
import cn.cqut.utils.JsonResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/image",method = RequestMethod.POST)
public class ImageController {
    @Autowired
    private ImageService imageService;
    @RequestMapping("/queryImageByType")
    public JsonResult queryImageByType(@RequestBody ImageQuery query){
        try{
            QueryWrapper<Image> queryWrapper = new QueryWrapper<Image>()
                    .eq(StringUtils.hasText(query.getImagetype()),"imagetype", query.getImagetype())
                    .eq(query.getImagestate() != null ,"imagestate", query.getImagestate())
                    ;

            List<Image> list =  imageService.list(queryWrapper);
            return JsonResult.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return JsonResult.error("接口出现错误，请联系管理员"+e.getMessage());
        }


    }
}
