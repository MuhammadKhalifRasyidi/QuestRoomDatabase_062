package com.example.meet09.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.meet09.data.entity.Mahasiswa
import com.example.meet09.repository.RepositoryMhs
import com.example.meet09.ui.navigation.DestinasiUpdate
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateMhsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryMhs: RepositoryMhs
) : ViewModel() {

    var UpdateUIState by mutableStateOf(MhsUIState())
        private set

    private val _nim: String = checkNotNull(savedStateHandle[DestinasiUpdate.NIM])

    init {
        viewModelScope.launch {
            UpdateUIState = repositoryMhs.getMhs(_nim)
                .filterNotNull()
                .first()
                .toUIStateMhs()
        }
    }

    fun UpdateState(mahasiswaEvent: MahasiswaEvent) {
        UpdateUIState = UpdateUIState.copy(
            mahasiswaEvent = mahasiswaEvent,
        )
    }

    fun validateFields() : Boolean {
        val event = UpdateUIState.mahasiswaEvent
        val errorState = FormErrorState(
            nim = if (event.nim.isNotEmpty()) null else "NIM tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            jenisKelamin = if (event.jenisKelamin.isNotEmpty()) null else "Jenis Kelamin tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
            kelas =  if (event.kelas.isNotEmpty()) null else "Kelas tidak boleh kosong",
            angkatan =  if (event.angkatan.isNotEmpty()) null else "Angkatan tidak boleh kosong",
        )

        UpdateUIState = UpdateUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun UpdateData() {
        val currentEvent = UpdateUIState.mahasiswaEvent

        if (validateFields()) {
            viewModelScope.launch {
                try {
                    repositoryMhs.updateMhs(currentEvent.toMahasiswaEntity())
                    UpdateUIState = UpdateUIState.copy(
                        snackBarMessage = "Data berhasil diupdate",
                        mahasiswaEvent = MahasiswaEvent(),
                        isEntryValid = FormErrorState()
                    )
                    println("SnackBarMessageDiatur: ${UpdateUIState.snackBarMessage}")
                } catch (e:Exception) {
                    UpdateUIState = UpdateUIState.copy(
                        snackBarMessage = "Data Gagal Diupdate"
                    )
                }
            }
        } else {
            UpdateUIState = UpdateUIState.copy(
                snackBarMessage = "Data Gagal diupdate"
            )
        }
    }

    fun resetSnackBarMessage() {
        UpdateUIState = UpdateUIState.copy(snackBarMessage = null)
    }
}

fun Mahasiswa.toUIStateMhs() : MhsUIState = MhsUIState (
    mahasiswaEvent = this.toDetailUiEvent(),
)