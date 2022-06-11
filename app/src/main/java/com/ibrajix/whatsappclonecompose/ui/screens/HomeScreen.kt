package com.ibrajix.whatsappclonecompose.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.ui.model.TabItem
import com.ibrajix.whatsappclonecompose.ui.theme.topBarTextColor
import com.ibrajix.whatsappclonecompose.viewmodel.StorageViewModel
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch
import java.util.*

@Destination
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier){

    Column(
        modifier = modifier
            .background(MaterialTheme.colors.background),
    ){
        TopBar()
    }

}

private val tabs = listOf(
    TabItem.Camera,
    TabItem.Chat,
    TabItem.Status,
    TabItem.Call
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        backgroundColor = MaterialTheme.colors.primary)
    {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            //title and switch
            TitleAndSwitch(modifier, R.string.whatsapp)

        }
    }

    //tab layout with horizontal scrolling
    TabLayout(tabs = tabs, selectedIndex = pagerState.currentPage ,
        onPageSelected = { tabItem->
            coroutineScope.launch {
                pagerState.animateScrollToPage(tabItem.index)
            }
        })

    TabPage(pagerState = pagerState, tabItem = tabs)

}

@Composable
fun TabLayout(
    modifier: Modifier = Modifier,
    tabs: List<TabItem>,
    selectedIndex: Int,
    onPageSelected: ((tabItem: TabItem) -> Unit)
) {

    TabRow(
        selectedTabIndex = selectedIndex,
        divider = { }
    ) {

        tabs.forEachIndexed{index, tabItem ->
            Tab(
                selected = index == selectedIndex,
                modifier = modifier.background(MaterialTheme.colors.primary),
                onClick = {
                onPageSelected(tabItem)
            },
                text =
                {
                    if (tabItem == TabItem.Camera) {
                        Icon(painter = painterResource(id = R.drawable.ic_camera), stringResource(id = R.string.icon)).toString()
                    }

                    else {
                        Text(
                            text = stringResource(id = tabItem.title).uppercase(Locale.ROOT),
                            style = MaterialTheme.typography.caption,
                        )
                    }

                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabPage(pagerState: PagerState, tabItem: List<TabItem>){

    HorizontalPager(
        count = tabs.size,
        state = pagerState,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxSize()
    ) {index->
        tabItem[index].screenToLoad()
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
