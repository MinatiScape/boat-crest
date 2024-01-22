package com.coveiot.android.leonardo.onboarding.paring.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ListItemDeviceLayoutBinding;
import com.coveiot.android.devicemodels.DeviceRemoteConfig;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.leonardo.onboarding.paring.adapters.DeviceModelsAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class DeviceModelsAdapter extends RecyclerView.Adapter<ViewHolder> implements Filterable {
    @NotNull
    public final DeviceItemListener h;
    @NotNull
    public List<DeviceRemoteConfig.DeviceModelsBean> i;
    @NotNull
    public List<DeviceRemoteConfig.DeviceModelsBean> j;
    public int k;
    public int l;
    @NotNull
    public Filter m;

    /* loaded from: classes5.dex */
    public interface DeviceItemListener {
        void deviceCount(int i);

        void deviceSelected(@NotNull DeviceRemoteConfig.DeviceModelsBean deviceModelsBean, int i);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemDeviceLayoutBinding f5228a;
        public final /* synthetic */ DeviceModelsAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull DeviceModelsAdapter deviceModelsAdapter, ListItemDeviceLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = deviceModelsAdapter;
            this.f5228a = binding;
        }

        public static final void b(DeviceModelsAdapter this$0, ViewHolder this$1, DeviceRemoteConfig.DeviceModelsBean device, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(this$1, "this$1");
            Intrinsics.checkNotNullParameter(device, "$device");
            this$0.l = this$0.k;
            this$0.k = this$1.getAbsoluteAdapterPosition();
            this$0.notifyItemChanged(this$0.l);
            this$0.notifyItemChanged(this$0.k);
            this$0.getListener().deviceSelected(device, this$1.getAbsoluteAdapterPosition());
        }

        public final void bind(@NotNull final DeviceRemoteConfig.DeviceModelsBean device) {
            Intrinsics.checkNotNullParameter(device, "device");
            ListItemDeviceLayoutBinding listItemDeviceLayoutBinding = this.f5228a;
            final DeviceModelsAdapter deviceModelsAdapter = this.b;
            listItemDeviceLayoutBinding.setDeviceData(device);
            listItemDeviceLayoutBinding.ivDevice.setImageResource(DeviceUtils.Companion.getWatchModelImage(device));
            if (deviceModelsAdapter.k == getAbsoluteAdapterPosition()) {
                listItemDeviceLayoutBinding.clMain.setBackgroundResource(R.drawable.selected_device_background);
            } else {
                listItemDeviceLayoutBinding.clMain.setBackgroundResource(R.drawable.unselected_device_background);
            }
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.onboarding.paring.adapters.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DeviceModelsAdapter.ViewHolder.b(DeviceModelsAdapter.this, this, device, view);
                }
            });
        }
    }

    public DeviceModelsAdapter(@NotNull DeviceItemListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.h = listener;
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = -1;
        this.l = -1;
        this.m = new Filter() { // from class: com.coveiot.android.leonardo.onboarding.paring.adapters.DeviceModelsAdapter$filter$1
            @Override // android.widget.Filter
            @NotNull
            public Filter.FilterResults performFiltering(@Nullable CharSequence charSequence) {
                List list;
                List<DeviceRemoteConfig.DeviceModelsBean> list2;
                ArrayList arrayList = new ArrayList();
                if (charSequence != null) {
                    if (!(charSequence.length() == 0)) {
                        list2 = DeviceModelsAdapter.this.j;
                        for (DeviceRemoteConfig.DeviceModelsBean deviceModelsBean : list2) {
                            String name = deviceModelsBean.getName();
                            Intrinsics.checkNotNullExpressionValue(name, "device.name");
                            Locale locale = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
                            String lowerCase = name.toLowerCase(locale);
                            Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                            String obj = charSequence.toString();
                            Locale locale2 = Locale.getDefault();
                            Intrinsics.checkNotNullExpressionValue(locale2, "getDefault()");
                            String lowerCase2 = obj.toLowerCase(locale2);
                            Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase(locale)");
                            if (StringsKt__StringsKt.contains$default((CharSequence) lowerCase, (CharSequence) lowerCase2, false, 2, (Object) null)) {
                                arrayList.add(deviceModelsBean);
                            }
                        }
                        Filter.FilterResults filterResults = new Filter.FilterResults();
                        filterResults.values = arrayList;
                        return filterResults;
                    }
                }
                list = DeviceModelsAdapter.this.j;
                arrayList.addAll(list);
                Filter.FilterResults filterResults2 = new Filter.FilterResults();
                filterResults2.values = arrayList;
                return filterResults2;
            }

            @Override // android.widget.Filter
            @SuppressLint({"NotifyDataSetChanged"})
            public void publishResults(@Nullable CharSequence charSequence, @NotNull Filter.FilterResults results) {
                List list;
                List list2;
                List list3;
                Intrinsics.checkNotNullParameter(results, "results");
                list = DeviceModelsAdapter.this.i;
                list.clear();
                list2 = DeviceModelsAdapter.this.i;
                Object obj = results.values;
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.collections.Collection<com.coveiot.android.devicemodels.DeviceRemoteConfig.DeviceModelsBean>");
                list2.addAll((Collection) obj);
                DeviceModelsAdapter.DeviceItemListener listener2 = DeviceModelsAdapter.this.getListener();
                list3 = DeviceModelsAdapter.this.i;
                listener2.deviceCount(list3.size());
                DeviceModelsAdapter.this.notifyDataSetChanged();
            }
        };
    }

    @Override // android.widget.Filterable
    @NotNull
    public Filter getFilter() {
        return this.m;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.i.size();
    }

    @NotNull
    public final DeviceItemListener getListener() {
        return this.h;
    }

    public final void setDeviceList(@NotNull List<? extends DeviceRemoteConfig.DeviceModelsBean> deviceData) {
        Intrinsics.checkNotNullParameter(deviceData, "deviceData");
        this.j.clear();
        List<DeviceRemoteConfig.DeviceModelsBean> asMutableList = TypeIntrinsics.asMutableList(deviceData);
        this.i = asMutableList;
        this.j.addAll(asMutableList);
        notifyDataSetChanged();
    }

    public final void setSelectionPosition(int i) {
        this.k = i;
        this.l = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.i.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemDeviceLayoutBinding inflate = ListItemDeviceLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            Lay…, parent, false\n        )");
        return new ViewHolder(this, inflate);
    }
}
