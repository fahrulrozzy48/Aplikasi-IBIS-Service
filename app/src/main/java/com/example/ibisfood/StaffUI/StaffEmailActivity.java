package com.example.ibisfood.StaffUI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibisfood.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class StaffEmailActivity extends AppCompatActivity {

    EditText toEmail, cc1Email, cc2Email;
    EditText subjectStaffEmail, descStaffEmail;
    Button btnSendEmail;
    String sMail, sPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_email);

        //ini
        toEmail = findViewById(R.id.email_To);
        cc1Email = findViewById(R.id.email_Cc1);
//        cc2Email = findViewById(R.id.email_Cc2);
        subjectStaffEmail = findViewById(R.id.email_subject);
        descStaffEmail = findViewById(R.id.email_desc);

        btnSendEmail = findViewById(R.id.btn_send_email);

        //sender email credential harus membuat email terlebih dahulu dan mengatur ketentuan layanan
        sMail = "fahrulrozzy555@gmail.com"; //contoh : staff-ibis@gmail.com
        sPassword = "fahrul123"; //contoh : ibispasteur


        btnSendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inisialiasi properties
                Properties properties = new Properties();
                properties.put("mail.smtp.auth", "true");
                properties.put("mail.smtp.starttls.enable", "true");
                properties.put("mail.smtp.host", "smtp.gmail.com");
                properties.put("mail.smtp.port", "587");

                //inisialisasi session
                Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sMail,sPassword);
                    }
                });


                try {
                    //inisialisasi email content
                    Message message = new MimeMessage(session);
                    //Sender mail
                    message.setFrom(new InternetAddress(sMail));
                    //Recipient email
                    message.setRecipients(Message.RecipientType.TO,
                            InternetAddress.parse(toEmail.getText().toString()));
                    message.setRecipients(Message.RecipientType.CC,
                            InternetAddress.parse(cc1Email.getText().toString()));
//                    message.setRecipients(Message.RecipientType.CC,
//                            InternetAddress.parse(cc2Email.getText().toString()));
                    //email subject
                    message.setSubject(subjectStaffEmail.getText().toString());
                    //email desc
                    message.setText(descStaffEmail.getText().toString());


                    //send mail
                    new SendMail().execute(message);

                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        });




    }

    private class SendMail extends AsyncTask<Message,String,String> {
        //ini progres dialog
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //create and show progres dialog
            progressDialog = ProgressDialog.show(StaffEmailActivity.this,"Please Wait", "Sending Mail...", true, false);
        }

        @Override
        protected String doInBackground(Message... messages) {
            try {
                //apabila sukses
                Transport.send(messages[0]);
                return "Succes";
            } catch (MessagingException e) {
                //apabila eror
                e.printStackTrace();
                return "Error";
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //hilangkan progres dialog
            progressDialog.dismiss();
            if(s.equals("Succes")){
                //ketika sukses

                //inisialisasi alert dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(StaffEmailActivity.this);
                builder.setCancelable(false);
                builder.setTitle(Html.fromHtml("<font color='#509324'>Succes</font>"));
                builder.setMessage("Mail send Succesfully.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //clear history
                        subjectStaffEmail.setText("");
                        descStaffEmail.setText("");
                    }
                });

                builder.show();
            } else {
                //apabila eror
                Toast.makeText(getApplicationContext(), "Something when wrong?", Toast.LENGTH_SHORT).show();
            }
        }
    }
}