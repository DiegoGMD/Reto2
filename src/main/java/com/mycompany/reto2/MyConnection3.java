/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author profesor1
 */
public class MyConnection3 {

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

    public String[] incidenciasPorCurso(String cicloSelec) {
        Connection conn = makeConection();
        List<String> datos = new ArrayList<>();
        if (conn != null) {
            try {
                String query = "SELECT incidencia.numincidencia AS NumIncidencia, "
                        + "incidencia.fechaincidencia AS FechaIncidencia, "
                        + "incidencia.observaciones AS Observaciones "
                        + "FROM incidencia "
                        + "JOIN empresa ON incidencia.idempresa = empresa.idempresa "
                        + "JOIN prevision_fct ON empresa.idempresa = prevision_fct.idempresa "
                        + "JOIN ciclo ON prevision_fct.idciclo = ciclo.idCiclo "
                        + "WHERE ciclo.ciclo = '"+ cicloSelec +"';";
                PreparedStatement pstmt = conn.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String numIncidencia = Integer.toString(rs.getInt("NumIncidencia"));
                    String fechaIncidencia = rs.getString("FechaIncidencia");
                    String observaciones = rs.getString("Observaciones");
                    datos.add(numIncidencia);
                    datos.add(fechaIncidencia);
                    datos.add(observaciones);
                }
                rs.close();
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        for (String dato : datos) {
            System.out.println(dato);
        }
        return datos.toArray(new String[0]);
    }
    
    public List<String> datosSolicitadosPorEmpresaYCiclo(String companySelected, String cycleSelected) {
        Connection conn = makeConection();
        List<String> datos = new ArrayList<>();
        if (conn != null) {
            try {
                String query = "SELECT e.nombre AS CompanyName, pf.totalSoli AS TotalSoli, c.ciclo AS Cycle, c.curso AS Year "
                + "FROM empresa e "
                + "JOIN prevision_fct pf ON e.idempresa = pf.idempresa "
                + "JOIN ciclo c ON pf.idciclo = c.idCiclo "
                + "WHERE e.nombre = '"+ companySelected +"' "
                + "AND c.ciclo = '"+ cycleSelected +"' ";

                PreparedStatement pstmt = conn.prepareStatement(query);              
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String companyName = rs.getString("CompanyName");
                    int totalRequests = rs.getInt("TotalSoli");
                    String yearString = rs.getString("Cycle");
                    String cyclesString = rs.getString("Year");
                    datos.add(companyName);
                    datos.add(totalRequests + "");
                    datos.add(cyclesString);
                    datos.add(yearString);
                }
                rs.close();
                pstmt.close();
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
