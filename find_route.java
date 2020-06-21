import java.io.*;
import java.util.*;

public class find_route {

  public static void main(String[] args)
  {
    System.out.println("Hello, World!");

    String inputFile = "input1.txt";
    ReadFile(inputFile);
  }

  public static void ReadFile(String inputFile)
  {
    try
    {
      //FileReader fr = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      String inputLine = br.readLine().toString();

      while(!(inputLine.equals("END OF INPUT")))
      {
         //System.out.println(inputLine);
         inputLine = br.readLine().toString();
        // line = br.readLine();
      }
      br.close();
    }
    catch(Exception e)
    {
      System.out.println("File error, please check file.");
      e.printStackTrace();
    }


  }
}
