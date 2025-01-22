package com.example.softlearning.applicationcore.entity.sharedkernel.appservices.serializers;

import java.util.TreeMap;

import com.example.softlearning.applicationcore.entity.book.dtos.BookDTO;


public class SerializersCatalog {

    private static TreeMap<Serializers, Serializer> catalog = new TreeMap<>();   

    private static void loadCatalog(){
        //AL CARREGAR EL SERIALITZADOR PASSEM PER CONSTRUCTOR L'OBJECTE AMB QUE ES REALIZARÀ LA SERIALITZACIO       

        catalog.put(Serializers.BOOK_JSON, new JacksonSerializer<BookDTO>());
        catalog.put(Serializers.BOOK_XML, new JacksonXMLSerializer<BookDTO>());        
        
    }  

    public static Serializer getInstance(Serializers type){
        if (catalog.isEmpty()){
            loadCatalog();
        }      
        return catalog.get(type);
    }   
}
