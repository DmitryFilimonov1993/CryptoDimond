package com.cryptodimond.presentation.ui.exchangedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cryptodimond.R
import com.cryptodimond.domain.model.exchanges.ExchangesInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.ErrorShow
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.descriptionTextStyle
import com.cryptodimond.presentation.ui.theme.headerTextBold
import com.cryptodimond.presentation.ui.theme.smallTextBold
import com.cryptodimond.presentation.ui.theme.smallTextMedium
import com.cryptodimond.presentation.ui.theme.titleTextBold

@Composable
fun ExchangesDetailsScreen() {

    val viewModel = hiltViewModel<ExchangesDetailsViewModel>()
    val data by viewModel.state.collectAsState()
    //ErrorShow(text = id.toString())
    when {
        data.isLoading -> ContentWithProgress()
        data.error != null -> ErrorShow(text = data.error.orEmpty())
        data.exchangeInfo != null -> ShowContent(data.exchangeInfo!!)
    }
}

@Composable
fun ShowContent(exchangesInfo: ExchangesInfo) {
    Column() {
        HeaderCoinDetailsView(exchangesInfo)
        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Text(
                text = LocalContext.current.getString(R.string.link_text),
                style = MaterialTheme.typography.headerTextBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = exchangesInfo.link,
                style = MaterialTheme.typography.commonTextStyle,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Text(
                text = LocalContext.current.getString(R.string.weekly_visits),
                style = MaterialTheme.typography.headerTextBold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = exchangesInfo.visits,
                style = MaterialTheme.typography.commonTextStyle,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = exchangesInfo.description,
            style = MaterialTheme.typography.descriptionTextStyle,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState())

        )
    }
}

@Composable
fun ColumnScope.HeaderCoinDetailsView(exchangesInfo: ExchangesInfo) {
    Row(modifier = Modifier.padding(12.dp)) {
        AsyncImage(
            model = exchangesInfo.logo,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(horizontalAlignment = Start) {
            Text(
                text = exchangesInfo.name,
                style = MaterialTheme.typography.titleTextBold
            )
            Text(
                text = LocalContext.current.getString(R.string.volume_with_args, exchangesInfo.volumeUSD),
                style = MaterialTheme.typography.smallTextBold
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = LocalContext.current.getString(R.string.last_update_with_args, exchangesInfo.dataLaunched),
                style = MaterialTheme.typography.smallTextMedium
            )
        }
    }
    Spacer(modifier = Modifier.height(6.dp))

    Text(
        modifier = Modifier.padding(12.dp),
        text = LocalContext.current.getString(R.string.supported_fiat_with_args, exchangesInfo.fiats),
        style = MaterialTheme.typography.smallTextMedium
    )
}
