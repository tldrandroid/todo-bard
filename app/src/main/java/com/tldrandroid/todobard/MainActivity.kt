package com.tldrandroid.todobard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.tldrandroid.todobard.model.TodoViewModel
import com.tldrandroid.todobard.ui.TodoScreen
import com.tldrandroid.todobard.ui.theme.ToDoBardTheme

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ToDoBardTheme {
                TodoScreen(viewModel = todoViewModel)
            }
        }
    }
}
