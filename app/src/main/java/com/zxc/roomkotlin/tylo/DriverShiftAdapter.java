package com.zxc.roomkotlin.tylo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.zxc.roomkotlin.R;
import com.zxc.roomkotlin.old.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DriverShiftAdapter extends RecyclerView.Adapter<DriverShiftAdapter.recycleview> {

    private static final String TAG = "DriverShiftAdapter";
    Context mctx;
    ArrayList<ShiftModel> shiflist;

    public DriverShiftAdapter(Context ctx, ArrayList<ShiftModel> shiftlist) {
        this.mctx = ctx;
        this.shiflist = shiftlist;
    }

    @NonNull
    @Override
    public recycleview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driver_shift, parent, false);
        recycleview recyclerViewHolder = new recycleview(view);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recycleview holder, final int position) {
        holder.timingTV.setText(shiflist.get(position).getShift_timing());
        holder.statusTV.setText("Status : " + shiflist.get(position).getShift_status());

        Log.e(TAG, "onBindViewHolderdfsdgvsfvgsfd: " + shiflist.get(position).getShift_status());

//        holder.clicklay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shiflist.get(position).getShift_status().equalsIgnoreCase("Active")) {
//                    String shiftid = shiflist.get(position).getShift_id();
//                    mctx.startActivity(new Intent(mctx, ShiftChild.class)
//                            .putExtra(SharedPreferenceConstant.VEHICLEID, SharedPreferenceUtils.getString(SharedPreferenceConstant.VEHICLEID))
//                            .putExtra(SharedPreferenceConstant.SHIFTID, shiftid)
//                            .putExtra(SharedPreferenceConstant.SHIFTTIME, shiflist.get(position).getShift_timing())
//                    );
//                } else {
//                    ToastUtils.longToast(mctx, "Sorry shift is not yet active");
//                }
//            }
//        });

//        holder.openmapLAy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (shiflist.get(position).getShift_status().equalsIgnoreCase("Active")) {
//                    mctx.startActivity(new Intent(mctx, MapsActivity.class));
//                } else {
//                    ToastUtils.longToast(mctx, "Sorry shift is not yet active");
//                }
//            }
//        });

////////////////////////////////////////////////////////////////

//        if (shiflist.get(position).getShift_status().equalsIgnoreCase("Active")) {
//            holder.onoffswitch.setChecked(true);
//            holder.onoffswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
//                }
//            });
//
//
//        } else {
//            holder.onoffswitch.setChecked(false);
//            if (isanyActive) {
//                Log.e(TAG, "onBindViewHolder: " + isanyActive);
//                ToastUtils.shortToast(mctx, "some other shift is running");
//
//            } else {
//                holder.onoffswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                    @Override
//                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                        ShiftModel model = shiflist.get(position);
//                        if (isanyActive) {
//                            ToastUtils.longToast(mctx, "you cannot active 2 shift at one time");
//                        } else {
//                            model.setShift_status("Active");
//                        }
//                    }
//                });
//            }
//        }

        // Switch code start
        holder.onoffswitch.setChecked(shiflist.get(position).getShift_status().equalsIgnoreCase("Active"));

        holder.onoffswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ShiftModel model = shiflist.get(position);
                if (isAnyEnabled()) {
                    if (model.getShift_status().equalsIgnoreCase("Active")) {
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

    public boolean isAnyEnabled() {
        for (ShiftModel model : shiflist) {
            if (model.getShift_status().equalsIgnoreCase("Active")) return true;
        }
        return false;
    }

    private void callOffApi(final int position) {
        String url = "";
        String body = "";
        new ParseUrl_Post(new Callback() {
            @Override
            public void result(String json) {
                if (json != null) {
                    Log.e(TAG, "result: " + json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String msg = jsonObject.getString("msg");
                        int status = jsonObject.getInt("status");
                        ToastUtils.longToast(mctx, msg);
                        if (status == 1) {
                            shiflist.get(position).setShift_status("In-Active");
                            notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, url, body).execute();
    }

    private void callOnApi(final int position) {
        String url = "";
        String body = "";
        new ParseUrl_Post(new Callback() {
            @Override
            public void result(String json) {
                if (json != null) {
                    Log.e(TAG, "result: " + json);
                    try {
                        JSONObject jsonObject = new JSONObject(json);
                        String msg = jsonObject.getString("msg");
                        int status = jsonObject.getInt("status");
                        ToastUtils.longToast(mctx, msg);
                        if (status == 1) {
                            shiflist.get(position).setShift_status("Active");
                            notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, url, body).execute();
    }

//    private void shiftactiveWs(String shift_id) {
//        String body = "";
//        try {
////            body =
////                    "shiftid=" + URLEncoder.encode(shift_id, "UTF-8") +
////                            "&vehicleid=" + URLEncoder.encode(SharedPreferenceUtils.getString(SharedPreferenceConstant.VEHICLEID), "UTF-8");
////            Log.e(TAG, "shiftactiveWs: " + body);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        new ParseUrl_Post(new Callback() {
//            @Override
//            public void result(String json) {
//                if (json != null) {
//                    Log.e(TAG, "result: " + json);
//                    try {
//                        JSONObject jsonObject = new JSONObject(json);
//                        String msg = jsonObject.getString("msg");
//                        int status = jsonObject.getInt("status");
//                        if (status == 1) {
//                            ToastUtils.longToast(mctx, msg);
//                            isanyActive = true;
//                        } else {
//                            ToastUtils.longToast(mctx, msg);
//                        }
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        },"", body).execute();
//
//    }
//
//
//    private void shiftdeactiveWs(String shift_id) {
//        String body = "";
//        try {
////            body =
////
////                    "shiftid=" + URLEncoder.encode(shift_id, "UTF-8") +
////                            "&vehicleid=" + URLEncoder.encode(SharedPreferenceUtils.getString(SharedPreferenceConstant.VEHICLEID), "UTF-8");
////            Log.e(TAG, "shiftactiveWs: " + body);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        new ParseUrl_Post(new Callback() {
//            @Override
//            public void result(String json) {
//                if (json != null) {
//                    Log.e(TAG, "result: " + json);
//                    try {
//                        JSONObject jsonObject = new JSONObject(json);
//                        int status = jsonObject.getInt("status");
//                        SharedPreferenceUtils.setString(SharedPreferenceConstant.VEHICLEID, jsonObject.getString("vehiclid"));
//                        if (status == 1) {
//                            isanyActive = false;
//                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            }
//        },"", body).execute();
//
//
//    }

    @Override
    public int getItemCount() {
        return shiflist.size();
    }

    public class recycleview extends RecyclerView.ViewHolder {
        TextView timingTV, statusTV;
        LinearLayout openmapLAy;
        CardView clicklay;
        Switch onoffswitch;

        public recycleview(View itemView) {
            super(itemView);
//            timingTV = itemView.findViewById(R.id.shifttiming);
//            statusTV = itemView.findViewById(R.id.status);
//            openmapLAy = itemView.findViewById(R.id.openmap);
//            onoffswitch = itemView.findViewById(R.id.switchid);
//            clicklay = itemView.findViewById(R.id.openshift);

        }
    }
}
