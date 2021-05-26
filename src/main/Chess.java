package main;

import java.util.Stack;

/**
 * @author GodofOrange
 *    棋盘数据结构
 */
public class Chess {
    //棋盘大小
    private int[][] arr;
    private int x;
    private int y;
    //记录下棋的类
    class ChessPiece{
        int x;
        int y;
        public ChessPiece(int x,int y) {
            this.x=x;
            this.y=y;
        }
    }
    //栈
    Stack<Chess> stack;
    //构造方法
    public Chess(int x, int y) {
        stack = new Stack<>();
        arr = new int[x][y];
        this.x=x;
        this.y=y;
    }
    //检查该地是否有空位置
    private boolean isEmpty(int x,int y) {
        if(arr[x][y]==0) return true;
        else return false;
    }
    //判断输赢
    private int checkVictory(int x,int y,int var) {
        //横向判断
        int a = 0;
        for(int i=x-4;i<x+5;i++) {
            if(i<0||i>=this.x) continue;
            if(arr[i][y]==var) {
                a++;
            }
            else {
                a=0;
            }
            if(a==5) return var;
        }
        //纵向判断
        int b = 0;
        for(int i=y-4;i<y+5;i++) {
            if(i<0||i>=this.y) continue;
            if(arr[x][i]==var) {
                b++;
            }
            else {
                b =0;
            }
            if(b ==5) return var;
        }
        //从左上到右下
        int leftUPToDown = 0;
        for(int i=x-4,j=y+4;i<x+5&&j>y-5;i++,j--) {
            if(i<0||i>=this.x||j<0||j>=this.y) continue;
            if(arr[i][j]==var) {
                leftUPToDown++;
            }else {
                leftUPToDown=0;
            }
            if(leftUPToDown==5) return var;
        }
        //从左下到右上
        int leftDownToUP = 0;
        for(int i=x+4,j=y+4;i>x-5&&j>y-5;i--,j--) {
            if(i<0||i>=this.x||j<0||j>=this.y) continue;
            if(arr[i][j]==var) {
                leftDownToUP++;
            }else {
                leftDownToUP=0;
            }
            if(leftDownToUP==5) return var;
        }
        return 0;
    }
    /**
     * 在该位置下棋  1:white 2:black
     * @param x 横坐标
     * @param y 纵坐标
     * @param var 棋子种类
     * @return 1:white 赢   2:black赢
     */
    public int ChessIt(int x,int y,int var) {
        if(isEmpty(x,y)) {
            arr[x][y] =var;
            Chess chess = new Chess(x,y);
            stack.push(chess);
            return checkVictory(x, y, var);
        }
        else return -1;
    }
    //悔棋
    public boolean RetChess() {
        if(stack.isEmpty()) return false;
        Chess chess = stack.pop();
        arr[chess.x][chess.y]= 0;
        return true;
    }
    //获得棋盘状态
    public int[][] getArr(){
        return this.arr;
    }
    //重新开始
    public void Restart() {
        for(int i=0;i<this.x;i++) {
            for(int j=0;j<this.y;j++) {
                this.arr[i][j]=0;
            }
        }
        this.stack.clear();
    }
}