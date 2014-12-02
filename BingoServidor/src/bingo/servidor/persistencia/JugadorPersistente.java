package bingo.servidor.persistencia;

import bingo.servidor.modelo.entidades.Jugador;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JugadorPersistente implements Serializable, Persistente {

    private Jugador jugador;
    private ManejadorBD db = ManejadorBD.getInstancia();

    public JugadorPersistente() {
    }

    public JugadorPersistente(Jugador u){
            this.jugador = u;
    }

    public Jugador getUsuario() {
        return jugador;
    }

    public void setUsuario(Jugador jugador) {
        this.jugador = jugador;
    }

    public void setId(int id) {
        jugador.setId(id);
    }

    @Override
    public String getInsertSQL() throws RemoteException {
        return "INSERT INTO usuarios (usuario, password, saldo) VALUES (" 
            + jugador.getUsuario() + "','" 
            + jugador.getPassword() + "','" 
            + jugador.getSaldo() + "')";
    }

    @Override
    public String getUpdateSQL() throws RemoteException {
        return "UPDATE usuarios SET saldo ='" + jugador.getSaldo() + "' WHERE id=" + jugador.getId();
    }

    @Override
    public String getDeleteSQL() {
        return "DELETE FROM usuarios " + " WHERE id=" + jugador.getId();
    }

    @Override
    public String getSelectSQL() {
        return "SELECT * FROM usuarios WHERE tipo=2;";        
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) {
        try {
            jugador.setId(rs.getInt("id"));
            jugador.setUsuario(rs.getString("usuario"));
            jugador.setPassword(rs.getString("password"));
            jugador.setSaldo(rs.getDouble("saldo"));
        } catch (SQLException e) {

        }
    }

    public int getId() {
        return jugador.getId();
    }   

    @Override
    public Object getObjeto() {
        return jugador;
    }
  
    public void eliminar() throws RemoteException {
        db.ejecutar(getDeleteSQL());
    }

    @Override
    public Persistente getNuevo() throws RemoteException {
        return new JugadorPersistente(new Jugador());
    }

}
