package com.coveiot.android.leonardo.dashboard.permissioncheck.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ItemPermssionsRequiredBinding;
import com.coveiot.android.leonardo.dashboard.model.PermissionsRequiredData;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class PermissionsRequiredAdapter extends RecyclerView.Adapter<PermissionsRequiredViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ArrayList<PermissionsRequiredData> f4806a;

    public PermissionsRequiredAdapter(@NotNull ArrayList<PermissionsRequiredData> pendingPermissions) {
        Intrinsics.checkNotNullParameter(pendingPermissions, "pendingPermissions");
        this.f4806a = pendingPermissions;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f4806a.size();
    }

    @NotNull
    public final ArrayList<PermissionsRequiredData> getPendingPermissions() {
        return this.f4806a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull PermissionsRequiredViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().textviewPermissionsTitle.setText(this.f4806a.get(i).getTitle());
        holder.getBinding().textviewPermissionsDescription.setText(this.f4806a.get(i).getDescription());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public PermissionsRequiredViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemPermssionsRequiredBinding binding = (ItemPermssionsRequiredBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_permssions_required, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new PermissionsRequiredViewHolder(binding);
    }
}
