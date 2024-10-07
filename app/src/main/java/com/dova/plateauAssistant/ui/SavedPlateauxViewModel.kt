package com.dova.plateauAssistant.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dova.plateauAssistant.data.entities.Plateau
import com.dova.plateauAssistant.domain.PlateauRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SavedPlateauxViewModel @Inject constructor(
    private val plateauRepository: PlateauRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SavedPlateauxUiState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            plateauRepository.getAllPlateaux().take(1).collect { plateaux ->
                _uiState.update {
                    it.copy(
                        customPlateaux = plateaux
                    )
                }
            }
        }
    }
}

data class SavedPlateauxUiState(
    val customPlateaux: List<Plateau> = emptyList(),
    val defautlsPlateaux: List<Plateau> = emptyList()
)