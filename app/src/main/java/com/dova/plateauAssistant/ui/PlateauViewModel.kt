package com.dova.plateauAssistant.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class PlateauViewModel @Inject constructor() : ViewModel() {
    private val _uiState = MutableStateFlow(PlateauUiState())
    val uiState = _uiState.asStateFlow()

    fun dropOnPlateau(case: Int, elem: Int) {
        val nv_plateau = uiState.value.plateau.toMutableList()
        nv_plateau[case] = elem
        _uiState.update {
            it.copy(
                plateau = nv_plateau.toList()
            )
        }
    }
}

data class PlateauUiState(
    val nbCases: Int = 8,
    val plateau: List<Int> = listOf(0,0,0,0,0,0,0,0)
)