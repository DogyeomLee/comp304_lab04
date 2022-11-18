package com.example.comp304_lab04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PatientRVAdapter extends ListAdapter<Patient, PatientRVAdapter.ViewHolder> {

    private OnItemClickListener listener;
    PatientRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Patient> DIFF_CALLBACK = new DiffUtil.ItemCallback<Patient>() {
        @Override
        public boolean areItemsTheSame(Patient oldItem, Patient newItem) {
            return oldItem.getPatientID() == newItem.getPatientID();
        }

        @Override
        public boolean areContentsTheSame(Patient oldItem, Patient newItem) {

            return oldItem.getFirstName().equals(newItem.getFirstName()) &&
                    oldItem.getLastName().equals(newItem.getLastName()) &&
                    oldItem.getDepartment().equals(newItem.getDepartment()) &&
                    oldItem.getRoom().equals(newItem.getRoom());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_rv_item, parent, false);
        return new ViewHolder(item);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Patient model = getPatientAt(position);
        holder.patientIDTV.setText(Integer.toString(model.getPatientID()));
        holder.firstNameTV.setText(model.getFirstName());
        holder.lastNameTV.setText(model.getLastName());
        holder.departmentTV.setText(model.getDepartment());
        holder.nurseIDTV.setText(model.getNurseID());
        holder.roomTV.setText(model.getRoom());
    }

    public Patient getPatientAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView patientIDTV, firstNameTV, lastNameTV, departmentTV, nurseIDTV, roomTV;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            patientIDTV = itemView.findViewById(R.id.TVPatientID);
            firstNameTV = itemView.findViewById(R.id.TVfirstName);
            lastNameTV = itemView.findViewById(R.id.TVlastName);
            departmentTV = itemView.findViewById(R.id.TVDepartment);
            nurseIDTV = itemView.findViewById(R.id.TVNurseID);
            roomTV = itemView.findViewById(R.id.TVRoom);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Patient patient);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
