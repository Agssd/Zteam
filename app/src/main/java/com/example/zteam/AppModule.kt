package com.example.zteam

import GameDatabase
import GameRepository
import com.example.zteam.data.RetrofitInstance
import com.example.zteam.data.remote.NetworkDataSource
import com.example.zteam.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module

val appModule = module {
    viewModel { ListViewModel(get()) }
    single { RetrofitInstance.api }

    single { NetworkDataSource(get()) }

    single { GameDatabase.getDatabase(androidContext()) }
    single { get<GameDatabase>().gameDao() }
    single { get<GameDatabase>().genreDao() }
    single { get<GameDatabase>().hotNewGameDao() }

    single { GameRepository(get(), get(), get(), get()) }
}