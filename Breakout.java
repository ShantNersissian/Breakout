import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Breakout extends JPanel implements KeyListener {
    private int width = 640;
    private int height = 480;
    private int paddleW = 80;
    private int paddleH = 10;
    private int ballR = 10;
    private int brickW = 60;
    private int brickH = 20;
    private int bricksN = 30;
    private int paddleV = 5;
    private int ballV = 2;

    private int paddleX;
    private int ballX;
    private int ballY;
    private int ballXV;
    private int ballYV;
    private boolean[] bricks;

    public Breakout() {
        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.addKeyListener(this);
        frame.add(this);
        frame.setVisible(true);

        paddleX = width / 2 - paddleW / 2;
        ballX = width / 2 - ballR;
        ballY = height / 2 - ballR;
        ballXV = ballV;
        ballYV = ballV;

        bricks = new boolean[bricksN];
        for (int i = 0; i < bricksN; i++) {
            bricks[i] = true;
        }
    }
}