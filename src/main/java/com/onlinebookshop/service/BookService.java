package com.onlinebookshop.service;

import com.onlinebookshop.model.Book;
import com.onlinebookshop.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    // CREATE (Add a new book)
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // READ (Get all books)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // READ (Get book by ID)
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // UPDATE (Edit existing book)
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id).map(book -> {
            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setGenre(updatedBook.getGenre());
            book.setPrice(updatedBook.getPrice());
            book.setStockQuantity(updatedBook.getStockQuantity());
            return bookRepository.save(book);
        }).orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    // DELETE (Remove a book)
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
