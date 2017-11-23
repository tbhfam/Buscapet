package buscapet.buscapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btCadastrar;
    private Button btCadastrarToLogin;
    private Button btCadastrarToVisita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etSenha = (EditText)findViewById(R.id.etSenha);

        btCadastrar = (Button)findViewById(R.id.btCadastrar);
        btCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidEmailAddress(etEmail.getText().toString())) {
                    BackgroundTask backgroundTask = new BackgroundTask();
                    backgroundTask.execute(etEmail.getText().toString(), etSenha.getText().toString());
                    Toast.makeText(CadastrarActivity.this, "Válido", Toast.LENGTH_SHORT).show();
                } else {
                    etEmail.requestFocus();
                    Toast.makeText(CadastrarActivity.this, "Email invalido", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btCadastrarToLogin = (Button) findViewById(R.id.btCadastrarToLogin);
        btCadastrarToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this, LoginActivity2.class);
                startActivity(intent);
                finish();
            }
        });

        btCadastrarToVisita = (Button) findViewById(R.id.btCadastrarToVisita);
        btCadastrarToVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CadastrarActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }


}
