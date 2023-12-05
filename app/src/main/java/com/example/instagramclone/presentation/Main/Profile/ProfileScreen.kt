package com.example.instagramclone.presentation.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.instagramclone.presentation.BottomNavigationItem
import com.example.instagramclone.presentation.BottomNavigationMenu
import com.example.instagramclone.util.Response
import com.example.instagramclone.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController:NavController){
    val userviewmodel: UserViewModel = hiltViewModel()
    userviewmodel.getUserInfo()
    when(val response=userviewmodel.getUserData.value){
        is Response.Loading ->{
            CircularProgressIndicator()
        }
        is Response.Success-> {
            if (response.data != null) {
                val obj = response.data
                val selectedTabIndex by remember { mutableStateOf(0) }
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier.weight(1f)) {
                        TopAppBar(
                            title = {
                                Text(text = obj.name, fontWeight = FontWeight.Bold, fontStyle = FontStyle.Italic, fontSize = 25.sp)
                            },
                            actions = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_new),
                                    contentDescription = "Create",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_hamburger),
                                    contentDescription = "More Options",
                                    tint = Color.Black,
                                    modifier = Modifier.size(30.dp)
                                )
                            },
                            backgroundColor = Color.White,
                            elevation = 10.dp
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ){
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 10.dp, start = 10.dp, bottom = 10.dp, end = 20.dp
                                    )
                            ){
                                RoundedImage(
                                    image = rememberImagePainter(data = obj.imageUrl),
                                    modifier = Modifier.size(100.dp).weight(3.5f)
                                )
                            }
                        }
                    }
                }
            }
        }
        is Response.Error->{

        }
    }
    Column(modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.weight(1f)){
            Text(text = "Profile Screen")
        }
        BottomNavigationMenu(selectedItem = BottomNavigationItem.PROFILE, navController = navController)
    }
}