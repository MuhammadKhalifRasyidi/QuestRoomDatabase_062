package com.example.meet09.ui.viewmodel

import com.example.meet09.data.entity.Mahasiswa

fun Mahasiswa.toDetailUiEvent () : MahasiswaEvent{
    return MahasiswaEvent(
        nim = nim,
        nama = nama,
        jenisKelamin = jenisKelamin,
        alamat = alamat,
        kelas = kelas,
        angkatan = angkatan
    )
}