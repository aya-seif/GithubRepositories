package com.example.githubrepoviewer.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoviewer.domain.usecases.GetRepositoriesUseCase
import com.example.githubrepoviewer.domain.NetworkException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RepositoriesUiState())
    val state = _state.asStateFlow()

    init {
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isLoading = true) }
                val result = getRepositoriesUseCase()
                _state.update { it.copy(isLoading = false, repos = result) }
            } catch (exception: Exception) {
                _state.update { it.copy(error = handleException(exception)) }
            }
        }
    }


    private fun handleException(exception: Exception): String {
        return when (exception) {
            is NetworkException.NotFoundException -> "Not found"
            is NetworkException.ApiKeyExpiredException -> "API key expired"
            is NetworkException.UnAuthorizedException -> "Unauthorized"
            else -> "Unknown error"
        }
    }

}