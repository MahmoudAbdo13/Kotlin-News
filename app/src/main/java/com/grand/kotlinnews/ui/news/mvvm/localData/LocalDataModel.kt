package com.grand.kotlinnews.ui.news.mvvm.localData

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.grand.kotlinnews.data.News
import com.grand.kotlinnews.room.DatabaseClass
import com.example.redditnews.room.Entity
import java.util.*

class LocalDataModel {
    private var mUsedData= ArrayList<News>()
    companion object
    {
        lateinit var mFragment: Fragment
        fun intializeModel(fragment: Fragment): LocalDataModel
        {
            mFragment =fragment
            return LocalDataModel()
        }
    }
    private fun getLocalDatabase() {
        val mDatabase= DatabaseClass.getInstance(mFragment.requireContext())
        var mEntry=mDatabase.mDao().getlist()

        for(m in mEntry)
        {
            mUsedData.add(News(m.title,m.selftext,m.icon_url))
        }
    }
    fun liveData(): MutableLiveData<List<News>>
    {
        getLocalDatabase()
        var mMutable= MutableLiveData<List<News>>()
        mMutable.postValue(mUsedData)
        return mMutable
    }
    fun changeListToItems(it: List<News>) {
        var mDatabase= DatabaseClass.getInstance(mFragment.requireContext())
        var id:Int=1
        for (ch in it) {
            Log.e("insert to local",id.toString())
            insertIntoLocalDatabase(
                mDatabase,
                ch.title,
                ch.selftext,
                ch.icon_url!!)
            id++
        }

    }
    fun insertIntoLocalDatabase(
        mDatabase: DatabaseClass,
        title: String,
        text: String,
        uri: String
    )
    {
        mDatabase.mDao().insertNews(
            Entity(

                title,
                text,
                uri
            )
        )
    }
}