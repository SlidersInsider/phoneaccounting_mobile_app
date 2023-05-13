package com.mzhadan.phoneaccounting.ui.fragments.phonedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.models.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.impl.PhoneAccountingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhoneDetailsViewModel: ViewModel() {

    var phoneDetailsList = MutableLiveData<List<PhoneInfo>>()
    private val phoneInfoRepositoryImpl = PhoneAccountingRepositoryImpl()

    fun getPhoneInfoById(phoneId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepositoryImpl.getPhoneById(phoneId)
            phoneDetailsList.postValue(phoneInfoResponse)
        }
    }
}