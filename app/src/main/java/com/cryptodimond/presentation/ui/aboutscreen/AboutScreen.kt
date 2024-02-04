package com.cryptodimond.presentation.ui.aboutscreen

import com.cryptodimond.R as Res
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cryptodimond.domain.model.apikey.ApiUsageInfo
import com.cryptodimond.presentation.ui.ContentWithProgress
import com.cryptodimond.presentation.ui.ErrorShow

@Composable
fun AboutScreen() {

    val viewModel = hiltViewModel<AboutApiUsageViewModel>()
    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> ContentWithProgress()
        state.error != null -> ErrorShow(text = state.error.orEmpty())
        state.apiUsageInfo != null -> TextFunView(state.apiUsageInfo!!)
    }
}

@Composable
private fun ResultCard(result: String) {
    Text(
        text = result,
        fontSize = MaterialTheme.typography.subtitle1.fontSize,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        textAlign = TextAlign.Start,
    )
}

@Composable
private fun TextFunView(appUsageInfo: ApiUsageInfo)
{
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp)
    ) {

        val context = LocalContext.current

        Column(
            modifier = Modifier
                .fillMaxSize(),
                //.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            ResultCard(context.getString(Res.string.credit_limit_monthly_reset, appUsageInfo.creditMonthlyReset))
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = context.getString(Res.string.credit_monthly_limit, appUsageInfo.currentMonthlyLimit),
                fontSize = MaterialTheme.typography.subtitle1.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = context.getString(Res.string.current_day),
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row (horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()){
                ResultCard(context.getString(Res.string.credit_used, appUsageInfo.creditDailyUsed))
                ResultCard(context.getString(Res.string.credit_left, appUsageInfo.creditDailyLeft))
            }
            
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = context.getString(Res.string.current_month),
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                ResultCard(context.getString(Res.string.credit_used, appUsageInfo.creditMonthlyUsed))
                ResultCard(context.getString(Res.string.credit_left, appUsageInfo.creditMonthlyLeft))
            }
            Spacer(modifier = Modifier.height(28.dp))
            Image(
                painter = painterResource(id = Res.mipmap.logo_about),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize(),
                alignment = Alignment.Center
            )
        }
    }
}

@Composable
@Preview
fun AboutScreenPreview() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "AboutScreen",
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}