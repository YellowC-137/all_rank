package yellowc.app.allrank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yellowc.app.allrank.data.remote.repositoryimpl.RemoteRepositoryImpl
import yellowc.app.allrank.domain.repositories.RetrofitRepositories
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRemoteRepository(
        RepositoryImpl: RemoteRepositoryImpl,
    ): RetrofitRepositories
}