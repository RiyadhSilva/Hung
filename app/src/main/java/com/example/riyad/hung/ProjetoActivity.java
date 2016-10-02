package com.example.riyad.hung;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ProjetoActivity extends AppCompatActivity {
    private String nome;
    private String desc;
    public Long projetoID;
    private TextView tv_desc;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            nome = (String) bundle.get("nome");
            desc = (String) bundle.get("desc");
            projetoID   = (Long) bundle.get("id");

        }

        System.out.println("O id do projeto eh: " + projetoID);

        getSupportActionBar().setTitle(nome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_desc = (TextView) findViewById(R.id.projeto_tv_desc);
        tv_desc.setText(desc);

        //RecyclerView
        recyclerView = (RecyclerView) findViewById(R.id.atividade_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        //Lista e adapter
        AtividadeDB atividadeDB = new AtividadeDB(this);
        List<Atividade> atividades = atividadeDB.findAllByProjetoID(projetoID);

        recyclerView.setAdapter(new AtividadeAdapter(this, atividades, onClickAtividade()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    //OnClick Projeto
    private AtividadeAdapter.AtividadeOnClickListener onClickAtividade(){
        return new AtividadeAdapter.AtividadeOnClickListener(){
            @Override
            public void onClickAtividade(View view, int idx){
                AtividadeDB atividadeDB = new AtividadeDB(view.getContext());
                List<Atividade> atividades = atividadeDB.findAllByProjetoID(projetoID);
                Atividade a = atividades.get(idx);
                Toast.makeText(getBaseContext(), "Atividade: " + a.nome, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(view.getContext(), AtividadeActivity.class);
                i.putExtra("nome", a.nome);
                i.putExtra("desc", a.desc);
                startActivity(i);
            }
        };
    }

    private Activity getActivity(){ return this;}


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.action_linear_layout){
            //Troca o modo de vizualizacao para lista
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            return true;
        } else if(id == R.id.action_grid_layout){
            //Troca o modo de vizualicao para grid
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
            return true;
        } else if (id == R.id.action_new){
            //Chama a intent NovaAtividade
            Intent i = new Intent(this, NovaAtividadeActivity.class);
            i.putExtra("projeto_id", projetoID);
            startActivity(i);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        //Lista e adapter
        AtividadeDB atividadeDB = new AtividadeDB(this);
        List<Atividade> atividades = atividadeDB.findAllByProjetoID(projetoID);
        recyclerView.setAdapter(new AtividadeAdapter(this, atividades, onClickAtividade()));
        super.onResume();
    }





}
