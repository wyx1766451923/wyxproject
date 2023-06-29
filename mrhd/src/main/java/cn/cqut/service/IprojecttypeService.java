package cn.cqut.service;

import cn.cqut.domain.Business;
import cn.cqut.domain.ProjectType;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IprojecttypeService extends IService<ProjectType> {
    List<ProjectType> getList();
}
