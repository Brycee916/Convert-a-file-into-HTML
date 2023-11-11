import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class HTMLGenerator{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Scanner fileIn; //input file connection
        PrintWriter fileOut; //HTML file connection
        String filenameIn; //original file's name
        String filenameOut; //new HTML file's name
        int dotIndex; //position of any . in the filename
        String line = null; //line from input file

        /*
        //how to create a file
        try{
            File file = new File("fileName.txt");
            if(!file.exists()){//if file doesnt exist
                file.createNewFile();
            }
            //if file exists
            PrintWriter pw = new PrintWriter(file); //writes to the file
            pw.println("this is my file contents");
            pw.close();
            System.out.println("Done");
        } 
        catch(IOException e){
            e.printStackTrace();
        }
        */
        
        
        //ask user for file name or path
        System.out.println("Enter file name or file path: ");
        filenameIn = scanner.nextLine();

        //check if file exists
        try{
            fileIn = new Scanner(new FileReader(filenameIn));
            //if file exists then rename it to a .html file ex. file -> file.html
            dotIndex = filenameIn.lastIndexOf(".");//gets the last . index of the filename
            if(dotIndex == -1){ //if no dots are found in filename
                filenameOut = filenameIn + ".html";
            }
            else{ //if there was a dot in the filename ex. file.txt -> file.html
                filenameOut = filenameIn.substring(0, dotIndex) + ".html";
            }
            //sets the file output to .html file name, we'll be able to write to the .html file now
            fileOut = new PrintWriter(filenameOut);

            //determine if file is empty
            try{
                line = "            " + fileIn.nextLine();//read the first line
            }
            catch(NoSuchElementException e){
                System.out.println("Error:  " + e.getMessage());
            }
            if(line == null){
                System.out.println("This file is empty");
            }
            else{
                //read each line and insert <tags>
                fileOut.println("<html>");
                fileOut.println("   <head>");
                fileOut.println("   </head>");
                fileOut.println("       <body>");
                fileOut.println(line);

                //continuously read file contents until empty
                while(fileIn.hasNextLine()){
                    fileOut.println("           <br>");//move to next line on webpage
                    line = fileIn.nextLine(); //gets the line from the input file
                    
                    if(line.isEmpty()){//if the line has no contents then print a empty line in html doc
                        fileOut.println("<br>");
                    }
                    else{//otherwise print the line
                        fileOut.println("           " + line);
                    }
                }
                //once file has been read through
                fileOut.println("       </body>");
                fileOut.println("</html>");
                System.out.println("Your html file has now been processed");
            }
            fileIn.close();
            fileOut.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        
    }
}