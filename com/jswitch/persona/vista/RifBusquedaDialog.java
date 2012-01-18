

package com.jswitch.persona.vista;

import java.awt.Dimension;
import java.awt.Toolkit;
import com.jswitch.base.modelo.Dominios;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.maestra.Rif;
import org.hibernate.Query;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.FormController;
import org.openswing.swing.mdi.client.MDIFrame;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.java.Consts;

public class RifBusquedaDialog extends javax.swing.JDialog {

    private BeanVO beanVO;
    private final String[] attName;
    private final String[] entityName;

    public RifBusquedaDialog() {
        this(null, null);
    }

    public RifBusquedaDialog(String[] entityName, String[] attName) {
        super(MDIFrame.getInstance(), true);
        initComponents();
        this.attName = attName;
        this.entityName = entityName;
        saveButton1.setIcon(jButton1.getIcon());
        jButton1.setVisible(false);
        form1.setFormController(new RifDialogController());
        form1.setMode(Consts.INSERT);
        this.getRootPane().setDefaultButton(saveButton1);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(((int) d.getWidth() - this.getWidth()) / 2, ((int) d.getHeight() - this.getHeight()) / 2);
    }

    public BeanVO getBenVO() {
        setVisible(true);
        return beanVO;
    }

    public Rif getRif() {
        return (Rif) form1.getVOModel().getValueObject();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        form1 = new org.openswing.swing.form.client.Form();
        jButton1 = new javax.swing.JButton();
        cedula_lab = new org.openswing.swing.client.LabelControl();
        numericControl2 = new org.openswing.swing.client.NumericControl();
        tipoCedula = new org.openswing.swing.client.ComboBoxControl();
        tipoCedula_lab = new org.openswing.swing.client.LabelControl();
        labelControl1 = new org.openswing.swing.client.LabelControl();
        textControl1 = new org.openswing.swing.client.TextControl();
        jButton2 = new javax.swing.JButton();
        saveButton1 = new org.openswing.swing.client.SaveButton();
        numericControl1 = new org.openswing.swing.client.NumericControl();
        labelControl2 = new org.openswing.swing.client.LabelControl();
        labelControl3 = new org.openswing.swing.client.LabelControl();
        textControl2 = new org.openswing.swing.client.TextControl();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Rif");
        setResizable(false);

        form1.setVOClassName(Rif.class.getName());
        form1.setSaveButton(saveButton1);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check1.png"))); // NOI18N
        jButton1.setPreferredSize(new java.awt.Dimension(33, 33));

        cedula_lab.setLabel("numeroCedula");

        numericControl2.setAttributeName("numeroCedula");
        numericControl2.setLinkLabel(cedula_lab);
        numericControl2.setMaxCharacters(8);
        numericControl2.setMaxValue(9.9999999E7);
        numericControl2.setRequired(true);
        numericControl2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numericControl2KeyReleased(evt);
            }
        });

        tipoCedula.setAttributeName("tipoCedula");
        tipoCedula.setDomainId(Dominios.TipoCedula().getDomainId());
        tipoCedula.setLinkLabel(tipoCedula_lab);
        tipoCedula.setRequired(true);
        tipoCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoCedulaActionPerformed(evt);
            }
        });

        tipoCedula_lab.setLabel("tipoCedula");

        labelControl1.setLabel("cedula");

        textControl1.setAttributeName("cedulaCompleta");
        textControl1.setEnabledOnEdit(false);
        textControl1.setEnabledOnInsert(false);
        textControl1.setLinkLabel(labelControl1);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cancel2.png"))); // NOI18N
        jButton2.setPreferredSize(new java.awt.Dimension(33, 33));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        numericControl1.setAttributeName("finRif");
        numericControl1.setColumns(1);
        numericControl1.setLinkLabel(labelControl2);
        numericControl1.setMaxCharacters(1);
        numericControl1.setMaxValue(9.0);
        numericControl1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                numericControl1KeyReleased(evt);
            }
        });

        labelControl2.setLabel("-");

        labelControl3.setLabel("rif");

        textControl2.setAttributeName("rif");
        textControl2.setEnabledOnEdit(false);
        textControl2.setEnabledOnInsert(false);

        javax.swing.GroupLayout form1Layout = new javax.swing.GroupLayout(form1);
        form1.setLayout(form1Layout);
        form1Layout.setHorizontalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(form1Layout.createSequentialGroup()
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoCedula_lab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cedula_lab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoCedula, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(textControl1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form1Layout.createSequentialGroup()
                                .addComponent(numericControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, form1Layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 181, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(form1Layout.createSequentialGroup()
                        .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textControl2, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cedula_lab, labelControl1, labelControl3, tipoCedula_lab});

        form1Layout.setVerticalGroup(
            form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(form1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoCedula_lab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoCedula, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cedula_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numericControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelControl3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textControl2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(form1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        form1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cedula_lab, labelControl1, labelControl2, labelControl3, numericControl1, numericControl2, textControl1, textControl2, tipoCedula, tipoCedula_lab});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(form1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(form1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tipoCedulaActionPerformed(java.awt.event.ActionEvent evt) {
        if (tipoCedula.getSelectedIndex() == 6 || tipoCedula.getSelectedIndex() == 7) {
            numericControl2.setEnabled(false);
            numericControl1.setEnabled(false);
            numericControl1.setValue(null);
            numericControl2.setText("0");
        } else {
            numericControl2.setEnabled(true);
            numericControl1.setEnabled(true);
        }
        doCedulaCompleta();
    }

    public void doCedulaCompleta() {
        if (tipoCedula.getSelectedIndex() != -1) {
            String s;
            if (numericControl2.getBigDecimal() != null) {
                s = numericControl2.getBigDecimal().toString();
            } else {
                s = "";
            }
            Character in = Dominios.TipoCedula.values()[tipoCedula.getSelectedIndex()].getIn();
            textControl1.setText(in + s);
            StringBuilder sb = new StringBuilder("00000000");
            sb.replace(8 - s.length(), 8, s);
            String digFin = "";
            if (numericControl1.getBigDecimal() != null) {
                digFin = "-" + numericControl1.getBigDecimal().toString();
            }
            textControl2.setText(in + sb.toString() + digFin);
        }
    }

    private void numericControl2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numericControl2KeyReleased
        doCedulaCompleta();
    }//GEN-LAST:event_numericControl2KeyReleased

    private void numericControl1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_numericControl1KeyReleased
        doCedulaCompleta();
    }//GEN-LAST:event_numericControl1KeyReleased
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.openswing.swing.client.LabelControl cedula_lab;
    private org.openswing.swing.form.client.Form form1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private org.openswing.swing.client.LabelControl labelControl1;
    private org.openswing.swing.client.LabelControl labelControl2;
    private org.openswing.swing.client.LabelControl labelControl3;
    private org.openswing.swing.client.NumericControl numericControl1;
    private org.openswing.swing.client.NumericControl numericControl2;
    private org.openswing.swing.client.SaveButton saveButton1;
    private org.openswing.swing.client.TextControl textControl1;
    private org.openswing.swing.client.TextControl textControl2;
    private org.openswing.swing.client.ComboBoxControl tipoCedula;
    private org.openswing.swing.client.LabelControl tipoCedula_lab;
    // End of variables declaration//GEN-END:variables

    class RifDialogController extends FormController {

        @Override
        public Response insertRecord(ValueObject newPersistentObject) throws Exception {
            Session s = null;
            if (tipoCedula.getSelectedIndex() <= 5) {

                s = HibernateUtil.getSessionFactory().openSession();
                Query q = null;
                beanVO = null;
                if (attName != null && entityName != null) {
                    for (int i = 0; i < attName.length && beanVO == null; i++) {
                        q = s.createQuery("FROM " + entityName[i]
                                + " WHERE " + attName[i] + ".rif.rif=?").
                                setString(0, textControl2.getText());
                        beanVO = (BeanVO) q.uniqueResult();
                    }
                }
                if (beanVO == null) {
                    q = s.createQuery("FROM " + Persona.class.getName()
                            + " WHERE rif.rif=?").
                            setString(0, textControl2.getText());
                    beanVO = (BeanVO) q.uniqueResult();
                }


            }
            s.close();
            RifBusquedaDialog.this.dispose();
            return new VOResponse(newPersistentObject);

        }
    }
}
