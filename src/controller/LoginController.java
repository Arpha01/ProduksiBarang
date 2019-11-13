/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.JOptionPane;
import model.Enkripsi;
import view.FormLogin;
import model.Petugas;

/**
 *
 * @author mucha
 */
public class LoginController {
    private final Enkripsi enkripsi = new Enkripsi();
    private final Petugas petugas = new Petugas();
    
    public boolean validasi(javax.swing.JTextField nipTextField, javax.swing.JPasswordField passwordField){
        boolean valid = false, userIdSalah=false;
        String hashedInputPassword = "";
        
        if (!nipTextField.getText().equals("")){       
            if (!valid){
                if (petugas.baca(nipTextField.getText())){
                    try {
                        hashedInputPassword = enkripsi.hashMD5(new String(passwordField.getPassword()));
                    } catch (Exception ex){}
                    
                    if (petugas.getPassword().equalsIgnoreCase(hashedInputPassword)){
                        valid = true;
                        FormLogin.tipe = "Petugas";
                    } else {
                        userIdSalah = true;
                    }
                } else {
                    if (petugas.getPesan().substring(0, 3).equalsIgnoreCase("NIP")){
                        userIdSalah = true;
                    }
                }
                
                if (!valid){
                    if (userIdSalah){
                        JOptionPane.showMessageDialog(null, "User Id atau password salah", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, petugas.getPesan(), "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "User Id (NIM) tidak boleh kosong", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }
        
        return valid;
    }
}
