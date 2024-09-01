package com.liucc.sparse;

/**
 * 稀疏数组
 */
public class SparseArray {
    public static void main(String[] args) {
        // 定义二维数组 11 * 11
        // 1表示黑子，2表示白子
        int[][] twoDArray = new int[11][11];
        twoDArray[1][2] = 1;
        twoDArray[2][3] = 2;
        twoDArray[4][5] = 2;
        System.out.println("原始数组:");
        for (int[] row : twoDArray) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }

        /**
         * 转为稀疏数组：
         * 遍历二维数组，获取其有效数据的个数 count
         * 创建稀疏数组，大小为[count+1][3] row, col, value
         * 遍历二维数组，将有效数据存入稀疏数组
         * 打印稀疏数组
         */
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (twoDArray[i][j] != 0) {
                    count++;
                }
            }
        }
        int sparseArr[][] = new int[count + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = count;

        int index = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (twoDArray[i][j] != 0) {
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = twoDArray[i][j];
                }
            }
        }
        System.out.println("稀疏数组为：");
        for (int[] row : sparseArr) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }

        /**
         * 稀疏数组转为二维数组：
         * 根据稀疏数组第一行数据，创建二维数组 int[row][col]
         * 根据后续行数的数据，给二维数组进行赋值
         * 打印二维数组
         */
        int row = sparseArr[0][0];
        int col = sparseArr[0][1];
        int twoDArray2[][] = new int[row][col];
        // 从二行开始遍历
        for (int i = 1; i <= count; i++) {
            int r1 = sparseArr[i][0];
            int c1 = sparseArr[i][1];
            int v1 = sparseArr[i][2];
            // 赋值
            twoDArray2[r1][c1] = v1;
        }
        System.out.println("还原后的二维数组：");
        for (int[] array : twoDArray2) {
            for (int num : array) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }
}
