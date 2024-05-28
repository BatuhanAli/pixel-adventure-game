import javax.swing.JFrame;

public class Main{
    public static void main(String[] args) {
        JFrame frame = new JFrame("Old western");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        gamePanel gamePanel = new gamePanel();
        frame.add(gamePanel);
        frame.pack();
        
        gamePanel.startGameThread();
        frame.setVisible(true);
    }
}