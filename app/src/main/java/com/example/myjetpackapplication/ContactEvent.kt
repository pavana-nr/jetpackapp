package com.example.myjetpackapplication

import com.example.myjetpackapplication.data.Contact

sealed interface ContactEvent{
    object SaveContact:ContactEvent
    data class SetFirstName(val firstName: String):ContactEvent

    data class SetLastName(val lastName: String):ContactEvent

    data class SetPhoneNum(val phoneNum: String):ContactEvent

    object ShowDialog: ContactEvent
    object HideDialog: ContactEvent

    data class SortContacts(val sortTypes: SortTypes):ContactEvent
    data class DeleteContact(val contact: Contact): ContactEvent
}