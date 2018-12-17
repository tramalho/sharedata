package com.example.tramalho.sharedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class ActivityViewModel : DialogViewModel() {

    val navigate: MutableLiveData<Int> = MutableLiveData()

    fun init() {
        activityData.value = "texto MainActivity: ${System.nanoTime()}"
    }

    fun onClickNavigateDialog() {
        navigate.value = 1
    }

    fun onClickNavigateEdit() {
        navigate.value = 2
    }
}

abstract class DialogViewModel : EditHourViewModel() {

    fun onClickAction() {
        dialogData.value = "clique no botão dialog : ${System.nanoTime()}"
    }
}

abstract class EditHourViewModel : ViewModel() {

    val activityData: MutableLiveData<String> = MutableLiveData()

    val dialogData : MutableLiveData<String> = MutableLiveData()

    val editHourData : MutableLiveData<String> = MutableLiveData()

    val close : MutableLiveData<Int> = MutableLiveData()

    val refreshAct : MutableLiveData<Int> = MutableLiveData()

    fun onClickData() {
        editHourData.value = "clique no botão edit : ${System.nanoTime()}"
    }

    open fun close() {
        close.value = 1
        refreshAct.value = 1
    }
}