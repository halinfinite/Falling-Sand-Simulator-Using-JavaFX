package sandbox.simulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Random;

public class Sand {
    private double x, y;
    private double radius = 6; // Radius of the sand particle
    private double yVelocity = 0; // Initial vertical velocity

    // Constructor
    public Sand(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Update position based on gravity
    public void update(double gravity, double groundY) {
        yVelocity += gravity;
        y += yVelocity;
        // Check if particle has hit the ground and bounce slightly
        if (y > groundY - radius) {
            y = groundY - radius;
            yVelocity *= -0.5; // Bounce with energy loss
        }
    }
    
    // Draw the sand particle
    public void display(GraphicsContext gc) {
        gc.setFill(Color.SANDYBROWN);
        gc.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }
}
