package com.linx.books.dao;

import com.linx.books.model.BookInfo;
import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@Data
public class BookDao {
    public List<BookInfo> mockData() {
        List<BookInfo> bookInfos = new ArrayList<>();

        //还没有使用数据库，先 mock数据
        for (int i = 1; i <= 15; i++) {
            BookInfo bookInfo = new BookInfo();
            bookInfo.setId(i);
            bookInfo.setBookName("书名" + i);
            bookInfo.setAuthor("作者" + i);
            bookInfo.setNum(3 * i);
            bookInfo.setPrice(new BigDecimal(i * 2));
            bookInfo.setPublishName("出版社" + i);
            if (i % 5 == 0) {
                bookInfo.setStatus(2);//不可借阅
//                bookInfo.setStatusCN("不可借阅");
            } else {
                bookInfo.setStatus(1);
//                bookInfo.setStatusCN("可借阅");
            }
            bookInfos.add(bookInfo);
        }
        return bookInfos;
    }
}
