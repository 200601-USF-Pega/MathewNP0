package com.revature.libraryconsoleapp.service;

import com.revature.libraryconsoleapp.dao.AuthorRepoDB;
import com.revature.libraryconsoleapp.models.Author;

import java.util.List;

public class AuthorService {

        private AuthorRepoDB authorRepoDB = new AuthorRepoDB();
        private ValidationService validationService = new ValidationService();
        private Author selectedAuthor;

        public AuthorService() {

        }

        //check author table for author and get id.
                //you get the id from`
        //if no author create author and then return id.
        public Author authorSelector() {
                 validationService = new ValidationService();
                 List<Author> authorList = authorRepoDB.getAllAuthors();
                 int size = authorList.size();
                 if(size != 0){
                     System.out.println("Known Authors List, if present, enter the number.");
                     for(int i = 0; i < size; i++) {
                         System.out.format("[%d] %s\n", i+1 , authorList.get(i).getFullName());
                     }
                     System.out.println("[0] Author not present, I will create a new author: ");
                     int userInput;
                     do{
                         userInput = validationService.getValidInt("Please enter the number associated with the author");
                         System.out.println(userInput);
                         if (userInput != 0 && userInput <= size){
                             //use the appropriate author.
                             Author selectedAuthor= authorList.get(userInput -1);
                             System.out.format("Selected %s \n", selectedAuthor.getFullName());
                             return selectedAuthor;

                         } else if (userInput == 0){
                             Author author = createAuthor();
                             authorRepoDB.addAuthor(author);
                             return author;
                         }
                     }
                     while(validationService.trueIfNotHappy(userInput, size));
                 } else {
                     System.out.println("No known owner to select form , add a new author");
                     Author author = createAuthor();
                     authorRepoDB.addAuthor(author);
                     return author;
                 }
                 return null;
        }

        private Author createAuthor(){
            String firstName= validationService.getValidNameInput("Please enter the author's first name: ");
            String lastName= validationService.getValidNameInput("Please enter the author's last name: ") ;
            return new Author(firstName, lastName);
        }




}
