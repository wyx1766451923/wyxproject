package cn.cqut.service;

import cn.cqut.domain.Project;
import cn.cqut.query.ProjectQuery;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IProjectService extends IService<Project> {
    List<Project> getProinfos(ProjectQuery query);

    PageList<Project> getProinfosPage(ProjectQuery query);

    List<Project> listByIsOwn(Long tecid);

    void deleteById(Project project);
}
