package Chess.Board;

import Chess.Piece.*;
import Chess.color;
import Chess.Board.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BoardUtils {
    public static boolean isValid(int x_cordinate, int y_cordinate) {
        if (x_cordinate < 0 || y_cordinate < 0 || x_cordinate > 7 || y_cordinate > 7)
            return false;
        return true;
    }

    public static Board Get_board(String fen) {
        Board temp = new Board();
        int x = 0;
        int y = 0;
        int index = 0;
        while (x < 8) {
            if (fen.charAt(index) == '/' || y > 7) {
                x++;
                if (x == 8) {
                    break;
                }
                y = 0;
                index++;
            }
            Point pp = new Point(x, y);
            if (fen.charAt(index) == 'r') {
                temp.Grid[x][y].My_piece = new Rock(pp, color.Black, temp);
            } else if (fen.charAt(index) == 'n') {
                temp.Grid[x][y].My_piece = new Knight(pp, color.Black, temp);
            } else if (fen.charAt(index) == 'b') {
                temp.Grid[x][y].My_piece = new Bishop(pp, color.Black, temp);
            } else if (fen.charAt(index) == 'q') {
                temp.Grid[x][y].My_piece = new Queen(pp, color.Black, temp);
            } else if (fen.charAt(index) == 'k') {
                temp.Grid[x][y].My_piece = new King(pp, color.Black, temp);
            } else if (fen.charAt(index) == 'p') {
                temp.Grid[x][y].My_piece = new Pawn(pp, color.Black, temp);
            } else if (fen.charAt(index) == 'R') {
                temp.Grid[x][y].My_piece = new Rock(pp, color.White, temp);
            } else if (fen.charAt(index) == 'N') {
                temp.Grid[x][y].My_piece = new Knight(pp, color.White, temp);
            } else if (fen.charAt(index) == 'B') {
                temp.Grid[x][y].My_piece = new Bishop(pp, color.White, temp);
            } else if (fen.charAt(index) == 'Q') {
                temp.Grid[x][y].My_piece = new Queen(pp, color.White, temp);
            } else if (fen.charAt(index) == 'K') {
                temp.Grid[x][y].My_piece = new King(pp, color.White, temp);
            } else if (fen.charAt(index) == 'P') {
                temp.Grid[x][y].My_piece = new Pawn(pp, color.White, temp);
            } else if (Character.isDigit(fen.charAt(index))) {
                int zz = fen.charAt(index) - '0';
                while (zz > 0) {
                    y++;
                    zz--;
                }
                y--;
            }
            index++;
            y++;
        }
        char debug = fen.charAt(index);
        index++;
        char debug2 = fen.charAt(index);
        if (fen.charAt(index) == 'w') {
            temp.myturn = color.White;
        } else if (fen.charAt(index) == 'b') {
            temp.myturn = color.Black;
        }
        index++;
        index++;
        if (fen.charAt(index) == ' ') {
            return temp;
        }
        if (fen.charAt(index) == 'K') {
            temp.Castle_to_right_white = true;
            index++;
        }
        if (fen.charAt(index) == 'Q') {
            temp.Castle_to_left_white = true;
            index++;
        }
        if (fen.charAt(index) == 'k') {
            temp.Castle_to_right_black = true;
            index++;
        }
        if (fen.charAt(index) == 'q') {
            temp.Castle_to_left_black = true;
            index++;
        }
        index++;
        /*
         * // for pass
         * if (index != fen.length())
         * {
         * int char1 = (int)fen.charAt(index) - 97;
         * int char2 = (int)fen.charAt(index + 1) - 48;
         *
         * board.pass_pawn = new tile(xx, yy);
         * }
         */
        return temp;
    }

    public static String Generate_string(Board temp) {
        String fen = "";
        boolean BR = temp.Castle_to_right_black;
        boolean BL = temp.Castle_to_left_black;
        boolean WL = temp.Castle_to_left_white;
        boolean WR = temp.Castle_to_right_white;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (temp.Grid[i][j].My_piece == null) {
                    int zz = 0;
                    while (temp.Grid[i][j].My_piece == null) {
                        zz++;
                        j++;
                        if (j > 7)
                            break;
                    }
                    fen += Integer.toString(zz);
                }
                if (j <= 7) {
                    if (temp.Grid[i][j].My_piece.piece_color == color.White) {
                        if (temp.Grid[i][j].My_piece.isKing()) {
                            fen += "K";
                        } else if (temp.Grid[i][j].My_piece.isBishop()) {
                            fen += "B";
                        } else if (temp.Grid[i][j].My_piece.isRock()) {
                            fen += "R";
                        } else if (temp.Grid[i][j].My_piece.isPawn()) {
                            fen += "P";
                        } else if (temp.Grid[i][j].My_piece.isQueen()) {
                            fen += "Q";
                        } else if (temp.Grid[i][j].My_piece.isKnight()) {
                            fen += "N";
                        }
                    } else {
                        if (temp.Grid[i][j].My_piece.isKing()) {
                            fen += "k";
                        } else if (temp.Grid[i][j].My_piece.isBishop()) {
                            fen += "b";
                        } else if (temp.Grid[i][j].My_piece.isRock()) {
                            fen += "r";
                        } else if (temp.Grid[i][j].My_piece.isPawn()) {
                            fen += "p";
                        } else if (temp.Grid[i][j].My_piece.isQueen()) {
                            fen += "q";
                        } else if (temp.Grid[i][j].My_piece.isKnight()) {
                            fen += "n";
                        }
                    }
                }
            }
            if (i != 7)
                fen += '/';
        }

        fen += " ";
        if (temp.myturn == color.White) {
            fen += 'w';
        } else {
            fen += 'b';
        }
        fen += " ";
        if (WR)
            fen += 'K';
        if (WL)
            fen += 'Q';
        if (BR)
            fen += 'k';
        if (BL)
            fen += 'q';

        // pass
        return fen + "      ";
    }

    public static Stack<String> stack1 = new Stack<String>(), stack2 = new Stack<String>();

    public static void norm(Board board) {
        stack1.push(Generate_string(board));
        stack2.clear();
    }

    public static Board undo() {
        if (stack1.empty()) {
            System.out.println("In valid operation");
            return null;
        } else {
            stack2.push(stack1.peek());
            stack1.pop();
            return Get_board(stack1.peek());
        }

    }

    public static Board redo() {
        if (stack2.empty()) {
            System.out.println("In valid operation");
            return null;
        } else {
            stack1.push(stack2.peek());
            stack2.pop();
            return Get_board(stack1.peek());
        }
    }


    public static boolean checkAttack(Board board, Point point, color turn) {// return true if point is safe
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.nonNull(board.Grid[i][j].My_piece)) {
                    if (board.Grid[i][j].My_piece.piece_color != turn) {
                        Piece attack_piece = board.Grid[i][j].My_piece;
                        List<Move> move = new ArrayList<Move>();
                        move = attack_piece.legal_Moves(board);
                        for (Move moves : move) {
                            if (moves.x_distination_cor == point.x && moves.y_distination_cor == point.y)
                                return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static Board create_Move(Board board, Move move) {
        board.Grid[move.piece.my_point.x][move.piece.my_point.y].My_piece = null;
        board.Grid[move.x_distination_cor][move.y_distination_cor].My_piece = move.piece;
        return board;
    }
    public static Board create_Move2(Board board, Move move) {
        board.Grid[move.piece.my_point.x][move.piece.my_point.y].My_piece = null;
        move.piece.my_point = new Point(move.x_distination_cor, move.y_distination_cor);
        board.Grid[move.x_distination_cor][move.y_distination_cor].My_piece = move.piece;
        return board;
    }
    public static Point get_king(Board board, color turn) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.nonNull(board.Grid[i][j].My_piece)) {
                    if (board.Grid[i][j].My_piece.isKing() && board.Grid[i][j].My_piece.get_color() == turn) {
                        return new Point(i, j);
                    }
                }
            }
        }
        return null;
    }
    public static List<Move> get_legal_move(Board board, color turn) {
        String str = Generate_string(board);
        List<Move> move = new ArrayList<Move>();
        Point king = get_king(board, turn);
        List<Move> temp = board.Grid[king.x][king.y].My_piece.legal_Moves(board);
        for (Move mo : temp) {
            Board new_board = Get_board(str);
            BoardUtils.create_Move(new_board, mo);
            if (checkAttack(new_board, new Point(mo.x_distination_cor, mo.y_distination_cor), turn)) {
                move.add(mo);
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (Objects.nonNull(board.Grid[i][j].My_piece)) {
                    if (board.Grid[i][j].My_piece.get_color() == turn && !board.Grid[i][j].My_piece.isKing()) {
                        temp = board.Grid[i][j].My_piece.legal_Moves(board);
                        for (Move mo : temp) {
                            Board new_board = Get_board(str);
                            BoardUtils.create_Move(new_board, mo);
                            if (checkAttack(new_board, king, turn)) {
                                move.add(mo);
                            }
                        }
                    }
                }
            }
        }
        return move;
    }

    public static boolean checkMate(Board board, color turn) {
        List<Move> move = get_legal_move(board, turn);
        return move.size() == 0 && !checkAttack(board, get_king(board, turn), turn);
    }

    public static boolean checkDraw(Board board, color turn) {
        List<Move> move = get_legal_move(board, turn);
        return move.size() == 0 && checkAttack(board, get_king(board, turn), turn);
    }

    public static void print(Board temp) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (temp.Grid[i][j].My_piece == null) {
                    System.out.print("*  ");
                    continue;
                }
                if (temp.Grid[i][j].My_piece.piece_color == color.White) {
                    if (temp.Grid[i][j].My_piece.isKing()) {
                        System.out.print("K  ");
                    } else if (temp.Grid[i][j].My_piece.isBishop()) {
                        System.out.print("B  ");
                    } else if (temp.Grid[i][j].My_piece.isRock()) {
                        System.out.print("R  ");
                    } else if (temp.Grid[i][j].My_piece.isPawn()) {
                        System.out.print("P  ");
                    } else if (temp.Grid[i][j].My_piece.isQueen()) {
                        System.out.print("Q  ");
                    } else if (temp.Grid[i][j].My_piece.isKnight()) {
                        System.out.print("N  ");
                    }
                } else {
                    if (temp.Grid[i][j].My_piece.isKing()) {
                        System.out.print("k  ");
                    } else if (temp.Grid[i][j].My_piece.isBishop()) {
                        System.out.print("b  ");
                    } else if (temp.Grid[i][j].My_piece.isRock()) {
                        System.out.print("r  ");
                    } else if (temp.Grid[i][j].My_piece.isPawn()) {
                        System.out.print("z  ");
                    } else if (temp.Grid[i][j].My_piece.isQueen()) {
                        System.out.print("q  ");
                    } else if (temp.Grid[i][j].My_piece.isKnight()) {
                        System.out.print("n  ");
                    }
                }
            }
            System.out.println("");
        }
    }


    public static void flow(Board temp) {
        while (!doo_move(temp)) {
        }
    }

    public static boolean doo_move(Board temp) {
        Scanner cin2 = new Scanner(System.in);
        int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        BoardUtils.print(temp);
        if (temp.myturn == color.White) {
            System.out.println("White Turn");
            System.out.println("Enter first Tile");
        } else {
            System.out.println("Black Turn");
            System.out.println("Enter first tile");
        }
        while (true) {
            try {
                x1 = cin2.nextInt();
                y1 = cin2.nextInt();
                x1--;
                y1--;
                if (temp.Grid[x1][y1].My_piece != null && temp.Grid[x1][y1].My_piece.piece_color == temp.myturn)
                    break;
                System.out.println("Not apiece");
            } catch (Exception e) {
                System.out.println("Biiiiiiig Mistake");
            }
        }
        System.out.println("Enter second Tile");
        while (true) {
            try {
                x2 = cin2.nextInt();
                y2 = cin2.nextInt();
                x2--;
                y2--;
                break;
            } catch (Exception e) {
                System.out.println("Biiiiiiig Mistake");
            }
        }
        Piece chosen = temp.Grid[x1][y1].My_piece;
        List<Move> legal_inthis = get_legal_move(temp, temp.myturn);
        for (Move item : legal_inthis) {
            if (item.x_distination_cor == x2 && item.y_distination_cor == y2 && item.piece == chosen) {
                if( item.isPromotionMove() || item.isAttackPromotionMove() ){
                    System.out.println("1 for queen , 2 for rock , 3 for bishop , 4 for knight");
                    int k = 0;
                    k = cin2.nextInt();
                    if(k == 1 ){
                        item.piece = new Queen(chosen.my_point , chosen.piece_color , chosen.my_board);
                    }
                    else if(k==2){
                        item.piece = new Rock(chosen.my_point , chosen.piece_color , chosen.my_board);
                    }
                    else if(k ==3){
                        item.piece = new Bishop(chosen.my_point , chosen.piece_color , chosen.my_board);
                    }else if(k==4){
                        item.piece = new Knight(chosen.my_point , chosen.piece_color , chosen.my_board);
                    }
                }
                temp = BoardUtils.create_Move2(temp, item);
                alter(temp);
                break;
            }

        }
        if(BoardUtils.checkDraw(temp, temp.myturn)){
            System.out.println("Draw");
            return true;
        }

        if (BoardUtils.checkMate(temp, temp.myturn)  ) {
            System.out.println("winner");
            return true;
        }

        return false;
    }
    public static void alter(Board temp) {
        if (temp.myturn == color.White) {
            temp.myturn = color.Black;
        } else {
            temp.myturn = color.White;
        }
    }
}