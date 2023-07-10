package com.example.taskapp.ui

import amr_handheld.network.MyViewModelFactory
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taskapp.databinding.FragmentPostDetailBinding
import com.example.taskapp.databse.getDatabase
import com.example.taskapp.util.PostApplication
import com.example.taskapp.viewmodels.PostsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class PostDetailFrag : Fragment() {

    lateinit var postDetailBinding: FragmentPostDetailBinding
    lateinit var viewModel: PostsViewModel
    var position:Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        postDetailBinding =  FragmentPostDetailBinding.inflate(inflater, container, false)
        val view = postDetailBinding.root
        val mainRepository = (requireActivity().application as PostApplication).repository
        viewModel = ViewModelProvider(this@PostDetailFrag, MyViewModelFactory(mainRepository, getDatabase(requireActivity().applicationContext))).get(PostsViewModel::class.java)
        position = requireArguments().getInt("position")
        GlobalScope.launch {
            getList()
        }
        Toast.makeText(context, "Click on Body Card to Access Service!", Toast.LENGTH_SHORT).show()
        postDetailBinding.postBody.setOnClickListener {
            val intent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            startActivity(intent)
        }
        return view
    }

    private suspend fun getList() {
        val list = getDatabase(requireActivity().applicationContext).appDao().getPosts()
        postDetailBinding.postTitle.text = list[position!!].title
        postDetailBinding.postBody.text = list[position!!].body
    }


}