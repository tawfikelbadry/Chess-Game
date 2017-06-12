/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.control;

import static chess.ChessPane.squares;
import chess.Game.Location;
import chess.Game.PieceMove;
import chess.Square;
import chess.Game.Player;
import chess.Game.TYPE;
import chess.Pieces.Piece;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tito
 */

public class Game {

    public static Player white_player = new Player(false);
    public static Player black_player = new Player(true);

    public static void initializeGame() {

        // to inilize player
        white_player = new Player(false);
        black_player = new Player(true);

        // initialize white player pieces
        for (int i = 0; i < white_player.getPieces().size(); i++) {
            Piece piec = white_player.getPieces().get(i);
            squares[piec.x][piec.y].setSquareImage(piec.getImg());
            squares[piec.x][piec.y].setPiece(piec);
            System.out.println("game " + piec.x + " " + piec.y);

        }

        for (int i = 0; i < black_player.getPieces().size(); i++) {
            Piece piec = black_player.getPieces().get(i);
            squares[piec.x][piec.y].setSquareImage(piec.getImg());
            squares[piec.x][piec.y].setPiece(piec);
            System.out.println("game " + piec.x + " " + piec.y);

        }
    }

    public static void clearGame() {
        for (int i = 0; i < squares.length; i++) {
            for (int l = 0; l < squares[i].length; l++) {
                squares[l][i].removeImage();
                squares[l][i].setPiece(null);
            }
        }

    }

    public static Location getClickedSquareLocation(Square sq) {
        for (int i = 0; i < squares.length; i++) {
            for (int l = 0; l < squares[i].length; l++) {
                if (sq == squares[i][l]) {
                    System.out.println(l + "  " + i);
                    return new Location(l, i);

                }
            }
        }
        return null;
    }

    public static void showBoard() {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print((squares[j][i].getPiece() == null) ? "N" : "X");
                System.out.print("   ");
            }
            System.out.println("");
        }
        System.out.println("");

    }

    public static ArrayList<PieceMove> allAvailbleMovesForBlackPieces() {
        ArrayList<PieceMove> availableMoves = new ArrayList();

        ArrayList<Piece> black_pieces = (ArrayList<Piece>) black_player.getPieces();
        // loop fro each black player existed piece and get it's available locations
        for (int i = 0; i < black_pieces.size(); i++) {
            Piece currentP = black_pieces.get(i);
            int availablePositions[][] = currentP.drawPath(currentP.y, currentP.x);
            System.out.println("Type " + currentP.x + " " + currentP.y + "  : " + currentP.getType());
            for (int j = 0; j < availablePositions.length; j++) {
                for (int k = 0; k < availablePositions[j].length; k++) {
                    if (availablePositions[j][k] == 1) {
                        Location from = new Location(currentP.x, currentP.y);
                        Location to = new Location(k, j);
                        int cur_value = 0;
                        if (squares[k][j].getPiece() != null) {
                            cur_value = squares[k][j].getPiece().getValue();
                        }
                        availableMoves.add(new PieceMove(currentP, from, to, cur_value));
                        System.out.print("X");
                    } else {
                        System.out.print("N");
                    }
                    System.out.print("  ");
                }
                System.out.println("");

            }
            System.out.println("");
        }
        System.out.println("available moves : " + availableMoves.size());

        return availableMoves;

    }

    public static ArrayList<PieceMove> allAvailbleMovesForPlayer(Player player) {
        ArrayList<PieceMove> availableMoves = new ArrayList();

        ArrayList<Piece> player_pieces = (ArrayList<Piece>) player.getPieces();
        // loop fro each black player existed piece and get it's available locations
        for (int i = 0; i < player_pieces.size(); i++) {
            Piece currentP = player_pieces.get(i);
            int availablePositions[][] = currentP.drawPath(currentP.y, currentP.x);
            System.out.println("Type " + currentP.x + " " + currentP.y + "  : " + currentP.getType());
            for (int j = 0; j < availablePositions.length; j++) {
                for (int k = 0; k < availablePositions[j].length; k++) {
                    if (availablePositions[j][k] == 1) {
                        Location from = new Location(currentP.x, currentP.y);
                        Location to = new Location(k, j);
                        int cur_value = 0;
                        if (squares[k][j].getPiece() != null) {
                            cur_value = squares[k][j].getPiece().getValue();
                        }
                        PieceMove cur_move = new PieceMove(currentP, from, to, cur_value);
                        availableMoves.add(cur_move);

                        System.out.print("X");
                    } else {
                        System.out.print("N");
                    }
                    System.out.print("  ");
                }
                System.out.println("");

            }
            System.out.println("");
        }
        System.out.println("available moves : " + availableMoves.size());

        return availableMoves;

    }

    public static PieceMove bestMove() {
        ArrayList<PieceMove> availablemoves = allAvailbleMovesForPlayer(black_player);

        // code for get max value move
        int maxVal = Integer.MIN_VALUE;
        for (int i = 0; i < availablemoves.size(); i++) {
            PieceMove currentMove = availablemoves.get(i);
            if (currentMove.getValue() > maxVal) {
                maxVal = currentMove.getValue();
            }
        }
        System.out.println("max value " + maxVal);

        // array list to carry all valuble moves
        ArrayList<PieceMove> valubleMoves = new ArrayList();
        for (int i = 0; i < availablemoves.size(); i++) {
            PieceMove currentMove = availablemoves.get(i);

            if (currentMove.getValue() == maxVal) {
                valubleMoves.add(currentMove);

            }
        }

        int size = valubleMoves.size();
        return valubleMoves.get((int) (Math.random() * size));

    }

    public static boolean isSaFeMove(PieceMove move) {
        ArrayList<PieceMove> availablemoves = allAvailbleMovesForPlayer(white_player);
        for (int i = 0; i < availablemoves.size(); i++) {
            if (move.getToLoc().x == availablemoves.get(i).getToLoc().x && move.getToLoc().y == availablemoves.get(i).getToLoc().y) {

                return false;

            }
        }
        return true;
    }

    public static boolean hasWhitePlayerWon() {

        for (int i = 0; i < black_player.getPieces().size(); i++) {
            if (black_player.getPieces().get(i).getType() == TYPE.KING) {
                return false;
            }
        }
        return true;
    }

    public static boolean hasBlackPlayerWon() {

        for (int i = 0; i < white_player.getPieces().size(); i++) {
            if (white_player.getPieces().get(i).getType() == TYPE.KING) {
                return false;
            }
        }
        return true;
    }

    public static PieceMove theMove;

    public static int minimax(int depth, Player player) {

        if (hasBlackPlayerWon()) {
            return -900;
        }
        if (hasWhitePlayerWon()) {
            return 900;
        }
        List<PieceMove> availableMoves = allAvailbleMovesForPlayer(player);
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < availableMoves.size(); i++) {
            PieceMove move = availableMoves.get(i);
            if (!player.isBlack()) {

                int currentScore = minimax(depth + 1, black_player);
                max = Math.max(currentScore, max);
                if (depth == 0) {
                    System.out.println("computer score for position " + move + " = " + currentScore);
                }
                if (currentScore >= 0) {
                    if (depth == 0) {
                        theMove = move;
                    }
                }

                if (depth == 3) {
                    return max;
                }
                if (currentScore == 900) {

//                    squares[move.getFromLoc().x][move.getFromLoc().y].setPiece(move.getPiece());
                    revertmovePiece(move, player);

                    break;

                }
                if (i == availableMoves.size() - 1 && max < 0) {
                    if (depth == 0) {
                        theMove = move;
                    }
                }
            } else {

                movethePiece(move);
                int currentScore = minimax(depth + 1, white_player);
                min = Math.min(currentScore, min);
                if (min == -900) {
//                    squares[move.getFromLoc().y][move.getFromLoc().y].setPiece(move.getPiece());
                    revertmovePiece(move, player);

                    break;
                }

            }
//            squares[move.getFromLoc().x][move.getFromLoc().y].setPiece(move.getPiece());
            revertmovePiece(move, player);

        }

        return player == white_player ? max : min;
    }

    /// this code used to move piece and commit all changes occur
    public static void movethePiece(PieceMove move) {
        boolean isremoved = Game.white_player.removePiece(squares[move.getToLoc().x][move.getToLoc().y].getPiece());
        System.out.println("removed " + isremoved);
        squares[move.getToLoc().x][move.getToLoc().y].setPiece(move.getPiece());
        squares[move.getToLoc().x][move.getToLoc().y].setSquareImage(move.getPiece().getImg());
        squares[move.getFromLoc().x][move.getFromLoc().y].removeImage();

        squares[move.getFromLoc().x][move.getFromLoc().y].setPiece(null);
        Game.black_player.updatePieces(move.getPiece(), move.getToLoc().x, move.getToLoc().y);

    }

    public static void movethePieceForPlayer(PieceMove move, Player player) {
        boolean isremoved;
        if (player.isBlack()) {
            isremoved = white_player.removePiece(squares[move.getToLoc().x][move.getToLoc().y].getPiece());
        } else {
            isremoved = black_player.removePiece(squares[move.getToLoc().x][move.getToLoc().y].getPiece());

        }
        System.out.println("removed " + isremoved);
        squares[move.getFromLoc().x][move.getFromLoc().y].setPiece(move.getPiece());
        squares[move.getFromLoc().x][move.getFromLoc().y].setSquareImage(move.getPiece().getImg());
        squares[move.getToLoc().x][move.getToLoc().y].removeImage();

        squares[move.getToLoc().x][move.getToLoc().y].setPiece(null);
        player.updatePieces(move.getPiece(), move.getFromLoc().x, move.getFromLoc().y);

    }

    public static void revertmovePiece(PieceMove move, Player player) {
        boolean isremoved;
        if (player.isBlack()) {
            isremoved = white_player.removePiece(squares[move.getToLoc().x][move.getToLoc().y].getPiece());
        } else {
            isremoved = black_player.removePiece(squares[move.getToLoc().x][move.getToLoc().y].getPiece());

        }
        System.out.println("removed " + isremoved);
        squares[move.getFromLoc().x][move.getFromLoc().y].setPiece(move.getPiece());
        squares[move.getFromLoc().x][move.getFromLoc().y].setSquareImage(move.getPiece().getImg());
        squares[move.getToLoc().x][move.getToLoc().y].removeImage();

        squares[move.getToLoc().x][move.getToLoc().y].setPiece(null);
        player.updatePieces(move.getPiece(), move.getFromLoc().x, move.getFromLoc().y);

    }

}
