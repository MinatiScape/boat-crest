package com.mappls.sdk.category.adapters;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.mappls.sdk.category.R;
import com.mappls.sdk.category.fragment.CategoryResultFragment;
import com.mappls.sdk.category.model.PoiResult;
import com.mappls.sdk.category.model.SearchCategoryUIOption;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes11.dex */
public final class b extends RecyclerView.Adapter<C0610b> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<? extends PoiResult> f12539a;
    @NotNull
    public final SearchCategoryUIOption b;
    @NotNull
    public final a c;

    /* loaded from: classes11.dex */
    public interface a {
        void a(@NotNull PoiResult poiResult);
    }

    /* renamed from: com.mappls.sdk.category.adapters.b$b  reason: collision with other inner class name */
    /* loaded from: classes11.dex */
    public static final class C0610b extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f12540a;
        @NotNull
        public final TextView b;
        @NotNull
        public final TextView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C0610b(@NotNull View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.mappls_category_result_place_name);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.m…tegory_result_place_name)");
            this.f12540a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.mappls_category_text_view_distance);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.m…egory_text_view_distance)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.mappls_category_item_place_result_place_address);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.m…ace_result_place_address)");
            this.c = (TextView) findViewById3;
        }

        @NotNull
        public final TextView a() {
            return this.c;
        }

        @NotNull
        public final TextView b() {
            return this.b;
        }

        @NotNull
        public final TextView c() {
            return this.f12540a;
        }
    }

    public b(@NotNull ArrayList dataSet, @NotNull SearchCategoryUIOption searchCategoryUIOption, @NotNull CategoryResultFragment.c poiResultSelectedListener) {
        Intrinsics.checkNotNullParameter(dataSet, "dataSet");
        Intrinsics.checkNotNullParameter(searchCategoryUIOption, "searchCategoryUIOption");
        Intrinsics.checkNotNullParameter(poiResultSelectedListener, "poiResultSelectedListener");
        this.f12539a = dataSet;
        this.b = searchCategoryUIOption;
        this.c = poiResultSelectedListener;
    }

    public static final void b(b this$0, PoiResult data, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(data, "$data");
        this$0.c.a(data);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a */
    public final void onBindViewHolder(@NotNull C0610b viewHolder, final int i) {
        String str;
        DecimalFormat decimalFormat;
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        final PoiResult poiResult = this.f12539a.get(i);
        TextView a2 = viewHolder.a();
        Integer resultPlaceAddressTextColor = this.b.resultPlaceAddressTextColor();
        Intrinsics.checkNotNullExpressionValue(resultPlaceAddressTextColor, "searchCategoryUIOption.r…ltPlaceAddressTextColor()");
        a2.setTextColor(resultPlaceAddressTextColor.intValue());
        TextView c = viewHolder.c();
        Integer resultPlaceNameTextColor = this.b.resultPlaceNameTextColor();
        Intrinsics.checkNotNullExpressionValue(resultPlaceNameTextColor, "searchCategoryUIOption.resultPlaceNameTextColor()");
        c.setTextColor(resultPlaceNameTextColor.intValue());
        TextView b = viewHolder.b();
        Integer resultPlaceDistanceTextColor = this.b.resultPlaceDistanceTextColor();
        Intrinsics.checkNotNullExpressionValue(resultPlaceDistanceTextColor, "searchCategoryUIOption.r…tPlaceDistanceTextColor()");
        b.setTextColor(resultPlaceDistanceTextColor.intValue());
        viewHolder.c().setText(!TextUtils.isEmpty(poiResult.getPlaceName()) ? poiResult.getPlaceName() : "");
        viewHolder.a().setText(!TextUtils.isEmpty(poiResult.getPlaceAddress()) ? poiResult.getPlaceAddress() : "");
        Long distance = poiResult.getDistance();
        if ((distance == null ? 0L : distance.longValue()) > 0) {
            TextView b2 = viewHolder.b();
            long longValue = poiResult.getDistance().longValue();
            if (longValue >= 1000) {
                new DecimalFormat("#.##").setRoundingMode(RoundingMode.CEILING);
                str = decimalFormat.format(longValue / 1000.0d) + " km";
            } else {
                str = longValue + " m";
            }
            b2.setText(str);
        } else {
            viewHolder.b().setText("");
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.mappls.sdk.category.adapters.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                b.b(b.this, poiResult, i, view);
            }
        });
    }

    public final void a(@Nullable List<? extends PoiResult> list) {
        if (list != null) {
            this.f12539a = list;
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.f12539a.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final C0610b onCreateViewHolder(ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mappls_category_place_result, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new C0610b(view);
    }
}
