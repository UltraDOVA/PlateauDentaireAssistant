package com.dova.plateauAssistant.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dova.plateauAssistant.data.entities.Plateau
import com.dova.plateauAssistant.domain.PlateauRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlateauViewModel @Inject constructor(
    private val plateauRepository: PlateauRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlateauUiState())
    val uiState = _uiState.asStateFlow()

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

    //todo : snippet, to move in the init()
    fun loadPlateau() {
        var tmpPlateau = uiState.value.plateau
        viewModelScope.launch {
            //todo : remove and replace by an id search (transmitted through navigation)
            plateauRepository.getAllPlateaux().collect {
                tmpPlateau = it.firstOrNull()!!
            }
        }
        viewModelScope.launch {
            delay(20)
            _uiState.update {
                it.copy(
                    plateau = tmpPlateau
                )
            }
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