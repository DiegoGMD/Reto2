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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
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
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                    JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
                }
            }
        }
        return data;
    }

    public List<String> getFCTsDBData() {
        Connection conn = makeConection();
        List<String> data = new ArrayList<>();

        if (conn != null) {
            try {
                String query = "SELECT "
                        + "r.idempresa AS company_id, "
                        + "e.nombre AS company_name, "
                        + "r.idgrupo AS group_id, "
                        + "g.nombre_grupo AS group_name, "
                        + "r.cursoescolar AS course_year, "
                        + "r.num_alu_asignados AS assigned_students, "
                        + "r.estado AS status "
                        + "FROM "
                        + "realizan_fct r "
                        + "LEFT JOIN empresa e ON r.idempresa = e.idempresa "
                        + "LEFT JOIN grupo g ON r.idgrupo = g.idGrupo "
                        + "LEFT JOIN ciclo c ON g.idCiclo = c.idCiclo "
                        + "ORDER BY e.idempresa;";

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String line = rs.getString("company_id")
                            + ", " + rs.getString("company_name")
                            + ", " + rs.getString("group_id")
                            + ", " + rs.getString("group_name")
                            + ", " + rs.getString("course_year")
                            + ", " + rs.getString("assigned_students")
                            + ", " + rs.getString("status");
                    data.add(line);
                }

                stmt.close();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
                }
            }
        }
        return data;
    }

    public String getFCTsDBData(int index) {
        Connection conn = makeConection();
        String line = new String();
        if (conn != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                String query = "SELECT "
                        + "r.idempresa AS company_id, "
                        + "e.nombre AS company_name, "
                        + "r.idgrupo AS group_id, "
                        + "g.nombre_grupo AS group_name, "
                        + "r.cursoescolar AS course_year, "
                        + "r.num_alu_asignados AS assigned_students, "
                        + "r.estado AS status "
                        + "FROM "
                        + "realizan_fct r "
                        + "LEFT JOIN empresa e ON r.idempresa = e.idempresa "
                        + "LEFT JOIN grupo g ON r.idgrupo = g.idGrupo "
                        + "LEFT JOIN ciclo c ON g.idCiclo = c.idCiclo "
                        + "WHERE e.idempresa = ? "
                        + "ORDER BY e.idempresa;";

                pstmt = conn.prepareStatement(query);
                pstmt.setInt(1, index);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    line = rs.getString("company_id")
                            + ", " + rs.getString("company_name")
                            + ", " + rs.getString("group_id")
                            + ", " + rs.getString("group_name")
                            + ", " + rs.getString("course_year")
                            + ", " + rs.getString("assigned_students")
                            + ", " + rs.getString("status");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
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
                    JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
                }
            }
        }
        return line;
    }

    public void insertFCTDBData(String info) {
        Connection conn = makeConection();
        if (conn != null) {
            if (info != null && !info.isEmpty()) {
                String[] values = info.split(", ");
                if (values.length >= 4) {
                    Statement stmt = null;
                    try {
                        int companyId = Integer.parseInt(values[0]);
                        int groupId = Integer.parseInt(values[1]);
                        String courseYear = values[2].replace("'", "''");
                        int assignedStudents = Integer.parseInt(values[3]);
                        String status = values[4].replace("'", "''");

                        String query = String.format("INSERT INTO realizan_fct (idempresa, idgrupo, cursoescolar, num_alu_asignados, estado) VALUES (%d, %d, '%s', %d, '%s')",
                                companyId, groupId, courseYear, assignedStudents, status);

                        stmt = conn.createStatement();
                        stmt.executeUpdate(query);

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Data format error: " + e.toString());
                    } finally {
                        try {
                            if (stmt != null) {
                                stmt.close();
                            }
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error while executing the query: Datos insuficientes");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error while executing the query: No has agregado información");
            }
        }
    }

    public void deleteFCTDBData(String info) {
        Connection conn = makeConection();
        if (conn != null) {
            if (info != null && !info.isEmpty()) {
                String[] values = info.split(", ");
                if (values.length >= 4) {
                    Statement stmt = null;
                    try {
                        int companyId = Integer.parseInt(values[0]);
                        int groupId = Integer.parseInt(values[1]);
                        String courseYear = values[2].replace("'", "''");
                        int assignedStudents = Integer.parseInt(values[3]);
                        String status = "I";

                        String query = String.format("UPDATE realizan_fct SET estado = '%s' WHERE idempresa = %d AND idgrupo = %d AND cursoescolar = '%s'",
                                status, companyId, groupId, courseYear);

                        stmt = conn.createStatement();
                        stmt.executeUpdate(query);

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Data format error: " + e.toString());
                    } finally {
                        try {
                            if (stmt != null) {
                                stmt.close();
                            }
                            conn.close();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error while executing the query: Datos insuficientes");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error while executing the query: No has agregado información");
            }
        }
    }

    public void modifyFCTDBData(String info) {
        Connection conn = makeConection();
        if (conn != null) {
            if (info != null && !info.isEmpty()) {
                String[] values = info.split(", ");
                if (values.length >= 4) {
                    Statement stmt = null;
                    try {
                        int companyId = Integer.parseInt(values[0]);
                        int groupId = Integer.parseInt(values[1]);
                        String courseYear = values[2].replace("'", "''");
                        int assignedStudents = Integer.parseInt(values[3]);
                        String status = values[4].replace("'", "''");

                        String query = String.format("UPDATE realizan_fct SET num_alu_asignados = %d, estado = '%s' WHERE idempresa = %d AND idgrupo = %d AND cursoescolar = '%s'",
                                assignedStudents, status, companyId, groupId, courseYear);

                        stmt = conn.createStatement();
                        stmt.executeUpdate(query);

                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(null, "Error while executing the query: " + e.toString());
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Data format error: " + e.toString());
                    } finally {
                        try {
                            if (stmt != null) {
                                stmt.close();
                            }
                            if (conn != null) {
                                conn.close();
                            }
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(null, "Error while closing the connection: " + e.toString());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error while executing the query: Datos insuficientes");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error while executing the query: No has agregado información");
            }
        }
    }
}
