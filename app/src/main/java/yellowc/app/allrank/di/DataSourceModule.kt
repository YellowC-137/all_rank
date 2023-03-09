package yellowc.app.allrank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.datasourceimpl.RemoteDatasourceImpl
import yellowc.app.allrank.data.remote.repositoryimpl.JsoupRepositoryImpl
import yellowc.app.allrank.data.remote.repositoryimpl.RemoteRepositoryImpl
import yellowc.app.allrank.domain.repositories.JsoupRepositories
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        RemoteDataSourceImpl: RemoteDatasourceImpl,
    ): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindJsoupRepository(
        JsoupRepositoryImpl: JsoupRepositoryImpl,
    ): JsoupRepositories

}