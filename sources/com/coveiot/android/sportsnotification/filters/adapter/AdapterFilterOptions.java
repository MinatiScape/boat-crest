package com.coveiot.android.sportsnotification.filters.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.sportsnotification.R;
import com.coveiot.android.sportsnotification.databinding.FilterOptionListItemBinding;
import com.coveiot.android.sportsnotification.model.Options;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class AdapterFilterOptions extends RecyclerView.Adapter<OptionsHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5848a;
    @NotNull
    public final List<Options> b;
    @Nullable
    public final List<Integer> c;
    public FilterSelectionListener listener;

    /* loaded from: classes7.dex */
    public interface FilterSelectionListener {
        void onFilterOption(int i, boolean z);
    }

    /* loaded from: classes7.dex */
    public final class OptionsHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final FilterOptionListItemBinding f5849a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OptionsHolder(@NotNull AdapterFilterOptions adapterFilterOptions, FilterOptionListItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5849a = binding;
        }

        @NotNull
        public final FilterOptionListItemBinding getBinding() {
            return this.f5849a;
        }
    }

    public /* synthetic */ AdapterFilterOptions(Context context, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i & 4) != 0 ? null : list2);
    }

    public static final void b(AdapterFilterOptions this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.listener != null) {
            FilterSelectionListener listener = this$0.getListener();
            Integer optionId = this$0.b.get(i).getOptionId();
            Intrinsics.checkNotNullExpressionValue(optionId, "options.get(position).optionId");
            listener.onFilterOption(optionId.intValue(), z);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5848a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final FilterSelectionListener getListener() {
        FilterSelectionListener filterSelectionListener = this.listener;
        if (filterSelectionListener != null) {
            return filterSelectionListener;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        return null;
    }

    @NotNull
    public final List<Options> getOptions() {
        return this.b;
    }

    @Nullable
    public final List<Integer> getSelectedOptionIds() {
        return this.c;
    }

    public final void setListener(@NotNull FilterSelectionListener filterSelectionListener) {
        Intrinsics.checkNotNullParameter(filterSelectionListener, "<set-?>");
        this.listener = filterSelectionListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public AdapterFilterOptions(@NotNull Context context, @NotNull List<? extends Options> options, @Nullable List<Integer> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(options, "options");
        this.f5848a = context;
        this.b = options;
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull OptionsHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().cbFilter.setText(this.b.get(i).getDisplayName());
        if (this.c != null) {
            holder.getBinding().cbFilter.setChecked(this.c.contains(this.b.get(i).getOptionId()));
        }
        holder.getBinding().cbFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.sportsnotification.filters.adapter.a
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AdapterFilterOptions.b(AdapterFilterOptions.this, i, compoundButton, z);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public OptionsHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        FilterOptionListItemBinding binding = (FilterOptionListItemBinding) DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.filter_option_list_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(binding, "binding");
        return new OptionsHolder(this, binding);
    }
}
