package cn.cqut.service.impl;

import cn.cqut.domain.Business;
import cn.cqut.mapper.BusinessMapper;
import cn.cqut.service.IBusinessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusinessServiceImpl extends ServiceImpl<BusinessMapper,Business> implements IBusinessService {

    @Override
    public List<Business> getList() {
        List<Business> list = baseMapper.selectList(null);
        return list;
    }
}
