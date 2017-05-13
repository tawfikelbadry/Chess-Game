/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Pieces;

import chess.ChessPane;
import static chess.ChessPane.squares;
import chess.Game.Player;
import chess.Game.TYPE;

/**
 *
 * @author tito
 */
public class QWEEN extends Piece {

    private final int value = 90;

    public QWEEN(int x, int y, Player player) {
        super(x, y, player);
        if (player.isBlack()) {
            img = PIECES_IMAGES.BLACK_QUEEN;
        } else {
            img = PIECES_IMAGES.WHITE_QUEEN;
        }
        super.Value = this.value;

    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (super.isValid(fromX, fromY, toX, toY) == false) {
            return false;
        }
        /**
         * **************************************************************
         */
//        if (toX == fromX) {
//            return true;
//        }
//        if (toY == fromY) {
//            return true;
//        }
//        //diagonal
//        if (Math.abs(toX - fromX) == Math.abs(toY - fromY)) {
//            return true;
//        }
        /**
         * *****************************************************************
         */

        if (toY == fromY) {
            if (fromX < toX) {
                int i;
                for (i = fromX + 1; i < toX; i++) {
                    if (squares[fromY][i].getPiece() != null) {
                        return false;
                    }
                }
                if (i == toX && squares[fromY][i].getPiece() != null) {
                    if (!player.isBlack()) {
                        if (!squares[fromY][i].getPiece().player.isBlack()) {
                            return false;
                        }
                    } else if (squares[fromY][i].getPiece().player.isBlack()) {
                        return false;
                    }
                }
                return true;

            } else if (toX < fromX) {
                int i;
                for (i = fromX - 1; i > toX; i--) {

                    if (squares[fromY][i].getPiece() != null) {

                        return false;
                    }

                }
                if (i == toX && squares[fromY][i].getPiece() != null) {
                    if (!player.isBlack()) {

                        if (!squares[fromY][i].getPiece().player.isBlack()) {
                            return false;
                        }
                    } else if (squares[fromY][i].getPiece().player.isBlack()) {
                        return false;
                    }
                }
                return true;
            }

        }

        if (toX == fromX) {

            if (fromY < toY) {
                int i;
                for (i = fromY + 1; i < toY; i++) {
                    if (squares[i][fromX].getPiece() != null) {

                        return false;
                    }
                }
                if (i == toY && squares[i][fromX].getPiece() != null) {
                    if (!player.isBlack()) {

                        if (!squares[i][fromX].getPiece().player.isBlack()) {
                            return false;
                        }
                    } else if (squares[i][fromX].getPiece().player.isBlack()) {
                        return false;
                    }
                }
                return true;

            } else if (toY < fromY) {
                int i;
                for (i = fromY - 1; i > toY; i--) {
                    if (squares[i][fromX].getPiece() != null) {

                        return false;
                    }
                }
                if (i == toY && squares[i][fromX].getPiece() != null) {
                    if (!player.isBlack()) {

                        if (!squares[i][fromX].getPiece().player.isBlack()) {
                            return false;
                        }
                    } else if (squares[i][fromX].getPiece().player.isBlack()) {
                        return false;
                    }
                }

                return true;
            }
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
                } else if (!ChessPane.squares[c][i].getPiece().player.isBlack()) {
                    return false;
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
            }
        }

        return arr;
    }

    @Override
    public TYPE getType() {
        return TYPE.QWEEN;
    }

    public int getValue() {
        return value;
    }

}
