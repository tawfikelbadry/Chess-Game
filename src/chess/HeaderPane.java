package chess;

import chess.control.Game;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

public class HeaderPane extends VBox {

    Button exit;
    Button Maximize;

    Button newGame, onePlayer, twoPlayer;

    public HeaderPane() {

        // initializing variables        
        exit = new Button("Exit");
        newGame = new Button("New Game");
        Maximize = new Button("Maximize");
        onePlayer = new Button("One Player");
        twoPlayer = new Button("Two Players");

        //   setting style 
        exit.setStyle("-fx-background-color:#F00;");
        this.setPadding(new Insets(10));
        this.setStyle("-fx-background-color:#689dc7;");

        this.getStylesheets().add(HeaderPane.class.getResource("/chess/view/styles/style.css").toExternalForm());

        // adding to pane 
        this.getChildren().addAll(newGame, onePlayer, twoPlayer, Maximize, exit);

        // binding 
        newGame.prefWidthProperty().bind(new SimpleDoubleProperty(160));
        exit.prefWidthProperty().bind(new SimpleDoubleProperty(160));
        Maximize.prefWidthProperty().bind(new SimpleDoubleProperty(160));
        onePlayer.prefWidthProperty().bind(new SimpleDoubleProperty(160));
        twoPlayer.prefWidthProperty().bind(new SimpleDoubleProperty(160));

        /// actions
        exit.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                System.exit(0);

            }
        });

        newGame.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                Game.showBoard();
                Game.clearGame();
                Game.initializeGame();
                Game.showBoard();

            }
        });

        Maximize.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                if (Maximize.getText().equals("Maximize")) {
                    Main.ChessStage.setFullScreen(true);
                    Maximize.setText("Minimize");
                } else {
                    Main.ChessStage.setFullScreen(false);
                    Maximize.setText("Maximize");
                }

            }
        });

    }

}
