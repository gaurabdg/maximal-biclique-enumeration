package MBEA;

import java.util.ArrayList;
import java.util.List;

class Vertex{
    private int label;
    private List<Vertex> neighbours  = new ArrayList<>();

    Vertex(){}
    Vertex(int label)
    {
        this.label = label;
    }

    List<Vertex> getNeighbours()
    {
        return neighbours;
    }

    int getLabel()
    {
        return label;
    }

    private void addNeighbour(Vertex v)
    {
        if(neighbours.contains(v))
            throw new RuntimeException("Vertex::add_neighbour - vertex is already a neighbour.");
        neighbours.add(v);
    }

    public void removeNeighbour(Vertex v)
    {
        if(neighbours.contains(v))
            neighbours.remove(v);
//        else
//            throw new RuntimeException("Vertex::remove_neighbour - vertex is not a neighbour.");
    }

    static void addEdge(Vertex v1, Vertex v2)
    {
        try
        {
            v1.addNeighbour(v2);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Vertex::add_edge - " + Integer.toString(v2.label) + " is already a neighbour of " + Integer.toString(v1.label));
        }
        try
        {
            v2.addNeighbour(v1);
        }
        catch (RuntimeException e) {
            throw new RuntimeException("Vertex::add_edge - " + Integer.toString(v1.label) + " is already a neighbour of " + Integer.toString(v2.label));
        }
    }

    void removeEdge(Vertex v1, Vertex v2)
    {
            v1.removeNeighbour(v2);
            v2.removeNeighbour(v1);
    }



    int getNeighboursSize()
    {
        return neighbours.size();
    }

    int numberOfNeighboursOfVInSet(List<Vertex> set)
    {
        int out = 0;
        for (Vertex aSet : set)
        {
            if (isNeighbour(aSet))
            {
                out += 1;
            }
        }
        return out;
    }

    boolean isNeighbour(Vertex otherV)
    {
        return neighbours.contains(otherV);
    }

    boolean isEqual(Vertex otherV)
    {
        return this.label == otherV.label;
    }

    boolean isMember(List<Vertex> set)
    {
        boolean out = false;
        for(Vertex v:set)
        {
            if(this.equals(v))
                out = true;
        }
        return out;
    }

    public int compareTo(Vertex other)
    {
        return this.getNeighboursSize() - other.getNeighboursSize();
    }

}
