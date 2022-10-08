package com.metaxiii.fr.service.mapper;

import com.metaxiii.fr.dto.BookDTO;
import com.metaxiii.fr.entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    Book toEntity(BookDTO book);
}
