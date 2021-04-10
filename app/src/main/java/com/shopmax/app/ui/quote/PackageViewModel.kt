package com.shopmax.app.ui.quote

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shopmax.app.network.model.getquote.*
import com.shopmax.app.network.model.getquoteresponse.GetQuoteResponse
import com.shopmax.app.repo.MainRepositrory
import com.shopmax.app.util.DispasherProvider
import com.shopmax.app.util.Resource
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PackageViewModel @Inject constructor ( private val repository: MainRepositrory, private val dispatchers: DispasherProvider) : ViewModel() {

    private val _response = MutableStateFlow<Event>(Event.Empty)
    val response: StateFlow<Event> = _response

    fun creatDimen(lenght: String, width: String, height: String): Dimensions{
        return Dimensions(height.toInt(), lenght.toInt(), width.toInt())
    }

    fun createPackage(dimensions: Dimensions, name: String, weight: String): Package{
        return  Package(dimensions, name, weight.toInt())
    }



    fun quote(request: GetQuoteRequest){
        if(request == null) {
            _response.value = Event.Failure("Not a valid amount")
            return
        }
        viewModelScope.launch(dispatchers.io) {
            _response.value = Event.Loading
            val res = repository.quote(request)
            when(res){
                is Resource.Error -> _response.value = Event.Failure(res.message!!)

                is Resource.Success -> _response.value = Event.Success(res.data!!)
            }
        }
    }

    fun createRequest(pickupDetails: PickupDetails, pick: Boolean, date: String, receiverDetails: ReceiverDetails, pack: MutableList<Package>): GetQuoteRequest{

        return GetQuoteRequest(pack, pickupDetails, pick, date, receiverDetails)
    }


    sealed class Event {
        class Success(val response: GetQuoteResponse): Event()
        class Failure(val errorText: String): Event()
        object Loading : Event()
        object Empty : Event()
    }
}