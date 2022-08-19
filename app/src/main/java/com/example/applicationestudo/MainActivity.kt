package com.example.applicationestudo

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.applicationestudo.adapters.MainAdapter
import com.example.applicationestudo.databinding.ActivityMainBinding
import com.example.applicationestudo.data.repositories.MainRepository
import com.example.applicationestudo.data.rest.RetrofitService
import com.example.applicationestudo.domain.usecases.GetLive
import com.example.applicationestudo.presenter.main.MainViewModel
import com.example.applicationestudo.presenter.main.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance();
    private lateinit var getLive: GetLive
    private val adapter = MainAdapter {
        openLink(it.link)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getLive = GetLive(MainRepository(retrofitService))

        viewModel =
            ViewModelProvider(this, MainViewModelFactory(getLive)).get(
                MainViewModel::class.java
            )
        binding.recyclerview.adapter = adapter

    }

    override fun onStart() {
        super.onStart()
        viewModel.liveList.observe(this, Observer { lives ->
            Log.i("initial", "onStart")
            adapter.setLiveList(lives)
        })

        viewModel.errorMessage.observe(this, Observer { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllLives()
    }

    private fun openLink(link: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
        startActivity(browserIntent)
    }
}