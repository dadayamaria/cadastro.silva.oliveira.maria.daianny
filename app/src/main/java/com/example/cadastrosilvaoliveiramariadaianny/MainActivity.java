package com.example.cadastrosilvaoliveiramariadaianny;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

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

        // obtemos o ViewModel pois é nele que está o método que se conecta ao servior web.
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        Button bntcadastro = (Button) findViewById(R.id.bntcadastro);

        bntcadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etnome = (EditText) findViewById(R.id.etnome);
                String nome = etnome.getText().toString();
                if(nome.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de nome não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etphone = (EditText) findViewById(R.id.etphone);
                String telefone = etphone.getText().toString();
                if(telefone.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de telefone não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etcpf = (EditText) findViewById(R.id.etcpf);
                String cpf = etcpf.getText().toString();
                if(cpf.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de cpf não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etdate = (EditText) findViewById(R.id.etdate);
                String data = etdate.getText().toString();
                if(data.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de data não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }

                EditText etemail = (EditText) findViewById(R.id.etemail);
                String email = etemail.getText().toString();
                if(email.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Campo de email não preenchido", Toast.LENGTH_LONG).show();
                    return;
                }
                // O ViewModel possui o método register, que envia as informações para o servidor web.
                // O servidor web recebe as infos e cadastra um novo usuário. Se o usuário foi cadastrado
                // com sucesso, a app recebe o valor true. Se não o servidor retorna o valor false.
                //
                // O método de register retorna um LiveData, que na prática é um container que avisa
                // quando o resultado do servidor chegou.
                LiveData<Boolean> resultLD = mainViewModel.cadastrar(nome, cpf, email, telefone, data);

                // Aqui nós observamos o LiveData. Quando o servidor responder, o resultado indicando
                // se o cadastro deu certo ou não será guardado dentro do LiveData. Neste momento o
                // LiveData avisa que o resultado chegou chamando o método onChanged abaixo.
                resultLD.observe(MainActivity.this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        // aBoolean contém o resultado do cadastro. Se aBoolean for true, significa
                        // que o cadastro do usuário foi feito corretamente. Indicamos isso ao usuário
                        // através de uma mensagem do tipo toast e finalizamos a Activity. Quando
                        // finalizamos a Activity, voltamos para a tela de login.
                        if(aBoolean) {
                            Toast.makeText(MainActivity.this, "Novo usuario registrado com sucesso", Toast.LENGTH_LONG).show();
                        }
                        else {
                            // Se o cadastro não deu certo, apenas continuamos na tela de cadastro e
                            // indicamos com uma mensagem ao usuário que o cadastro não deu certo.
                            Toast.makeText(MainActivity.this, "Erro ao registrar novo usuário", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }

        });
    }
}