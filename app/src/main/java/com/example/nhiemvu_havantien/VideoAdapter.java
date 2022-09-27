package com.example.nhiemvu_havantien;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ItemViewHolder> {
    private final Context context;
    private final List<JSONObject> listRecyclerItem;

    public VideoAdapter(Context context, List<JSONObject> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {


        private TextView nametype;
        private ImageView img;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            nametype = (TextView) itemView.findViewById(R.id.titlevideo);
            img = itemView.findViewById(R.id.img);

        }
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        ItemViewHolder vh = new ItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder viewHolder, @SuppressLint("RecyclerView") int i) {
        try {
            viewHolder.nametype.setText(listRecyclerItem.get(i).getJSONObject("snippet").getString("title"));
            Picasso.get().load(listRecyclerItem.get(i).getJSONObject("snippet").getJSONObject("thumbnails").getJSONObject("high").getString("url")).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailVideo.class);
                try {
                    intent.putExtra("desc",listRecyclerItem.get(i).getJSONObject("snippet").getString("description"));
                    intent.putExtra("title",listRecyclerItem.get(i).getJSONObject("snippet").getString("title"));
                    intent.putExtra("video",listRecyclerItem.get(i).getJSONObject("id").getString("videoId"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                view.getContext().startActivity(intent);
            }
        });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }
}
