package com.liucc.recursion;

public class MiGong {
    public static void main(String[] args) {
        int[][] map = new int[8][7];
        // 1表示墙体
        // 上下墙体
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右墙体
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        // 设置特殊墙体
        map[1][4] = 1;
        map[2][4] = 1;
        // 打印地图
        System.out.println("===初始地图===");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        findWay(map, 1, 1);
        System.out.println("===小球移动路线===");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

    }

    /**
     * 1、小球初始位置（1，1），到达（6，5）说明小球找到通路
     * 2、约定1表示墙体，2表示通路已经走过，0表示为走过，3表示走过但走不通
     * 3、小球移动策略：上->右->下->左
     *
     * @param map 初始地图
     * @param i   小球初始位置横坐标
     * @param j   小球初始位置纵坐标
     * @return
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) { // 说明小球找到通路
            return true;
        } else {
            if (map[i][j] == 0) {// 说明这条路还没走过
                // 假设向下移动的点是通路
                map[i][j] = 2;
                if (findWay(map, i - 1, j)) { // 向上
                    return true;
                } else if (findWay(map, i, j + 1)) { // 向右
                    return true;
                } else if (findWay(map, i + 1, j)) {// 向下
                    return true;
                } else if (findWay(map, i, j - 1)) {// 向左
                    return true;
                } else {
                    map[i][j] = 3;  // 死路，走不通
                    return false;
                }
            } else {// 1墙体，2已走过，3思路
                return false;
            }

        }
    }
}
