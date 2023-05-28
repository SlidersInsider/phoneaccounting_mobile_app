package com.mzhadan.phoneaccounting.ui.fragments.phonedetails

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
class PhoneDetailsViewModel @Inject constructor(
    private val phoneInfoRepository: PhonesInfoRepository
): ViewModel() {

    var phoneDetailsList = MutableLiveData<List<PhoneInfo>>()

    fun getPhoneInfoById(phoneId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepository.getPhoneById(phoneId)
            phoneDetailsList.postValue(phoneInfoResponse)
        }
    }
}