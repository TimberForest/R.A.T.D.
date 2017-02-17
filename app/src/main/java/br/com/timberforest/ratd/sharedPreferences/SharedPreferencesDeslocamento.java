package br.com.timberforest.ratd.sharedPreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import br.com.timberforest.ratd.R;
import br.com.timberforest.ratd.dashboards.MainActivity;
import br.com.timberforest.ratd.formulariosActivity.relatorioAssistenciaTecnica.FormRelatorioAssistenciaTecnicaActivity;
import br.com.timberforest.ratd.listActivity.relatorioAssistenciaTecnica.ListRelatorioAssistenciaTecnicaActivity;
import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class SharedPreferencesDeslocamento extends AppCompatActivity {
    public static final String PREFS_NAME = "Preferences";
    Button btn_ini_desl, btn_ini_trab, btn_ini_alm, btn_fim_alm, btn_fim_trab, btn_fim_desl, btn_km_inicial, btn_km_final;
    TextView txt_ini_desl, txt_ini_trab, txt_ini_alm, txt_fim_alm, txt_fim_trab, txt_fim_desl, txt_km_rodado, aux;
    EditText edt_km_inicial, edt_km_final;
    Button btn_limpar;
    private CoordinatorLayout coordinatorLayout;

    final Style styleAlert = new Style.Builder()
            .setTextSize(30)
            .setBackgroundColorValue(Color.RED)
            .setConfiguration(new Configuration.Builder().setDuration(5000).build())
            .build();

    final Style styleConfirm = new Style.Builder()
            .setTextSize(30)
            .setBackgroundColorValue(Color.GREEN)
            .setConfiguration(new Configuration.Builder().setDuration(5000).build())
            .build();
    final Style styleInf = new Style.Builder()
            .setTextSize(30)
            .setBackgroundColorValue(Color.BLUE)
            .setConfiguration(new Configuration.Builder().setDuration(5000).build())
            .build();

    //recupera hora atual
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
    Date hora = Calendar.getInstance().getTime(); // Ou qualquer outra forma que tem
    final String dataFormatada = sdf.format(hora);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences_deslocamento);
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        btn_km_inicial = (Button) findViewById(R.id.btn_km_inicial);
        btn_km_final = (Button) findViewById(R.id.btn_km_final);
        edt_km_final = (EditText) findViewById(R.id.edt_km_final);
        edt_km_inicial = (EditText) findViewById(R.id.edt_km_inicial);
        txt_km_rodado = (TextView) findViewById(R.id.txt_km_rodado);

        txt_ini_desl = (TextView) findViewById(R.id.txt_ini_desl);
        txt_ini_trab = (TextView) findViewById(R.id.txt_ini_trab);
        txt_ini_alm = (TextView) findViewById(R.id.txt_ini_alm);
        txt_fim_alm = (TextView) findViewById(R.id.txt_fim_alm);
        txt_fim_trab = (TextView) findViewById(R.id.txt_fim_trab);
        txt_fim_desl = (TextView) findViewById(R.id.txt_fim_desl);

        //botões gravar horarios
        btn_ini_desl = (Button) findViewById(R.id.btn_ini_desl);
        btn_ini_trab = (Button) findViewById(R.id.btn_ini_trab);
        btn_ini_alm = (Button) findViewById(R.id.btn_ini_alm);
        btn_fim_alm = (Button) findViewById(R.id.btn_fim_alm);
        btn_fim_trab = (Button) findViewById(R.id.btn_fim_trab);
        btn_fim_desl = (Button) findViewById(R.id.btn_fim_desl);

        atualizaCampos();

        btn_ini_desl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniDesl();
            }
        });
        btn_ini_trab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniTrab();
            }
        });
        btn_ini_alm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniAlm();
            }
        });
        btn_fim_alm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fimAlm();
            }
        });
        btn_fim_trab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fimTrab();
            }
        });
        btn_fim_desl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fimDesl();
            }
        });


        btn_km_inicial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
                SharedPreferences.Editor editor = settings.edit();
                if (edt_km_inicial.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Campo Km inicial sem informação, insira informações para poder gravar !", Toast.LENGTH_LONG).show();

                    Crouton.makeText(SharedPreferencesDeslocamento.this, "Campo Km inicial sem informação, insira informações para poder gravar !", styleAlert).show();
                } else {
                    editor.putString("km_inicial", edt_km_inicial.getText().toString());
                    editor.commit();
//                    Toast.makeText(getApplicationContext(), "Km inicial gravado com sucesso !"+edt_km_inicial.getText().toString(), Toast.LENGTH_LONG).show();
                    Crouton.makeText(SharedPreferencesDeslocamento.this, "Km inicial gravado com sucesso !    " + edt_km_inicial.getText().toString() + " km", styleConfirm).show();
                    iniDesl();
                }
            }
        });
        btn_km_final.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

                edt_km_final.getText().toString();
                if (edt_km_final.getText().toString().equals("")){
//                    Toast.makeText(getApplicationContext(), "Campo Km final sem informação, insira informações para poder gravar !", Toast.LENGTH_LONG).show();
                    Crouton.makeText(SharedPreferencesDeslocamento.this, "Campo Km final sem informação, insira informações para poder gravar !", styleAlert).show();
                    atualizaCampos();
                }else {
                    continuaMetodo();
                }
            }
        });

    }

    public void continuaMetodo(){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();

        long aux1 = Long.parseLong(edt_km_inicial.getText().toString());
        long aux2 = Long.parseLong(edt_km_final.getText().toString());

        if (aux2 < aux1) {
//                    Toast.makeText(getApplicationContext(), "Km final informado é menor que o Km inicial !", Toast.LENGTH_LONG).show();
            Crouton.makeText(SharedPreferencesDeslocamento.this, "Km final informado é menor que o Km inicial !", styleAlert).show();
        } else if (aux1 == aux2) {
//                    Toast.makeText(getApplicationContext(), "Km final informado é igual ao Km inicial !", Toast.LENGTH_LONG).show();
            Crouton.makeText(SharedPreferencesDeslocamento.this, "Km final informado é igual ao Km inicial !", styleAlert).show();
        } else {
            editor.putString("km_final", edt_km_final.getText().toString());
            editor.commit();
//                    Toast.makeText(getApplicationContext(), "Km final gravado com sucesso !" + edt_km_final.getText().toString(), Toast.LENGTH_SHORT).show();
            Crouton.makeText(SharedPreferencesDeslocamento.this, "Km final gravado com sucesso !" + edt_km_final.getText().toString(), styleConfirm).show();
            calculaKmRodado();
        }
        atualizaCampos();
    }


    //botão voltar do device
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onKeyDown(keyCode, event);
    }

    private void atualizaCampos() {

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        txt_ini_desl.setText(settings.getString("inicio_deslocamento", ""));
        txt_ini_trab.setText(settings.getString("inicio_trabalho", ""));
        txt_ini_alm.setText(settings.getString("inicio_almoço", ""));
        txt_fim_alm.setText(settings.getString("fim_almoço", ""));
        txt_fim_trab.setText(settings.getString("fim_trabalho", ""));
        txt_fim_desl.setText(settings.getString("fim_deslocamento", ""));

        edt_km_inicial.setText(settings.getString("km_inicial", ""));
        edt_km_final.setText(settings.getString("km_final", ""));
        txt_km_rodado.setText(settings.getString("km_rodado", ""));

        verificaAlteracaoEdt();
        verificaAlteracaoBtn();
    }

    private void verificaAlteracaoBtn() {

        if (edt_km_inicial.getText().toString().equals("")) {
            btn_ini_desl.setEnabled(false);
            btn_ini_trab.setEnabled(false);
            btn_ini_alm.setEnabled(false);
            btn_fim_alm.setEnabled(false);
            btn_fim_trab.setEnabled(false);
            btn_fim_desl.setEnabled(false);
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_ini_desl.setEnabled(true);
            btn_ini_trab.setEnabled(true);
            btn_ini_alm.setEnabled(true);
            btn_fim_alm.setEnabled(false);
            btn_fim_trab.setEnabled(false);
            btn_fim_desl.setEnabled(false);
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        }
        if (txt_ini_desl.getText().toString().equals("")) {
            btn_fim_alm.setEnabled(false);
            btn_fim_trab.setEnabled(false);
            btn_fim_desl.setEnabled(false);
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_ini_trab.setEnabled(true);
            btn_ini_alm.setEnabled(true);
        }

        if (txt_ini_trab.getText().toString().equals("")) {
            btn_fim_trab.setEnabled(false);
            btn_fim_desl.setEnabled(false);
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_fim_trab.setEnabled(true);
        }

        if (txt_ini_alm.getText().toString().equals("")) {
            btn_fim_alm.setEnabled(false);
            btn_fim_desl.setEnabled(false);
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_fim_alm.setEnabled(true);
        }

        if (txt_fim_trab.getText().toString().equals("") || txt_fim_alm.getText().toString().equals("")) {
            btn_fim_desl.setEnabled(false);
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_fim_desl.setEnabled(true);
        }

        if (txt_fim_desl.getText().toString().equals("")) {
            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_km_final.setEnabled(true);
            edt_km_final.setEnabled(true);

            verificaBotaoFinal();
        }

    }


    private void verificaBotaoFinal() {
        if (edt_km_inicial.getText().toString().equals("") || edt_km_final.getText().toString().equals("")) {

        } else {
            btn_km_inicial.setEnabled(false);
            edt_km_inicial.setEnabled(false);

            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        }
    }

    //verificação de botão inicial e final de KM
    private void verificaAlteracaoEdt() {
        if (edt_km_inicial.getText().toString().equals("") && edt_km_final.getText().toString().equals("")) {
            btn_km_inicial.setEnabled(true);
            edt_km_inicial.setEnabled(true);

            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        } else {
            btn_km_inicial.setEnabled(false);
            edt_km_inicial.setEnabled(false);

            edt_km_final.setEnabled(false);
            btn_km_final.setEnabled(false);
        }
    }


    private void calculaKmRodado() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        long aux1 = Long.parseLong(edt_km_inicial.getText().toString());
        long aux2 = Long.parseLong(edt_km_final.getText().toString());
        long aux = aux2 - aux1;
        editor.putString("km_rodado", "" + aux);
        editor.commit();
        Toast.makeText(getApplicationContext(), aux + " km Foram rodados !", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SharedPreferencesDeslocamento.this, ListRelatorioAssistenciaTecnicaActivity.class);
        startActivity(intent);
    }

    public void iniDesl() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (txt_ini_desl.getText().toString().equals("")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("inicio_deslocamento", dataFormatada);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Inicio do Deslocamento:"+dataFormatada+" Gravado !", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
            atualizaCampos();
        } else {
            Toast.makeText(getApplicationContext(), "Inicio de deslocamento já foi informado: " + txt_ini_desl.getText().toString() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
            atualizaCampos();
        }
    }

    public void iniTrab() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (txt_ini_trab.getText().toString().equals("")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("inicio_trabalho", dataFormatada);
            editor.commit();

            Toast.makeText(getApplicationContext(), "Inicio do Deslocamento:"+dataFormatada+" Gravado !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Inicio de deslocamento já foi informado: " + txt_ini_trab.getText().toString() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void iniAlm() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (txt_ini_alm.getText().toString().equals("")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("inicio_almoço", dataFormatada);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Inicio do Deslocamento:" + dataFormatada + " Gravado !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Inicio de deslocamento já foi informado: " + txt_ini_alm.getText().toString() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void fimAlm() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (txt_fim_alm.getText().toString().equals("")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("fim_almoço", dataFormatada);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Inicio do Deslocamento:" + dataFormatada + " Gravado !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Inicio de deslocamento já foi informado: " + txt_fim_alm.getText().toString() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void fimTrab() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (txt_fim_trab.getText().toString().equals("")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("fim_trabalho", dataFormatada);
            editor.commit();
            Toast.makeText(getApplicationContext(), "Inicio do Deslocamento:" + dataFormatada + " Gravado !", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Inicio de deslocamento já foi informado: " + txt_fim_trab.getText().toString() + "", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SharedPreferencesDeslocamento.this, ListRelatorioAssistenciaTecnicaActivity.class);
            startActivity(intent);
        }
    }

    public void fimDesl() {
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (txt_fim_desl.getText().toString().equals("")) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("fim_deslocamento", dataFormatada);
            editor.commit();

            Crouton.makeText(SharedPreferencesDeslocamento.this, "Fim do Deslocamento: " +dataFormatada+" Gravado !", styleConfirm).show();

            atualizaCampos();
        } else {
            Crouton.makeText(SharedPreferencesDeslocamento.this, "Fim do deslocamento já foi informado: " + txt_fim_desl.getText().toString() + " ", styleInf).show();
            atualizaCampos();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        Crouton.cancelAllCroutons();
    }

}
