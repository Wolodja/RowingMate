package com.example.vvaskovy.rowingmate;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by VVASKOVY on 02.11.2017.
 */

public class TrainingListAdapter extends BaseAdapter {

    private Context context;
    private List<Training> trainingList;

    public TrainingListAdapter(Context context, List<Training> trainingList) {
        this.context = context;
        this.trainingList = trainingList;
    }

    @Override
    public int getCount() {
        return trainingList.size();
    }

    @Override
    public Object getItem(int position) {
        return trainingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.item_traning_list, null);

        TextView moc = (TextView)v.findViewById(R.id.mocText);
        TextView sposob = (TextView)v.findViewById(R.id.sposobText);
        TextView dystans = (TextView)v.findViewById(R.id.dystansText);
        TextView tempo = (TextView)v.findViewById(R.id.tempoText);
        TextView czas = (TextView)v.findViewById(R.id.czasText);
        TextView data = (TextView)v.findViewById(R.id.dataText);

        moc.setText(trainingList.get(position).getInterwals().get(0).getMocInterwalu()+" watt");
        sposob.setText(trainingList.get(position).getSposobTreningu());
        dystans.setText(trainingList.get(position).getInterwals().get(0).getDystansInterwalu()+" m");
        tempo.setText(trainingList.get(position).getInterwals().get(0).getTempoInterwalu()+" s/m");
        czas.setText(trainingList.get(position).getInterwals().get(0).getCzasInterwalu());
        data.setText(trainingList.get(position).getDataTreningu());

        v.setTag(trainingList.get(position).getIdTreningu());

        return v;

    }
}
