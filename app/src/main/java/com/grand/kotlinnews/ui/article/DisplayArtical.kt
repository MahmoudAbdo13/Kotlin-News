package com.grand.kotlinnews.ui.article

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.grand.kotlinnews.ui.news.mvvm.localData.LocalDataViewModel
import com.grand.kotlinnews.ui.news.mvvm.ViewModel
import com.grand.kotlinnews.ui.news.mvvm.NotifiyedDataChanged
import com.grand.kotlinnews.ui.news.NewsAdapter
import com.grand.kotlinnews.network.Network
import com.grand.kotlinnews.databinding.FragmentDiaplayarticalBinding


class DisplayArtical : Fragment(), NotifiyedDataChanged {
    private lateinit var mViewModel: ViewModel
    private lateinit var mLocalModel: LocalDataViewModel

    private  var madapet: NewsAdapter?=null
    private lateinit var mbinding: FragmentDiaplayarticalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mbinding = FragmentDiaplayarticalBinding.inflate(inflater, container, false)
        defineViewModel()
        defineToolBar()
        offlineOrOnline()
        defineRecyclerView()
        return mbinding.getRoot()
    }
    private fun defineToolBar()
    {
        mbinding.toolbar.title="Kotlin News"
        //setSupportActionBar(mbinding.toolbar)
    }
    private fun defineViewModel()
    {
        mViewModel= ViewModelProvider(this)[ViewModel::class.java]
        mLocalModel= ViewModelProvider(this)[LocalDataViewModel::class.java]
        mLocalModel.intialViewModel(this)
    }
    private fun defineRecyclerView()
    {

        mbinding.recyclerView.apply {
            layoutManager= LinearLayoutManager(requireContext())
        }
    }
    private fun offlineOrOnline()
    {
        if(Network.checkForInternet(requireContext()))
        {
            mViewModel.intialViewModel(this)
            Log.d("net","true")
        }
        else{
            getLocalData()
        }
    }
    @SuppressLint("NotifyDataSetChanged")
    fun getLocalData()
    {
        mLocalModel.liveData()
        mLocalModel.getData().observe(viewLifecycleOwner){
            madapet = NewsAdapter(it,this@DisplayArtical)
            mbinding.recyclerView.adapter=madapet
            madapet?.notifyDataSetChanged()
        }

    }
    @SuppressLint("NotifyDataSetChanged")
    override fun dataChanged() {
        mViewModel.getData().observe(viewLifecycleOwner) {
            madapet = NewsAdapter(it,this@DisplayArtical)
            mbinding.recyclerView.adapter=madapet
            madapet?.notifyDataSetChanged()
             mLocalModel.insertData(it)
        }
    }
}