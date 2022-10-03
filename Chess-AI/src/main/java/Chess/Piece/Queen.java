package Chess.Piece;

import Chess.Board.Board;
import Chess.Board.Move;
import Chess.color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {
    public Queen(Point my_point, color piece_color, Board my_board) {
        super(my_point, piece_color, my_board);
    }
    @Override
    public boolean isQueen(){
        return true;
    }
    public List<Move> legal_Moves(Board board) {
        List<Move> moves = new ArrayList<>();
        return moves = legal_Movesq(board, 0, 8);
    }
}
