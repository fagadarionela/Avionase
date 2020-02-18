package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board extends JPanel implements MouseMotionListener, MouseListener, ActionListener {
    private static final int  dimension = 10;
    private static Image[] img;
    private final int N_ROWS = 10;
    private final int N_COLS = 10;
    private final int CELL_SIZE = 30;
    private final int WIDTH = N_ROWS*CELL_SIZE ;
    private final int HEIGHT = N_COLS*CELL_SIZE +50;
    static int[][] field;
    int avioaneDescop;
    private final int N_AVIOANE = 3;
    public static boolean inGame;
    public static boolean run;
    public static boolean gata;
    private final int STANGA = 1;
    private final int DREAPTA = 2;
    private final int JOS = 3;
    private final int SUS = 4;
    public static int numarClick;
    static int[][] direction;

    public Board() {
        //this.statusbar = statusbar;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        img = new Image[13];
        img[0] = (new ImageIcon("src/0.png")).getImage();
        img[1] = (new ImageIcon("src/1.png")).getImage();
        img[2] = (new ImageIcon("src/2.png")).getImage();
        img[10] = (new ImageIcon("src/4.png")).getImage();
        img[11] = (new ImageIcon("src/3.png")).getImage();
        img[12] = (new ImageIcon("src/3.png")).getImage();
        this.addMouseListener(this);
        //this.addMouseMotionListener(this);
        newGame();
    }
    private void newGame() {
        inGame = true;
        Random r = new Random();
        field = new int [N_ROWS][N_COLS];
        direction = new int[N_ROWS][N_COLS];
        for(int i=0;i<N_ROWS;i++) {
            for(int j=0;j<N_COLS;j++)
            {
                field[i][j] = 0;
                direction[i][j] = 0;
            }
        }
        int i = 0;
        while (i < N_AVIOANE) {
            int positionX = r.nextInt(10);
            int positionY = r.nextInt(10);
            int dir = r.nextInt(4) + 1;

            if (field[positionX][positionY] == 0) //neocupat
            {
                if (dir == STANGA)
                    if (positionX <= 6 && positionY <= 8 && positionY >= 1)
                        if (field[positionX + 1][positionY] == 0 && field[positionX + 2][positionY] == 0 && field[positionX + 3][positionY] == 0 && field[positionX + 1][positionY - 1] == 0 && field[positionX + 1][positionY + 1] == 0 && field[positionX + 3][positionY - 1] == 0 && field[positionX + 3][positionY + 1] == 0) {
                            field[positionX][positionY] = 12;  //cap
                            field[positionX + 1][positionY] = 11;
                            field[positionX + 2][positionY] = 11;
                            field[positionX + 3][positionY] = 11;
                            field[positionX + 1][positionY - 1] = 11;
                            field[positionX + 1][positionY + 1] = 11;
                            field[positionX + 3][positionY - 1] = 11;
                            field[positionX + 3][positionY + 1] = 11;
                            direction[positionX][positionY] = STANGA;
                            i++;
                        }
                if (dir == DREAPTA)
                    if (positionX >= 3 && positionY <= 8 && positionY >= 1)
                        if (field[positionX - 1][positionY] == 0 && field[positionX - 2][positionY] == 0 && field[positionX - 3][positionY] == 0 && field[positionX - 1][positionY - 1] == 0 && field[positionX - 1][positionY + 1] == 0 && field[positionX - 3][positionY - 1] == 0 && field[positionX - 3][positionY + 1] == 0) {
                            field[positionX][positionY] = 12;  //cap
                            field[positionX - 1][positionY] = 11;
                            field[positionX - 2][positionY] = 11;
                            field[positionX - 3][positionY] = 11;
                            field[positionX - 1][positionY - 1] = 11;
                            field[positionX - 1][positionY + 1] = 11;
                            field[positionX - 3][positionY - 1] = 11;
                            field[positionX - 3][positionY + 1] = 11;
                            direction[positionX][positionY] = DREAPTA;
                            i++;
                        }
                if (dir == JOS)
                    if (positionX <= 8 && positionY <= 6 && positionX >= 1)
                        if (field[positionX][positionY + 1] == 0 && field[positionX][positionY + 2] == 0 && field[positionX][positionY + 3] == 0 && field[positionX - 1][positionY + 1] == 0 && field[positionX + 1][positionY + 1] == 0 && field[positionX - 1][positionY + 3] == 0 && field[positionX + 1][positionY + 3] == 0) {
                            field[positionX][positionY] = 12;  //cap
                            field[positionX][positionY + 1] = 11;
                            field[positionX][positionY + 2] = 11;
                            field[positionX][positionY + 3] = 11;
                            field[positionX - 1][positionY + 1] = 11;
                            field[positionX + 1][positionY + 1] = 11;
                            field[positionX - 1][positionY + 3] = 11;
                            field[positionX + 1][positionY + 3] = 11;
                            direction[positionX][positionY] = JOS;
                            i++;
                        }
                if (dir == SUS)
                    if (positionX <= 8 && positionY >= 3 && positionX >= 1)
                        if (field[positionX][positionY - 1] == 0 && field[positionX][positionY - 2] == 0 && field[positionX][positionY - 3] == 0 && field[positionX - 1][positionY - 1] == 0 && field[positionX + 1][positionY - 1] == 0 && field[positionX - 1][positionY - 3] == 0 && field[positionX + 1][positionY - 3] == 0) {
                            field[positionX][positionY] = 12;  //cap
                            field[positionX][positionY - 1] = 11;
                            field[positionX][positionY - 2] = 11;
                            field[positionX][positionY - 3] = 11;
                            field[positionX - 1][positionY - 1] = 11;
                            field[positionX + 1][positionY - 1] = 11;
                            field[positionX - 1][positionY - 3] = 11;
                            field[positionX + 1][positionY - 3] = 11;
                            direction[positionX][positionY] = SUS;
                            i++;
                        }

            }
        }
    }






    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawPolyline(xLine, yLine, nLine);
        //g.drawImage()
        int uncover = 3;
        for(int i=0;i<N_ROWS;i++)
        {
            for(int j=0;j<N_COLS;j++)
            {
                int cell = field[i][j];
                g.drawImage(img[cell], (j * CELL_SIZE),
                        (i * CELL_SIZE), this);


            }
        }
        g.drawString("Ai apasat de " + numarClick + " ori.",N_ROWS*CELL_SIZE/2 - 40,N_ROWS*CELL_SIZE+10);
        if(avioaneDescop == N_AVIOANE) {
            g.drawString("Ai castigat!!",N_ROWS*CELL_SIZE/2 - 20,N_ROWS*CELL_SIZE+20);
            //g.drawString("Apasa click ca sa incepi un joc nou",N_ROWS*CELL_SIZE/2-90,N_ROWS*CELL_SIZE+30);
            run = false;
            gata = true;
            //inGame = false;
        }
        if (uncover == 0 && inGame) {
            inGame = false;
            // this.setText("Ai castigat!");
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved   (MouseEvent e) {}
    public void mouseEntered (MouseEvent e) {}
    public void mouseClicked (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int cCol = x / CELL_SIZE;
        int cRow = y / CELL_SIZE;

        boolean doRepaint = false;

        if (!inGame) {
            newGame();
            numarClick = 0;
            avioaneDescop = 0;
            repaint();
        }
        if(this.numarClick-Board1.numarClick>0) run = false;
        else run = true;
        if (!gata && run &&(x < N_COLS * CELL_SIZE) && (y < N_ROWS * CELL_SIZE)) {

            if (SwingUtilities.isLeftMouseButton(e)) {
                if(field[cRow][cCol] > 10 || field[cRow][cCol] ==0)numarClick ++;
                if (field[cRow][cCol] == 0) {
                    field[cRow][cCol] +=10;
                }
                if (field[cRow][cCol] > 10) {
                    doRepaint = true;
                    field[cRow][cCol] -= 10;
                    if (field[cRow][cCol] == 2)
                    {
                        avioaneDescop ++;
                        if (direction[cRow][cCol] == STANGA)
                        {
                            if (field[cRow + 1][cCol]>10)
                                field[cRow + 1][cCol] -= 10;
                            if(field[cRow + 2][cCol]>10)
                                field[cRow + 2][cCol] -= 10;
                            if(field[cRow + 3][cCol] >10)
                                field[cRow + 3][cCol] -= 10;
                            if(field[cRow + 1][cCol-1] >10)
                                field[cRow  + 1][cCol - 1] -= 10;
                            if(field[cRow + 1][cCol+1] >10)
                                field[cRow  + 1][cCol + 1] -= 10;
                            if(field[cRow  + 3][cCol - 1] > 10)
                              field[cRow  + 3][cCol - 1] -= 10;
                            if(field[cRow  + 3][cCol + 1] > 10)
                             field[cRow  + 3][cCol + 1] -= 10;
                        }
                        else if(direction[cRow][cCol] == DREAPTA)
                        {
                            if(field[cRow  - 1][cCol] > 10)
                            field[cRow  - 1][cCol] -= 10;
                            if(field[cRow  - 2][cCol] > 10)
                            field[cRow  - 2][cCol] -= 10;
                            if(field[cRow  - 3][cCol] > 10)
                            field[cRow  - 3][cCol] -= 10;
                            if(field[cRow  - 1][cCol - 1] > 10)
                            field[cRow  - 1][cCol - 1] -= 10;
                            if(field[cRow  - 1][cCol + 1] > 10)
                            field[cRow  - 1][cCol + 1] -= 10;
                            if(field[cRow  - 3][cCol - 1] > 10)
                            field[cRow  - 3][cCol - 1] -= 10;
                            if(field[cRow  - 3][cCol + 1] > 10)
                            field[cRow  - 3][cCol + 1] -= 10;
                        }
                        else if(direction[cRow][cCol] == JOS)
                        {
                            if(field[cRow ][cCol + 1]>10)
                            field[cRow ][cCol + 1] -= 10;
                            if(field[cRow ][cCol + 2] > 10)
                            field[cRow ][cCol + 2] -= 10;
                            if(field[cRow ][cCol + 3]>10)
                            field[cRow ][cCol + 3] -= 10;
                            if(field[cRow - 1][cCol + 1]>10)
                            field[cRow - 1][cCol + 1] -= 10;
                            if(field[cRow  + 1][cCol + 1]>10)
                            field[cRow  + 1][cCol + 1] -= 10;
                            if(field[cRow - 1][cCol + 3] >10)
                            field[cRow - 1][cCol + 3] -= 10;
                            if(field[cRow  + 1][cCol + 3] >10)
                            field[cRow  + 1][cCol + 3] -= 10;
                        }
                        else if(direction[cRow][cCol] == SUS) {
                            if(field[cRow ][cCol - 1] >10)
                            field[cRow ][cCol - 1] -= 10;
                            if(field[cRow ][cCol - 2] >10)
                            field[cRow ][cCol - 2] -= 10;
                            if(field[cRow ][cCol - 3] >10)
                            field[cRow ][cCol - 3] -= 10;
                            if(field[cRow  - 1][cCol - 1] >10)
                            field[cRow  - 1][cCol - 1] -= 10;
                            if(field[cRow  + 1][cCol - 1] >10)
                            field[cRow  + 1][cCol - 1] -= 10;
                            if(field[cRow  - 1][cCol - 3] >10)
                            field[cRow  - 1][cCol - 3] -= 10;
                            if(field[cRow  + 1][cCol - 3] >10)
                            field[cRow  + 1][cCol - 3] -= 10;
                        }
                    }
                }
            }
        }
        repaint();
    }
    public void mouseReleased(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}


}
