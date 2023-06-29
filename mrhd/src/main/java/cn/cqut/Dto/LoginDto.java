package cn.cqut.Dto;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
    private String nickName;
    private String avatarUrl;
    private String code;


}
