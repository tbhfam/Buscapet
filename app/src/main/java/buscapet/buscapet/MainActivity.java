package buscapet.buscapet;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import buscapet.buscapet.fragments.AchadosFragment;
import buscapet.buscapet.fragments.AdocaoFragment;
import buscapet.buscapet.fragments.MensagensFragment;
import buscapet.buscapet.fragments.PerdidosFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private FragmentManager fragmentManager;
    private AdocaoFragment fAdocao;
    private AchadosFragment fAchados;
    private PerdidosFragment fPerdidos;
    private MensagensFragment fMenssagens;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.ndAdocao:
                    selectedFragment = AdocaoFragment.newInstance();
                    break;
                case R.id.ndAchados:
                    selectedFragment = AchadosFragment.newInstance();
                    break;
                case R.id.ndPerdidos:
                    selectedFragment = PerdidosFragment.newInstance();
                    break;
                case R.id.ndMensagens:
                    selectedFragment = MensagensFragment.newInstance();
                    break;
            }
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment);
            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        BottomNavigationViewHelper.removeShiftMode(navigation);

    }



}
