package cn.cqut.query;

import lombok.Data;

@Data
public class TechnicianQuery extends BaseQuery{
    private String techname;
    private Integer busid;

    private String proname;
    private Long protypeid;
}
