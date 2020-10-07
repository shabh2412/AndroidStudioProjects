package com.rishabhpanesar.listviewtextimage;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomListView extends ArrayAdapter<String> {
    private String[] carNames;
    private String[] carDescription;
    private Integer[] carImages;
    private Activity context;

    public CustomListView(Activity context, String[] carNames, String[] carDescription, Integer[] carImages) {
        super(context, R.layout.listview_layout, carNames);
        this.carNames = carNames;
        this.carDescription = carDescription;
        this.carImages = carImages;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            view = inflater.inflate(R.layout.listview_layout, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(carImages[position]);
        viewHolder.name.setText(carNames[position]);
        viewHolder.description.setText(carDescription[position]);

        return view;
    }

    class ViewHolder {
        TextView name, description;
        ImageView imageView;

        public ViewHolder(View view) {
            this.name = view.findViewById(R.id.tvCarName);
            this.description = view.findViewById(R.id.tvCarDesription);
            this.imageView = view.findViewById(R.id.imageView);

        }
    }

}
