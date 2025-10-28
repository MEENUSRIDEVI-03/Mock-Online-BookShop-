package com.onlinebookshop.controller;

import com.onlinebookshop.model.Book;
import com.onlinebookshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookViewController {

    @Autowired
    private BookService service;

    @GetMapping
    public String viewBooks(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "books";  // matches books.html
    }

    @PostMapping
    public String addBook(@ModelAttribute Book book) {
        service.addBook(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return "redirect:/books";
    }
}
