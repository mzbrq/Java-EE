package com.linx.books.controller;


import com.linx.books.model.BookInfo;
import com.linx.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/getBookList")
    public List<BookInfo> getBookList() {
//        BookService bookService = new BookService();
        return bookService.getBookList();
    }
}
