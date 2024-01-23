package com.example.githubrepoviewer.ui.issues

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepoviewer.domain.usecases.GetRepositoryIssues
import com.example.githubrepoviewer.domain.model.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(
    private val getIssuesUseCase: GetRepositoryIssues,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = MutableStateFlow(emptyList<Issue>())
    val state = _state.asStateFlow()

    private val args = IssuesArgs(savedStateHandle)
    private val name = args.name
    private val owner = args.owner

    init {
        getIssues()
    }

    private fun getIssues() {
        viewModelScope.launch {
            try {
                val result = getIssuesUseCase(owner, name)
                _state.update { result }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}