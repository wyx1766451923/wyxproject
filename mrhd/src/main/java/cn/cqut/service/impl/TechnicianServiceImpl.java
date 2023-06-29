package cn.cqut.service.impl;

import cn.cqut.domain.Business;
import cn.cqut.domain.Project;
import cn.cqut.domain.Technician;
import cn.cqut.mapper.TechnicianMapper;
import cn.cqut.query.BusinessQuery;
import cn.cqut.query.TechnicianQuery;
import cn.cqut.service.ITechnicianService;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

@Service
public class TechnicianServiceImpl extends ServiceImpl<TechnicianMapper,Technician> implements ITechnicianService {
    @Autowired
    private TechnicianMapper technicianMapper;
    @Value("/static/img/technician/")
    private String technicianImgPath;
    @Override
    public List<Technician> getTechnicians(TechnicianQuery query) {
        return baseMapper.getTechnicians(query);
    }

    @Override
    public PageList<Technician> getTechniciansPage(TechnicianQuery query) {
        Long total = baseMapper.getTechniciansCount(query);
        List<Technician> list =  baseMapper.getTechniciansPage(query);
        return new PageList<Technician>(total,list);
    }
    //删除数据
    @Override
    public void deleteById(Technician technician) {
            String imageurl = technician.getImageurl();
            System.out.println(imageurl);
            if(StringUtils.hasText(imageurl)){
                imageurl = imageurl.substring(imageurl.lastIndexOf("/") + 1);
                String path1 = "G:/cqutmrxmsx/mrhd/src/main/resources" + technicianImgPath + imageurl;
                String path2 = "G:/cqutmrxmsx/mrhd/target/classes" + technicianImgPath + imageurl;
//            System.out.println("path1="+path1);
//            System.out.println("path2="+path2);

               try {
                   File file1 = new File(path1);
                   File file2 = new File(path2);
                   if(file1.exists()) {
                       System.out.println("path1存在");
                       file1.delete();
                   }
                   if(file2.exists()){
                       System.out.println("path2存在");
                       file2.delete();
                   }
               }catch (Exception e){
                   e.printStackTrace();
               }


            }
            baseMapper.deleteBatchTecproByTecid(technician.getId());
            technicianMapper.deleteById(technician.getId());
    }

    @Override
    public boolean save(Technician technician){
        boolean saveRes = super.save(technician);
        if(technician.getProids() != null && technician.getProids().size()>0){
            baseMapper.insertToTecpro(technician.getId(),technician.getProids());
        }
        return saveRes;
    }

    @Override
    public boolean updateById(Technician technician) {
        boolean updateRes = super.updateById(technician);
        //2.判断图片是否已改变，如果已改变，则要删除原先的旧图片
        if(technician.getImgIsChanged()){
            String imageurl = technician.getOldImageurl();
            System.out.println(imageurl);
            if(StringUtils.hasText(imageurl)){
                imageurl = imageurl.substring(imageurl.lastIndexOf("/") + 1);
                String path1 = "G:/cqutmrxmsx/mrhd/src/main/resources" + technicianImgPath + imageurl;
                String path2 = "G:/cqutmrxmsx/mrhd/target/classes" + technicianImgPath + imageurl;
                File file1 = new File(path1);
                File file2 = new File(path2);
                if(file1.exists())  file1.delete();
                if(file2.exists())  file2.delete();
            }
        }

        //3.维护中间表数据
        //     *      先根据技师id删除所有数据
        baseMapper.deleteBatchTecproByTecid(technician.getId());

        //     *      再重新批量添加
        if(technician.getProids() != null && technician.getProids().size() > 0){
            baseMapper.insertToTecpro(technician.getId(), technician.getProids());
        }

        return updateRes;
    }
}
