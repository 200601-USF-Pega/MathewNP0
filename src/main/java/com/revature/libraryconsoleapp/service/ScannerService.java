package com.revature.libraryconsoleapp.service;

import java.util.Scanner;

public class ScannerService {

    private static Scanner scannerSingleton = null;
    private Scanner scanner;



    public Scanner getScannerObject() {
        return scanner;
    }

    public static Scanner getInstance() {
        if(scannerSingleton == null) {
            scannerSingleton = new Scanner(System.in);
        }
        return scannerSingleton;
    }


    @Override
    public void finalize() {
        try {
            scanner.close();
        } catch(Exception e) {

        }
    }

}