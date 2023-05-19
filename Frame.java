import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public class Frame{
    public Frame(){
        JFrame jframe = new JFrame();
        ImageIcon image = new ImageIcon("breakout.png");
        ImageIcon ball = new ImageIcon("ball.png");
        JLabel label = new JLabel();
        Border border = BorderFactory.createLineBorder(Color.white );

        jframe.setSize(500, 500);
        jframe.setVisible(true);
        jframe.setIconImage(image.getImage());
        jframe.getContentPane().setBackground(Color.BLACK);
        jframe.setTitle("Breakout");
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);

        label.setIcon(ball);
        jframe.add(label);
        label.setBorder(border);
    }
}