package com.mzhadan.phoneaccounting.ui.fragments.simcardlist

import androidx.lifecycle.ViewModel
import com.mzhadan.phoneaccounting.repository.simcards.SimCardsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SimcardListViewModel @Inject constructor(
    private val simCardsRepository: SimCardsRepository
): ViewModel() {
}