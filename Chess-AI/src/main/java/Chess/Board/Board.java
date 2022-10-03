package Chess.Board;

import Chess.Piece.Piece;
import Chess.color;

import java.util.Scanner;
import java.util.Stack;

import static Chess.Board.BoardUtils.Generate_string;
import static Chess.Board.BoardUtils.Get_board;

public class Board {
    public Tile[][] Grid;
    public Tile[][] Grid2;// 2d array
    public color myturn;
    public boolean Castle_to_right_white;
    public boolean Castle_to_left_white;
    public boolean Castle_to_right_black;
    public boolean Castle_to_left_black;

    public Board() { // constructor give 2d array each cell = Tile his point fixed;

        Castle_to_right_white = false;
        Castle_to_left_white = false;
        Castle_to_left_black = false;
        Castle_to_right_black = false;
        Grid = new Tile[8][8];
        Grid2 = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Grid[i][j] = new Tile(i, j);
                Grid2[i][j] = new Tile(i, j);
            }
        }
    }

    public Piece get_piece(int x_cordinate, int y_cordinate) {
        return Grid[x_cordinate][y_cordinate].My_piece;
    }

    public static void main(String[] args) {
        Scanner cin=new Scanner(System.in);
        Board finallll = new Board();
        finallll = BoardUtils.Get_board("1q6/2P5/2B5/7K/8/1R6/1P5k/1R6 w - - 0 1");
        BoardUtils.flow(finallll);
    }


}
