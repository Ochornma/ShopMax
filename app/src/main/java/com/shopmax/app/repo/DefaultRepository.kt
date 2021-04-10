package com.shopmax.app.repo

import com.shopmax.app.network.model.getquote.GetQuoteRequest
import com.shopmax.app.network.model.getquoteresponse.GetQuoteResponse
import retrofit2.Response
import retrofit2.http.Body

interface DefaultRepository {

    suspend fun login(@Body request: GetQuoteRequest): Response<GetQuoteResponse>
}