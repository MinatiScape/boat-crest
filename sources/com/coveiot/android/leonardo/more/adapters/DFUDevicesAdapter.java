package com.coveiot.android.leonardo.more.adapters;

import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DFUDevicesAdapter extends RecyclerView.Adapter<DFUDeviceViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<BluetoothDevice> f5050a;
    @NotNull
    public final DFUDeviceSelectionListener b;

    /* loaded from: classes5.dex */
    public interface DFUDeviceSelectionListener {
        void onDeviceSelected(@NotNull BluetoothDevice bluetoothDevice);
    }

    /* loaded from: classes5.dex */
    public static final class DFUDeviceViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5051a;
        public final TextView b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DFUDeviceViewHolder(@NotNull View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.f5051a = (TextView) itemView.findViewById(R.id.tv_device_name);
            this.b = (TextView) itemView.findViewById(R.id.tv_device_address);
        }

        public final void bindView(@NotNull BluetoothDevice device) {
            Intrinsics.checkNotNullParameter(device, "device");
            this.f5051a.setText(device.getName());
            this.b.setText(device.getAddress());
        }

        public final TextView getDeviceAddress() {
            return this.b;
        }

        public final TextView getDeviceName() {
            return this.f5051a;
        }
    }

    public DFUDevicesAdapter(@NotNull ArrayList<BluetoothDevice> deviceList, @NotNull DFUDeviceSelectionListener listener) {
        Intrinsics.checkNotNullParameter(deviceList, "deviceList");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5050a = deviceList;
        this.b = listener;
    }

    public static final void b(DFUDevicesAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DFUDeviceSelectionListener dFUDeviceSelectionListener = this$0.b;
        BluetoothDevice bluetoothDevice = this$0.f5050a.get(i);
        Intrinsics.checkNotNullExpressionValue(bluetoothDevice, "deviceList.get(position)");
        dFUDeviceSelectionListener.onDeviceSelected(bluetoothDevice);
    }

    @NotNull
    public final ArrayList<BluetoothDevice> getDeviceList() {
        return this.f5050a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5050a.size();
    }

    @NotNull
    public final DFUDeviceSelectionListener getListener() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull DFUDeviceViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        BluetoothDevice bluetoothDevice = this.f5050a.get(i);
        Intrinsics.checkNotNullExpressionValue(bluetoothDevice, "deviceList.get(position)");
        holder.bindView(bluetoothDevice);
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DFUDevicesAdapter.b(DFUDevicesAdapter.this, i, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public DFUDeviceViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dfu_devices_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context)\n   …list_item, parent, false)");
        return new DFUDeviceViewHolder(inflate);
    }
}
