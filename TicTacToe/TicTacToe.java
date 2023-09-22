import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private static final int SIZE = 3;
    private JButton[][] buttons;
    private char currentPlayer;
    private JPanel gamePanel;
    private JButton restartButton;
    private ImageIcon xIcon, oIcon;
    private JLabel statusLabel;

    public TicTacToe() {
        buttons = new JButton[SIZE][SIZE];
        currentPlayer = 'X';
        xIcon = new ImageIcon("x.png");
        oIcon = new ImageIcon("o.png");

        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initializeGamePanel();
        initializeStatusLabel();

        setSize(400, 450);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void initializeGamePanel() {
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(SIZE, SIZE));
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                JButton button = new JButton("");
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(this);
                buttons[row][col] = button;
                gamePanel.add(button);
            }
        }

        add(gamePanel, BorderLayout.CENTER);
    }



    private void initializeStatusLabel() {
        statusLabel = new JLabel("Player " + currentPlayer + "'s turn");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        statusLabel.setHorizontalAlignment(JLabel.CENTER);
        add(statusLabel, BorderLayout.NORTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            button.setText(String.valueOf(currentPlayer));
            button.setIcon(currentPlayer == 'X' ? xIcon : oIcon);
            button.setEnabled(false);

            if (checkWin()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            } else if (isBoardFull()) {
                JOptionPane.showMessageDialog(this, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                resetGame();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                statusLabel.setText("Player " + currentPlayer + "'s turn");
            }
        }
    }

    private boolean checkWin() {
        String[][] board = new String[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                board[row][col] = buttons[row][col].getText();
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[i][0].equals(String.valueOf(currentPlayer)) &&
                    board[i][1].equals(String.valueOf(currentPlayer)) &&
                    board[i][2].equals(String.valueOf(currentPlayer))) {
                return true;
            }
            if (board[0][i].equals(String.valueOf(currentPlayer)) &&
                    board[1][i].equals(String.valueOf(currentPlayer)) &&
                    board[2][i].equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }
        if (board[0][0].equals(String.valueOf(currentPlayer)) &&
                board[1][1].equals(String.valueOf(currentPlayer)) &&
                board[2][2].equals(String.valueOf(currentPlayer))) {
            return true;
        }
        if (board[0][2].equals(String.valueOf(currentPlayer)) &&
                board[1][1].equals(String.valueOf(currentPlayer)) &&
                board[2][0].equals(String.valueOf(currentPlayer))) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (buttons[row][col].getText().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void resetGame() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                buttons[row][col].setText("");
                buttons[row][col].setIcon(null);
                buttons[row][col].setEnabled(true);
            }
        }
        currentPlayer = 'X';
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe();
        });
    }
}
