package com.example.meet09.ui.viewmodel

import com.example.meet09.data.entity.Mahasiswa


data class HomeUiState (
    val listMhs: List<Mahasiswa> = listOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
)