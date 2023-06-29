package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("business")
public class  Business {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String busname;
    private String busaddress;
    private String busexplain;
    private String bustell;
    private String servertime;
}
