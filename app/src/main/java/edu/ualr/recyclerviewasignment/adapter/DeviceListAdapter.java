package edu.ualr.recyclerviewasignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.ualr.recyclerviewasignment.R;
import edu.ualr.recyclerviewasignment.data.DeviceDataFormatTools;
import edu.ualr.recyclerviewasignment.model.Device;

/**
 * Created by irconde on 2019-10-04.
 */
public class DeviceListAdapter extends RecyclerView.Adapter {

    private List<Device> mItems;
    private Context mContext;

    public DeviceListAdapter(Context context) {
        this.mContext = context;
        this.mItems = new ArrayList<>();
    }

    public void addAll(List<Device> devices) {
        for (int i = 0; i < devices.size(); i++) {
            mItems.add(devices.get(i));
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.device_list_item, parent, false);
        vh = new DeviceViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Device device = mItems.get(position);
        DeviceViewHolder view = (DeviceViewHolder) holder;
        view.name.setText(device.getName());
        view.elapsedTimeLabel.setText(DeviceDataFormatTools.getTimeSinceLastConnection(mContext, device));
        DeviceDataFormatTools.setDeviceStatusMark(mContext, view.statusMark, device);
        DeviceDataFormatTools.setDeviceThumbnail(mContext, view.image, device);
        DeviceDataFormatTools.setConnectionBtnLook(mContext, view.connectBtn, device.getDeviceStatus());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RelativeLayout image;
        private ImageView statusMark;
        private TextView name;
        private TextView elapsedTimeLabel;
        private ImageButton connectBtn;

        public DeviceViewHolder(View v) {
            super(v);
            image = v.findViewById(R.id.image);
            statusMark = v.findViewById(R.id.status_mark);
            name = v.findViewById(R.id.name);
            elapsedTimeLabel = v.findViewById(R.id.elapsed_time);
            connectBtn = v.findViewById(R.id.device_connect_btn);
            connectBtn.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            toggleConnection();
        }

        private void toggleConnection() {
            Device device = mItems.get(getAdapterPosition());
            Device.DeviceStatus deviceStatus = device.getDeviceStatus();
            if (deviceStatus == Device.DeviceStatus.Connected) {
                device.setDeviceStatus(Device.DeviceStatus.Ready);
                device.setLastConnection(new Date());
            } else if (deviceStatus == Device.DeviceStatus.Ready) {
                device.setDeviceStatus(Device.DeviceStatus.Connected);
            }
            notifyItemChanged(this.getAdapterPosition());
        }
    }
}
