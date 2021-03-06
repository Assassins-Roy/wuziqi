package main;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 图形界面
 */
public class Windows extends JFrame implements MouseListener {
    public Chess chess;
    private static final long serialVersionUID = 1L;
    private int var = 1;
    public Windows(String title) {
        super(title);
        chess = new Chess(19, 19);
        this.setSize(700, 600);
        this.setLocation(800, 200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
        this.addMouseListener(this);
    }

    //绘画格子和棋子
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        // 横
        for (int i = 0; i < 19; i++)
            g.drawLine(30, 30 + i * 30, 570, 30 + i * 30);
        // 竖线
        for (int i = 0; i < 19; i++)
            g.drawLine(30 + i * 30, 60, 30 + i * 30, 570);

        int[][] board = chess.getArr();
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (board[i][j] == 1)
                    g.drawOval(20 + i * 30, 50 + j * 30, 20, 20);
                if(board[i][j]==2)
                    g.fillOval(20+i*30, 50+j*30, 20, 20);
            }
        }
        g.drawRect(600,60, 50, 30);
        g.drawString("悔棋",610,80);
        g.drawRect(600,120,50, 30);
        g.drawString("开始",610,140);
        g.drawRect(600,180,50, 30);
        g.drawString("设置",610,200);

    }

    @Override
    public void mouseClicked(MouseEvent arg0) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        if (e.getX() < 570 && e.getY() < 570) {
            int a = chess.ChessIt(_CgetX(e.getX()), (_CgetY(e.getY())), var);
            this.repaint();
            if (a == 1) {
                JOptionPane.showMessageDialog(null,"白棋胜利", "恭喜", JOptionPane.DEFAULT_OPTION);
            }
            if(a==2) {
                JOptionPane.showMessageDialog(null,"黑棋胜利", "恭喜", JOptionPane.DEFAULT_OPTION);
            }
            if(a!=-1) {
                if(var==1) var=2;
                else if(var==2) var=1;
            }
        }
        else if(e.getX()>600&&e.getX()<650&&e.getY()>60&&e.getY()<90) {
            chess.RetChess();
            if(var==1) var=2;
            else if(var==2) var=1;
            this.repaint();
        }
        if(e.getX()>600&&e.getX()<650&&e.getY()>120&&e.getY()<150) {
            chess.Restart();
            this.repaint();
        }
        if(e.getX()>600&&e.getX()<650&&e.getY()>180&&e.getY()<210) {
            Object[] options = {"白先","黑先"};
            int n = JOptionPane.showOptionDialog(null,"白先还是黑先？","游戏设置",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, null,options,options[0]);
            if(n==0) this.var=1;
            if(n==1) this.var=2;
            this.chess.Restart();
            this.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    private int _CgetX(int x) {
        x -= 30;
        if (x % 15 <= 7)
            return x / 30;
        else
            return x / 30 + 1;
    }

    private int _CgetY(int y) {
        y -= 60;
        if (y % 15 <= 7)
            return y / 30;
        else
            return y / 30 + 1;
    }
}