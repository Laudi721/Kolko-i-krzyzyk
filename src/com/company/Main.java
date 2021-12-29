package com.company;

import java.awt.*;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.swing.*;

public class Main  {
    public static void main(String[] args)
    {

        var kik = new KolkoIKrzyzyk();
    }

}

class KolkoIKrzyzyk implements ActionListener
{
    Random rnd = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    char whoWins =' ';
    int count = 0;


    KolkoIKrzyzyk()
    {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,600);
        frame.getContentPane().setBackground(new Color(50,50,50));
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Free", Font.BOLD,75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Kolko i krzyzyk ");
        textField.setOpaque(true);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0,0,500,100);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(125,125,125));

        for(var i = 0; i < 9; i++){
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD,120));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add((textField));
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for(var i = 0; i < 9; i++)
        {
            if(e.getSource()==buttons[i])
            {
                if (player1_turn)
                {
                    if (buttons[i].getText() == "")
                    {
                        buttons[i].setForeground(Color.red);
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                    }
                } else
                {
                    if (buttons[i].getText() == "")
                    {
                        buttons[i].setForeground(Color.blue);
                        buttons[i].setText("O");
                        player1_turn = true;
                        textField.setText("X turn");
                        check();
                    }
                }
            }
        }
    }

    public void firstTurn()
    {
        try
        {
            Thread.sleep(1000);
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if(rnd.nextInt(2) == 0)
        {
            player1_turn = true;
            textField.setText("X turn");
        }else
        {
            player1_turn = false;
            textField.setText("O turn");
        }
    }

    public void check()
    {
        while (buttons[count].getText() != "") {
            if (count == buttons.length - 1) {
                Draws();
                break;
            }
            count++;
        }

        // check player X
        if(     (buttons[0].getText()=="X") &&
                (buttons[1].getText()=="X") &&
                (buttons[2].getText()=="X"))
        {
            xWins(0,1,2);
        }

        if(     (buttons[3].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[5].getText()=="X"))
        {
            xWins(3,4,5);
        }

        if(     (buttons[6].getText()=="X") &&
                (buttons[7].getText()=="X") &&
                (buttons[8].getText()=="X"))
        {
            xWins(6,7,8);
        }

        if(     (buttons[0].getText()=="X") &&
                (buttons[3].getText()=="X") &&
                (buttons[6].getText()=="X"))
        {
            xWins(0,3,6);
        }

        if(     (buttons[1].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[7].getText()=="X"))
        {
            xWins(1,4,7);
        }

        if(     (buttons[2].getText()=="X") &&
                (buttons[5].getText()=="X") &&
                (buttons[8].getText()=="X"))
        {
            xWins(2,5,8);
        }

        if(     (buttons[0].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[8].getText()=="X"))
        {
            xWins(0,4,8);
        }

        if(     (buttons[2].getText()=="X") &&
                (buttons[4].getText()=="X") &&
                (buttons[6].getText()=="X"))
        {
            xWins(2,4,6);
        }

        // check player O
        if(     (buttons[0].getText()=="O") &&
                (buttons[1].getText()=="O") &&
                (buttons[2].getText()=="O"))
        {
            oWins(0,1,2);
        }

        if(     (buttons[3].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[5].getText()=="O"))
        {
            oWins(3,4,5);
        }

        if(     (buttons[6].getText()=="O") &&
                (buttons[7].getText()=="O") &&
                (buttons[8].getText()=="O"))
        {
            oWins(6,7,8);
        }

        if(     (buttons[0].getText()=="O") &&
                (buttons[3].getText()=="O") &&
                (buttons[6].getText()=="O"))
        {
            oWins(0,3,6);
        }

        if(     (buttons[1].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[7].getText()=="O"))
        {
            oWins(1,4,7);
        }

        if(     (buttons[2].getText()=="O") &&
                (buttons[5].getText()=="O") &&
                (buttons[8].getText()=="O"))
        {
            oWins(2,5,8);
        }

        if(     (buttons[0].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[8].getText()=="O"))
        {
            oWins(0,4,8);
        }

        if(     (buttons[2].getText()=="O") &&
                (buttons[4].getText()=="O") &&
                (buttons[6].getText()=="O"))
        {
            oWins(2,4,6);
        }
    }

    public void Draws()
    {
        for(var i = 0; i<9; i++){
            buttons[i].setEnabled((false));
        }
            textField.setText("Draw");
            this.whoWins = 'D';
            SaveToFile();
    }

    public void xWins(int a, int b, int c)
    {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(var i = 0; i<9; i++){
            buttons[i].setEnabled((false));
        }
        textField.setText("X wins");
        this.whoWins = 'X';
        SaveToFile();
    }

    public void oWins(int a, int b, int c)
    {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(var i = 0; i<9; i++){
            buttons[i].setEnabled((false));
        }
        textField.setText("O wins");
        this.whoWins = 'O';
        SaveToFile();
    }

    // Metoda tworzaca plik .txt i zapisujaca do niego kolejne rozegrane gry. Plik zostaje utworzony w glownym folderze projektu.
    public void SaveToFile(){
        PrintWriter save;
        try
        {
            var date = LocalDateTime.now();
            var dateFormat = DateTimeFormatter.ofPattern("\t dd-MM-yyyy \t HH:mm");
            var formattedDate = date.format(dateFormat);

            save = new PrintWriter(new FileOutputStream("Game_History.txt", true));
            if(whoWins == 'X'){
                save.write("\nX wins " + formattedDate);
                save.close();
            }
            if(whoWins == 'O'){
                save.write("\nO wins " + formattedDate);
                save.close();
            }
            if(whoWins == 'D')
            {
                save.write("\nDraw " + formattedDate);
                save.close();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}


