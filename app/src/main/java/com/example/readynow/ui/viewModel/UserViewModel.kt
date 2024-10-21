package com.example.readynow.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.readynow.data.model.User
import com.example.readynow.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {
    val allItems: LiveData<List<User>> = liveData {
        emit(repository.getAll())
    }

    fun insertItem(entity: User) {
        viewModelScope.launch {
            repository.insert(entity)
        }
    }
}