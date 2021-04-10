package com.shopmax.app.repo

import com.shopmax.app.network.Api
import com.shopmax.app.network.model.getquote.GetQuoteRequest
import com.shopmax.app.network.model.getquoteresponse.GetQuoteResponse
import retrofit2.Response
import javax.inject.Inject

class MainRepositrory @Inject constructor(private val api: Api): DefaultRepository {


    override suspend fun login(request: GetQuoteRequest): Response<GetQuoteResponse> {
        TODO("Not yet implemented")
    }
}