package bingo.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import bingo.modelo.entidades.Usuario;

public class UsuarioPersistente{

    private Usuario usuario;
    private ManejadorBD db = ManejadorBD.getInstancia();

    public UsuarioPersistente() {
    }

    public UsuarioPersistente(Usuario u){
            this.usuario = u;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(int id) {
    usuario.setId(id);
    }


    public String getInsertSQL() {

            return "INSERT INTO Usuario(id,usuario,password,logueado)" + "VALUES("
                            + usuario.getId() + ",'" + usuario.getUsuario() + "','"
                            + usuario.getPassword()+ ",'" + usuario.estaLogueado() + "')";
    }

    public String getUpdateSQL() {
            return "UPDATE Usuario SET usuario='" + usuario.getUsuario()
                            + "', password='" + usuario.getPassword()
                            + "', logueado='" + usuario.estaLogueado()
                            + "' WHERE id=" + usuario.getId();
    }

    public String getDeleteSQL() {

            return "DELETE FROM Usuario " + " WHERE id=" + usuario.getId();
    }

    public String getSelectSQL() {

            String sql = "SELECT * FROM Usuario ";
            if (usuario!=null && getId() != 0) {
                    sql = sql + " WHERE id=" + getId();
            }
            return sql;
    }

    public void leerDesdeResultSet(ResultSet rs) {

            try {
                    usuario.setId(rs.getInt("id"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setLogueado(rs.getBoolean("logueado"));

            } catch (SQLException e) {

            }
    }


    /*public Persistente getNuevo() {
        return new UsuarioPersistente(new Usuario());
    }*/

    public int getId() {
        return usuario.getId();
    }   

    public Object getObjeto() {
        return usuario;
    }

    public void guardar() {
        if(usuario.getId()==0){
            int id = db.proximoOid();
            this.setId(id);
            db.ejecutar(getInsertSQL());
        }else{
            db.ejecutar(getUpdateSQL());            
        }
    }
    
    public void eliminar(){
        db.ejecutar(getDeleteSQL());
    }

}