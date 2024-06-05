package sandbox.simulation; 

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.*;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class App extends Application {
    
    private Canvas canvas;
    private GraphicsContext gc;
    private boolean isSandSelected = true; // Start with sand selected
    private List<Sand> sandParticles = new ArrayList<>();
    
    @Override
    public void start(Stage primaryStage) {
        // 1. Create UI Elements
        ToggleButton sandButton = new ToggleButton("Sand");
        ToggleButton waterButton = new ToggleButton("Water");
        ToggleGroup toggleGroup = new ToggleGroup();
        sandButton.setToggleGroup(toggleGroup);
        waterButton.setToggleGroup(toggleGroup);
        sandButton.setSelected(true); // Default to Sand
        
        canvas = new Canvas(600, 400); // Adjust size as needed
        gc = canvas.getGraphicsContext2D();
        // Set background color of the canvas
        gc.setFill(Color.BEIGE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

/*
        Rectangle canvasRectangle = new Rectangle(0, 0, canvas.getWidth(), canvas.getHeight());
        canvasRectangle.setFill(Color.TRANSPARENT); // Set background color
        canvasRectangle.setStroke(Color.BLACK); // Set border color
        canvasRectangle.setStrokeWidth(2.0); // Set border width

        StackPane canvasStackPane = new StackPane();
        canvasStackPane.getChildren().addAll(canvas, canvasRectangle);
*/



        // 2. Handle Button Actions
        sandButton.setOnAction(event -> isSandSelected = true);
        waterButton.setOnAction(event -> isSandSelected = false);

        // 3. Layout
        BorderPane root = new BorderPane();
        HBox hbox = new HBox(10, sandButton, waterButton);
        hbox.setAlignment(Pos.CENTER);
        root.setTop(hbox); // Place buttons at the top
        
        Pane canvasPane = new Pane(canvas);
        BorderStroke canvasBorderStroke = new BorderStroke(
            Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT);
        Border canvasBorder = new Border(canvasBorderStroke);
        canvasPane.setBorder(canvasBorder);

        root.setCenter(canvas); // Place canvas in the center

        // 4. Create Scene and Stage
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Sandbox Simulation");
        primaryStage.setScene(scene);
        primaryStage.show();

        // 5. Simulation Logic
        
        // Create sand particles


        canvas.setOnMousePressed(event -> {
            if (isSandSelected) {
                sandParticles.add(new Sand(event.getX(), event.getY()));
            } 
            // (Add water particle creation later)
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()); // Clear canvas

                // Update and display sand particles
                for (Sand sand : sandParticles) {
                    sand.update(0.1, canvas.getHeight());
                    sand.display(gc);
                }

                // (Add water particle update and display later)
            }
        };
        timer.start();        

    }

    public static void main(String[] args) {
        launch(args);
    }
}
