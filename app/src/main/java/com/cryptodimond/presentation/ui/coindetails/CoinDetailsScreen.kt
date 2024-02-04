package com.cryptodimond.presentation.ui.coindetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.cryptodimond.R
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.DynamicLabelView
import com.cryptodimond.presentation.ui.ErrorShow
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.descriptionTextStyle
import com.cryptodimond.presentation.ui.theme.headerTextBold
import com.cryptodimond.presentation.ui.theme.smallTextMedium
import com.cryptodimond.presentation.ui.theme.titleTextBold
import com.cryptodimond.presentation.ui.theme.titleTextMedium

@Composable
fun CoinDetailsScreen(onClick: (String) -> Unit, id: String? = null) {

    val viewModel = hiltViewModel<CoinDetailsViewModel>()
    val data by viewModel.state.collectAsState()
    //ErrorShow(text = id.toString())
    when {
        data.isLoading -> ContentWithProgress()
        data.error != null -> ErrorShow(text = data.error.orEmpty())
        data.coinInfoList != null -> ShowContent(data.coinInfoList!!, onClick = onClick)
    }

}

@Composable
fun ShowContent(coinInfoList: CoinDetailsInfo, onClick: (String) -> Unit) {
    Column() {
        HeaderCoinDetailsView(coinInfoList, onClick)
        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = LocalContext.current.getString(R.string.market_cap_with_arg, coinInfoList.cap),
            style = MaterialTheme.typography.headerTextBold,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = coinInfoList.description,
            style = MaterialTheme.typography.descriptionTextStyle,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }
}
@Composable
fun ColumnScope.HeaderCoinDetailsView(coinInfoList: CoinDetailsInfo, onClick: (String) -> Unit) {
    Row(modifier = Modifier.padding(12.dp)) {
        AsyncImage(
            model = coinInfoList.logo,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center,
            modifier = Modifier.size(90.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(horizontalAlignment = Start) {
            Text(
                text = coinInfoList.name,
                style = MaterialTheme.typography.titleTextBold
            )
            Text(
                text = coinInfoList.symbol,
                style = MaterialTheme.typography.titleTextMedium
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = LocalContext.current.getString(R.string.last_update_with_args, coinInfoList.dataLaunched),
                style = MaterialTheme.typography.smallTextMedium
            )
        }
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
    ) {

        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = "$" + coinInfoList.price,
                style = MaterialTheme.typography.titleTextBold
            )
            DynamicLabelView(value = coinInfoList.income, postfix = "%")
        }
        Button(
            onClick = { onClick(coinInfoList.id) },
            modifier = Modifier
                .width(200.dp)
                .height(45.dp)
                .align(CenterVertically),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(
                    ContextCompat.getColor(
                        LocalContext.current,
                        R.color.gray_light
                    )
                )
            )
        ) {
            Text(
                text = "View exchanges",
                style = MaterialTheme.typography.commonTextStyle,
                modifier = Modifier.align(CenterVertically)
            )
        }
    }
}
