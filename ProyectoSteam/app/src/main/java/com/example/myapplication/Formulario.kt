package com.example.myapplication

import androidx.appcompat.app.AlertDialog
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import javax.mail.Message
import javax.mail.MessagingException
import javax.mail.PasswordAuthentication
import javax.mail.Session
import javax.mail.Transport
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import java.util.*

class Formulario : AppCompatActivity() {

    private lateinit var editTextNombre: EditText
    private lateinit var editTextCorreo: EditText
    private lateinit var checkBoxMayorEdad: CheckBox

    private val SMTP_HOST = "smtp.gmail.com"
    private val SMTP_PORT = "587"
    private val EMAIL = "marccordero003@gmail.com"
    private val PASSWORD = "fznttxvntpqgwcuc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        editTextNombre = findViewById(R.id.editTextNombre)
        editTextCorreo = findViewById(R.id.editTextCorreo)
        checkBoxMayorEdad = findViewById(R.id.checkBoxMayorEdad)

        val botonVolver: Button = findViewById(R.id.buttonVolver)
        botonVolver.setOnClickListener {
            volverAlMainActivity(it)
        }
    }

    fun enviarFormulario(view: View) {
        val nombre = editTextNombre.text.toString()
        val correo = editTextCorreo.text.toString()
        val esMayorEdad = checkBoxMayorEdad.isChecked

        if (nombre.isEmpty() || correo.isEmpty() || !esMayorEdad) {
            // Mostrar AlertDialog indicando campos faltantes
            mostrarAlertaCamposFaltantes()
        } else {
            val subject = "Miniproyecto"
            val body = "Para mas proyectos sigueme en: https://github.com/marccordero"

            SendEmailTask().execute(subject, body, correo)
        }
    }

    private inner class SendEmailTask : AsyncTask<String, Void, Boolean>() {
        override fun doInBackground(vararg params: String): Boolean {
            val subject = params[0]
            val body = params[1]
            val recipient = params[2]

            return try {
                sendEmail(subject, body, recipient)
                true
            } catch (e: MessagingException) {
                e.printStackTrace()
                false
            }
        }

        override fun onPostExecute(success: Boolean) {
            if (success) {
                Toast.makeText(this@Formulario, "Correo enviado correctamente", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this@Formulario, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
            }

            val intent = Intent(this@Formulario, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    @Throws(MessagingException::class)
    private fun sendEmail(subject: String, body: String, recipient: String) {
        val properties = Properties()
        properties["mail.smtp.auth"] = "true"
        properties["mail.smtp.starttls.enable"] = "true"
        properties["mail.smtp.host"] = SMTP_HOST
        properties["mail.smtp.port"] = SMTP_PORT

        val session = Session.getInstance(properties, object : javax.mail.Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(EMAIL, PASSWORD)
            }
        })

        val message = MimeMessage(session)
        message.setFrom(InternetAddress(EMAIL))
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient))
        message.subject = subject
        message.setText(body)

        Transport.send(message)
    }

    fun volverAlMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun mostrarAlertaCamposFaltantes() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Campos faltantes")
        builder.setMessage("Por favor completa todos los campos del formulario.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
