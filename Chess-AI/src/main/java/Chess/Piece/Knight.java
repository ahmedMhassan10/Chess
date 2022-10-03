package Chess.Piece;


import Chess.Board.Board;
import Chess.Board.BoardUtils;
import Chess.Board.Move;
import Chess.color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    int[] x_cor = {2,2,-2,-2,1,1,-1,-1};
    int[] y_cor = {1,-1,-1,1,2,-2,2,-2};

    public Knight(Point my_point, color piece_color, Board my_board) {
        super(my_point, piece_color, my_board);
    }

    @Override
    public boolean isKnight() {
        return true;
    }

    @Override
    public List<Move> legal_Moves(Board board) {
        List<Move> moves = new ArrayList<>();
        for(int i = 0;i< 8 ;i++)
        {
            int x = my_point.x + x_cor[i];
            int y = my_point.y + y_cor[i];
            if(BoardUtils.isValid(x,y)){
                // get the pice on the poistion here
                Piece position_piece  = board.get_piece(x,y);
                if(position_piece == null){
                    moves.add(new Move.MajorMove(board, this , x , y)) ;
                }else{
                    color enemy = position_piece.get_color();
                    if(enemy != this.get_color()){
                        moves.add(new Move.AttackMove(board, this,x,y,position_piece)) ;
                    }
                }


            }

        }
        return moves;
    }
}
