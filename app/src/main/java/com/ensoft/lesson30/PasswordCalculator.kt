package com.ensoft.lesson30

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.MutableLiveData
import java.util.regex.Matcher
import java.util.regex.Pattern

class PasswordCalculator : TextWatcher {

    var passwordStrengthLevel: MutableLiveData<String> = MutableLiveData()
    var passwordStrengthColor: MutableLiveData<Int> = MutableLiveData()

    var lowerCase: MutableLiveData<Int> = MutableLiveData()
    var upperCase: MutableLiveData<Int> = MutableLiveData()
    var digit: MutableLiveData<Int> = MutableLiveData()
    var characters: MutableLiveData<Int> = MutableLiveData()

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if (s != null) {
            lowerCase.value = if (hasLowerCase(s)) {
                1
            } else {
                0
            }
            upperCase.value = if (hasUpperCase(s)) {
                1
            } else {
                0
            }
            digit.value = if (hasDigit(s)) {
                1
            } else {
                0
            }
            characters.value = if (hasCharacter(s)) {
                1
            } else {
                0
            }
            passwordStrength(s)
        }
    }

    private fun passwordStrength(pass: CharSequence) {
        if (pass.length in 0..5) {
            passwordStrengthColor.value = R.color.pass_weak
            passwordStrengthLevel.value = "Kuchsiz"
        } else if (pass.length in 6..11) {
            if (lowerCase.value == 1 || upperCase.value == 1 || digit.value == 1 || characters.value == 1) {
                passwordStrengthColor.value = R.color.pass_medium
                passwordStrengthLevel.value = "O'rtacha"
            }
        } else if (pass.length in 12..15) {
            if (lowerCase.value == 1 || upperCase.value == 1 || digit.value == 1 || characters.value == 1) {
                if (lowerCase.value == 1 && upperCase.value == 1) {
                    passwordStrengthColor.value = R.color.pass_strong
                    passwordStrengthLevel.value = "Kuchli"
                }
            }
        }
        else if (pass.length > 15){
            if (lowerCase.value == 1 && upperCase.value == 1 && digit.value == 1 && characters.value == 1) {
                    passwordStrengthColor.value = R.color.pass_super
                    passwordStrengthLevel.value = "Juda kuchli"
            }
        }
    }

    override fun afterTextChanged(s: Editable?) {}

    private fun hasLowerCase(str: CharSequence?): Boolean {
        val pattern = Pattern.compile("[a-z]")
        val lowerCase: Matcher = pattern.matcher(str!!)
        return lowerCase.find()
    }

    private fun hasUpperCase(str: CharSequence?): Boolean {
        val pattern = Pattern.compile("[A-Z]")
        val upperCase: Matcher = pattern.matcher(str!!)
        return upperCase.find()
    }

    private fun hasDigit(str: CharSequence?): Boolean {
        val pattern = Pattern.compile("[0-9]")
        val digit: Matcher = pattern.matcher(str!!)
        return digit.find()
    }

    private fun hasCharacter(str: CharSequence?): Boolean {
        val pattern = Pattern.compile("[!@#\$%^&*()_=+{}/.<>|\\\\[\\\\]~-]")
        val character: Matcher = pattern.matcher(str!!)
        return character.find()
    }
}