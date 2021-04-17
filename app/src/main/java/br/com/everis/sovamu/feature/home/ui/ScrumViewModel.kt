package br.com.everis.sovamu.feature.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.everis.sovamu.ui.BaseViewModel
import br.com.everis.sovamu.utils.DateFormatUtil
import kotlinx.coroutines.CoroutineDispatcher
import org.threeten.bp.LocalDate

class ScrumViewModel(
    mainDispatcher: CoroutineDispatcher,
    ioDispatcher: CoroutineDispatcher
) : BaseViewModel(mainDispatcher, ioDispatcher) {

    private val qntDias : Long = 1

    fun addDay() {
        _data.value = _data.value?.plusDays(qntDias)
    }

    fun removeDay() {
        _data.value = _data.value?.minusDays(qntDias)
    }

    private val _data by lazy { MutableLiveData<LocalDate>() }
    val data: LiveData<LocalDate>
        get() = _data

    init {
        _data.value = DateFormatUtil.brTime().toLocalDate()
    }
}