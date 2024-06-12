/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Administrador
 */
public class MyConnection2 {
    public static Connection connection = null;
    public static String usuario = "root";
    public static String contraseña = "Virtual01";
    public static String bd = "bd_bongosquad";
    public static String ip = "192.168.0.10";
    public static String puerto = "3306";
    public static String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;

    public Connection makeConection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(cadena, usuario, contraseña);
        } catch (Exception e) {
            JOptionPane.showInternalMessageDialog(null, "No se pudo conectar" + e.toString());
        }
        return connection;
    }
    
    public String[] listaTec() {
        Connection conn = makeConection();
        List<String> Tec = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT DISTINCT descripcion FROM tecnologia";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String tec = rs.getString("descripcion");
                    Tec.add(tec);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return Tec.toArray(new String[0]);
    }
    
    public String[] empresaDesdeTecnologia(String tecnologia) {
        Connection conn = makeConection();
        List<String> Empresa = new ArrayList<>();
        if (conn != null) {
            try {
                String query = "SELECT empresa.nombre as Empresa "
                        + "FROM empresa "
                        + "JOIN empresa_tecno ON empresa.idempresa = empresa_tecno.idempresa "
                        + "JOIN tecnologia ON empresa_tecno.idtecno = tecnologia.idTecno "
                        + "WHERE tecnologia.descripcion = '" + tecnologia + "'; ";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String empresa = rs.getString("Empresa");
                    Empresa.add(empresa);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return Empresa.toArray(new String[0]);
    }
    
    public List<String> informacionEmpresaYCicloSelecionado(String empresaSelec, String cicloSelec) {
        Connection conn = makeConection();
        List<String> datos = new ArrayList<>();
        String numAlus = "";
        String sDes = "";
        String tec = "";
        if (conn != null) {
            try {
                String query = "SELECT realizan_fct.num_alu_asignados AS NumAlus, sector.descripcion AS SDes, tecnologia.descripcion as Tec "
                        + "FROM realizan_fct "
                        + "JOIN empresa ON empresa.idempresa = realizan_fct.idempresa "
                        + "JOIN sector ON empresa.idSector = sector.idSector "
                        + "JOIN empresa_tecno ON empresa.idempresa = empresa_tecno.idempresa "
                        + "JOIN tecnologia ON empresa_tecno.idtecno = tecnologia.idTecno "
                        + "JOIN grupo ON grupo.idGrupo = realizan_fct.idgrupo "
                        + "JOIN ciclo ON ciclo.idCiclo = grupo.idCiclo "
                        + "WHERE empresa.nombre = '" + empresaSelec + "' and ciclo.ciclo = '" + cicloSelec + "'; ";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    numAlus = rs.getString("NumAlus");
                    System.out.println("Num alus = " + numAlus);
                    sDes = rs.getString("sDes");
                    System.out.println("sDes = " + sDes);
                    tec = rs.getString("tec");
                    System.out.println("tec = " + tec);
                    datos.add(numAlus);
                    datos.add(sDes);
                    datos.add(tec);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        for (String dato : datos) {
            System.out.println(dato);
        }
        return datos;
    }
}