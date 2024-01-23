package com.example.githubrepoviewer.ui.ropedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoviewer.domain.usecases.GetRepositoryDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(RepositoryDetailsScreenUiState())
    val state = _state.asStateFlow()

    private val args = RepositoryDetailsArgs(savedStateHandle)
    private val name = args.name
    private val owner = args.owner

    init {
        getRepositoryDetails()
    }

    private fun getRepositoryDetails() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _state.update { it.copy(isLoading = true) }
                val result = getRepositoryDetailsUseCase(repoName = name, owner = owner).toUiState()
                _state.value = _state.value.copy(
                    isLoading = false,
                    repositoryDetails = result
                )
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}