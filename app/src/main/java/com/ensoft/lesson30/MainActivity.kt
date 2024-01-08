package com.ensoft.lesson30

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.ensoft.lesson30.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var color: Int = R.color.pass_weak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val passwordCalculator = PasswordCalculator()
        binding.etPassword.addTextChangedListener(passwordCalculator)

        passwordCalculator.passwordStrengthLevel.observe(this, Observer {
            showLevel(it)
            return@Observer
        })

        passwordCalculator.passwordStrengthColor.observe(this, Observer {
            color = it
            return@Observer
        })

        passwordCalculator.lowerCase.observe(this,Observer{
            changePasswordInfo(it, binding.ivLowercase, binding.tvLowercase)
            return@Observer
        })

        passwordCalculator.upperCase.observe(this,Observer{
            changePasswordInfo(it, binding.ivUppercase, binding.tvUppercase)
            return@Observer
        })

        passwordCalculator.digit.observe(this,Observer{
            changePasswordInfo(it, binding.ivDigit, binding.tvDigit)
            return@Observer
        })

        passwordCalculator.characters.observe(this,Observer{
            changePasswordInfo(it, binding.ivCharacter, binding.tvCharacter)
            return@Observer
        })

    }

    private fun changePasswordInfo(value: Int, img: ImageView, textView: TextView){
        if (value == 1){
            img.setColorFilter(ContextCompat.getColor(this, R.color.pass_super))
            textView.setTextColor(ContextCompat.getColor(this, R.color.pass_super))
        }
        else{
            img.setColorFilter(ContextCompat.getColor(this, R.color.dark_gray))
            textView.setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
        }
    }

    private fun showLevel(level:String){
        binding.btnSignUp.isEnabled = level.contains("Kuchli") || level.contains("Juda kuchli")

        binding.passwordStrengthTxt.text = level
        binding.passwordStrengthTxt.setTextColor(ContextCompat.getColor(this,color))
    }
}