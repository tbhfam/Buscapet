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
import buscapet.buscapet.adapter.ListaPerdidosAdapter;
import buscapet.buscapet.modelo.bean.AnimalPerdido;
import buscapet.buscapet.Download_data.download_complete;

import static buscapet.buscapet.R.string.perdidos;

public class PerdidosFragment extends Fragment implements Download_data.download_complete {

        public ListView lvPerdidos;
    public ArrayList<AnimalPerdido> animalperdido = new ArrayList<AnimalPerdido>();
    public ListAdapter adapter;

    public PerdidosFragment() {
        // Required empty public constructor
    }

    public static PerdidosFragment newInstance() {
        PerdidosFragment fragment = new PerdidosFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View minhaview = inflater.inflate(R.layout.fragment_perdidos, container, false);

        lvPerdidos = (ListView) minhaview.findViewById(R.id.lvPerdidos);
        adapter = new ListaPerdidosAdapter(this);
        lvPerdidos.setAdapter(adapter);

        Download_data download_data = new Download_data((download_complete) this);
        download_data.download_data_from_link("https://buscapet.000webhostapp.com/conn.php?table=perdidos");

        return minhaview;
    }

    public void get_data(String data)
    {
        try {
            JSONArray data_array=new JSONArray(data);
            for (int i = 0 ; i < data_array.length() ; i++)
            {
                JSONObject obj=new JSONObject(data_array.get(i).toString());
                AnimalPerdido add = new AnimalPerdido();
                add.animal_especie = obj.getString("animal_especie");
                animalperdido.add(add);
            }

            //adapter.notifyDataSetChanged();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
