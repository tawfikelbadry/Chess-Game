/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Game;

import chess.Pieces.Piece;

/**
 *
 * @author tito
 */
public class PieceMove {

    private Piece piece;
    private Location fromLoc;
    private Location toLoc;
    private int value;

    public PieceMove() {
    }

    public PieceMove(Piece piece, Location toLoc) {
        this.piece = piece;
        this.toLoc = toLoc;
    }

    public PieceMove(Piece piece, Location fromLoc, Location toLoc) {
        this.piece = piece;
        this.fromLoc = fromLoc;
        this.toLoc = toLoc;
    }
    public PieceMove(Piece piece, Location fromLoc, Location toLoc,int val) {
        this.piece = piece;
        this.fromLoc = fromLoc;
        this.toLoc = toLoc;
        this.value=val;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Location getFromLoc() {
        return fromLoc;
    }

    public void setFromLoc(Location fromLoc) {
        this.fromLoc = fromLoc;
    }

    public Location getToLoc() {
        return toLoc;
    }

    public void setToLoc(Location toLoc) {
        this.toLoc = toLoc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    

}
