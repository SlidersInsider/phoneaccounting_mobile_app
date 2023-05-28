package com.mzhadan.phoneaccounting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.phones.PhonesInfoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val phoneInfoRepository: PhonesInfoRepository
): ViewModel() {
    var phoneInfoList = MutableLiveData<List<PhoneInfo>>()

//    init {
//        viewModelScope.launch(Dispatchers.IO) {
//            val phoneInfoResponse = phoneInfoRepositoryImpl.getPhoneById(1)
//            phoneInfoList.postValue(phoneInfoResponse)
//        }
//    }

    fun getPhoneInfoById(phoneId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepository.getPhoneById(phoneId)
            phoneInfoList.postValue(phoneInfoResponse)
        }
    }

//    fun addNewPhoneData(phoneInfo: PhoneInfo) {
//        viewModelScope.launch(Dispatchers.IO) {
//            phoneInfoRepositoryImpl.addNewPhoneInfo(phoneInfo)
//        }
//    }
}