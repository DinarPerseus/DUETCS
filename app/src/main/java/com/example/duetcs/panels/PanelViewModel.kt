package com.example.duetcs.panels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PanelViewModel(private val repository: PanelRepository) : ViewModel() {
    val allPanelMembers = repository.allPanelMembers

    fun addPanelMember(name: String, phone: String, email: String, role: String, currentDesignation: String, series: String) = viewModelScope.launch {
        repository.insert(PanelMember(name = name, phone = phone, email = email, role = role, currentDesignation = currentDesignation, series = series))
    }

    fun deletePanelMember(item: PanelMember) = viewModelScope.launch {
        repository.delete(item)

    }
    fun updatePanelMember(item: PanelMember) = viewModelScope.launch {
        repository.update(item)
    }
}
