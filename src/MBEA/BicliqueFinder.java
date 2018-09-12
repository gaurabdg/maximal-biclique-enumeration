package MBEA;

import java.util.ArrayList;

public class BicliqueFinder extends Biclique {
    private boolean foundAll = false;
    private int maxPossible;
    private BipartiteGraph graph;
    private VertexSet initL;
    private VertexSet initP;
    private VertexSet initR;
    private VertexSet initQ;
    private ArrayList<Biclique> maximalBicliques;


    BicliqueFinder(BipartiteGraph inGraph)
    {
        graph = inGraph;
        initL = new VertexSet(graph.getLeftNodes());
        initP = new VertexSet(graph.getRightNodes());

        initR = new VertexSet();
        initQ = new VertexSet();
//                printList(initQ.getSetV());
        maximalBicliques = new ArrayList<Biclique>();

    }

    void findMaximalBicliques(String algType)
    {
        if(algType.equals("standard"))
        {
//            printList(initL.getSetV());
            bicliqueFind(initL, initR, initP, initQ);
            foundAll = true;
        }
        else if(algType.equals("improved"))
        {
            initP.sortByNumOfNeighbours();
            bicliqueFindimP(initL,initR,initP,initQ);
        }
    }

    ArrayList<Biclique> getMaximalBicliques()
    {
        if(foundAll)
            return maximalBicliques;
        else
            System.out.println("not found yet");
        return null;
    }

    String getLRPQinit()
    {
        String res = null;
        for (int i = 0; i < initL.getSize(); i++){
            res += Integer.toString((initL.getVertex(i).getLabel())) + " ";
        }
        res += "\n";
        for (int i = 0; i < initR.getSize(); i++){
            res += Integer.toString((initR.getVertex(i).getLabel())) + " ";
        }
        res += "\n";
        for (int i = 0; i < initP.getSize(); i++){
            res += Integer.toString((initP.getVertex(i).getLabel())) + " ";
        }
        res += "\n";
        for (int i = 0; i < initQ.getSize(); i++){
            res += Integer.toString((initQ.getVertex(i).getLabel())) + " ";
        }
        res += "\n";

        return res;
    }

    private void bicliqueFind(VertexSet inL, VertexSet inR, VertexSet inP, VertexSet inQ)
    {
        VertexSet L = inL;
        VertexSet R = inR;
        VertexSet P = inP;
        VertexSet Q = inQ;
        int cond = 0;
//        System.out.println("L: "+L.toStringVertexSet());
//        System.out.println("R: "+R.toStringVertexSet());
//        System.out.println("P: "+P.toStringVertexSet());
//        System.out.println("Q: "+Q.toStringVertexSet());

        while (!P.isSetEmpty())
        {
            Vertex x = P.getVertex(0);
            //System.out.println("x: "+x.getLabel());
            VertexSet Rprime;
            if(cond==0)
                Rprime = R;
            else
                Rprime = new VertexSet();
            Rprime.addVertex(x);
            //System.out.println("Rpr: "+Rprime.toStringVertexSet());

            VertexSet Lprime = new VertexSet();

            for(int j=0;j<L.getSize();j++)
            {
                Vertex u = L.getVertex(j);
                //System.out.println("u: "+u.getLabel());
                if(u.isNeighbour(x))
                {
                    Lprime.addVertex(u);
                    //System.out.println("Lpr: "+Lprime.toStringVertexSet());
                }
            }

            VertexSet Pprime = new VertexSet();
            VertexSet Qprime = new VertexSet();

            boolean isMax = true;

            for(int j=0;j<Q.getSize();j++)
            {
                Vertex v = Q.getVertex(j);
                //System.out.println("v: "+v.getLabel());
                int numLprimeNeighbours = v.numberOfNeighboursOfVInSet(Lprime.getSetV());
                //System.out.println("numLprNeigh: "+numLprimeNeighbours);
                if(numLprimeNeighbours == Lprime.getSize())
                {
                    //System.out.println("inside if 1");
                    isMax = false;
                    break;
                }
                else if (numLprimeNeighbours > 0)
                {
                    Qprime.addVertex(v);
                    //System.out.println("inside else if 1");
                    //System.out.println("Qpr: "+Qprime.toStringVertexSet());
                }
            }

            if(isMax)
            {
               // System.out.println("inside if 2");
                for(int j=0;j<P.getSize();j++)
                {
                    Vertex v = P.getVertex(j);
                    //System.out.println("v: "+v.getLabel());
                    if(v.isEqual(x)) // doubt equals
                    {
                       // System.out.println("inside if 2.1");
                        continue;
                    }
                    //System.out.println("Lrp1: "+Lprime.toStringVertexSet());
                    int numLprimeNeighbours = v.numberOfNeighboursOfVInSet(Lprime.getSetV());
                    //System.out.println("numLprNeighs1: "+numLprimeNeighbours);
                    if(numLprimeNeighbours == Lprime.getSize()) {
                        Rprime.addVertex(v);
                        //System.out.println("Rpr1: "+Rprime.toStringVertexSet());
                    }
                    else if(numLprimeNeighbours > 0){
                        Pprime.addVertex(v);
                        //System.out.println("Ppr: "+Pprime.toStringVertexSet());
                    }

                }

                Biclique bcq = new Biclique(Lprime.getSetV(), Rprime.getSetV());
                bcq.isMaximal = true;
                System.out.println(bcq.toStringBiclique());
                maximalBicliques.add(bcq);

                if(!Pprime.isSetEmpty()){
                   // System.out.println("calling again");
                    bicliqueFind(Lprime,Rprime,Pprime,Qprime);
                    cond = 1;
                }
                else
                    cond = 1;

            }
            P.removeVertex(x);
            //System.out.println("P1: "+P.toStringVertexSet());
            Q.addVertex(x);
            //System.out.println("Q1: "+Q.toStringVertexSet());
        }
    }

    private void bicliqueFindimP(VertexSet inL, VertexSet inR, VertexSet inP, VertexSet inQ)
    {
        VertexSet L = inL;
        VertexSet R = inR;
        VertexSet P = inP;
        VertexSet Q = inQ;

        while (!P.isSetEmpty())
        {
            Vertex x = P.getVertex(0);
            VertexSet Rprime = R;
            Rprime.addVertex(x);

            VertexSet Lprime = new VertexSet();
            VertexSet overlineLprime = L;
            VertexSet C = new VertexSet();

            for(int j=0;j<L.getSize();j++)
            {
                Vertex u = L.getVertex(j);
                if(u.isNeighbour(x))
                {
                    Lprime.addVertex(u);
                    overlineLprime.removeVertex(u);
                }
            }

            C.addVertex(x);

            VertexSet Pprime = new VertexSet();
            VertexSet Qprime = new VertexSet();

            isMaximal = true;

            for(int j=0;j<Q.getSize();j++)
            {
                Vertex v = Q.getVertex(j);
                int numLprimeNeighbours = v.numberOfNeighboursOfVInSet(Lprime.getSetV());

                if(numLprimeNeighbours == Lprime.getSize())
                {
                    isMaximal = false;
                    break;
                }
                else if (numLprimeNeighbours > 0)
                {
                    Qprime.addVertex(v);
                }
            }

            if(isMaximal)
            {
                for(int j=0;j<P.getSize();j++)
                {
                    Vertex v = P.getVertex(j);
                    if(v.isEqual(x)) // doubt equals
                        continue;

                    int numLprimeNeighbours = v.numberOfNeighboursOfVInSet(Lprime.getSetV());
                    if(numLprimeNeighbours == Lprime.getSize()) {
                        Rprime.addVertex(v);
                        int numoverlineLprimeneighbours = v.numberOfNeighboursOfVInSet(overlineLprime.getSetV());
                        if(numoverlineLprimeneighbours == 0)
                            C.addVertex(v);
                    }
                    else if(numLprimeNeighbours > 0)
                        Pprime.addVertex(v);
                }

                Biclique bcq = new Biclique(Lprime.getSetV(), Rprime.getSetV());
                bcq.isMaximal = true;
                maximalBicliques.add(bcq);

                if(!Pprime.isSetEmpty())
                    bicliqueFindimP(Lprime,Rprime,Pprime,Qprime);
            }

            for(int j=0;j<C.getSize();j++)
            {
                Vertex v = C.getVertex(j);
                Q.addVertex(v);
                P.removeVertex(v);
            }
        }
    }

    int getNumBicliques()
    {
        if(foundAll)
            return maximalBicliques.size();
        return 0;
    }

    String toStringBicliqueF()
    {


        if(foundAll)
        {
            String res = "";
            for(int i=0;i<maximalBicliques.size();i++)
            {
                Biclique b = maximalBicliques.get(i);
                res += b.toStringBiclique();
                res += "\n";
            }
            return res;
        }
        else
            return null;

    }
}
