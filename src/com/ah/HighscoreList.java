package com.ah;

import java.io.*;
import java.util.Scanner;


public class HighscoreList {

    public HighscoreList() {
    }


    public void updateHighscore(String name, int newScore) {

        File file = new File("Highscore.txt"); // Skapar fil
        try {
            Scanner inFile = new Scanner(file);

            FileWriter fw = new FileWriter("Highscore.tmp"); // Temp. fil
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outFile = new PrintWriter(bw);

            boolean done = false;

            while (inFile.hasNext()) {  // Hanterar lista
                String line = inFile.nextLine();
                String strScore = line.split(";")[1];
                int intScore = Integer.parseInt(strScore);
                if (newScore > intScore && !done) {
                    outFile.println(name + ";" + newScore);
                    done = true;
                }
                outFile.println(line);
            }
            if (!done)
                outFile.println(name + ";" + newScore);

            inFile.close();  // Raderar temp fil.
            outFile.close();
            file.delete();
            File newHigh = new File("Highscore.tmp");
            newHigh.renameTo(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
