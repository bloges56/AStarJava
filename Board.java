import java.util.Random;
import java.util.*;
import java.lang.System;

public class Board 
{
    private int[] board;

    public Board()
    {
        this.randomBoard();
    }

    public Board(int[] setBoard)
    {
        board = new int[9];
        System.arraycopy(setBoard, 0, board, 0, 9);
    }

    public int[] getBoard()
    {
        return board;
    }

    public Board[] makeAllMoves()
    {
        int blankLoc = findBlank();
        Board[] moves = new Board[4];

        Board leftMoveBoard = new Board(board);
        if(leftMoveBoard.makeMove(blankLoc, -1))
        {
            moves[0] = leftMoveBoard;
        }
        

        Board rightMoveBoard = new Board(board);
        if(rightMoveBoard.makeMove(blankLoc, 1))
        {
            moves[1] = rightMoveBoard;
        }
        

        Board upMoveBoard = new Board(board);
        if(upMoveBoard.makeMove(blankLoc, -3))
        {
            moves[2] = upMoveBoard;
        }

        Board downMoveBoard = new Board(board);
        if(downMoveBoard.makeMove(blankLoc, 3))
        {
            moves[3] = downMoveBoard;
        }
        
        return moves;

    }

    private boolean makeMove(int blankLoc, int move)
    {
        int left = -1;
        int right = 1;
        int up = -3;
        int down = 3;
        if ((move == left && blankLoc % 3 != 0) || 
            (move == right && blankLoc % 3 != 2) || 
            (move == up && blankLoc / 3 != 0) || 
            (move == down && blankLoc / 3 != 2))
        {
            board[blankLoc] = board[blankLoc + move];
            board[blankLoc + move] = 0;
            return true;
        }
        return false;
    }

    private int findBlank()
        {
            for (int i = 0; i < board.length; i++)
            {
                if (board[i] == 0)
                {
                    return i;
                }
            }
            return 0;
        }

    private void randomBoard()
    {
        Random rnd = new Random();
        do{
            this.board = new int[9];
            List<Integer> nums = new ArrayList<Integer>();
            for(int i = 0; i <9; i++)
            {
                nums.add(i);
            }
            for (int i = 0; i < 9; i++)
            {
                int rand = rnd.nextInt(nums.size());
                this.board[i] = nums.remove(rand);
            } 
        }while(this.getInvCount() % 2 != 0);
    }

    private int getInvCount()
    {
        int inv_count = 0;
            for(int i = 0; i < 9; i++)
            {
               for(int j = i+1; j < 9; j++)
                {
                    if(this.board[i] > 0 && this.board[j] > 0 && this.board[i] > this.board[j])
                    {
                        inv_count++;
                    }
                }
            }
            return inv_count;
    }
}
