package MBEA;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class VertexSet extends Vertex{
    private List<Vertex> setV ;

    VertexSet()
    {
        setV =  new ArrayList<>();
    }

    VertexSet(List<Vertex> nodesIn)
    {
        List<Vertex> newlist = new ArrayList<>();
        newlist.addAll(nodesIn);
        setV = newlist;
    }

    List<Vertex> getSetV()
    {
        return setV;
    }
    int getSize()
    {
        return setV.size();
    }
    Vertex getVertex(int i)
    {
        return setV.get(i);
    }

    void addVertex(Vertex v)
    {
        if(!setV.contains(v))
            setV.add(v);
    }

    void removeVertex(Vertex v)
    {
//        if(setV.contains(v))
            setV.remove(v);
    }

    List<Vertex> sortByNumOfNeighbours()
    {
        Collections.sort(setV, new Comparator<Vertex>() {
            @Override
            public int compare(Vertex o1, Vertex o2) {
                return o1.getNeighboursSize() - o2.getNeighboursSize();
            }
        });

        return setV;
    }

    boolean isEqual(VertexSet other)
    {
        return setV.equals(other.setV);
    }

    boolean isSetEmpty()
    {
        return setV.isEmpty();
    }

    String toStringVertexSet()
    {
        String res = "";
        for (Vertex v:setV)
            res += Integer.toString(v.getLabel())+ " ";
        return res;
    }
}
