package buscapet.buscapet.modelo.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

public class UsuarioDao extends DbDao {

    private static final String TABELA = "Usuario";
    private static final String TAG = "DB_USUARIO";

    public UsuarioDao(Context context) {
        super(context);
    }

    public void cadastrar(String login, String senha) {

        // Objeto para armazenar os valores dos campos
        ContentValues values = new ContentValues();

        // Definicao de valores dos campos da tabela
        values.put("login", login);
        values.put("senha", senha);

        // Inserir dados do Cliente no BD
        getWritableDatabase().insert(TABELA, null, values);
        Log.i(TAG, "Usuário cadastrado: " + login);
    }

    public void deletar() {
        // Exclusao do Usuário
        getWritableDatabase().delete(TABELA, null, null);
    }

    public boolean isUsuario() {
        String sql = "select * from " + TABELA ;
        Cursor cursor = null;
        try {
            //Abertura da conexao com BD e execucao da consulta
            cursor = getReadableDatabase().rawQuery(sql, null);
            //Retorna true, se for devolvida alguma linha
            return cursor.getCount() > 0;
        } catch (SQLException e) {
            Log.e(TAG, e.getMessage());
            return false;
        } finally {
            cursor.close();
        }
    }

}
