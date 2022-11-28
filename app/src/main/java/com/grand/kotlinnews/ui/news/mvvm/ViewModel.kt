package com.grand.kotlinnews.ui.news.mvvm

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.grand.kotlinnews.data.News

class ViewModel : ViewModel() {
    private  var mDataClass= MutableLiveData<List<News>>()
    fun intialViewModel(mFragment: Fragment)
    {
        var mmodel= Model.intializeModel(mFragment)
        mDataClass=mmodel.liveData()
        Log.d("error", "er4")
    }
    fun getData(): MutableLiveData<List<News>> {
        return mDataClass
    }
}