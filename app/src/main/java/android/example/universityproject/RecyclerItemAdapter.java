package android.example.universityproject;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecyclerItemAdapter extends RecyclerView.Adapter<RecyclerItemAdapter.ViewHolder> {


    Context context;
    ArrayList<ItemModel> arrItems;
    RecyclerItemAdapter(Context context, ArrayList<ItemModel> arrItems){
        this.context = context;
        this.arrItems = arrItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel item = arrItems.get(position);
        holder.itemName.setText(item.getItemName());
        holder.itemCategory.setText(item.getItemCategory());
        holder.contactNo.setText(item.getArea());
    }

    @Override
    public int getItemCount() {
        return arrItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView itemName, itemCategory, contactNo;
        ImageView star, delete;
        int count = 0;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.item_name);
            itemCategory = itemView.findViewById(R.id.item_category);
            contactNo = itemView.findViewById(R.id.contact_no);
            star =  itemView.findViewById(R.id.star);
            delete = itemView.findViewById(R.id.delete);
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

            star.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(count == 0) {
                        star.setImageResource(R.drawable.ic_baseline_star_24);
                        count = 1;
                    } else{
                        star.setImageResource(R.drawable.ic_baseline_star_border_24);
                        count = 0;
                    }
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    databaseReference.removeValue();
                }
            });



        }
    }
}
