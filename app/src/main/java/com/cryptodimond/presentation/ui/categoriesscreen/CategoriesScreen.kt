package com.cryptodimond.presentation.ui.categoriesscreen


import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptodimond.domain.util.categories.CoinCategory
import com.cryptodimond.domain.util.coin.CoinInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.ErrorShow
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.headerTextBold


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoriesScreen() {

    val viewModel = hiltViewModel<CoinCategoriesViewModel>()
    val state by viewModel.state.collectAsState()
    val pullRefreshState = rememberPullRefreshState(state.isLoading, { viewModel.load() })


    when {
        state.isLoading -> ContentWithProgress()
        state.error != null -> ErrorShow(text = state.error.orEmpty())
        state.coinCategories != null -> GreetingList(state.coinCategories!!, pullRefreshState) { viewModel.load() }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun GreetingList(coinInfoList: List<CoinCategory>, pullRefreshState: PullRefreshState, doWork: () -> Unit) {

        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .pullRefresh(pullRefreshState)
        ) {
            items(items = coinInfoList) { category ->
                CategoriesItemView(
                    category = category
                ) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    doWork.invoke()
                }
            }
        }
}

@Composable
private fun CategoriesItemView(category: CoinCategory, onClick: (msg: String) -> Unit) {

    val msg = "${category.name} clicked"

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .clickable { onClick(msg) }
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 10.dp, end = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = category.name,
                    style = MaterialTheme.typography.headerTextBold
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = category.tokens + " tokens",
                    style = MaterialTheme.typography.commonTextStyle
                )
            }

            Column(verticalArrangement = Arrangement.Center) {
                Text(
                    text = category.volume,
                    style = MaterialTheme.typography.commonTextStyle
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = category.volumeChange,
                    style = MaterialTheme.typography.commonTextStyle
                )
            }
        }
    }
}