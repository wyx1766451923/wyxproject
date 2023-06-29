package cn.cqut.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @TableName 用来指定ORM映射的表名称，如果不指定，默认使用当前实体类名称来作为表名称
 */
@TableName("t_employee")
public class Employee {

    /**
     * @TableId 表示主键字段
     *  IdType.AUTO：表示主键自动递增
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * @TableField("name")
     *  用来指定当前属性名称与t_employee表中哪个列名称对应？
     *  如果不指定，默认就使用当前属性名称来作为列名称
     */
    //@TableField("name")
    private String name;

    private String phone;
    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
