package com.shopmax.app.repo

import com.shopmax.app.network.Api
import javax.inject.Inject

class MainRepositrory @Inject constructor(private val api: Api): DefaultRepository {
}