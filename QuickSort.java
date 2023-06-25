//Quick Sort

import java.util.*;

class QuickSort {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int n = obj.nextInt();
        int arr[] = new int[n];
        System.out.println("Enter the elements: ");
        for(int i=0; i<n; i++){
            arr[i]=obj.nextInt();
        }

        System.out.println("Before Quick Sort");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int low = 0;
        int high = n - 1;
        quickSort(arr,low,high);
        
        System.out.println("Array After Quick Sort");
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int p = arr[high];
            int i = (low - 1);
            for (int j = low; j <= high - 1; j++) {
                if (arr[j] < p) {
                    i++;
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
            int temp = arr[i + 1];
            arr[i + 1] = arr[high];
            arr[high] = temp;
            int pi = i + 1;
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
}
