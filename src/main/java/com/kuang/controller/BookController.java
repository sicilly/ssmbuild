package com.kuang.controller;

import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {   // controller调service层
    @Autowired
    @Qualifier("BookServiceImpl")
    private BookService bookService;

// 查询全部书籍，展示书籍
    @RequestMapping("/allBook")
    public String list(Model model) {
        List<Books> list = bookService.queryAllBook();  // 查询全部书籍
        model.addAttribute("list", list);  // 把查到的list传给前端
        return "allBook";
    }
// 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddPaper() {
        return "addBook";
    }

//    添加书籍
    @RequestMapping("/addBook")
    public String addPaper(Books books) {
        System.out.println(books);
        bookService.addBook(books);
        // 重定向
        return "redirect:/book/allBook";
    }

//   跳转到修改页面
    @RequestMapping("/toUpdateBook")
    public String toUpdateBook(Model model, int id) {
        Books books = bookService.queryBookById(id); // 先根据前端传过来的id查到要修改哪本书
        System.out.println(books);
        model.addAttribute("book",books ); // 再把这本书的信息传回给前端
        return "updateBook";
    }

//    修改页面
    @RequestMapping("/updateBook")
    public String updateBook(Model model, Books book) {
        System.out.println(book);  // 输出前端传过来的书籍
        bookService.updateBook(book);  // 修改书籍信息
        return "redirect:/book/allBook";  // 重定向到全部书籍页
    }
}