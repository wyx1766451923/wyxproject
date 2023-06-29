package cn.cqut.service;

import cn.cqut.domain.Business;
import cn.cqut.domain.Technician;
import cn.cqut.query.BusinessQuery;
import cn.cqut.query.TechnicianQuery;
import cn.cqut.utils.PageList;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface ITechnicianService extends IService<Technician> {
    List<Technician> getTechnicians(TechnicianQuery query);

    PageList<Technician> getTechniciansPage(TechnicianQuery query);

    void deleteById(Technician technician);
}
