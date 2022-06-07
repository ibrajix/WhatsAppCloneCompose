package com.ibrajix.whatsappclonecompose.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.ui.theme.topBarTextColor
import com.ibrajix.whatsappclonecompose.viewmodel.StorageViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ChatScreen(
    navigator: DestinationsNavigator,
    modifier: Modifier = Modifier){

    Column(
        modifier = modifier
        .fillMaxSize(),
    ){
        TopBar()
    }

}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.primary)
            .padding(20.dp)
    ) {

        TitleAndSwitch(modifier, R.string.whatsapp)
        /*TabViews(modifier)*/

    }

}

@Composable
fun TitleAndSwitch(
    modifier: Modifier = Modifier,
    @StringRes title: Int,
){
    Row(
        modifier
            .padding(0.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    )
    {

        //whatsapp
        Text(
            text = stringResource(id = title),
            color = MaterialTheme.colors.topBarTextColor,
            style = MaterialTheme.typography.body1,
        )

        //checkbox
        val checkedState = rememberSaveable { mutableStateOf(false) }
        StatelessSwitch(switchState = checkedState)

    }

}

@Composable
private fun StatelessSwitch(switchState: MutableState<Boolean>, storageViewModel: StorageViewModel = hiltViewModel() ) {

    val currentTheme = storageViewModel.selectedTheme.observeAsState()

    currentTheme.value?.let { it ->
        Switch(
        checked = it,
        onCheckedChange = {
            switchState.value = it
            storageViewModel.changeSelectedTheme(it)
        },

        colors = SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colors.surface,
            uncheckedThumbColor = MaterialTheme.colors.surface,
            checkedTrackColor = MaterialTheme.colors.topBarTextColor,
            uncheckedTrackColor = MaterialTheme.colors.topBarTextColor,
            checkedTrackAlpha = 1.0f,
            uncheckedTrackAlpha = 1.0f,
        ),
    )
    }

}

/*
@Composable
fun TabViews(modifier: Modifier = Modifier){

    var selectedTab by remember {
        mutableStateOf(0)
    }

    Row(modifier = modifier
        .fillMaxWidth()
        .padding(0.dp)
    ) {

        Image(painter = painterResource(R.drawable.ic_camera),
            contentDescription = stringResource(id = R.string.icon))

        TextTabs(modifier = modifier) {
            selectedTab = it
        }

    }

}
*/

/*@Composable
fun TextTabs(
    modifier: Modifier = Modifier,
    onTabSelected: (selectedIndex: Int) -> Unit

){

    val tabData = listOf(
       stringResource(id = R.string.chats),
       stringResource(id = R.string.status),
       stringResource(id = R.string.calls)
    )

    val pagerState = rememberPagerState(
        initialPage = 1
    )

    var selectedTabIndex by remember {
        mutableStateOf(0)
    }

    val inactiveColor = MaterialTheme.colors.tabInActiveColor
    val activeColor = MaterialTheme.colors.tabActiveColor

    TabRow(
        contentColor = activeColor,
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Transparent,
        modifier = modifier,
    ){
        Tab(selected = selectedTabIndex == 0,
            selectedContentColor = activeColor,
            unselectedContentColor = inactiveColor,
            onClick = {
            selectedTabIndex = 0
            onTabSelected(0)
        }) {
            Text(text = stringResource(id = R.string.chats))
        }

        Tab(selected = selectedTabIndex == 0,
            selectedContentColor = activeColor,
            unselectedContentColor = inactiveColor,
            onClick = {
                selectedTabIndex = 0
                onTabSelected(0)
            }) {
            Text(text = stringResource(id = R.string.status))
        }

        Tab(selected = selectedTabIndex == 0,
            selectedContentColor = activeColor,
            unselectedContentColor = inactiveColor,
            onClick = {
                selectedTabIndex = 0
                onTabSelected(0)
            }) {
            Text(text = stringResource(id = R.string.calls))
        }


    }

}*/
