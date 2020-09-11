/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beita.assignment4;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ubaita
 */
public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        List<String> studentId = new ArrayList<>();
        List<String> studentName = new ArrayList<>();
        List<String> studentScore = new ArrayList<>();
        String data = "";

        System.out.println("Enter the No of records you want to enter: ");
        int records = sc.nextInt();
        
        for(int i=0; i<records;i++){
            System.out.println("Enter student "+(i+1)+" ID: ");
            studentId.add(sc.next());
            System.out.println("Enter student "+(i+1)+" name: ");
            studentName.add(sc.next());
            System.out.println("Enter student "+(i+1)+" Score: ");
            studentScore.add(sc.next()); 
        }
        
        for(int i=0; i<records; i++){
            data += studentId.get(i)+","+studentName.get(i)+","+studentScore.get(i)+",";            
        }
       
        try {
            File file = new File("student_records.txt");
            Path dataPath = Files.writeString(file.toPath(), data, StandardCharsets.UTF_8);
            String content = Files.readString(dataPath, StandardCharsets.UTF_8);
            System.out.println(content);
        } catch (IOException e) {
            System.out.println("Error!!");
        }  
    }
}
