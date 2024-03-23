package main;

import javax.swing.*;

public class Main {
    public static void main(String arg[])
    {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Project - Undead Revival");

        GamePanel gamepanel = new GamePanel();
        window.add(gamepanel);


        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamepanel.startGameThread();

    }
}