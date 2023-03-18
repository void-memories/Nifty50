package com.voidmemories.nifty50.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.voidmemories.nifty50.Nifty50Application
import com.voidmemories.nifty50.presentation.components.ComposableCard
import com.voidmemories.nifty50.presentation.theme.Nifty50Theme
import com.voidmemories.nifty50.presentation.viewmodels.MainViewModel
import com.voidmemories.nifty50.presentation.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private lateinit var mainViewModel: MainViewModel

    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as Nifty50Application).appComponent.inject(this)

        mainViewModel = ViewModelProvider(this, mainViewModelFactory)[MainViewModel::class.java]

        mainViewModel.runnableCode.run()

        setContent {
            Nifty50Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainView(mainViewModel)
                }
            }
        }
    }
}

@Composable
fun MainView(mainViewModel: MainViewModel) {
    val nifty50Objects by mainViewModel.nifty50LiveData.observeAsState(listOf())
    val mContext = LocalContext.current

    LaunchedEffect(nifty50Objects) {
        Toast.makeText(mContext, "Data Updated!", Toast.LENGTH_SHORT).show()
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            "Dashboard",
            Modifier.padding(bottom = 32.dp),
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
        )

        if (nifty50Objects == null) {
            Text(
                "We're having problems fetching data.", style = MaterialTheme.typography.body2,
            )
        } else if (nifty50Objects!!.isEmpty()) {
            Text(
                "Firing APIs, please wait...", style = MaterialTheme.typography.body2,
            )
        } else {
            LazyColumn {
                items(nifty50Objects!!) { item ->
                    if (item.identifier != "NIFTY 50") {
                        ComposableCard(stockObject = item)
                    }
                }
            }
        }
    }
}
