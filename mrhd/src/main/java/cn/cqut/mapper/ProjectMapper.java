package cn.cqut.mapper;

import cn.cqut.domain.Project;
import cn.cqut.query.ProjectQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface ProjectMapper extends BaseMapper<Project> {
    List<Project> getProinfos(ProjectQuery query);

    Long getProinfosCount(ProjectQuery query);


    List<Project> getProinfosPage(ProjectQuery query);

    List<Project> listByIsOwn(Long tecid);
}
