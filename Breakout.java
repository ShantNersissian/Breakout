import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Breakout extends JPanel implements KeyListener {
    private int width = 920;
    private int height = 540;
    private int paddleW = 80;
    private int paddleH = 10;
    private int ballR = 10;
    private int brickW = 60;
    private int brickH = 20;
    private int bricksN = 40;
    private int paddleV = 5;
    private int ballV = 2;

    private int paddleX;
    private int ballX;
    private int ballY;
    private int ballXV;
    private int ballYV;
    private boolean[] bricks;

    private int brickColumns = 8;

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
        
        setFocusable(true); // Add this line to make the panel focusable
        requestFocusInWindow(); // Add this line to request focus for keyboard input
        
        repaint(); // Add this line to call the paintComponent method
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        setBackground(Color.BLACK); // Set background color to black

        // Draw paddle
        g.setColor(Color.WHITE);
        g.fillRect(paddleX, height - paddleH, paddleW, paddleH);

        // Draw ball
        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, ballR * 2, ballR * 2);

        // Draw bricks
        for (int i = 0; i < bricksN; i++) {
            if (bricks[i]) {
                int column = i % brickColumns;
                int row = i / brickColumns;
                int brickX = column * (brickW + 2) + 2;
                int brickY = row * (brickH + 2) + 50;
                g.setColor(Color.WHITE);
                g.fillRect(brickX, brickY, brickW, brickH);
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
            movePaddleLeft();
        } else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            movePaddleRight();
        }
    }

    private void movePaddleRight() {
    }

    private void movePaddleLeft() {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
}
