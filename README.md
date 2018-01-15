# Paint shop problem: 


The problem is solved using a tree where each branch to a leaf node is a combination of colors to satisfy all customers.
A full explanation of the solution is below.

1. How to build the the tree
2. How to use the tree to find the best solution
3. optimizations


## How to build the the tree: 
```
Start with a root node
For each customer:
    for each leaf node:
    create a node for each preference of the current customer and attach to leaf
```

### Example
Given the input:
```
5  == number of paints
3  == number of customers
1 1 1
2 1 0 2 0
1 5 0 3 1  
```           
        
1. Start with a root node         
                                                           
                                                           root


2. for each leaf node, create a node for each preference of the current customer and attach to leaf                                                           
Take customer 1 (1 1 1) 
Customer 1 only has a single preference i.e. (Color(1), MATTE)
Attach it to all leaves, at the moment there is only a single leaf (root)

                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))                                                        
                                                           
3. for each leaf node, create a node for each preference of the current customer and attach to leaf   
Customer 2 has 2 preferences i.e. (Color(1), GLOSS) and (Color(2), GLOSS)
Attach it to all leaves, at the moment there is only a single leaf (Paint(Color(1),Finish(MATTE)))

                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
                        Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS)))        

4. for each leaf node, create a node for each preference of the current customer and attach to leaf    
Take customer 3 (1 5 0 3 1)        

Result                                                    
                                                           
                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
                        Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS)))        
                         /                   \                                   /                               \
                        /                     \                                 /                                 \
    Paint(Color(5),Finish(GLOSS))  Paint(Color(3),Finish(MATTE))               Paint(Color(5),Finish(GLOSS))  Paint(Color(3),Finish(MATTE))  


## How to use the tree to find the best solution
1. Trace each path to a leaf.
2. For each leaf we determine if the solution is valid. A path is determined to be invalid if it needs the same color to be in both matte and gloss. 
3. If it is valid we check how many matte paints are required in the branch.
4. We choose the branch with the least matte paints required.

Using the example above:


1. Trace each path to a leaf.
                                                           
                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
                        Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS)))       
                         /                   \                                   /                               \
         Paint(Color(5),Finish(GLOSS))  Paint(Color(3),Finish(MATTE))               Paint(Color(5),Finish(GLOSS))  Paint(Color(3),Finish(MATTE))  
        /                                          \                                                       |                            |
        Impossible(Color 1 can't be matte and gloss) Impossible(Color 1 can't be matte and gloss)      GoodSolution(MATTE_COST=1)           GoodSolution(MATTE_COST=2)


2. 2 paths are invalid these can be ignored 
3. Of the other 2 paths one has a matte cost of 1 and the other has a matte cost of 2.
4. So the best solution to this problem is Paint(Color(1),Finish(MATTE)),Paint(Color(2),Finish(GLOSS)),Paint(Color(5),Finish(GLOSS))
For each colour which a client doesn't care about we default to GLOSS.

So the full answer is 
```
Paint(Color(1),Finish(MATTE))
Paint(Color(2),Finish(GLOSS))
Paint(Color(3),Finish(GLOSS))
Paint(Color(4),Finish(GLOSS))
Paint(Color(5),Finish(GLOSS))
```

## Optimizations

### Optimization 1: Build the tree and search at the same time
Instead of the build and search taking place in 2 separate operations they both take place at the same time

### Optimization 2: Each time we are adding a node to the tree we check if the current path is possible if it is not possible then we no longer add nodes to that branch.

From the example above in "How to build the the tree: "

step 4 actually looks like: 

create a node for each preference of the current customer and attach to all current leaves  
Take customer 3 (1 5 0 3 1)        

                                                 
                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
            (IMPOSSIBLE) Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS)))        
                                                                                 /                               \
                        No nodes will be added here                             /                                 \
                                                                               Paint(Color(5),Finish(GLOSS))  Paint(Color(3),Finish(MATTE))  



### Optimization 3: keep track of the matte cost of each path and the current solution with the lowest matte score, don't create branches if they are more than or equal to the current best solution's matte score

From the example above in "How to build the the tree: "

step 4 actually looks like:                                                 
                                
at node = Paint(Color(2),Finish(GLOSS))), create the left branch (the branch ending in Paint(Color(5),Finish(GLOSS))).
This branch is a SuccessfulCombination with a matte cost of 1.
The other path is not built because any SuccessfulCombination down that path will have either the same or a greater matte cost than the current solution. 
  
                                
                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
            (IMPOSSIBLE) Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS))) (MATTE_COST=1)       
                                                                                 /                         
                        No nodes will be added here                             /                                   Do not build nodes      
                                                                               Paint(Color(5),Finish(GLOSS))  
                                                                                    |          
                                                                                    |
                                                                               SuccessfulCombination(MATTE_COST=1)
                                                                               
                                                                               
                                                                               
### Optimization 4: check if a customer is satisfied by the current branch before adding nodes for them.
Each time we add a node to a branch
1. we increase the chance of this branch being impossible (e.g the node we are adding might be a colour already specified in the path but with a different finish )
2. we might increase the matte cost of a possible branch

Basically adding nodes is bad.
Ideally we want to satisfy a customer preferences without adding adding a node to the tree

Given the input:
```
5  == number of paints
4  == number of customers
1 1 1
2 1 0 2 0
2 5 0 3 1  
2 2 0 4 0  
```          
                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
            (IMPOSSIBLE) Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS))) (MATTE_COST=1)       
                                                                                 /                         
                        No nodes will be added here                             /                                   Do not build nodes      
                                                                               Paint(Color(5),Finish(GLOSS))  
                                                                               

When satisfying customer 4 (2 2 0 4 0).
We notice that path already satisfies customer 4 because Paint(Color(2),Finish(GLOSS))) is in the path already. 
In this case we don't add the customer to the tree.

### Optimization 5: traverse non matte branches first
If a branch with a low Matte cost score is found early in the processing, then we will not process branches with high matte scores (Optimization 3).
To leverage on this, when a user has preferences for both matte and glossy paints and we can't use Optimization 4, then we traverse the glossy path first.



### Optimization 6: sort the customers based on number of preferences before processing

Why this works.
Properties of user with a lot of preferences: 
1. Very expensive to add to a tree, because a branch needs to be made for each preference. 
2. likely hood of them being satisfied by the existing path increases as their number of preferences increases
3. likely hood of them being satisfied by the existing path increases as the number of paints in the path increase

based on these properties it makes sense to add users with a lot of preferences at the end when they are likely to be satisfied by the existing paths.
If they are satisfied by the existing path we can use Optimization 4.


Example : 
example path == Paint(Colour(1),MATTE),Paint(Colour(3),GLOSSY)
The more preferences that a customer has then the more likely it is that they will have a preference for Paint(Colour(1),MATTE) or Paint(Colour(3),GLOSSY)
 

Given the input:
```
5  == number of paints
4  == number of customers
4 1 0 2 0 3 0 4 0
2 5 0 3 1 
1 1 1 
2 2 0 4 0  
```    

It is obvious that we need to have Paint(Color(1),Finish(MATTE)) in the solution, to satisfy customer 3. 
However if we start building the tree we will build 3 levels before we realize the paths are invalid.


                                root
                             /               ...       
                            /                  ...        
            Paint(Color(1),GLOSSY)               ...
             /                   \                  ...
            /                     \                   ...
        Paint(Color(5),GLOSSY)     Paint(Color(3),MATTE) ...
         /                           \                                                  
        /                             \                                 
    Paint(Color(1),MATTE)           Paint(Color(1),MATTE)
  
    
However if we sort the customers first based on number of preferences
We will not build those invalid paths. 

After sorting
```
1 1 1 
2 2 0 4 0
2 5 0 3 1 
4 1 0 2 0 3 0 4 0
```

all the paths you build will contain the required Paint(Color(1),Finish(MATTE)).

### Optimization 7: when tree is large number of nodes per path the paint selection is cached at some nodes
For optimization 4 we need to be able check if a customer is satisfied by the current branch before adding them.
For Optimization 2 we need to be able to check if a branch is impossible.

For both these operations we need to look at every value in the path.
This cost is directly proportional to the number of paints in the path. 

As an example:

Given the tree below and and customer(preferences = Paint(Color3,MATTE))

                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
          (IMPOSSIBLE) Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS)))        
                                                                               /                              \
                                                                              /                                (too high matte cost)
                                                              Paint(Color(5),Finish(GLOSS))   

To add the customer we are going to have to trace all nodes in a path to see if Paint(Color3,MATTE) is in the branch. 
In this example there 3 nodes need to be checked before we can determine weather of not to add a node. 
In this example the cost is small enough to check all nodes in a branch. 

However when the branch is 100's of nodes deep this in unacceptable. 

To overcome this there is a special caching node, a caching node will store a list of colors and finishes required at that point in the path. 
So queries can be serviced in absolute time. 

With a caching node the above 3 would look like: 


                                                           root
                                                            |
                                                    Paint(Color(1),Finish(MATTE))
                                                    /                            \
      (IMPOSSIBLE)     Paint(Color(1),Finish(GLOSS)))                            Paint(Color(2),Finish(GLOSS)))        
                                                                                 /                               
                                                                                /                               
                                                                           Paint(Color(5),Finish(GLOSS))  
                                                                           Cache: 
                                                                           Color(1) -> MATTE
                                                                           Color(2) -> GLOSS 
                                                                           Color(5) -> GLOSS  



### Optimization 8: run children nodes in parallel (exists in the branch feature/multithreaded, didn't get the time to merge into master) 
Exactly what it says, if there are 4 or more children nodes then split the children onto separate threads.
