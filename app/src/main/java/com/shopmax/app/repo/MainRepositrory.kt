package com.shopmax.app.repo

import com.shopmax.app.network.Api
import com.shopmax.app.network.model.getquote.GetQuoteRequest
import com.shopmax.app.network.model.getquoteresponse.GetQuoteResponse
import com.shopmax.app.util.Resource
import retrofit2.Response
import javax.inject.Inject

class MainRepositrory @Inject constructor(private val api: Api): DefaultRepository {


    override suspend fun quote(request: GetQuoteRequest): Resource<GetQuoteResponse> {
        return try {
            val response = api.quote(request)
            when(response.code()){
                200 -> response.body()?.let {
                    return@let Resource.Success(it) }?: Resource.Error("Unknown Error")
                else -> Resource.Error(response.message())
            }
        }catch (e: Exception){
            Resource.Error("Unknown Error")
        }
    }
}