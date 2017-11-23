package buscapet.buscapet;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import buscapet.buscapet.modelo.dao.UsuarioDao;

import static buscapet.buscapet.R.layout.activity_login2;

public class LoginActivity2 extends AppCompatActivity {

    private EditText etLogin;
    private EditText etSenha;
    private Button btLogin;
    private Button btRecuperarSenha;
    private Button btLoginToCadastrar;
    private Button btLoginToVisita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login2);

        etLogin = (EditText) findViewById(R.id.etEmail);
        etSenha = (EditText) findViewById(R.id.etSenha);

        btLogin = (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String LOGIN_URL = "https://buscapet.000webhostapp.com/user_login.php?email="
                        + etLogin.getText().toString()
                        + "&senha="
                        + etSenha.getText().toString();
                autenticar(LOGIN_URL);
            }
        });

        // Recuperação de conta - botão "Esqueci minha senha"
        btRecuperarSenha = (Button) findViewById(R.id.btRecuperarSenha);
        btRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidEmailAddress(etLogin.getText().toString())) {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity2.this).create();
                    alertDialog.setTitle("Recuperação de conta");
                    alertDialog.setMessage(Html.fromHtml(
                            "Instruções para a criação de uma nova senha serão enviadas para o endereço de e-mail:<br><br><b>"
                                    +etLogin.getText().toString()
                                    +"</b><br><br>Confirma que seu endereço está correto?"));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Confirmar",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    AlertDialog finalDialog = new AlertDialog.Builder(LoginActivity2.this).create();
                                    finalDialog.setTitle("Mensagem enviada");
                                    finalDialog.setMessage("Verifique sua caixa de entrada e siga as instruções para criar uma nova senha.\n\n" +
                                            "Caso a mensagem não seja recebida, verifique a caixa de spam.");
                                    finalDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                    etSenha.requestFocus();
                                                }
                                            });
                                    finalDialog.show();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Corrigir",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    etLogin.requestFocus();
                                }
                            });
                    alertDialog.show();
                } else if (etLogin.getText().toString().equals("")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity2.this).create();
                    //alertDialog.setTitle("Esqueci minha senha");
                    alertDialog.setMessage("O campo e-mail está vazio.\n\nInforme um e-mail válido e tente novamente.");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    etLogin.requestFocus();
                                }
                            });
                    alertDialog.show();
                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity2.this).create();
                    //alertDialog.setTitle("Esqueci minha senha");
                    alertDialog.setMessage(Html.fromHtml(
                            "O seguinte endereço de e-mail não é válido:<br><br><b>"
                                    +etLogin.getText().toString()
                                    +"</b><br><br>Por favor, informe um endereço válido e tente novamente."));
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });

        btLoginToCadastrar = (Button) findViewById(R.id.btLoginToCadastrar);
        btLoginToCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity2.this, CadastrarActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btLoginToVisita = (Button) findViewById(R.id.btLoginToVisita);
        btLoginToVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity2.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    // Autenticar em Servidor externo
    private void autenticar(String url){

        class GetUSER extends AsyncTask<String, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(LoginActivity2.this, "Autenticando no servidor", null, true, true);
            }

            @Override
            protected String doInBackground(String... strings) {
                String uri = strings[0];
                BufferedReader bufferedReader = null;
                try{
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while((json = bufferedReader.readLine())!=null){
                        sb.append(json);
                    }
                    return sb.toString().trim();
                } catch (Exception e){
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                //String sucesso = "ok";

                if (s.equals("luigi")) {
                    UsuarioDao daoFilho = new UsuarioDao(LoginActivity2.this);
                    daoFilho.cadastrar(etLogin.getText().toString(),etSenha.getText().toString());

                    Intent listagem = new Intent(LoginActivity2.this, MainActivity.class);
                    startActivity(listagem);

                    LoginActivity2.this.finish();
                } else {
                    etLogin.requestFocus();
                    //Snackbar.make(getWindow().getDecorView().getRootView(), "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        }
        GetUSER gu = new GetUSER();
        gu.execute(url);
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

}