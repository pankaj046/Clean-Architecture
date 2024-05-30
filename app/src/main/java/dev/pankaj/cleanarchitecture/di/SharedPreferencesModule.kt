package dev.pankaj.cleanarchitecture.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.pankaj.cleanarchitecture.data.local.prefmanager.SharedPreferencesUtil
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SharedPreferencesModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesUtil(
        sharedPreferences: SharedPreferences,
        editor: SharedPreferences.Editor
    ): SharedPreferencesUtil {
        return SharedPreferencesUtil(sharedPreferences, editor)
    }
}

