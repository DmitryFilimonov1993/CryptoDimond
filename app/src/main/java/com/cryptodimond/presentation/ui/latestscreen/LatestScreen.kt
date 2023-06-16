package com.cryptodimond.presentation.ui.latestscreen

import android.graphics.BitmapFactory
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.cryptodimond.R
import com.cryptodimond.R.string
import com.cryptodimond.domain.util.coin.CoinDetailsInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.DynamicLabelView
import com.cryptodimond.presentation.ui.ErrorShow
import com.cryptodimond.presentation.ui.SearchView
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.headerTextBold

@Composable
fun LatestScreen(onClick: (String) -> Unit) {

    val viewModel = hiltViewModel<LatestViewModel>()
    val data = viewModel.rec.collectAsLazyPagingItems()
    //val state by viewModel.state.collectAsState()
    //val pullRefreshState = rememberPullRefreshState(state.isLoading, { viewModel.load() })

    ContentCoinInfoView(coinInfoList = data, onClick = onClick)


    when(val state = data.loadState.prepend) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            ContentWithProgress()
        }
        is LoadState.Error -> {
            ErrorShow(text = state.error.message?: "fail")
        }
    }

    when(val state = data.loadState.refresh) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            ContentWithProgress()
        }
        is LoadState.Error -> {
            ErrorShow(text = state.error.message?:"fail")
        }
    }

    when(val state = data.loadState.append) {
        is LoadState.NotLoading -> Unit
        is LoadState.Loading -> {
            ContentWithProgress()
        }
        is LoadState.Error -> {
            ErrorShow(text = state.error.message?: "fail")
        }
    }
}

@Composable
private fun ContentCoinInfoView(coinInfoList: LazyPagingItems<CoinDetailsInfo>, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column {
            Column(
                modifier = Modifier
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                val textState = remember { mutableStateOf(TextFieldValue("")) }
                SearchView(
                    hint = "Search for tokens...",
                    state = textState
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row (horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)){
                    SortDirectionButton(stringResource(id = string.market_cap), true)
                    SortDirectionButton(stringResource(id = string.price_usd))
                    SortDirectionButton(stringResource(id = string.value_24h_label))
                }

            }

            Divider(color = Color.Green, thickness = 2.dp, modifier = Modifier.fillMaxWidth())

            GreetingList(coinInfoList, onClick)
        }
    }
}

@Composable
private fun SortDirectionButton(
    text: String,
    selected: Boolean = false,
    click: (() -> Unit)? = null
){
    IconButton(onClick = { /*TODO*/ }) {

        Row {
            Text(
                text = text,
                style = MaterialTheme.typography.headerTextBold
            )
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                bitmap = BitmapFactory
                    .decodeResource(LocalContext.current.resources, R.mipmap.arrow)
                    .asImageBitmap(),
                contentDescription = "",
                modifier = Modifier
                    .size(16.dp)
                    .align(CenterVertically),
                tint = if (selected) Color(ContextCompat.getColor(LocalContext.current, R.color.blue_sky))
                else Color(ContextCompat.getColor(LocalContext.current, R.color.gray))
            )
        }
    }
}

@Composable
private fun GreetingList(coinInfoList: LazyPagingItems<CoinDetailsInfo>, onClick: (String) -> Unit) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier
        .padding(vertical = 4.dp)
        ) {
        items(
            count = coinInfoList.itemCount,
            key = coinInfoList.itemKey(),
            contentType = coinInfoList.itemContentType()
        ) { index ->
            val item = coinInfoList[index]
            CoinInfoView(
                coinInfo = item!!,
                index = index + 1
            ) {
                onClick.invoke(it)
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CoinInfoView(coinInfo: CoinDetailsInfo, index: Int, onClick: (msg: String) -> Unit) {

    Card(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 12.dp)
            .height(64.dp)
            .clickable { onClick(coinInfo.id) }
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 10.dp, end = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()) {
                Text(
                    text = index.toString(),
                    modifier = Modifier
                        .align(CenterVertically),
                    style = MaterialTheme.typography.commonTextStyle
                )
                Spacer(modifier = Modifier.width(16.dp))
                AsyncImage(
                    model = coinInfo.logo,
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column() {
                    Text(
                        text = coinInfo.name,
                        style = MaterialTheme.typography.commonTextStyle
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = coinInfo.cap,
                        style = MaterialTheme.typography.commonTextStyle)
                }
            }
            Text(
                text = coinInfo.price,
                style = MaterialTheme.typography.commonTextStyle
            )
            DynamicLabelView(value = coinInfo.income, postfix = "%")
        }
    }

}