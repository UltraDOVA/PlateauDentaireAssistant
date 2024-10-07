package com.dova.plateauAssistant.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dova.plateauAssistant.data.entities.Plateau
import com.dova.plateauAssistant.domain.PlateauRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlateauViewModel @Inject constructor(
    private val plateauRepository: PlateauRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlateauUiState())
    val uiState = _uiState.asStateFlow()

    init {
        val plateauToLoad = savedStateHandle.get<String>("id")?.toIntOrNull()
        if (plateauToLoad != null) {
            viewModelScope.launch {
                _uiState.update {
                    PlateauUiState(
                        plateau = plateauRepository.getPlateau(plateauToLoad)
                    )
                }
            }
        }
    }

    fun dropOnPlateau(index: Int, elem: Int) {
        val newValues = uiState.value.plateau.values.toMutableList()
        newValues[index] = elem
        _uiState.update {
            it.copy(
                setNewValues(it.plateau, newValues)
            )
        }
    }

    fun resetCase(index: Int) {
        val newValues = uiState.value.plateau.values.toMutableList()
        newValues[index] = -1
        _uiState.update {
            it.copy(
                setNewValues(it.plateau, newValues)
            )
        }
    }

    fun askForName() {
        _uiState.update {
            it.copy(
                askNameForSave = true
            )
        }
    }

    fun dismissAskForName() {
        _uiState.update {
            it.copy(
                askNameForSave = false
            )
        }
    }

    fun savePlateau(name: String) {
        _uiState.update {
            it.copy(
                plateau = Plateau(
                    name = name,
                    index = uiState.value.plateau.index,
                    nbLines = uiState.value.plateau.nbLines,
                    nbCases = uiState.value.plateau.nbCases,
                    values = uiState.value.plateau.values
                ),
                askNameForSave = false
            )
        }
        viewModelScope.launch {
            plateauRepository.insertPlateau(uiState.value.plateau)
        }
    }

    private fun setNewValues(plateau: Plateau, newValues: List<Int>): Plateau {
        return Plateau(
            name = plateau.name,
            index = plateau.index,
            nbCases = plateau.nbCases,
            nbLines = plateau.nbLines,
            values = newValues
        )
    }

}

data class PlateauUiState(
    val plateau: Plateau = Plateau(
        name = "current",
        index = 0,
        nbCases = 8,
        nbLines = 2,
        values = listOf(-1,-1,-1,-1,-1,-1,-1,-1)
    ),
    val askNameForSave: Boolean = false
)