package gol;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class GameOfLife {

    private final Set<AliveCell> grid = new HashSet<AliveCell>();
    private final int rows;
    private final int cols;

    public GameOfLife(final String... lines) {
        this.rows = lines.length;
        this.cols = lines[0].length();
        new EachCoordinate(rows, cols).execute(new GridOperation() {
            public boolean execute(int row, int col) {
                return lines[row].charAt(col) == '*' && lives(row, col);
            }

            private boolean lives(final int x, final int y) {
                grid.add(new AliveCell(x, y));
                return true;
            }
        });
    }

    public GameOfLife nextStep() {
        final Set<AliveCell> nextGeneration = new HashSet<AliveCell>();
        new EachCoordinate(rows, cols).execute(new GridOperation() {
            public boolean execute(final int row, final int col) {
                final AliveCell cell = new AliveCell(row, col);
                final boolean currentlyAlive = grid.contains(cell);
                return livesOnNextGeneration(currentlyAlive, aliveNeighboursOf(row, col)) && storeAlive(cell);
            }

            private boolean livesOnNextGeneration(final boolean cellAlive, final int neighbours) {
                return (cellAlive && (neighbours == 2 || neighbours == 3)) || (!cellAlive && neighbours == 3);
            }

            private boolean storeAlive(AliveCell cell) {
                nextGeneration.add(cell);
                return true;
            }

            private int aliveNeighboursOf(final int x, final int y) {
                final AtomicInteger result = new AtomicInteger();
                new EachCoordinate(3, 3).execute(new GridOperation() {
                    public boolean execute(final int row, final int col) {
                        return !(row == 1 && col == 1)
                                && grid.contains(new AliveCell(x + row - 1, y + col - 1))
                                && oneMoreNeighbour();
                    }

                    private boolean oneMoreNeighbour() {
                        result.addAndGet(1);
                        return true;
                    }
                });
                return result.get();
            }
        });
        grid.clear();
        grid.addAll(nextGeneration);
        return this;
    }

    private class AliveCell {
        private final int row, col;

        private AliveCell(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public int hashCode() {
            return 31 * row + col;
        }

        @Override
        public boolean equals(final Object o) {
            return this == o ||
                    o != null && getClass() == o.getClass() &&
                            row == ((AliveCell) o).row && col == ((AliveCell) o).col;
        }
    }

    @Override
    public boolean equals(final Object o) {
        return this == o ||
                o != null && getClass() == o.getClass() &&
                        ((grid == null && ((GameOfLife) o).grid == null) ||
                                (grid != null && grid.equals(((GameOfLife) o).grid)));
    }

    @Override
    public int hashCode() {
        return grid != null ? grid.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuilder result = new StringBuilder();
        new EachCoordinate(rows, cols).execute(new GridOperation() {
            public boolean execute(final int row, final int col) {
                final boolean alive = grid.contains(new AliveCell(row, col));
                return ((alive && append("*")) || append("0")) && col == cols - 1 && append("\n");
            }

            private boolean append(final String string) {
                result.append(string);
                return true;
            }
        });
        return result.toString();
    }

    private interface GridOperation {
        boolean execute(int row, int col);
    }

    private class EachCoordinate {
        private final int row, col;
        private int currentRow = 0;
        private int currentCol = 0;

        private EachCoordinate(final int row, final int col) {
            this.row = row;
            this.col = col;
        }

        public void execute(GridOperation operation) {
            doVisit(currentRow, currentCol, operation);
        }

        private boolean doVisit(final int row, final int col, GridOperation operation) {
            operation.execute(row, col);
            return ((col < this.col - 1 && incrementCurrentY()) ||
                    resetY() && (row < this.row - 1 && incrementCurrentX()))
                    && doVisit(currentRow, currentCol, operation);
        }

        private boolean resetY() {
            this.currentCol = 0;
            return true;
        }

        private boolean incrementCurrentX() {
            this.currentRow++;
            return true;
        }

        private boolean incrementCurrentY() {
            this.currentCol++;
            return true;
        }

    }
}
