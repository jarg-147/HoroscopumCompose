package com.bitbiird.horoscopumcompose.presentation.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.navigation.Screen
import com.bitbiird.horoscopumcompose.ui.theme.HoroscopumTypography
import com.bitbiird.horoscopumcompose.ui.theme.cardColor
import com.bitbiird.horoscopumcompose.ui.theme.cardTextColor
import com.bitbiird.horoscopumcompose.util.enums.HoroscopeSigns

@Composable
fun SignsListScreen(navController: NavHostController) {
    SignsList(onNavigationIconClick = {
        navController.popBackStack(Screen.Home.route, false)
    }, onSignClick = { signId ->
        navController.navigate(Screen.Detail.setHoroscopeSignId(signId))
    })
}

@Composable
fun SignsList(onNavigationIconClick: () -> Unit, onSignClick: (signId: Int) -> Unit) {
    Column {
        SignListTopBar(onNavigationIconClick)
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
        ) {
            items(HoroscopeSigns.values()) { items ->
                HoroscopeItem(items) { sign ->
                    onSignClick(sign)
                }
            }
        }
    }
}

@Composable
fun HoroscopeItem(sign: HoroscopeSigns, onItemClick: (signId: Int) -> Unit) {
    Card(elevation = 2.dp,
        backgroundColor = MaterialTheme.colors.cardColor,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(sign.id)
            }) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = sign.signIcon), contentDescription = "Sign icon", Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(id = sign.signName),
                style = HoroscopumTypography.body2,
                color = MaterialTheme.colors.cardTextColor,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(id = sign.signDate),
                style = HoroscopumTypography.caption,
                color = MaterialTheme.colors.cardTextColor,
                fontStyle = FontStyle.Italic
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignListTopBar(onNavigationIconClick: () -> Unit) {
    CenterAlignedTopAppBar(modifier = Modifier.fillMaxWidth(), title = {
        Text(
            text = stringResource(id = R.string.horoscope_list_title), style = HoroscopumTypography.h3
        )
    }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Transparent), navigationIcon = {
        IconButton(onNavigationIconClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back), contentDescription = "Navigation back", tint = Color.White
            )
        }
    })
}