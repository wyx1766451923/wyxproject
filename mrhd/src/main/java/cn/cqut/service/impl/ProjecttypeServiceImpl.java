package cn.cqut.service.impl;

import cn.cqut.domain.Business;
import cn.cqut.domain.ProjectType;
import cn.cqut.mapper.ProjecttypeMapper;
import cn.cqut.service.IprojecttypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjecttypeServiceImpl extends ServiceImpl<ProjecttypeMapper,ProjectType> implements IprojecttypeService {

    @Override
    public List<ProjectType> getList() {
        List<ProjectType> list = baseMapper.selectList(null);
        return list;
    }
}
