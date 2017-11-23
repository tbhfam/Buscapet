package buscapet.buscapet;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class BackgroundTask extends AsyncTask<String, Void, String> {

    String add_info_url;
    @Override
    protected void onPreExecute() {
        add_info_url = "https://buscapet.000webhostapp.com/user_cadastro.php";
    }

    @Override
    protected String doInBackground(String... arg) {
        String email, senha;
        email = arg[0];
        senha = arg[1];
        try {
            URL url = new URL(add_info_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"utf-8"),8);
            String data_String =
                    URLEncoder.encode("email","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"+
                    URLEncoder.encode("senha","UTF-8")+"="+URLEncoder.encode(senha,"UTF-8");
            bufferedWriter.write(data_String);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            inputStream.close();
            httpURLConnection.disconnect();
            Log.d("email = ",email);
            Log.d("senha = ",senha);
            return "Data has been inserted successfuly";
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
