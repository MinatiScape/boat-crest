package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.boat.databinding.ListItemDiagnosticTestingBinding;
import com.coveiot.android.leonardo.model.DiagnosticTestModel;
import com.coveiot.android.leonardo.more.models.TestingStatus;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.android.theme.compundview.DottedCircleProgressBarCustom;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class DiagnosticTestingAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<DiagnosticTestModel> f5056a = new ArrayList();

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemDiagnosticTestingBinding f5057a;

        /* loaded from: classes5.dex */
        public /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[TestingStatus.values().length];
                try {
                    iArr[TestingStatus.PASSED.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[TestingStatus.FAILED.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[TestingStatus.IN_PROGRESS.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull DiagnosticTestingAdapter diagnosticTestingAdapter, ListItemDiagnosticTestingBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.f5057a = binding;
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull DiagnosticTestModel diagnosticTest) {
            CardView cardView;
            int i;
            Intrinsics.checkNotNullParameter(diagnosticTest, "diagnosticTest");
            ListItemDiagnosticTestingBinding listItemDiagnosticTestingBinding = this.f5057a;
            listItemDiagnosticTestingBinding.setDiagnosticData(diagnosticTest);
            DottedCircleProgressBarCustom dottedProgressbar = listItemDiagnosticTestingBinding.dottedProgressbar;
            Intrinsics.checkNotNullExpressionValue(dottedProgressbar, "dottedProgressbar");
            ViewUtilsKt.gone(dottedProgressbar);
            TextView tvDiagnosticTest = listItemDiagnosticTestingBinding.tvDiagnosticTest;
            Intrinsics.checkNotNullExpressionValue(tvDiagnosticTest, "tvDiagnosticTest");
            ViewUtilsKt.emptyDrawable(tvDiagnosticTest);
            if (diagnosticTest.getShow()) {
                cardView = listItemDiagnosticTestingBinding.cvVibrationTest;
                i = 0;
            } else {
                cardView = listItemDiagnosticTestingBinding.cvVibrationTest;
                i = 8;
            }
            cardView.setVisibility(i);
            int i2 = WhenMappings.$EnumSwitchMapping$0[diagnosticTest.getTestStatus().ordinal()];
            if (i2 == 1) {
                TextView tvDiagnosticTest2 = listItemDiagnosticTestingBinding.tvDiagnosticTest;
                Intrinsics.checkNotNullExpressionValue(tvDiagnosticTest2, "tvDiagnosticTest");
                ViewUtilsKt.drawableEnd(tvDiagnosticTest2, 2131232023);
            } else if (i2 == 2) {
                TextView tvDiagnosticTest3 = listItemDiagnosticTestingBinding.tvDiagnosticTest;
                Intrinsics.checkNotNullExpressionValue(tvDiagnosticTest3, "tvDiagnosticTest");
                ViewUtilsKt.drawableEnd(tvDiagnosticTest3, R.drawable.ic_diagnostic_failed);
            } else if (i2 != 3) {
            } else {
                DottedCircleProgressBarCustom dottedProgressbar2 = listItemDiagnosticTestingBinding.dottedProgressbar;
                Intrinsics.checkNotNullExpressionValue(dottedProgressbar2, "dottedProgressbar");
                ViewUtilsKt.visible(dottedProgressbar2);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f5056a.size();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setDiagnosticTestList(@NotNull List<DiagnosticTestModel> featureData) {
        Intrinsics.checkNotNullParameter(featureData, "featureData");
        this.f5056a = TypeIntrinsics.asMutableList(featureData);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.f5056a.get(i));
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
