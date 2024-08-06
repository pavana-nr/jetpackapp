package com.example.myjetpackapplication

import com.example.myjetpackapplication.data.Contact

data class ContactSatate(
    val contacts:List<Contact> = emptyList(),
    val firstName: String = "",
    val lastName: String = "",
    val phoneNum:  String = "",
    val isAddingContact: Boolean = false,
    val sortType : SortTypes = SortTypes.FIRST_NAME
)