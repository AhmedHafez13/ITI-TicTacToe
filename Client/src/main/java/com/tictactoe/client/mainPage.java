package com.tictactoe.client;

import com.gluonhq.charm.glisten.control.Avatar;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public abstract class mainPage extends ScrollPane {

    protected final Pane pane;
    protected final Rectangle rectangle;
    protected final Rectangle rectangle0;
    protected final TextField textField;
    protected final Button button;
    protected final ImageView imageView;
    protected final ScrollPane scrollPane;
    protected final AnchorPane anchorPane;
    protected final Pane pane0;
    protected final Avatar avatar;
    protected final TextField textField0;
    protected final Button button0;
    protected final Rectangle rectangle1;
    protected final Button button1;
    protected final TextField textField1;
    protected final Rectangle rectangle2;
    protected final ImageView imageView0;
    protected final Button button2;
    protected final ImageView imageView1;
    protected final Button button3;
    protected final ImageView imageView2;
    protected final ImageView imageView3;
    protected final InnerShadow innerShadow;
    protected final ImageView imageView4;

    public mainPage() {

        pane = new Pane();
        rectangle = new Rectangle();
        rectangle0 = new Rectangle();
        textField = new TextField();
        button = new Button();
        imageView = new ImageView();
        scrollPane = new ScrollPane();
        anchorPane = new AnchorPane();
        pane0 = new Pane();
        avatar = new Avatar();
        textField0 = new TextField();
        button0 = new Button();
        rectangle1 = new Rectangle();
        button1 = new Button();
        textField1 = new TextField();
        rectangle2 = new Rectangle();
        imageView0 = new ImageView();
        button2 = new Button();
        imageView1 = new ImageView();
        button3 = new Button();
        imageView2 = new ImageView();
        imageView3 = new ImageView();
        innerShadow = new InnerShadow();
        imageView4 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(700.0);
        setStyle("-fx-background-color: #a18c7e;");

        pane.setLayoutX(445.0);
        pane.setLayoutY(121.0);
        pane.setPrefHeight(364.0);
        pane.setPrefWidth(211.0);

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.valueOf("#f5ece600"));
        rectangle.setHeight(366.0);
        rectangle.setLayoutX(-8.0);
        rectangle.setLayoutY(-2.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle.setWidth(224.0);

        rectangle0.setArcHeight(5.0);
        rectangle0.setArcWidth(5.0);
        rectangle0.setFill(javafx.scene.paint.Color.valueOf("#21402b"));
        rectangle0.setHeight(61.0);
        rectangle0.setLayoutX(4.0);
        rectangle0.setLayoutY(5.0);
        rectangle0.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle0.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle0.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle0.setStrokeWidth(2.0);
        rectangle0.setWidth(204.0);

        textField.setAlignment(javafx.geometry.Pos.CENTER);
        textField.setEditable(false);
        textField.setLayoutX(14.0);
        textField.setLayoutY(19.0);
        textField.setPrefHeight(34.0);
        textField.setPrefWidth(132.0);
        textField.setStyle("-fx-background-color: none; -fx-text-fill: white; -fx-region-border: 0; -fx-font-size: 18;");
        textField.setText("Online Players");
        textField.setFont(new Font("Bodoni MT Bold", 18.0));
        textField.setCursor(Cursor.NONE);

        button.setLayoutX(144.0);
        button.setLayoutY(24.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(25.0);
        button.setPrefWidth(35.0);
        button.setStyle("-fx-background-color: none;");

        imageView.setFitHeight(26.0);
        imageView.setFitWidth(35.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../../../images/refresh.png").toExternalForm()));
        imageView.setCursor(Cursor.HAND);
        button.setGraphic(imageView);
        button.setCursor(Cursor.HAND);

        scrollPane.setLayoutX(1.0);
        scrollPane.setLayoutY(72.0);
        scrollPane.setPrefHeight(288.0);
        scrollPane.setPrefWidth(208.0);
        scrollPane.setStyle("-fx-background: #21402b; -fx-border-color: black;");

        anchorPane.setMinHeight(0.0);
        anchorPane.setMinWidth(0.0);
        anchorPane.setStyle("-fx-background-color: #21402b;");

        pane0.setLayoutX(7.0);
        pane0.setLayoutY(1.0);
        pane0.setPrefHeight(61.0);
        pane0.setPrefWidth(183.0);

        avatar.setLayoutX(6.0);
        avatar.setLayoutY(15.0);
        avatar.setImage(new Image(getClass().getResource("../../../images/avatar1.png").toExternalForm()));

        textField0.setLayoutX(52.0);
        textField0.setLayoutY(20.0);
        textField0.setPrefHeight(29.0);
        textField0.setPrefWidth(71.0);
        textField0.setStyle("-fx-background-color: none; -fx-text-fill: white; -fx-region-border: 0; -fx-font-size: 15;");
        textField0.setText("Player 1");

        button0.setCacheShape(false);
        button0.setFocusTraversable(false);
        button0.setGraphicTextGap(0.0);
        button0.setLayoutX(123.0);
        button0.setLayoutY(17.0);
        button0.setMinWidth(53.0);
        button0.setMnemonicParsing(false);
        button0.setNodeOrientation(javafx.geometry.NodeOrientation.RIGHT_TO_LEFT);
        button0.setPrefHeight(34.0);
        button0.setPrefWidth(53.0);
        button0.setStyle("-fx-background-color: none; -fx-font-size: 12; -fx-text-fill: #dedc66; -fx-border-width: 2; -fx-border-color: #dedc66; -fx-border-radius: 15;");
        button0.setText("Invite");
        button0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button0.setTextOverrun(javafx.scene.control.OverrunStyle.CENTER_WORD_ELLIPSIS);
        button0.setCursor(Cursor.HAND);
        scrollPane.setContent(anchorPane);

        rectangle1.setArcHeight(5.0);
        rectangle1.setArcWidth(5.0);
        rectangle1.setFill(javafx.scene.paint.Color.valueOf("#21402b"));
        rectangle1.setHeight(86.0);
        rectangle1.setLayoutX(22.0);
        rectangle1.setLayoutY(25.0);
        rectangle1.setSmooth(false);
        rectangle1.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle1.setStrokeDashOffset(10.0);
        rectangle1.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle1.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle1.setStrokeMiterLimit(50.0);
        rectangle1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle1.setStrokeWidth(2.0);
        rectangle1.setWidth(641.0);

        button1.setLayoutX(58.0);
        button1.setLayoutY(62.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(25.0);
        button1.setPrefWidth(36.0);
        button1.setStyle("-fx-background-color: none;");
        button1.setTextFill(javafx.scene.paint.Color.valueOf("#895f99"));
        button1.setCursor(Cursor.HAND);

        textField1.setAlignment(javafx.geometry.Pos.CENTER);
        textField1.setLayoutX(154.0);
        textField1.setLayoutY(38.0);
        textField1.setPrefHeight(61.0);
        textField1.setPrefWidth(351.0);
        textField1.setStyle("-fx-background-color: none; -fx-text-fill: white; -fx-font-size: 30; -fx-region-border: 0;");
        textField1.setText("Player Name");
        textField1.setFont(new Font("Bodoni MT Bold", 30.0));

        rectangle2.setArcHeight(5.0);
        rectangle2.setArcWidth(5.0);
        rectangle2.setFill(javafx.scene.paint.Color.valueOf("#21402b"));
        rectangle2.setHeight(368.0);
        rectangle2.setLayoutX(26.0);
        rectangle2.setLayoutY(118.0);
        rectangle2.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle2.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle2.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle2.setWidth(398.0);

        imageView0.setFitHeight(132.0);
        imageView0.setFitWidth(132.0);
        imageView0.setLayoutX(168.0);
        imageView0.setLayoutY(202.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../../../images/tic-tac-toe%20(1).png").toExternalForm()));

        button2.setLayoutX(139.0);
        button2.setLayoutY(343.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(50.0);
        button2.setPrefWidth(172.0);
        button2.setStyle("-fx-background-color: #8c522b; -fx-font-size: 15;");
        button2.setText(" Play With PC");
        button2.setTextFill(javafx.scene.paint.Color.WHITE);
        button2.setFont(new Font("Aldhabi", 27.0));
        button2.setCursor(Cursor.HAND);

        imageView1.setFitHeight(27.0);
        imageView1.setFitWidth(38.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../../../images/playstation.png").toExternalForm()));
        button2.setGraphic(imageView1);

        button3.setDefaultButton(true);
        button3.setLayoutX(139.0);
        button3.setLayoutY(400.0);
        button3.setMnemonicParsing(false);
        button3.setPrefHeight(50.0);
        button3.setPrefWidth(172.0);
        button3.setStyle("-fx-background-color: #8c522b; -fx-font-size: 15;");
        button3.setText("Leader Board");
        button3.setTextFill(javafx.scene.paint.Color.WHITE);
        button3.setFont(new Font("Aldhabi", 27.0));
        button3.setCursor(Cursor.HAND);

        imageView2.setFitHeight(35.0);
        imageView2.setFitWidth(40.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("../../../images/crown%20(1).png").toExternalForm()));
        button3.setGraphic(imageView2);

        imageView3.setFitHeight(61.0);
        imageView3.setFitWidth(258.0);
        imageView3.setLayoutX(96.0);
        imageView3.setLayoutY(133.0);
        imageView3.setPickOnBounds(true);
        imageView3.setImage(new Image(getClass().getResource("../../../images/9.png").toExternalForm()));

        innerShadow.setChoke(0.76);
        innerShadow.setColor(javafx.scene.paint.Color.#070000);
        innerShadow.setHeight(10.5);
        innerShadow.setRadius(2.125);
        innerShadow.setWidth(0.0);
        imageView3.setEffect(innerShadow);

        imageView4.setFitHeight(38.0);
        imageView4.setFitWidth(76.0);
        imageView4.setLayoutX(57.0);
        imageView4.setLayoutY(53.0);
        imageView4.setPickOnBounds(true);
        imageView4.setPreserveRatio(true);
        imageView4.setImage(new Image(getClass().getResource("../../../images/arrow.png").toExternalForm()));
        imageView4.setCursor(Cursor.HAND);

        pane.getChildren().add(rectangle);
        pane.getChildren().add(rectangle0);
        pane.getChildren().add(textField);
        pane.getChildren().add(button);
        pane0.getChildren().add(avatar);
        pane0.getChildren().add(textField0);
        pane0.getChildren().add(button0);
        anchorPane.getChildren().add(pane0);
        pane.getChildren().add(scrollPane);
        getChildren().add(pane);
        getChildren().add(rectangle1);
        getChildren().add(button1);
        getChildren().add(textField1);
        getChildren().add(rectangle2);
        getChildren().add(imageView0);
        getChildren().add(button2);
        getChildren().add(button3);
        getChildren().add(imageView3);
        getChildren().add(imageView4);

    }
}
