package com.mzhadan.phoneaccounting.ui.fragments.simcardlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mzhadan.phoneaccounting.remote.entities.Notification
import com.mzhadan.phoneaccounting.remote.entities.SimCard
import com.mzhadan.phoneaccounting.repository.simcards.SimCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SimcardListViewModel @Inject constructor(
    private val simCardsRepository: SimCardsRepository
): ViewModel() {
    var simcardsList = MutableLiveData<List<SimCard>>()

    fun getAllSimcards() {
        viewModelScope.launch(Dispatchers.IO) {
            val simcardsResponse = simCardsRepository.getAllSimCards()
            simcardsList.postValue(simcardsResponse)
        }
    }

    @Synchronized
    fun deleteSimCardById(simcardId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            simCardsRepository.deleteSimCardById(simcardId)
        }
    }

    @Synchronized
    fun updateSimCardIsLocked(simcardId: Int, isLocked: String) {
        viewModelScope.launch(Dispatchers.IO) {
            simCardsRepository.updateSimCardIsLocked(simcardId, isLocked)
        }
    }
}