package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.GardenItem
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.ThemeItem
import com.example.androiddevchallenge.gardens
import com.example.androiddevchallenge.themes
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.iconDark
import com.example.androiddevchallenge.ui.theme.iconLight
import com.example.androiddevchallenge.ui.theme.white850
import com.example.androiddevchallenge.ui.widget.BloomBottomAppBar

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(themes: List<ThemeItem>, gardens: List<GardenItem>) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Content(themes, gardens)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun Content(themes: List<ThemeItem>, gardens: List<GardenItem>) {
    Scaffold(bottomBar = { BloomBottomAppBar() }) {
        ScaffoldContent(themes, gardens)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun ScaffoldContent(
    themes: List<ThemeItem>,
    gardens: List<GardenItem>
) {
    Column(Modifier.padding(top = 8.dp)) {
        SearchTextField(Modifier.padding(start = 16.dp, end = 16.dp))

        Spacer(Modifier.height(16.dp))

        BrowseThemeText(Modifier.padding(start = 16.dp, end = 16.dp))

        Spacer(Modifier.height(16.dp))

        ThemeRow(themes)

        Spacer(Modifier.height(16.dp))

        DesignYourHomeGardenText(Modifier.padding(start = 16.dp, end = 16.dp))

        Spacer(Modifier.height(16.dp))

        GardenRow(Modifier.padding(start = 16.dp), gardens)
    }
}

@ExperimentalComposeUiApi
@Composable
private fun SearchTextField(modifier: Modifier = Modifier) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchKeyword by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = searchKeyword,
        onValueChange = { searchKeyword = it },
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colors.onPrimary,
            textColor = MaterialTheme.colors.onPrimary,
        ),
        maxLines = 1,
        placeholder = { SearchTextFieldPlaceHolder() },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hideSoftwareKeyboard()
            }
        )
    )
}

@Composable
private fun SearchTextFieldPlaceHolder() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Default.Search,
            contentDescription = null,
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = stringResource(R.string.search),
            color = MaterialTheme.colors.onSurface
        )
    }
}

@Composable
private fun BrowseThemeText(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(R.string.home_browse_themes_title),
        style = MaterialTheme.typography.h1,
        color = MaterialTheme.colors.onPrimary,
        fontWeight = FontWeight.Bold,
    )
}

@Composable
private fun ThemeRow(themes: List<ThemeItem>) {
    LazyRow(
        contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(themes.size) { ThemeCard(themes[it]) }
    }
}

@Composable
private fun ThemeCard(themeItem: ThemeItem) {
    Card(
        modifier = Modifier
            .width(136.dp)
            .height(136.dp)
            .clickable { },
        shape = MaterialTheme.shapes.small,
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(96.dp),
                painter = painterResource(themeItem.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )

            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    text = themeItem.title,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

@Composable
private fun DesignYourHomeGardenText(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.home_design_garden_title),
            style = MaterialTheme.typography.h1,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
        )

        Box(Modifier.fillMaxSize()) {
            Icon(
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.CenterEnd)
                    .clickable { },
                imageVector = Icons.Default.FilterList,
                contentDescription = null,
            )
        }
    }
}

@Composable
private fun GardenRow(modifier: Modifier = Modifier, gardens: List<GardenItem>) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(gardens.size) { GardenRow(gardens[it]) }
    }
}

@Composable
private fun GardenRow(gardenItem: GardenItem) {
    var checkBoxState by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .height(64.dp)
            .clickable {
                checkBoxState = !checkBoxState
            }
    ) {
        GardenCardImage(gardenItem)
        GardenCardContent(gardenItem, checkBoxState) {
            checkBoxState = !checkBoxState
        }
    }
}

@Composable
private fun GardenCardImage(gardenItem: GardenItem) {
    Image(
        modifier = Modifier
            .width(64.dp)
            .height(64.dp)
            .clip(MaterialTheme.shapes.small),
        painter = painterResource(gardenItem.image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun GardenCardContent(
    gardenItem: GardenItem,
    checked: Boolean,
    onCheckedChange: ((Boolean) -> Unit)?
) {
    Box(Modifier.fillMaxSize()) {
        GardenItemText(gardenItem)

        Checkbox(
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd),
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkmarkColor = MaterialTheme.colors.background,
            )
        )

        Divider(
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.BottomEnd),
            startIndent = 8.dp
        )
    }
}

@Composable
private fun GardenItemText(gardenItem: GardenItem) {
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = gardenItem.title,
            style = MaterialTheme.typography.h2,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = gardenItem.subtitle,
            style = MaterialTheme.typography.body1,
            color = MaterialTheme.colors.onPrimary,
        )
    }
}

@ExperimentalComposeUiApi
@Preview("Home Screen Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenLightPreview() {
    MyTheme {
        HomeScreen(themes, gardens)
    }
}

@ExperimentalComposeUiApi
@Preview("Home Screen Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun HomeScreenDarkPreview() {
    MyTheme(darkTheme = true) {
        HomeScreen(themes, gardens)
    }
}