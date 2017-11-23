package buscapet.buscapet.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import buscapet.buscapet.Download_data;
import buscapet.buscapet.R;
import buscapet.buscapet.adapter.ListaAchadosAdapter;
import buscapet.buscapet.modelo.bean.AnimalAchado;
import buscapet.buscapet.Download_data.download_complete;

import static buscapet.buscapet.R.string.achados;

public class AchadosFragment extends Fragment implements Download_data.download_complete {

    public ListView lvAchados;
    public ArrayList<AnimalAchado> animalachado = new ArrayList<AnimalAchado>();
    public ListAdapter adapter;

    public AchadosFragment() {
        // Required empty public constructor
    }

    public static AchadosFragment newInstance() {
        AchadosFragment fragment = new AchadosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View minhaview = inflater.inflate(R.layout.fragment_achados, container, false);

        lvAchados = (ListView) minhaview.findViewById(R.id.lvAchados);
        adapter = new ListaAchadosAdapter(this);
        lvAchados.setAdapter(adapter);

        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("http://buscapet.000webhostapp.com/conn.php?table=perdidos");

        return minhaview;
    }

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                AnimalAchado add = new AnimalAchado();
                add.animal_especie = obj.getString("animal_especie");
                animalachado.add(add);
            }

            //adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
