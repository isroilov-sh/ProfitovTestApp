package com.example.testapp.viewmodel

interface ViewModelProvider<ViewModelType> {
    fun viewModel(): ViewModelType
}