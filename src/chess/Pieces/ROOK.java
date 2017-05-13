package chess.Pieces;

import static chess.ChessPane.squares;
import chess.Game.Player;
import chess.Game.TYPE;

/**
 *
 * @author tito
 */
public class ROOK extends Piece {

    private final int value = 50;

    public ROOK(int x, int y, Player player) {
        super(x, y, player);
        if (player.isBlack()) {
            img = PIECES_IMAGES.BLACK_ROOK;
        } else {
            img = PIECES_IMAGES.WHITE_ROOK;
        }

        super.Value = this.value;

    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (super.isValid(fromX, fromY, toX, toY) == false) {
            return false;
        }
//        if (toX == fromX) {
//
//            return true;
//        }
//        if (toY == fromY) {
//            return true;
//        }

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
        return TYPE.ROOK;
    }

    public int getValue() {
        return value;
    }

}
