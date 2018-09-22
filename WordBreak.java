package FBQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * QUESTION:
 * =========
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Example 1:
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 *
 * Example 2:
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 * Note that you are allowed to reuse a dictionary word.
 *
 * Example 3:
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * SOLUTION:
 * =========
 * It is a DP problem.
 * The main logic is to decide weather to insert the space between the characters or not.
 * For example: Input: s = "leetcode", wordDict = ["leet", "code"]
 * You can insert space after every character and run it recursively.
 * But thinking for a minutes will help you realize that it does not make sense to insert the space after every character.
 * Rather insertering the space only after the valid word makes sense. Keeping that in mind we can write a recursive program to do so.
 *
 * LEETCODE REFERENCE: 139. Word Break + 140. Word Break II
 * ===================
 * https://leetcode.com/problems/word-break/description/
 * https://leetcode.com/problems/word-break-ii/description/
 *
 * QUESTIONS/ASSUMPTIONS:
 * ======================
 *
 *
 */

public class WordBreak {

    public boolean wordBreakWithOutDP(String s, List<String> wordDict) {
        return isValidWithOutDP(s,wordDict,0);
    }

    public boolean isValidWithOutDP(String s, List<String> wordDict, int startIdx){
        if(startIdx==s.length())
            return true;

        for(int i=startIdx; i<=s.length();i++){
            if(wordDict.contains(s.substring(startIdx,i))){
                if(isValidWithOutDP(s,wordDict,i))
                    return true;
            }
        }
        return false;
    }



    public boolean wordBreakWithDP(String s, List<String> wordDict) {
        int []  cache = new int[s.length()+1];
        Arrays.fill(cache,0);
        return isValidWithDP(s,wordDict,0,cache);
    }


    public boolean isValidWithDP(String s, List<String> wordDict, int startIdx, int [] cache){
        if(startIdx==s.length())
            return true;

        if(cache[startIdx]==-1)
            return false;
        if(cache[startIdx]==1)
            return true;

        for(int i=startIdx; i<=s.length();i++){
            if(wordDict.contains(s.substring(startIdx,i))){
                if(isValidWithDP(s,wordDict,i,cache)){
                    cache[startIdx]=1;
                    return true;
                } else{
                    cache[startIdx]=-1;
                }
            }
        }
        return false;
    }


    public List<String> wordBreak(String s, List<String> wordDict) {
        StringBuilder sb = new StringBuilder("");
        List<String> result = new ArrayList<String>();
        isSentenceBreakableWithOutDP(s,wordDict,0,sb,result);
        return result;
    }

    public boolean isSentenceBreakableWithOutDP(String s, List<String> wordDict, int startIdx, StringBuilder sentence,List<String> result){
        if(startIdx==s.length())
            return true;

        for(int i=startIdx; i<=s.length();i++){
            if(wordDict.contains(s.substring(startIdx,i))){
                sentence.append(" "+s.substring(startIdx,i));
                if(isSentenceBreakableWithOutDP(s,wordDict,i,sentence,result))
                    result.add(sentence.toString().trim());
                sentence.delete(sentence.length()-s.substring(startIdx,i).length()-1,sentence.length());
            }
        }
        return false;
    }


}
