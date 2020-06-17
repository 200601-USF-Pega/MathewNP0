package com.revature.libraryconsoleapp.service;

import com.revature.libraryconsoleapp.dao.BookRepoDB;
import com.revature.libraryconsoleapp.dao.CatalogRepoDB;
import com.revature.libraryconsoleapp.models.Book;
import com.revature.libraryconsoleapp.models.Catalog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryService {

    private CatalogRepoDB inventoryRepoDB = new CatalogRepoDB();
    private BookRepoDB bookRepoDB = new BookRepoDB();

    public int printInventory() {
        /**
        Map<Integer, Integer> inventoryMap = inventoryRepoDB.inventoryAvailable();
        Map<Integer, Book> bookMap = bookRepoDB.getMappedBookList();
        Map<Book , Integer> freePool = new HashMap<>();
        for (Map.Entry<Integer,Integer> entry : inventoryMap.entrySet()){
            freePool.put(bookMap.get(entry.getKey()), entry.getValue());
        }
        for (Map.Entry<Book ,Integer> entry : freePool.entrySet())
            System.out.println("Key = " + entry.getKey().getTitle() + ", Copies = " + entry.getValue());
        **/

        List<Catalog>  catalogList  = new CatalogRepoDB().getAllCatalogs();

        for (Catalog catalog: catalogList){
            System.out.println(catalog);
        }
        return catalogList.size();
    }

    public Map<Integer, Catalog> getInventoryMap(){
        Map<Integer, Catalog> catalogMap = new HashMap<>();
        List<Catalog>  catalogList  = new CatalogRepoDB().getAllCatalogs();
        int index = 1;
        for(Catalog catalog: catalogList) {
            System.out.println(catalog.getBook().getTitle());
            catalogMap.put(1, catalog);
            index++;
        }
        return catalogMap;
    }
}

