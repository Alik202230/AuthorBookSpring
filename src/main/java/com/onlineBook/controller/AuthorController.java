package com.onlineBook.controller;

import com.onlineBook.entity.Author;
import com.onlineBook.repository.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AuthorController {

  private final AuthorRepository authorRepository;

  public AuthorController(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @GetMapping("/authors")
  public String getAuthorsPage(ModelMap model) {
    List<Author> authors = authorRepository.findAll();
    model.put("authors", authors);
    return "author/authors";
  }

  @GetMapping("/authors/add")
  public String addAuthorPage() {
    return "author/addAuthor";
  }

  @PostMapping("/authors/add")
  public String addAuthor(@ModelAttribute Author author) {
    authorRepository.save(author);
    return "redirect:/authors";
  }

  @GetMapping("/authors/delete")
  public String deleteAuthor(@RequestParam("id") int id) {
    authorRepository.deleteById(id);
    return "redirect:/authors";
  }

  @GetMapping("/authors/description")
  public String getAuthorById(ModelMap modelMap, @RequestParam("id") int id) {
    Optional<Author> authorOptional = authorRepository.findByIdOrElseThrow(id);
    if (authorOptional.isPresent()) {
      Author author = authorOptional.get();
      modelMap.put("author", author);
      return "author/authorDescription";
    }
    return "redirect:/authors";
  }

}
