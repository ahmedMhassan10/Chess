package Chess.Piece;

import Chess.Board.Board;
import Chess.Board.BoardUtils;
import Chess.Board.Move;
import Chess.color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{
    int x_cor[] = {2,1,1,1};
    int y_cor[] = {0,0,1,-1};
    private boolean is_enpassant = false;
    public Pawn(Point my_point, color piece_color , Board board) {
        super(my_point, piece_color, board);
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<Move> legal_Moves(Board board) {
        List<Move> moves = new ArrayList<>();
        if (((my_point.x == 1 && piece_color.isBlack()) || (my_point.x == 6 && piece_color.isWhite()))
                && BoardUtils.isValid(my_point.x+ piece_color.getDirection() * x_cor[0], my_point.y)
                && board.Grid[my_point.x + piece_color.getDirection() * x_cor[0]][my_point.y].My_piece == null
                && board.Grid[my_point.x + piece_color.getDirection() * x_cor[1]][my_point.y].My_piece == null)
        {
            int x = my_point.x + piece_color.getDirection() * x_cor[0];
            moves.add(new Move.MajorMove(board, this, x, my_point.y));
        }
        if (BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[1], my_point.y )
            && board.Grid[my_point.x + piece_color.getDirection() * x_cor[1]][my_point.y].My_piece == null
                && BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[0], my_point.y))
        {
            int x = my_point.x + piece_color.getDirection() * x_cor[1];
            moves.add(new Move.MajorMove(board, this, x, my_point.y));
        }
        if (BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[2], my_point.y+y_cor[2])
            && board.Grid[my_point.x + piece_color.getDirection() * x_cor[2]][my_point.y + y_cor[2]].My_piece != null &&
                BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[0], my_point.y))
        {
            int x =my_point.x + (piece_color.getDirection() * x_cor[2]);
            int y = my_point.y + y_cor[2];
            Piece piece_position = board.get_piece(x, y);

            if (this.piece_color != piece_position.piece_color)
                moves.add(new Move.AttackMove(board, this, x,y, piece_position));
        }
        if (BoardUtils.isValid(my_point.x + (piece_color.getDirection() * x_cor[3]), my_point.y+ y_cor[3])
           && board.Grid[my_point.x + (piece_color.getDirection() * x_cor[3])][my_point.y + y_cor[3]].My_piece != null
                && BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[0], my_point.y))
        {
            int x = my_point.x + (piece_color.getDirection() * x_cor[3]);
            int y =my_point.y + y_cor[3];
            Piece piece_position = board.get_piece(x, y);
            if (this.piece_color != piece_position.piece_color)
                moves.add(new Move.AttackMove(board, this, x, y, piece_position));
        }

        if (BoardUtils.isValid(my_point.x + (piece_color.getDirection() * x_cor[2]), my_point.y+y_cor[2])
                && BoardUtils.isValid(my_point.x , my_point.y+y_cor[2])
                && board.Grid[my_point.x + (piece_color.getDirection() * x_cor[2])][my_point.y + y_cor[2]].My_piece == null
                && board.Grid[my_point.x ][my_point.y + y_cor[2]].My_piece != null)
        {
            int x =my_point.x ;
            int y = my_point.y + y_cor[2];
            Piece piece_position = board.get_piece(x, y);
            if (this.piece_color != piece_position.piece_color && piece_position.number_moves == 1
                    && BoardUtils.isValid(x + piece_position.piece_color.getOppositeDirection()*3 , y))
                moves.add(new Move.AttackMove(board, this, x+piece_color.getDirection()*x_cor[2],y, piece_position));
        }
        if (BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[3], my_point.y+ y_cor[3])
                && BoardUtils.isValid(my_point.x , my_point.y+ y_cor[3])
                && board.Grid[my_point.x + piece_color.getDirection() * x_cor[3]][my_point.y + y_cor[3]].My_piece == null
                && board.Grid[my_point.x][my_point.y + y_cor[3]].My_piece != null)
        {
            int x = my_point.x ;
            int y =my_point.y + y_cor[3];
            Piece piece_position = board.get_piece(x, y);
            if (this.piece_color != piece_position.piece_color&& piece_position.number_moves == 1
                 && BoardUtils.isValid(x + piece_position.piece_color.getOppositeDirection()*3 , y) )
                moves.add(new Move.AttackMove(board, this, x+ piece_color.getDirection() * x_cor[3], y, piece_position));
        }
        if (BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[1], my_point.y )
                && board.Grid[my_point.x + piece_color.getDirection() * x_cor[1]][my_point.y].My_piece == null
                && ! BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[0], my_point.y))
        {
            int x = my_point.x + piece_color.getDirection() * x_cor[1];
            moves.add(new Move.PromotionMove(board, this, x, my_point.y));
        }
        if (BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[2], my_point.y+y_cor[2])
                && board.Grid[my_point.x + piece_color.getDirection() * x_cor[2]][my_point.y + y_cor[2]].My_piece != null
                && !BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[0], my_point.y))
        {
            int x =my_point.x + (piece_color.getDirection() * x_cor[2]);
            int y = my_point.y + y_cor[2];
            Piece piece_position = board.get_piece(x, y);

            if (this.piece_color != piece_position.piece_color)
                moves.add(new Move.AttackPromotionMove(board, this, x,y, piece_position));
        }
        if (BoardUtils.isValid(my_point.x + (piece_color.getDirection() * x_cor[3]), my_point.y+ y_cor[3])
                && board.Grid[my_point.x + (piece_color.getDirection() * x_cor[3])][my_point.y + y_cor[3]].My_piece != null
                && !BoardUtils.isValid(my_point.x + piece_color.getDirection() * x_cor[0], my_point.y))

        {
            int x = my_point.x + (piece_color.getDirection() * x_cor[3]);
            int y =my_point.y + y_cor[3];
            Piece piece_position = board.get_piece(x, y);
            if (this.piece_color != piece_position.piece_color)
                moves.add(new Move.AttackPromotionMove(board, this, x, y, piece_position));
        }
        return moves;

    }
}
