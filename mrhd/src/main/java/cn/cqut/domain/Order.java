package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@TableName("`order`")
public class Order {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userid;
    private String openid;
    private String proname;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date makedate;
    private String username;

    //0 已预约待支付 1 已支付 2 服务完毕 3 已取消
    private Integer orderstate = 0;
    private Date placedate = new Date();
    private String usertell;
    private String information;
    private Long busid;
    private Long proid;
    private Long tecid;
}
