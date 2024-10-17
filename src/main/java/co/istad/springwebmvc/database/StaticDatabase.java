package co.istad.springwebmvc.database;

import co.istad.springwebmvc.domain.Book;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Getter
public class StaticDatabase {

    private final List<Book> books;

    public StaticDatabase() {

        books = new ArrayList<>();

        Book book = new Book();
        book.setId(1);
        book.setCode("B-001");
        book.setTitle("Avenger");
        book.setDesc("Description");
        book.setThumbnail("/images/bookavenger.jpg");
        book.setStatus(false);

        Book book2 = new Book();
        book2.setId(2);
        book2.setCode("B-002");
        book2.setTitle("Avenger: EndGame");
        book2.setDesc("Description of EndGame");
        book2.setThumbnail("/images/bookavenger.jpg");
        book2.setStatus(true);

        Book book3 = new Book();
        book3.setId(3);
        book3.setCode("B-003");
        book3.setTitle("Go Ahead");
        book3.setDesc("Dramatic");
        book3.setThumbnail("/images/goahead.jpg");
        book3.setStatus(true);

        books.add(book);
        books.add(book2);
        books.add(book3);
    }

}
