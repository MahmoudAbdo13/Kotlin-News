package com.grand.kotlinnews.ui.news.mvvm.localData

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grand.kotlinnews.data.News

class LocalDataViewModel : ViewModel() {
    private  var mDataClass= MutableLiveData<List<News>>()
    private  lateinit var mmodel: LocalDataModel
    fun intialViewModel(fragment: Fragment)
    {
        mmodel= LocalDataModel.intializeModel(fragment)
    }
    fun liveData()
    {
        mDataClass=mmodel.liveData()
    }
    fun getData(): MutableLiveData<List<News>> {
        return mDataClass
    }
    fun insertData(it: List<News>)
    {
      mmodel.changeListToItems(it)
    }
}