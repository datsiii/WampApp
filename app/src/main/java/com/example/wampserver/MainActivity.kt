package com.example.wampserver

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.wampserver.ui.theme.WampServerTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var mainApi: MainApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val name = remember {
        mutableStateOf("")
    }
    val age = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.7f)
            //.background(color = Color.LightGray),
        ) {
            items(viewModel.userList.value) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp)
                ) {
                    Text(
                        text = it.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth()
                            .padding(10.dp)
                    )

                }
            }
        }
        Column {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                value = name.value,
                onValueChange = {
                    name.value = it
                })
            Spacer(modifier = Modifier.height(5.dp))
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp),
                //maxLines = 1,
                value = age.value,
                onValueChange = {
                    age.value = it
                })
        }
        //Spacer(modifier = Modifier.height(10.dp))
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp),
            onClick = {
                viewModel.saveUser(
                    User(
                        name = name.value,
                        tel = 0,
                        time = System.currentTimeMillis().toString(),
                        age = age.value
                    )
                )
            }) {
            Text(text = "Save")
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}
