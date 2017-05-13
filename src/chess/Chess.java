/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import javafx.scene.layout.BorderPane;

public class Chess extends BorderPane {

    HeaderPane header;
    static ChessPane pane;

    public Chess() {
       // setOpacity(0.5);
        header = new HeaderPane();
        pane = new ChessPane();

        this.setLeft(header);
        this.setCenter(pane);
    }

}
