package com.coveiot.android.sleepenergyscore.energymeter.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sleepenergyscore.databinding.DrainGainTableRowLegendBinding;
import com.coveiot.android.sleepenergyscore.energymeter.model.DrainGainGridItem;
import com.coveiot.android.sleepenergyscore.utils.Utils;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class EnergyScoreLegendGridAdapter1 extends RecyclerView.Adapter<EnergyScoreLegendGridAdapterViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5700a;
    @NotNull
    public List<DrainGainGridItem> b;

    /* loaded from: classes6.dex */
    public final class EnergyScoreLegendGridAdapterViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final DrainGainTableRowLegendBinding f5701a;
        public final /* synthetic */ EnergyScoreLegendGridAdapter1 b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public EnergyScoreLegendGridAdapterViewHolder(@NotNull EnergyScoreLegendGridAdapter1 energyScoreLegendGridAdapter1, DrainGainTableRowLegendBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = energyScoreLegendGridAdapter1;
            this.f5701a = binding;
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull DrainGainGridItem drainGainGridItem) {
            String valueOf;
            Intrinsics.checkNotNullParameter(drainGainGridItem, "drainGainGridItem");
            DrainGainTableRowLegendBinding drainGainTableRowLegendBinding = this.f5701a;
            EnergyScoreLegendGridAdapter1 energyScoreLegendGridAdapter1 = this.b;
            String name = drainGainGridItem.getName();
            if (name == null || name.length() == 0) {
                return;
            }
            Drawable wrap = DrawableCompat.wrap(drainGainTableRowLegendBinding.imageView.getDrawable());
            Integer color = drainGainGridItem.getColor();
            Intrinsics.checkNotNull(color);
            DrawableCompat.setTint(wrap, color.intValue());
            String name2 = drainGainGridItem.getName();
            Intrinsics.checkNotNull(name2);
            if (StringsKt__StringsKt.contains$default((CharSequence) name2, (CharSequence) ":", false, 2, (Object) null)) {
                try {
                    String name3 = drainGainGridItem.getName();
                    List split$default = name3 != null ? StringsKt__StringsKt.split$default((CharSequence) name3, new String[]{":"}, false, 0, 6, (Object) null) : null;
                    if (split$default != null && split$default.size() >= 2 && split$default.get(0) != null && split$default.get(1) != null) {
                        Context context = energyScoreLegendGridAdapter1.getContext();
                        String str = (String) split$default.get(1);
                        Intrinsics.checkNotNull(str);
                        Integer valueOf2 = Integer.valueOf(str);
                        Intrinsics.checkNotNullExpressionValue(valueOf2, "valueOf(typeArr?.get(1)!!)");
                        int intValue = valueOf2.intValue();
                        Integer valueOf3 = Integer.valueOf((String) split$default.get(0));
                        Intrinsics.checkNotNullExpressionValue(valueOf3, "valueOf(typeArr[0])");
                        valueOf = Utils.getActivityModeNames(context, intValue, valueOf3.intValue());
                    } else {
                        valueOf = drainGainGridItem.getName();
                    }
                    Intrinsics.checkNotNull(valueOf);
                } catch (Exception e) {
                    e.printStackTrace();
                    valueOf = String.valueOf(drainGainGridItem.getName());
                }
            } else {
                valueOf = String.valueOf(drainGainGridItem.getName());
            }
            drainGainTableRowLegendBinding.textView.setText(valueOf);
        }
    }

    public EnergyScoreLegendGridAdapter1(@NotNull Context context, @NotNull List<DrainGainGridItem> mutableList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mutableList, "mutableList");
        this.f5700a = context;
        this.b = mutableList;
    }

    @NotNull
    public final Context getContext() {
        return this.f5700a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final List<DrainGainGridItem> getMutableList() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5700a = context;
    }

    public final void setMutableList(@NotNull List<DrainGainGridItem> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull EnergyScoreLegendGridAdapterViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public EnergyScoreLegendGridAdapterViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        DrainGainTableRowLegendBinding inflate = DrainGainTableRowLegendBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new EnergyScoreLegendGridAdapterViewHolder(this, inflate);
    }
}
