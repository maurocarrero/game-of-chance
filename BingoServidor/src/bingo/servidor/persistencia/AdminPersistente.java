package bingo.servidor.persistencia;

import bingo.servidor.modelo.entidades.Administrador;
import bingo.servidor.modelo.entidades.Usuario;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPersistente implements Serializable, Persistente {

    private Administrador admin;
    private ManejadorBD db = ManejadorBD.getInstancia();

    public AdminPersistente() {
    }

    public AdminPersistente(Administrador admin){
            this.admin = admin;
    }

    public Usuario getUsuario() {
        return admin;
    }

    public void setUsuario(Administrador admin) {
        this.admin = admin;
    }

    public void setId(int id) {
        admin.setId(id);
    }


    @Override
    public String getInsertSQL() throws RemoteException {
        return "INSERT INTO usuarios (usuario, password)" + "VALUES ("
            + admin.getUsuario() + "','"
            + admin.getPassword()+ "')";
    }

    @Override
    public String getUpdateSQL() throws RemoteException {
            return "UPDATE usuarios SET usuario='" + admin.getUsuario()
                            + "', password='" + admin.getPassword()
                            + "' WHERE id=" + admin.getId();
    }

    @Override
    public String getDeleteSQL() {
        return "DELETE FROM usuarios " 
            + " WHERE id=" + admin.getId();
    }

    @Override
    public String getSelectSQL() {
        return "SELECT * FROM usuarios WHERE tipo=1;";
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) {
        try {
            admin.setId(rs.getInt("id"));
            admin.setUsuario(rs.getString("usuario"));
            admin.setPassword(rs.getString("password"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getId() {
        return admin.getId();
    }   

    @Override
    public Object getObjeto() {
        return admin;
    }
    
    public void eliminar() throws RemoteException {
        db.ejecutar(getDeleteSQL());
    }

    @Override
    public Persistente getNuevo() throws RemoteException {
        return new AdminPersistente(new Administrador());
    }

}
