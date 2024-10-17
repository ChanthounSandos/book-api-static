package co.istad.springwebmvc.controller;

import co.istad.springwebmvc.dto.BookResponse;
import co.istad.springwebmvc.dto.CreateBookRequest;
import co.istad.springwebmvc.dto.UpdateBookRequest;
import co.istad.springwebmvc.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookRestController {

    private final BookService bookService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{code}")
    void deleteBook(@PathVariable String code){
        bookService.deleteBookByCode(code);
    }

    @PutMapping("/{code}")
    BookResponse updateBook(@PathVariable String code, @Valid @RequestBody UpdateBookRequest updateBookRequest){
        return bookService.updateBook(code, updateBookRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    BookResponse saveBook(@Valid @RequestBody CreateBookRequest createBookRequest){
        return bookService.saveBook(createBookRequest);
    }

    @GetMapping("/search")
    public List<BookResponse> searchBookByTitle(
            @RequestParam(name = "k") String keyword, //if don't have name, can use argument to call
            @RequestParam(required = false) Boolean status
    ) {
        return bookService.searchBookByTitleOrStatus(keyword, status);
    }

    @GetMapping("/{code}")
    public BookResponse getBookByCode(@PathVariable("code") String c) {
        System.out.println("Code: " + c);
        return bookService.getBookbycode(c);
    }

    @GetMapping
    public List<BookResponse> getBook() {
        return bookService.getBook();
    }

}
