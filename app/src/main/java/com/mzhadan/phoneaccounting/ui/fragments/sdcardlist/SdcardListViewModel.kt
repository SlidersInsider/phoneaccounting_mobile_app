package com.mzhadan.phoneaccounting.ui.fragments.sdcardlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.remote.entities.SdCard
import com.mzhadan.phoneaccounting.remote.entities.SimCard
import com.mzhadan.phoneaccounting.repository.sdcards.SdCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SdcardListViewModel @Inject constructor(
    private val sdCardsRepository: SdCardsRepository
): ViewModel() {
    var sdcardsList = MutableLiveData<List<SdCard>>()

    fun getAllSdCards() {
        viewModelScope.launch(Dispatchers.IO) {
            val sdcardsResponse = sdCardsRepository.getAllSdCards()
            sdcardsList.postValue(sdcardsResponse)
        }
    }

    @Synchronized
    fun deleteSdCardById(sdcardId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            sdCardsRepository.deleteSdCardById(sdcardId)
        }
    }
}