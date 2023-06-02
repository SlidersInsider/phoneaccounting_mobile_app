package com.mzhadan.phoneaccounting.ui.fragments.phonedetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.remote.entities.PhoneInfo
import com.mzhadan.phoneaccounting.remote.entities.SdCard
import com.mzhadan.phoneaccounting.remote.entities.SimCard
import com.mzhadan.phoneaccounting.repository.phones.PhonesInfoRepository
import com.mzhadan.phoneaccounting.repository.sdcards.SdCardsRepository
import com.mzhadan.phoneaccounting.repository.simcards.SimCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhoneDetailsViewModel @Inject constructor(
    private val phoneInfoRepository: PhonesInfoRepository,
    private val simCardsRepository: SimCardsRepository,
    private val sdCardsRepository: SdCardsRepository
): ViewModel() {

    var phoneInfoElem = MutableLiveData<PhoneInfo>()
    var simcard1Elem = MutableLiveData<List<SimCard>>()
    var simcard2Elem = MutableLiveData<List<SimCard>>()
    var sdCardElem = MutableLiveData<List<SdCard>>()

    @Synchronized
    fun getPhoneInfoById(phoneId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val phoneInfoResponse = phoneInfoRepository.getPhoneById(phoneId)
            phoneInfoElem.postValue(phoneInfoResponse)
        }
    }

    @Synchronized
    fun getSimcardByNumber(number: String, numberCounter: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val simcardResponse = simCardsRepository.getSimCardByNumber(number)
            if (numberCounter == 1) {
                simcard1Elem.postValue(simcardResponse)
            } else {
                simcard2Elem.postValue(simcardResponse)
            }
        }
    }

    @Synchronized
    fun getSdcardBySerialNumber(serialNumber: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val sdcardResponse = sdCardsRepository.getSdCardBySerialNumber(serialNumber)
            sdCardElem.postValue(sdcardResponse)
        }
    }
}