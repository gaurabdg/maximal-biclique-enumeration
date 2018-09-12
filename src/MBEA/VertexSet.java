package MBEA;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;

public class VertexSet extends Vertex{
    private List<Vertex> setV ;

    VertexSet(){
        setV =  new ArrayList<>();
    }
    VertexSet(List<Vertex> nodesIn)
    {
        setV = nodesIn;
    }

    List<Vertex> getSetV()
    {
        return setV;
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

    boolean isEqual(VertexSet other)
    {
        return setV.equals(other.setV);
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

    Vertex getVertex(int i)
    {
        return setV.get(i);
    }

    int getSize()
    {
        return setV.size();
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
