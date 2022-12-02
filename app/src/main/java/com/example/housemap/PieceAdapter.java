package com.example.housemap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.housemap.model.Mur;
import com.example.housemap.model.Piece;
import de.hdodenhof.circleimageview.CircleImageView;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

public class PieceAdapter extends RecyclerView.Adapter<PieceAdapter.PieceViewHolder> implements  RecyclerViewInterface{
    Context mContext;
    List<Piece> pieceList;
    private final RecyclerViewInterface recyclerViewInterface;

    public PieceAdapter(Context mContext, List<Piece> pieceList, RecyclerViewInterface recyclerViewInterface) {
        this.mContext = mContext;
        this.pieceList = pieceList;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @NotNull
    public PieceViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_piece,parent,false);
        return new PieceViewHolder(view);
    }

    public void onBindViewHolder(@NonNull @NotNull PieceViewHolder holder, int position) {
        Piece piece = pieceList.get(position);
        holder.name_piece.setText(piece.getNom());
        Mur premMur = piece.getMur(0);
        ImageView img = holder.img_piece;
        FileInputStream fis;
        try {
            if(premMur!=null) { //si une photo du mur existe
                fis = mContext.openFileInput(premMur.getNomPhoto());
                Bitmap bm = BitmapFactory.decodeStream(fis);
                //Picasso.get().load(premMur.getNomPhoto()).into(holder.img_piece);
                holder.img_piece.setImageBitmap(bm);
            }
            else{
                holder.img_piece.setImageResource(R.drawable.icon_room);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getItemCount() {
        return pieceList.size();
    }

    @Override
    public void OnItemClick(int position) {

    }

    public class PieceViewHolder extends RecyclerView.ViewHolder {
        TextView name_piece;
        CircleImageView img_piece;

        public PieceViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name_piece = itemView.findViewById(R.id.name_porte);
            img_piece = itemView.findViewById(R.id.img_piece);
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
