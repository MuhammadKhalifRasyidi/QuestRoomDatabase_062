package com.example.meet09.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.meet09.data.entity.Mahasiswa

@Dao
interface MahasiswaDao {
    @Insert
    suspend fun insertMahasiswa(
        mahasiswa: Mahasiswa
    )
}