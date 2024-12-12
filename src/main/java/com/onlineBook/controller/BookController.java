package com.onlineBook.controller;

import com.onlineBook.entity.Author;
import com.onlineBook.entity.Book;
import com.onlineBook.repository.AuthorRepository;
import com.onlineBook.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  public BookController(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  @GetMapping
  public String bookPage(ModelMap modelMap) {
    List<Book> books = this.bookRepository.findAll();
    modelMap.put("books", books);
    return "book/books";
  }

  @GetMapping("/add")
  public String getAllBooks(ModelMap modelMap) {
    List<Author> authors = authorRepository.findAll();
    modelMap.put("authors", authors);
    return "book/addBook";
  }

  @PostMapping("/add")
  public String addBook(@ModelAttribute Book book) {
    book.setCreatedAt(new Date());
    this.bookRepository.save(book);
    return "redirect:/books";
  }

  @GetMapping("/details")
  public String getBookById(@RequestParam("id") int id, ModelMap modelMap) {
    Optional<Book> bookOptional = this.bookRepository.findById(id);
    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      modelMap.put("book", book);
      return "book/bookDetails";
    }
    return "redirect:/books";
  }

}
