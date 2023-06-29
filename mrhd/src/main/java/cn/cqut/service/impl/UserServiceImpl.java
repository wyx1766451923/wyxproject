package cn.cqut.service.impl;


import cn.cqut.Dto.LoginDto;
import cn.cqut.Dto.WechatUserSession;
import cn.cqut.domain.User;
import cn.cqut.mapper.UserMapper;
import cn.cqut.service.IUserService;
import cn.cqut.utils.jwt.JwtUtils;
import cn.cqut.utils.jwt.PayLoadData;
import cn.cqut.utils.jwt.RsaUtils;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Value("${wechat.miniProgram.appid}")
    //    private String appid="wx54932d7e715c6c43";
    private String appid;
    @Value("${wechat.miniProgram.appSecret}")
    private String appsecret;
    @Value("${wechat.miniProgram.code2SessionUrl}")
    private String code2SessionUrl;
    @Value("/static/img/user/")
    private String userImgPath;




    @Override
    public Map<String, Object> login(LoginDto loginDto) {
        //查询用户是否存在
        User user = baseMapper.selectByUsernameOrByPhone(loginDto.getUsername());
        Assert.isTrue(user != null, "用户名不存在！");

        //查看密码是否正确
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean matches = passwordEncoder.matches(loginDto.getPassword(), user.getPassword());
        Assert.isTrue(matches, "密码错误！");

        //登陆成功，调用jwt生成jtwtoken
        return loginSuccess(user);
    }

    @Override
    public Map<String, Object> wechatLogin(LoginDto loginDto) {
//        System.out.println(appid+"  "+appsecret+"  "+loginDto.getCode());
        code2SessionUrl = String.format(code2SessionUrl,appid,appsecret,loginDto.getCode());
        String jsonStr = HttpUtil.get(code2SessionUrl);
        System.out.println(jsonStr);
        WechatUserSession wechatUserSession = JSON.parseObject(jsonStr,WechatUserSession.class);
        if(wechatUserSession.getErrcode() != null || StringUtils.hasText(wechatUserSession.getErrmsg()) ){
            throw new RuntimeException("微信登陆失败！"+wechatUserSession.getErrmsg());
        }
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("openid",wechatUserSession.getOpenid()));
        if(user == null){
            user = new User();
            user.setNickname(loginDto.getNickName());
            user.setImageurl(loginDto.getAvatarUrl());
            user.setOpenid(wechatUserSession.getOpenid());
            user.setSessionkey(wechatUserSession.getSession_key());
            baseMapper.insert(user);
        }
        return loginSuccess(user);
    }



    private Map<String, Object> loginSuccess(User user) {
        Map<String, Object> map = new HashMap<>();
        try {
            PayLoadData payLoadData = new PayLoadData();
            user.setPassword(null);     //将密码置空，更安全
            payLoadData.setUser(user);
            //私钥加密
            PrivateKey privateKey = RsaUtils.getPrivateKey(JwtUtils.class.getClassLoader()
                    .getResource("hrm_auth_rsa").getFile());
            String userToken = JwtUtils.generateTokenExpireInMinutes(payLoadData, privateKey, 720);
            map.put("loginUser", user);
            map.put("userToken", userToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean updateById(User user) {
        if(user.getImgIsChanged()){
            String imageurl = user.getOldImageurl();
            System.out.println(imageurl);
            if(StringUtils.hasText(imageurl)){
                imageurl = imageurl.substring(imageurl.lastIndexOf("/") + 1);
                String path1 = "G:/cqutmrxmsx/mrhd/src/main/resources" + userImgPath + imageurl;
                String path2 = "G:/cqutmrxmsx/mrhd/target/classes" + userImgPath + imageurl;
                File file1 = new File(path1);
                File file2 = new File(path2);
                if(file1.exists())  file1.delete();
                if(file2.exists())  file2.delete();
            }
        }
        return super.updateById(user);
    }
}
