package buscapet.buscapet.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import buscapet.buscapet.DownloadImageTask;
import buscapet.buscapet.R;
import buscapet.buscapet.fragments.PerdidosFragment;
import buscapet.buscapet.fragments.PerdidosFragment;
import buscapet.buscapet.modelo.bean.AnimalPerdido;

public class ListaPerdidosAdapter extends BaseAdapter{

    PerdidosFragment activity;

    public ListaPerdidosAdapter(PerdidosFragment activity) {
        this.activity = activity;
    }


    public int getCount() {
        return activity.animalperdido.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    static class ViewHolderItem {
        TextView animal_especie;
        //ImageView id;
    }


    public View getView(int i, View convertView , ViewGroup viewGroup) {

        ViewHolderItem holder = new ViewHolderItem();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_perdidos, null);

            holder.animal_especie = (TextView) convertView.findViewById(R.id.animal_especie);
            //holder.id= (ImageView) convertView.findViewById(R.id.id);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolderItem) convertView.getTag();
        }

        holder.animal_especie.setText(this.activity.animalperdido.get(i).animal_especie);

        //new DownloadImageTask((ImageView) holder.id).execute(this.activity.animalperdido.get(i).id.toString());

        return convertView;

    }

}
