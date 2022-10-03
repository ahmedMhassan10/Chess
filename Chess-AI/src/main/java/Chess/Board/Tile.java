package Chess.Board ;

import Chess.Piece.Piece;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;


    public class Tile {
        public Point point1; // x and y cordinate
        public Piece My_piece;

        public Tile(int x, int y) {
            point1=new Point(x,y);
            My_piece = null;
        }
    }


