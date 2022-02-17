package com.example.testapp.modules

import com.example.testapp.viewmodel.MediaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

class VMModules {

    fun getMediaViewModel(): Module {
        return module {
            viewModel {
                MediaViewModel(get())
            }
        }
    }
}