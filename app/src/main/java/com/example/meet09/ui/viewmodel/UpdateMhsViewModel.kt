package com.example.meet09.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.meet09.data.entity.Mahasiswa
import com.example.meet09.repository.RepositoryMhs

class UpdateMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {


}

fun Mahasiswa.toUIStateMhs() : MhsUIState = MhsUIState (
    mahasiswaEvent = this.toDetailUiEvent(),
)