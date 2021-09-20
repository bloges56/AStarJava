 import java.util.*;
 
 public class Node implements Comparable<Node>{
     // declare global variables
     private Board board;
     private int cost;


     //constructor to generate a random board
     public Node()
     {
         board = new Board();
         cost = 0;

     }

     //contsturctor with parameters
     public Node(int newCost, Board newBoard)
     {
         board = new Board(newBoard.getBoard());
         cost = newCost;
     }


     //return board
     public Board getBoard()
     {
         return board;
     }

     //return cost
     public int getCost()
     {
         return cost;
     }


     //return number of misplaced tiles
     public int getMisplacedTiles()
     {
         int sum = 0;
         for (int i = 0; i < board.getBoard().length; i++)
         {
             if (board.getBoard()[i] != i)
             {
                 sum++;
             }
         }
         return sum;
     }

     @Override public int compareTo(Node n)
     {
         if(this.getCost() + this.getMisplacedTiles() > n.getCost() + n.getMisplacedTiles())
         {
             return 1;
         }
         else if(this.getCost() + this.getMisplacedTiles() < n.getCost() + n.getMisplacedTiles())
         {
             return -1;
         }
         else 
         {
            return 0;
         }
     }

     @Override public boolean equals(Object obj)
     {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Node other = (Node) obj;
         if(Arrays.equals(other.getBoard().getBoard(), this.getBoard().getBoard())  && other.getCost() == this.getCost())
         {
             return true;
         }
         return false;
     }
}
