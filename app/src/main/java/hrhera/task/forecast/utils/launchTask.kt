package hrhera.task.forecast.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun ViewModel.launchTask(task: suspend () -> Unit) = viewModelScope.launch(Dispatchers.IO) { task() }
