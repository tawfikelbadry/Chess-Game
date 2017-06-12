package chess.Pieces;

import chess.ChessPane;
import static chess.ChessPane.squares;
import chess.Game.Player;
import chess.Game.TYPE;

/**
 *
 * @author tito
 */
public class PAWN extends Piece {

    private final int value = 10;
    private boolean firstMove = true;

    public PAWN(int x, int y, Player player) {
        super(x, y, player);
        if (player.isBlack()) {
            img = PIECES_IMAGES.BLACK_BAWN;
        } else {
            img = PIECES_IMAGES.WHITE_BAWN;
        }
        super.Value = this.value;
        

    }

    @Override
    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (super.isValid(fromX, fromY, toX, toY) == false) {
            return false;
        }
        // if black blayer is playing 
        if (player.isBlack()) {
            if (ChessPane.squares[toY][toX].getPiece() == null || !ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {
                if (firstMove) {
                    if ((toX - fromX == 2 && toY == fromY)) {

                        if (squares[toY][toX].getPiece() == null && squares[toY][toX - 1].getPiece() == null) {
                            return true;
                        }
                    }

                    if (toX - fromX == 1 && toY == fromY) {
                        if (squares[toY][toX].getPiece() == null) {
                            return true;
                        }
                    }
                    if ((toX - fromX == 1 && Math.abs(toY - fromY) == 1)) {
                        if (squares[toY][toX].getPiece() != null && !ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {
                            return true;

                        }
                    }

                } else {
                    if (toX - fromX == 1 && toY == fromY) {
                        if (squares[toY][toX].getPiece() == null) {
                            return true;
                        }
                    }
                    if ((toX - fromX == 1 && Math.abs(toY - fromY) == 1)) {
                        if (squares[toY][toX].getPiece() != null && !ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {
                            return true;

                        }
                    }

                }
            }
            // if white player is playing
        } else if (ChessPane.squares[toY][toX].getPiece() == null || ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {

            if (firstMove) {
                if ((fromX - toX == 2 && toY == fromY)) {
                    if (squares[toY][toX].getPiece() == null && squares[toY][toX + 1].getPiece() == null) {
                        return true;
                    }
                }
                if ((fromX - toX == 1 && toY == fromY)) {
                    if (squares[toY][toX].getPiece() == null) {
                        return true;
                    }
                }

                if ((fromX - toX == 1 && Math.abs(toY - fromY) == 1)) {
                    if (squares[toY][toX].getPiece() != null && ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {

                        return true;
                    }
                }

            } else {
                if ((fromX - toX == 1 && toY == fromY)) {
                    if (squares[toY][toX].getPiece() == null) {
                        return true;
                    }
                }

                if ((fromX - toX == 1 && Math.abs(toY - fromY) == 1)) {
                    if (squares[toY][toX].getPiece() != null && ChessPane.squares[toY][toX].getPiece().getPlayer().isBlack()) {

                        return true;
                    }
                }
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
        int availablespots[][] = new int[8][8];
//        if (player.isBlack()) {
//            if (startX != 7) {
//
//                if (StartY != 0) {
//                    availablespots[startX + 1][StartY - 1] = 1;
//                }
//                if (StartY != 7) {
//                    availablespots[startX + 1][StartY + 1] = 1;
//
//                }
//                availablespots[startX + 1][StartY] = 1;
//            }
//            if (isFirstMove()) {
//                availablespots[startX + 2][StartY] = 1;
//
//            }
//        } else {
//            if (startX != 0) {
//
//                if (StartY != 0) {
//                    availablespots[startX - 1][StartY - 1] = 1;
//                }
//                if (StartY != 7) {
//                    availablespots[startX - 1][StartY + 1] = 1;
//
//                }
//                availablespots[startX - 1][StartY] = 1;
//            }
//
//            if (isFirstMove()) {
//                availablespots[startX - 2][StartY] = 1;
//
//            }
//
//        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isValid(startX, StartY, i, j)) {
                    availablespots[i][j] = 1;
                }
            }
        }

        return availablespots;
    }

    @Override
    public TYPE getType() {
        return TYPE.PAWN;
    }

    public int getValue() {
        return value;
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

}
