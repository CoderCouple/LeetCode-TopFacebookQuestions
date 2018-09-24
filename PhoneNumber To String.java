package FBQuestions;

import java.util.*;


/**
 * QUESTION:
 * =========
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 *
 * Note: Although the above answer is in lexicographical order, your answer could be in any order you want.
 *
 * SOLUTION:
 * =========
 * It seems a simple recursive problem.
 * but the key is that can you solve this problem iteratively.
 * Since string is immutable datastructure in java, how to optimize string operations.
 *
 * LEETCODE REFERENCE: 17. Letter Combinations of a Phone Number
 * ===================
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * QUESTIONS/ASSUMPTIONS:
 * ======================
 * 
 * Follow Up Questions:
 * ====================
 * 1) Total No of solution ?
 * Ans: M * N 
 * [M * N =  Number of Alternative for each digit Digits , M= Number of Digits, N= No Of characters for Digit]
 *
 * 2) Will each solution be unique ?
 * Ans: Yes
 *
 * 3) How will you design an automated checking for it ?
 * Ans: Each letter in the answer should map back to the digits in the given input string.(Reverse mapping)
 */
public class PhoneNumberToString {

        //TC : O(M * N) [M * N =  Number of Alternative for each digit Digits , M= Number of Digits, N= No Of characters for Digit]
        //SC : O(M) [M:  Number of Digits = Maximum depth of tree]
        public List<String> letterCombinations(String digits, Map<Character,String> map) {

            List<String> result = new ArrayList<>();
            if(digits.length()==0)
                return result;

            getCombinations(digits,0,new StringBuilder(""),result,map);
            return result;
        }

        public void getCombinations(String digits, int index, StringBuilder currString,List<String> result,Map<Character,String> map){
            if(index==digits.length()){
                result.add(currString.toString().trim());
                return;
            }

            char digit =digits.charAt(index);
            String letters=map.get(digit);
            for(int i=0; i<letters.length();i++){
                currString.append(letters.charAt(i)+"");
                getCombinations(digits,index+1,currString,result,map);
                currString.setLength(currString.length() - 1);
            }
        }

    //TC : O(M * N) [M * N =  Number of Alternative for each digit Digits , M= Number of Digits, N= No Of characters for Digit]
    //TC : O(M * N) [M * N =  Number of Alternative for each digit Digits , M= Number of Digits, N= No Of characters for Digit]
    public List<String> letterCombinationsIterative(String digits,Map<Character,String> map) {

        Queue<String> queue = new LinkedList<String>();
        if(digits.length()==0)
            return new ArrayList<String>(queue);


        queue.offer("");
        for(int i=0; i<digits.length(); i++){
            String letters = map.get(digits.charAt(i));
            int size= queue.size();
            for(int k=0;k<size;k++){
                String s=queue.poll();
                for(int j=0; j<letters.length();j++){
                    char ch = letters.charAt(j);
                    queue.offer(s+ch);

                }
            }
        }
        return new ArrayList<String>(queue);
    }


    public static void main(String args []){

        PhoneNumberToString obj = new PhoneNumberToString();
        Map<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        String s = "245";

        List<String> m1 = obj.letterCombinations(s,map);
        System.out.println(m1.size());
        for(String str : m1)
            System.out.println(str);


        System.out.println("===================");

        List<String> m2 = obj.letterCombinationsIterative(s,map);
        System.out.println(m2.size());
        for(String str : m2)
            System.out.println(str);
    }

}
