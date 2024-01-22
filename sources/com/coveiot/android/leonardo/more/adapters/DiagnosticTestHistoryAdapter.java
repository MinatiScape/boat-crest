package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ListItemDiagnosticHistoryBinding;
import com.coveiot.android.leonardo.model.DiagnosticTestHistoryModel;
import com.coveiot.android.leonardo.more.adapters.DiagnosticTestHistoryAdapter;
import com.coveiot.android.leonardo.more.listeners.DiagnosticHistoryDownloadListener;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DiagnosticTestHistoryAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final DiagnosticHistoryDownloadListener f5052a;
    @NotNull
    public List<DiagnosticTestHistoryModel> b;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemDiagnosticHistoryBinding f5053a;
        public final /* synthetic */ DiagnosticTestHistoryAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull DiagnosticTestHistoryAdapter diagnosticTestHistoryAdapter, ListItemDiagnosticHistoryBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = diagnosticTestHistoryAdapter;
            this.f5053a = binding;
        }

        public static final void b(DiagnosticTestHistoryAdapter this$0, DiagnosticTestHistoryModel diagnosticTest, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(diagnosticTest, "$diagnosticTest");
            this$0.getListener().downloadDiagnosticHistory(diagnosticTest.getTestDownloadUrl());
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final DiagnosticTestHistoryModel diagnosticTest) {
            Intrinsics.checkNotNullParameter(diagnosticTest, "diagnosticTest");
            ListItemDiagnosticHistoryBinding listItemDiagnosticHistoryBinding = this.f5053a;
            final DiagnosticTestHistoryAdapter diagnosticTestHistoryAdapter = this.b;
            listItemDiagnosticHistoryBinding.setDiagnosticTestHistoryData(diagnosticTest);
            this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.adapters.l
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    DiagnosticTestHistoryAdapter.ViewHolder.b(DiagnosticTestHistoryAdapter.this, diagnosticTest, view);
                }
            });
        }
    }

    public DiagnosticTestHistoryAdapter(@NotNull DiagnosticHistoryDownloadListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f5052a = listener;
        this.b = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @NotNull
    public final DiagnosticHistoryDownloadListener getListener() {
        return this.f5052a;
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setDiagnosticTestHistoryList(@NotNull List<DiagnosticTestHistoryModel> historyData) {
        Intrinsics.checkNotNullParameter(historyData, "historyData");
        List<DiagnosticTestHistoryModel> asMutableList = TypeIntrinsics.asMutableList(historyData);
        this.b = asMutableList;
        LogHelper.e("setDiagnosticTestHistoryList", String.valueOf(asMutableList));
        notifyDataSetChanged();
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
        ListItemDiagnosticHistoryBinding inflate = ListItemDiagnosticHistoryBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
