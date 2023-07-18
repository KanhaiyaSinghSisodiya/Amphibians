package com.example.amphibians.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibianApp(modifier: Modifier) {
    Scaffold(modifier = modifier, topBar = {
        TopAppBar(title = ({
            Text(text = "Amphibians", style = MaterialTheme.typography.headlineMedium)
        }))
    }) {
        val amphibiansViewModel: AmphibiansViewModel =
            viewModel(factory = AmphibiansViewModel.Factory)
        HomeScreen(modifier = modifier.padding(it), retryAction = {amphibiansViewModel.getAmphibiansData()}, uiState = amphibiansViewModel.uiState)
    }
}