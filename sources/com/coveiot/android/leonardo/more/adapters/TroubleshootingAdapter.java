package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ListItemNewDesignTitleBinding;
import com.coveiot.android.leonardo.model.TroubleshootingModel;
import com.coveiot.android.leonardo.more.adapters.TroubleshootingAdapter;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class TroubleshootingAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5091a;
    @NotNull
    public final List<TroubleshootingModel> b;
    @Nullable
    public OnItemClickListener c;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onItemClick(@NotNull TroubleshootingModel troubleshootingModel);
    }

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemNewDesignTitleBinding f5092a;
        public final /* synthetic */ TroubleshootingAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull TroubleshootingAdapter troubleshootingAdapter, ListItemNewDesignTitleBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = troubleshootingAdapter;
            this.f5092a = binding;
        }

        public static final void b(TroubleshootingAdapter this$0, TroubleshootingModel troubleshootingModel, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(troubleshootingModel, "$troubleshootingModel");
            OnItemClickListener onItemClickListener = this$0.c;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(troubleshootingModel);
            }
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final TroubleshootingModel troubleshootingModel) {
            Intrinsics.checkNotNullParameter(troubleshootingModel, "troubleshootingModel");
            ListItemNewDesignTitleBinding listItemNewDesignTitleBinding = this.f5092a;
            final TroubleshootingAdapter troubleshootingAdapter = this.b;
            listItemNewDesignTitleBinding.troubleshootTitle.setText(troubleshootingModel.getName());
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.l0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TroubleshootingAdapter.ViewHolder.b(TroubleshootingAdapter.this, troubleshootingModel, view);
                }
            });
        }
    }

    public TroubleshootingAdapter(@NotNull Context context, @NotNull List<TroubleshootingModel> items) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(items, "items");
        this.f5091a = context;
        this.b = items;
    }

    @NotNull
    public final Context getContext() {
        return this.f5091a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.c = listener;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemNewDesignTitleBinding inflate = ListItemNewDesignTitleBinding.inflate(LayoutInflater.from(this.f5091a), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…(context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
