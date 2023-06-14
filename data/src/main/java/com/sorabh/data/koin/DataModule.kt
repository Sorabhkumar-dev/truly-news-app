package com.sorabh.data.koin

import com.sorabh.data.network.KtorClient
import com.sorabh.data.network.KtorClientInterface
import com.sorabh.data.network.KtorClientInterfaceImpl
import com.sorabh.data.network.KtorClientRepository
import com.sorabh.data.network.KtorClientRepositoryImpl
import org.koin.dsl.module

val dataModule = module {
    single { KtorClient.ktorHttpClient }
    single<KtorClientInterface>{ KtorClientInterfaceImpl(get()) }
    single<KtorClientRepository>{ KtorClientRepositoryImpl(get()) }
}