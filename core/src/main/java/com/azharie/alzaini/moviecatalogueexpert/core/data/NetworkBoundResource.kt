package com.azharie.alzaini.moviecatalogueexpert.core.data

import com.azharie.alzaini.moviecatalogueexpert.core.data.source.remote.network.ApiResponse
import com.azharie.alzaini.moviecatalogueexpert.core.utils.AppExecutors
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(private val mExecutors: AppExecutors) {
    private val result : Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        val dbSource = loadFromDatabase().first()
        if (shouldFetch(dbSource)){
            emit(Resource.Loading())
            when(val apiResponse = createCall().first()){
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDatabase().map { Resource.Success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(loadFromDatabase().map { Resource.Success(it) })
                }
                is ApiResponse.Error ->{
                    onFetchFailed()
                    emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loadFromDatabase().map { Resource.Success(it) })
        }
    }


    protected open fun onFetchFailed() {}
    protected abstract fun loadFromDatabase(): Flow<ResultType>
    protected abstract fun shouldFetch(data: ResultType?): Boolean
    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>
    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}
