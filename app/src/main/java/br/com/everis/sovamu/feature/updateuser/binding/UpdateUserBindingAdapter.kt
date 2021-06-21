package br.com.everis.sovamu.feature.updateuser.binding

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import br.com.everis.sovamu.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setErrorAlert")
fun setErrorAlert(view: View, msgError: String?) {
    if (!msgError.isNullOrEmpty()) {
        view.requestFocus()
        val builder = AlertDialog.Builder(view.context)
        with(builder)
        {
            setIcon(R.drawable.ic_error)
            setTitle(R.string.app_name)
            setMessage(msgError)
            setPositiveButton("OK") { dialog, whith ->  dialog.dismiss() }
            setCancelable(false)
            show()
        }
    }
}


@BindingAdapter("requestFocus")
fun requestFocus(view: TextInputEditText, isFocus:String?) {
    if (!isFocus.isNullOrEmpty()) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
    }
}


