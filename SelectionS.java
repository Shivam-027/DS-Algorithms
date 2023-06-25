
/**Selection Sort*/

import java.util.*;

public class SelectionS {
    public static void main(String args[]) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Enter the size of the array: ");
        int s = obj.nextInt();
        int arr[] = new int[s];
        System.out.println("Enter the elements: ");
        for (int i = 0; i < s; i++) {
            arr[i] = obj.nextInt();
        }

        // Selection sort
        for (int i = 0; i < s - 1; i++) {
            int min = i;
            for (int j = i + 1; j < s; j++) {
                if (arr[j] < arr[min])
                    min = j;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }

        // Print
        System.out.println("Arrays after selection sort: ");
        for (int i = 0; i < s; i++) {
            System.out.print(arr[i] + ", ");
        }
    }
}