package com.lytw13.demo;

public enum OperType {
    INSERT("新增",1),
    DELETE("删除",2),
    UPDATE("修改",3),
    SELECT("查找",4);
    // 成员变量
    private String type;
    private Integer code;
    // 构造方法
    private OperType(String type,Integer code) {
        this.type = type;
        this.code = code;
    }

    public Integer getCode() {
        return this.code;
    }
    public String getType() {
        return this.type;
    }
}
