/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import chess.Game.Location;
import chess.Game.PieceMove;
import chess.Pieces.PAWN;
import chess.control.Game;
import static chess.control.Game.getClickedSquareLocation;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;
import javax.swing.JOptionPane;

/**
 *
 * @author tito
 */
public class ChessPane extends GridPane implements EventHandler<MouseEvent> {

    public static Square squares[][] = new Square[8][8];

    public ChessPane() {

        this.setWidth(800);
        this.setHeight(700);
        this.setStyle("-fx-border-color: #689dc7;\n"
                + "-fx-border-width: 20;\n"
                + "-fx-border-style: solid;\n"
                + "");

        for (int i = 0; i < squares.length; i++) {
            for (int l = 0; l < squares[i].length; l++) {
                if (((i + l) % 2) == 0) {
                    squares[l][i] = new Square("#FFF", this);
                    this.add(squares[l][i], l, i);
                } else {
                    squares[l][i] = new Square("#62a5e0", this);
                    this.add(squares[l][i], l, i);

                }

                squares[l][i].setOnMouseClicked(this);

            }
        }
        Game.initializeGame();

    }

    //----------------------------------------------------------------------//
    boolean isReady = false;

    ArrayList<Pair<Integer, Integer>> availableSquares = new ArrayList();

    Image chhosed_img = null;
    Square choosed_sr = null;

    private boolean isFirstPlayer = true;

    private boolean isTwoPlayersGame = false;

    private int fromRow, fromCol, toRow, toCol;

    @Override
    public void handle(MouseEvent event) {
        Square temp = (Square) event.getSource();

        // if white player turn
        if (isFirstPlayer) {
            if (isReady == false) {
                Location loction = getClickedSquareLocation(temp);
                fromRow = loction.x;
                fromCol = loction.y;
                if (temp.getSquareImage() != null && !temp.getPiece().player.isBlack()) {
                    choosed_sr = temp;
                    setAvailableSopotsDots(choosed_sr.getPiece().drawPath(fromRow, fromCol));

                    chhosed_img = temp.getSquareImage();
                    choosed_sr.setSelectedSquareStyle();
                    isReady = true;
                }
            } else {

                Location loction = getClickedSquareLocation(temp);
                toRow = loction.x;
                toCol = loction.y;
                if (choosed_sr.getPiece().isValid(fromRow, fromCol, toRow, toCol)) {
                    choosed_sr.removeImage();

                    // this code is applied when PAWN played for it's first move only 
                    // to set it's first variable to false to move one step only after that
                    if (choosed_sr.getPiece() instanceof PAWN) {
                        PAWN pawn = (PAWN) choosed_sr.getPiece();
                        if (pawn.isFirstMove()) {
                            pawn.setFirstMove(false);
                        }
                    }
                    /**
                     * ******** end of specified code for pawn first move
                     * ***********
                     */

                    Game.white_player.updatePieces(choosed_sr.getPiece(), toCol, toRow);
                    // remove deleted piece from 

                    boolean isremoved = Game.black_player.removePiece(temp.getPiece());
                    System.out.println("removed : " + isremoved);

                    temp.setPiece(choosed_sr.getPiece());
                    temp.setSquareImage(chhosed_img);

                    choosed_sr.setPiece(null);

                    if (Game.hasWhitePlayerWon()) {
                        JOptionPane.showMessageDialog(null, "Congratulations , You Has Won");

                    }

                    isFirstPlayer = false;
                } else {
                    System.out.println("can't");
                }
                clearSquaresDots();
                choosed_sr.setDefaultSqaureStyle();

                isReady = false;
                Game.black_player.showMyPieces();

            }

            // if black player turn
        } else if (isFirstPlayer == false) {
            // if the second player is a human
            if (isTwoPlayersGame) {
                if (isReady == false) {

                    Location loction = getClickedSquareLocation(temp);
                    fromRow = loction.x;
                    fromCol = loction.y;
                    if (temp.getSquareImage() != null && temp.getPiece().player.isBlack()) {
                        choosed_sr = temp;
                        setAvailableSopotsDots(choosed_sr.getPiece().drawPath(fromRow, fromCol));

                        chhosed_img = temp.getSquareImage();
                        choosed_sr.setSelectedSquareStyle();
                        isReady = true;
                    }
                } else {
                    //if (temp.getSquareImage() == null) {
                    Location loction = getClickedSquareLocation(temp);
                    toRow = loction.x;
                    toCol = loction.y;
                    if (choosed_sr.getPiece().isValid(fromRow, fromCol, toRow, toCol)) {
                        choosed_sr.removeImage();

                        // this code is applied when PAWN played for it's first move only 
                        // to set it's first variable to false to move one step only after that
                        if (choosed_sr.getPiece() instanceof PAWN) {
                            PAWN pawn = (PAWN) choosed_sr.getPiece();
                            if (pawn.isFirstMove()) {
                                pawn.setFirstMove(false);
                            }
                        }
                        /**
                         * ******** end of specified code for pawn first move
                         * ***********
                         */
                        Game.black_player.updatePieces(choosed_sr.getPiece(), toCol, toRow);

                        boolean isremoved = Game.white_player.removePiece(temp.getPiece());
                        System.out.println("removed " + isremoved);
                        temp.setPiece(choosed_sr.getPiece());
                        temp.setSquareImage(chhosed_img);
                        choosed_sr.setPiece(null);

                        //   }
                        isFirstPlayer = true;
                    } else {
                        System.out.println("can't");
                    }
                    clearSquaresDots();
                    choosed_sr.setDefaultSqaureStyle();

                    isReady = false;
                    Game.white_player.showMyPieces();

                }
                //  if the second player is the computer
            } else {

                PieceMove move = Game.bestMove();
//                
                System.out.println("piece fromX " + move.getFromLoc().x + "  fromy : " + move.getFromLoc().y);
                System.out.println("piece toX " + move.getToLoc().x + "  toy : " + move.getToLoc().y);
                boolean isremoved = Game.white_player.removePiece(squares[move.getToLoc().x][move.getToLoc().y].getPiece());
                System.out.println("removed " + isremoved);
                squares[move.getToLoc().x][move.getToLoc().y].setPiece(move.getPiece());
                squares[move.getToLoc().x][move.getToLoc().y].setSquareImage(move.getPiece().getImg());
                squares[move.getFromLoc().x][move.getFromLoc().y].removeImage();
//
                squares[move.getFromLoc().x][move.getFromLoc().y].setPiece(null);
                Game.black_player.updatePieces(move.getPiece(), move.getToLoc().x, move.getToLoc().y);
                Game.movethePiece(move);
                
              //  Game.allAvailbleMovesForBlackPieces();
//                Game.minimax(0, Game.white_player);
//                PieceMove moveminimax = Game.theMove;
//
//                Game.movethePiece(Game.theMove);
//
//                Game.white_player.showMyPieces();

                if (Game.hasBlackPlayerWon()) {
                    JOptionPane.showMessageDialog(null, "Game Over ,You has Losed");
                };
                isFirstPlayer = true;
            }

        }
    }

    public boolean isAvailable(int row, int col) {
        for (int i = 0; i < availableSquares.size(); i++) {
            if (availableSquares.get(i).getKey() == col && availableSquares.get(i).getValue() == row) {
                return true;
            }
        }
        return false;
    }

    public void clearSquaresDots() {
        for (int i = 0; i < squares.length; i++) {
            for (int l = 0; l < squares[i].length; l++) {
                squares[l][i].clearDotSquare();
            }

        }

    }

    public void setAvailableSopotsDots(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == 1) {
                    squares[j][i].setDotOnSquare();
                }
            }

        }

    }

}
