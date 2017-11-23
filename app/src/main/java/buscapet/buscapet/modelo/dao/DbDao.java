package buscapet.buscapet.modelo.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbDao extends SQLiteOpenHelper {

    // Constantes para auxílio no controle de versões
    private static final int VERSAO = 1;
    private static final String DATABASE = "ModeloCadastro";
    private static final String TABELA_A = "Usuario";

    // Constante para log no Logcat
    private static final String TAG = "DB_MODELOCADASTRO";

    // Método Construtor
    public DbDao(Context context) {

        // Chama o construtor para acessar o BD
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        /* Definição do comando DDL a ser executado
           para criar a tabela Cliente */

        String sql_a = "CREATE TABLE " + TABELA_A + " ( "
                + "id INTEGER PRIMARY KEY, "
                + "nome TEXT, sobrenome TEXT, telefone TEXT, endereco TEXT, email TEXT, site TEXT, foto TEXT)";

        // Execução do comando no SQLite
        sqLiteDatabase.execSQL(sql_a);
        Log.i(TAG, "Tabela criada com sucesso: " + TABELA_A);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        // Definição do comando para excluir a tabela Cliente
        String sql_a = "DROP TABLE IF EXISTS " + TABELA_A;
        //Execução do comando de exclusão
        sqLiteDatabase.execSQL(sql_a);
        //Cria novamente a estrutura
        onCreate(sqLiteDatabase);
    }
}
