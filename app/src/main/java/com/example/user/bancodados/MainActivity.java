package com.example.user.bancodados;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase banco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banco = this.openOrCreateDatabase("baseNomes",
                Context.MODE_PRIVATE, null);

        banco.execSQL("CREATE table if not exists  frase (" +
                "id_frase INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "texto varchar(60))");


    }

    public void adicionar(View v) {
        EditText et = (EditText) findViewById(R.id.editText);
        banco.execSQL("INSERT INTO frase(texto) values('" +
                et.getText().toString() + "')");

    }


    public void listar(View v) {
        Cursor c = banco.rawQuery("SELECT id_frase, texto FROM frase;", new String[]{});

        LinearLayout linearLayout = (LinearLayout)  findViewById(R.id.resultado);
        linearLayout.removeAllViews();
        while (c.moveToNext()) {

            TextView textView = new TextView(this);
            textView.setText(c.getString(0) + ", " + c.getString(1));
            linearLayout.addView(textView);
        }
        public void deletar(View v) {
            banco.execSQL("delete FROM frase;");
            carregarTabela();
        }

        public void atualizar(View v) {
            Cursor c = banco.rawQuery("SELECT id_frase, texto FROM frase;", new String[]{});

            LinearLayout linearLayout = (LinearLayout)  findViewById(R.id.resultado);
            linearLayout.removeAllViews();
            while (c.moveToNext()) {

                TextView textView = new TextView(this);
                textView.setText(c.getString(0) + ", " + c.getString(1));
                linearLayout.addView(textView);
            }

            c.close();

    }

}

    private void carregarTabela() {

    }
    }
