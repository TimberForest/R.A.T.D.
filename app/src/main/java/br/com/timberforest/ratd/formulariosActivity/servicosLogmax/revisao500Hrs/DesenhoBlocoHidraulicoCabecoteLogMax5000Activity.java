package br.com.timberforest.ratd.formulariosActivity.servicosLogmax.revisao500Hrs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import br.com.timberforest.ratd.R;

public class DesenhoBlocoHidraulicoCabecoteLogMax5000Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desenho_bloco_hidraulico_cabecote_log_max5000);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_simples_botao_voltar, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_voltar) {
            super.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}