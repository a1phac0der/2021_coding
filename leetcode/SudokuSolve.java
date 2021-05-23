package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class SudokuSolve {
    boolean[][]   rows    = new boolean[9][10];
    boolean[][]   columns = new boolean[9][10];
    boolean[][][] grids = new boolean[3][3][10];
    Queue<int[]>  queue = new LinkedList();

    public void solveSudoku(char[][] board) {
        fillData(board);
        tryFilling(board);
    }

    private boolean tryFilling(char[][] board){
        if(queue.isEmpty()){
            return true;
        }
        int[] emptyCell = queue.poll();
        for(int digit=1; digit <=9; digit++){
            if(canPlaceAt(emptyCell, digit)){
                int[] cell = placeAt(board, emptyCell, digit);
                boolean success = tryFilling(board);
                if(success) {
                    return true;
                }else{
                    clearCell(board, cell, digit);
                }
            }
        }
        queue.offer(emptyCell);
        return false;
    }

    private int[] placeAt(char[][] board, int[] cell, int digit){
        board[cell[0]][cell[1]] = Character.forDigit(digit,10);
        rows[cell[0]][digit] = true;
        columns[cell[1]][digit] = true;
        grids[cell[0]/3][cell[1]/3][digit] = true;
        return cell;
    }

    private void clearCell(char[][] board, int[] cell, int digit){
        board[cell[0]][cell[1]] = '.';
        rows[cell[0]][digit] = false;
        columns[cell[1]][digit] = false;
        grids[cell[0]/3][cell[1]/3][digit] = false;
    }

    private void fillData(char[][] board){
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                if(board[i][j] == '.'){
                    queue.add(new int[]{i,j});
                    continue;
                }
                char c = board[i][j];
                rows[i][c - '0'] = true;
                columns[j][c - '0'] = true;
                grids[i/3][j/3][c - '0'] = true;
            }
        }
    }

    private boolean canPlaceAt(int[] emptyCell, int number){
        return !(rows[emptyCell[0]][number] || columns[emptyCell[1]][number] || grids[emptyCell[0]/3][emptyCell[1]/3][number]);
    }
}
