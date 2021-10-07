package com.example.moviestestapp.data

data class ResponseData(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String,
    val fault: Fault?
)