package com.example.superandome_appfinal.Daos;

import com.example.superandome_appfinal.Entidades.TipoArchivo;
import com.example.superandome_appfinal.Helpers.DataDB;
import com.example.superandome_appfinal.IDaos.TipoArchivoDao;
import com.j256.ormlite.dao.BaseDaoImpl;

import java.sql.SQLException;

public class TipoArchivoDaoImpl extends BaseDaoImpl<TipoArchivo, Integer> implements TipoArchivoDao {
    public TipoArchivoDaoImpl() throws SQLException {
        super(DataDB.getConnectionSource(), TipoArchivo.class);
    }
}
