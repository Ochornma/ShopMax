package com.shopmax.app.network

import com.shopmax.app.network.model.getquote.GetQuoteRequest
import com.shopmax.app.network.model.getquoteresponse.GetQuoteResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response

interface Api {

    @POST("auth/login")
    suspend fun login(@Body request: GetQuoteRequest): Response<GetQuoteResponse>
}