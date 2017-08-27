package com.microsoft.recyclerviewdemo.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.microsoft.recyclerviewdemo.R;
import com.microsoft.recyclerviewdemo.model.Country;

import java.util.List;

public class CountriesListAdapter extends
        RecyclerView.Adapter<CountriesListAdapter.CountryViewHolder> {

    private final List<Country> countries;
    private OnCountrySelectedListener listener;

    public CountriesListAdapter(List<Country> countries) {
        this.countries = countries;
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_row_list, parent, false);
        return new CountryViewHolder(view);
    }

    public void setListener(OnCountrySelectedListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, int position) {
        Country country = countries.get(position);

        holder.imageView.setImageResource(country.icon);
        holder.textView.setText(country.name);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Country selectedCountry = countries.get(holder.getAdapterPosition());

                if (listener != null) {
                    listener.onCountrySelected(selectedCountry);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    interface OnCountrySelectedListener {
        void onCountrySelected(Country country);
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
