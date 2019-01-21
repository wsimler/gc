package com.zxc.roomkotlin;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

public class Demo extends RecyclerView.Adapter<Demo.ViewHolder> {

    private List<Model> list = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.aSwitch.setChecked(list.get(position).isEnabled);

        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Model model = list.get(position);
                if (isAnyEnabled()) {
                    if (model.isEnabled){
                        // call off api for this model
                        // on response for that api set the boolean for this model as false call notifyDataSetChanged();
                    }
                } else {
                    // call on api for this model
                    // on response for that api set the boolean for this model as true call notifyDataSetChanged();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        Switch aSwitch;

        ViewHolder(View itemView) {
            super(itemView);
        }
    }

    public boolean isAnyEnabled() {
        for (Model model : list) {
            if (model.isEnabled) return true;
        }
        return false;
    }

    public class Model {
        public boolean isEnabled = false;
    }
}
