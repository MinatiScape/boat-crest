package com.coveiot.android.leonardo.dashboard.health.spo2.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2BluetoothDeviceInfo;
import com.coveiot.android.theme.compundview.AnimatedDotsView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2BluetoothDeviceScanAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4735a;
    @Nullable
    public DeviceSelectionListener b;
    @NotNull
    public ArrayList<SPO2BluetoothDeviceInfo> c;

    /* loaded from: classes3.dex */
    public interface DeviceSelectionListener {
        void onConnectClick(@NotNull SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo);
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4736a;
        @NotNull
        public final Button b;
        @NotNull
        public final AnimatedDotsView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SPO2BluetoothDeviceScanAdapter sPO2BluetoothDeviceScanAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.spo2_ox_name_tv);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.spo2_ox_name_tv)");
            this.f4736a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.connect_btn);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.connect_btn)");
            this.b = (Button) findViewById2;
            View findViewById3 = view.findViewById(R.id.connectProgressDots);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.connectProgressDots)");
            this.c = (AnimatedDotsView) findViewById3;
        }

        @NotNull
        public final AnimatedDotsView getSpo2AnimationDotView() {
            return this.c;
        }

        @NotNull
        public final Button getSpo2OxConnectBtn() {
            return this.b;
        }

        @NotNull
        public final TextView getSpo2OxNameTv() {
            return this.f4736a;
        }
    }

    public SPO2BluetoothDeviceScanAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4735a = context;
        this.c = new ArrayList<>();
    }

    public static final void c(Function2 event, RecyclerView.ViewHolder this_listen, View view) {
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this_listen, "$this_listen");
        event.invoke(Integer.valueOf(this_listen.getAdapterPosition()), Integer.valueOf(this_listen.getItemViewType()));
    }

    public static final void d(SPO2BluetoothDeviceScanAdapter this$0, SPO2BluetoothDeviceInfo spo2BleDeviceInfo, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(spo2BleDeviceInfo, "$spo2BleDeviceInfo");
        if (this$0.b != null) {
            spo2BleDeviceInfo.setConnecting(true);
            DeviceSelectionListener deviceSelectionListener = this$0.b;
            Intrinsics.checkNotNull(deviceSelectionListener);
            deviceSelectionListener.onConnectClick(spo2BleDeviceInfo);
            this$0.notifyDataSetChanged();
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f4735a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final <T extends RecyclerView.ViewHolder> T listen(@NotNull final T t, @NotNull final Function2<? super Integer, ? super Integer, Unit> event) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        t.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.adapters.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SPO2BluetoothDeviceScanAdapter.c(Function2.this, t, view);
            }
        });
        return t;
    }

    public final void setDeviceSelectionListener(@Nullable DeviceSelectionListener deviceSelectionListener) {
        this.b = deviceSelectionListener;
    }

    public final void setScanDeviceList(@Nullable List<SPO2BluetoothDeviceInfo> list) {
        if (list != null) {
            this.c.clear();
            this.c.addAll(list);
        } else {
            this.c = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        String previousDeviceName;
        BluetoothDevice bluetoothDevice;
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo = this.c.get(i);
        Intrinsics.checkNotNullExpressionValue(sPO2BluetoothDeviceInfo, "mDevices[position]");
        final SPO2BluetoothDeviceInfo sPO2BluetoothDeviceInfo2 = sPO2BluetoothDeviceInfo;
        TextView spo2OxNameTv = viewHolder.getSpo2OxNameTv();
        BleDevice bleDevice = sPO2BluetoothDeviceInfo2.getBleDevice();
        if (bleDevice == null || (bluetoothDevice = bleDevice.getmDevice()) == null || (previousDeviceName = bluetoothDevice.getName()) == null) {
            previousDeviceName = sPO2BluetoothDeviceInfo2.getPreviousDeviceName();
        }
        spo2OxNameTv.setText(previousDeviceName);
        if (this.c.get(i).isConnecting()) {
            viewHolder.getSpo2OxConnectBtn().setVisibility(8);
            viewHolder.getSpo2AnimationDotView().setVisibility(0);
            viewHolder.getSpo2AnimationDotView().startAnimation();
        } else {
            viewHolder.getSpo2OxConnectBtn().setVisibility(0);
            viewHolder.getSpo2AnimationDotView().setVisibility(8);
            if (!viewHolder.getSpo2AnimationDotView().isStop()) {
                viewHolder.getSpo2AnimationDotView().stopAnimation();
            }
        }
        viewHolder.getSpo2OxConnectBtn().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.adapters.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SPO2BluetoothDeviceScanAdapter.d(SPO2BluetoothDeviceScanAdapter.this, sPO2BluetoothDeviceInfo2, view);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sp02_bluetooth_device_selection, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new ViewHolder(this, view);
    }
}
