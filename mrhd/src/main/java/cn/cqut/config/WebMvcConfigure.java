package cn.cqut.config;



import cn.cqut.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
@Configuration
public class WebMvcConfigure extends WebMvcConfigurerAdapter{
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册拦截器
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/**")             //拦截所有请求
                .excludePathPatterns("/login","/technician/**","/wechatLogin", "/index", "/userList", "/static/**","/technicianList","/projectList","/userInfoChange");     //要放行的请求
    }
}