package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName("image")
public class Image {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String imageurl;
    private String imagetitle;
    private String imagetype;
    private Integer imagestate;


}
