package com.zxc.roomkotlin.swth;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.zxc.roomkotlin.R;

import java.util.ArrayList;
import java.util.List;

public class SwitchActivity extends AppCompatActivity {

    private List<ShiftDTO> shiftDTOList = new ArrayList<>();
    private RecyclerView recyclerView;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initData();
        initUI();
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Please wait...");
    }

    private void initData() {
        shiftDTOList.add(new ShiftDTO("First", "active"));
        shiftDTOList.add(new ShiftDTO("Second", "inactive"));
        shiftDTOList.add(new ShiftDTO("Third", "inactive"));
        shiftDTOList.add(new ShiftDTO("Fourth", "inactive"));
        shiftDTOList.add(new ShiftDTO("Fifth", "inactive"));
    }

    private void initUI() {
        ShiftAdapter shiftAdapter = new ShiftAdapter(shiftDTOList);
        recyclerView.setAdapter(shiftAdapter);
    }

    public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ViewHolder> {

        private List<ShiftDTO> list = new ArrayList<>();

        public ShiftAdapter(List<ShiftDTO> list) {
            this.list = list;
        }

        @Override
        public ShiftAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_shift, parent, false);
            return new ShiftAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ShiftAdapter.ViewHolder holder, final int position) {
            final ShiftDTO dto = list.get(position);

            holder.onOffSwitch.setText(dto.name);
            holder.onOffSwitch.setChecked(dto.status.equalsIgnoreCase("active"));

            holder.onOffSwitch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAnyEnabled()) {
                        if (dto.status.equalsIgnoreCase("active")) {
                            // call off api for this model
                            // on response for that api set the boolean for this model as false call notifyDataSetChanged();
                            callOffApi(position);
                        }
                    } else {
                        // call on api for this model
                        // on response for that api set the boolean for this model as true call notifyDataSetChanged();
                        callOnApi(position);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            CustomSwitch onOffSwitch;

            ViewHolder(View itemView) {
                super(itemView);
                onOffSwitch = (CustomSwitch) itemView.findViewById(R.id.shftSwitch);
            }
        }

        public boolean isAnyEnabled() {
            for (ShiftDTO dto : list) {
                if (dto.status.equalsIgnoreCase("active")) return true;
            }
            return false;
        }

        private void callOffApi(final int position) {
            progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    list.get(position).status = "inactive";
                    notifyDataSetChanged();
                }
            }, 2000);
        }

        private void callOnApi(final int position) {
            progressDialog.show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.dismiss();
                    list.get(position).status = "active";
                    notifyDataSetChanged();
                }
            }, 2000);
        }
    }

    public class ShiftDTO {
        public String name;
        public String status = "inactive";

        public ShiftDTO(String name, String status) {
            this.name = name;
            this.status = status;
        }
    }
}
