package Chapter3;

import java.util.ArrayList;
import java.util.Arrays;

public class Tsum {
    public int[] input;

    public Tsum(int[] input){
        this.input = input;
    }

    public void findTriplets(){
        ArrayList<ArrayList<Integer>> triplets = new ArrayList<>();

        Arrays.sort(input); //sort array

        for (int i = 0; i < input.length - 2; i++) {
            // we do this to skip duplicates
            if (i != 0 && input[i] == input[i - 1]) {
                continue;
            }

            // 2 pointers approach
            int left = i + 1;
            int right = input.length - 1;

            while (left < right) {
                int sum = input[i] + input[left] + input[right];

                // if sum equals 0 then we found the triplet
                if (sum == 0) {
                    triplets.add(new ArrayList<>(Arrays.asList(input[i], input[left], input[right])));

                    // Move both pointers and skip duplicates
                    left++;
                    right--;

                    // Skip duplicates for the left pointer
                    while (left < right && input[left] == input[left - 1]) {
                        left++;
                    }

                    // Skip duplicates for the right pointer
                    while (left < right && input[right] == input[right + 1]) {
                        right--;
                    }

                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        for (ArrayList<Integer> triplet : triplets) {
            System.out.println(triplet);
        }
    }

    public static void main(String[] args) {
        int[] input = {-1, 0, 3, 2, -1, -5};

        //[-5,-1,-1,0,3,2] sorted array
        //for i=0 left=-1 right=2 sum=-5 -1 +2 = -4 --> left+1=-1 sum=-4 -->left+1 =0 sum= -5 0 +2=-3 --> left=3 sum=-5+3+2=0 triplet found

        Tsum threeS = new Tsum(input);

        threeS.findTriplets();
    }
}