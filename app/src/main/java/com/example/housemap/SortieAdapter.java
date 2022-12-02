package com.example.housemap;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.housemap.model.Sortie;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SortieAdapter extends RecyclerView.Adapter<SortieAdapter.SortieViewHolder> implements RecyclerViewInterface {
    Context mContext;
    List<Sortie> porteList;
    private final RecyclerViewInterface recyclerViewInterface;

    public SortieAdapter(Context mContext, List<Sortie> porteList, RecyclerViewInterface recyclerViewInterface) {
        this.mContext = mContext;
        this.porteList = porteList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @NotNull
    public SortieAdapter.SortieViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_porte,parent,false);
        return new SortieAdapter.SortieViewHolder(view);
    }

    public void onBindViewHolder(@NonNull @NotNull SortieAdapter.SortieViewHolder holder, int position) {
        Sortie porte = porteList.get(position);
        holder.name_porte.setText(porte.getNomPiece());
    }

    @Override
    public int getItemCount() {
        return porteList.size();
    }

    @Override
    public void OnItemClick(int position) {

    }

    public class SortieViewHolder extends RecyclerView.ViewHolder {
        TextView name_porte;

        public SortieViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name_porte = itemView.findViewById(R.id.name_porte);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();
                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.OnItemClick(pos);
                        }

                    }
                }
            });
        }
    }
}
