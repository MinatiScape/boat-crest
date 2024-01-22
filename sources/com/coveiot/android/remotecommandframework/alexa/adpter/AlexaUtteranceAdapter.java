package com.coveiot.android.remotecommandframework.alexa.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.remotecommandframework.databinding.ListItemAlexaUttranceBinding;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes6.dex */
public final class AlexaUtteranceAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f5594a;
    @NotNull
    public List<String> b;

    /* loaded from: classes6.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TextView f5595a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull AlexaUtteranceAdapter alexaUtteranceAdapter, ListItemAlexaUttranceBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            TextView textView = binding.tvUtterance;
            Intrinsics.checkNotNullExpressionValue(textView, "binding.tvUtterance");
            this.f5595a = textView;
        }

        @NotNull
        public final TextView getTvUtterance() {
            return this.f5595a;
        }
    }

    public AlexaUtteranceAdapter(@NotNull Context context, @NotNull List<String> utterances) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(utterances, "utterances");
        this.f5594a = context;
        this.b = utterances;
    }

    @NotNull
    public final Context getContext() {
        return this.f5594a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    @NotNull
    public final List<String> getUtterances() {
        return this.b;
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f5594a = context;
    }

    public final void setUtterances(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.getTvUtterance().setText(this.b.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemAlexaUttranceBinding inflate = ListItemAlexaUttranceBinding.inflate(LayoutInflater.from(this.f5594a), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            Lay…          false\n        )");
        return new ViewHolder(this, inflate);
    }
}
