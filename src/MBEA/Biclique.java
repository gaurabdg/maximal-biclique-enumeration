package MBEA;

import java.util.ArrayList;
import java.util.List;

import static MBEA.Vertex.addEdge;

public class Biclique {
    boolean isMaximal;
    private List<List<Integer>> incidenceMatrix ;
    protected List<Vertex> leftNodes ;
    protected List<Vertex> rightNodes ;
    List<List<Vertex>> leftNeighbours ;
    List<List<Vertex>> rightNeighbours ;
    Biclique() {}

    public boolean isMaximal() {
        return isMaximal;
    }

    public List<List<Integer>> getIncidenceMatrix() {
        return incidenceMatrix;
    }

    public List<Vertex> getLeftNodes() {
        return leftNodes;
    }

    public List<Vertex> getRightNodes() {
        return rightNodes;
    }

    public List<List<Vertex>> getLeftNeighbours() {
        return leftNeighbours;
    }

    public List<List<Vertex>> getRightNeighbours() {
        return rightNeighbours;
    }

    Biclique(List<List<Integer>> incMat)

    {
        incidenceMatrix = incMat;
    }

    Biclique(List<Vertex> leftV, List<Vertex> rightV)
    {
        leftNeighbours = new ArrayList<>();
        rightNeighbours = new ArrayList<>();
        leftNodes = leftV;
        rightNodes = rightV;
        // TODO check for duplicate vertices
//        List<Vertex> tmp = leftNodes;
//        tmp.addAll(rightNodes);


        for(int i=0;i<leftNodes.size();i++)
        {

            for(int j=0;j<rightNodes.size();j++)
            {
                Vertex left = leftNodes.get(i);
                Vertex right = rightNodes.get(j); //i to j
                try {
                    Vertex.addEdge(left,right);
                } catch (RuntimeException e) {
                }
            }
        }

        for(int i=0;i<leftNodes.size();i++)
        {
            Vertex leftVer = leftNodes.get(i);
            leftNeighbours.add(leftVer.getNeighbours());
        }

        for(int i=0;i<rightNodes.size();i++)
        {
            Vertex rightVer = rightNodes.get(i);
            rightNeighbours.add(rightVer.getNeighbours());
        }

    }

    String toStringBiclique()
    {
        String res = "";

        for(int i=0;i< leftNodes.size();i++)
        {
            res += Integer.toString(leftNodes.get(i).getLabel()) + " ";
        }

        res += "| ";

        for(int i=0;i< rightNodes.size();i++)
        {
            res += Integer.toString(rightNodes.get(i).getLabel()) + " ";
//            System.out.println(rightNodes.get(i).getLabel());
        }
        return res;
    }

    private String getNeighbourhoodString(List<List<Vertex>> neighbours)
    {
        String res = "";
        for(int i=0;i<neighbours.size();i++)
        {
            List<Vertex> nvList = neighbours.get(i);
            for(int j=0;j<nvList.size();j++)
            {
                Vertex v = nvList.get(j);
                int lab = v.getLabel();
                res += Integer.toString(lab) + " ";
            }
            res += "\n";
        }
        return res;
    }

    void printNeighbourhoods()
    {
        System.out.println(getNeighbourhoodString(leftNeighbours));
        System.out.println("\n");
        System.out.println(getNeighbourhoodString(rightNeighbours));
    }

    void printList(List<Vertex> ls)

    {
        for(Vertex v:ls)
            System.out.println(v.getLabel());
    }

    void printGraph()
    {
        printList(getLeftNodes());
        printList(getRightNodes());
    }
}
