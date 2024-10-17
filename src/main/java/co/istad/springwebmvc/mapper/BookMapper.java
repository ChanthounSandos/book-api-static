package co.istad.springwebmvc.mapper;

import co.istad.springwebmvc.domain.Book;
import co.istad.springwebmvc.dto.BookResponse;
import co.istad.springwebmvc.dto.CreateBookRequest;
import co.istad.springwebmvc.dto.UpdateBookRequest;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateBookRequest(@MappingTarget Book book, UpdateBookRequest updateBookRequest);

    /**
     * Target data source (target source)
     * Original data source
     **/
    Book fromCreateBookRequest(CreateBookRequest createBookRequest);

    BookResponse toBookResponse(Book book);

}
