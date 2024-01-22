package com.coveiot.android.leonardo.more.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.LayoutItemQrCodeAppListBinding;
import com.coveiot.android.leonardo.model.SelectedAppDataForQrCodePush;
import com.coveiot.android.leonardo.more.fragments.AppClickListener;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class QrCodeAppListAdapter extends RecyclerView.Adapter<AppListAdapterViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<SelectedAppDataForQrCodePush> f5076a;
    @NotNull
    public AppClickListener b;

    public QrCodeAppListAdapter(@NotNull List<SelectedAppDataForQrCodePush> appData, @NotNull AppClickListener appClickListener) {
        Intrinsics.checkNotNullParameter(appData, "appData");
        Intrinsics.checkNotNullParameter(appClickListener, "appClickListener");
        this.f5076a = appData;
        this.b = appClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5076a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull AppListAdapterViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().setAppData(this.f5076a.get(i));
        holder.getBinding().setClickListener(this.b);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public AppListAdapterViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutItemQrCodeAppListBinding binding = (LayoutItemQrCodeAppListBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.layout_item_qr_code_app_list, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new AppListAdapterViewHolder(binding);
    }
}
