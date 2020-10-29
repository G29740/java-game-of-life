package G29740.GoLProject;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

/**
 * Represents a cell
 * Has two display states: alive (display in blue on grid) or dead (display in background color on grid)
 * Has two "mouse clickable" states: locked (not clickable), unlocked (clickable).
 *
 * @author G29740
 */
public class Cell extends JPanel {

    private boolean cellAlive;
    private boolean cellUnlocked;
    private Color cellColor;

    /**
     * Creates a cell (dead by default)
     * Color: blue by default
     * Cell locked, cannot be clicked by default
     * Dimension 45px by default
     */
    public Cell() {
        setCellColor(Color.BLUE);
        this.cellAlive = false;
        this.cellUnlocked = false;
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (cellUnlocked) {
                    setCellAlive(!isCellAlive());
                }
            }
        });
        setPreferredSize(new Dimension(22, 22));
        setBorder(new LineBorder(Color.LIGHT_GRAY, 1));
    }

    /**
     * Returns cell's alive state
     * @return cellAlive Cell's state (alive or dead)
     */
    public boolean isCellAlive() {
        return this.cellAlive;
    }

    /**
     * Set cell's alive state
     * @param b New cell's state
     */
    public void setCellAlive(boolean b) {
        repaint();
        if (b != cellAlive) {
            this.cellAlive = b;
            firePropertyChange("alive", !b, b);
        } else {
            firePropertyChange("alive", b, b);
        }
    }

    /**
     * Toggles alive/dead states of the cell
     */
    public void reverseCellState() {
        setCellAlive(!cellAlive);
    }

    /**
     * Returns whether the cell is clickable (unlocked)
     * @return cellUnlocked True (unlocked/clickable), false (locked/not clickable)
     */
    public boolean isCellUnlocked() {
        return this.cellUnlocked;
    }

    /**
     * Set lock (click) status on cell
     * If unlocked, cell will have blue borders
     */
    public void setCellUnlocked(boolean b) {
        cellUnlocked = b;
    }

    /**
     * Return the cell's color
     * @return cellColor Current color
     */
    public Color getCellColor() {
        return cellColor;
    }

    /**
     * Set the cell's color
     * @param cl New color to set
     */
    public void setCellColor(Color cl) {
        this.cellColor = cl;
    }

    /**
     * Draws the cell
     * In blue if alive, background color if dead
     * @param g Graphic data to draw
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawRect(0, 0, 22, 22);
        if (cellAlive) {
            g.setColor(getCellColor());
            g.fillRect(0, 0, 22, 22);
        } else {
            g.setColor(this.getBackground());
            g.fillRect(0, 0, 22, 22);
        }
    }
}