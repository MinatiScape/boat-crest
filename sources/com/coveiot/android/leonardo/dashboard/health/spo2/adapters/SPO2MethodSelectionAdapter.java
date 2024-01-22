package com.coveiot.android.leonardo.dashboard.health.spo2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.SPO2ListItem;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class SPO2MethodSelectionAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4741a;
    @NotNull
    public final List<SPO2ListItem> b;
    @Nullable
    public ItemClickListener c;

    /* loaded from: classes3.dex */
    public interface ItemClickListener {
        void onItemClick(@NotNull String str);
    }

    /* loaded from: classes3.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f4742a;
        @NotNull
        public final TextView b;
        @NotNull
        public final ImageView c;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull SPO2MethodSelectionAdapter sPO2MethodSelectionAdapter, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.spo2_mode_tv);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.spo2_mode_tv)");
            this.f4742a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.spo2_mode_desc_tv);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.spo2_mode_desc_tv)");
            this.b = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.spo2_mode_img_v);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.spo2_mode_img_v)");
            this.c = (ImageView) findViewById3;
        }

        @NotNull
        public final TextView getSpo2ModeDescTv() {
            return this.b;
        }

        @NotNull
        public final ImageView getSpo2ModeImgV() {
            return this.c;
        }

        @NotNull
        public final TextView getSpo2ModeTitleTv() {
            return this.f4742a;
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
            ItemClickListener itemClickListener = SPO2MethodSelectionAdapter.this.c;
            if (itemClickListener != null) {
                itemClickListener.onItemClick(((SPO2ListItem) SPO2MethodSelectionAdapter.this.b.get(i)).getName());
            }
        }
    }

    public SPO2MethodSelectionAdapter(@NotNull Context context, @NotNull List<SPO2ListItem> items) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        this.f4741a = context;
        this.b = items;
    }

    public static final void b(Function2 event, RecyclerView.ViewHolder this_listen, View view) {
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this_listen, "$this_listen");
        event.invoke(Integer.valueOf(this_listen.getAdapterPosition()), Integer.valueOf(this_listen.getItemViewType()));
    }

    @NotNull
    public final Context getContext() {
        return this.f4741a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final <T extends RecyclerView.ViewHolder> T listen(@NotNull final T t, @NotNull final Function2<? super Integer, ? super Integer, Unit> event) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        t.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.adapters.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SPO2MethodSelectionAdapter.b(Function2.this, t, view);
            }
        });
        return t;
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.c = itemClickListener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        viewHolder.getSpo2ModeTitleTv().setText(this.b.get(i).getName());
        viewHolder.getSpo2ModeDescTv().setText(this.b.get(i).getDescription());
        viewHolder.getSpo2ModeImgV().setImageResource(this.b.get(i).getDrawableResId());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkNotNullParameter(viewGroup, "viewGroup");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_sp02_method_selection, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return (ViewHolder) listen(new ViewHolder(this, view), new a());
    }
}
