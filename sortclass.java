import java.util.Arrays;

public class sortclass {
    public static void insertionsort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int current = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > current) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = current;
        }
    }
    public static void mergesort(int[] array){
        if(array.length < 2){ // base case durumu
            return;
        }
        int array_length = array.length;
        int middleofarray = (int) array.length/2;
        int[] leftarray = new int[middleofarray];
        int[] rightarray = new int[array_length - middleofarray];
        for(int i = 0; i < array.length; i++){
            if(i<middleofarray){
                leftarray[i] = array[i];
            }
            else{
                rightarray[i-middleofarray] = array[i];
            }
        }
        mergesort(leftarray);
        mergesort(rightarray);
        merge(leftarray, rightarray,array);
    }
    public static void merge(int[] left, int[] right, int[] array){
        int minimum_number = Math.min(right.length, left.length);
        int arrayindex = 0;
        int i = 0;
        for(; i< minimum_number; i++){
            if(left[i]>right[i]){
                array[arrayindex] = right[i];
                arrayindex++;
            }
            if(left[i]<right[i]){
                array[arrayindex] = left[i];
                arrayindex++;
            }
        }
        while(i < right.length) {
            array[arrayindex] = right[i];
            i++;
            arrayindex++;
        }
        while(i < left.length){
            array[arrayindex] = left[i];
            i++;
            arrayindex++;
        }

    }
    public static int[] countingSort(int[] input, int k) {
        int[] count = new int[k + 1];
        int[] output = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            count[input[i]]++;}
        for (int i = 1; i <= k; i++) {
            count[i] += count[i - 1];
        }
        for (int i = input.length - 1; i >= 0; i--) {
            output[count[input[i]] - 1] = input[i];
            count[input[i]]--;
        }

        return output;
    }
}
