package com.example.amphibians.ui.theme.screens

import android.graphics.Paint.Align
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import com.example.amphibians.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.amphibians.model.AmphibianModelClass

@Composable
fun HomeScreen(modifier: Modifier, retryAction: () -> Unit, uiState: Amphibians) {
    when(uiState){
        is Amphibians.Success -> AmphibiansScreen(modifier = modifier, list = (uiState as Amphibians.Success).list)
        Amphibians.Loading -> LoadingScreen(modifier = modifier)
        Amphibians.Error -> ErrorScreen(modifier = modifier, retryAction = retryAction )
    }
}

@Composable
fun LoadingScreen(modifier: Modifier) {
    Image(modifier = modifier, painter = painterResource(id = R.drawable.download), contentDescription = "Loading Image")
}

@Composable
fun ErrorScreen(modifier: Modifier, retryAction: ()->Unit) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.baseline_error_24), contentDescription = "Error",
            modifier = Modifier
                .width(250.dp)
                .height(250.dp))

        Button(onClick = retryAction, modifier = Modifier.padding(20.dp) ) {
            Text(text = "Retry")
        }
    }
}

@Composable
fun AmphibianCard(item: AmphibianModelClass) {
    Card(modifier = Modifier
        .fillMaxWidth(0.96F)
        .padding(10.dp) ) {
        Column() {
            Text(text = item.name+"("+item.type+")", style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
            )
            AsyncImage(model = item.img, contentDescription = null, placeholder = painterResource(id = R.drawable.download), modifier = Modifier.fillMaxWidth(), contentScale = ContentScale.Crop)
            Text(text = item.description, textAlign = TextAlign.Justify, style = MaterialTheme.typography.bodyMedium, modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp))
        }
    }
}

@Composable
fun AmphibiansScreen(modifier: Modifier, list: List<AmphibianModelClass>) {
    LazyColumn(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        items(items = list, itemContent = {
            item ->  AmphibianCard(item)
        })
    }
}

