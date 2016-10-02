package com.example.riyad.hung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NovoProjetoActivity extends AppCompatActivity {
    private EditText nome;
    private EditText desc;
    private Projeto projeto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_projeto);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.drawable.ic_action_add);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Novo Projeto");
        actionBar.setDisplayHomeAsUpEnabled(true);

        nome = (EditText) findViewById(R.id.novo_projeto_tv_nome);
        desc = (EditText) findViewById(R.id.novo_projeto_tv_desc);


    }

    public void criarNovoProjeto(View view){
        projeto = new Projeto();

        projeto.nome = nome.getText().toString();
        projeto.desc = desc.getText().toString();
        projeto.img  = R.drawable.ic_projeto;

        //Conexao com o banco
        ProjetoDB projetoDB = new ProjetoDB(this);
        //Salva no banco
        projetoDB.save(projeto);
        toast("Projeto Criado!");
        finish();



    }

    private void toast (String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.activity_novo_projeto_menu, menu);
        return true;
    }
}
