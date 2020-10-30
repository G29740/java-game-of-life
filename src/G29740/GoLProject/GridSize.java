package G29740.GoLProject;

import javax.swing.*;
import java.awt.Frame;
import java.awt.event.*;

/**
 * GoL grid resize window
 *
 * @author G29740
 */
public class GridSize extends javax.swing.JDialog {

    // Variables declaration
    private JButton cancelButton;
    private JButton okButton;
    private JSlider columnSlider;
    private JSlider rowSlider;
    private GoLMain golMain;

    /**
     * Create the frame with 2 sliders and 2 buttons
     * The sliders are set to the current grid size (row/column)
     *
     * @param parent The parent frame
     * @param modal Specifies if current frame is modal or not
     */
    public GridSize(Frame parent, boolean modal) {
        super(parent, modal);
        golMain = (GoLMain) parent;
        initComponents();
        setLocationRelativeTo(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
    */
    private void initComponents() {

        okButton = new JButton();
        cancelButton = new JButton();
        rowSlider = new JSlider();
        columnSlider = new JSlider();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Change grid size");

        okButton.setText("OK");
        okButton.addActionListener(evt -> okButtonActionPerformed(evt));

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(evt -> cancelButtonActionPerformed(evt));

        rowSlider.setMajorTickSpacing(10);
        rowSlider.setMaximum(45);
        rowSlider.setMinimum(5);
        rowSlider.setMinorTickSpacing(5);
        rowSlider.setPaintLabels(true);
        rowSlider.setPaintTicks(true);
        rowSlider.setSnapToTicks(true);
        rowSlider.setValue(golMain.getGolGrid().getRowCount());
        rowSlider.setBorder(BorderFactory.createTitledBorder("Rows"));

        columnSlider.setMajorTickSpacing(10);
        columnSlider.setMaximum(45);
        columnSlider.setMinimum(5);
        columnSlider.setMinorTickSpacing(5);
        columnSlider.setPaintLabels(true);
        columnSlider.setPaintTicks(true);
        columnSlider.setSnapToTicks(true);
        columnSlider.setValue(golMain.getGolGrid().getColumnCount());
        columnSlider.setBorder(BorderFactory.createTitledBorder("Columns"));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(columnSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(rowSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(okButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cancelButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(rowSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(columnSlider, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(58, 58, 58)
                                                .addComponent(okButton)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cancelButton)))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    /**
     * Resize the grid with the new parameters
     *
     * @param evt the action event
     */
    private void okButtonActionPerformed(ActionEvent evt) {
        try {
            golMain.getGolGrid().createGrid(rowSlider.getValue(), columnSlider.getValue());
            golMain.getGolGrid().setUnlockedCells(true);
            golMain.pack();
        } catch (GridException ex) {
            ex.printStackTrace();
        }
        dispose();
    }

    /**
     * Close the dialog without changing anything
     *
     * @param evt the action event
     */
    private void cancelButtonActionPerformed(ActionEvent evt) {
        dispose();
    }
}
