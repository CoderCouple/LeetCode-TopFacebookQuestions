package FBQuestions;

/**
 * QUESTION:
 * =========
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.
 * Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).
 * You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n), your function should minimize the number of calls to knows.
 *
 * Note: There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.
 *
 * SOLUTION:
 * =========
 * It is a directed graph problem.
 * The answers to the problem is the node which does not have any outgoing links and had incoming links from all nodes in the graph.
 *
 * If(A -> B)
 * then A CAN NOT be celebrity but B can be.
 *
 * if(Celebrity ==k)
 * then for i<k  i should know k and k should not know any i
 *      for i>k i should know k and k should not know any i
 *
 * The first loop is to find the candidate as the author explains. In detail, suppose the candidate after the first for loop is person k,
 * it means 0 to k-1 cannot be the celebrity, because they know a previous or current candidate. Also,
 * since k knows no one between k+1 and n-1, k+1 to n-1 can not be the celebrity either. Therefore,
 * k is the only possible celebrity, if there exists one.
 *
 *
 * LEETCODE REFERENCE: 277. Find the Celebrity
 * ===================
 * https://leetcode.com/problems/find-the-celebrity/description/
 *
 * QUESTIONS/ASSUMPTIONS:
 * ======================
 * 1)Will there be always a celebrity present ?
 * Ans: may/may not be
 *
 */

public class CelebritySearch {

        //one pointer approach
        public int findCelebrity_I(int n) {
            int celebrity=0;
            for(int individual=1; individual<n;individual++){
               if(knows(celebrity,individual))
                   celebrity=individual;
            }

            for(int individual=0; individual<n;individual++){
                if(individual<celebrity && (!knows(individual,celebrity) || knows(celebrity,individual)))
                    return -1;

                if(individual>celebrity && !knows(individual,celebrity))
                    return -1;
            }

            return celebrity;
        }


    //Two pointer approach
    public int findCelebrity(int n) {
        int start=0;
        int end=n-1;
        while(start!=end){
                if(knows(start,end))
                    start++;
                else end--;

        }
        int celebrity=start;
        for(int individual=0; individual<n;individual++){
            if(individual<celebrity && (!knows(individual,celebrity) || knows(celebrity,individual)))
                return -1;

            if(individual>celebrity && !knows(individual,celebrity))
                return -1;
        }

        return celebrity;
    }


        public boolean knows(int a , int b){
            return true;
        }
}
