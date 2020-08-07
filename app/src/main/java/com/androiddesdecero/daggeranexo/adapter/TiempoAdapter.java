package com.androiddesdecero.daggeranexo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androiddesdecero.daggeranexo.R;
import com.androiddesdecero.daggeranexo.model.Tiempo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TiempoAdapter extends RecyclerView.Adapter<TiempoAdapter.TiempoViewHolder> {
    private List<Tiempo> meteorologia;

    public TiempoAdapter(){
        meteorologia = new ArrayList<>();
    }

    @NonNull
    @Override
    public TiempoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_tiempo, viewGroup, false);
        return new TiempoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TiempoViewHolder tiempoViewHolder, int i) {
        Tiempo tiempo = new Tiempo();
        tiempo = meteorologia.get(i);
        Long tiempoLong = Long.parseLong(tiempo.getDt());
        Date date = new Date((long)tiempoLong*1000);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tiempoString = simpleDateFormat.format(date);
        tiempoViewHolder.litFecha.setText(tiempoString);
        tiempoViewHolder.litHumedad.setText(tiempo.getMain().getTemp());
        tiempoViewHolder.litTemperatura.setText(tiempo.getMain().getHumidity());

    }

    @Override
    public int getItemCount() {
        return meteorologia.size();
    }

    public void setData(List<Tiempo> data){
        this.meteorologia.addAll(data);
        notifyDataSetChanged();
    }

    public class TiempoViewHolder extends RecyclerView.ViewHolder{
        private TextView litFecha;
        private TextView litHumedad;
        private TextView litTemperatura;
        public TiempoViewHolder(@NonNull View itemView) {
            super(itemView);
            litFecha = itemView.findViewById(R.id.litFecha);
            litHumedad = itemView.findViewById(R.id.litHumedad) ;
            litTemperatura =  itemView.findViewById(R.id.litTemperatura);

        }
    }
}
