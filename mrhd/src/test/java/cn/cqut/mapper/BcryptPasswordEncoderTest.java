package cn.cqut.mapper;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderTest {
    @Test
    public void test() throws Exception{
        System.out.println(new BCryptPasswordEncoder().encode("123"));
    }
}