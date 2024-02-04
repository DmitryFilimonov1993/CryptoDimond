package com.cryptodimond.presentation.ui.exchangesscreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cryptodimond.domain.model.exchanges.ExchangesInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.ErrorShow
import com.cryptodimond.presentation.ui.SearchView
import com.cryptodimond.presentation.ui.theme.commonTextStyle

@Composable
fun ExchangesScreen(onClick: (String) -> Unit) {

    val viewModel = hiltViewModel<ExchangesInfoViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> ContentWithProgress()
        state.error != null -> ErrorShow(text = state.error.orEmpty()){viewModel.load()}
        state.exchangeInfoList != null -> ExchangesInfoView(
            exchangeInfoList = state.exchangeInfoList!!,
            onClick = onClick
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun ExchangesInfoView(exchangeInfoList: List<ExchangesInfo>, onClick: (String) -> Unit) {
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
                    hint = "Search for exchanges...",
                    state = textState
                )

                Spacer(modifier = Modifier.height(24.dp))

            }

            ExchangesList(exchangeInfoList, onClick)
        }
    }
}

@Composable
private fun ExchangesList(exchangeInfoList: List<ExchangesInfo>, onClick: (String) -> Unit) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = exchangeInfoList) { exchangeInfo ->
            ExchangeInfoView(
                exchangesInfo = exchangeInfo
            ) {
                onClick.invoke(it)
            }
        }
    }
}

@Composable
private fun ExchangeInfoView(exchangesInfo: ExchangesInfo, onClick: (msg: String) -> Unit) {


    Card(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 12.dp)
            .clickable { onClick(exchangesInfo.id.toString()) }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(start = 10.dp, end = 38.dp, top = 12.dp, bottom = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //val (image, column) = cr

            AsyncImage(
                model = exchangesInfo.logo,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center
            )
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    text = exchangesInfo.name,
                    style = MaterialTheme.typography.commonTextStyle
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "launched at " + exchangesInfo.dataLaunched,
                    style = MaterialTheme.typography.commonTextStyle
                )
            }
            Text(
                text = exchangesInfo.volumeUSD,
                style = MaterialTheme.typography.commonTextStyle
            )
        }
    }
}

@Composable
@Preview
fun ExchangesScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ExchangesScreen",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}