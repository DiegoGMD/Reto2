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
public class MyConnection {
    
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
    
    public String[] listaCiclos() { // Combobox de ciclo
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
    
    public String[] listaAños() { // Combobox de curso
        Connection conn = makeConection();
        List<String> Ciclo = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT DISTINCT curso FROM ciclo";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    String curso = rs.getString("curso");
                    Ciclo.add(curso);
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
    
    public String[] listaGrupos() { // Combobox de curso
        Connection conn = makeConection();
        List<String> Grupo = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT DISTINCT nombre_grupo FROM grupo";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    String grupo = rs.getString("nombre_grupo");
                    Grupo.add(grupo);
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
    
    public List<String> listaProfesores() { // Combobox de curso
        Connection conn = makeConection();
        List<String> profesList = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT idProf, nombre, apellidos FROM profesor";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                String line = "IDProf // Name // SecondName";
                profesList.add(line);
                while (rs.next()) {
                     line = rs.getString("idProf") + ", " + rs.getString("nombre")
                            + ", " + rs.getString("apellidos");
                    profesList.add(line);
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return profesList;
    }
    
   public List<String> teacherData(int index) {
    Connection conn = makeConection();
    List<String> profesList = new ArrayList<>();
    
    if (conn != null) {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String query = "SELECT idProf, nombre, apellidos FROM profesor " + 
                           "WHERE idProf = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, index);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                String line = rs.getString("idProf") + ", " + rs.getString("nombre")
                              + ", " + rs.getString("apellidos");
                profesList.add(line);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    return profesList;
}

    
    public List<String> informacionEmpresaSelecionada(String empresaSelec) { //Esto es una consulta temporal, lo tendremos de modelo
        Connection conn = makeConection();
        List<String> datos = new ArrayList<>();
        if (conn != null) {
            try {
                String query = "SELECT DISTINCT empresa.nombre AS Empresa, "
                        + "sector.descripcion AS Sector, "
                        + "profesor.nombre AS Responsable, "
                        + "personal.nombre AS Personal_Contacto "
                        + "FROM empresa "
                        + "JOIN sector ON empresa.idSector = sector.idSector "
                        + "JOIN responsable ON empresa.idempresa = responsable.idempresa "
                        + "JOIN profesor ON responsable.idprofe = profesor.idProf "
                        + "LEFT JOIN personal ON empresa.idempresa = personal.idempresa "
                        + "AND personal.escontacto = 1;";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    String empresa = rs.getString("Empresa");
                    if (empresa.equals(empresaSelec)) {
                        
                        String line = empresa;
                        String line2 = rs.getString("Sector");
                        String line3 = rs.getString("Responsable");
                        String line4 = rs.getString("Personal_Contacto");
                        datos.add(line);
                        datos.add(line2);
                        datos.add(line3);
                        datos.add(line4);
                    }
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
    
    public List<String> informacionFctPorCursoYCiclo(String cicloSelec, String cursoSelec) { //Esto es una consulta temporal, lo tendremos de modelo
        Connection conn = makeConection();
        List<String> datos = new ArrayList<>();
        if (conn != null) {
            try {
                String query = "SELECT empresa.nombre AS Empresa,"
                        + "ciclo.ciclo AS Ciclo, "
                        + "ciclo.curso AS Curso_Ciclo, "
                        + "count(realizan_fct.num_alu_asignados) AS Numero_Fct "
                        + "FROM realizan_fct "
                        + "JOIN grupo ON realizan_fct.idgrupo = grupo.idGrupo "
                        + "JOIN ciclo ON grupo.idCiclo = ciclo.idCiclo "
                        + "JOIN empresa ON realizan_fct.idempresa = empresa.idempresa "
                        + "GROUP BY empresa.nombre, ciclo.ciclo, ciclo.curso, realizan_fct.cursoescolar;";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
                while (rs.next()) {
                    String ciclo = rs.getString("Ciclo");
                    String curso = rs.getString("Curso_ciclo");
                    if (ciclo.equals(cicloSelec) && curso.equals(cursoSelec)) {
                        String line = rs.getString("Empresa");
                        int fcts = rs.getInt("Numero_Fct");
                        String line2 = Integer.toString(fcts);
                        datos.add(line);
                        datos.add(line2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Alguna de las dos opciones no tiene fct solicitada");
                    }
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta" + e.toString());
            }
        }
        for (String dato : datos) {
            System.out.println(dato);
        }
        return datos;
    }
    
    public List<String> informationTutorAndCompanyWithCourse(String cursoSelec, String cicloSelec, String grupoSelec) {
        Connection conn = makeConection();
        List<String> datos = new ArrayList<>();
        if (conn != null) {
            try {
                String query = "SELECT grupo.nombre_grupo AS Grupo, ciclo.ciclo AS Ciclo, ciclo.curso AS Curso_Ciclo, "
                        + "profesor.nombre AS Profesor_Nombre, profesor.apellidos AS Profesor_Apellidos, "
                        + "empresa.nombre AS Empresa, COUNT(realizan_fct.num_alu_asignados) AS Numero_Fct "
                        + "FROM grupo "
                        + "JOIN tutorFct ON grupo.idGrupo = tutorFct.idgrupo "
                        + "JOIN profesor ON tutorFct.idprofe = profesor.idProf "
                        + "JOIN realizan_fct ON grupo.idGrupo = realizan_fct.idgrupo "
                        + "JOIN empresa ON realizan_fct.idempresa = empresa.idempresa "
                        + "JOIN ciclo ON grupo.idCiclo = ciclo.idCiclo "
                        + "GROUP BY grupo.nombre_grupo, ciclo.ciclo, ciclo.curso, profesor.nombre, "
                        + "profesor.apellidos, empresa.nombre;";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String ciclo = rs.getString("Ciclo");
                    String curso = rs.getString("Curso_Ciclo");
                    String grupo = rs.getString("Grupo");
                    if (ciclo.equals(cicloSelec) && curso.equals(cursoSelec) && grupo.equals(grupoSelec)) {
                        String line = rs.getString("Profesor_Nombre");
                        String line2 = rs.getString("Profesor_Apellidos");
                        String line3 = rs.getString("Empresa");
                        int fcts = rs.getInt("Numero_Fct");
                        String line4 = Integer.toString(fcts);
                        datos.add(line);
                        datos.add(line2);
                        datos.add(line3);
                        datos.add(line4);
                    } else {
                        JOptionPane.showMessageDialog(null, "Alguna de las dos opciones no tiene fct solicitada");
                    }
                }
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta" + e.toString());
            }
        }
        for (String dato : datos) {
            System.out.println(dato);
        }
        return datos;
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
    
    public String[] getCompaniesDBData() {
        Connection conn = makeConection();
        List<String> data = new ArrayList<>();
        
        if (conn != null) {
            try {
                String query = "SELECT "
                        + "e.idempresa AS company_id, "
                        + "e.nombre AS company_name, "
                        + "s.descripcion AS sector, "
                        + "rf.num_alu_asignados AS fct_real_assigned_students, "
                        + "pf.solicitaAlu AS available_places_for_students "
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
                            + " || " + rs.getString("available_places_for_students");
                    data.add(line);
                }
                
                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al realizar la consulta: " + e.toString());
            }
        }
        return data.toArray(new String[0]);
    }
    
    public String[] getCompaniesDBData(int index) {
        Connection conn = makeConection();
        List<String> data = new ArrayList<>();
        
        if (conn != null) {
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                String query = "SELECT "
                        + "e.idempresa AS company_id, "
                        + "e.nombre AS company_name, "
                        + "s.descripcion AS sector, "
                        + "rf.num_alu_asignados AS fct_real_assigned_students, "
                        + "pf.solicitaAlu AS available_places_for_students "
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
                            + " || " + rs.getString("available_places_for_students");
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
        return data.toArray(new String[0]);
    }
}
