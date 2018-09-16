package MBEA;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solver {
    public static void main(String args[])
    {
        List<List<Integer>> adjMatrix = new ArrayList<>();
        Scanner input = null;

        try
        {
             input = new Scanner(new File("matrix.txt"));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        while(input.hasNextLine())
        {
            Scanner colReader = new Scanner(input.nextLine());
            List<Integer> col = new ArrayList<>();
            while(colReader.hasNextInt())
            {
                col.add(colReader.nextInt());
            }
            adjMatrix.add(col);
        }

        BicliqueFinder bicliqueFinder = new BicliqueFinder(new BipartiteGraph(adjMatrix));

        bicliqueFinder.findMaximalBicliques("standard");

    }
}
