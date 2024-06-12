/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.reto2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author profesor3
 */
public class MyConnection1 {

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

    public void tryQuery() { //Esto es una consulta temporal, lo tendremos de modelo
        Connection conn = makeConection();
        if (conn != null) {
            try {
                String query = "SELECT nombre FROM profesor";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    System.out.println("Nombre: " + nombre);
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
    }

    public void tryQuery2() { //Esto es una consulta temporal, lo tendremos de modelo
        Connection conn = makeConection();
        if (conn != null) {
            try {
                String query = "SELECT apellidos FROM profesor";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String nombre = rs.getString("apellidos");
                    System.out.println("Nombre: " + nombre);
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
    }

    public void tryQuery3() { //Esto es una consulta temporal, lo tendremos de modelo
        Connection conn = makeConection();
        if (conn != null) {
            try {
                String query = "INSERT INTO prevision_fct(idempresa,idciclo,cursoescolar,solicitaAlu,acogeAlu,totalSoli)"
                        + "VALUES(1,2,'2023-2024',40,0,40)";
                Statement stmt = conn.createStatement();
                int rs = stmt.executeUpdate(query);

                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
    }

    public String[] tryQuery4() { // Combobox de empresas
        Connection conn = makeConection();
        List<String> Empresas = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT nombre FROM empresa";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    Empresas.add(nombre);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return Empresas.toArray(new String[0]);
    }

    public String[] tryQuery5() { // Combobox de ciclo
        Connection conn = makeConection();
        List<String> Ciclo = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT DISTINCT ciclo FROM ciclo";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String cicloStr = rs.getString("ciclo");
                    Ciclo.add(cicloStr);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return Ciclo.toArray(new String[0]);
    }

    public String[] tryQuery6() { // Combobox de grupo
        Connection conn = makeConection();
        List<String> Grupo = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT idGrupo FROM grupo";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String idGrupoStr = rs.getString("idGrupo");
                    Grupo.add(idGrupoStr);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return Grupo.toArray(new String[0]);
    }

    public boolean verificarCredenciales(String nombre, String clave) {
        boolean verificacion = false;
        Connection conn = makeConection();
        if (conn != null) {
            try {
                String query = "SELECT nombre, contrasena FROM credenciales";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String nombre1 = rs.getString("nombre");
                    String clave1 = rs.getString("contrasena");
                    if (nombre.equals(nombre1) && clave.equals(clave1)) {
                        verificacion = true;
                        break;
                    }
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return verificacion;
    }

    public List<String> getCompaniesDBData() {
        Connection conn = makeConection();
        List<String> data = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT "
                        + "e.nombre AS company_name, "
                        + "s.descripcion AS sector, "
                        + "rf.num_alu_asignados AS fct_real_assigned_students, "
                        + "pf.solicitaAlu AS available_places_for_students, "
                        + "pf.totalSoli AS total_requests "
                        + "FROM "
                        + "empresa e "
                        + "LEFT JOIN sector s ON e.idSector = s.idSector "
                        + "LEFT JOIN realizan_fct rf ON e.idempresa = rf.idempresa "
                        + "LEFT JOIN prevision_fct pf ON e.idempresa = pf.idempresa "
                        + "ORDER BY e.idempresa;";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String line = rs.getString("company_name")
                            + " || " + rs.getString("sector")
                            + " || " + rs.getString("fct_real_assigned_students")
                            + " || " + rs.getString("available_places_for_students")
                            + " || " + rs.getString("total_requests");
                    data.add(line);
                }

                stmt.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
                }
            }
        }
        return data;
    }

    public List<String> getCompanyDBData(int index) {
        Connection conn = makeConection();
        List<String> data = new ArrayList<>();

        if (conn != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                String query = "SELECT "
                        + "e.nombre AS company_name, "
                        + "s.descripcion AS sector, "
                        + "rf.num_alu_asignados AS fct_real_assigned_students, "
                        + "pf.solicitaAlu AS available_places_for_students, "
                        + "pf.totalSoli AS total_requests "
                        + "FROM "
                        + "empresa e "
                        + "LEFT JOIN sector s ON e.idSector = s.idSector "
                        + "LEFT JOIN realizan_fct rf ON e.idempresa = rf.idempresa "
                        + "LEFT JOIN prevision_fct pf ON e.idempresa = pf.idempresa "
                        + "WHERE e.idempresa = ? "
                        + "ORDER BY e.idempresa;";

                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, index);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    String line = rs.getString("company_name")
                            + " || " + rs.getString("sector")
                            + " || " + rs.getString("fct_real_assigned_students")
                            + " || " + rs.getString("available_places_for_students")
                            + " || " + rs.getString("total_requests");
                    data.add(line);
                }

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error al cerrar la conexión: " + e.toString());
                }
            }
        }
        return data;
    }

    public void insertCompanyData(List<String> companyInfo) {
        Connection conn = makeConection();

        if (conn != null) {
            PreparedStatement pstmt = null;
            try {
                // Insert into empresa
                String query = "INSERT INTO empresa (nombre, idSector) VALUES (?, ?);";
                pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                pstmt.setString(1, companyInfo.get(0)); // Company name
                pstmt.setInt(2, getSectorId(companyInfo.get(1))); // Sector ID
                pstmt.executeUpdate();

                ResultSet generatedKeys = pstmt.getGeneratedKeys();
                int companyId = -1;
                if (generatedKeys.next()) {
                    companyId = generatedKeys.getInt(1);
                }

                if (companyId != -1) {
                    // Insert into realizan_fct
                    query = "INSERT INTO realizan_fct (idempresa, idgrupo, cursoescolar, periodo, num_alu_asignados) VALUES (?, ?, ?, ?, ?);";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, companyId); // Company ID
                    pstmt.setInt(2, Integer.parseInt(companyInfo.get(2))); // Group ID
                    pstmt.setString(3, null); // Academic year
                    pstmt.setString(4, null); // Period
                    pstmt.setInt(5, Integer.parseInt(companyInfo.get(5))); // Number of assigned students
                    pstmt.executeUpdate();

                    // Insert into prevision_fct
                    query = "INSERT INTO prevision_fct (idempresa, idciclo, cursoescolar, periodo, solicitaAlu, acogeAlu) VALUES (?, ?, ?, ?, ?, ?);";
                    pstmt = conn.prepareStatement(query);
                    pstmt.setInt(1, companyId); // Company ID
                    pstmt.setInt(2, Integer.parseInt(companyInfo.get(6))); // Cycle ID
                    pstmt.setString(3, companyInfo.get(7)); // Academic year
                    pstmt.setString(4, companyInfo.get(8)); // Period
                    pstmt.setBoolean(5, Boolean.parseBoolean(companyInfo.get(9))); // Solicits students
                    pstmt.setBoolean(6, Boolean.parseBoolean(companyInfo.get(10))); // Accommodates students
                    pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error inserting data: " + e.toString());
            } finally {
                try {
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error closing connection: " + e.toString());
                }
            }
        }
    }

    // Helper method to get the sector ID
    private int getSectorId(String sectorDescription) {
        Connection conn = makeConection();
        int sectorId = -1;
        if (conn != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                String query = "SELECT idSector FROM sector WHERE descripcion = ?;";
                pstmt = conn.prepareStatement(query);
                pstmt.setString(1, sectorDescription);
                rs = pstmt.executeQuery();
                if (rs.next()) {
                    sectorId = rs.getInt("idSector");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error fetching sector ID: " + e.toString());
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (pstmt != null) {
                        pstmt.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error closing connection: " + e.toString());
                }
            }
        }
        return sectorId;
    }
}
