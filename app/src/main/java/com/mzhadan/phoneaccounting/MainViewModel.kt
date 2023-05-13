package com.mzhadan.phoneaccounting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.models.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.impl.PhoneAccountingRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    var phoneInfoList = MutableLiveData<List<PhoneInfo>>()
    private val phoneInfoRepositoryImpl = PhoneAccountingRepositoryImpl()

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            val phoneInfoResponse = phoneInfoRepositoryImpl.getPhoneById(1)
//            phoneInfoList.postValue(phoneInfoResponse)
//        }
//    }

    fun getPhoneInfoById(phoneId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepositoryImpl.getPhoneById(phoneId)
            phoneInfoList.postValue(phoneInfoResponse)
        }
    }

//    fun addNewPhoneData(phoneInfo: PhoneInfo) {
//        viewModelScope.launch(Dispatchers.IO) {
//            phoneInfoRepositoryImpl.addNewPhoneInfo(phoneInfo)
//        }
//    }
}