package com.coveiot.android.leonardo.more.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class IntervalAdapterNew extends RecyclerView.Adapter<IntervalViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Integer[] f5060a;
    @NotNull
    public Context b;
    @Nullable
    public Integer c;
    public IntervalSelectionListener listner;

    /* loaded from: classes5.dex */
    public interface IntervalSelectionListener {
        void onIntervalSelected(int i);
    }

    /* loaded from: classes5.dex */
    public final class IntervalViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5061a;
        public final /* synthetic */ IntervalAdapterNew b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public IntervalViewHolder(@NotNull IntervalAdapterNew intervalAdapterNew, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            this.b = intervalAdapterNew;
            View findViewById = itemView.findViewById(R.id.tv_interval);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById<TextView>(R.id.tv_interval)");
            this.f5061a = (TextView) findViewById;
        }

        public final void bindView(int i) {
            TextView textView = this.f5061a;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format("%d Min", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
            textView.setText(format);
        }

        @NotNull
        public final TextView getTv_interval() {
            return this.f5061a;
        }

        public final void selected(boolean z) {
            if (z) {
                this.f5061a.setBackgroundResource(R.drawable.interval_bg_selected);
                this.f5061a.setTextAppearance(R.style.semi_bold);
                this.f5061a.setTextColor(this.b.getContext().getResources().getColor(R.color.main_text_color));
                return;
            }
            this.f5061a.setBackgroundResource(R.drawable.interval_bg_unselected);
            this.f5061a.setTextAppearance(R.style.regular);
            this.f5061a.setTextColor(this.b.getContext().getResources().getColor(R.color.secondary_text_color));
        }
    }

    public IntervalAdapterNew(@NotNull Integer[] intervalList, @NotNull Context context) {
        Intrinsics.checkNotNullParameter(intervalList, "intervalList");
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5060a = intervalList;
        this.b = context;
    }

    public static final void b(IntervalAdapterNew this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.c = this$0.f5060a[i];
        this$0.notifyDataSetChanged();
        IntervalSelectionListener listner = this$0.getListner();
        Integer num = this$0.c;
        Intrinsics.checkNotNull(num);
        listner.onIntervalSelected(num.intValue());
    }

    @NotNull
    public final Context getContext() {
        return this.b;
    }

    @NotNull
    public final Integer[] getIntervalList() {
        return this.f5060a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5060a.length;
    }

    @NotNull
    public final IntervalSelectionListener getListner() {
        IntervalSelectionListener intervalSelectionListener = this.listner;
        if (intervalSelectionListener != null) {
            return intervalSelectionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException("listner");
        return null;
    }

    @Nullable
    public final Integer getSelectedInterval() {
        return this.c;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.b = context;
    }

    public final void setListner(@NotNull IntervalSelectionListener intervalSelectionListener) {
        Intrinsics.checkNotNullParameter(intervalSelectionListener, "<set-?>");
        this.listner = intervalSelectionListener;
    }

    public final void setSelectedInterval(@Nullable Integer num) {
        this.c = num;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull IntervalViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bindView(this.f5060a[i].intValue());
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntervalAdapterNew.b(IntervalAdapterNew.this, i, view);
            }
        });
        Integer num = this.c;
        holder.selected(num != null && num.intValue() == this.f5060a[i].intValue());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public IntervalViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.inteval_item_new, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…_item_new, parent, false)");
        return new IntervalViewHolder(this, inflate);
    }
}
