package com.cryptodimond.presentation.ui

import android.graphics.BitmapFactory
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import com.cryptodimond.R.color
import com.cryptodimond.R.mipmap
import com.cryptodimond.presentation.ui.theme.commonTextStyle
import com.cryptodimond.presentation.ui.theme.headerTextBold
import kotlin.math.absoluteValue

@Composable
fun ContentWithProgress() {
    Surface(color = Color.Black.copy(alpha = 0.5f)) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun DynamicLabelView(
    value: Double,
    postfix: String = EMPTY_STRING,
    prefix: String = EMPTY_STRING
) {
    val isGrowing = value > 0
    val text = prefix + EMPTY_STRING_WITH_SHIFT + value.absoluteValue.toString() + EMPTY_STRING_WITH_SHIFT + postfix
    val backgroundColor =
        Color(ContextCompat.getColor(LocalContext.current, if (isGrowing) color.green_light else color.red_light))
    val textColor =
        Color(ContextCompat.getColor(LocalContext.current, if (isGrowing) color.green_second else color.red))
    val imageColor =
        Color(ContextCompat.getColor(LocalContext.current, if (isGrowing) color.green_second else color.red))
    val directionIcon = if (isGrowing) 180f else 0f
    Row(
        Modifier.background(
            color = backgroundColor,
            shape = RoundedCornerShape(size = 4.dp)
        )
    ) {
        Spacer(modifier = Modifier.width(4.dp))

        Icon(
            bitmap = BitmapFactory
                .decodeResource(LocalContext.current.resources, mipmap.arrow)
                .asImageBitmap(),
            contentDescription = "",
            modifier = Modifier
                .size(14.dp)
                .align(Alignment.CenterVertically)
                .rotate(directionIcon),
            tint = imageColor
        )
        Text(
            text = text,
            style = MaterialTheme.typography.commonTextStyle,
            color = textColor,
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 2.dp)
        )
    }
}


@Composable
fun SearchView(
    hint: String,
    state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        placeholder = {
            Text(
                text = hint,
                color = Color.Black
            )
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(28.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(24.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Green,
            cursorColor = Color.Black,
            leadingIconColor = Color.DarkGray,
            trailingIconColor = Color.Black,
            backgroundColor = Color.LightGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ErrorShow(text: String, retry: (() -> Unit)? = null)
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
            color = Color.Red,
            modifier = Modifier.clickable {
                retry?.invoke()
            }
        )
    }
}