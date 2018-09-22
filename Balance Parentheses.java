package FBQuestions;

import java.util.Arrays;
import java.util.Stack;

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
