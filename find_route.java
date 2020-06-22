import java.io.*;
import java.util.*;

/*
Use State Based Search
Start state, goal test, successor function, step cost

IDS or BFS with tree Search

Uniform cost for most optimal route
File contains: start state, goal state, and cost between (floating point)
*/

public class find_route {

  public static void main(String[] args)
  {
    String[] command_array;
    String inputFile;
    String search = "uninf";
    String start_state;
    String goal_state;
    String heuristicFile;
    float cost;

    // Example input: find_route inf input1.txt Munich Kassel h_kassel.txt

    // EVERYTHING BELOW IS HARDCODED
    /*
    Scanner command_line = new Scanner(System.in);
    String command_current = command_line.next();

    // find_route
    if (command_current != "find_route")
    {
      System.exit(0);
    }
    command_current = command_line.next();
    // inf
    if (command_current == "inf")
    {
      search = command_current;
      command_current = command_line.next();
    }
    // inputFile
    inputFile = command_current;
    command_current = command_line.next();
    // start
    start_state = command_current;
    command_current = command_line.next();
    // goal
    goal_state = command_current;
    command_current = command_line.next();
    // heuristic
    if (search == "inf")
    {
      heuristicFile = command_current;
    }
    */

    // HARDCODED
    inputFile = "input1.txt";
    search = "uninf";
    start_state = "Bremen";
    goal_state = "Kassel";
    heuristicFile = "h_kassel.txt";

    // ReadFile(inputFile);
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
