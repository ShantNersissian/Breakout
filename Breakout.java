import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.sound.sampled.*;

public class Breakout extends JFrame {
    
    // Dimensions
    private static final int WIDTH = 950;
    private static final int HEIGHT = 600;
    
    // Paddle properties
    private static final int PADDLE_WIDTH = 100;
    private static final int PADDLE_HEIGHT = 10;
    
    // Ball properties
    private static final int BALL_SIZE = 20;
    
    // Brick properties
    private static final int BRICK_WIDTH = 80;
    private static final int BRICK_HEIGHT = 20;
    private static final int NUM_BRICKS = 50;
    
    // Game properties
    private static final int FPS = 240;
    private static final int DELAY = 1000 / FPS;
    
    // Game components
    private JPanel gamePanel;
    private Timer timer;
    
    // Game state variables
    private int paddleX;
    private int ballX, ballY;
    private int ballXSpeed, ballYSpeed;
    private boolean isGameOver;
    private boolean[] bricks;
    private int lives;

    public Breakout() {
        initGame();
        initComponents();
        addListeners();
    }

    // Initializes the game state
    private void initGame() {
        paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
        ballX = WIDTH / 2 - BALL_SIZE / 2;
        ballY = HEIGHT / 2 - BALL_SIZE / 2;
        ballXSpeed = 5;
        ballYSpeed = -5;
        lives = 3;
        isGameOver = false;
        bricks = new boolean[NUM_BRICKS];
        for (int i = 0; i < NUM_BRICKS; i++) {
            bricks[i] = true;
        }
    }

    // Initializes the game components and sets their properties
    private void initComponents() {
        gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGame(g);
            }
        };
        gamePanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        gamePanel.setBackground(Color.BLACK);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gamePanel);
        pack();
        setTitle("Breakout");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    // Adds event listeners to the game panel and sets up the game timer
    private void addListeners() {
        gamePanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                paddleX = e.getX() - PADDLE_WIDTH / 2;
            }
        });

        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
                gamePanel.repaint();
            }
        });
        timer.start();
    }

    // Draws the game elements on the panel
    private void drawGame(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Lives: " + lives, 10, 30);

        if (isGameOver) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("Game Over", WIDTH / 2 - 120, HEIGHT / 2);
            playSound("message_for_mr.poole.wav");
            timer.stop();
            return;
        }

        g.setColor(Color.WHITE);
        g.fillRect(paddleX, HEIGHT - PADDLE_HEIGHT - 10, PADDLE_WIDTH, PADDLE_HEIGHT);

        g.setColor(Color.WHITE);
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

        int brickCount = 0;
        for (int i = 0; i < NUM_BRICKS; i++) {
            if (bricks[i]) {
                int brickX = (i % 10) * (BRICK_WIDTH + 10) + 30;
                int brickY = (i / 10) * (BRICK_HEIGHT + 10) + 50;
                g.setColor(Color.WHITE);
                g.fillRect(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
                brickCount++;
            }
        }

        if (brickCount == 0) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("You Win!", WIDTH / 2 - 100, HEIGHT / 2);
            timer.stop();
        }
    }

    // Updates the game state and performs game logic
    private void updateGame() {
        ballX += ballXSpeed;
        ballY += ballYSpeed;

        if (ballX <= 0 || ballX + BALL_SIZE >= WIDTH) {
            ballXSpeed = -ballXSpeed;
        }

        if (ballY <= 0) {
            ballYSpeed = -ballYSpeed;
        }

        if (ballY + BALL_SIZE >= HEIGHT) {
            lives--;

            if (lives <= 0) {
                isGameOver = true;
            } else {
                ballX = WIDTH / 2 - BALL_SIZE / 2;
                ballY = HEIGHT / 2 - BALL_SIZE / 2;
                paddleX = WIDTH / 2 - PADDLE_WIDTH / 2;
            }
        }

        if (ballY + BALL_SIZE >= HEIGHT - PADDLE_HEIGHT - 10 && ballX + BALL_SIZE >= paddleX && ballX <= paddleX + PADDLE_WIDTH) {
            ballYSpeed = -ballYSpeed;
        }

        for (int i = 0; i < NUM_BRICKS; i++) {
            if (bricks[i]) {
                int brickX = (i % 10) * (BRICK_WIDTH + 10) + 30;
                int brickY = (i / 10) * (BRICK_HEIGHT + 10) + 50;
                Rectangle brickRect = new Rectangle(brickX, brickY, BRICK_WIDTH, BRICK_HEIGHT);
                Rectangle ballRect = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);
                if (brickRect.intersects(ballRect)) {
                    bricks[i] = false;
                    ballYSpeed = -ballYSpeed;
                    playSound("bonk.wav");
                    break;
                }
            }
        }
    }

    // Plays a sound file
    private void playSound(String soundFile) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(soundFile));
    
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
    
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}