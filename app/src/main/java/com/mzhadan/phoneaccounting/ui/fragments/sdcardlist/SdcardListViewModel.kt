package com.mzhadan.phoneaccounting.ui.fragments.sdcardlist

import androidx.lifecycle.ViewModel
import com.mzhadan.phoneaccounting.repository.sdcards.SdCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SdcardListViewModel @Inject constructor(
    private val sdCardsRepository: SdCardsRepository
): ViewModel() {
}