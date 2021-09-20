import java.util.*;
import java.io.FileWriter;   
import java.io.IOException;

class AStarJava {
    private static Set<Node> initialStates = new HashSet<Node>();
    public static void main(String[] args) {
        //initiale data tracker
        Set<int[]> tracker = new HashSet<int[]>();

        //run AStar 100 times
        for (int i = 0; i < 100; i++)
        {
            //initialize tracker for the cost at each depth
            int[] costAtDepth = new int[] { -1, -1 };

            //ensure that search starts with a unique initial state
            while (costAtDepth[0] == -1)
            {
                //run AStar
                costAtDepth = search();
            }

            boolean depthExists = false;
            //check if already found solution at current depth
            for(int[] track : tracker){
                if(track[0] == costAtDepth[0])
                {
                    //add number of runs of depth and add to total cost s
                    track[1]++;
                    track[2] += costAtDepth[1];
                    depthExists = true;
                    break;
                }
            }

            //otherwise, add a new depth
            if(!depthExists)
            {
                tracker.add(new int[] { costAtDepth[0], 1, costAtDepth[1] });
            }
            System.out.println("\n" + i);
        }

        try
        {
            FileWriter myWriter = new FileWriter("data.txt");
            for(int[] track : tracker)
            {
                myWriter.write("depth: " + track[0] + "average: " + (1.0 * track[2]) / (1.0 * track[1]));
            }
            myWriter.close();
        }
        catch (IOException e) 
             {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }

    private static int[] search()
        {

            //initial state
            Node state = new Node();

            //ensure that initial node is unique
            for(Node n : initialStates)
            {
                if(Arrays.equals(n.getBoard().getBoard(), state.getBoard().getBoard()))
                {
                    return new int[] { -1, -1 };
                }
            }

            //add initial state
            initialStates.add(state);

            //initialize frontier
            PriorityQueue<Node> frontier = new PriorityQueue<Node>();

            //add initial state's children to frontier
            for(Board board : state.getBoard().makeAllMoves())
            {
                if (board != null)
                {
                    frontier.add(new Node(1, board));
                }
            }

            //initiale memory
            HashSet<Node> visited = new HashSet<Node>();
            visited.add(state);

            //run until frontier is empty
            while (frontier.size() != 0)
            {
                //dequeue next node
                Node next = frontier.remove();

                //check that the node has not already been visited
                for(Node n : visited)
                {
                    if(Arrays.equals(n.getBoard().getBoard(), next.getBoard().getBoard())  && n.getCost() <= next.getCost())
                    {
                        continue;
                    }
                }

                //add next to memory
                visited.add(next);
                System.out.print("\r{0}" + visited.size());

                //check if we reached goal state
                if (next.getMisplacedTiles() == 0)
                {
                    return new int[] { next.getCost(), visited.size() };
                }

                //add children of next to frontier
                for (Board board : next.getBoard().makeAllMoves())
                {
                    if (board != null)
                    {
                        frontier.add(new Node(next.getCost() + 1, board));
                    }
                }
            }
            //return fail state
            return new int[] { -1, -1 };
        }
}