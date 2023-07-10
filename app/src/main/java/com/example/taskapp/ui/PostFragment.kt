package com.example.taskapp.ui

import amr_handheld.network.MyViewModelFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.taskapp.R
import com.example.taskapp.databinding.FragmentPostBinding
import com.example.taskapp.databse.getDatabase
import com.example.taskapp.util.PostApplication
import com.example.taskapp.viewmodels.PostsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostFragment : Fragment() {

    lateinit var postFragmentBinding: FragmentPostBinding
    lateinit var viewModel: PostsViewModel
    lateinit var  adapter:PostAdapter
    var mLayoutManager: LinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postFragmentBinding = FragmentPostBinding.inflate(inflater, container, false)
        val view = postFragmentBinding.root
        val mainRepository = (requireActivity().application as PostApplication).repository
        viewModel = ViewModelProvider(this@PostFragment, MyViewModelFactory(mainRepository, getDatabase(requireActivity().applicationContext))).get(PostsViewModel::class.java)
        viewModel.getPosts()
        GlobalScope.launch {
            setList()
        }
        viewModel.postsList.observe(viewLifecycleOwner){
            if (it != null && it.isNotEmpty()) {
                adapter = PostAdapter(it,object :AdapterCallback{
                    override fun onItemClicked(position:Int) {
                        val args = Bundle()
                        args.putInt("position", position)
                        val fragmentManager = getFragmentManager()
                        val fragmentTransaction = fragmentManager?.beginTransaction()
                        val postDetailFrag = PostDetailFrag()
                        postDetailFrag.setArguments(args)
                        fragmentTransaction?.replace(R.id.fragment_container_view, postDetailFrag)
                        fragmentTransaction?.addToBackStack(null)
                        fragmentTransaction?.commit()
                    }

                })
                mLayoutManager= LinearLayoutManager(activity)
                postFragmentBinding.recyclerViewMeterList.setLayoutManager(mLayoutManager)
                postFragmentBinding.recyclerViewMeterList.adapter = adapter
            }
        }

        return view
    }
    private suspend fun setList() {
        val list = getDatabase(requireActivity().applicationContext).appDao().getPosts()
        adapter = PostAdapter(list,object :AdapterCallback{
            override fun onItemClicked(position:Int) {
                val args = Bundle()
                args.putInt("position", position)
                val fragmentManager = getFragmentManager()
                val fragmentTransaction = fragmentManager?.beginTransaction()
                val postDetailFrag = PostDetailFrag()
                postDetailFrag.setArguments(args)
                fragmentTransaction?.replace(R.id.fragment_container_view, postDetailFrag)
                fragmentTransaction?.addToBackStack(null)
                fragmentTransaction?.commit()
            }

        })
        mLayoutManager= LinearLayoutManager(activity)
        postFragmentBinding.recyclerViewMeterList.setLayoutManager(mLayoutManager)
        postFragmentBinding.recyclerViewMeterList.adapter = adapter
    }



}