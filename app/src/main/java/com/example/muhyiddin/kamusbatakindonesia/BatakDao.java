package com.example.muhyiddin.kamusbatakindonesia;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao

public interface BatakDao {

    @Query("SELECT * FROM BahasaBatakTerbaru")
    List<BahasaBatakTerbaru> getAll();


    @Query("SELECT * FROM BahasaBatakTerbaru WHERE batak like :batak ")
    BahasaBatakTerbaru findByBahasaBatak(String batak);

    @Query("SELECT * FROM BahasaBatakTerbaru WHERE arti like :arti")
    BahasaBatakTerbaru findByArtiBatak(String arti);

    @Query("SELECT batak FROM BahasaBatakTerbaru")
    String []viewbatak();



//    @Query("SELECT * FROM bahasabatak WHERE id ")

    //
    @Insert
    void insertALL(List<BahasaBatakTerbaru> listBatak);

    @Delete
    void deleteKamusBatak(List<BahasaBatakTerbaru> listBatak);
}


