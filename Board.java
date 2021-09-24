/*
Board
This class implements a representation of an instance of a 8-puzzle board using an array.

Authors: Jake Mathews and Brady Logan
*/
import java.util.Random;
import java.util.*;
import java.lang.System;

public class Board 
{
    //state of board
    private int[] board;

    //constructor
    public Board()
    {
        this.randomBoard();
    }

    //cunstructor with parameters
    public Board(int[] setBoard)
    {
        board = new int[9];
        System.arraycopy(setBoard, 0, board, 0, 9);
    }

    //returns the board
    public int[] getBoard()
    {
        return board;
    }

    //returns all the possible boards that the current board can generat
    public Board[] makeAllMoves()
    {
        //find where the blank space is
        int blankLoc = findBlank();

        //declare an array of boards
        Board[] moves = new Board[4];

        //generate the left move board
        Board leftMoveBoard = new Board(board);
        if(leftMoveBoard.makeMove(blankLoc, -1))
        {
            moves[0] = leftMoveBoard;
        }
        
        //generate the right move board
        Board rightMoveBoard = new Board(board);
        if(rightMoveBoard.makeMove(blankLoc, 1))
        {
            moves[1] = rightMoveBoard;
        }
        
        //generate the up move board
        Board upMoveBoard = new Board(board);
        if(upMoveBoard.makeMove(blankLoc, -3))
        {
            moves[2] = upMoveBoard;
        }

        //generate the down move board
        Board downMoveBoard = new Board(board);
        if(downMoveBoard.makeMove(blankLoc, 3))
        {
            moves[3] = downMoveBoard;
        }
        
        return moves;

    }

    //change the board according to the given move and location of the blank square
    private boolean makeMove(int blankLoc, int move)
    {
        //only make the move if it is valid
        //else return false
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


    //finds the location of the blank square
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


    //generates a random board
    private void randomBoard()
    {
        //keep generating random boards until it is a valid initial state
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

    //gets the inversion count of a board
    //this is used in randomBoard() to esnure that a board is valid
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
