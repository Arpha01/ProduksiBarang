/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.PemasukkanController;
import javax.swing.JOptionPane;

/**
 *
 * @author mucha
 */
public class FormBarangMasuk extends javax.swing.JInternalFrame {
    private final PemasukkanController pemasukkanController = new PemasukkanController();
    private final PesanDialog pesanDialog = new PesanDialog();
    /**
     * Creates new form FormBarangMasuk
     */
    public FormBarangMasuk() {
        initComponents();
        
        tglProduksiTextField.setText(pemasukkanController.getTanggalSekarang());
    }
    
    public void setNo_masuk(String no_masuk){
        no_masukTextField.setText(no_masuk);
    }
    
    public String getNo_masuk(){
        return no_masukTextField.getText();
    }
    
    public void setNo_barang(String no_barang){
        no_barangTextField.setText(no_barang);
    }
    
    public String getNo_barang(){
        return no_barangTextField.getText();
    }
    
    public void setTgl_produksi(String tgl_produksi){
        tglProduksiTextField.setText(tgl_produksi);
    }
    
    public String getTgl_produksi(){
        return tglProduksiTextField.getText();
    }
    
    public void setNama(String nama){
        namaTextField.setText(nama);
    }
    
    public String getNama(){
        return namaTextField.getText();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        no_masukTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        no_barangTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        namaTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tglProduksiTextField = new javax.swing.JTextField();
        otomatisCheckBox = new javax.swing.JCheckBox();
        lihatPemasukkanButton = new javax.swing.JButton();
        lihatBarangButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        simpanButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        tutupButton = new javax.swing.JButton();

        setClosable(true);
        setTitle("Barang Masuk");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Data Barang Masuk"));

        jLabel1.setText("No. Pemasukkan");

        jLabel2.setText("No. Barang");

        no_barangTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                no_barangTextFieldFocusLost(evt);
            }
        });
        no_barangTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                no_barangTextFieldPropertyChange(evt);
            }
        });

        jLabel3.setText("Nama Barang");

        namaTextField.setEditable(false);

        jLabel6.setText("Tanggal Produksi");

        tglProduksiTextField.setEditable(false);

        otomatisCheckBox.setSelected(true);
        otomatisCheckBox.setText("Isi Otomatis");
        otomatisCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                otomatisCheckBoxItemStateChanged(evt);
            }
        });
        otomatisCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                otomatisCheckBoxStateChanged(evt);
            }
        });

        lihatPemasukkanButton.setText("LIhat");
        lihatPemasukkanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatPemasukkanButtonActionPerformed(evt);
            }
        });

        lihatBarangButton.setText("Lihat");
        lihatBarangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatBarangButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tglProduksiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(otomatisCheckBox))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(no_barangTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(no_masukTextField, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lihatPemasukkanButton)
                            .addComponent(lihatBarangButton))))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(no_masukTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(lihatPemasukkanButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(no_barangTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lihatBarangButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tglProduksiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(otomatisCheckBox))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        simpanButton.setText("Simpan");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        hapusButton.setText("Hapus");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        tutupButton.setText("Tutup");
        tutupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tutupButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(simpanButton)
                .addGap(124, 124, 124)
                .addComponent(hapusButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tutupButton)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanButton)
                    .addComponent(hapusButton)
                    .addComponent(tutupButton))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void otomatisCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_otomatisCheckBoxItemStateChanged
        // TODO add your handling code here:
        if(otomatisCheckBox.isSelected()){
            tglProduksiTextField.setEditable(false);
            tglProduksiTextField.setText(pemasukkanController.getTanggalSekarang());
        }else{
            tglProduksiTextField.setEditable(true);
            tglProduksiTextField.setText("");
        }
        
    }//GEN-LAST:event_otomatisCheckBoxItemStateChanged

    private void otomatisCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_otomatisCheckBoxStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_otomatisCheckBoxStateChanged

    private void tutupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tutupButtonActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_tutupButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
         if (pesanDialog.tampilkanPilihan("Apakah anda yakin ingin menghapus " +getNo_masuk() + "?","Konfirmasi", new Object[]{"Ya","Tidak"}) == 0){
             pemasukkanController.hapus(no_masukTextField);
         }
         
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void lihatPemasukkanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatPemasukkanButtonActionPerformed
        // TODO add your handling code here:
        pemasukkanController.tampilkanFormLihatBarangMasuk();
    }//GEN-LAST:event_lihatPemasukkanButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        // TODO add your handling code here:
        pemasukkanController.simpan(no_masukTextField, no_barangTextField, tglProduksiTextField);
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void lihatBarangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatBarangButtonActionPerformed
        // TODO add your handling code here:
        pemasukkanController.tampilkanFormLihatBarang();
    }//GEN-LAST:event_lihatBarangButtonActionPerformed

    private void no_barangTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_no_barangTextFieldFocusLost
        // TODO add your handling code here:
        if(!no_barangTextField.getText().equals("")){
            try{
                pemasukkanController.cariBarang(no_barangTextField);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Barang dengan kode " + getNo_barang() + " tidak ditemukan" );
            }
        }
    }//GEN-LAST:event_no_barangTextFieldFocusLost

    private void no_barangTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_no_barangTextFieldPropertyChange

    }//GEN-LAST:event_no_barangTextFieldPropertyChange


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hapusButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton lihatBarangButton;
    private javax.swing.JButton lihatPemasukkanButton;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JTextField no_barangTextField;
    private javax.swing.JTextField no_masukTextField;
    private javax.swing.JCheckBox otomatisCheckBox;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTextField tglProduksiTextField;
    private javax.swing.JButton tutupButton;
    // End of variables declaration//GEN-END:variables
}
