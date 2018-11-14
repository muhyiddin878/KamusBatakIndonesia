package com.example.muhyiddin.kamusbatakindonesia;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class BahasaBatakTerbaru {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name ="batak")
    String batak;

    @ColumnInfo(name="arti")
    String arti;



    public int getId(){
        return id;
    }


    public String getBatak() {
        return batak;
    }

    public String getArti() {
        return arti;
    }

    public void setId(int id){
        this.id=id;
    }


    public void setBatak(String batak ){
        this.batak= batak;
    }

    public void setArti(String arti) {
        this.arti = arti;
    }

}
