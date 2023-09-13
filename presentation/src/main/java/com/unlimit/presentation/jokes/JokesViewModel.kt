package com.unlimit.presentation.jokes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.unlimit.domain.base.UseCaseResponse
import com.unlimit.domain.model.Joke
import com.unlimit.domain.usecase.GetJokesUseCase
import com.unlimit.presentation.base.BaseViewModel

class JokesViewModel(private val jokesUseCase: GetJokesUseCase): BaseViewModel() {

    var jokesData = MutableLiveData<List<Joke>>()
        private set

    fun getJokes(isNetworkAvailable : Boolean) {
        jokesUseCase.invoke(viewModelScope, isNetworkAvailable, object :
            UseCaseResponse<List<Joke>?> {
            override fun onSuccess(result: List<Joke>?) {
                hideLoadingProgressBar()
                jokesData.value = result
            }

            override fun onError(throwable: Throwable) {
                hideLoadingProgressBar()
            }
        })
    }

}