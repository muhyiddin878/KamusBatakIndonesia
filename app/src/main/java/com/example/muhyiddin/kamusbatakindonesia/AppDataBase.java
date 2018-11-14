package com.example.muhyiddin.kamusbatakindonesia;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {BahasaBatakTerbaru.class},version = 1)
public abstract class AppDataBase  extends RoomDatabase{
    public abstract BatakDao userDao();
}
