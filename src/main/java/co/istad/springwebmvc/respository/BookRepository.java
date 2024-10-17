package co.istad.springwebmvc.respository;

import co.istad.springwebmvc.domain.Book;

import java.util.List;

//access data from database
public interface BookRepository {
    List<Book> findAll();

}
