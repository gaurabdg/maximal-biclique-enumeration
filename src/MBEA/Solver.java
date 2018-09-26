package MBEA;

import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Solver {
    public static void main(String args[])
    {

        List<List<Integer>> adjMatrix = new ArrayList<>();
        Scanner input = null;

        try
        {
             input = new Scanner(new File(args[0]));
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


        bicliqueFinder.findMaximalBicliques(args[1]);

    // BENCHMARK TESTING FOR RANDOM BINARY MATRIX


//        Random random = new Random();
//
//        int size = 4;
//        for(int i=0;i<size;i++)
//        {
//            adjMatrix.add(new ArrayList<>());
//            for(int j=0;j<size;j++)
//            {
//                if(random.nextDouble() < 0.5)
//                    adjMatrix.get(i).add(1);
//                else
//                    adjMatrix.get(i).add(0);
//            }
//        }

//        for(int i=0;i<size;i++)
//        {
//            adjMatrix.add(new ArrayList<>());
//            for(int j=0;j<size;j++)
//            {
//                System.out.print(adjMatrix.get(i).get(j));
//            }
//            System.out.print(" ");
//        }

//        BicliqueFinder bicliqueFinder = new BicliqueFinder(new BipartiteGraph(adjMatrix));
//
//
//        System.out.println("Maximal Bicliques: ");
//        long startTime = System.currentTimeMillis();
//        bicliqueFinder.findMaximalBicliques("standard");
//        long endTime = System.currentTimeMillis();
//
//        long startTime1 = System.currentTimeMillis();
//        bicliqueFinder.findMaximalBicliques("improved");
//        long endTime1 = System.currentTimeMillis();
//
//        System.out.println("*******Standard*******");
//        System.out.println("No. of Maximal Bicliques: "+bicliqueFinder.getMaximalBicliques().size());
//        System.out.println("Total execution time: " + (endTime-startTime) + "ms");
//        System.out.println("*******Improved*******");
//        System.out.println("No. of Maximal Bicliques: "+bicliqueFinder.getMaximalBicliques().size());
//        System.out.println("Total execution time: " + (endTime1-startTime1) + "ms");



}
    }

