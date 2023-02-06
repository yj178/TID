package com.sdyj178.backendTest.DTO;


import org.springframework.web.bind.annotation.PathVariable;

public class TestDto {
    private long num;
    private String str;

    public TestDto(long num, String str) {
        this.num = num;
        this.str = str;
    }

    public long getNum() {
        return num;
    }

    public void setNum(long num) {
        this.num = num;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "TestDto{" +
                "num=" + num +
                ", str='" + str + '\'' +
                '}';
    }
}
