package com.coveiot.android.leonardo.dashboard.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.TextAppearanceSpan;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.model.InsightsStepsDataModel;
import com.coveiot.android.theme.databinding.ListItemInsightsLayoutBinding;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.covepreferences.UserDataManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public final class VitalStepsInsightsAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4711a;
    @NotNull
    public List<InsightsStepsDataModel> b;

    /* loaded from: classes2.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemInsightsLayoutBinding f4712a;
        public final /* synthetic */ VitalStepsInsightsAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull VitalStepsInsightsAdapter vitalStepsInsightsAdapter, ListItemInsightsLayoutBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = vitalStepsInsightsAdapter;
            this.f4712a = binding;
        }

        public final void a(ListItemInsightsLayoutBinding listItemInsightsLayoutBinding, InsightsStepsDataModel insightsStepsDataModel) {
            if (insightsStepsDataModel.isValueDecreased()) {
                listItemInsightsLayoutBinding.ivInsightDecreaseIncrease.setBackgroundResource(R.drawable.ic_small_red_arrow_down);
            } else {
                listItemInsightsLayoutBinding.ivInsightDecreaseIncrease.setBackgroundResource(R.drawable.ic_small_green_arrow_up);
            }
        }

        public final SpannableStringBuilder b(String str, String str2) {
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#ffffff")), StringsKt__StringsKt.indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null) + str2.length(), 33);
            spannableString.setSpan(new TextAppearanceSpan(this.b.getContext(), R.style.bold), StringsKt__StringsKt.indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null), StringsKt__StringsKt.indexOf$default((CharSequence) str, str2, 0, false, 6, (Object) null) + str2.length(), 18);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) spannableString);
            return spannableStringBuilder;
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull InsightsStepsDataModel insight) {
            Intrinsics.checkNotNullParameter(insight, "insight");
            ListItemInsightsLayoutBinding listItemInsightsLayoutBinding = this.f4712a;
            VitalStepsInsightsAdapter vitalStepsInsightsAdapter = this.b;
            int absoluteAdapterPosition = getAbsoluteAdapterPosition();
            if (absoluteAdapterPosition == 0) {
                listItemInsightsLayoutBinding.clInsight.setBackgroundResource(R.drawable.rounded_top_left_yellow_border_square_background);
                if (insight.isStepData()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(insight.getSubValue());
                    sb.append(' ');
                    Boolean isDistanceUnitInMile = UserDataManager.getInstance(vitalStepsInsightsAdapter.getContext()).isDistanceUnitInMile();
                    Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
                    sb.append(isDistanceUnitInMile.booleanValue() ? vitalStepsInsightsAdapter.getContext().getString(R.string.mil) : vitalStepsInsightsAdapter.getContext().getString(R.string.kms));
                    String sb2 = sb.toString();
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String format = String.format(vitalStepsInsightsAdapter.getContext().getString(R.string.you_covered) + " %s " + ThemesUtils.INSTANCE.getYestWeekMonthText(insight.getSelectedType(), vitalStepsInsightsAdapter.getContext()), Arrays.copyOf(new Object[]{sb2}, 1));
                    Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                    listItemInsightsLayoutBinding.tvInsightInfo.setText(b(format, sb2));
                }
            } else if (absoluteAdapterPosition == 1) {
                listItemInsightsLayoutBinding.clInsight.setBackgroundResource(R.drawable.rounded_top_left_blue_border_square_background);
            } else if (absoluteAdapterPosition == 2) {
                listItemInsightsLayoutBinding.clInsight.setBackgroundResource(R.drawable.rounded_top_left_red_border_square_background);
            } else if (absoluteAdapterPosition == 3) {
                listItemInsightsLayoutBinding.clInsight.setBackgroundResource(R.drawable.rounded_top_left_red_border_square_background);
            }
            if (getAbsoluteAdapterPosition() != 3) {
                if (getAbsoluteAdapterPosition() == 0) {
                    listItemInsightsLayoutBinding.tvInsightValue.setText(String.valueOf(Math.abs(insight.getMainValue())));
                } else {
                    listItemInsightsLayoutBinding.tvInsightValue.setText(String.valueOf(Math.abs((int) insight.getMainValue())));
                }
            } else {
                if (!(insight.getMainValue() == 0.0d)) {
                    TextView textView = listItemInsightsLayoutBinding.tvInsightValue;
                    textView.setText((((int) insight.getMainValue()) / 60) + "hr : " + (((int) insight.getMainValue()) % 60) + 'm');
                } else {
                    listItemInsightsLayoutBinding.tvInsightValue.setText(vitalStepsInsightsAdapter.getContext().getString(R.string._00hr_00m));
                }
            }
            if (insight.getSubValueInfo().length() > 0) {
                listItemInsightsLayoutBinding.tvInsightHeader.setTextSize(2, 11.0f);
                String subValue = insight.getSubValue();
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String format2 = String.format("%s " + insight.getSubValueInfo(), Arrays.copyOf(new Object[]{subValue}, 1));
                Intrinsics.checkNotNullExpressionValue(format2, "format(format, *args)");
                listItemInsightsLayoutBinding.tvInsightHeader.setText(b(format2, subValue));
            } else {
                listItemInsightsLayoutBinding.tvInsightHeader.setText(insight.getHeader());
            }
            if (insight.isStepData()) {
                if (getAbsoluteAdapterPosition() != 0) {
                    listItemInsightsLayoutBinding.tvInsightInfo.setText(insight.getInfo());
                }
            } else {
                listItemInsightsLayoutBinding.tvInsightInfo.setText(insight.getInfo());
            }
            if (!(insight.getMainValue() == 0.0d) && getAbsoluteAdapterPosition() == 0) {
                a(listItemInsightsLayoutBinding, insight);
            } else if (((int) insight.getMainValue()) == 0 || getAbsoluteAdapterPosition() == 3) {
            } else {
                a(listItemInsightsLayoutBinding, insight);
            }
        }
    }

    public VitalStepsInsightsAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4711a = context;
        this.b = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f4711a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    @SuppressLint({"NotifyDataSetChanged"})
    public final void setInsightList(@NotNull List<InsightsStepsDataModel> insightDataList) {
        Intrinsics.checkNotNullParameter(insightDataList, "insightDataList");
        this.b = TypeIntrinsics.asMutableList(insightDataList);
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
        ListItemInsightsLayoutBinding inflate = ListItemInsightsLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n            Lay…, parent, false\n        )");
        return new ViewHolder(this, inflate);
    }
}
