package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("project")
public class Project {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String proexplain;
    private Double proprice;
    private String prostep;
    private Long protypeid;
    private String proname;
    private Long imageid;
    private Integer prostatus = 1;
    private Long busid;
    private Long tecid;
    @TableField(exist = false)
    private Image image;
    @TableField(exist = false)
    private ProjectType projectType;
    @TableField(exist = false)
    private String imageurl;
    @TableField(exist = false)
    private boolean imgIsChanged;
    @TableField(exist = false)
    private Long isOwn;
    @TableField(exist = false)
    private Business business;


    public boolean getImgIsChanged() {
        return imgIsChanged;
    }
}
