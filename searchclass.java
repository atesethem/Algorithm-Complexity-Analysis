public class searchclass {
    public static int linearsearch(int[] array, int key){
        for(int i = 0; i< array.length;i++){
            if(array[i] == key){
                return i;
            }
        }
        return -1;
    }
    public static int binarysearch(int[] array, int key) { // binarysearchi  pseudocode a tam uygun yazmadım herkesinkinden biraz farklı olsun diye
        int leftside = 0;
        int rightside = array.length - 1;
        while (leftside <= rightside) {
            int middle = leftside + (rightside - leftside) / 2;
            if (array[middle] == key) {
                return middle;
            } else if (array[middle] < key) {
                leftside = middle + 1;
            } else {
                rightside = middle - 1;
            }
        }
        return -1;
    }
}
