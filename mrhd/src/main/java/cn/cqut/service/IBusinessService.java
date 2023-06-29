package cn.cqut.service;

import cn.cqut.domain.Business;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IBusinessService extends IService<Business> {
    List<Business> getList();
}
