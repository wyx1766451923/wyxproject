package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String nickname;
    private String openid;
    private String imageurl;
    private String sessionkey;
    private String token;
    private String username;
    private String phone;
    private String password;

    @TableField(exist = false)
    private boolean imgIsChanged;
    @TableField(exist = false)
    private String oldImageurl;


    public boolean getImgIsChanged() {
        return imgIsChanged;
    }

}
