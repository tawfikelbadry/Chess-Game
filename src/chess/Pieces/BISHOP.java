/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Pieces;

import chess.ChessPane;
import chess.Game.Player;
import chess.Game.TYPE;

/**
 *
 * @author tito
 */
public class BISHOP extends Piece {

    private final int value = 30;

    public BISHOP(int x, int y, Player player) {
        super(x, y, player);
        if (player.isBlack()) {
            img = PIECES_IMAGES.BLACK_BISHOP;
        } else {
            img = PIECES_IMAGES.WHITE_BISHOP;
        }
        super.Value=this.value;
    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (super.isValid(fromX, fromY, toX, toY) == false) {
            return false;
        }
        boolean isFirstPieceInWay = false;
        if (Math.abs(toX - fromX) == Math.abs(toY - fromY)) {

            int i, c;
            for (i = fromX, c = fromY; i != toX && c != toY;) {
                if (fromX > toX) {
                    i--;
                } else {
                    i++;
                }
                if (fromY > toY) {
                    c--;
                } else {
                    c++;
                }

                if (isFirstPieceInWay) {
                    return false;
                }
                if (!isFirstPieceInWay) {
                    if (ChessPane.squares[c][i].getPiece() != null) {
                        isFirstPieceInWay = true;
                    }

                }

            }

            if (ChessPane.squares[toY][toX].getPiece() != null) {
                if (player.isBlack()) {
                    if (ChessPane.squares[c][i].getPiece().player.isBlack()) {
                        return false;
                    }
                } else {
                    if (!ChessPane.squares[c][i].getPiece().player.isBlack()) {
                        return false;
                    }
                }

            }

            return true;
        }

        return false;
    }

    @Override
    public boolean isValidPath(int finalX, int FinalY) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int[][] drawPath(int startX, int StartY) {
        int arr[][] = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (isValid(startX, StartY, i, j)) {
                    arr[i][j] = 1;
                }
//                if (Math.abs(i - startX) == Math.abs(j - StartY)) {
//                    arr[i][j] = 1;
//                }
            }
        }

        return arr;
    }

    @Override
    public TYPE getType() {
        return TYPE.BISHOP;
    }

    public int getValue() {
        return value;
    }

}
