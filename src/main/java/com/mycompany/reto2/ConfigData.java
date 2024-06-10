/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto2;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author profesor3
 */
public class ConfigData {
    Connection connection = null;
    String username;
    String password;
    String bd = "bd_bongosquad";
    String ip = "192.168.0.10";
    String puerto = "3306";
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    // Array de Empresas
    ArrayList<String> empresas = new ArrayList<>(Arrays.asList(
        "480", "InnovateLtd", "Lego", "Orbyz", "Porcelanosa"
    ));

    // ArrayList de Ciclos Formativos
    ArrayList<String> ciclosFormativos = new ArrayList<>(Arrays.asList(
        "DAW", "DAM", "ASIR", "SMR", "AF"
    ));

    // ArrayList de Grupos
    ArrayList<Integer> grupos = new ArrayList<>(Arrays.asList(
        1, 2, 3, 4, 5
    ));
    
  
}
