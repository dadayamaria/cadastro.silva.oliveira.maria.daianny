package com.example.cadastrosilvaoliveiramariadaianny;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button bntcadastro = (Button) findViewById(R.id.bntcadastro);

        bntcadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etnome = (EditText) findViewById(R.id.etnome);
                String nome = etnome.getText().toString();

                EditText etphone = (EditText) findViewById(R.id.etphone);
                String telefone = etphone.getText().toString();

                EditText etcpf = (EditText) findViewById(R.id.etcpf);
                String cpf = etcpf.getText().toString();

                EditText etdate = (EditText) findViewById(R.id.etdate);
                String data = etdate.getText().toString();

                EditText etemail = (EditText) findViewById(R.id.etemail);
                String email = etemail.getText().toString();

                Intent i = new Intent(Intent.ACTION_SENDTO);

                i.setData(Uri.parse("mailto:"));
            }

        });
    }
}