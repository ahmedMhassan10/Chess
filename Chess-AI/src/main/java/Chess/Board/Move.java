package Chess.Board;

import Chess.Piece.Piece;

public abstract class Move {
    final Board board;
    Piece piece;
    final int x_distination_cor ;
    final int y_distination_cor ;

    public boolean isMove() {
        return false;
    }

    public boolean isAttackMove() {
        return false;
    }

    public boolean isPromotionMove() {
        return false;
    }

    public boolean isAttackPromotionMove() {
        return false;
    }
    private Move(Board board , Piece piece , int x_distination_cor , int y_distination_cor){
        this.board = board;
        this.piece = piece;
        this.x_distination_cor = x_distination_cor;
        this.y_distination_cor = y_distination_cor;

    }


    public static  class MajorMove extends Move{
        public MajorMove(Board board , Piece piece , int x_distination_cor, int y_distination_car){
            super(board, piece , x_distination_cor, y_distination_car);
        }
        public boolean isMove() {
            return true;
        }
    }

    public static class AttackMove extends Move{
        Piece attack_piece;
        public AttackMove(Board board , Piece piece , int x_distination_cor, int y_distination_car, Piece attack_piece ){
            super(board, piece , x_distination_cor, y_distination_car);
            this.attack_piece = attack_piece;
        }
        public boolean isAttackMove() {
            return true;
        }
    }

    public static class PromotionMove extends Move{
        public PromotionMove(Board board , Piece piece , int x_distination_cor, int y_distination_car ){
            super(board, piece , x_distination_cor, y_distination_car);
        }
        public boolean isPromotionMove() {
            return true;
        }
    }

    public static class AttackPromotionMove extends Move{
        Piece attack_piece;
        public AttackPromotionMove(Board board , Piece piece , int x_distination_cor, int y_distination_car, Piece attack_piece ){
            super(board, piece , x_distination_cor, y_distination_car);
            this.attack_piece = attack_piece;
        }
        public boolean isAttackPromotionMove() {
            return true;
        }
    }



}
