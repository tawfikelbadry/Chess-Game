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
public class KING extends Piece {

    private final int value = 900;

    public KING(int x, int y, Player player) {
        super(x, y, player);
        if (player.isBlack()) {
            img = PIECES_IMAGES.BLACK_KING;
        } else {
            img = PIECES_IMAGES.WHITE_KING;
        }
        super.Value = this.value;

    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (super.isValid(fromX, fromY, toX, toY) == false) {
            return false;
        }
        if (this.getPlayer().isBlack()) {
            if (ChessPane.squares[toY][toX].getPiece() == null || !ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {
                if (Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1) {
                    return true;
                }
            }

            // if white player move 
        } else // check if place is empty or for enemy to move
        if (ChessPane.squares[toY][toX].getPiece() == null || ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {
            if (Math.abs(toX - fromX) <= 1 && Math.abs(toY - fromY) <= 1) {
                return true;
            }
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
        System.out.println(player);
        System.out.println("start x : " + startX);
        System.out.println("start Y : " + StartY);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                if (isValid(startX, StartY, i, j)) {
                    arr[i][j] = 1;
                }

            }
        }
        return arr;
    }

    @Override
    public TYPE getType() {
        return TYPE.KING;
    }

    public int getValue() {
        return value;
    }

}
