//Note - This program assumes your window is 600 by 600

import java.util.concurrent.TimeUnit;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;


public class Main extends Applet implements MouseListener {
    private String[][] board = new String[3][3];
    private String player = "";
    private int turn = 0;
    
    private boolean isWin = false;
    private boolean isTie = false;
    
    private int x = 0;
    private int y = 0;
    
    
    
    public void init() {
        setBackground(Color.black);
        addMouseListener(this);
    }
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    
    public void mousePressed(MouseEvent e) {
        
        if (isWin || isTie) return;
        
        if (turn % 2 == 0) player = "O";
        else player = "X";
        
        x = e.getX();
        y = e.getY();
        
        int row = x / 200;
        int col = y / 200;
        
        if (x == 600) row = 2;
        if (y == 600) col = 2;
        
        
        if (board[row][col] == null) {
            board[row][col] = player;
            turn++;
        }
        
        repaint();
    }
    
    public void mouseReleased(MouseEvent e) {}
    
    
    
    
    
    public boolean checkWin(String player) {
        
        for (int i = 0; i < 3; i++) {
                
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                isWin = true;
                return true;
                
            } if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                isWin = true;
                return true;
            }
        }
        
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            isWin = true;
            return true;
        }
        
        if (board[2][0] == player && board[1][1] == player && board[0][2] == player) {
            isWin = true;
            return true;
        }
        
        return false;
    }
    
    
    public boolean checkTie() {
        for (String[] row: board) {
            for (String sym: row) {
                if (sym == null) return false;
            }
        }
        
        isTie = true;
        return true;
    }
    
    
    
    
    public void paint(Graphics g) {
        if (isWin || isTie) return;
        
        g.setColor(Color.white);
        g.drawLine(200, 0, 200, 600);
        g.drawLine(400, 0, 400, 600);
        
        g.drawLine(0, 200, 600, 200);
        g.drawLine(0, 400, 600, 400);
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                
                if (board[i][j] != null) {
                    int xT = (i*200) + 10;
                    int yT = (j*200) + 187;
                    
                    g.setFont(new Font("Monospaced", Font.PLAIN, 300));
                    g.drawString(board[i][j], xT, yT);
                }
            }
        }
          
        checkOver(g); //paint method is solely for graphics
       //thus, we will do further calculations in a separate method
    }
    
    
    public void checkOver(Graphics g) {
        boolean p1 = checkWin("O");
        boolean p2 = checkWin("X");
        boolean tie = checkTie();
        
        if (p1) end(g, "O");
        if (p2) end(g, "X");
        if (tie) end(g, null);
        
    }
    
    
    public void end(Graphics g, String player) {
        
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        g.setColor(Color.black);
        g.fillRect(0, 0, 600, 600);
        
        if (player != null) {
            g.setColor(Color.white);
            g.setFont(new Font("Monospaced", Font.PLAIN, 50));
            g.drawString("Player " + player + " won!", 120, 300);
            return;
            
        } else {
            g.setColor(Color.white);
            g.setFont(new Font("Monospaced", Font.PLAIN, 50));
            g.drawString("It's a Tie!", 150, 300);
            return;
        }
                    
    }
}
