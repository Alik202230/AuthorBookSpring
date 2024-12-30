package com.onlineBook.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlineBook.entity.Author;
import com.onlineBook.service.AuthorService;
import com.onlineBook.specification.SearchCriteria;

@Controller
@RequestMapping("/authors")
public class AuthorController {

  private final AuthorService authorService;

  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public String authorPage(ModelMap modelMap, 
      @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber,
      @RequestParam(value = "pageSize", defaultValue = "3") int pageSize
  ) {

    Sort sort = Sort.by(Sort.Direction.DESC, "id");
    PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize, sort);

    Page<Author> authorPage = this.authorService.getAllAuthors(pageRequest);
    int totalPage = authorPage.getTotalPages();

    if (totalPage > 0) {
      List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPage)
          .boxed()
          .toList();
      modelMap.addAttribute("pageNumbers", pageNumbers);
    }

    modelMap.put("authors", authorPage);
    return "author/authors";
  }

  @GetMapping("/search")
  public String searchAuthor(@RequestParam("keyword") String keyword, ModelMap modelMap) {
    List<Author> result = this.authorService.search(keyword);
    modelMap.put("authors", result);
    return "author/search";
  }

  @GetMapping("/filter")
  public String filterAuthor(@ModelAttribute SearchCriteria criteria, ModelMap modelMap) {
    List<Author> result = this.authorService.filter(criteria);
    modelMap.put("name", criteria.getName());
    modelMap.put("surname", criteria.getSurname());
    modelMap.put("phone", criteria.getPhone());
    modelMap.put("authors", result);
    return "author/search";
  }

  @GetMapping("/authors")
  public String getAuthorsPage(ModelMap model) {
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
