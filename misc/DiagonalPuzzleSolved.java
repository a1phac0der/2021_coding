import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class DiagonalPuzzleSolved {
    static int diagonalNumber = 1;
    static Map<Cell, Integer> whiteCellToDiagonalMap = new HashMap<>();
    static Map<Cell, Integer> blackCellToDiagonalMap = new HashMap<>();
    static Map<Integer, Set<Integer>> flipOppositeDiagonalGraph = new HashMap<>();
    static Map<Integer, Set<Integer>> flipSameDiagonalGraph = new HashMap<>();
    static Map<Integer, Boolean> diagonalVisitedDuringFlip = new HashMap<>();
    static Map<Integer, Boolean> diagonalVisitedDuringNoFlip = new HashMap<>();
    static char[][] grid;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        int cases = scanner.nextInt();

        for (int case_number = 1; case_number <= cases; case_number++) {
            grid = readGrid(scanner);
            System.out.print("Case #" + case_number + ":");
            solve();
            cleanUpObjects();
        }

    }

    private static void cleanUpObjects() {
        diagonalNumber = 1;
        whiteCellToDiagonalMap.clear();
        blackCellToDiagonalMap.clear();
        flipOppositeDiagonalGraph.clear();
        flipSameDiagonalGraph.clear();
        diagonalVisitedDuringFlip.clear();
        diagonalVisitedDuringNoFlip.clear();
    }


    private static char[][] readGrid(Scanner scanner) {
        int gridSize = scanner.nextInt();
        char[][] grid = new char[gridSize][gridSize];
        for (int r = 0; r < gridSize; r++) {
            String str = scanner.next();
            for (int c = 0; c < gridSize; c++) {
                grid[r][c] = str.charAt(c);
            }
        }
        return grid;
    }

    private static void solve() {
        goThroughForwardDiagonals();
        goThroughBackwardDiagonals();
        int total = 0;
        for (Map.Entry<Integer, Set<Integer>> diagonalPair : flipOppositeDiagonalGraph.entrySet()) {
            int flipCount = getTotalFlips(diagonalPair.getKey(), true, new AtomicInteger(0));
            int noFlipCount = getTotalFlips(diagonalPair.getKey(), false, new AtomicInteger(0));
            total += Math.min(flipCount, noFlipCount);
        }
        System.out.println(" " + total);
    }

    private static int getTotalFlips(int diagonal, boolean flip, AtomicInteger flips) {
        if (flip) {
            if (diagonalVisitedDuringFlip.getOrDefault(diagonal, false))
                return flips.get();
            diagonalVisitedDuringFlip.put(diagonal, true);
            flips.incrementAndGet();
        } else {
            if (diagonalVisitedDuringNoFlip.getOrDefault(diagonal, false))
                return flips.get();
            diagonalVisitedDuringNoFlip.put(diagonal, true);
        }
        if (flipSameDiagonalGraph.containsKey(diagonal))
            for (int currentDiagonal : flipSameDiagonalGraph.get(diagonal))
                getTotalFlips(currentDiagonal, flip, flips);
        if (flipOppositeDiagonalGraph.containsKey(diagonal))
            for (int currentDiagonal : flipOppositeDiagonalGraph.get(diagonal))
                getTotalFlips(currentDiagonal, !flip, flips);

        return flips.get();
    }

    private static int strikeWithDiagonal(List<List<Cell>> diagonalsCrossingDots, int indexOfDiagonalWithMaxPoints, char[][] grid, Map<Cell, List<List<Cell>>> cellToDiagonalsMap) {
        //        int flippedToDots = 0;
        for (Cell cell : diagonalsCrossingDots.get(indexOfDiagonalWithMaxPoints)) {
            char currentChar = grid[cell.row][cell.column];
            char flippedChar = currentChar == '.' ? '#' : '.';
            grid[cell.row][cell.column] = flippedChar;
            if (flippedChar == '.') {
                //                flippedToDots++;
                diagonalsCrossingDots.remove(indexOfDiagonalWithMaxPoints);
                diagonalsCrossingDots.addAll(cellToDiagonalsMap.get(cell));
            }
        }
        return 0;
    }

    private static int getIndexOfDiagonalWithMaxFlipPoints(List<List<Cell>> diagonalsCrossingDots, char[][] grid) {
        int maxPoints = Integer.MIN_VALUE;
        int indexOfDiagonalWithMaxPoints = -1;
        List<Integer> indexesToBeRemoved = new ArrayList<>();
        for (int i = 0; i < diagonalsCrossingDots.size(); i++) {
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
        for (int index : indexesToBeRemoved) {
            diagonalsCrossingDots.remove(index - sizeReduction++);
        }

        for (int i = 0; i < diagonalsCrossingDots.size(); i++) {
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

    private static void noticeDiagonalCrossingCell(Cell cell, Map<Cell, Integer> cellToDiagonalMap, Map<Integer, Set<Integer>> diagonalGraph, int currentDiagonal) {
        if (cellToDiagonalMap.containsKey(cell)) {
            int previousDiagonal = cellToDiagonalMap.get(cell);
            addDiagonalToGraph(currentDiagonal, previousDiagonal, diagonalGraph);
            addDiagonalToGraph(previousDiagonal, currentDiagonal, diagonalGraph);
        } else
            cellToDiagonalMap.put(cell, currentDiagonal);
    }

    private static void addDiagonalToGraph(int diagonal1, int diagonal2, Map<Integer, Set<Integer>> diagonalGraph) {
        diagonalGraph.compute(diagonal1, (k, v) -> {
            Set<Integer> diagonals = v;
            if (diagonals == null)
                diagonals = new TreeSet<>();
            diagonals.add(diagonal2);
            return diagonals;
        });
    }

    private static void goThroughForwardDiagonals() {
        int maxIndex = grid.length - 1;
        int i = 0, j = 0, k = 0, l = 0;
        while (i <= maxIndex && l <= maxIndex) {
            if (i == k) {
                int currentDiagonal = diagonalNumber++;
                for (int m = 0; m <= maxIndex; m++) {
                    Cell cell = new Cell(m, m);
                    if (grid[m][m] == '.') {
                        noticeDiagonalCrossingCell(cell, whiteCellToDiagonalMap, flipOppositeDiagonalGraph, currentDiagonal);
                    } else {
                        noticeDiagonalCrossingCell(cell, blackCellToDiagonalMap, flipSameDiagonalGraph, currentDiagonal);
                    }
                }
            } else {
                int currentDiagonal = diagonalNumber++;
                for (int m = i, n = j; m <= maxIndex; m++, n++) {
                    Cell cell = new Cell(m, n);
                    if (grid[m][n] == '.') {
                        noticeDiagonalCrossingCell(cell, whiteCellToDiagonalMap, flipOppositeDiagonalGraph, currentDiagonal);
                    } else {
                        noticeDiagonalCrossingCell(cell, blackCellToDiagonalMap, flipSameDiagonalGraph, currentDiagonal);
                    }
                }
                currentDiagonal = diagonalNumber++;
                for (int m = k, n = l; n <= maxIndex; m++, n++) {
                    Cell cell = new Cell(m, n);
                    if (grid[m][n] == '.') {
                        noticeDiagonalCrossingCell(cell, whiteCellToDiagonalMap, flipOppositeDiagonalGraph, currentDiagonal);
                    } else {
                        noticeDiagonalCrossingCell(cell, blackCellToDiagonalMap, flipSameDiagonalGraph, currentDiagonal);
                    }
                }
            }
            i++;
            l++;
        }
    }

    private static List<List<Cell>> goThroughBackwardDiagonals() {
        List<List<Cell>> backwardDiagonals = new ArrayList<>();
        int row_count = grid.length;
        int i = row_count - 1, k = row_count - 1;
        int j = 0, l = 0;
        while (i >= 0 && j >= 0) {
            if (i == k) {
                int currentDiagonal = diagonalNumber++;
                for (int m = i, n = 0; m >= 0; m--, n++) {
                    Cell cell = new Cell(m, n);
                    if (grid[m][n] == '.') {
                        noticeDiagonalCrossingCell(cell, whiteCellToDiagonalMap, flipOppositeDiagonalGraph, currentDiagonal);
                    } else {
                        noticeDiagonalCrossingCell(cell, blackCellToDiagonalMap, flipSameDiagonalGraph, currentDiagonal);
                    }
                }
            } else {
                int currentDiagonal = diagonalNumber++;
                for (int m = i, n = j; m >= 0 && n <= i; m--, n++) {
                    Cell cell = new Cell(m, n);
                    if (grid[m][n] == '.') {
                        noticeDiagonalCrossingCell(cell, whiteCellToDiagonalMap, flipOppositeDiagonalGraph, currentDiagonal);
                    } else {
                        noticeDiagonalCrossingCell(cell, blackCellToDiagonalMap, flipSameDiagonalGraph, currentDiagonal);
                    }
                }
                currentDiagonal = diagonalNumber++;
                for (int m = k, n = l; m >= l && n <= k; m--, n++) {
                    Cell cell = new Cell(m, n);
                    if (grid[m][n] == '.') {
                        noticeDiagonalCrossingCell(cell, whiteCellToDiagonalMap, flipOppositeDiagonalGraph, currentDiagonal);
                    } else {
                        noticeDiagonalCrossingCell(cell, blackCellToDiagonalMap, flipSameDiagonalGraph, currentDiagonal);
                    }
                }
            }
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
