package com.example.softlearning.applicationcore.entity.book.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.softlearning.applicationcore.entity.sharedkernel.domainservices.validations.DataCheck;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.BuildException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.exceptions.GeneralDateTimeException;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.physics.PhysicalData;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.physics.Storable;
import com.example.softlearning.applicationcore.entity.sharedkernel.model.products.Product;

public class Book extends Product implements Storable{

    protected PhysicalData physics;
    protected String isbn;
    protected int edition;
    protected LocalDate releaseDate;
    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    protected Book() {}
            
    public static Book getInstance (int id, String name, String owner, String type, String description, 
    double price, String isbn, int edition, String releaseDate, double heigth, 
    double width, double depth, double weigth, boolean fragile) throws BuildException {
        
        String errorMessage = "";
        Book b = new Book();
        
        b.checkData (id, name, owner, type, description, price);
        try {
            b.physics = PhysicalData.getInstance(heigth, width, depth, weigth, fragile);
        } catch (BuildException ex) {
            errorMessage += "Bad PhysicalData: " + ex.getMessage() + ";";
        }
        b.isbn = isbn;
        b.edition = edition;
        try {
            b.setReleaseDate (releaseDate); 
        } catch (GeneralDateTimeException ex) {
            errorMessage += "Bad Release Date: " + ex.getMessage() + ";";
        }
        
        if (errorMessage.length()>0){
            b = null;
            throw new BuildException(errorMessage);
        }else{
            return b;
        }
    }

    @Override
    public String getSize() {
        return this.physics.getSize();
    }

    @Override
    public double getVolum() {
        return this.physics.getVolum();        
    }

    @Override
    public double getWeigth() {
        return this.physics.getWeigth();
    }
    
    public double getHeigth() {
        return this.physics.getHeigth();
    }
    
    public double getWidth() {
        return this.physics.getWidth();
    }
    
    public double getDepth() {
        return this.physics.getDepth();
    }

    @Override
    public boolean isFragile() {
        return this.physics.getFragile();
    }

    @Override
    public int getIdent() {
        return this.getId();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getReleaseDate() {
        return releaseDate.format(formatter);
    }

    public void setReleaseDate(String releaseDate) throws GeneralDateTimeException  {
        this.releaseDate = DataCheck.convertStringToDate(releaseDate, this.formatter);;
    }  
}
