package com.coveiot.android.leonardo.onboarding.paring.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.bleabstract.models.BleDevice;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ListItemScannedBleDeviceLayoutBinding;
import com.coveiot.android.leonardo.onboarding.paring.adapters.ScannedBleDeviceAdapter;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class ScannedBleDeviceAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public SelectedBleDeviceListener f5230a;
    @NotNull
    public List<BleDevice> b;
    public int c;

    /* loaded from: classes5.dex */
    public interface SelectedBleDeviceListener {
        void deviceSelector(@NotNull BleDevice bleDevice, int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemScannedBleDeviceLayoutBinding f5231a;
        public final /* synthetic */ ScannedBleDeviceAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull ScannedBleDeviceAdapter scannedBleDeviceAdapter, ListItemScannedBleDeviceLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = scannedBleDeviceAdapter;
            this.f5231a = binding;
        }

        public static final void b(ScannedBleDeviceAdapter this$0, BleDevice bleDevice, ViewHolder this$1, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(bleDevice, "$bleDevice");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            this$0.getSelectedBleDeviceListener().deviceSelector(bleDevice, this$1.getAbsoluteAdapterPosition());
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final BleDevice bleDevice) {
            Intrinsics.checkNotNullParameter(bleDevice, "bleDevice");
            ListItemScannedBleDeviceLayoutBinding listItemScannedBleDeviceLayoutBinding = this.f5231a;
            final ScannedBleDeviceAdapter scannedBleDeviceAdapter = this.b;
            listItemScannedBleDeviceLayoutBinding.setDeviceInfo(bleDevice);
            if (scannedBleDeviceAdapter.c == getAbsoluteAdapterPosition()) {
                listItemScannedBleDeviceLayoutBinding.tvDeviceName.setBackgroundResource(R.drawable.rounded_corner_selected_grey_with_border);
            } else {
                listItemScannedBleDeviceLayoutBinding.tvDeviceName.setBackgroundResource(R.drawable.rounded_corner_grey_with_border);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.adapters.b
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ScannedBleDeviceAdapter.ViewHolder.b(ScannedBleDeviceAdapter.this, bleDevice, this, view);
                }
            });
        }
    }

    public ScannedBleDeviceAdapter(@NotNull SelectedBleDeviceListener selectedBleDeviceListener) {
        Intrinsics.checkNotNullParameter(selectedBleDeviceListener, "selectedBleDeviceListener");
        this.f5230a = selectedBleDeviceListener;
        this.b = new ArrayList();
        this.c = -1;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final SelectedBleDeviceListener getSelectedBleDeviceListener() {
        return this.f5230a;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setScannedBleDevices(@NotNull ArrayList<BleDevice> bleDevice) {
        Intrinsics.checkNotNullParameter(bleDevice, "bleDevice");
        this.b = bleDevice;
        notifyDataSetChanged();
    }

    public final void setSelectedBleDeviceListener(@NotNull SelectedBleDeviceListener selectedBleDeviceListener) {
        Intrinsics.checkNotNullParameter(selectedBleDeviceListener, "<set-?>");
        this.f5230a = selectedBleDeviceListener;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setSelectedDevicePosition(int i) {
        this.c = i;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemScannedBleDeviceLayoutBinding inflate = ListItemScannedBleDeviceLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
