package com.revature.libraryconsoleapp.service;

import com.revature.libraryconsoleapp.dao.CategoryRepoDB;

import java.util.List;

public class CategoryService {
    private ValidationService validationService = new ValidationService();
    private CategoryRepoDB categoryRepoDB = new CategoryRepoDB();

    public CategoryService() {}

    public String categorySelector() {
        List<String> categoryList= categoryRepoDB.getAllCategories();
        int size = categoryList.size();
        if(size != 0){
            System.out.println("Known Category List, if present, enter the number.");
            for(int i = 0; i < size; i++) {
                System.out.format("[%d] %s\n", i+1 , categoryList.get(i));
            }
            System.out.println("[0] Category not present, I will create a new category: ");
            int userInput;
            do {
                userInput = validationService.getValidInt("Please enter the number associated with the category");
                //System.out.println(userInput);
                if (userInput != 0 && userInput <= size){
                    //use the appropriate author.
                    String selectedCategory= categoryList.get(userInput -1);
                    System.out.format("Selected %s \n", selectedCategory);
                    return selectedCategory;

                } else if (userInput == 0){
                    String category = createCategory();
                    categoryRepoDB.addCategory(category);
                    return category;
                }
            } while(validationService.trueIfNotHappy(userInput, size));
        } else {
            System.out.println("No known category to select from , add a new category");
            String category= createCategory();
            categoryRepoDB.addCategory(category);
            return category;
        }
        return null;
    }



    private String createCategory() {
        String category= validationService.getValidStringInput("Please enter the category: ") ;
        return category;
    }

}
