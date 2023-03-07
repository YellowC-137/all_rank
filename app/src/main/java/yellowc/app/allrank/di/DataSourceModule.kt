package yellowc.app.allrank.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yellowc.app.allrank.data.remote.datasource.RemoteDataSource
import yellowc.app.allrank.data.remote.datasourceimpl.RemoteDatasourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(
        RemoteDataSourceImpl: RemoteDatasourceImpl,
    ): RemoteDataSource

}