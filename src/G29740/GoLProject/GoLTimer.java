package G29740.GoLProject;

import java.awt.Frame;

/**
 * Frame allowing user to modify the timer speed
 *
 * @author G29740
 */
public class GoLTimer extends javax.swing.JDialog {
    // Variables declaration
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonOk;
    private javax.swing.JSlider sliderTimer;
    private GoLMain golMain;

    /**
     * Create the frame with a slider and 2 buttons
     * The slider is set to the current time speed
     *
     * @param parent The parent frame
     * @param modal Specifies if current frame is modal or not
     */
    public GoLTimer(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        golMain = (GoLMain) parent;
        sliderTimer.setValue(golMain.getTimer().getDelay());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     */
    @SuppressWarnings("unchecked")
    private void initComponents() {

        buttonOk = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        sliderTimer = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Timer delay");
        setResizable(false);

        buttonOk.setText("OK");
        buttonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOkActionPerformed(evt);
            }
        });

        buttonCancel.setText("Annuler");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        sliderTimer.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        sliderTimer.setMajorTickSpacing(100);
        sliderTimer.setMaximum(500);
        sliderTimer.setMinimum(100);
        sliderTimer.setMinorTickSpacing(25);
        sliderTimer.setPaintLabels(true);
        sliderTimer.setPaintTicks(true);
        sliderTimer.setSnapToTicks(true);
        sliderTimer.setValue(200);
        sliderTimer.setBorder(javax.swing.BorderFactory.createTitledBorder("Delay"));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sliderTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(buttonOk, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonCancel, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(sliderTimer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(buttonOk)
                                        .addComponent(buttonCancel))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    /**
     * Set timer speed with the given value
     *
     * @param evt the action event
     */
    private void buttonOkActionPerformed(java.awt.event.ActionEvent evt) {
        golMain.getTimer().setDelay(sliderTimer.getValue());
        dispose();
    }

    /**
     * Close the dialog without changing anything
     *
     * @param evt the action event
     */
    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        dispose();
    }
}