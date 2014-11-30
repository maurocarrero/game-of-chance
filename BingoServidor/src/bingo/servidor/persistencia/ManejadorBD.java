package bingo.servidor.persistencia;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;

public class ManejadorBD implements Serializable, Remote {    

    private Connection conexion;

    private static ManejadorBD instancia;

    public static ManejadorBD getInstancia() {
            if (instancia == null) {
                    instancia = new ManejadorBD();
            }
            return instancia;
    }

    public void conectar(String url)  throws RemoteException {
        try {
             conexion = DriverManager.getConnection(url);
        } catch (SQLException e1) {
              System.out.println("Error de conexión.:" + e1.getMessage());
        }
    }

    public void desconectar()  throws RemoteException {
            try {
                 conexion.close();
                 System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                 System.out.println("Error al cerrar la conexión.");
            }
    }

    public void ejecutar(String sql) throws RemoteException {
            try {
                 Statement st = conexion.createStatement();
                 st.executeUpdate(sql);
                 st.close();
            } catch (SQLException e) {
                 System.out.println("Error al ejecutar sql.\n" + e.getMessage());
            }
    }

    public ResultSet obtenerResultSet(String sql) throws RemoteException {
            ResultSet rs = null;
            try {
                Statement st = conexion.createStatement();
                rs = st.executeQuery(sql);
            } catch (SQLException e) {
                System.out.println("Error al ejecutar sql.\n" + e.getMessage());
            }
            return rs;
    }
    
    public void leer(Persistente b) throws RemoteException {
            try {
                    ResultSet rs = this.obtenerResultSet(b.getSelectSQL());
                    if (rs.next()) {
                            b.leerDesdeResultSet(rs);
                    }
            } catch (SQLException e) {
                    System.out.println("Error al leer objeto.\n" + e.getMessage());
            }
    }

    public ArrayList obtenerTodos(Persistente b) throws RemoteException {
            ArrayList ret = new ArrayList();
            try {
                ResultSet rs = this.obtenerResultSet(b.getSelectSQL());
                while (rs.next()) {
                    Persistente aux = b.getNuevo();
                    aux.leerDesdeResultSet(rs);
                    ret.add(aux.getObjeto());
                }
            } catch (SQLException e) {
                    System.out.println("Error al obtener objetos.\n" + e.getMessage());
            }
            return ret;
    }
}

