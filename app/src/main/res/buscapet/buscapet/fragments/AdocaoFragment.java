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
import buscapet.buscapet.adapter.ListaAdocaoAdapter;
import buscapet.buscapet.modelo.bean.AnimalAdocao;
import buscapet.buscapet.Download_data.download_complete;

import static buscapet.buscapet.R.string.adocao;

public class AdocaoFragment extends Fragment implements Download_data.download_complete {

    public ListView lvAdocao;
    public ArrayList<AnimalAdocao> animaladocao = new ArrayList<AnimalAdocao>();
    public ListAdapter adapter;

    public AdocaoFragment() {
        // Required empty public constructor
    }

    public static AdocaoFragment newInstance() {
        AdocaoFragment fragment = new AdocaoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View minhaview = inflater.inflate(R.layout.fragment_adocao, container, false);

        lvAdocao = (ListView) minhaview.findViewById(R.id.lvAdocao);
        adapter = new ListaAdocaoAdapter(this);
        lvAdocao.setAdapter(adapter);

        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("http://buscapet.000webhostapp.com/conn.php?table=adocao");

        return minhaview;
    }

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                AnimalAdocao add = new AnimalAdocao();
                add.animal_especie = obj.getString("animal_especie");
                animaladocao.add(add);
            }

            //adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
