package com.mzhadan.phoneaccounting.ui.fragments.phonelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.local.AppDatabase
import com.mzhadan.phoneaccounting.local.entities.LocalPhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.repository.phones.PhoneAccountingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneListViewModel @Inject constructor(
    private val roomDatabase: AppDatabase,
    private val phoneInfoRepository: PhoneAccountingRepository
): ViewModel() {

    var phoneInfoList = MutableLiveData<List<PhoneInfo>>()
    var localPhoneInfoList = MutableLiveData<List<LocalPhoneInfo>>()

    @Synchronized
    fun remoteGetAllPhoneInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepository.getAllPhonesInfo()
            phoneInfoList.postValue(phoneInfoResponse)
        }
    }

    @Synchronized
    fun localGetAllPhoneInfo() {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = roomDatabase.phonesDao().getAllPhonesInfo()
            localPhoneInfoList.postValue(phoneInfoResponse)
        }
    }

    @Synchronized
    fun localSaveAllPhoneInfo(phoneInfoList: List<PhoneInfo>) {
        viewModelScope.launch(Dispatchers.IO) {
            roomDatabase.phonesDao().saveAllPhonesInfo(PhoneInfo.mapToLocalPhoneInfo(phoneInfoList))
        }
    }

//    fun getPhoneInfoById(phoneId: Int) {
//        viewModelScope.launch(Dispatchers.IO) {
//            val phoneInfoResponse = phoneInfoRepositoryImpl.getPhoneById(phoneId)
//            phoneInfoList.postValue(phoneInfoResponse)
//        }
//    }
}