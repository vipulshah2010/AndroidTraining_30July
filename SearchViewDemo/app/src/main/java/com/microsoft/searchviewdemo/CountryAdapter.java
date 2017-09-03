package com.microsoft.searchviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> implements Filterable {

    private final List<String> countries;
    private final List<String> filteredCountries;

    CountryAdapter(List<String> countries) {
        this.countries = countries;
        this.filteredCountries = new ArrayList<>(countries);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence searchText) {
                final List<String> tempList = new ArrayList<>();
                if (searchText != null) {
                    for (String country : countries) {
                        if (country.contains(searchText)) {
                            tempList.add(country);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = tempList;
                results.count = tempList.size();
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredCountries.clear();
                filteredCountries.addAll((List<String>) filterResults.values);
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return filteredCountries.size();
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        String country = filteredCountries.get(position);
        holder.countryNameTextView.setText(country);
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        TextView countryNameTextView;

        public CountryViewHolder(View itemView) {
            super(itemView);

            countryNameTextView = itemView.findViewById(R.id.countryNameTextView);
        }
    }
}
