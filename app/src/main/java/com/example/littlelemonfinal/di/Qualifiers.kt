package com.example.littlelemonfinal.di

import dagger.Provides
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeRemoteQualifier()


@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class HomeLocalQualifier()

