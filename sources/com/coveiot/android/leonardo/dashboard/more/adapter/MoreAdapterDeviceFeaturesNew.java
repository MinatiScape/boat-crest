package com.coveiot.android.leonardo.dashboard.more.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.SupportedDeviceFeatureUtilsNew;
import com.coveiot.android.theme.utils.ThemesUtils;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class MoreAdapterDeviceFeaturesNew extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4804a;
    @Nullable
    public ItemClickListener b;
    @NotNull
    public List<SupportedDeviceFeatureUtilsNew.Feature> c;

    /* loaded from: classes3.dex */
    public interface ItemClickListener {
        void onItemClick(@NotNull SupportedDeviceFeatureUtilsNew.Feature feature);
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4805a;
        @NotNull
        public final View b;
        @NotNull
        public final ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull MoreAdapterDeviceFeaturesNew moreAdapterDeviceFeaturesNew, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.settings_name);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.settings_name)");
            this.f4805a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.list_item_divider);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.list_item_divider)");
            this.b = findViewById2;
            View findViewById3 = view.findViewById(R.id.ivRightArrow);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.ivRightArrow)");
            this.c = (ImageView) findViewById3;
        }

        @NotNull
        public final ImageView getIvRightArrow() {
            return this.c;
        }

        @NotNull
        public final View getList_item_divider() {
            return this.b;
        }

        @NotNull
        public final TextView getTv_title() {
            return this.f4805a;
        }
    }

    /* loaded from: classes3.dex */
    public static final class a extends Lambda implements Function2<Integer, Integer, Unit> {
        public a() {
            super(2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Integer num, Integer num2) {
            invoke(num.intValue(), num2.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(int i, int i2) {
            ItemClickListener itemClickListener = MoreAdapterDeviceFeaturesNew.this.b;
            if (itemClickListener != null) {
                itemClickListener.onItemClick(MoreAdapterDeviceFeaturesNew.this.getSupportedFeatureList().get(i));
            }
        }
    }

    public MoreAdapterDeviceFeaturesNew(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4804a = context;
        this.c = new ArrayList();
    }

    public static final void b(Function2 event, RecyclerView.ViewHolder this_listen, View view) {
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this_listen, "$this_listen");
        event.invoke(Integer.valueOf(this_listen.getAbsoluteAdapterPosition()), Integer.valueOf(this_listen.getItemViewType()));
    }

    @NotNull
    public final Context getContext() {
        return this.f4804a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final List<SupportedDeviceFeatureUtilsNew.Feature> getSupportedFeatureList() {
        return this.c;
    }

    @NotNull
    public final <T extends RecyclerView.ViewHolder> T listen(@NotNull final T t, @NotNull final Function2<? super Integer, ? super Integer, Unit> event) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        t.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.more.adapter.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoreAdapterDeviceFeaturesNew.b(Function2.this, t, view);
            }
        });
        return t;
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.b = itemClickListener;
    }

    public final void setSupportedFeatureList(@NotNull List<SupportedDeviceFeatureUtilsNew.Feature> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        ThemesUtils themesUtils = ThemesUtils.INSTANCE;
        if (!themesUtils.isGuestUser(this.f4804a) && !themesUtils.isPairDeviceLater(this.f4804a)) {
            viewHolder.getIvRightArrow().setBackgroundResource(2131232383);
        } else {
            viewHolder.getTv_title().setTextColor(this.f4804a.getResources().getColor(R.color.color_666666));
            viewHolder.getIvRightArrow().setBackgroundResource(R.drawable.ic_right_arrow_actual_grey);
        }
        viewHolder.getTv_title().setText(this.c.get(i).getName());
        viewHolder.getList_item_divider().setVisibility(this.c.size() + (-1) != i ? 0 : 8);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_new_design_divider_withouticon, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return (ViewHolder) listen(new ViewHolder(this, view), new a());
    }
}
