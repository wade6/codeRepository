package com.code.repository.test;

/**
 * 给定一个包含了一些 0 和 1的非空二维数组 grid , 一个 岛屿 是由四个方向 (水平或垂直) 的 1 (代表土地) 构成的组合。你可以假设二维矩阵的四个边缘都被水包围着。
 *
 * 找到给定的二维数组中最大的岛屿面积。(如果没有岛屿，则返回面积为0。)
 *
 * 示例 1:
 *
 * [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *  [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *  [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *  [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *  [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *  [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 对于上面这个给定矩阵应返回 6。注意答案不应该是11，因为岛屿只能包含水平或垂直的四个方向的‘1’。
 *
 * 示例 2:
 *
 * [[0,0,0,0,0,0,0,0]]
 * 对于上面这个给定的矩阵, 返回 0。
 */
public class MaxAreaOfIsland {

    public static void main(String[] args) {
        int[][] grid = new int[][]{{0,0,1,0,0,0,0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                                    {0,1,1,0,1,0,0,0,0,0,0,0,0},
                                    {0,1,0,0,1,1,0,0,1,0,1,0,0},
                                    {0,1,0,0,1,1,0,0,1,1,1,0,0},
                                    {0,0,0,0,0,0,0,0,0,0,1,0,0},
                                    {0,0,0,0,0,0,0,1,1,1,0,0,0},
                                    {0,0,0,0,0,0,0,1,1,0,0,0,0}};
        System.out.println(maxAreaOfIsland(grid));
    }

    public static int maxAreaOfIsland(int[][] grid) {
        int max =0;
        int area = 0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){ // 向四周确认是否都是1
                    area = confirm(grid,i,j);
                    max = max>area ?max:area;
                }
            }
        }
        return max;
    }

    public static int confirm(int[][] grid,int i,int j){
        if(i<0 || i==grid.length){
            return 0;
        }
        if(j<0 || j==grid[0].length){
            return 0;
        }
        if(grid[i][j]==1){
            grid[i][j]=2;
            int area = 1+confirm(grid,i-1,j)+confirm(grid,i+1,j)+confirm(grid,i,j-1)+confirm(grid,i,j+1);
            return area;
        }
        return 0;
    }
}
