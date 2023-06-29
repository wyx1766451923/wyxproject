package cn.cqut.mapper;

import cn.cqut.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<User> {
    User selectByUsernameOrByPhone(String username);
}
