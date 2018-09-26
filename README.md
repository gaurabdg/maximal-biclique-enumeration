# maximal-biclique-enumeration

## Usage Instructions:

Update: **Extension for finding Minimum Biclique Cover is added.**

###Execution

Run the JAR file with the file containing the adjacency matrix and the type of output required as arguments.

- *standard* - compute all maximal bicliques(MBEA)
- *MBC* - find the minimum nuber of bicliques required to cover all edges(Minimum Biclique Cover problem)

`$ java -jar MBEA.jar path_to/filename.txt  _type_`

###Interpreting the output:

`lNode1 lNode2 ... <-> rNode1 rNode2 ...` is a maximal biclique satisfying the conditions.

- lNode_i_ belongs to the left vertex set
- rNode_i_ belongs to the right vertex set

### To test:

Go to the main directory and execute :

`$ java -jar MBEA.jar matrix.txt MBC`

Output:
```
1 3 <-> 5 7 
2 3 <-> 5 8 
3 4 <-> 6 7 8 
```

## TODO:
- [x] ~~Implement the improved version of MBEA algorithm~~
- [x] ~~Runbenchmarks for the algorithms~~
- [x] ~~Add MBC extension~~
 
