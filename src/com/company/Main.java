package com.company;

import javax.swing.*;

public class Main extends JApplet {

    public static void main(String[] args) {
        JFrame window1 = new JFrame();
        window1.setTitle("Joc Avioane");
        window1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel content = new JPanel();
        Board b = new Board();
        Board1 b1 = new Board1();
        content.add(b);
        content.add(b1);
        window1.setContentPane(content);
        window1.pack();
        window1.setVisible(true);
		for(int i=0;i<10;i++)
		{
			for(int j=0;j<10;j++)
				if(b.field[i][j]== 0) System.out.print(b.field[i][j]+" ");
				else  System.out.print(b.field[i][j]-10+" ");
			System.out.println();
		}

    }

    public Main() {
        this.setContentPane(new Board());
    }
}
