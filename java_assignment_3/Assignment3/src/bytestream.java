import java.io.*;
public class bytestream 
{ 
    public static void main(String[] args) throws IOException 
    { 
    	FileInputStream fr = null; 
    	FileOutputStream fw = null;
        try 
        { 
            fr = new FileInputStream("input.txt"); 
            fw = new FileOutputStream("output-bytestream.txt");
            int temp; 
            while ((temp = fr.read()) != -1) 
                 fw.write((char)temp); 
        } 
        finally 
        {   
            if (fr != null)             
                fr.close();
            if (fw != null)             
                fw.close();
        } 
    } 
} 