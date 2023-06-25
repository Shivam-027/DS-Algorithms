/**Insertion Sort*/

import java.util.*;

public class InsertionS{
    public static void main(String args[]){
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int s = obj.nextInt();
        int arr[] = new int[s];
        System.out.println("Enter the elements: ");
        for(int i=0; i<s; i++){
            arr[i]=obj.nextInt();
        }

        //Insertion sort
        for (int i = 1; i < s; ++i) {
            int t = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > t) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = t;
        }

        //Print
        System.out.println("Array after insertion sort: ");
        for(int i=0; i<s; i++){
            System.out.print(arr[i] + ", ");
        }
    }
}