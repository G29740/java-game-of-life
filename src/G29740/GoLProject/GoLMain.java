package G29740.GoLProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Frame containing the grid, the control buttons and a menu bar
 *
 * @author G29740
 */
public class GoLMain extends javax.swing.JFrame {

    private Timer timer;
    private Grid golGrid;
    private JPanel buttonsPanel = new JPanel();
    private JButton autoStepButton = new JButton("Start");
    private JButton stopAutoButton = new JButton("Stop");
    private JButton stepButton = new JButton("Step");
    private JButton resetButton = new JButton("Reset");

    private JLabel generationLabel = new JLabel("Generation:");
    private JTextField generationValueTF = new JTextField("0");
    private JLabel populationLabel = new JLabel("Population:");
    private JTextField populationValueTF = new JTextField("0");

    private JMenuBar menuBarGoL = new JMenuBar();
    private JMenu menuGame = new JMenu("Game");
    private JMenu menuHelp = new JMenu("Help");
    private JMenu menuOptions = new JMenu("Options");
    private JMenuItem menuItemAutoStep = new JMenuItem("Auto step");
    private JMenuItem menuItemStopAuto = new JMenuItem("Stop auto");
    private JMenuItem menuItemStep = new JMenuItem("Step");
    private JMenuItem menuItemReset = new JMenuItem("Reset");
    private JMenuItem menuItemQuit = new JMenuItem("Quit");
    private JMenuItem menuOptionsColor = new JMenuItem("Colors");
    private JMenuItem menuOptionsSize = new JMenuItem("Size");
    private JMenuItem menuOptionsTimer = new JMenuItem("Timer");
    private JMenuItem menuItemAbout = new JMenuItem("About");


    /**
     * Create the frame and the components
     * (buttons, menus, options) and a Timer for execution.
     */
    public GoLMain() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        setLocationRelativeTo(null);
        resetActions();
        setVisible(true);
    }

    /**
     * Entry point of application
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        new GoLMain();
    }

    private void initComponents() {

        try {
            generationValueTF.setEditable(false);
            generationValueTF.setBorder(null);
            generationValueTF.setHorizontalAlignment(0);
            populationValueTF.setEditable(false);
            populationValueTF.setBorder(null);
            populationValueTF.setHorizontalAlignment(0);
            golGrid = new Grid();
            timer = new Timer(200, e -> {
                getGolGrid().evolution();
                generationValueTF.setText("" + getGolGrid().getGenerationNb());
                populationValueTF.setText("" + getGolGrid().getPopulation());
            });
            menuOptionsSize.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuOptionsSizeAction(e);
                }
            });

            menuOptionsColor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuOptionsColourActionPerformed(e);
                }
            });

            menuOptionsTimer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuOptionsTimerActionPerformed(e);
                }
            });

            menuItemAutoStep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stepAction(e);
                }
            });
            menuItemStopAuto.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopActions();
                }
            });
            menuItemStep.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stepAction(e);
                }
            });
            menuItemReset.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetActions();
                }
            });
            menuItemQuit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            menuItemAbout.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    menuAboutAction(e);
                }
            });

            autoStepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    autoActions();
                }
            });


            stepButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stepAction(e);
                }
            });

            stopAutoButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopActions();
                }
            });

            resetButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    resetActions();
                }
            });


            setLayout(new FlowLayout());
            menuGame.add(menuItemAutoStep);
            menuGame.add(menuItemStep);
            menuGame.add(menuItemReset);
            menuGame.add(menuItemQuit);
            menuBarGoL.add(menuGame);
            menuOptions.add(menuOptionsColor);
            menuOptions.add(menuOptionsSize);
            menuOptions.add(menuOptionsTimer);
            menuBarGoL.add(menuOptions);
            menuHelp.add(menuItemAbout);
            menuBarGoL.add(menuHelp);
            setJMenuBar(menuBarGoL);
            getContentPane().add(golGrid);
            buttonsPanel.setLayout(new GridLayout(0, 1, 0, 2));
            buttonsPanel.add(autoStepButton);
            buttonsPanel.add(stopAutoButton);
            buttonsPanel.add(stepButton);
            buttonsPanel.add(resetButton);
            buttonsPanel.add(generationLabel);
            buttonsPanel.add(generationValueTF);
            buttonsPanel.add(populationLabel);
            buttonsPanel.add(populationValueTF);
            getContentPane().add(buttonsPanel);

            pack();

        } catch (GridException ex) {
            System.out.println(ex.getMessage());
        }


    }

    /**
     * Opens a dialog window for board size customisation
     *
     * @param evt the action event
     */
    private void menuOptionsSizeAction(java.awt.event.ActionEvent evt) {

        GridSize t = new GridSize(this, true);
        t.setVisible(true);
        stopActions();
    }

    /**
     * Opens a dialog window for colour customisation of the cells
     * An exception is thrown if the chosen colour is the same as the current colour
     *
     * @param evt the action event
     */
    private void menuOptionsColourActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Color cl = JColorChooser.showDialog(this, "Cell color", getGolGrid().getAliveCellColor());
            getGolGrid().setAliveCellColor(cl);
        } catch (GridException pve) {
            JOptionPane.showMessageDialog(this,
                    pve.getMessage(),
                    "Error!",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
        }

    }


    /**
     * Closes the application
     *
     * @param evt the action event
     */
    private void menuQuitAction(ActionEvent evt) {
        System.exit(0);
    }

    /**
     * From the menu:
     * - Play one step of the evolution
     * - Deactivate cells
     *
     * @param evt the event
     */
    private void menuStepActionPerformed(java.awt.event.ActionEvent evt) {

        getGolGrid().setUnlockedCells(false);
        getGolGrid().evolution();
    }

    /**
     * From the menu:
     * - Play the simulation
     * - Start timer
     *
     * @param evt the action event
     */
    private void menuAutoStepActionPerformed(java.awt.event.ActionEvent evt) {

        autoActions();
    }

    /**
     * From the menu:
     * - Stop simulation
     * - Stop timer
     *
     * @param evt the action event
     */
    private void menuStopAutoActionPerformed(java.awt.event.ActionEvent evt) {
        stopActions();
    }


    /**
     * From the menu:
     * - Display about dialog
     *
     * @param evt the action event
     */
    private void menuAboutAction(ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "Game of Life (Cellular Automata)\n" +
                        "by G29740\n" +
                        "2009",
                "About",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Display dialog for timer configuration
     *
     * @param evt the action event
     */
    private void menuOptionsTimerActionPerformed(java.awt.event.ActionEvent evt) {
        GoLTimer tt = new GoLTimer(this, true);
        tt.setVisible(true);
    }

    /**
     * Enable/disable screen buttons
     *
     * @param step Step button boolean
     * @param auto Auto button boolean
     * @param stop Stop button boolean
     */
    private void setButtons(boolean step, boolean auto, boolean stop) {
        stepButton.setEnabled(step);
        menuItemStep.setEnabled(step);
        autoStepButton.setEnabled(auto);
        menuItemAutoStep.setEnabled(auto);
        stopAutoButton.setEnabled(stop);
        menuItemStopAuto.setEnabled(stop);
    }

    /**
     * Start simulation with given timer value
     */
    private void autoActions() {
        setButtons(false, false, true);
        getTimer().start();
        getGolGrid().setUnlockedCells(false);
    }

    /**
     * Play one step of the evolution and reactivate cells
     *
     * @param evt the action event
     */
    private void stepAction(java.awt.event.ActionEvent evt) {
        getGolGrid().setUnlockedCells(true);
        getGolGrid().evolution();
        generationValueTF.setText("" + getGolGrid().getGenerationNb());
        populationValueTF.setText("" + getGolGrid().getPopulation());
    }

    /**
     * Stop the simulation and the timer and reactivate cells
     */
    private void stopActions() {
        getGolGrid().setUnlockedCells(true);
        setButtons(true, true, false);
        getTimer().stop();
    }

    /**
     * Reset the grid as well as the following:
     * - Stop the timer
     * - Re-enable cell selection
     * - Kill all cells
     * - Reset generation and population values
     * - Enable/disable buttons accordingly
     */
    private void resetActions() {
        generationValueTF.setText("0");
        populationValueTF.setText("0");
        setButtons(true, true, false);
        getTimer().stop();
        getGolGrid().killCells();
    }

    /**
     * Return the grid
     *
     * @return the grid
     */
    public Grid getGolGrid() {
        return golGrid;
    }

    /**
     * Return the timer
     *
     * @return the timer
     */
    public Timer getTimer() {
        return timer;
    }
}
