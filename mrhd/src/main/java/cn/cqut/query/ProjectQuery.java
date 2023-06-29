package cn.cqut.query;

import lombok.Data;

@Data
public class ProjectQuery extends BaseQuery{
    private Double propriceMin;
    private Double propriceMax;
    private Long protypeid;
    private String proname;
    private Integer prostatus;
    private Long busid;
}
