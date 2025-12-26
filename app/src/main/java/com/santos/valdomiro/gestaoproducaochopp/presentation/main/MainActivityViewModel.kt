package com.santos.valdomiro.gestaoproducaochopp.presentation.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.valdomiro.gestaoproducaochopp.domain.usecase.GetCurrentUserUseCase
import com.santos.valdomiro.gestaoproducaochopp.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : ViewModel() {

    var isLoading = mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Screen.Login.route)
        private set

    init {
        verificarUsuarioLogado()
    }

    private fun verificarUsuarioLogado() {
        viewModelScope.launch {
            delay(1000)

            val result = getCurrentUserUseCase()

            result.onSuccess { usuario ->
                startDestination = if (usuario != null) {
                    Screen.Home.route
                } else {
                    Screen.Login.route
                }
            }.onFailure {
                startDestination = Screen.Login.route
            }

            isLoading.value = false
        }
    }

}