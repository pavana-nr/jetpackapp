package com.example.myjetpackapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myjetpackapplication.ContactDAO
import com.example.myjetpackapplication.ContactEvent
import com.example.myjetpackapplication.ContactSatate
import com.example.myjetpackapplication.SortTypes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn


class ContactViewModel(
    private val dao:ContactDAO
) : ViewModel(){
    private val _sortType = MutableStateFlow(SortTypes.FIRST_NAME)
    private val _contacts = _sortType.flatMapLatest {
        sortType -> when(sortType){
        SortTypes.FIRST_NAME -> dao.getContactOrderByFirstName()
        SortTypes.LAST_NAME -> dao.getContactOrderByLastName()
        SortTypes.PHONE_NUM -> dao.getContactOrderByPhoneNumber()
    }
    }
    private val _state = MutableStateFlow(ContactSatate())
    val state =combine(_state, _sortType, _contacts) { state, sortType, contacts ->
    state.copy(
        contacts = contacts,
        sortType = sortType
    )
}.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ContactSatate())

    fun onEvent(event: ContactEvent){
        when(event){
            is ContactEvent.DeleteContact -> {
                viewModelScope.launch {
                    dao.deleteContact(event.contact)
                }
            }

            ContactEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingContact = false
                    )
                }
            }
            ContactEvent.SaveContact -> {

            }
            is ContactEvent.SetFirstName ->{
                _state.update {
                    it.copy(
                        firstName = event.firstName
                    )
                }
            }
            is ContactEvent.SetLastName -> {
                _state.update { it.copy(
                    lastName = event.lastName
                ) }
            }
            is ContactEvent.SetPhoneNum -> {
                _state.update {
                    it.copy(
                        phoneNum = event.phoneNum
                    )
                }
            }
            ContactEvent.ShowDialog -> {
                _state.update { it.copy(

                ) }
            }
            is ContactEvent.SortContacts -> TODO()
        }
    }
}