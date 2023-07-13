package com.rahmat.palindrome.ui.firstScreen

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.rahmat.palindrome.databinding.ActivityMainBinding
import com.rahmat.palindrome.ui.secondScreen.SecondScreen

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val edName = binding.edName
        val edPalindrome = binding.edPalindrome
        val btnCheck = binding.btnCheck
        val btnNext = binding.btnNext

        btnCheck.setOnClickListener {
            val input = edPalindrome.text.toString()
            if (input.isNotEmpty()) {
                val isPalindrome = isPalindrome(input)

                val message = if (isPalindrome) {
                    "Palindrome"
                } else {
                    "Not Palindrome"
                }

                showAlertDialog(message)
            }
        }

        btnNext.setOnClickListener {
            if(edName.text.isNotEmpty()){
                val moveWithData = Intent(this@MainActivity, SecondScreen::class.java)
                moveWithData.putExtra("Name",edName.text.toString())
                startActivity(moveWithData)
            }

        }
    }

    fun isPalindrome(string: String): Boolean {
        val normalizedString = string.lowercase().replace(Regex("[^a-zA-Z0-9]"), "")
        return normalizedString == normalizedString.reversed()
    }

    private fun showAlertDialog(message: String) {
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setMessage(message)
        alertDialogBuilder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}