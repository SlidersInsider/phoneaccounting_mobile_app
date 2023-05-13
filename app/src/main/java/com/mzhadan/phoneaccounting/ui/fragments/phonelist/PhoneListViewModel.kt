package com.mzhadan.phoneaccounting.ui.fragments.phonelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.models.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.impl.PhoneAccountingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhoneListViewModel: ViewModel() {

    var phoneInfoList = MutableLiveData<List<PhoneInfo>>()
    private val phoneInfoRepositoryImpl = PhoneAccountingRepositoryImpl()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepositoryImpl.getAllPhonesInfo()
            phoneInfoList.postValue(phoneInfoResponse)
        }
    }

//    fun getPhoneInfoById(phoneId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val phoneInfoResponse = phoneInfoRepositoryImpl.getPhoneById(phoneId)
//            phoneInfoList.postValue(phoneInfoResponse)
//        }
//    }
}