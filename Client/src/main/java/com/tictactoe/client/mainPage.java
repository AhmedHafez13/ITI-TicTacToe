package models;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.effect.Blend;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public abstract class mainPage extends Pane {

    protected final Pane pane;
    protected final Rectangle rectangle;
    protected final Rectangle rectangle0;
    protected final Rectangle rectangle1;
    protected final ScrollBar scrollBar;
    protected final TextField textField;
    protected final Rectangle rectangle2;
    protected final Button button;
    protected final ImageView imageView;
    protected final TextField textField0;
    protected final Rectangle rectangle3;
    protected final TextField textField1;
    protected final Blend blend;
    protected final ImageView imageView0;
    protected final Button button0;
    protected final Button button1;
    protected final ImageView imageView1;
    protected final ImageView imageView2;

    public mainPage() {

        pane = new Pane();
        rectangle = new Rectangle();
        rectangle0 = new Rectangle();
        rectangle1 = new Rectangle();
        scrollBar = new ScrollBar();
        textField = new TextField();
        rectangle2 = new Rectangle();
        button = new Button();
        imageView = new ImageView();
        textField0 = new TextField();
        rectangle3 = new Rectangle();
        textField1 = new TextField();
        blend = new Blend();
        imageView0 = new ImageView();
        button0 = new Button();
        button1 = new Button();
        imageView1 = new ImageView();
        imageView2 = new ImageView();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(500.0);
        setPrefWidth(700.0);

        pane.setLayoutX(445.0);
        pane.setLayoutY(121.0);
        pane.setPrefHeight(364.0);
        pane.setPrefWidth(211.0);

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.WHITE);
        rectangle.setHeight(364.0);
        rectangle.setLayoutX(-5.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle.setWidth(218.0);

        rectangle0.setArcHeight(5.0);
        rectangle0.setArcWidth(5.0);
        rectangle0.setFill(javafx.scene.paint.Color.valueOf("#bcf2a7"));
        rectangle0.setHeight(50.0);
        rectangle0.setLayoutX(6.0);
        rectangle0.setLayoutY(14.0);
        rectangle0.setStroke(javafx.scene.paint.Color.valueOf("#d3c1c1"));
        rectangle0.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle0.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle0.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle0.setStrokeWidth(2.0);
        rectangle0.setWidth(200.0);

        rectangle1.setArcHeight(5.0);
        rectangle1.setArcWidth(5.0);
        rectangle1.setFill(javafx.scene.paint.Color.valueOf("#bcf2a7"));
        rectangle1.setHeight(287.0);
        rectangle1.setLayoutX(6.0);
        rectangle1.setLayoutY(72.0);
        rectangle1.setStroke(javafx.scene.paint.Color.valueOf("#d3c1c1"));
        rectangle1.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle1.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle1.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle1.setStrokeWidth(2.0);
        rectangle1.setWidth(200.0);

        scrollBar.setLayoutX(188.0);
        scrollBar.setLayoutY(72.0);
        scrollBar.setOrientation(javafx.geometry.Orientation.VERTICAL);
        scrollBar.setPrefHeight(287.0);
        scrollBar.setPrefWidth(18.0);
        
        textField.setAlignment(javafx.geometry.Pos.CENTER);
        textField.setLayoutX(49.0);
        textField.setLayoutY(27.0);
        textField.setPrefHeight(25.0);
        textField.setPrefWidth(115.0);
        textField.setText("Online Players");

        rectangle2.setArcHeight(5.0);
        rectangle2.setArcWidth(5.0);
        rectangle2.setFill(javafx.scene.paint.Color.valueOf("#dddddd"));
        rectangle2.setHeight(71.0);
        rectangle2.setLayoutX(26.0);
        rectangle2.setLayoutY(36.0);
        rectangle2.setSmooth(false);
        rectangle2.setStroke(javafx.scene.paint.Color.valueOf("#bcbcbc"));
        rectangle2.setStrokeDashOffset(10.0);
        rectangle2.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle2.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle2.setStrokeMiterLimit(50.0);
        rectangle2.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle2.setStrokeWidth(2.0);
        rectangle2.setWidth(631.0);

        button.setLayoutX(56.0);
        button.setLayoutY(56.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(25.0);
        button.setPrefWidth(36.0);

        imageView.setFitHeight(24.0);
        imageView.setFitWidth(113.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(getClass().getResource("../../../../images/back.png").toExternalForm()));
        button.setGraphic(imageView);

        textField0.setAlignment(javafx.geometry.Pos.CENTER);
        textField0.setLayoutX(216.0);
        textField0.setLayoutY(52.0);
        textField0.setPrefHeight(40.0);
        textField0.setPrefWidth(251.0);
       
        textField0.setText("Player Name");
        textField0.setFont(new Font("Bodoni MT", 16.0));

        rectangle3.setArcHeight(5.0);
        rectangle3.setArcWidth(5.0);
        rectangle3.setFill(javafx.scene.paint.Color.WHITE);
        rectangle3.setHeight(364.0);
        rectangle3.setLayoutX(27.0);
        rectangle3.setLayoutY(121.0);
        rectangle3.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle3.setStrokeLineCap(javafx.scene.shape.StrokeLineCap.ROUND);
        rectangle3.setStrokeLineJoin(javafx.scene.shape.StrokeLineJoin.ROUND);
        rectangle3.setStrokeType(javafx.scene.shape.StrokeType.OUTSIDE);
        rectangle3.setWidth(396.0);

        textField1.setAlignment(javafx.geometry.Pos.CENTER);
        textField1.setEditable(false);
        textField1.setLayoutX(111.0);
        textField1.setLayoutY(138.0);
        textField1.setPrefHeight(32.0);
        textField1.setPrefWidth(211.0);
        textField1.setText("Tic-Tac-Toe");
        textField1.setFont(new Font("Book Antiqua", 25.0));

        textField1.setEffect(blend);

        imageView0.setFitHeight(132.0);
        imageView0.setFitWidth(132.0);
        imageView0.setLayoutX(159.0);
        imageView0.setLayoutY(195.0);
        imageView0.setPickOnBounds(true);
        imageView0.setPreserveRatio(true);
        imageView0.setImage(new Image(getClass().getResource("../../../../images/tic-tac-toe%20(1).png").toExternalForm()));

        button0.setDefaultButton(true);
        button0.setLayoutX(139.0);
        button0.setLayoutY(335.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(40.0);
        button0.setPrefWidth(172.0);
        button0.setText("        Play With PC");
        button0.setTextFill(javafx.scene.paint.Color.WHITE);
        button0.setFont(new Font("Aldhabi", 23.0));

        button1.setDefaultButton(true);
        button1.setLayoutX(139.0);
        button1.setLayoutY(390.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(40.0);
        button1.setPrefWidth(172.0);
        button1.setText("     Leader Board");
        button1.setTextFill(javafx.scene.paint.Color.WHITE);

        imageView1.setFitHeight(27.0);
        imageView1.setFitWidth(38.0);
        imageView1.setLayoutX(152.0);
        imageView1.setLayoutY(342.0);
        imageView1.setPickOnBounds(true);
        imageView1.setPreserveRatio(true);
        imageView1.setImage(new Image(getClass().getResource("../../../../images/playstation.png").toExternalForm()));

        imageView2.setFitHeight(40.0);
        imageView2.setFitWidth(40.0);
        imageView2.setLayoutX(146.0);
        imageView2.setLayoutY(390.0);
        imageView2.setPickOnBounds(true);
        imageView2.setPreserveRatio(true);
        imageView2.setImage(new Image(getClass().getResource("../../../../images/crown%20(1).png").toExternalForm()));

        pane.getChildren().add(rectangle);
        pane.getChildren().add(rectangle0);
        pane.getChildren().add(rectangle1);
        pane.getChildren().add(scrollBar);
        pane.getChildren().add(textField);
        getChildren().add(pane);
        getChildren().add(rectangle2);
        getChildren().add(button);
        getChildren().add(textField0);
        getChildren().add(rectangle3);
        getChildren().add(textField1);
        getChildren().add(imageView0);
        getChildren().add(button0);
        getChildren().add(button1);
        getChildren().add(imageView1);
        getChildren().add(imageView2);

    }
}
