package com.example.placementmanager.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.placementmanager.R;
import com.example.placementmanager.database.entities.Student;

import java.util.List;

public class StudentAdapter extends BaseAdapter {

    private Context context;
    private List<Student> studentList;

    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @Override
    public int getCount() {
        return studentList.size();
    }

    @Override
    public Object getItem(int position) {
        return studentList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return studentList.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        StudentViewHolder holder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
            holder = new StudentViewHolder();
            holder.tvName = view.findViewById(R.id.tvStudentName);
            holder.tvDept = view.findViewById(R.id.tvStudentDept);
            holder.tvCgpa = view.findViewById(R.id.tvStudentCgpa);
            holder.tvPlaced = view.findViewById(R.id.tvPlacedStatus);
            view.setTag(holder);
        } else {
            holder = (StudentViewHolder) view.getTag();
        }

        Student student = studentList.get(position);
        holder.tvName.setText("Name: " + student.name);
        holder.tvDept.setText("Department: " + student.department);
        holder.tvCgpa.setText("CGPA: " + student.cgpa);
        holder.tvPlaced.setText("Placed: " + (student.placed ? "✅ Yes" : "❌ No"));

        return view;
    }

    static class StudentViewHolder {
        TextView tvName, tvDept, tvCgpa, tvPlaced;
    }
}
