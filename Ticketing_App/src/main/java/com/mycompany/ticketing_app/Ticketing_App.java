/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.ticketing_app;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.input.ReversedLinesFileReader;
/**
 *
 * @author Marawan
 */
public class Ticketing_App {public static void main(String[] args) throws IOException, CsvException{
	Scanner scanner = new Scanner(System.in);
	System.out.println("Problem Ticketing System");
	System.out.println("-------------------------------------------------------------------------------------");
	boolean exit = false;
        //file readers and writers
        CSVReader creader = new CSVReader(new FileReader("src\\main\\java\\Files\\Categories.csv"));
        FileWriter fileWriter = new FileWriter(new File("src\\main\\java\\Files\\Tickets.csv"),true);
        CSVWriter twriter = new CSVWriter(fileWriter);
        CSVReader treader = new CSVReader(new FileReader("src\\main\\java\\Files\\Tickets.csv"));
        
        
        
        //skip headers
        treader.skip(1);
        creader.skip(1);
        
        
        //get categories
        List<String[]> categoriesf = creader.readAll();
        String[] categories = new String[categoriesf.size()];
        for(int i =0;i<categoriesf.size();i++){
            categories[i]= categoriesf.get(i)[1];
        }
        
     
        
        //get new ticket number
        int ticketval = readlastticket(fileWriter);
      
       
	while (!exit) {
            String[] ticket = new String[2];
                int choice = 0;
                System.out.println("Choose Category");
		for(int i =0;i<categories.length;i++){
                    System.out.println((i+1) +"."+categories[i]);
                }
                while(choice<1||choice>categories.length){
		choice = scanner.nextInt();
                if(choice<1||choice>categories.length){
                    System.out.println("Invalid choice\nChoose Again:");
                    continue;
                }
                    ticket[0] = String.valueOf(ticketval);
                    ticket[1] = categories[choice-1];
                    twriter.writeNext(ticket, true);
                    twriter.close();
                    System.out.println("Your ticket was submitted succesfully\nTicket Number is #"+ticketval);
                    exit = true;
                    break;
                }
		

}

	scanner.close();

}

    private static int readlastticket(FileWriter writer) throws IOException {
        //read last line for new ticket vlaue
        ReversedLinesFileReader reader = new ReversedLinesFileReader(new File("src\\main\\java\\Files\\Tickets.csv"));
        String line;
       line = reader.readLine();
       if(!line.isBlank()){
           if(line.charAt(0)!='I'){
           return (Integer.valueOf(line.charAt(1)))-47;}else{
              writer.write("\n");
           return 1;
       }
       }else{
           return 1;
       }

    }

}