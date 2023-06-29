package cn.cqut.service.impl;

import cn.cqut.domain.Image;
import cn.cqut.domain.Project;
import cn.cqut.mapper.ImageMapper;
import cn.cqut.mapper.ProjectMapper;
import cn.cqut.query.ProjectQuery;
import cn.cqut.service.IProjectService;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.Serializable;
import java.util.List;

@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper,Project> implements IProjectService {
    @Autowired
    private ImageMapper imageMapper;
    @Value("${file.upload.img.project}")
    private String projectImgPath;
    @Autowired
    private ProjectMapper projectMapper;
    @Override
    public List<Project> getProinfos(ProjectQuery query) {
        return baseMapper.getProinfos(query);
    }

    @Override
    public PageList<Project> getProinfosPage(ProjectQuery query) {
        Long total = baseMapper.getProinfosCount(query);
        List<Project> list =  baseMapper.getProinfosPage(query);
        return new PageList<Project>(total,list);
    }

    @Override
    public List<Project> listByIsOwn(Long tecid) {
        return baseMapper.listByIsOwn(tecid);
    }



    @Override
    @Transactional
    public boolean save(Project project){
        //将图片保存到图片表获取图片的主键
        Image image = new Image()
                .setImageurl(project.getImageurl())
                .setImagestate(1)
                .setImagetitle("产品")
                .setImagetype("pro");
        //mybatisplus由于单表操作没有SQL语句，尤其是新增，它不确定你是否需要返回主键值，所以就统一只要是insert单行数据都会默认返回主键
        imageMapper.insert(image);//返回主键
        //保存project表的数据
        project.setImageid(image.getId());
        return super.save(project);

    }

    @Override
    @Transactional
    public boolean updateById(Project project) {
        if(project.getImgIsChanged()){
            //删除原先的图片文件
            String imageurl = project.getImage().getImageurl();
            System.out.println(imageurl);
            imageurl = imageurl.substring(imageurl.lastIndexOf("/") + 1);
            String path1 = "G:/cqutmrxmsx/mrhd/src/main/resources" + projectImgPath + imageurl;
            String path2 = "G:/cqutmrxmsx/mrhd/target/classes" + projectImgPath + imageurl;

            File file1 = new File(path1);
            File file2 = new File(path2);
            if(file1.exists()) {
                file1.delete();
            }
            if(file2.exists()) {
                file2.delete();
            }

            //先删除原先的旧图片
            imageMapper.deleteById(project.getImageid());

            //再保存新的图片数据
            Image image = new Image()
                    .setImageurl(project.getImageurl())
                    .setImagestate(1)
                    .setImagetitle("产品")
                    .setImagetype("pro");
            //mybatisplus由于单表操作没有SQL语句，尤其是新增，它不确定你是否需要返回主键值，所以就统一只要是insert单行数据都会默认返回主键
            imageMapper.insert(image);
            project.setImageid(image.getId());
        }
        return super.updateById(project);
    }
    @Override
    public void deleteById(Project project) {
        String imageurl = project.getImage().getImageurl();
        System.out.println(imageurl);
        imageurl = imageurl.substring(imageurl.lastIndexOf("/") + 1);
        String path1 = "G:/cqutmrxmsx/mrhd/src/main/resources" + projectImgPath + imageurl;
        String path2 = "G:/cqutmrxmsx/mrhd/target/classes" + projectImgPath + imageurl;
//        System.out.println("path1="+path1);
//        System.out.println("path2="+path2);
        File file1 = new File(path1);
        File file2 = new File(path2);
        if(file1.exists()) {
            System.out.println("path1存在");
            file1.delete();
        }
        if(file2.exists()) {
            System.out.println("path2存在");
            file2.delete();
        }
        projectMapper.deleteById(project.getId());
    }
}
