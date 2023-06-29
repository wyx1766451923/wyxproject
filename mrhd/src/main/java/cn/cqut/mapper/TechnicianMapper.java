package cn.cqut.mapper;

import cn.cqut.domain.Business;
import cn.cqut.domain.Project;
import cn.cqut.domain.Technician;
import cn.cqut.query.BusinessQuery;
import cn.cqut.query.TechnicianQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface TechnicianMapper extends BaseMapper<Technician> {
    List<Technician> getTechnicians(TechnicianQuery query);

    Long getTechniciansCount(TechnicianQuery query);

    List<Technician> getTechniciansPage(TechnicianQuery query);

    void insertToTecpro(@Param("tecid") Long tecid, @Param("proids") List<Long> proids);

    void deleteBatchTecproByTecid(Long id);
}
