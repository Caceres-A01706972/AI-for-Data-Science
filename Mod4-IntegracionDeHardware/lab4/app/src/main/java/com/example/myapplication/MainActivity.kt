package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}

@Composable
fun HelloWorld(){
    Button(
        onClick = { },
        modifier = Modifier.wrapContentSize()
    ) {
        Text(
            text = "Hola Mundooo xx ddo",
            fontSize = 16.sp
        )
    }
}

@Composable
fun UserCard(user: User){
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(12.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(12.dp)
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier
                    .background(
                        color = colorResource(id = R.color.purple_700),
                        shape = CircleShape
                    )
                    .size(120.dp),
                contentDescription = ""
            )
            Column {
                Text(text = "${user.id} ${user.name}")
                Button(
                    onClick = {
                        //perform a button click action here
                    },
                    modifier = Modifier
                        .wrapContentHeight()
                        .fillMaxWidth()
                ) {
                    Text(text = "View Profile")
                }
            }
        }
    }
}

@Composable
fun MainContent(){
    val users = mutableListOf<User>()
    for(i in 1..1000){
        users.add(User(i, "Nombre"))
    }
    UserList(users = users)
}

@Composable
fun UserList(users:List<User>){
    LazyColumn {
        items(users){user ->
            UserCard(user)
        }
    }
}


@Preview
@Composable
fun PreviewUI(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        MainContent()
    }
}

