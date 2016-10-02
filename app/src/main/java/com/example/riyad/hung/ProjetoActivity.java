package com.example.riyad.hung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ProjetoActivity extends AppCompatActivity {
    private String nome;
    private String desc;
    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            nome = (String) bundle.get("nome");
            desc = (String) bundle.get("desc");

        }

        getSupportActionBar().setTitle(nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_desc = (TextView) findViewById(R.id.projeto_tv_desc);
        tv_desc.setText(desc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
