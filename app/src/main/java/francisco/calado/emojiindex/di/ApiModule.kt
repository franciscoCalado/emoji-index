package francisco.calado.emojiindex.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGithubRetrofit(
        adapterFactory: RxJava2CallAdapterFactory,
        converterFactory: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addCallAdapterFactory(adapterFactory)
            .addConverterFactory(converterFactory).build()
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
}