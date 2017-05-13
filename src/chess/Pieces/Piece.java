/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess.Pieces;

import chess.Game.Player;
import chess.Game.TYPE;
import javafx.scene.image.Image;

/**
 *
 * @author tito
 */
public abstract class Piece {

    public int x, y;

    public boolean available;

    public Player player;
    protected Image img;

    protected int Value;

    public Piece(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
        this.available = true;

    }

    public abstract boolean isValidPath(int finalX, int FinalY);

    public abstract int[][] drawPath(int startX, int StartY);

    public abstract TYPE getType();

    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (toX == fromX && toY == fromY) {
            return false; //cannot move nothing
        }
        if (toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY < 0 || fromY > 7) {
            return false;
        }
        return true;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Piece{" + "x=" + x + ", y=" + y + ", available=" + available + '}';
    }

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }
    
    

}
