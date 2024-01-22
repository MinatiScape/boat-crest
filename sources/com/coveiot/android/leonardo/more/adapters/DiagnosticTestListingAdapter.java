package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.ListItemDiagnosticTestingBinding;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DiagnosticTestListingAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<DiagnosticTestModel> f5054a = new ArrayList();

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemDiagnosticTestingBinding f5055a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull DiagnosticTestListingAdapter diagnosticTestListingAdapter, ListItemDiagnosticTestingBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5055a = binding;
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull DiagnosticTestModel diagnosticTest) {
            Intrinsics.checkNotNullParameter(diagnosticTest, "diagnosticTest");
            ListItemDiagnosticTestingBinding listItemDiagnosticTestingBinding = this.f5055a;
            listItemDiagnosticTestingBinding.setDiagnosticData(diagnosticTest);
            DottedCircleProgressBarCustom dottedProgressbar = listItemDiagnosticTestingBinding.dottedProgressbar;
            Intrinsics.checkNotNullExpressionValue(dottedProgressbar, "dottedProgressbar");
            ViewUtilsKt.gone(dottedProgressbar);
            TextView tvDiagnosticTest = listItemDiagnosticTestingBinding.tvDiagnosticTest;
            Intrinsics.checkNotNullExpressionValue(tvDiagnosticTest, "tvDiagnosticTest");
            ViewUtilsKt.emptyDrawable(tvDiagnosticTest);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5054a.size();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setDiagnosticTestList(@NotNull List<DiagnosticTestModel> featureData) {
        Intrinsics.checkNotNullParameter(featureData, "featureData");
        this.f5054a = TypeIntrinsics.asMutableList(featureData);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.f5054a.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemDiagnosticTestingBinding inflate = ListItemDiagnosticTestingBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
