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
import java.util.Scanner;

/**
 *
 * @author Ubaita
 */
public class Main {
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        
        Student student = new Student();        
        
        String data = "";

        System.out.println("Enter the No of records you want to enter: ");
        int records = sc.nextInt();
        
        for(int i=0; i<records; i++){
            System.out.println("Enter student "+(i+1)+" ID: ");
            student.setId(sc.nextInt());
            System.out.println("Enter student "+(i+1)+" name: ");
            student.setName(sc.next());
            System.out.println("Enter student "+(i+1)+" Score: ");
            student.setScore(sc.nextInt());
            
            data += data.concat(student.getId()+","+student.getName()+","+student.getScore()+",");
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
class Student{
    private int id;
    private String name;
    private int score;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}