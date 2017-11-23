package buscapet.buscapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SplashActivity extends AppCompatActivity {

    private Button btSplashToCadastrar;
    private Button btSplashToLogin;
    private Button btSplashToVisita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btSplashToCadastrar = (Button) findViewById(R.id.btSplashToCadastrar);
        btSplashToLogin = (Button) findViewById(R.id.btSplashToLogin);
        btSplashToVisita = (Button) findViewById(R.id.btSplashToVisita);

        btSplashToCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, CadastrarActivity.class);
                startActivity(intent);
            }
        });

        btSplashToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity2.class);
                startActivity(intent);
            }
        });

        btSplashToVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
