/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;
import controller.PengeluaranController;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime; 
import java.time.format.DateTimeFormatter;     
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author mucha
 */
public class FormBarangKeluar extends javax.swing.JInternalFrame {
    private final DefaultTableModel barangKeluarTableModel;
    private final PengeluaranController pengeluaranController = new PengeluaranController();
    /**
     * Creates new form FormBarangKeluar
     */
    public FormBarangKeluar() {
        initComponents();
        
        barangKeluarTableModel = (DefaultTableModel) barangKeluarTable.getModel();
        tglKeluarTextField.setText(pengeluaranController.getTanggalSekarang());
    }
    
    public void setEnableButtonSimpan(boolean enabled){
        simpanButton.setEnabled(enabled);
    }
    
    public boolean getEnableButtonSimpan(){
        return simpanButton.isEnabled();
    }
    
    public void setTglKeluar(String tgl){
        tglKeluarTextField.setText(tgl);
    }
    
    public String getTglKeluar(){
        return tglKeluarTextField.getText();
    }
    
    public String getKodeBarang(){
        return kodeBarangTextField.getText();
    }
    
    public void setKodeBarang(String kd_barang){
        kodeBarangTextField.setText(kd_barang);
    }
    
    public String getNamaBarang(){
        return namaTextField.getText();
    }
    
    public void setNamaBarang(String nama){
        namaTextField.setText(nama);
    }
    
    public Integer getJumlah(){
        return Integer.parseInt(jumlahTextField.getText());
    }
    
    public void setJumlah(Integer jumlah){
        jumlahTextField.setText(jumlah.toString());
    }
    
    public Integer getHarga(){
        return Integer.parseInt(hargaTextField.getText());
    }
    
    public void setHarga(Integer harga){
        hargaTextField.setText(harga.toString());
    }
    
    public void bersihkanTableBarangKeluar(){
        barangKeluarTableModel.setRowCount(0);
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
        jScrollPane2 = new javax.swing.JScrollPane();
        barangKeluarTable = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        kodeBarangTextField = new javax.swing.JTextField();
        lihatBarangButton = new javax.swing.JButton();
        namaTextField = new javax.swing.JTextField();
        jumlahTextField = new javax.swing.JTextField();
        hargaTextField = new javax.swing.JTextField();
        simpanButton = new javax.swing.JButton();
        tglKeluarTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lihatTglKeluarButton = new javax.swing.JButton();
        resetTanggalButton = new javax.swing.JButton();

        setClosable(true);
        setTitle("Data barang keluar");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Barang keluar"));

        barangKeluarTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "No. Keluar", "Tgl Produksi", "Kode Supplier", "Nama Supplier", "Jumlah"
            }
        ));
        barangKeluarTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                barangKeluarTablePropertyChange(evt);
            }
        });
        barangKeluarTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barangKeluarTableKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(barangKeluarTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Kode Barang");

        jLabel2.setText("Nama");

        jLabel5.setText("Jumlah");

        jLabel6.setText("Harga");

        lihatBarangButton.setText("Lihat");
        lihatBarangButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatBarangButtonActionPerformed(evt);
            }
        });

        namaTextField.setEditable(false);

        jumlahTextField.setEditable(false);

        hargaTextField.setEditable(false);
        hargaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(namaTextField)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(kodeBarangTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lihatBarangButton, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jumlahTextField)
                    .addComponent(hargaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(111, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jumlahTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(hargaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(kodeBarangTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lihatBarangButton))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 24, Short.MAX_VALUE))
        );

        simpanButton.setText("Simpan");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        tglKeluarTextField.setEditable(false);

        jLabel3.setText("Tgl_keluar");

        lihatTglKeluarButton.setText("Lihat");
        lihatTglKeluarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lihatTglKeluarButtonActionPerformed(evt);
            }
        });

        resetTanggalButton.setText("Reset Tanggal");
        resetTanggalButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetTanggalButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tglKeluarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lihatTglKeluarButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetTanggalButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(simpanButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpanButton)
                    .addComponent(tglKeluarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(lihatTglKeluarButton)
                    .addComponent(resetTanggalButton))
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hargaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaTextFieldActionPerformed

    private void lihatBarangButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatBarangButtonActionPerformed
        // TODO add your handling code here:
        pengeluaranController.tampilkanFormLihatBarang();
    }//GEN-LAST:event_lihatBarangButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        // TODO add your handling code here:
        pengeluaranController.simpan(kodeBarangTextField, tglKeluarTextField, barangKeluarTable);
    }//GEN-LAST:event_simpanButtonActionPerformed
    public void setTambahBarangKeluar(Object[] barangKeluar){
        int noRecord;
         if ((noRecord = barangKeluarTableModel.getRowCount()-1) >= 0){
            while ((((barangKeluarTable.getValueAt(noRecord, 0) == null) || barangKeluarTable.getValueAt(noRecord, 0).equals("")) && (noRecord > 0))){
                noRecord--;
            }
            
            if (!((barangKeluarTable.getValueAt(noRecord, 0) == null) || barangKeluarTable.getValueAt(noRecord, 0).equals(""))){
                noRecord++;
            }
            
            if (noRecord < barangKeluarTableModel.getRowCount()){
                if ((barangKeluarTable.getValueAt(noRecord, 0) == null) || barangKeluarTable.getValueAt(noRecord, 0).equals("")){
                    barangKeluarTableModel.removeRow(noRecord--);
                }
            } else {
                noRecord--;
            }
        }
        
        barangKeluarTableModel.insertRow(++noRecord, barangKeluar);
    }
    private void barangKeluarTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_barangKeluarTablePropertyChange
        // TODO add your handling code here:
        if(barangKeluarTable.getSelectedRowCount()>0){
            if(barangKeluarTable.getSelectedColumn()==2){
                String kodeSupplier = "";
                try{
                    kodeSupplier = barangKeluarTable.getValueAt(barangKeluarTable.getSelectedRow(), 2).toString();
                }catch(Exception ex){}
            
                if(!kodeSupplier.equals("")){
                    pengeluaranController.cariSupplier(kodeSupplier);
                }
            }
        }
    }//GEN-LAST:event_barangKeluarTablePropertyChange

    private void barangKeluarTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barangKeluarTableKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(barangKeluarTable.getSelectedRow() == (barangKeluarTable.getRowCount()-1)){
                if(barangKeluarTable.getSelectedColumn()==0){
                    barangKeluarTableModel.insertRow(barangKeluarTableModel.getRowCount(), new Object[]{});
                }else{
                    if(barangKeluarTable.getSelectedColumn() == (barangKeluarTable.getColumnCount()-1)){
                        barangKeluarTableModel.insertRow(barangKeluarTableModel.getRowCount(), new Object[]{});
                        barangKeluarTable.changeSelection(barangKeluarTable.getSelectedRow(), 0, false, false);
                    }else{
                        barangKeluarTable.changeSelection(barangKeluarTable.getSelectedRow()-1, barangKeluarTable.getSelectedColumn()+1, false, false);
                    }
                }
            }else{
                if(barangKeluarTable.getSelectedColumn() == (barangKeluarTable.getColumnCount()-1)){
                    barangKeluarTable.changeSelection(barangKeluarTable.getSelectedRow(), 0, false, false);
                }else{
                    barangKeluarTable.setColumnSelectionInterval(barangKeluarTable.getSelectedColumn()+1, barangKeluarTable.getSelectedColumn()+1);
                    barangKeluarTable.setRowSelectionInterval(barangKeluarTable.getSelectedRow()-1, barangKeluarTable.getSelectedRow()-1);
                }
            }
        }
    }//GEN-LAST:event_barangKeluarTableKeyPressed

    private void lihatTglKeluarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lihatTglKeluarButtonActionPerformed
        // TODO add your handling code here:
        pengeluaranController.tampilkanFormLihatBarangKeluar();
    }//GEN-LAST:event_lihatTglKeluarButtonActionPerformed

    private void resetTanggalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetTanggalButtonActionPerformed
        // TODO add your handling code here:
       tglKeluarTextField.setText(pengeluaranController.getTanggalSekarang());
       setEnableButtonSimpan(true);
       bersihkanTableBarangKeluar();
       setTambahBarangKeluar(new Object[]{});
       
    }//GEN-LAST:event_resetTanggalButtonActionPerformed
    
    public void setNamaSupplier(String nama){
        barangKeluarTable.setValueAt(nama, barangKeluarTable.getSelectedRow(), 3);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable barangKeluarTable;
    private javax.swing.JTextField hargaTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jumlahTextField;
    private javax.swing.JTextField kodeBarangTextField;
    private javax.swing.JButton lihatBarangButton;
    private javax.swing.JButton lihatTglKeluarButton;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JButton resetTanggalButton;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTextField tglKeluarTextField;
    // End of variables declaration//GEN-END:variables
}
