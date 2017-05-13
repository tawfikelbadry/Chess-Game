package chess.Game;

import chess.Pieces.BISHOP;
import chess.Pieces.KING;
import chess.Pieces.KNIGHT;
import chess.Pieces.PAWN;
import chess.Pieces.Piece;
import chess.Pieces.QWEEN;
import chess.Pieces.ROOK;
import java.util.ArrayList;
import java.util.List;

public class Player {

    private boolean black;

    private int PAWNS = 8;
    private int BISHOPS = 2;
    private int ROOKS = 2;
    private int KNIGHTS = 2;
    private int QUEEN = 1;
    private List<Piece> pieces = new ArrayList();

    public Player(boolean black) {
        this.black = black;
        this.PAWNS = 8;
        this.BISHOPS = 2;
        this.ROOKS = 2;
        this.KNIGHTS = 2;
        this.QUEEN = 1;
        pieces.clear();
        pieces.clear();

        initializePieces();

    }

    public void initializePieces() {
        if (this.black) {
            for (int i = 0; i < 8; i++) { // draw pawns
                pieces.add(new PAWN(i, 1, this));
            }
            pieces.add(new ROOK(0, 0, this));
            pieces.add(new ROOK(7, 0, this));
            pieces.add(new BISHOP(2, 0, this));
            pieces.add(new BISHOP(5, 0, this));
            pieces.add(new KNIGHT(1, 0, this));
            pieces.add(new KNIGHT(6, 0, this));
            pieces.add(new QWEEN(3, 0, this));
            pieces.add(new KING(4, 0, this));
        } else {

            for (int i = 0; i < 8; i++) { // draw pawns
                pieces.add(new PAWN(i, 6, this));
            }
            pieces.add(new ROOK(0, 7, this));
            pieces.add(new ROOK(7, 7, this));
            pieces.add(new BISHOP(2, 7, this));
            pieces.add(new BISHOP(5, 7, this));
            pieces.add(new KNIGHT(1, 7, this));
            pieces.add(new KNIGHT(6, 7, this));
            pieces.add(new QWEEN(3, 7, this));
            pieces.add(new KING(4, 7, this));

        }

    }

    public int getPAWNS() {
        return PAWNS;
    }

    public void setPAWNS(int aPAWNS) {
        PAWNS = aPAWNS;
    }

    public int getBISHOPS() {
        return BISHOPS;
    }

    public void setBISHOPS(int aBISHOPS) {
        BISHOPS = aBISHOPS;
    }

    public int getROOKS() {
        return ROOKS;
    }

    public void setROOKS(int aROOKS) {
        ROOKS = aROOKS;
    }

    public int getKNIGHTS() {
        return KNIGHTS;
    }

    public void setKNIGHTS(int aKNIGHTS) {
        KNIGHTS = aKNIGHTS;
    }

    public int getQUEEN() {
        return QUEEN;
    }

    public void setQUEEN(int aQUEEN) {
        QUEEN = aQUEEN;
    }

    public boolean isBlack() {
        return black;
    }

    public void setBlack(boolean black) {
        this.black = black;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void setPieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean removePiece(Piece piece) {
        boolean removed = this.pieces.remove(piece);

        return removed;
    }

    public void updatePieces(Piece piece, int x, int y) {

        for (int i = 0; i < pieces.size(); i++) {
            if (pieces.get(i) == piece) {
                Piece temppiece = pieces.get(i);
                temppiece.setX(x);
                temppiece.setY(y);

                pieces.set(i, temppiece);

            }
        }

    }

    public int getPieceIndex(int row, int col) {
        for (int i = 0; i < this.pieces.size(); i++) {
            if (pieces.get(i).getX() == row && pieces.get(i).getY() == col) {
                return i;
            }
        }
        return 0;
    }

    public void showMyPieces() {
        int bishops = 0, rooks = 0, knight = 0, pawns = 0, queen = 0, king = 0;
        for (int i = 0; i < pieces.size(); i++) {
            System.out.println(pieces.get(i).getType() + "  " + pieces.get(i).x + "  " + pieces.get(i).y);

            if (pieces.get(i).getType() == TYPE.BISHOP) {
                bishops++;
            } else if (pieces.get(i).getType() == TYPE.KNIGHT) {
                knight++;
            } else if (pieces.get(i).getType() == TYPE.ROOK) {
                rooks++;
            } else if (pieces.get(i).getType() == TYPE.PAWN) {
                pawns++;
            } else if (pieces.get(i).getType() == TYPE.QWEEN) {
                queen++;
            } else if (pieces.get(i).getType() == TYPE.KING) {
                king++;
            }

        }
        System.out.println(this.isBlack() ? "Black" : "White");
        System.out.println("pawns : " + pawns);
        System.out.println("knights : " + knight);
        System.out.println("rook : " + rooks);
        System.out.println("bishop : " + bishops);
        System.out.println("queen : " + queen);
        System.out.println("king : " + king);

    }

}
