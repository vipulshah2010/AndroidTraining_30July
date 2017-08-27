package com.microsoft.recyclerviewdemo.grid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.recyclerviewdemo.R;
import com.microsoft.recyclerviewdemo.model.Country;

import java.util.List;

public class CountriesGridAdapter extends RecyclerView.Adapter<CountriesGridAdapter.CountryViewHolder> {

    private final List<Country> countries;

    public CountriesGridAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_row_grid, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        Country country = countries.get(position);

        holder.imageView.setImageResource(country.icon);
        holder.textView.setText(country.name);
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public CountryViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.imageView);
            textView = view.findViewById(R.id.textView);
        }
    }
}
