//Strassen’s matrix 

import java.util.*;

public class Strassenmartix {
    public static void main(String[] args) {
        Strassenmartix sx = new Strassenmartix();
        Scanner obj = new Scanner(System.in);
        System.out.print("Enter the Size of the matrix: ");
        int n = obj.nextInt();
        int A[][] = new int[n][n];
        int B[][] = new int[n][n];
        int C[][] = new int[n][n];

        System.out.println("Enter the elements in the 1st matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = obj.nextInt();
                A[i][j] = a;
            }
        }

        // print 1st matrix
        System.out.println("1st Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");

        System.out.println("Enter the elements in the 2nd matrix: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int b = obj.nextInt();
                B[i][j] = b;
            }
        }

        // print 2nd matrix
        System.out.println("2nd Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(B[i][j] + " ");
            }
            System.out.print("\n");
        }

        // start
        int[][] A11 = new int[n / 2][n / 2];
        int[][] A12 = new int[n / 2][n / 2];
        int[][] A21 = new int[n / 2][n / 2];
        int[][] A22 = new int[n / 2][n / 2];
        int[][] B11 = new int[n / 2][n / 2];
        int[][] B12 = new int[n / 2][n / 2];
        int[][] B21 = new int[n / 2][n / 2];
        int[][] B22 = new int[n / 2][n / 2];

        // Divide matrix A
        A11 = sx.Divide(A, A11, 0, 0);
        A12 = sx.Divide(A, A12, 0, n / 2);
        A21 = sx.Divide(A, A21, n / 2, 0);
        A22 = sx.Divide(A, A22, n / 2, n / 2);

        // Divide matrix B
        B11 = sx.Divide(B, B11, 0, 0);
        B12 = sx.Divide(B, B12, 0, n / 2);
        B21 = sx.Divide(B, B21, n / 2, 0);
        B22 = sx.Divide(B, B22, n / 2, n / 2);

        //
        // M1:=(A1+A3)×(B1+B2)
        int[][] M1 = sx.multiply(sx.add(A11, A22), sx.add(B11, B22));

        // M2:=(A2+A4)×(B3+B4)
        int[][] M2 = sx.multiply(sx.add(A21, A22), B11);

        // M3:=(A1−A4)×(B1+A4)
        int[][] M3 = sx.multiply(A11, sx.sub(B12, B22));

        // M4:=A1×(B2−B4)
        int[][] M4 = sx.multiply(A22, sx.sub(B21, B11));
        // M5:=(A3+A4)×(B1)
        int[][] M5 = sx.multiply(sx.add(A11, A12), B22);

        // M6:=(A1+A2)×(B4)
        int[][] M6 = sx.multiply(sx.sub(A21, A11), sx.add(B11, B12));

        // M7:=A4×(B3−B1)
        int[][] M7 = sx.multiply(sx.sub(A12, A22), sx.add(B21, B22));

        // P:=M2+M3−M6−M7
        int[][] C11 = sx.add(sx.sub(sx.add(M1, M4), M5), M7);

        // Q:=M4+M6
        int[][] C12 = sx.add(M3, M5);

        // R:=M5+M7
        int[][] C21 = sx.add(M2, M4);

        // S:=M1−M3−M4−M5
        int[][] C22 = sx.add(sx.sub(sx.add(M1, M3), M2), M6);


        //
        sx.join(C11, C, 0, 0);
        sx.join(C12, C, 0, n / 2);
        sx.join(C21, C, n / 2, 0);
        sx.join(C22, C, n / 2, n / 2);

        //final result
        System.out.println("\n");
        System.out.println("Final Matrix");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.print("\n");
        }


    }

    public int[][] Divide(int[][] P, int[][] Q, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < Q.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < Q.length; j1++, j2++)
                Q[i1][j1] = P[i2][j2];

        return Q;
    }

    public int[][] sub(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                C[i][j] = A[i][j] - B[i][j];
        return C;
    }

    public int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    public void join(int[][] C, int[][] P, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
            for (int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
                P[i2][j2] = C[i1][j1];
    }

    public int[][] multiply(int[][]A, int[][]B){
        int c[][]=new int[A.length][A.length];
        for(int i=0;i<A.length;i++){    
            for(int j=0;j<A.length;j++){    
                c[i][j]=0;      
                for(int k=0;k<A.length;k++){      
                    c[i][j]+=A[i][k]*B[k][j];      
                } 
            }
        }
        return c;
    }
}
