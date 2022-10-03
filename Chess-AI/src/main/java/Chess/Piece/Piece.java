package Chess.Piece;


import Chess.Board.Board;
import Chess.Board.BoardUtils;
import Chess.Board.Move;
import Chess.color;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class Piece {
    public  Point my_point;
    public Board my_board;
    public final color piece_color;

    public int  number_moves = 0;
    public Piece(Point my_point, color piece_color, Board my_board) {
        this.my_point = my_point;
        this.piece_color = piece_color;
        this.my_board = my_board;
    }
    public boolean isBishop() {
        return false;
    }

    public boolean isKing() {
        return false;
    }

    public boolean isQueen() {
        return false;
    }

    public boolean isRock() {
        return false;
    }

    public boolean isPawn() {
        return false;
    }

    public boolean isKnight() {
        return false;
    }

    public List<Move> legal_Moves(final Board board) {
        return null;
    }

    public List<Move> legal_Movesq(final Board board, int st, int en) {
        int[] dx = { 0, 0, 1, -1, -1, 1, -1, 1 };
        int[] dy = { 1, -1, 0, 0, -1, 1, 1, -1 };

        List<Move> moves = new ArrayList<>();
        for (int i = st; i < en; i++) {
            int pp_x = my_point.x;
            int pp_y = my_point.y;
            for (int j = 0; j < 8; j++) {
                pp_x += dx[i];
                pp_y += dy[i];
                if (!BoardUtils.isValid(pp_x,pp_y)) {
                    break;
                }
                if (!Objects.nonNull(board.Grid[pp_x][pp_y].My_piece)) {
                    moves.add(new Move.MajorMove(board,this,pp_x,pp_y));
                } else {
                    if (board.Grid[pp_x][pp_y].My_piece.piece_color == this.piece_color) {
                        break;
                    } else {
                        moves.add(new Move.AttackMove(board,this,pp_x,pp_y, board.get_piece(pp_x,pp_y)));
                        break;
                    }
                }
            }
        }
        return moves;
    }
    public color get_color(){
        return piece_color ;
    }



}
