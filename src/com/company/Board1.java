package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class Board1 extends JPanel implements MouseMotionListener, MouseListener, ActionListener {
    private static final int  dimension = 10;
    private static Image[] img;
    private final int N_ROWS = 10;
    private final int N_COLS = 10;
    private final int CELL_SIZE = 30;
    private final int WIDTH = N_ROWS*CELL_SIZE ;
    private final int HEIGHT = N_COLS*CELL_SIZE +50;
    static int[][] field;
    int avioaneDescop;
    int nrAvioane;
    private final int N_AVIOANE = 3;
    public static boolean inGame;
    public static boolean run;
    private final int STANGA = 1;
    private final int DREAPTA = 2;
    private final int JOS = 3;
    private final int SUS = 4;
    public static int numarClick;
    static int[][] direction;
    private boolean gasit;

    public Board1() {
        //this.statusbar = statusbar;
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        img = new Image[13];
        img[0] = (new ImageIcon("src/0.png")).getImage();
        img[1] = (new ImageIcon("src/1.png")).getImage();
        img[2] = (new ImageIcon("src/2.png")).getImage();
        img[10] = (new ImageIcon("src/4.png")).getImage();
        img[11] = (new ImageIcon("src/11.png")).getImage();
        img[12] = (new ImageIcon("src/12.png")).getImage();
        this.addMouseListener(this);
       //this.addMouseMotionListener(this);
        newGame();
    }
    private void newGame() {
        //inGame = true;
        nrAvioane = 0;
        numarClick = 0;
        avioaneDescop = 0;
        field = new int [N_ROWS][N_COLS];
        direction = new int[N_ROWS][N_COLS];
        for(int i=0;i<N_ROWS;i++) {
            for(int j=0;j<N_COLS;j++)
            {
                field[i][j] = 0;
                direction[i][j] = 0;
                inGame = false;
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
            g.drawString("A castigat computerul!!",N_ROWS*CELL_SIZE/2 - 20,N_ROWS*CELL_SIZE+20);
            //g.drawString("Apasa click ca sa incepi un joc nou",N_ROWS*CELL_SIZE/2-90,N_ROWS*CELL_SIZE+30);
            run = false;
            Board.gata = true;
            //inGame = false;
           // newGame();
            //avioaneDescop = 0;
            //Board.inGame = false;
        }
        if (uncover == 0 && inGame) {
            inGame = false;
            // this.setText("Ai castigat!");
        }

    }

    public void mousePressed(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseMoved   (MouseEvent e) { }
    public void mouseEntered (MouseEvent e) {}
    public void mouseClicked (MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        int cCol = x / CELL_SIZE;
        int cRow = y / CELL_SIZE;

        boolean doRepaint = false;
        if(Board.inGame == false)
        {
            this.inGame =false;
            newGame();
        }
        if(Board.numarClick<= this.numarClick)
            run = false;
        else run = true;
        if(Board.numarClick == this.numarClick && Board.numarClick ==0) run = true;

        if (!inGame) {
            numarClick = 0;
            avioaneDescop = 0;
            if (SwingUtilities.isLeftMouseButton(e))
            {
                field[cRow][cCol] = 11;
            }
            else if (SwingUtilities.isRightMouseButton(e))
            {
                field[cRow][cCol] = 12;
                if (cRow<=N_ROWS-3 && cCol<=N_COLS-1 && cCol>=1)
                    if(field[cRow + 1][cCol] == 11 &&
                field[cRow + 2][cCol] == 11 &&
                field[cRow + 3][cCol] == 11 &&
                field[cRow + 1][cCol - 1] == 11 &&
                field[cRow + 1][cCol + 1] == 11 &&
                field[cRow + 3][cCol - 1] == 11 &&
                field[cRow + 3][cCol + 1] == 11)
                    direction[cRow][cCol] = STANGA;
                if(cRow>=3 && cCol>=1 && cCol<=N_COLS-1)
                    if(field[cRow - 1][cCol]== 11 &&
                field[cRow - 2][cCol]== 11 &&
                field[cRow - 3][cCol]== 11 &&
                field[cRow - 1][cCol - 1]== 11 &&
                field[cRow - 1][cCol + 1]== 11 &&
                field[cRow - 3][cCol - 1]== 11 &&
                field[cRow - 3][cCol + 1]== 11)
                direction[cRow][cCol] = DREAPTA;
                if(cCol<=N_COLS-3 && cRow>=1 && cRow<=N_ROWS-1) if(
                field[cRow][cCol + 1]== 11 &&
                field[cRow][cCol + 2]== 11 &&
                field[cRow][cCol + 3]== 11 &&
                field[cRow - 1][cCol + 1]== 11 &&
                field[cRow + 1][cCol + 1]== 11 &&
                field[cRow - 1][cCol + 3]== 11 &&
                field[cRow + 1][cCol + 3]== 11)
                direction[cRow][cCol] = JOS;
                if(cCol>=3 && cRow>=1 && cRow<=N_ROWS-1)
                    if(field[cRow][cCol - 1]== 11 &&
                field[cRow][cCol - 2]== 11 &&
                field[cRow][cCol - 3]== 11 &&
                field[cRow - 1][cCol - 1]== 11 &&
                field[cRow + 1][cCol - 1]== 11 &&
                field[cRow - 1][cCol - 3]== 11 &&
                field[cRow + 1][cCol - 3]== 11)
                direction[cRow][cCol] = SUS;
                nrAvioane++;
                if (nrAvioane == N_AVIOANE) inGame = true;
            }
            repaint();
        }

        if(inGame && run) {
            //if (SwingUtilities.isLeftMouseButton(e)) {

                System.out.println();
                for(int i=0;i<10;i++) {
                    for (int j = 0; j < 10; j++)
                        System.out.print(direction[i][j] + " ");
                    System.out.println();
                }
                do {
                    Random r = new Random();
                    int rand = r.nextInt(10);
                    int coloana = r.nextInt(10);
                    if (field[rand][coloana] > 10 || field[rand][coloana] == 0) {
                        numarClick++;
                        gasit = true;
                    }
                    else gasit = false;
                    if (field[rand][coloana] == 0) {
                        field[rand][coloana] += 10;
                    }
                    if (field[rand][coloana] > 10) {
                        doRepaint = true;
                        field[rand][coloana] -= 10;
                    }
                    if (field[rand][coloana] == 2) {
                        avioaneDescop++;
                        if (direction[rand][coloana] == STANGA) {
                            if (field[rand + 1][coloana] > 10)
                                field[rand + 1][coloana] -= 10;
                            if (field[rand + 2][coloana] > 10)
                                field[rand + 2][coloana] -= 10;
                            if (field[rand + 3][coloana] > 10)
                                field[rand + 3][coloana] -= 10;
                            if (field[rand + 1][coloana - 1] > 10)
                                field[rand + 1][coloana - 1] -= 10;
                            if (field[rand + 1][coloana + 1] > 10)
                                field[rand + 1][coloana + 1] -= 10;
                            if (field[rand + 3][coloana - 1] > 10)
                                field[rand + 3][coloana - 1] -= 10;
                            if (field[rand + 3][coloana + 1] > 10)
                                field[rand + 3][coloana + 1] -= 10;
                        } else if (direction[rand][coloana] == DREAPTA) {
                            if (field[rand - 1][coloana] > 10)
                                field[rand - 1][coloana] -= 10;
                            if (field[rand - 2][coloana] > 10)
                                field[rand - 2][coloana] -= 10;
                            if (field[rand - 3][coloana] > 10)
                                field[rand - 3][coloana] -= 10;
                            if (field[rand - 1][coloana - 1] > 10)
                                field[rand - 1][coloana - 1] -= 10;
                            if (field[rand - 1][coloana + 1] > 10)
                                field[rand - 1][coloana + 1] -= 10;
                            if (field[rand - 3][coloana - 1] > 10)
                                field[rand - 3][coloana - 1] -= 10;
                            if (field[rand - 3][coloana + 1] > 10)
                                field[rand - 3][coloana + 1] -= 10;
                        } else if (direction[rand][coloana] == JOS) {
                            if (field[rand][coloana + 1] > 10)
                                field[rand][coloana + 1] -= 10;
                            if (field[rand][coloana + 2] > 10)
                                field[rand][coloana + 2] -= 10;
                            if (field[rand][coloana + 3] > 10)
                                field[rand][coloana + 3] -= 10;
                            if (field[rand - 1][coloana + 1] > 10)
                                field[rand - 1][coloana + 1] -= 10;
                            if (field[rand + 1][coloana + 1] > 10)
                                field[rand + 1][coloana + 1] -= 10;
                            if (field[rand - 1][coloana + 3] > 10)
                                field[rand - 1][coloana + 3] -= 10;
                            if (field[rand + 1][coloana + 3] > 10)
                                field[rand + 1][coloana + 3] -= 10;
                        } else if (direction[rand][coloana] == SUS) {
                            if (field[rand][coloana - 1] > 10)
                                field[rand][coloana - 1] -= 10;
                            if (field[rand][coloana - 2] > 10)
                                field[rand][coloana - 2] -= 10;
                            if (field[rand][coloana - 3] > 10)
                                field[rand][coloana - 3] -= 10;
                            if (field[rand - 1][coloana - 1] > 10)
                                field[rand - 1][coloana - 1] -= 10;
                            if (field[rand + 1][coloana - 1] > 10)
                                field[rand + 1][coloana - 1] -= 10;
                            if (field[rand - 1][coloana - 3] > 10)
                                field[rand - 1][coloana - 3] -= 10;
                            if (field[rand + 1][coloana - 3] > 10)
                                field[rand + 1][coloana - 3] -= 10;
                        }
                    }
                    repaint();
                }while (gasit ==false);
            }
        }

    public void mouseReleased(MouseEvent e) {}
    public void actionPerformed(ActionEvent e) {}


}
