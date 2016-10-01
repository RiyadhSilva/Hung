package com.example.riyad.hung;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by riyad on 01/10/2016.
 */
public class Projeto {
    public String nome;
    public int img;

    public Projeto(String nome, int img){
        this.nome = nome;
        this.img = img;
    }

    public static List<Projeto> getProjetos() {
        List<Projeto> projetos = new ArrayList<Projeto>();
        projetos.add(new Projeto("Projeto 1", R.mipmap.ic_launcher));
        projetos.add(new Projeto("Projeto 2", R.mipmap.ic_launcher));
        projetos.add(new Projeto("Projeto 3", R.mipmap.ic_launcher));
        projetos.add(new Projeto("Projeto 4", R.mipmap.ic_launcher));
        projetos.add(new Projeto("Projeto 5", R.mipmap.ic_launcher));
        projetos.add(new Projeto("Projeto 6", R.mipmap.ic_launcher));
        projetos.add(new Projeto("Projeto 7", R.mipmap.ic_launcher));
        return projetos;
    }
}
