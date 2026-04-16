package com.example.userlist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userlist.domain.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserListViewModel(
    private val repository: UserRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<UserListState>(UserListState.Loading)
    val state: StateFlow<UserListState> = _state.asStateFlow()

    init {
        loadUsers()
    }

    fun loadUsers() {
        viewModelScope.launch {
            _state.value = UserListState.Loading
            try {
                val users = repository.getUsers()
                _state.value = if (users.isEmpty()) {
                    UserListState.Empty
                } else {
                    UserListState.Success(users)
                }
            } catch (e: Exception) {
                _state.value = UserListState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}
