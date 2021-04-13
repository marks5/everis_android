package br.com.everis.sovamu.feature.login.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("setErrorVisibility")
fun View.setErrorVisibility(msgError: String){
    if(msgError.isEmpty()) {
        this.visibility = View.INVISIBLE
    } else {
        this.visibility = View.VISIBLE
    }
}