package com.linx.books.service;

import com.linx.books.dao.BookDao;
import com.linx.books.model.BookInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookDao bookDao;

    public List<BookInfo> getBookList() {
//        BookDao bookDao = new BookDao();
        List<BookInfo> bookInfos =  bookDao.mockData();

        for (BookInfo bookInfo:
             bookInfos) {
            if (bookInfo.getStatus() == 2) {
                bookInfo.setStatusCN("不可借阅");
            } else {
                bookInfo.setStatusCN("可借阅");
            }
        }
        return bookInfos;
    }
}
