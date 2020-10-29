package G29740.GoLProject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.TitledBorder;

/**
 * This class represents the grid to contain the cells
 *
 * @author G29740
 */
public class Grid extends javax.swing.JPanel {

    private Cell[][] arrCells;
    private boolean[][] arrCellsState;
    private int arrRows;
    private int arrColumns;
    private Color color;
    private int countAlive;
    private int generationNb;
    private int population;

    /**
     * Creates a grid with 25 x 40 dead cells by default
     * An exception is thrown when number of columns or rows is less than 2 or more than 50
     *
     * @throws GridException grid exception is thrown
     */
    public Grid() throws GridException {
        this(25, 40);
    }

    /**
     * Builds the grid with "dead" cells and with the given number of rows and columns
     * (r x c)
     * An exception is thrown when number of columns or rows is less than 2 or greater than 40
     * @param r Number of rows
     * @param c Number of column
     * @throws GridException grid exception is thrown
     */
    public Grid(int r, int c) throws GridException {
        initComponents();
        createGrid(r, c);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 300, Short.MAX_VALUE));
    }

    /**
     * Another two-dimentional array of booleans of similar size than the grid is used to contain cell states.
     * This method uses the boolean array and updates all the Cell objects inside the GoL grid.
     * An exception is thrown in the following cases:
     * - If nothing is given in parameter (null)
     * - If the given array is not a rectangular array
     * - If the given array size is less than 2x2 or more than 50x50
     *
     * @param cells the two-dimentional array of booleans
     * @return returns true if the GoL grid has been successfully updated
     * @throws GridException grid exception is thrown
     */
    public boolean setState(boolean[][] cells) throws GridException {
        if (cells == null) {
            throw new GridException("Grid is empty (has no cells)!");
        }
        for (int i = 0; i < cells.length; i++) {
            int tailleCls = cells[0].length;
            if (cells[i].length != tailleCls) {
                throw new GridException("Non rectangular grid!");
            }
            if ((cells[i].length < 2) || (cells[i].length > 50)) {
                throw new GridException("Grid must be at least 2x2 and not greater than 50x50!");
            }
        }
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                arrCells[i][j].setCellAlive(cells[i][j]);
            }
        }
        return false;

    }

    /**
     * This creates the GoL grid and the boolean array together.
     * An exception is thrown if the array size is less than 2x2 or more than 50x50
     * @param l Nombre de arrRows
     * @param c Nombre de arrColumns
     * @throws GridException exception plateau
     */
    public void createGrid(int l, int c) throws GridException {
        if ((l < 2) || (l > 50) || (c < 2) || (c > 50)) {
            throw new GridException("Invalid row/column size! Must be between 2 and 50!");
        } else {
            arrRows = l;
            arrColumns = c;
            removeAll();
            generationNb = 0;
            population = 0;
            countAlive = 0;
            arrCells = new Cell[l][c];
            arrCellsState = new boolean[l][c];
            setLayout(new GridLayout(l, c));

            setSize(new Dimension(c * 20, l * 20));

            setBorder(new TitledBorder("Grid"));
            for (int i = 0; i < l; i++) {
                for (int j = 0; j < c; j++) {
                    Cell led = new Cell();
                    arrCells[i][j] = led;
                    add(arrCells[i][j]);
                    arrCellsState[i][j] = false;

                    updateUI();
                }
            }
        }
        color = arrCells[0][0].getCellColor();
    }

    /**
     * Get the state array (array of booleans)
     * )
     * @return the state array
     */
    public boolean[][] getCellsStateArray() {
        return this.arrCellsState;
    }

    /**
     * Unlocks all cells on the grid (makes them clickable)
     *
     * @param b true for unlocked, false for locked
     */
    public void setUnlockedCells(boolean b) {
        for (Cell[] i : arrCells) {
            for (Cell j : i) {
                j.setCellUnlocked(b);
            }
        }
    }

    /**
     * Returns the current color of the cells
     *
     * @return the current color
     */
    public Color getAliveCellColor() {
        return color;
    }

    /**
     * Change the color of the cells
     * An exception is thrown if the color given is the same as the current color
     *
     * @param newColor the new color to set to the cells
     * @throws GridException color exception in the grid
     */
    public void setAliveCellColor(Color newColor) throws GridException {
        if (newColor.getRGB() == this.color.getRGB()) {
            throw new GridException("New color is identical to old one!");
        } else {
            this.color = newColor;
            for (Cell[] i : arrCells) {
                for (Cell j : i) {
                    j.setCellColor(newColor);
                    repaint();
                }
            }
        }
    }

    /**
     * Return the number of rows on the grid
     * @return The number of rows
     */
    public int getRowCount() {
        return this.arrRows;
    }

    /**
     * Return the number of columns on the grid
     * @return The number of columns
     */
    public int getColumnCount() {
        return this.arrColumns;
    }

    /**
     * Animate the cells : a cell is born or stays alive if exactly 3 alive cells are surrounding it,
     * a cell dies in any other case
     * The grid is 'redrawn' after each generation
     */
    public void evolution() {
        int cellsAliveCount = 0;
        population = 0;
        generationNb++;
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                arrCellsState[i][j] = arrCells[i][j].isCellAlive();
                if (arrCellsState[i][j]) {population++;}
            }
        }
        for (int i = 0; i < getRowCount(); i++) {
            for (int j = 0; j < getColumnCount(); j++) {
                if (((i != 0) && (j != 0)) && (arrCells[i - 1][j - 1].isCellAlive())) {
                    cellsAliveCount++;
                }
                if ((i != 0) && (arrCells[i - 1][j].isCellAlive())) {
                    cellsAliveCount++;
                }
                if (((i != 0) && (j != getColumnCount() - 1)) && (arrCells[i - 1][j + 1].isCellAlive())) {
                    cellsAliveCount++;
                }
                if ((j != 0) && (arrCells[i][j - 1].isCellAlive())) {
                    cellsAliveCount++;
                }
                if ((j < getColumnCount() - 1) && (arrCells[i][j + 1].isCellAlive())) {
                    cellsAliveCount++;
                }
                if (((i < getRowCount() - 1) && (j != 0)) && (arrCells[i + 1][j - 1].isCellAlive())) {
                    cellsAliveCount++;
                }
                if ((i < getRowCount() - 1) && (arrCells[i + 1][j].isCellAlive())) {
                    cellsAliveCount++;
                }
                if (((i < getRowCount() - 1) && (j < getColumnCount() - 1)) && (arrCells[i + 1][j + 1].isCellAlive())) {
                    cellsAliveCount++;
                }
                if ((!arrCells[i][j].isCellAlive()) && (cellsAliveCount == 3)) {
                    arrCellsState[i][j] = true;
                } else {
                    arrCellsState[i][j] = ((arrCells[i][j].isCellAlive()) && ((cellsAliveCount == 2) || (cellsAliveCount == 3)));
                }
                cellsAliveCount = 0;
            }
        }
        try {
            setState(getCellsStateArray());
        } catch (GridException ex) {
            ex.getMessage();
        }
    }

    /**
     * Kills all cells on the grid and unlocks them (makes them clickable)
     */
    public void killCells() {
        generationNb = 0;
        for (Cell[] i : arrCells) {
            for (Cell j : i) {
                j.setCellAlive(false);
                j.setCellUnlocked(true);
            }
        }
    }

    /**
     * Method to set the number of live cells on the grid
     *
     * @param i the new number
     */
    private void setCountAlive(int i) {
        int tmp = countAlive;
        this.countAlive = i;
        firePropertyChange("countAlive", tmp, countAlive);
    }

    /**
     * Return the generation number
     *
     * @return the generation number
     */
    public int getGenerationNb() {
        return this.generationNb;
    }

    public void setGenerationNb(int generationNb) {
        this.generationNb = generationNb;
    }

    /**
     * Return the number of live cells on the grid
     *
     * @return the population of alive cells
     */
    public int getPopulation() {
        return this.population;
    }

    /**
     * Set the population of live cells on the grid
     *
     * @param population
     */
    public void setPopulation(int population) {
        this.population = population;
    }
}