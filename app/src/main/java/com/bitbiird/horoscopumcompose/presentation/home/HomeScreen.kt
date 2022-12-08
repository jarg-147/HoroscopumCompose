package com.bitbiird.horoscopumcompose.presentation.home

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
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

    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.primary)
            .padding(top = paddingValues.calculateTopPadding() + 16.dp)
            .padding(horizontal = 24.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                focusManager.clearFocus(force = true)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HomeHeader(searchText = searchText, focusRequester = focusRequester, modifier = Modifier.fillMaxWidth()) { newText -> searchText = newText }

        Spacer(modifier = Modifier.height(16.dp))

        SignsList(searchText = searchText, onSignClick = onSignCardClick, modifier = Modifier.fillMaxWidth())
    }
}

@Composable
fun HomeHeader(searchText: String, focusRequester: FocusRequester, modifier: Modifier, onSearchTextChanged: (newText: String) -> Unit) {

    Text(
        stringResource(id = R.string.app_name),
        style = HoroscopumTypography.h1,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary,
        modifier = modifier
    )

    Spacer(modifier = modifier.height(8.dp))

    Text(
        stringResource(id = R.string.daily_horoscope_text),
        style = HoroscopumTypography.h4,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.onPrimary,
        fontStyle = FontStyle.Italic,
        modifier = modifier
    )

    Spacer(modifier = modifier.height(32.dp))

    OutlinedTextField(
        value = searchText,
        modifier = modifier
            .focusRequester(focusRequester),
        textStyle = HoroscopumTypography.h3.copy(fontWeight = FontWeight.Bold),
        singleLine = true,
        shape = MaterialTheme.shapes.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = MaterialTheme.colors.onSurface,
            backgroundColor = MaterialTheme.colors.surface,
            focusedBorderColor = MaterialTheme.colors.onSurface,
            cursorColor = MaterialTheme.colors.onSurface
        ),
        trailingIcon = {
            if (searchText.isNotBlank()) {
                IconButton(
                    onClick = {
                        onSearchTextChanged("")
                    },
                    content = {
                        Icon(imageVector = Icons.Default.Clear, contentDescription = "Clear icon")
                    })
            }
        },
        label = {
            Box {
                Text(
                    text = stringResource(id = R.string.search_your_sign_hint),
                    style = HoroscopumTypography.h4,
                    color = MaterialTheme.colors.onPrimary.copy(alpha = 0.8f),
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .align(Center)
                )
            }
        },
        onValueChange = { newText ->
            onSearchTextChanged(newText)
        }
    )

    Spacer(modifier = modifier.height(24.dp))

    Text(
        text = stringResource(id = R.string.select_your_sign_text),
        style = HoroscopumTypography.h2,
        color = MaterialTheme.colors.onPrimary,
        textAlign = TextAlign.Center,
        modifier = modifier
    )

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SignsList(searchText: String, modifier: Modifier, onSignClick: (signId: Int) -> Unit) {

    val itemsList = if (searchText.isBlank()) {
        HoroscopeSigns.values().toList()
    } else {
        HoroscopeSigns.values().filter { sign -> stringResource(id = sign.signName).contains(searchText, ignoreCase = true) }
    }

    val paddingValues = WindowInsets.systemBars.asPaddingValues()

    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier
                .padding(bottom = paddingValues.calculateBottomPadding())
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
        backgroundColor = MaterialTheme.colors.surface,
        shape = MaterialTheme.shapes.large,
        onClick = { onItemClick(sign.id) },
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                spotColor = Color.Black,
                shape = MaterialTheme.shapes.large
            )
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = sign.signIcon),
                contentDescription = "Sign icon",
                modifier = Modifier.size(56.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(id = sign.signName),
                style = HoroscopumTypography.body2,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = sign.signDate),
                style = HoroscopumTypography.caption,
                color = MaterialTheme.colors.onSurface,
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