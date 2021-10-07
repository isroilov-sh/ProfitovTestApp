package com.example.moviestestapp.viewmodel

interface ViewModelProvider<ViewModelType> {
    fun viewModel(): ViewModelType
}