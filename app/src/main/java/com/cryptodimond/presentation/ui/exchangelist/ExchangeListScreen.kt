package com.cryptodimond.presentation.ui.exchangelist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
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
import com.cryptodimond.presentation.ui.DynamicLabelView
import com.cryptodimond.presentation.ui.coindetails.CoinDetailsViewModel
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.headerTextBold
import com.cryptodimond.presentation.ui.theme.smallTextMedium
import com.cryptodimond.presentation.ui.theme.titleTextBold

@Composable
fun ExchangeListScreen() {

    val viewModel = hiltViewModel<CoinDetailsViewModel>()
    val data = viewModel.state.collectAsState()
    viewModel.load("")

    Column {
        HeaderCoinDetailsView()
        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "",
            style = MaterialTheme.typography.headerTextBold
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "",
            style = MaterialTheme.typography.commonTextStyle
        )
    }
}

@Composable
fun ColumnScope.HeaderCoinDetailsView() {
    Row() {
        AsyncImage(
            model = "",
            contentDescription = "",
            contentScale = ContentScale.Crop,
            alignment = Alignment.Center
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(horizontalAlignment = Start) {
            Text(
                text = "",
                style = MaterialTheme.typography.titleTextBold
            )
            Text(
                text = "",
                style = MaterialTheme.typography.headerTextBold
            )
            Text(
                text = "",
                style = MaterialTheme.typography.smallTextMedium
            )
        }
    }
    Spacer(modifier = Modifier.height(6.dp))
    Row(horizontalArrangement = Arrangement.SpaceBetween) {

        Column(horizontalAlignment = Start) {
            Text(
                text = "",
                style = MaterialTheme.typography.titleTextBold
            )
            DynamicLabelView(value = 0.0)
        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .background(color = Color(ContextCompat.getColor(LocalContext.current, R.color.gray_light)))
                .width(70.dp)
                .height(30.dp)
        ) {
            Text(
                text = "",
                style = MaterialTheme.typography.commonTextStyle
            )
        }
    }
}
