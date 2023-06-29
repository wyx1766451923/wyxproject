package cn.cqut.Dto;

import lombok.Data;

@Data
public class WechatUserSession {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
