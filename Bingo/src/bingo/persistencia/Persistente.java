package bingo.persistencia;

import java.sql.ResultSet;
import java.util.ArrayList;

public interface Persistente {


    public abstract String getInsertSQL();
    public abstract String getUpdateSQL();
    public abstract String getDeleteSQL();
    public abstract String getSelectSQL();
    public abstract void leerDesdeResultSet(ResultSet rs);
    public abstract Persistente getNuevo();
    public abstract Object getObjeto();
    public abstract int getOid();
    public abstract void  setOid(int oid);

}
