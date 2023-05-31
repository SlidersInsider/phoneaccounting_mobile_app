package com.mzhadan.phoneaccounting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.remote.entities.Notification
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
class MainViewModel @Inject constructor(
    private val phoneInfoRepository: PhonesInfoRepository,
    private val simCardsRepository: SimCardsRepository,
    private val sdCardsRepository: SdCardsRepository
): ViewModel() {

    @Synchronized
    fun addNewPhoneData(phoneInfo: PhoneInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            phoneInfoRepository.addNewPhoneInfo(phoneInfo)
        }
    }

    @Synchronized
    fun addNewSimCard(simCard: SimCard) {
        viewModelScope.launch(Dispatchers.IO) {
            simCardsRepository.addNewSimCard(simCard)
        }
    }

    @Synchronized
    fun addNewSdCard(sdCard: SdCard) {
        viewModelScope.launch(Dispatchers.IO) {
            sdCardsRepository.addNewSdCard(sdCard)
        }
    }
}