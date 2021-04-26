package br.com.everis.sovamu.feature.login.dialog

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import br.com.everis.sovamu.R

class LoginDialog(private val viewGroup: ViewGroup, private val context: Context) {

    private val viewCriada = criaLayout()

    fun showMyPasswordDialog() {
        val editText = viewCriada.findViewById<EditText>(R.id.text_email)
        AlertDialog.Builder(context)
                .setIcon(R.drawable.ic_baseline_email_24)
                .setTitle((R.string.dialog_informe_email))
                .setView(viewCriada)
                .setPositiveButton(context.getString(R.string.enviar_email)) { dialog, with ->
                    if(!editText?.text.isNullOrEmpty()) {
                        Toast.makeText(context,"e-mail ${editText.text} enviado", Toast.LENGTH_LONG).show()
                    }
                }
                .setNegativeButton(context.getString(R.string.cancelar_envio_email)){ dialog, with ->}
                .setCancelable(false)
                .show()
    }

    private fun criaLayout(): View {
        return LayoutInflater.from(context).inflate(R.layout.dialog_my_password, viewGroup, false)
    }
}