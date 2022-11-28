package com.grand.kotlinnews.ui.news.mvvm

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.grand.kotlinnews.data.DataModel
import com.grand.kotlinnews.data.News
import com.grand.kotlinnews.retrofit.Api
import com.grand.kotlinnews.retrofit.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Model {
    private lateinit var api: Api
    private val BASE_URL = "https://www.reddit.com"
    private var mArray = ArrayList<News>()

    companion object {
        private lateinit var notyfyChange: NotifiyedDataChanged
        fun intializeModel(mFragment: Fragment): Model {
            notyfyChange = mFragment as NotifiyedDataChanged
            return Model()
        }
    }

    private fun getData() {
        api = Retrofit.getRetrofit(BASE_URL).create(Api::class.java)
        val call = api.calldata()
        call.enqueue(object : Callback<DataModel> {
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.body() != null) {
                    Log.d(
                        "title",
                        response.body()!!.data.children.get(0).data.title
                    )
                    splitIntoArray(response.body()!!)
                }
            }

            override fun onFailure(call: Call<DataModel>, t: Throwable) {
                Log.d("error", t.message.toString())
            }

        })
    }

    fun liveData(): MutableLiveData<List<News>> {
        getData()
        var mMutable = MutableLiveData<List<News>>()
        mMutable.postValue(mArray)
        return mMutable
    }

    private fun splitIntoArray(body: DataModel) {
        for (news in body.data.children) {

            if (news.data.secure_media == null) {
                Log.e("Url null", "news.data.secure_media?.oembed?.thumbnailUrl!!")
                mArray.add(
                    News(
                        news.data.title,
                        news.data.selftext,
                        "null"
                    )
                )
            } else {
                //Log.e("Url", "data "+news.data.secure_media?.oembed?.thumbnailUrl!!)
                mArray.add(
                    News(
                        news.data.title,
                        news.data.selftext,
                        news.data.secure_media?.oembed?.thumbnail_url
                    )
                )
            }
        }
        notyfyChange.dataChanged()
    }
}