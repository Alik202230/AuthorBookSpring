package com.onlineBook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineBook.entity.Author;
import com.onlineBook.entity.Book;
import com.onlineBook.service.AuthorService;
import com.onlineBook.service.BookService;

@Controller
@RequestMapping("/books")
public class BookController {

  private final BookService bookService;
  private final AuthorService authorService;

  public BookController(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

  @GetMapping
  public String bookPage(ModelMap modelMap) {
    List<Book> books = this.bookService.getAllBooks();
    modelMap.put("books", books);
    return "book/books";
  }

  @GetMapping("/add")
  public String getAllBooks(ModelMap modelMap) {
    List<Author> authors = authorService.getAllAuthors();
    modelMap.put("authors", authors);
    return "book/addBook";
  }

  @PostMapping("/add")
  public String addBook(@ModelAttribute Book book) {
    this.bookService.addBook(book);
    return "redirect:/books";
  }

  @GetMapping("/details")
  public String getBookById(@RequestParam("id") int id, ModelMap modelMap) {
    Optional<Book> bookOptional = this.bookService.getBookById(id);
    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      modelMap.put("book", book);
      return "book/bookDetails";
    }
    return "redirect:/books";
  }

}
