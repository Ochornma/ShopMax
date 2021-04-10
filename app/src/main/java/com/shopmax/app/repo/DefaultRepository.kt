package com.shopmax.app.repo

import com.shopmax.app.network.model.getquote.GetQuoteRequest
import com.shopmax.app.network.model.getquoteresponse.GetQuoteResponse
import com.shopmax.app.util.Resource
import retrofit2.Response
import retrofit2.http.Body

interface DefaultRepository {

    suspend fun quote(@Body request: GetQuoteRequest): Resource<GetQuoteResponse>
}