package com.example.recyclerviewpermisos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recyclerviewpermisos.Modelos.Permiso;
import java.util.List;

public class PermisoAdaptador extends RecyclerView.Adapter<PermisoAdaptador.viewholder> {
    protected PermisoAdaptador.ItemListener itemListener;
    List<Permiso> LP;
    Context context;

    public PermisoAdaptador(PermisoAdaptador.ItemListener itemListener, List<Permiso> LP, Context context) {
        this.itemListener = itemListener;
        this.LP = LP;
        this.context = context;
    }

    @NonNull
    @Override
    public PermisoAdaptador.viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);

        return new viewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PermisoAdaptador.viewholder holder, int position) {
        holder.setData(LP.get(position));
    }

    @Override
    public int getItemCount() {
        return LP.size();
    }

    public interface ItemListener {
        void onItemClick(Permiso item);
    }

    public class viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtIntent;
        Permiso item;

        public viewholder(@NonNull View itemView) {
            super(itemView);

            txtIntent = itemView.findViewById(R.id.txtIntent);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemListener != null) {
                itemListener.onItemClick(item);
            }
        }

        public void setData(Permiso item) {
            this.item = item;
            txtIntent.setText(item.getNombrePermiso());
        }
    }
}
