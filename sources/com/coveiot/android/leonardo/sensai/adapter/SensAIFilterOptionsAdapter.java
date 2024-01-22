package com.coveiot.android.leonardo.sensai.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.SensAiFilterOptionListItemBinding;
import com.coveiot.android.leonardo.sensai.model.SensAIFilterOptions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class SensAIFilterOptionsAdapter extends RecyclerView.Adapter<OptionsHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5373a;
    @NotNull
    public final List<SensAIFilterOptions> b;
    @Nullable
    public final List<String> c;
    public FilterSelectionListener listener;

    /* loaded from: classes5.dex */
    public interface FilterSelectionListener {
        void onFilterOption(@NotNull String str, boolean z);
    }

    /* loaded from: classes5.dex */
    public final class OptionsHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final SensAiFilterOptionListItemBinding f5374a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public OptionsHolder(@NotNull SensAIFilterOptionsAdapter sensAIFilterOptionsAdapter, SensAiFilterOptionListItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5374a = binding;
        }

        @NotNull
        public final SensAiFilterOptionListItemBinding getBinding() {
            return this.f5374a;
        }
    }

    public /* synthetic */ SensAIFilterOptionsAdapter(Context context, List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, list, (i & 4) != 0 ? null : list2);
    }

    public static final void b(SensAIFilterOptionsAdapter this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.listener != null) {
            FilterSelectionListener listener = this$0.getListener();
            String name = this$0.b.get(i).getName();
            Intrinsics.checkNotNullExpressionValue(name, "filterOptions[position].name");
            listener.onFilterOption(name, z);
        }
    }

    @NotNull
    public final Context getContext() {
        return this.f5373a;
    }

    @NotNull
    public final List<SensAIFilterOptions> getFilterOptions() {
        return this.b;
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

    @Nullable
    public final List<String> getSelectedOptionIds() {
        return this.c;
    }

    public final void setListener(@NotNull FilterSelectionListener filterSelectionListener) {
        Intrinsics.checkNotNullParameter(filterSelectionListener, "<set-?>");
        this.listener = filterSelectionListener;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SensAIFilterOptionsAdapter(@NotNull Context context, @NotNull List<? extends SensAIFilterOptions> filterOptions, @Nullable List<String> list) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filterOptions, "filterOptions");
        this.f5373a = context;
        this.b = filterOptions;
        this.c = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull OptionsHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getBinding().cbFilter.setText(this.b.get(i).getName());
        if (this.c != null) {
            holder.getBinding().cbFilter.setChecked(this.c.contains(this.b.get(i).getName()));
        }
        holder.getBinding().cbFilter.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.sensai.adapter.e
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SensAIFilterOptionsAdapter.b(SensAIFilterOptionsAdapter.this, i, compoundButton, z);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public OptionsHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        SensAiFilterOptionListItemBinding inflate = SensAiFilterOptionListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f….context), parent, false)");
        return new OptionsHolder(this, inflate);
    }
}
