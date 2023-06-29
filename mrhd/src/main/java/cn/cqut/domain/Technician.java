package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@TableName("technician")
public class Technician {
    @TableId(type = IdType.AUTO)
    private Long id;//递增
    private String techexplain;
    private String techname;
    private Long busid;
    private String imageurl;

    @TableField(exist = false)
    private List<Long> proids = new ArrayList<>();
    @TableField(exist = false)
    private Business business;
    @TableField(exist = false)
    private List<Project> projects = new ArrayList<>();
    @TableField(exist = false)
    private boolean imgIsChanged;
    @TableField(exist = false)
    private String oldImageurl;


    public boolean getImgIsChanged() {
        return imgIsChanged;
    }
}
