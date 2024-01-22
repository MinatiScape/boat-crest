package com.coveiot.android.sportsnotification.filters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.databinding.SoccerFilterItemBinding;
import com.coveiot.android.sportsnotification.model.Filters;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AdapterSoccerFilter extends RecyclerView.Adapter<FilterViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5850a;
    @Nullable
    public ItemClickListener b;
    @Nullable
    public List<? extends Filters> c;
    public int d;

    /* loaded from: classes7.dex */
    public final class FilterViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final SoccerFilterItemBinding f5851a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public FilterViewHolder(@NotNull AdapterSoccerFilter adapterSoccerFilter, SoccerFilterItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5851a = binding;
        }

        @NotNull
        public final SoccerFilterItemBinding getBinding() {
            return this.f5851a;
        }
    }

    /* loaded from: classes7.dex */
    public interface ItemClickListener {
        void onItemClick(@Nullable Filters filters);
    }

    /* loaded from: classes7.dex */
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
            ItemClickListener itemClickListener = AdapterSoccerFilter.this.b;
            if (itemClickListener != null) {
                List<Filters> filters = AdapterSoccerFilter.this.getFilters();
                itemClickListener.onItemClick(filters != null ? filters.get(i) : null);
            }
        }
    }

    public AdapterSoccerFilter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f5850a = context;
    }

    public static final void c(Function2 event, RecyclerView.ViewHolder this_listen, View view) {
        Intrinsics.checkNotNullParameter(event, "$event");
        Intrinsics.checkNotNullParameter(this_listen, "$this_listen");
        event.invoke(Integer.valueOf(this_listen.getAdapterPosition()), Integer.valueOf(this_listen.getItemViewType()));
    }

    public static final void d(AdapterSoccerFilter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d = i;
        this$0.notifyDataSetChanged();
    }

    @NotNull
    public final Context getContext() {
        return this.f5850a;
    }

    @Nullable
    public final List<Filters> getFilters() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<? extends Filters> list = this.c;
        if (list != null) {
            Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
            Intrinsics.checkNotNull(valueOf);
            return valueOf.intValue();
        }
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    public final int getSelectedIndex() {
        return this.d;
    }

    @NotNull
    public final <T extends RecyclerView.ViewHolder> T listen(@NotNull final T t, @NotNull final Function2<? super Integer, ? super Integer, Unit> event) {
        Intrinsics.checkNotNullParameter(t, "<this>");
        Intrinsics.checkNotNullParameter(event, "event");
        t.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.filters.adapter.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterSoccerFilter.c(Function2.this, t, view);
            }
        });
        return t;
    }

    public final void setData(@Nullable List<? extends Filters> list) {
        this.c = list;
        notifyDataSetChanged();
    }

    public final void setFilters(@Nullable List<? extends Filters> list) {
        this.c = list;
    }

    public final void setItemClickListener(@Nullable ItemClickListener itemClickListener) {
        this.b = itemClickListener;
    }

    public final void setSelectedIndex(int i) {
        this.d = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull FilterViewHolder holder, final int i) {
        Filters filters;
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.sportsnotification.filters.adapter.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AdapterSoccerFilter.d(AdapterSoccerFilter.this, i, view);
            }
        });
        String str = null;
        if (this.d == i) {
            holder.getBinding().selectedDiv.setVisibility(0);
            holder.getBinding().filterName.setTextColor(this.f5850a.getColor(R.color.main_text_color));
            ItemClickListener itemClickListener = this.b;
            if (itemClickListener != null) {
                Intrinsics.checkNotNull(itemClickListener);
                List<? extends Filters> list = this.c;
                Filters filters2 = list != null ? list.get(i) : null;
                Intrinsics.checkNotNull(filters2);
                itemClickListener.onItemClick(filters2);
            }
        } else {
            holder.getBinding().selectedDiv.setVisibility(8);
            holder.getBinding().filterName.setTextColor(this.f5850a.getColor(R.color.secondary_text_color));
        }
        TextView textView = holder.getBinding().filterName;
        List<? extends Filters> list2 = this.c;
        if (list2 != null && (filters = list2.get(i)) != null) {
            str = filters.getName();
        }
        textView.setText(str);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public FilterViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SoccerFilterItemBinding binding = (SoccerFilterItemBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.soccer_filter_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return (FilterViewHolder) listen(new FilterViewHolder(this, binding), new a());
    }
}
