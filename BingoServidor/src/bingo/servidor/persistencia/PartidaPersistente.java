package bingo.servidor.persistencia;

import bingoservidor.Partida;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PartidaPersistente implements Serializable, Persistente {

    private Partida partida;
    private ManejadorBD db = ManejadorBD.getInstancia();

    public PartidaPersistente() {
    }

    public PartidaPersistente(Partida partida){
            this.partida = partida;
    }

    public Partida getPartida() {
        return partida;
    }

    public void setUsuario(Partida partida) {
        this.partida = partida;
    }

    @Override
    public String getInsertSQL() throws RemoteException {
        return "INSERT INTO config (clave, valor) VALUES (" 
           + "'CANT_FILAS', '" + partida.getCantFilas() + "','" 
           + "'CANT_COLUMNAS', '" + partida.getCantColumnas() + "','" 
           + "'CANT_JUGADORES', '" + partida.getCantJugadores() + "','" 
           + "'CANT_MAX_CARTONES', '" + partida.getCantMaxCartones() + "');";
    }

    @Override
    public String getUpdateSQL() throws RemoteException {
            return "UPDATE config SET "
                + "valor='" + partida.getCantColumnas()
                + "' WHERE clave='CANT_COLUMNAS';";
    }

    //TODO
    @Override
    public String getDeleteSQL() {
        return "";
    }

    @Override
    public String getSelectSQL() {
        String sql = "SELECT * FROM config;";
        return sql;
    }

    @Override
    public void leerDesdeResultSet(ResultSet rs) throws RemoteException {
        try {
            String clave = rs.getString("clave");
            String valor = rs.getString("valor");
            
            if (clave.equals("CANT_FILAS")) {
                partida.setCantFilas(Integer.parseInt(valor));
            }
            if (clave.equals("CANT_COLUMNAS")) {
                partida.setCantColumnas(Integer.parseInt(valor));
            }
            if (clave.equals("CANT_MAX_CARTONES")) {
                partida.setCantMaxCartones(Integer.parseInt(valor));
            }
            if (clave.equals("CANT_JUGADORES")) {
                partida.setCantJugadores(Integer.parseInt(valor));
            }
            if (clave.equals("VALOR_CARTON")) {
                partida.setValorCarton(Double.parseDouble(valor));
            }
            
        } catch (SQLException e) {

        }
    }

    @Override
    public Object getObjeto() {
        return partida;
    }
    
    public void eliminar() throws RemoteException {
        db.ejecutar(getDeleteSQL());
    }

    @Override
    public Persistente getNuevo() throws RemoteException {
        return new PartidaPersistente(new Partida());
    }

}
