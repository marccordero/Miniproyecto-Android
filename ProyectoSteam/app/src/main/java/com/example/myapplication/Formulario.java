package com.example.myapplication;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Formulario extends AppCompatActivity {

    private EditText editTextNombre;
    private EditText editTextCorreo;
    private CheckBox checkBoxMayorEdad;

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL = "tu correo electronico";
    private static final String PASSWORD = "contraseña smtp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        editTextNombre = findViewById(R.id.editTextNombre);
        editTextCorreo = findViewById(R.id.editTextCorreo);
        checkBoxMayorEdad = findViewById(R.id.checkBoxMayorEdad);

        Button botonVolver = findViewById(R.id.buttonVolver);
        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                volverAlMainActivity(v);
            }
        });
    }

    public void enviarFormulario(View view) {
        String nombre = editTextNombre.getText().toString();
        String correo = editTextCorreo.getText().toString();
        boolean esMayorEdad = checkBoxMayorEdad.isChecked();

        String subject = "Miniproyecto";
        String body = "Para mas proyectos sigueme en: https://github.com/marccordero";

        new SendEmailTask().execute(subject, body, correo);
    }

    private class SendEmailTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            String subject = params[0];
            String body = params[1];
            String recipient = params[2];

            try {
                sendEmail(subject, body, recipient);
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                Toast.makeText(Formulario.this, "Correo enviado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(Formulario.this, "Error al enviar el correo", Toast.LENGTH_SHORT).show();
            }


            Intent intent = new Intent(Formulario.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    private void sendEmail(String subject, String body, String recipient) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL, PASSWORD);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(EMAIL));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
    public void volverAlMainActivity(View view) {
        Intent intent = new Intent(Formulario.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
