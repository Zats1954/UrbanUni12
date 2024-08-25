package ru.zatsoft.listview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {

    private val _listUsers = MutableLiveData<MutableList<User>>()
    val listUsers: MutableLiveData<MutableList<User>>
        get() = _listUsers

    init {
        _listUsers.value = mutableListOf()
    }

    fun add(user: User) {
        _listUsers.value?.add(user)
    }
}