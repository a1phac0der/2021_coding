import java.util.*;

public class DiagonalPuzzle2 {
    public static void main(String[] args) {

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

    private static void solve(char[][] grid) {
        Map<Cell, List<List<Cell>>> cellToDiagonalsMap = new HashMap<>();
        List<List<Cell>> diagonalsCrossingDots = new ArrayList<>();
        getForwardDiagonalsOf(grid, cellToDiagonalsMap, diagonalsCrossingDots);
        getBackwardDiagonalsOf(grid, cellToDiagonalsMap, diagonalsCrossingDots);
        int indexOfDiagonalWithMaxPoints = getIndexOfDiagonalWithMaxFlipPoints(diagonalsCrossingDots, grid);
        int strikes = 0;
        while (indexOfDiagonalWithMaxPoints != -1){
            int newDots = strikeWithDiagonal(diagonalsCrossingDots, indexOfDiagonalWithMaxPoints, grid, cellToDiagonalsMap);
            strikes++;
            if (newDots == 0)
                diagonalsCrossingDots.remove(indexOfDiagonalWithMaxPoints);
            indexOfDiagonalWithMaxPoints = getIndexOfDiagonalWithMaxFlipPoints(diagonalsCrossingDots, grid);
        }
        System.out.print(" " + strikes);
    }

    private static int strikeWithDiagonal(List<List<Cell>> diagonalsCrossingDots, int indexOfDiagonalWithMaxPoints, char[][] grid, Map<Cell, List<List<Cell>>> cellToDiagonalsMap) {
        int flippedToDots = 0;
        for (Cell cell : diagonalsCrossingDots.get(indexOfDiagonalWithMaxPoints)) {
            char currentChar = grid[cell.row][cell.column];
            char flippedChar = currentChar == '.' ? '#' : '.';
            grid[cell.row][cell.column] = flippedChar;
            if (flippedChar == '.') {
                flippedToDots++;
                diagonalsCrossingDots.remove(indexOfDiagonalWithMaxPoints);
                diagonalsCrossingDots.addAll(cellToDiagonalsMap.get(cell));
            }
        }
        return flippedToDots;
    }

    private static int getIndexOfDiagonalWithMaxFlipPoints(List<List<Cell>> diagonalsCrossingDots, char[][] grid) {
        int maxPoints = Integer.MIN_VALUE;
        int indexOfDiagonalWithMaxPoints = -1;
        List<Integer> indexesToBeRemoved = new ArrayList<>();
        for (int i=0; i < diagonalsCrossingDots.size(); i++) {
            boolean anyDots = false;
            for (Cell cell : diagonalsCrossingDots.get(i)) {
                if (grid[cell.row][cell.column] == '.') {
                    anyDots = true;
                    break;
                }
            }
            if (!anyDots)
                indexesToBeRemoved.add(i);
        }
        int sizeReduction = 0;
        for (int index: indexesToBeRemoved){
            diagonalsCrossingDots.remove(index-sizeReduction++);
        }

        for (int i=0; i < diagonalsCrossingDots.size(); i++) {
            int points = 0;
            for (Cell cell : diagonalsCrossingDots.get(i)) {
                if (grid[cell.row][cell.column] == '.')
                    points++;
                else points--;
            }
            if (points > maxPoints) {
                maxPoints = points;
                indexOfDiagonalWithMaxPoints = i;
            }
        }
        return indexOfDiagonalWithMaxPoints;
    }

    private static void addCellToDiagonalsMap(Cell cell, List<Cell> diagonal, Map<Cell, List<List<Cell>>> cellToDiagonalsMap) {
        cellToDiagonalsMap.compute(cell, (key, value) -> {
            if (value == null) {
                List<List<Cell>> diagonals = new ArrayList<>();
                diagonals.add(diagonal);
                return diagonals;
            } else {
                cellToDiagonalsMap.get(cell).add(diagonal);
                return cellToDiagonalsMap.get(cell);
            }
        });
    }

    private static List<List<Cell>> getForwardDiagonalsOf(char[][] grid, Map<Cell, List<List<Cell>>> cellToDiagonalsMap, List<List<Cell>> diagonalsCrossingDots) {
        List<List<Cell>> forwardDiagonals = new ArrayList<>();
        int maxIndex = grid.length - 1;
        int i = 0, j = 0, k = 0, l = 0;
        while (i <= maxIndex && l <= maxIndex) {
            if (i == k) {
                List<Cell> diagonal = new ArrayList<>();
                boolean diagonalCrossedDots = false;
                for (int m = 0; m <= maxIndex; m++) {
//                    System.out.print(m + "" + m + " ");
                    Cell cell = new Cell(m, m);
                    diagonal.add(new Cell(m, m));
                    addCellToDiagonalsMap(cell, diagonal, cellToDiagonalsMap);
                    if (grid[m][m] == '.')
                        diagonalCrossedDots = true;
                }
                forwardDiagonals.add(diagonal);
                if (diagonalCrossedDots)
                    diagonalsCrossingDots.add(diagonal);
            } else {
                List<Cell> oneSideDiagonal = new ArrayList<>();
                boolean diagonalCrossedDots = false;
                for (int m = i, n = j; m <= maxIndex; m++, n++) {
//                    System.out.print(m + "" + n + " ");
                    Cell cell = new Cell(m, n);
                    oneSideDiagonal.add(cell);
                    addCellToDiagonalsMap(cell, oneSideDiagonal, cellToDiagonalsMap);
                    if (grid[m][n] == '.')
                        diagonalCrossedDots = true;
                }
                forwardDiagonals.add(oneSideDiagonal);
                if (diagonalCrossedDots)
                    diagonalsCrossingDots.add(oneSideDiagonal);
//                System.out.println();
                List<Cell> anotherSideDiagonal = new ArrayList<>();
                diagonalCrossedDots = false;
                for (int m = k, n = l; n <= maxIndex; m++, n++) {
//                    System.out.print(m + "" + n + " ");
                    Cell cell = new Cell(m, n);
                    anotherSideDiagonal.add(cell);
                    addCellToDiagonalsMap(cell, anotherSideDiagonal, cellToDiagonalsMap);
                    if (grid[m][n] == '.')
                        diagonalCrossedDots = true;
                }
                forwardDiagonals.add(anotherSideDiagonal);
                if (diagonalCrossedDots)
                    diagonalsCrossingDots.add(anotherSideDiagonal);
            }

//            System.out.println();
            i++;
            l++;
        }
        return forwardDiagonals;
    }

    private static List<List<Cell>> getBackwardDiagonalsOf(char[][] grid, Map<Cell, List<List<Cell>>> cellToDiagonalsMap, List<List<Cell>> diagonalsCrossingDots) {
        List<List<Cell>> backwardDiagonals = new ArrayList<>();
        int row_count = grid.length;
        int i = row_count - 1, k = row_count - 1;
        int j = 0, l = 0;
        while (i >= 0 && j >= 0) {
            if (i == k) {
                List<Cell> diagonal = new ArrayList<>();
                boolean diagonalCrossedDots = false;
                for (int m = i, n = 0; m >= 0; m--, n++) {
//                    System.out.print(m + "" + n + " ");
                    Cell cell = new Cell(m, n);
                    diagonal.add(cell);
                    addCellToDiagonalsMap(cell, diagonal, cellToDiagonalsMap);
                    if (grid[m][n] == '.')
                        diagonalCrossedDots = true;
                }
                backwardDiagonals.add(diagonal);
                diagonalsCrossingDots.add(diagonal);
            } else {
                List<Cell> oneSideDiagonal = new ArrayList<>();
                boolean diagonalCrossedDots = false;
                for (int m = i, n = j; m >= 0 && n <= i; m--, n++) {
//                    System.out.print(m + "" + n + " ");
                    Cell cell = new Cell(m, n);
                    oneSideDiagonal.add(cell);
                    addCellToDiagonalsMap(cell, oneSideDiagonal, cellToDiagonalsMap);
                    if (grid[m][n] == '.')
                        diagonalCrossedDots = true;
                }
                backwardDiagonals.add(oneSideDiagonal);
                if (diagonalCrossedDots)
                    diagonalsCrossingDots.add(oneSideDiagonal);
//                System.out.println();
                List<Cell> anotherSideDiagonal = new ArrayList<>();
                diagonalCrossedDots = false;
                for (int m = k, n = l; m >= l && n <= k; m--, n++) {
//                    System.out.print(m + "" + n + " ");
                    Cell cell = new Cell(m, n);
                    anotherSideDiagonal.add(cell);
                    addCellToDiagonalsMap(cell, anotherSideDiagonal, cellToDiagonalsMap);
                    if (grid[m][n] == '.')
                        diagonalCrossedDots = true;
                }
                backwardDiagonals.add(anotherSideDiagonal);
                if (diagonalCrossedDots)
                    diagonalsCrossingDots.add(anotherSideDiagonal);
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

        public boolean equals(Object o) {
            Cell that = (Cell) o;
            return (this.row == that.row && this.column == that.column);
        }

        public int hashCode() {
            return row + column;
        }
    }

}
