package cn.cqut.service.impl;

import cn.cqut.domain.Order;
import cn.cqut.mapper.OrderMapper;
import cn.cqut.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements IOrderService {
}
