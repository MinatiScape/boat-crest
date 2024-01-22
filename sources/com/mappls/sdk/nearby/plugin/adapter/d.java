package com.mappls.sdk.nearby.plugin.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.nearby.plugin.R;
import com.mappls.sdk.nearby.plugin.databinding.MapplsNearbyResultListAdapterBinding;
import com.mappls.sdk.nearby.plugin.fragment.b;
import com.mappls.sdk.nearby.plugin.view.NearbyResultViewOption;
import com.mappls.sdk.services.api.nearby.model.NearbyAtlasResult;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes10.dex */
public final class d extends RecyclerView.Adapter<b> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final NearbyResultViewOption f13060a;
    @Nullable
    public List<? extends NearbyAtlasResult> b;
    @Nullable
    public a c;

    /* loaded from: classes10.dex */
    public interface a {
        void a(@NotNull NearbyAtlasResult nearbyAtlasResult);
    }

    /* loaded from: classes10.dex */
    public final class b extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final MapplsNearbyResultListAdapterBinding f13061a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(@NotNull MapplsNearbyResultListAdapterBinding mBinding) {
            super(mBinding.getRoot());
            Intrinsics.checkNotNullParameter(mBinding, "mBinding");
            this.f13061a = mBinding;
        }

        @NotNull
        public final MapplsNearbyResultListAdapterBinding a() {
            return this.f13061a;
        }
    }

    public d(@NotNull NearbyResultViewOption viewOption) {
        Intrinsics.checkNotNullParameter(viewOption, "viewOption");
        this.f13060a = viewOption;
    }

    public static final void b(d this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        a aVar = this$0.c;
        if (aVar != null) {
            List<? extends NearbyAtlasResult> list = this$0.b;
            NearbyAtlasResult nearbyAtlasResult = list != null ? list.get(i) : null;
            Intrinsics.checkNotNull(nearbyAtlasResult);
            aVar.a(nearbyAtlasResult);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a */
    public final void onBindViewHolder(@NotNull b holder, final int i) {
        DecimalFormat decimalFormat;
        String str;
        NearbyAtlasResult nearbyAtlasResult;
        NearbyAtlasResult nearbyAtlasResult2;
        NearbyAtlasResult nearbyAtlasResult3;
        Intrinsics.checkNotNullParameter(holder, "holder");
        TextView textView = holder.a().mapplsNearbyPlaceNameTv;
        List<? extends NearbyAtlasResult> list = this.b;
        Long l = null;
        textView.setText((list == null || (nearbyAtlasResult3 = list.get(i)) == null) ? null : nearbyAtlasResult3.placeName);
        TextView textView2 = holder.a().mapplsNearbyAddressTv;
        List<? extends NearbyAtlasResult> list2 = this.b;
        textView2.setText((list2 == null || (nearbyAtlasResult2 = list2.get(i)) == null) ? null : nearbyAtlasResult2.placeAddress);
        TextView textView3 = holder.a().mapplsNearbyDistanceTv;
        List<? extends NearbyAtlasResult> list3 = this.b;
        if (list3 != null && (nearbyAtlasResult = list3.get(i)) != null) {
            l = nearbyAtlasResult.distance;
        }
        if (l == null) {
            str = "";
        } else {
            new DecimalFormat("#.##").setRoundingMode(RoundingMode.CEILING);
            str = "Approx. " + decimalFormat.format(l.longValue() / 1000.0d) + " km away";
        }
        textView3.setText(str);
        TextView textView4 = holder.a().mapplsNearbyPlaceNameTv;
        Integer placeNameTextColor = this.f13060a.placeNameTextColor();
        Intrinsics.checkNotNullExpressionValue(placeNameTextColor, "viewOption.placeNameTextColor()");
        textView4.setTextColor(placeNameTextColor.intValue());
        TextView textView5 = holder.a().mapplsNearbyDistanceTv;
        Integer distanceTextColor = this.f13060a.distanceTextColor();
        Intrinsics.checkNotNullExpressionValue(distanceTextColor, "viewOption.distanceTextColor()");
        textView5.setTextColor(distanceTextColor.intValue());
        TextView textView6 = holder.a().mapplsNearbyAddressTv;
        Integer addressTextColor = this.f13060a.addressTextColor();
        Intrinsics.checkNotNullExpressionValue(addressTextColor, "viewOption.addressTextColor()");
        textView6.setTextColor(addressTextColor.intValue());
        View view = holder.a().mapplsNearbyResultSeperator;
        Integer listSeperatorColor = this.f13060a.listSeperatorColor();
        Intrinsics.checkNotNullExpressionValue(listSeperatorColor, "viewOption.listSeperatorColor()");
        view.setBackgroundColor(listSeperatorColor.intValue());
        holder.a().getRoot().setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.nearby.plugin.adapter.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                d.b(d.this, i, view2);
            }
        });
    }

    public final void a(@NotNull b.C0653b callback) {
        Intrinsics.checkNotNullParameter(callback, "callback");
        this.c = callback;
    }

    public final void a(@NotNull ArrayList list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.b = list;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        List<? extends NearbyAtlasResult> list = this.b;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final b onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.mappls_nearby_result_list_adapter, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…t_adapter, parent, false)");
        return new b((MapplsNearbyResultListAdapterBinding) inflate);
    }
}
