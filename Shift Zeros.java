package FBQuestions;

/**
 * QUESTION:
 * =========
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * 
 * Example:
 * Input: [0,1,0,3,12]
 * Output: [1,3,12,0,0]
 * 
 * Note: 
 * 1) You must do this in-place without making a copy of the array.
 * 2) Minimize the total number of operations.
 *
 * SOLUTION:
 * =========
 * It seems a simple two pointer problem. The most important thing to as the interviewer is wheather sot sholde be stable or not.
 * 
 * LEETCODE REFERENCE: 283. Move Zeroes
 * ===================
 *
 * https://leetcode.com/problems/move-zeroes/description/
 *
 * QUESTIONS/ASSUMPTIONS:
 * ======================
 * 1) Should the Sort be stable ?
 * Ans: yes / no
 *
 */

public class ShiftZeros {
    public int shiftNonStable(int [] array){
        // This function is not a stable sort and hence it does not maintains
        // the ordering of the non zero elements
        int start=0;
        int end=array.length-1;
        while (start<=end){
            if(array[start]!=0){
                start++;
            } else if(array[end]==0){
                end--;
            } else {
                swap(array,start,end);
                start++;
                end--;
            }
        }
        return start;
    }

    public int shiftStable(int [] array){
        // This function is a stable sort and hence it does maintains
        // the ordering of the non zero elements
        int start=0;
        int idx=0;
        int end=array.length-1;
        while (start<end){
          if(array[start]==0){
              int j=start+1>idx ? start+1 : idx;
              while(j<=end){
                  if(array[j]!=0){
                      swap(array,start,j);
                      idx=j+1;
                      break;
                  } else {
                      j++;
                  }
              }
          } else {
              start++;
          }
        }
        return start;
    }


    public void swap(int [] array,int start, int end){
        int temp=array[start];
        array[start]=array[end];
        array[end]=temp;
    }
}
