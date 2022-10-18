package zelimkhan.magomadov.recipes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import zelimkhan.magomadov.recipes.data.core.LocalDatabase
import zelimkhan.magomadov.recipes.data.meal.MealRepository
import zelimkhan.magomadov.recipes.data.meal.local.MealDao
import zelimkhan.magomadov.recipes.data.meal.remote.MealApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideMealRepository(mealDao: MealDao, mealApi: MealApi): MealRepository {
        return MealRepository(localDataSource = mealDao, remoteDataSource = mealApi)
    }

    @Singleton
    @Provides
    fun provideMealApi(okHttpClient: OkHttpClient): MealApi {
        return Retrofit.Builder()
            .baseUrl("http://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MealApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .callTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideMealDao(database: LocalDatabase): MealDao {
        return database.mealDao
    }

    @Singleton
    @Provides
    fun provideLocalDatabase(@ApplicationContext context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            LocalDatabase.DATABASE_NAME
        ).build()
    }
}