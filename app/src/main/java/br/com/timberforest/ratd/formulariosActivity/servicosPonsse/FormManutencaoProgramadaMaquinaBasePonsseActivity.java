package br.com.timberforest.ratd.formulariosActivity.servicosPonsse;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import br.com.timberforest.ratd.R;

public class FormManutencaoProgramadaMaquinaBasePonsseActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_manutencao_programada_maquina_base_ponsse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_simples_botao_voltar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_voltar) {
            super.finish();
        }

        return super.onOptionsItemSelected(item);
    }
}