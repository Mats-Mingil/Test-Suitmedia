package com.rahmat.palindrome.ui.secondScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.rahmat.palindrome.R
import com.rahmat.palindrome.ViewModelFactory
import com.rahmat.palindrome.databinding.ActivitySecondScreenBinding
import com.rahmat.palindrome.ui.thirdScreen.ThirdScreen

class SecondScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel: SecondScreenViewModel by viewModels<SecondScreenViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = viewModel.getPreference(this)
        val nameTv= binding.tvName
        val selectedUserNameTv = binding.tvUsername
        val chooseUserBtn = binding.btnChoose


        nameTv.text = intent.getStringExtra("Name")
        selectedUserNameTv.text = username.value
        chooseUserBtn.setOnClickListener {
            val toNext = Intent(this@SecondScreen, ThirdScreen::class.java)
            startActivity(toNext)
        }

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

    override fun onResume() {
        super.onResume()
        val username = viewModel.getPreference(this)
        val selectedUserNameTv = binding.tvUsername
        if(username.value != ""){
            selectedUserNameTv.text = username.value
        }
    }
}