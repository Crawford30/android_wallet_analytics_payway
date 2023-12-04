package com.example.mobilewalletanalytics.di.modules

import com.example.mobilewalletanalytics.data.remote_interfaces.RemoteRepo
import com.example.mobilewalletanalytics.data.remote_repo.RemoteRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Module where repos are provided
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    /**
     * Provide an implementation of the [RemoteRepo] interface.
     */
    @Binds
    abstract fun bindRemoteRepo(remoteRepoImpl: RemoteRepoImpl): RemoteRepo
}