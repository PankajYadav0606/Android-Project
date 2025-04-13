package com.example.placementmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.placementmanager.R;
import com.example.placementmanager.database.entities.Company;

import java.util.List;

public class CompanyAdapter extends BaseAdapter {

    private Context context;
    private List<Company> companyList;

    public CompanyAdapter(Context context, List<Company> companyList) {
        this.context = context;
        this.companyList = companyList;
    }

    @Override
    public int getCount() {
        return companyList.size();
    }

    @Override
    public Object getItem(int position) {
        return companyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return companyList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        CompanyViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_company, parent, false);
            holder = new CompanyViewHolder();
            holder.tvName = view.findViewById(R.id.tvCompanyName);
            holder.tvRole = view.findViewById(R.id.tvJobRole);
            holder.tvEligibility = view.findViewById(R.id.tvEligibility);
            holder.tvDriveDate = view.findViewById(R.id.tvDriveDate);
            view.setTag(holder);
        } else {
            holder = (CompanyViewHolder) view.getTag();
        }

        Company company = companyList.get(position);
        holder.tvName.setText("Company: " + company.name);
        holder.tvRole.setText("Role: " + company.jobRole);
        holder.tvEligibility.setText("Eligibility: " + company.eligibilityCriteria);
        holder.tvDriveDate.setText("Drive Date: " + company.driveDate);

        return view;
    }

    static class CompanyViewHolder {
        TextView tvName, tvRole, tvEligibility, tvDriveDate;
    }
}
