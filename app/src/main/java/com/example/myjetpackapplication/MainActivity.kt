package com.example.myjetpackapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var names by remember{
                mutableStateOf(listOf<String>())
            }
val name = remember { mutableStateOf(TextFieldValue("")) }
Column(
    modifier = Modifier.padding(10.dp)
) {
    Row(){
        MyTextField(textValue = name)
        Spacer(modifier = Modifier.width(10.dp))
        FilledButtonExample(onClick = {
            names = names + name.value.text
            name.value = TextFieldValue("")
        })
    }
    LazyColumn {
        items(names) { name ->
            Text(text = name, modifier = Modifier.padding(8.dp))
            Divider(color = Color.Gray, thickness = 1.dp)
        }

    }
}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(textValue: MutableState<TextFieldValue>) {
    TextField(
        value = textValue.value,
        onValueChange = { newValue ->
            // Update the mutable state with the new value
            textValue.value = newValue
        },
        label = { Text("Enter name") }
    )
}

@Composable
fun FilledButtonExample(onClick: () -> Unit) {
    Button(onClick = { onClick() }) {
        Text("Add", modifier = Modifier.
        padding(10.dp))
    }
}




