package com.onlineBook.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineBook.entity.Author;
import com.onlineBook.service.AuthorService;

@Controller
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping("/authors")
  public String getAuthorsPage(ModelMap model) {
    List<Author> authors = this.authorService.getAllAuthors();
    model.put("authors", authors);
    return "author/authors";
  }

  @GetMapping("/authors/add")
  public String addAuthorPage() {
    return "author/addAuthor";
  }

  @PostMapping("/authors/add")
  public String addAuthor(@ModelAttribute Author author) {
    this.authorService.addAuthor(author);
    return "redirect:/authors";
  }

  @GetMapping("/authors/delete")
  public String deleteAuthor(@RequestParam("id") int id) {
    this.authorService.deleteAuthorById(id);
    return "redirect:/authors";
  }

  @GetMapping("/authors/description")
  public String getAuthorById(ModelMap modelMap, @RequestParam("id") int id) {
    Optional<Author> author = this.authorService.getAuthorById(id);
    if (author.isPresent()) {
      modelMap.put("author", author);
      return "author/authorDescription";
    }
    return "redirect:/authors";
  }
}
