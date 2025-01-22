package com.example.softlearning.applicationcore.entity.book.appservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.example.softlearning.applicationcore.entity.book.persistence.BookRepository;
import com.example.softlearning.applicationcore.entity.book.dtos.BookDTO;
import com.example.softlearning.applicationcore.entity.book.mappers.BookMapper;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializer;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.Serializers;
import com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers.SerializersCatalog;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.ServiceException;

@Controller
public class BookServicesImpl implements BookServices {

    @Autowired
    private BookRepository bookRepository;
    private Serializer<BookDTO> serializer;

    // ****** Implementing the business logic methods and common featues (clean code design) ******

    protected BookDTO getDTO(int id)  {
        return bookRepository.findById(id).orElse(null );
    }


    protected BookDTO getById(int id) throws ServiceException {
        BookDTO bdto = this.getDTO(id);

        if ( bdto == null ) {
            throw new ServiceException("book " + id + " not found");
        }
        return bdto;
    }
    
    
    protected BookDTO checkInputData(String book) throws ServiceException {
        try {
            BookDTO bdto = (BookDTO) this.serializer.deserialize(book, BookDTO.class);
            BookMapper.bookFromDTO(bdto);
            return bdto;
        } catch (BuildException e) {
            throw new ServiceException("error in the input book data: " + e.getMessage());
        }
    }


    protected BookDTO newBook(String book) throws ServiceException {
        BookDTO bdto = this.checkInputData(book);
          
        if (this.getDTO(bdto.getId()) == null) {
            return bookRepository.save(bdto);
        } 
        throw new ServiceException("book " + bdto.getId() + " already exists");
    }


    protected BookDTO updateBook(String book) throws ServiceException {
        try {
            BookDTO bdto = this.checkInputData(book);
            this.getById(bdto.getId());
            return bookRepository.save(bdto);
        } catch (ServiceException e) {
            throw e;
        }
    }



    // ****** Implementing the interface methods ******

    @Override
    public String getByIdToJson(int id) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.BOOK_JSON)
                .serialize(this.getById(id));
    }


    @Override
    public String getByIdToXml(int id) throws ServiceException {
        return SerializersCatalog.getInstance(Serializers.BOOK_XML)
                .serialize(this.getById(id));
    }

    
    @Override
    public String addFromJson(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.BOOK_JSON);
        return serializer.serialize(this.newBook(book));
    }


    @Override
    public String addFromXml(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.BOOK_XML);
        return serializer.serialize(this.newBook(book));
    }

    @Override
    public String updateOneFromJson(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.BOOK_JSON);
        return serializer.serialize(this.updateBook(book));
    }


    @Override
    public String updateOneFromXml(String book) throws ServiceException {
        this.serializer = SerializersCatalog.getInstance(Serializers.BOOK_XML);
        return serializer.serialize(this.updateBook(book));
    }


    @Override
    public void deleteById(int id) throws ServiceException {
        try {
            this.getById(id);
            bookRepository.deleteById(id);
        } catch (ServiceException e) {
            throw e;
        }
    }
}
