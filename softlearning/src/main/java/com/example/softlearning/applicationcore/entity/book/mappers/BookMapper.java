package com.example.softlearning.applicationcore.entity.book.mappers;

import com.example.softlearning.applicationcore.entity.book.dtos.BookDTO;
import com.example.softlearning.applicationcore.entity.book.model.Book;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;

public class BookMapper {
    public static Book bookFromDTO(BookDTO bookDTO) throws BuildException {       
        return Book.getInstance(
                bookDTO.getId(),
                bookDTO.getName(),
                bookDTO.getOwner(), 
                bookDTO.getType(),
                bookDTO.getDescription(),
                bookDTO.getPrice(),
                bookDTO.getIsbn(),
                bookDTO.getEdition(),
                bookDTO.getPublicationDate(),
                bookDTO.getWeigth(),
                bookDTO.getHeigth(),
                bookDTO.getWidth(),
                bookDTO.getDepth(),
                bookDTO.getFragile()
        );
    }
    
    public static BookDTO dtoFromBook(Book book) {
        return new BookDTO(
                book.getName(),
                book.getOwner(),
                book.getDescription(),
                book.getType(),
                book.getIsbn(),
                book.getReleaseDate(),
                book.getId(),
                book.getEdition(),
                book.getPrice(),
                book.getWeigth(),
                book.getHeigth(),
                book.getWidth(),
                book.getDepth(),
                book.getAvailability(),
                book.isFragile()
        );
    }
}
