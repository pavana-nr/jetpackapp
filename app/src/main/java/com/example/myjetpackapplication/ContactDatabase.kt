package com.example.myjetpackapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myjetpackapplication.data.Contact

@Database(
    entities = [Contact::class],
    version = 1
)
abstract class ContactDatabase: RoomDatabase() {
    abstract val dao: ContactDAO
}