package com.example.comp304_lab04;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TestRVAdapter extends ListAdapter<Test, TestRVAdapter.ViewHolder> {

    private OnItemClickListener listener;
    TestRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Test> DIFF_CALLBACK = new DiffUtil.ItemCallback<Test>() {
        @Override
        public boolean areItemsTheSame(Test oldItem, Test newItem) {
            return oldItem.getTestID() == newItem.getTestID();
        }

        @Override
        public boolean areContentsTheSame(Test oldItem, Test newItem) {

            return oldItem.getPatientID() == newItem.getPatientID() &&
                    oldItem.getNurseID().equals(newItem.getNurseID()) &&
                    oldItem.getBpl().equals(newItem.getBpl()) &&
                    oldItem.getBph().equals(newItem.getBph()) &&
                    oldItem.getTemperature() == newItem.getTemperature();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.test_rv_item, parent, false);
        return new ViewHolder(item);
    }

    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Test model = getTestAt(position);
        holder.testIDTV.setText(Integer.toString(model.getTestID()));
        holder.patientIDTV.setText(Integer.toString(model.getPatientID()));
        holder.nurseIDTV.setText(model.getNurseID());
        holder.bpl.setText(model.getBpl());
        holder.bph.setText(model.getBph());
        holder.temperature.setText(Integer.toString(model.getTemperature()));
    }

    public Test getTestAt(int position) {
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView testIDTV, patientIDTV, nurseIDTV, bpl, bph, temperature;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            testIDTV = itemView.findViewById(R.id.TVTestID);
            patientIDTV = itemView.findViewById(R.id.TVTestPatientID);
            nurseIDTV = itemView.findViewById(R.id.TVTestNurseID);
            bpl = itemView.findViewById(R.id.TVTestBPL);
            bph = itemView.findViewById(R.id.TVTestBPH);
            temperature = itemView.findViewById(R.id.TVTestTemperature);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(getItem(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Test test);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
