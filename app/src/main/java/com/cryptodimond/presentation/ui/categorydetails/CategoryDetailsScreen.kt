package com.cryptodimond.presentation.ui.categorydetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
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
import com.cryptodimond.domain.model.categories.CategoryDetails
import com.cryptodimond.domain.model.coin.CoinDetailsInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.DynamicLabelView
import com.cryptodimond.presentation.ui.ErrorShow
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.descriptionTextStyle
import com.cryptodimond.presentation.ui.theme.smallTextMedium
import com.cryptodimond.presentation.ui.theme.titleTextBold

@Composable
fun CategoryDetailsScreen() {

    val viewModel = hiltViewModel<CategoryDetailsViewModel>()
    val data by viewModel.state.collectAsState()
    when {
        data.isLoading -> ContentWithProgress()
        data.error != null -> ErrorShow(text = data.error.orEmpty())
        data.categoryDetails != null -> ShowContent(data.categoryDetails!!)
    }
}

@Composable
fun ShowContent(categoryDetails: CategoryDetails) {
    Column() {
        HeaderCoinDetailsView(categoryDetails)
        Divider(color = Color.Gray, thickness = 2.dp, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        GreetingList(categoryDetails.coins)
    }
}

@Composable
fun ColumnScope.HeaderCoinDetailsView(categoryDetails: CategoryDetails) {
    Row(modifier = Modifier.padding(12.dp)) {
        Column(horizontalAlignment = Start) {
            Text(
                text = categoryDetails.title,
                style = MaterialTheme.typography.titleTextBold
            )
            Text(
                text = categoryDetails.description,
                style = MaterialTheme.typography.descriptionTextStyle
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = LocalContext.current.getString(R.string.volume_with_args, categoryDetails.volume),
                style = MaterialTheme.typography.smallTextMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            DynamicLabelView(value = categoryDetails.income, postfix = "%")
            Spacer(modifier = Modifier.height(28.dp))
        }
    }
}

@Composable
private fun GreetingList(coinInfoList: List<CoinDetailsInfo>) {
    LazyColumn(
        modifier = Modifier
            .padding(vertical = 4.dp)
    ) {
        itemsIndexed(items = coinInfoList) { index, category ->
            CoinInfoView(
                coinInfo = category,
                index = index + 1
            )
        }
    }
}

@Composable
private fun CoinInfoView(coinInfo: CoinDetailsInfo, index: Int) {

    Card(
        modifier = Modifier
            .padding(vertical = 2.dp, horizontal = 12.dp)
            .height(64.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 10.dp, end = 18.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()) {
                Text(
                    text = index.toString(),
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
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