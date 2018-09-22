package FBQuestions;

import java.util.Stack;

/**
 * QUESTION:
 * =========
 * Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
 * Note: The input string may contain letters other than the parentheses ( and ).
 *
 * Example 1:
 * Input: "()())()"
 * Output: ["()()()", "(())()"]
 *
 * Example 2:
 * Input: "(a)())()"
 * Output: ["(a)()()", "(a())()"]
 *
 * Example 3:
 * Input: ")("
 * Output: [""]
 *
 * SOLUTION:
 * =========
 * It seems a simple stack based problem. but the key is that this problem can be solved with out stack in a more efficient manner.
 *
 * LEETCODE REFERENCE: 301. Remove Invalid Parentheses  32. Longest Valid Parentheses
 * ===================
 *
 * https://leetcode.com/problems/remove-invalid-parentheses/description/
 * https://leetcode.com/problems/longest-valid-parentheses/description/
 *
 * QUESTIONS/ASSUMPTIONS:
 * ======================
 *
 *
 */

public class BalanceParentheses {

    // Stack Based Approach
    // TC: O(N)
    // SC: O(N)
    public String getBalancedParentheses1(String str){
        Stack<Integer> stack = new Stack<>();
        char [] array=str.toCharArray();
        for(int i=0;i<array.length;i++){
            if(array[i]=='('){
                stack.push(i);
            }else if(array[i]==')'){
                if(!stack.isEmpty())
                    stack.pop();
                else array[i]='#';
            }
        }

        while (!stack.isEmpty())
            array[stack.pop()]='#';
        return new String(array).replaceAll("#","");
    }


    // Back and Forth Approach (2 - Pass)
    // TC: O(N)
    // SC: O(1) if the output string is not counted
    public String getBalancedParentheses2(String str){
        int forwardCounter=0;
        char [] array=str.toCharArray();
        for(int i=0;i<array.length;i++){
            if(array[i]=='('){
                forwardCounter++;
            }else if(array[i]==')'){
                if(forwardCounter>0)
                    forwardCounter--;
                else array[i]='#';
            }
        }

        int backwardCounter=0;
        for(int i=array.length-1;i>=0;i--){
            if(array[i]==')'){
                backwardCounter++;
            }else if(array[i]=='('){
                if(backwardCounter>0)
                    backwardCounter--;
                else array[i]='#';
            }
        }

        return new String(array).replaceAll("#","");
    }


    // Stack Based Approach
    // TC: O(N)
    // SC: O(1) if the output string is not counted
    public String getBalancedParentheses3(String str){
        int forwardCounter=0;
        char [] array=str.toCharArray();
        for(int i=0;i<array.length;i++){
            if(array[i]=='('){
                forwardCounter++;
            }else if(array[i]==')'){
                if(forwardCounter>0)
                    forwardCounter--;
                else array[i]='#';
            }
        }


        for(int i=array.length-1;i>=0;i--){
            if(forwardCounter==0)
                break;
            if(array[i]==')'){
                array[i]='#';
                forwardCounter--;
            }
        }

        int slow=0;
        for(int fast=0; fast<array.length;fast++){
            if(array[fast]!='#'){
                array[slow++]=array[fast];
            }
        }
        StringBuilder sb = new StringBuilder(new String(array));
        sb.trimToSize();
        return sb.toString();
    }


    public static void main(String args []){
        String str ="(131)(223)e3we)()";
        BalanceParentheses obj = new BalanceParentheses();
        //System.out.println(obj.getBalancedParentheses1(str));
        System.out.println(obj.getBalancedParentheses2(str));
    }
}
