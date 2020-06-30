import java.io.*;
import java.util.*;

class node implements Comparable<node>
{
	public String state;
  public Integer depth;
	public node parent;
	public double nodePathCost;
	public node nodeChild;

  // Collections code
	public node(String state, node parent, Integer depth, double nodePathCost)
	{
		this.state = state;
		this.depth = depth;
		this.nodePathCost = nodePathCost;
    this.parent = parent;
	}

	public String get_state()
	{
		return state;
	}
	public node get_parent()
	{
		return parent;
	}
	public Integer get_depth()
	{
		return depth;
	}
	public double get_PathCost()
	{
		return nodePathCost;
	}
  public node get_children()
  {
    return nodeChild;
  }

	@Override // needed to edit compare
	public int compareTo(node node)
	{
		double node_cost = node.nodePathCost;
		if (this.nodePathCost > node_cost)
    {
			return 1;
    }
		else if (this.nodePathCost == node_cost)
    {
			return 0;
    }
		else
    {
			return -1;
    }
	}
}

public class find_route
{
  public static ArrayList<String> inputFileArray = new ArrayList<String>();
	public static Map<String, Double> heuristicMap;
  // Open set
	public static ArrayList<node> priorityqueue = new ArrayList<node>();
  // Closed set
	public static ArrayList<String> visitedNode = new ArrayList<String>();
	public static node goal_node;
  public static int expanded = 0;
  public static int generated = 0;






	public static void main(String[] args)
	{
    String inputFile = args[0];
    String search_type = "uninf";
    String start_state = args[1];
		String goal_state = args[2];
		String heuristicFile;

		try{
			heuristicFile = args[3];
		}
		finally
		{

    // Example input: java find_route input1.txt Bremen Kassel h_kassel.txt

		/* U P D A T E D    T O   N O T    I N C L U D E
    Scanner command_line = new Scanner(System.in);
    String command_current = command_line.next();

    // find_route
    if (!command_current.equals("find_route"))
    {
      System.exit(0);
    }
    command_current = command_line.next();
    // inf
    if (command_current.equals("inf"))
    {
      search_type = command_current;
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
    if (search_type.equals("inf"))
    {
      heuristicFile = command_current;
    }
		*/

    /*
    // HARDCODED
    inputFile = "input1.txt";
    //search_type = "inf";
    start_state = "Bremen";
    goal_state = "Kassel";
    heuristicFile = "h_kassel.txt";

    // System.out.println("input commands: " + inputFile + " " + search_type + " " + start_state + " " + goal_state + " " + heuristicFile + "\n");


		if(search_type.equals("inf"))
		{
			heuristicMap = new LinkedHashMap<String, Double>();
			ReadHeuristicFile(heuristicFile);
		}
		*/

		ReadInputFile(inputFile);

    // * * * * * B E G I N    Q U E U E * * * * *

    // Create node representation of set (stirng, parent, g_val, depth)
		node beginning_node = new node(start_state, null, 0, 0);

    // Priority set = sorted list of the inputs.  Aka open set
		priorityqueue.add(beginning_node);
    // Initialize two counters (up above), generated and expanded

    // Priority Queue: [Bremen node] ----> NULL


    // Check if there's any more nodes in the queue or if goal has reached
          // empty if impossible               end if found goal is filled
		while((priorityqueue.isEmpty() == false)  && goal_node == null)
		{

      // Take out parent node but store data
			node temp_parent = priorityqueue.remove(0);

      expanded++;

      // Goal state doesn't go through while loop again
			if ((temp_parent.get_state().equals(goal_state)))
			{
				goal_node = temp_parent;
			}
			else
			{
        // Visted node is the closed set, if not on there it's added
        // Else it's already expanded all the way
				if (visitedNode.contains(temp_parent.get_state()) == false)
				{
          // Add to closed array
					visitedNode.add(temp_parent.get_state());

          int j = 0;
          // Look at "file" and see if the city apart of any other sets
					for (String i : inputFileArray)
					{
            // Child node has been found! (if true)
						if (i.contains(temp_parent.get_state()))
            {
              String[] current_child = inputFileArray.get(j).split("\\s+");
              // current_child[0] = city1
              // current_child[1] = city2
              // current_child[2] = Nodecost

              // * * * * * I N F O R M E D    S E A R C H * * * * *
              if(search_type.equals("inf"))
              {
                InformedSearch(current_child, temp_parent);
							}
              // * * * * * U N I N F O R M E D    S E A R C H * * * * *
							else if(search_type.equals("uninf"))
              {
                UninformedSearch(current_child, temp_parent);
							}
						}
            j++;
          // * * * * * Loop ends here * * * * *
					}
					Collections.sort(priorityqueue);
				}
			}
    }
    print();
	}
  }

  public static void InformedSearch(String[] current_child, node temp_parent)
  {
    double heuristicValue = 0.0;
    generated++;
    node sucessor_node;

    // Ex: inputcity1 = parent
    // Adds city1 to priorityqueue
    if(temp_parent.get_state().equals(current_child[0]))
    {
      if(heuristicMap.keySet().contains(temp_parent.get_state()) == true)
      {
        heuristicValue = heuristicMap.get(temp_parent.get_state());
      }
      sucessor_node = new node(current_child[1], temp_parent, temp_parent.get_depth() + 1, temp_parent.get_PathCost() + Double.valueOf(current_child[2]));

      priorityqueue.add(sucessor_node);
    }
    // Ex: inputcity2 = parent
    // Adds city2 to priorityqueue
    else
    {
      sucessor_node = new node(current_child[0], temp_parent, temp_parent.get_depth() + 1, temp_parent.get_PathCost() + Double.valueOf(current_child[2]));

      priorityqueue.add(sucessor_node);
    }
  }

  public static void UninformedSearch(String[] current_child, node temp_parent)
  {

    node sucessor_node;
    generated++;

    if(temp_parent.get_state().equals(current_child[0]))
    {
      sucessor_node = new node(current_child[1], temp_parent, temp_parent.get_depth() + 1, temp_parent.get_PathCost() + Double.valueOf(current_child[2]));
      priorityqueue.add(sucessor_node);
    }
    else
    {
    sucessor_node = new node(current_child[0], temp_parent, temp_parent.get_depth() + 1, temp_parent.get_PathCost() + Double.valueOf(current_child[2]));

    priorityqueue.add(sucessor_node);
    }
  }

	public static void ReadHeuristicFile(String heuristicFile)
	{
		try
		{
      File myObj = new File(heuristicFile);
			Scanner myReader = new Scanner((myObj));
      String input = myReader.nextLine();

			while(!(input.equals("END OF INPUT")))
			{
				String[] split = input.split("\\s+");

				heuristicMap.put(split[0], Double.valueOf(split[1]));

        input = myReader.nextLine();

			}
		}
		catch(Exception e)
		{
      System.out.println("File error, please check file.");
			e.printStackTrace();
		}
	}

  public static void ReadInputFile(String inputFile)
  {
    try
    {
      //FileReader fr = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(new FileReader(inputFile));
      String inputLine = br.readLine().toString();

      while(!(inputLine.equals("END OF INPUT")))
      {
         //System.out.println(inputLine);
         inputFileArray.add(inputLine);
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


public static void print()
{

    System.out.println("nodes expanded: " + expanded);
    System.out.println("nodes generated: " + generated);

    // Goal is fulfilled and at the end of priorityqueue
		if (goal_node != null)
    {

			goal_node.nodeChild = null;
			System.out.println("distance: " + goal_node.get_PathCost() + " km\nroute: ");

      // Traverses the optimal path (to beginning)
      while(goal_node.get_parent() != null)
      {
				goal_node.parent.nodeChild = goal_node;
				goal_node = goal_node.parent;
			}
      // Traverses the optimal path (to end)
			while(goal_node.nodeChild != null)
      {
				System.out.println(goal_node.get_state() + " to " + goal_node.nodeChild.get_state()+", " + (goal_node.nodeChild.get_PathCost() - goal_node.get_PathCost()) + " km");
				goal_node = goal_node.nodeChild;
			}
		}
    // Goal was never realized and the inputFileArray ran out
		else
    {
      System.out.println("distance: infinity");
      System.out.println("route:");
      System.out.println("none");
		}
	}
}
