package br.com.everis.sovamu.feature.updateuser.binding

import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import br.com.everis.sovamu.R
import br.com.everis.sovamu.feature.login.binding.setErrorVisibility
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("setErrorAlert")
fun setErrorAlert(view: View, msgError: String?) {
    if (!msgError.isNullOrEmpty()) {
        val builder = AlertDialog.Builder(view.context)
        with(builder)
        {
            setIcon(R.drawable.ic_error)
            setTitle(context.getString(R.string.title_alert_dialog))
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

@BindingAdapter("setErrorEnabled")
fun setErrorEnabled(view: TextInputEditText, msgError: String?) {
    if (!msgError.isNullOrEmpty() && view.text!!.isEmpty()) {
        view.error = "Campo obrigatorio"
    } else {
        view.error = null
    }
}

