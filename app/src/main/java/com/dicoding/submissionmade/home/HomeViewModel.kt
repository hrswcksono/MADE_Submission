package com.dicoding.submissionmade.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.dicoding.submissionmade.core.domain.usecase.MovieUseCase

class HomeViewModel (movieUseCase: MovieUseCase) : ViewModel() {
    val movie = movieUseCase.getAllMovie().asLiveData()


}