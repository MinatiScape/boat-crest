package com.coveiot.android.leonardo.more.adapters;

import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.LayoutItemQrCodeAppListBinding;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AppListAdapterViewHolder extends RecyclerView.ViewHolder {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final LayoutItemQrCodeAppListBinding f5043a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppListAdapterViewHolder(@NotNull LayoutItemQrCodeAppListBinding binding) {
        super(binding.getRoot());
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.f5043a = binding;
    }

    @NotNull
    public final LayoutItemQrCodeAppListBinding getBinding() {
        return this.f5043a;
    }
}
