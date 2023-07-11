package com.example.littlelemonfinal.ui.views

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.littlelemonfinal.R
import com.example.littlelemonfinal.model.data.MenuItemNetwork
import com.example.littlelemonfinal.ui.theme.GrayLight
import com.example.littlelemonfinal.ui.theme.GreenGrayDark
import com.example.littlelemonfinal.ui.theme.White
import com.example.littlelemonfinal.ui.theme.Yellow
import java.util.Locale
import com.example.littlelemonfinal.model.services.AppDatabase
import com.example.littlelemonfinal.ui.navigation.Profile
import com.example.littlelemonfinal.ui.theme.CreamLight
import com.example.littlelemonfinal.viewmodel.HomeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel=hiltViewModel(),
) {
    val mContext = LocalContext.current;
    var menuItems = remember {
        mutableStateOf(emptyList<MenuItemNetwork>())
    }
    var searchText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()







    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            menuItems.value= homeViewModel.getMenuFromDataBase().observeAsState().value ?: emptyList()
        if(homeViewModel.menuItemsLiveData.observeAsState().value?.isEmpty() == true){
            homeViewModel.fetchMenuFromServer()
            menuItems.value = homeViewModel.menuItemsLiveData.observeAsState().value ?: emptyList()
        }


        val categories=menuItems.value.map {
            it.category
        }
        Surface(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image",
                contentScale = ContentScale.Fit
            )

            Row (horizontalArrangement = Arrangement.End){
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "Profile Image",
                    Modifier.clickable {
                        navController.navigate(Profile.route)
                    }
                    )
            }
        }


        Column(
            Modifier
                .background(GreenGrayDark)
                .padding(horizontal = 12.dp, vertical = 16.dp)
        ) {
            Text(
                text = "Little Lemon",
                Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start,
                fontSize = 40.sp,
                color = Yellow
            )

            Row() {
                Column() {
                    Text(text = "Chicago", fontSize = 24.sp, color = White)
                    Box(Modifier.padding(top = 12.dp)) {
                        Text(
                            text = "We are a family owned \nMediterranean restaurant.\nfocused on traditional\nrecipes served with a\nmodern twist.",
                            color = White, fontSize = 18.sp
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.upperpanelimage),
                    contentDescription = "Hero Image",
                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(20.dp)
                        )
                )
            }

            TextField(shape = RoundedCornerShape(10.dp),
                value = searchText,
                onValueChange = { it -> searchText = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 12.dp, end = 12.dp)
                    .background(color = White, shape = RoundedCornerShape(10.dp)),

                colors = TextFieldDefaults.textFieldColors(focusedIndicatorColor = Color.Transparent),
                placeholder = { Text("Enter Search Phrase") },
                leadingIcon = { Icon( imageVector = Icons.Default.Search, contentDescription = "") }
            )
        }





        Column(
            Modifier
                .padding(start = 12.dp, end = 12.dp, top = 30.dp, bottom = 10.dp)
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Text(text = "ORDER FOR DELIVERY", fontSize = 18.sp, fontWeight = FontWeight.Bold)

            Row(Modifier.horizontalScroll(rememberScrollState())) {
                repeat(categories.size) {
                    var orderMenuItems by remember {
                        mutableStateOf(false)
                    }

                    var categoryChecked by remember {
                        mutableStateOf(false)
                    }
                    Box(
                        Modifier
                            .padding(end = 20.dp, top = 15.dp, bottom = 20.dp)
                            .background(
                                if (!categoryChecked) Color(0x36424644) else CreamLight,
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clickable {
                                categoryChecked = !categoryChecked;
                                orderMenuItems = false;
                            }
                    ) {
                        Text(
                            text =categories[it],
                            modifier = Modifier.padding(8.dp),
                            color = GreenGrayDark,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Divider(thickness = 1.dp, color = GrayLight)
        }

        if (searchText.isNotEmpty()) {
            val searchedItems = menuItems.value.filter {
                it.title.lowercase(Locale.getDefault()).contains(searchText)
            }
            MenuItems(searchedItems)

        } else {
            MenuItems(menuItems.value)

        }


    }


}

@Composable
fun MenuItems(menuItemsRoom: List<MenuItemNetwork>) {
    LazyColumn {
        items(menuItemsRoom) { Dish ->
            MenuItems(Dish)
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun MenuItems(dish: MenuItemNetwork) {
    Card(Modifier.padding(12.dp), elevation = 0.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
        ) {
            Column {
                Text(
                    text = dish.title, fontSize = 18.sp, fontWeight = FontWeight.Bold
                )
                Text(
                    text = dish.description,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(top = 5.dp, bottom = 5.dp)
                        .fillMaxWidth(.75f)
                )
                Text(
                    text = dish.price, color = Color.Gray, fontWeight = FontWeight.Bold
                )
            }
            GlideImage(
                model = dish.image,
                contentDescription = dish.title,
                Modifier.clip(RoundedCornerShape(10.dp))
            )

        }
    }
    Divider(
        modifier = Modifier.padding(start = 8.dp, end = 8.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )

}


@Composable
@Preview
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}

