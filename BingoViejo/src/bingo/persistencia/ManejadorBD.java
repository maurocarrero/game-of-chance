package bingo.persistencia;

import java.sql.*;
import java.util.ArrayList;

public class ManejadorBD {    

    private Connection conexion;

    private static ManejadorBD instancia;

    public static ManejadorBD getInstancia() {
            if (instancia == null) {
                    instancia = new ManejadorBD();
            }
            return instancia;
    }

    public void conectar(String url) {
            try {

                    conexion = DriverManager.getConnection(url);

            } catch (SQLException e1) {
                    System.out.println("Error de conexi�n.:" + e1.getMessage());
            }
    }

    public void desconectar() {
            try {
                    conexion.close();
            } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexi�n.");
            }
    }

    public void ejecutar(String sql) {
            try {
                    Statement st = conexion.createStatement();
                    st.executeUpdate(sql);
                    st.close();
            } catch (SQLException e) {
                    System.out.println("Error al ejecutar sql.\n" + e.getMessage());
            }
    }

    public ResultSet obtenerResultSet(String sql) {
            ResultSet rs = null;
            try {
                    Statement st = conexion.createStatement();
                    rs = st.executeQuery(sql);
            } catch (SQLException e) {
                    System.out.println("Error al ejecutar sql.\n" + e.getMessage());
            }
            return rs;
    }

    public int proximoOid() {
            int oid=-1;
            try {
                    String sql = "SELECT valor FROM Parametros WHERE nombre='oid'";
                    ResultSet rs = this.obtenerResultSet(sql);
                    if (rs.next()) {
                            oid=rs.getInt("valor");
                    }
                    rs.close();
                    oid++;
                    this.ejecutar("UPDATE Parametros set valor=" + oid + " WHERE nombre='oid'");
            } catch (SQLException e) {
                    System.out.println("Error al obtener el proximo oid." + e.getMessage());
            }
            return oid;
    }    
    
    public void leer(Persistente b) {
            try {
                    ResultSet rs = this.obtenerResultSet(b.getSelectSQL());
                    if (rs.next()) {
                            b.leerDesdeResultSet(rs);
                    }
            } catch (SQLException e) {
                    System.out.println("Error al leer objeto.\n" + e.getMessage());
            }
    }

    public ArrayList obtenerTodos(Persistente b) {
            ArrayList ret = new ArrayList();
            try {
                    ResultSet rs = this.obtenerResultSet(b.getSelectSQL());
                    while (rs.next()) {
                            Persistente aux=b.getNuevo();
                            aux.leerDesdeResultSet(rs);
                            ret.add(aux.getObjeto());
                    }
            } catch (SQLException e) {
                    System.out.println("Error al obtener objetos.\n" + e.getMessage());
            }
            return ret;
    }
}
