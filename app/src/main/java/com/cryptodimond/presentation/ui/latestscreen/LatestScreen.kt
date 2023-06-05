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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptodimond.R
import com.cryptodimond.R.string
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.SearchView

@Composable
fun LatestScreen() {

    val viewModel = hiltViewModel<LatestViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> ContentWithProgress()
        state.error != null -> ErrorShow(text = state.error.orEmpty())
        state.coinInfoList != null -> ContentCoinInfoView(coinInfoList = state.coinInfoList!!)
    }
}

@Composable
private fun ErrorShow(text: String)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.Red
        )
    }
}

@Composable
private fun ContentCoinInfoView(coinInfoList: List<CoinInfo>) {
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
                    modifier = Modifier.fillMaxWidth()){
                    SortDirectionButton(stringResource(id = string.market_cap), true)
                    SortDirectionButton(stringResource(id = string.price_usd))
                    SortDirectionButton(stringResource(id = string.value_24h_label))
                }

            }

            Divider(color = Color.Green, thickness = 2.dp, modifier = Modifier.fillMaxWidth())

            GreetingList(coinInfoList)
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
                fontSize = 14.sp
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
private fun GreetingList(coinInfoList: List<CoinInfo>) {
    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = coinInfoList) { coinInfo ->
            CoinInfoView(
                coinInfo = coinInfo,
                index = coinInfoList.indexOf(coinInfo) + 1
            ) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
private fun CoinInfoView(coinInfo: CoinInfo, index: Int, onClick: (msg: String) -> Unit) {

    val msg = "${coinInfo.name} clicked"

    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .clickable { onClick(msg) }
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth()
        ) {
            Text(text = index.toString())
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
                bitmap = BitmapFactory
                    .decodeResource(LocalContext.current.resources, R.mipmap.bitcoin)
                    .asImageBitmap(), contentDescription = ""
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(text = coinInfo.name)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = coinInfo.cap)
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = coinInfo.price,
                color = Color.Red
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = coinInfo.income,
                color = Color.Green
            )
        }
    }

}

@Composable
@Preview
fun LatestScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "LatestScreen",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}