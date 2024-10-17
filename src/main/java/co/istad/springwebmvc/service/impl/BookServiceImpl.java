package co.istad.springwebmvc.service.impl;

import co.istad.springwebmvc.database.StaticDatabase;
import co.istad.springwebmvc.domain.Book;
import co.istad.springwebmvc.dto.BookResponse;
import co.istad.springwebmvc.dto.CreateBookRequest;
import co.istad.springwebmvc.dto.UpdateBookRequest;
import co.istad.springwebmvc.mapper.BookMapper;
import co.istad.springwebmvc.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final StaticDatabase staticDB;
    private final BookMapper bookMapper;

    @Override
    public void deleteBookByCode(String code) {
        Book book = staticDB.getBooks()
                .stream()
                .filter(b -> b.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book code doesn't exist"));
        staticDB.getBooks().remove(book);
    }

    @Override
    public BookResponse updateBook(String code, UpdateBookRequest updateBookRequest) {

        //validate book code
        return staticDB
                .getBooks()
                .stream()
                .filter(b -> b.getCode().equals(code)) //lamda expression
                .peek(b -> bookMapper.fromUpdateBookRequest(b,  updateBookRequest))
                .map(bookMapper::toBookResponse)//method reference
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book code does not exist."
                        )
                );
    }

    @Override
    public BookResponse saveBook(CreateBookRequest createBookRequest) {
        //Transform data from DTO to domain model (DTO Pattern)
        Book book = bookMapper.fromCreateBookRequest(createBookRequest);

        //write system logic
        Random random = new Random();
        book.setId(random.nextInt(1000000, 9000000));
        book.setCode("B-" + book.getId());
        book.setStatus(true);

        //validate
        boolean isIdExisted = staticDB
                .getBooks()
                .stream()
                .anyMatch(b -> b.getId().equals(book.getId()));
        if (isIdExisted) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This id already exists");
        }
        boolean isCodeExisted = staticDB
                .getBooks()
                .stream()
                .anyMatch(b -> b.getCode().equals(book.getCode()));

        if (isCodeExisted) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "This code already exists");
        }


        staticDB.getBooks().add(book);
        return bookMapper.toBookResponse(book);
    }

    @Override
    public List<BookResponse> searchBookByTitleOrStatus(String title, Boolean status) {
        return staticDB
                .getBooks()
                .stream()
                .filter(book -> {
                    if (status != null) {
                        return book
                                .getTitle()
                                .toLowerCase()
                                .contains(title.toLowerCase())
                                && book.getStatus().equals(status);
                    }
                    return book
                            .getTitle()
                            .toLowerCase()
                            .contains(title.toLowerCase());
                })
                .map(book -> BookResponse.builder()
                        .code(book.getCode())
                        .title(book.getTitle())
                        .desc(book.getDesc())
                        .thumbnail(book.getThumbnail())
                        .status(book.getStatus())
                        .build()
                )
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse getBookbycode(String code) {
        return staticDB.getBooks()
                .stream()
                .filter(b -> b.getCode().equals(code))
                .map(book -> BookResponse.builder()
                        .code(book.getCode())
                        .title(book.getTitle())
                        .desc(book.getDesc())
                        .thumbnail(book.getThumbnail())
                        .status(book.getStatus())
                        .build()
                )
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    @Override
    public List<BookResponse> getBook() {
        return staticDB.getBooks()
                .stream()
                .map(book -> BookResponse.builder()
                        .code(book.getCode())
                        .title(book.getTitle())
                        .desc(book.getDesc())
                        .thumbnail(book.getThumbnail())
                        .status(book.getStatus())
                        .build()
                )
                .toList();
    }
}
