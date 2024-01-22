package com.coveiot.android.dashboard2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.adapter.DashboardTopFeaturesViewAdapter;
import com.coveiot.android.dashboard2.databinding.ItemTopFeaturesBinding;
import com.coveiot.coveaccess.smartgrid.model.GetSmartGridRes;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes4.dex */
public final class DashboardTopFeaturesViewAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f4218a;
    @NotNull
    public TopFeatureClickListener b;
    @NotNull
    public List<? extends GetSmartGridRes.GridItem> c;

    /* loaded from: classes4.dex */
    public interface TopFeatureClickListener {
        void topFeatureClicked(@NotNull GetSmartGridRes.GridItem gridItem);
    }

    /* loaded from: classes4.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ItemTopFeaturesBinding f4219a;
        public final /* synthetic */ DashboardTopFeaturesViewAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull DashboardTopFeaturesViewAdapter dashboardTopFeaturesViewAdapter, ItemTopFeaturesBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = dashboardTopFeaturesViewAdapter;
            this.f4219a = binding;
        }

        public static final void b(DashboardTopFeaturesViewAdapter this$0, GetSmartGridRes.GridItem gridItem, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(gridItem, "$gridItem");
            this$0.getListener().topFeatureClicked(gridItem);
        }

        public final void bind(@NotNull final GetSmartGridRes.GridItem gridItem) {
            Intrinsics.checkNotNullParameter(gridItem, "gridItem");
            ItemTopFeaturesBinding itemTopFeaturesBinding = this.f4219a;
            final DashboardTopFeaturesViewAdapter dashboardTopFeaturesViewAdapter = this.b;
            if (gridItem.getTitle() != null) {
                TextView textView = itemTopFeaturesBinding.tvTopFeaturesTitle;
                String title = gridItem.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "gridItem.title");
                textView.setText(CollectionsKt___CollectionsKt.joinToString$default(StringsKt__StringsKt.split$default((CharSequence) title, new String[]{HexStringBuilder.DEFAULT_SEPARATOR}, false, 0, 6, (Object) null), "\n", null, null, 0, null, null, 62, null));
            }
            itemTopFeaturesBinding.setFeatureData(gridItem);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.adapter.e
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DashboardTopFeaturesViewAdapter.ViewHolder.b(DashboardTopFeaturesViewAdapter.this, gridItem, view);
                }
            });
        }
    }

    public DashboardTopFeaturesViewAdapter(@NotNull Context context, @NotNull TopFeatureClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f4218a = context;
        this.b = listener;
        this.c = CollectionsKt__CollectionsKt.emptyList();
    }

    @NotNull
    public final Context getContext() {
        return this.f4218a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final TopFeatureClickListener getListener() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f4218a = context;
    }

    public final void setListener(@NotNull TopFeatureClickListener topFeatureClickListener) {
        Intrinsics.checkNotNullParameter(topFeatureClickListener, "<set-?>");
        this.b = topFeatureClickListener;
    }

    public final void setTopFeatureList(@NotNull List<? extends GetSmartGridRes.GridItem> items) {
        Intrinsics.checkNotNullParameter(items, "items");
        this.c = items;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ItemTopFeaturesBinding inflate = ItemTopFeaturesBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
