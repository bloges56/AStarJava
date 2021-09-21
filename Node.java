import java.util.Arrays;

public class Node{
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

     @Override public boolean equals(Object obj)
     {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        Node other = (Node) obj;
        return Arrays.equals(this.getBoard().getBoard(), other.getBoard().getBoard());
     }
}
