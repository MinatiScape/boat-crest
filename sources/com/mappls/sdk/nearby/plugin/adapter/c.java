package com.mappls.sdk.nearby.plugin.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.nearby.plugin.CategoryCode;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultCategoryAdapterBinding;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class c extends RecyclerView.Adapter<a> {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public List<? extends CategoryCode> f13058a;

    /* loaded from: classes10.dex */
    public static final class a extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final MapplsNearbyResultCategoryAdapterBinding f13059a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(@NotNull MapplsNearbyResultCategoryAdapterBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f13059a = binding;
        }

        @NotNull
        public final MapplsNearbyResultCategoryAdapterBinding a() {
            return this.f13059a;
        }
    }

    public final void a(@NotNull List<? extends CategoryCode> selectedCategories) {
        Intrinsics.checkNotNullParameter(selectedCategories, "selectedCategories");
        this.f13058a = selectedCategories;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<? extends CategoryCode> list = this.f13058a;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(a aVar, int i) {
        CategoryCode categoryCode;
        a holder = aVar;
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView textView = holder.a().mapplsNearbyCategoryTv;
        List<? extends CategoryCode> list = this.f13058a;
        textView.setText((list == null || (categoryCode = list.get(i)) == null) ? null : categoryCode.getCategory());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final a onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.mappls_nearby_result_category_adapter, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…y_adapter, parent, false)");
        return new a((MapplsNearbyResultCategoryAdapterBinding) inflate);
    }
}
