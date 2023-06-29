package cn.cqut.query;

import lombok.Data;

@Data
public class BaseQuery {
    private Integer page = 1;
    private Integer limit = 10;
    //排序字段
    private String orderField = "id";
    //排序方式
    private String orderType = "desc";
    public Integer getStartIndex(){
        return (page - 1) * limit;
    }
}
