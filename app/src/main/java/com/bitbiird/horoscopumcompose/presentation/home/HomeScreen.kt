package com.bitbiird.horoscopumcompose.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bitbiird.horoscopumcompose.R
import com.bitbiird.horoscopumcompose.navigation.Screen
import com.bitbiird.horoscopumcompose.ui.theme.*
import com.bitbiird.horoscopumcompose.util.enums.HoroscopeSigns

@Composable
fun HomeScreen(navController: NavController) {
    Home(onSignCardClick = { signId ->
        navController.navigate(Screen.Detail.setHoroscopeSignId(signId))
    })
}

@Composable
fun Home(
    onSignCardClick: (signId: Int) -> Unit,
) {

    var searchText by remember { mutableStateOf("") }

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.homeBackgroundColor)
            .padding(top = 48.dp)
            .clickable(interactionSource = interactionSource, indication = null) {
                focusManager.clearFocus(force = true)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        Text(
            stringResource(id = R.string.app_name),
            style = HoroscopumTypography.h1,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.homeTitleColor,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
        )

        OutlinedTextField(
            value = searchText,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
                .padding(horizontal = 24.dp)
                .focusRequester(focusRequester),
            textStyle = HoroscopumTypography.h3,
            singleLine = true,
            label = {
                Box {
                    Text(
                        text = stringResource(id = R.string.search_your_sign_hint),
                        style = HoroscopumTypography.h4,
                        color = MaterialTheme.colors.homeTitleColor.copy(alpha = 0.8f),
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .align(Center)
                    )
                }
            },
            onValueChange = { newText ->
                searchText = newText
            }
        )

        Text(
            text = stringResource(id = R.string.select_your_sign_text),
            style = HoroscopumTypography.h2,
            color = MaterialTheme.colors.homeTitleColor,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(horizontal = 24.dp)
        )

        SignsList(searchText = searchText, onSignClick = onSignCardClick)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignsList(searchText: String, onSignClick: (signId: Int) -> Unit) {

    val itemsList = if (searchText.isBlank()) {
        HoroscopeSigns.values().toList()
    } else {
        HoroscopeSigns.values().filter { sign -> stringResource(id = sign.signName).contains(searchText, ignoreCase = true) }
    }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(bottom = 24.dp),
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .padding(bottom = 32.dp)
                .padding(top = 16.dp)
        ) {
            items(itemsList, key = { it.id }) { item ->
                HoroscopeItem(Modifier.animateItemPlacement(), item) { sign ->
                    onSignClick(sign)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HoroscopeItem(modifier: Modifier, sign: HoroscopeSigns, onItemClick: (signId: Int) -> Unit) {
    Card(
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.cardColor,
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        onClick = { onItemClick(sign.id) },
        modifier = modifier
            .fillMaxWidth()
    ) {
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    Home {}
}