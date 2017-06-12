package chess;

import chess.Pieces.Piece;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 *
 * @author tito
 */
public class Square extends Pane {

    String color = "#FFF";
    Pane parent;
    ImageView icon = new ImageView();
    Circle c = new Circle();

    private Piece piece = null;

    public Square(String color, Pane parent) {

        this.color = color;

        this.parent = parent;

        setPrefWidth(50);
        setPrefHeight(50);
        setStyle("-fx-background-color:" + this.color);
        this.prefHeightProperty().bind(this.parent.heightProperty().divide(8));
        this.prefWidthProperty().bind(this.parent.widthProperty().divide(8));

        this.getChildren().add(icon);

    }

    // setting image using directory
    public void setSquareImage(String img_dir) {
        Image image = new Image(img_dir);
        this.icon.setImage(image);
        icon.fitWidthProperty().bind(this.parent.widthProperty().divide(8).subtract(new SimpleDoubleProperty(5)));
        icon.fitHeightProperty().bind(this.parent.heightProperty().divide(8).subtract(new SimpleDoubleProperty(5)));

    }

    // setting image passing image
    public void setSquareImage(Image img) {
        this.icon.setImage(img);
        icon.fitWidthProperty().bind(this.parent.widthProperty().divide(8).subtract(new SimpleDoubleProperty(5)));
        icon.fitHeightProperty().bind(this.parent.heightProperty().divide(8).subtract(new SimpleDoubleProperty(5)));

    }

    public Image getSquareImage() {
        return this.icon.getImage();
    }

    public void removeImage() {
        icon.setImage(null);
    }

    public void setSelectedSquareStyle() {
        Background bk = this.getBackground();
        this.setBackground(bk);
        this.setStyle("-fx-border-color:#4fde1a;-fx-border-width:5px;");
        // some optional styles
        //-fx-border-radius:20px;
    }

    public void setDefaultSqaureStyle() {
        this.setStyle("");

    }

    public void setDotOnSquare() {
        c = new Circle();
        c.centerXProperty().bind(this.widthProperty().divide(2));
        c.centerYProperty().bind(this.heightProperty().divide(2));
        c.setFill(Color.GREENYELLOW);
        c.setRadius(15);
        this.getChildren().add(c);

    }

    public void clearDotSquare() {
        this.getChildren().remove(c);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}
