package com.example.demo.s02;

import com.example.demo.assignment.FileService;
import com.example.demo.assignment.FileServicePractice;
import com.example.demo.comman.Util;

import java.io.File;

public class Assigment12 {
    public static void main(String[] args) {
        FileService fileService = new FileServicePractice();

        fileService.write("file.txt","this is a text file")
                .subscribe(Util.subscriber());

        fileService.read("file.txt")
                .subscribe(Util.subscriber());
        fileService.delete("file.txt")
                .subscribe(Util.subscriber());
    }
}
