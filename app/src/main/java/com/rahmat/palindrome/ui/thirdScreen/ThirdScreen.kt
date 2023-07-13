package com.rahmat.palindrome.ui.thirdScreen

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahmat.palindrome.ViewModelFactory
import com.rahmat.palindrome.adapter.UserAdapter
import com.rahmat.palindrome.databinding.ActivityThirdScreenBinding

class ThirdScreen : AppCompatActivity() {

    private lateinit var binding: ActivityThirdScreenBinding

    private val thirdViewModel: ThirdScreenViewModel by viewModels<ThirdScreenViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recMainStory.layoutManager = LinearLayoutManager(this)

        val adapter = UserAdapter(this,thirdViewModel)
        binding.recMainStory.adapter = adapter
        thirdViewModel.userData.observe(this) { adapter.submitData(lifecycle, it) }

        val actionBar = binding.toolbar
        setSupportActionBar(actionBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}