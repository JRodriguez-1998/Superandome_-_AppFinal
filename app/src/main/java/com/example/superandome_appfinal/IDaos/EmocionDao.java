package com.example.superandome_appfinal.IDaos;

import com.example.superandome_appfinal.Entidades.Consejo;
import com.example.superandome_appfinal.Entidades.Emocion;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public interface EmocionDao extends Dao<Emocion, Integer> {

    Emocion getEmocionById(int idEmocion) throws SQLException;;
}
