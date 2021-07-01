package com.CtCI.Chapter1;

// Problem: Given an image represented by an N x N matrix, where each pixel in the image is represented by an integer,
// write a method to rotate the image by 90 degrees. Can you do this in place?

public class RotateMatrix {
    public static void main(String[] args) {

        int[] row1 = {1, 2, 3, 4, 5};
        int[] row2 = {6, 7, 8, 9, 10};
        int[] row3 = {11, 12, 13, 14, 15};
        int[] row4 = {16, 17, 18, 19, 20};
        int[] row5 = {21, 22, 23, 24, 25};
        int[][] image = {row1, row2, row3, row4, row5};

        printMatrix(image);

        System.out.println("==========================");

        int[][] rotatedMatrix = RotateMatrix(image);
        printMatrix(rotatedMatrix);

        System.out.println("==========================");

        int[][] rotatedInPlaceMatrix = RotateMatrixInPlace(image);
        printMatrix(rotatedInPlaceMatrix);
    }

    public static void printMatrix(int[][] matrix) {
        for(int[] row : matrix) {
            for(int pixelVal : row) {
                System.out.print(String.format("%5d", pixelVal));
            }
            System.out.print(System.lineSeparator());
        }
    }

    public static int[][] RotateMatrix(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][matrix.length];

        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix.length; j++) {
                newMatrix[j][(matrix.length - 1) - i] = matrix[i][j];
            }
        }

        return newMatrix;
    }

    public static int[][] RotateMatrixInPlace(int[][] matrix) {
        int matrixWidth = matrix.length - 1;
        int tempMem;

        for(int shell = 0; shell < matrix.length / 2; shell++) {
            for(int i = shell; i < (matrix.length - (shell + 1)); i++) {
                tempMem = matrix[shell][i];
                matrix[shell][i] = matrix[matrixWidth - i][shell];
                matrix[matrixWidth - i][shell] = matrix[matrixWidth - shell][matrixWidth - i];
                matrix[matrixWidth - shell][matrixWidth - i] = matrix[i][matrixWidth - shell];
                matrix[i][matrixWidth - shell] = tempMem;
            }
        }

        return matrix;
    }
}
