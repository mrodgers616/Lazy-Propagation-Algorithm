# Lazy-Propagation-Algorithm
Optimized algorithm to perform Lazy Propagation on AVL Trees.


The board of directors are very impatient. It has been 5 days since the company restarted and the economy has not collapsed into itself. This suggests that the finance department needs to be sacked and has been thrown into the fourth closest sun.

The board has decided to increase the fees for the parks by at least 4 million altairian dollars. Some others think it might be better to increase each park individually and there are some who think it might be better to increase the fees of multiple parks at the same time. The finer points are still under scrutiny whereas the chairman is nowhere to be found after he went to take a stroll in the park.

They have decided that you, having helped implement the database in the first place, would be a good choice as a programmer to do the updates in the system.

There will be three types of queries to the database

insert a planet with name a and entrance fee k into the database
increase the entrance fee for all planets between a, b by k
return the entrance fee for a planet name a from the database
Input Format

The first line contains n the number of queries to be made to the database.

The next  lines contain queries of the following 3 types

1 a k, insert a planet with name a and entrance fee k into the database
2 a b k, increase the entrance fee for all planets between a, b by k
3 a, return the entrance fee for a planet name  from the database
Constraints

n <= 100000 --
|a|, |b| <= 10 --
0 <= k <= 10^5


You have to use the trees as described in class to solve this problem.

Output Format

For each query of type 3 print the entrance fee of the planet in a new line.

If the planet is not in the database, print a "-1", without the quotes.

