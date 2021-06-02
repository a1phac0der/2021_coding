import java.util.*;

public class DiagonalPuzzle {
    public static void main(String[] args) {
        char[][] square1 = {{'.', '.', '#'}, {'#', '.', '#'}, {'#', '.', '.'}};
        char[][] square2 = {{'.', '#', '#', '#', '#'}, {'#', '.', '#', '#', '#'}, {'#', '#', '.', '#', '#'}, {'#', '#', '#', '.', '#'}, {'#', '#', '#', '#', '#'}};

//        solve(square2);

        char[][][] all_grids = readInput();

        int case_number = 0;
        for (char[][] grid : all_grids) {
            case_number++;
            System.out.print("Case #" + case_number + ":");
            solve(grid);
            System.out.println();
        }

    }

    private static char[][][] readInput() {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        char[][][] allGrids = new char[testCases][][];
        for (int i = 0; i < testCases; i++) {
            int size = scanner.nextInt();
            char[][] grid = new char[size][size];
            for (int r = 0; r < size; r++) {
                String str = scanner.next();
                for (int c = 0; c < size; c++) {
                    grid[r][c] = str.charAt(c);
                }
            }
            allGrids[i] = grid;
        }
        return allGrids;
    }

    private static void solve(char[][] square) {
        List<List<Cell>> forwardDiagonals = getForwardDiagonalsOf(square.length);
        List<List<Cell>> backwardDiagonals = getBackwardDiagonalsOf(square.length);
        Map<Integer, List<Cell>> diagonalMap = mapDiagonalsByIndex(forwardDiagonals, backwardDiagonals);
//        System.out.println("Diagonal Map: " + diagonalMap);
        int keyOfDiagonalWithHighestProfit = getKeyOfDiagonalWithMaxFlipDifference(diagonalMap, square);
//        System.out.println("Key of Best Diagonal: " + keyOfDiagonalWithHighestProfit);
        int strikes = 0;
        while (keyOfDiagonalWithHighestProfit != -1) {
            flipCellsAcrossDiagonal(diagonalMap.get(keyOfDiagonalWithHighestProfit), square);
            strikes++;
            keyOfDiagonalWithHighestProfit = getKeyOfDiagonalWithMaxFlipDifference(diagonalMap, square);
//            System.out.println("Key of Best Diagonal: " + keyOfDiagonalWithHighestProfit);
        }
        System.out.print(" " + strikes);
    }

    private static void flipCellsAcrossDiagonal(List<Cell> diagonal, char[][] square) {
        for (Cell cell : diagonal) {
            char currentChar = square[cell.row][cell.column];
            char flippedChar = currentChar == '.' ? '#' : '.';
            square[cell.row][cell.column] = flippedChar;
        }
    }

    private static int getKeyOfDiagonalWithMaxFlipDifference(Map<Integer, List<Cell>> diagonalMap, char[][] square) {
        int maxDiff = Integer.MIN_VALUE;
        int keyOfMaxDiffDiagonal = -1;
        int dots = 0;
        for (Integer key : diagonalMap.keySet()) {
            int diff = 0;
            List<Cell> diagonal = diagonalMap.get(key);
            for (Cell cell : diagonal) {
                if (square[cell.row][cell.column] == '.') {
                    diff++;
                    dots++;
                } else diff--;
            }
            if (diagonal.size() > 1 && diff >= (diagonal.size() - 1)) {
                return key;
            }
//            System.out.println("diagonal[" + key + "]: " + diagonal + " | diff: " + diff);
            if (diff > maxDiff) {
                maxDiff = diff;
                keyOfMaxDiffDiagonal = key;
            }
        }

        return dots == 0 ? -1 : keyOfMaxDiffDiagonal;
    }

    private static Map<Integer, List<Cell>> mapDiagonalsByIndex(List<List<Cell>> forwardDiagonals, List<List<Cell>> backwardDiagonals) {
        Map<Integer, List<Cell>> diagonalMap = new HashMap();
        int keyIndex = 0;
        for (int i = 0; i < forwardDiagonals.size(); i++) {
            diagonalMap.put(keyIndex++, forwardDiagonals.get(i));
            diagonalMap.put(keyIndex++, backwardDiagonals.get(i));
        }
        return diagonalMap;
    }

    private static List<List<Cell>> getForwardDiagonalsOf(int size) {
        List<List<Cell>> forwardDiagonals = new ArrayList<>();
        int maxIndex = size - 1;
        int i = 0, j = 0, k = 0, l = 0;
        while (i <= maxIndex && l <= maxIndex) {
            if (i == k) {
                List<Cell> diagonal = new ArrayList<>();
                for (int m = 0; m <= maxIndex; m++) {
//                    System.out.print(m + "" + m + " ");
                    diagonal.add(new Cell(m, m));
                }
                forwardDiagonals.add(diagonal);
            } else {
                List<Cell> oneSideDiagonal = new ArrayList<>();
                for (int m = i, n = j; m <= maxIndex; m++, n++) {
//                    System.out.print(m + "" + n + " ");
                    oneSideDiagonal.add(new Cell(m, n));
                }
                forwardDiagonals.add(oneSideDiagonal);
//                System.out.println();
                List<Cell> anotherSideDiagonal = new ArrayList<>();
                for (int m = k, n = l; n <= maxIndex; m++, n++) {
//                    System.out.print(m + "" + n + " ");
                    anotherSideDiagonal.add(new Cell(m, n));
                }
                forwardDiagonals.add(anotherSideDiagonal);
            }

//            System.out.println();
            i++;
            l++;
        }
        return forwardDiagonals;
    }

    private static List<List<Cell>> getBackwardDiagonalsOf(int size) {
        List<List<Cell>> backwardDiagonals = new ArrayList<>();
        int row_count = size;
        int i = row_count - 1, k = row_count - 1;
        int j = 0, l = 0;
        while (i >= 0 && j >= 0) {
            if (i == k) {
                List<Cell> diagonal = new ArrayList<>();
                for (int m = i, n = 0; m >= 0; m--, n++) {
//                    System.out.print(m + "" + n + " ");
                    diagonal.add(new Cell(m, n));
                }
                backwardDiagonals.add(diagonal);
            } else {
                List<Cell> oneSideDiagonal = new ArrayList<>();
                for (int m = i, n = j; m >= 0 && n <= i; m--, n++) {
//                    System.out.print(m + "" + n + " ");
                    oneSideDiagonal.add(new Cell(m, n));
                }
                backwardDiagonals.add(oneSideDiagonal);
//                System.out.println();
                List<Cell> anotherSideDiagonal = new ArrayList<>();
                for (int m = k, n = l; m >= l && n <= k; m--, n++) {
//                    System.out.print(m + "" + n + " ");
                    anotherSideDiagonal.add(new Cell(m, n));
                }
                backwardDiagonals.add(anotherSideDiagonal);
            }
//            System.out.println();
            i--;
            l++;
        }
        return backwardDiagonals;
    }

    static class Cell {
        int row, column;

        Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        @Override
        public String
        toString() {
            return row + "" + column;
        }
    }

}
