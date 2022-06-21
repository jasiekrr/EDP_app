package com.example.edpapp.customControls;

import com.google.common.eventbus.EventBus;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class UpgradeControl extends Pane {
    private ImageView arrow;
    private ImageView arrowDisabled;
    private boolean active = false;

    private int level;
    private Label levelLabel;
    private StackPane stackPane;

    public UpgradeControl(boolean active, int level) throws FileNotFoundException {
        stackPane = new StackPane();
        levelLabel = new Label();

        arrow = new ImageView();
        arrowDisabled = new ImageView();


        stackPane.setPrefHeight(122.0);
        stackPane.setPrefWidth(122.0);

        levelLabel.setFont(new Font("Lato Bold Italic",48.0));
        levelLabel.setText(String.valueOf(level));


        InputStream stream = new FileInputStream("src/main/java/com/example/edpapp/customControls/arrow.png");
        arrow.setImage(new Image(stream));

        InputStream streamDisabled = new FileInputStream("src/main/java/com/example/edpapp/customControls/arrow_disabled.png");
        arrowDisabled.setImage(new Image(streamDisabled));

        arrow.fitWidthProperty().set(122);
        arrow.fitHeightProperty().set(122);

        arrowDisabled.fitWidthProperty().set(97);
        arrowDisabled.fitHeightProperty().set(97);

        stackPane.getChildren().add(arrowDisabled);
        stackPane.getChildren().add(arrow);
        stackPane.getChildren().add(levelLabel);


        this.active = active;
        this.level = level;
    }

    private Pane generateUpgradeControl(){
        Pane upgradeControl = new Pane();
        arrow.setVisible(active);

        upgradeControl.getChildren().add(stackPane);

        return upgradeControl;
    }
    public Pane getControl(){
        return generateUpgradeControl();
    }

    public ImageView getArrow() {
        return arrow;
    }

    public void setArrow(ImageView arrow) {
        this.arrow = arrow;
    }

    public ImageView getArrowDisabled() {
        return arrowDisabled;
    }

    public void setArrowDisabled(ImageView arrowDisabled) {
        this.arrowDisabled = arrowDisabled;
    }
}
