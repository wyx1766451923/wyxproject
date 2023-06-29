package cn.cqut.query;

import lombok.Data;

@Data
public class UserQuery extends BaseQuery{
    private String username;
    private String nickname;
    private String phone;


}
