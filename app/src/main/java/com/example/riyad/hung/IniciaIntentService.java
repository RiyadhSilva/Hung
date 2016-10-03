package com.example.riyad.hung;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by riyad on 03/10/2016.
 */
public class IniciaIntentService extends IntentService{

    public IniciaIntentService(){
        super("IniciaIntentService");
    }

    private static final int MAX = 1;
    private static final String TAG = "livro";
    private boolean running;
    private String nome;

    @Override
    protected void onHandleIntent(Intent intent){
        nome = intent.getStringExtra("nome");
        String hora = intent.getStringExtra("hora");
        Long lHora = Long.parseLong(hora);
        String minutos = intent.getStringExtra("minutos");
        Long lMinutos = Long.parseLong(minutos);


        Long duracao = ((lMinutos*60)) * 1000;
        Long agora = (System.currentTimeMillis());
        Long futuro = (agora + duracao)/1000;

        running = true;
        //Este metodo executa em uma thread
        //Quando ele terminar, o metodo stopSelf() sera chamado automaticamente
        Long count = agora/1000;
        int time = 0;
        while (running && time < 60){
            fazAlgumaCoisa();
            Log.d(TAG, "IniciaIntentService executando... " + time);
            count++;
            time++;
        }
        Log.d(TAG, "IniciaIntentService fim.");
    }

    private void fazAlgumaCoisa(){
        try{
            //Simula algum processamento
            Thread.sleep(1000);
        }catch (InterruptedException e){
            Log.e(TAG, e.getMessage(), e);
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        //Ao encerrar o servico, altera a flag para a thread parar
        running = false;
        Log.d(TAG, "IniciaIntentService.onDestroy()");
        notificacao("Hung", "Fim do tempo da atividade  " + nome + " !" );
    }

    private void notificacao(String cTitle, String cText) {
        int id = 1;
        String contentTitle = cTitle;
        String contentText = cText;
        Intent intent = new Intent(this, MainActivity.class);
        NotificationUtil.create(this, intent, contentTitle, contentText, id);
    }
}
