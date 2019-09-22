package com.code.repository.controller;

/**
 * @Author zhaoyuan.lizy on 2019/9/21
 **/

public enum TestEnum {
    LAZADA("1", "Lazada"),
    ALIEXPRESS("2", "AE"),
    TMALL("3", "TMW"),
    Alibaba("4", "1688");

    private String id;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private TestEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
