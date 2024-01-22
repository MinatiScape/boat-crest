package com.coveiot.android.leonardo.dashboard.permissioncheck.adapters;

import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ItemPermssionsRequiredBinding;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class PermissionsRequiredViewHolder extends RecyclerView.ViewHolder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final ItemPermssionsRequiredBinding f4807a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PermissionsRequiredViewHolder(@NotNull ItemPermssionsRequiredBinding binding) {
        super(binding.getRoot());
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.f4807a = binding;
    }

    @NotNull
    public final ItemPermssionsRequiredBinding getBinding() {
        return this.f4807a;
    }
}
