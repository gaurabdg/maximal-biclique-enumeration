package MBEA;

import java.util.ArrayList;
import java.util.List;

public class BipartiteGraph {

    protected List<List<Integer>> incidenceMatrix ;
    protected List<Vertex> leftNodes = new ArrayList<>();
    protected List<Vertex> rightNodes = new ArrayList<>() ;
    protected List<List<Vertex>> leftNeighbours = new ArrayList<>();
    protected List<List<Vertex>> rightNeighbours = new ArrayList<>();
    BipartiteGraph() {}
    BipartiteGraph(List<List<Integer>> incMat)
    {
        incidenceMatrix = incMat;

        checkInput(incidenceMatrix);

        int leftStart = 1;
        int rightStart = incidenceMatrix.size()+1;

        List<List<Integer>> transposed = transpose(incidenceMatrix);

        for(int i = 0;i<incidenceMatrix.size();i++)
        {
            Vertex leftNode = new Vertex(leftStart+i);
            leftNodes.add(leftNode);
        }

        for(int i = 0;i<transposed.size();i++)
        {
            Vertex rightNode = new Vertex(rightStart+i);
            rightNodes.add(rightNode);
        }

        for(int i=0;i<incidenceMatrix.size();i++)
        {
            List<Integer> row = incidenceMatrix.get(i);
            for(int j=0;j<row.size();j++)
            {
                if(incidenceMatrix.get(i).get(j) == 1)
                {
                    Vertex left = leftNodes.get(i);
                    Vertex right = rightNodes.get(j);
                    try
                    {
                        Vertex.addEdge(left, right);
                    }
                    catch (RuntimeException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }

        for(int i=0;i<incidenceMatrix.size();i++)
        {
            Vertex leftV = leftNodes.get(i);
            leftNeighbours.add(leftV.getNeighbours());
        }

        for(int i=0;i<transposed.size();i++)
        {
            Vertex rightV = rightNodes.get(i);
            rightNeighbours.add(rightV.getNeighbours());
        }
    }

    void checkInput(List<List<Integer>> incMat)
    {
        int rowSize = incMat.get(0).size();

        for(int i=0;i<incMat.size();i++)
        {
            List<Integer> row = incMat.get(i);
            int size = row.size();
            if(size!=rowSize)
                System.out.println("each row should be of same length");
            for(int j=0;j<size;j++)
            {
                int elem = incMat.get(i).get(j);
                if(!(elem == 1 || elem ==0))
                    System.out.println("should be 1/0");

            }

        }
    }

    List<List<Integer>> transpose(List<List<Integer>> mat)
    {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        int firstListSize = mat.get(0).size();
        for(int i=0;i<firstListSize;i++)
        {
            List<Integer> temp = new ArrayList<>();
            for(List<Integer> row : mat)
                temp.add(row.get(i));

            result.add(temp);
        }
        return result;
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

    public String getNeighbourhoodString(List<List<Vertex>> neighbours)
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
