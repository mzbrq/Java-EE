package com.linx.books.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookInfo {
    private Integer id;
    private String bookName;
    private String author;
    private Integer num;
    private BigDecimal price;
    private String publishName;
    private Integer status;// 1 --> 可借阅, 2 --> 不可借阅
    private String statusCN;
}
