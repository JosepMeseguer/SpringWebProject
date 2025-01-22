package com.example.softlearning.applicationcore.entity.book.persistence;

import java.util.List;
import java.util.Optional;
import com.example.softlearning.applicationcore.entity.book.dtos.BookDTO;


public interface BookRepository  {

    public Optional<BookDTO> findById(int id);

    public List<BookDTO> findByName(String title);
 
    public List<BookDTO> findByPartialTitle(String title);

    public Integer countByPartialTitle(String title);

    public BookDTO save(BookDTO book);
    
    public void deleteById(int id);
    
}
