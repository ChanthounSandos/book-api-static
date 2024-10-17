package co.istad.springwebmvc.service;

import co.istad.springwebmvc.dto.BookResponse;
import co.istad.springwebmvc.dto.CreateBookRequest;
import co.istad.springwebmvc.dto.UpdateBookRequest;

import java.util.List;

public interface BookService {

    //delete book
    void deleteBookByCode(String code);

    //update existing book
    BookResponse updateBook(String code, UpdateBookRequest updateBookRequest);

    /**
     * add new book
     * @params CreateBookRequest is request data from client
     * @return BookResponse
     */
    BookResponse saveBook (CreateBookRequest createBookRequest);

    //Search book by title or status
    //return type
    //parameter (data)
    List<BookResponse> searchBookByTitleOrStatus(String title, Boolean status);
    // get book by code
    BookResponse getBookbycode(String code);
    // get books
    List<BookResponse> getBook();
}
