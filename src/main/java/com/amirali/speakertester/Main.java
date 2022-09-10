package com.amirali.speakertester;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("SpeakerTester");
        stage.setScene(new Scene(createContent(), 600, 400));
        stage.show();
    }

    private Parent createContent() {
        var leftSpeakerImageView = new ImageView(Objects.requireNonNull(getClass().getResource("left-speaker.png")).toExternalForm());
        leftSpeakerImageView.setFitWidth(45);
        leftSpeakerImageView.setFitHeight(45);
        leftSpeakerImageView.setPickOnBounds(true);
        leftSpeakerImageView.setCursor(Cursor.HAND);
        leftSpeakerImageView.setOnMouseClicked(mouseEvent -> {
            playAudio(AudioBalance.LEFT);
        });
        var leftSpeakerContainer = new VBox(3, leftSpeakerImageView, new Label("Left"));
        leftSpeakerContainer.setAlignment(Pos.CENTER);

        var rightSpeakerImageView = new ImageView(Objects.requireNonNull(getClass().getResource("right-speaker.png")).toExternalForm());
        rightSpeakerImageView.setFitWidth(45);
        rightSpeakerImageView.setFitHeight(45);
        rightSpeakerImageView.setPickOnBounds(true);
        rightSpeakerImageView.setCursor(Cursor.HAND);
        rightSpeakerImageView.setOnMouseClicked(mouseEvent -> {
            playAudio(AudioBalance.RIGHT);
        });
        var rightSpeakerContainer = new VBox(3, rightSpeakerImageView, new Label("Right"));
        rightSpeakerContainer.setAlignment(Pos.CENTER);

        var region = new Region();
        HBox.setHgrow(region, Priority.ALWAYS);

        var top = new HBox(leftSpeakerContainer, region, rightSpeakerContainer);

        var speakerImageView = new ImageView(Objects.requireNonNull(getClass().getResource("speaker.png")).toExternalForm());
        speakerImageView.setFitWidth(50);
        speakerImageView.setFitHeight(50);
        speakerImageView.setPickOnBounds(true);
        speakerImageView.setCursor(Cursor.HAND);
        speakerImageView.setOnMouseClicked(mouseEvent -> {
            playAudio(AudioBalance.CENTER);
        });

        var center = new VBox(speakerImageView, new Label("Center"));
        center.setAlignment(Pos.CENTER);

        var root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);

        return root;
    }

    private void playAudio(double balance) {
        var audioClip = new AudioClip(Objects.requireNonNull(getClass().getResource("sound.mp3")).toExternalForm());
        audioClip.setBalance(balance);
        audioClip.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
