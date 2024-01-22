package com.coveiot.android.weeklyreport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.databinding.ListItemImageTitleSubtitleBinding;
import com.coveiot.android.theme.utils.ThemesUtils;
import com.coveiot.android.weeklyreport.R;
import com.coveiot.android.weeklyreport.adapter.WeeklyFitnessReportAdapter;
import com.coveiot.coveaccess.weeklyreport.response.FitnessReportRes;
import com.coveiot.utils.utility.UtilConstants;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes8.dex */
public final class WeeklyFitnessReportAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f6241a;
    @NotNull
    public final FitnessReportClickListener b;
    @NotNull
    public List<? extends FitnessReportRes.FitnessReportItem> c;

    /* loaded from: classes8.dex */
    public interface FitnessReportClickListener {
        void reportClick(@NotNull FitnessReportRes.FitnessReportItem fitnessReportItem);
    }

    /* loaded from: classes8.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final ListItemImageTitleSubtitleBinding f6242a;
        public final /* synthetic */ WeeklyFitnessReportAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull WeeklyFitnessReportAdapter weeklyFitnessReportAdapter, ListItemImageTitleSubtitleBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = weeklyFitnessReportAdapter;
            this.f6242a = binding;
        }

        public static final void b(WeeklyFitnessReportAdapter this$0, FitnessReportRes.FitnessReportItem reportData, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(reportData, "$reportData");
            this$0.getListener().reportClick(reportData);
        }

        public final void bind(@NotNull final FitnessReportRes.FitnessReportItem reportData) {
            Intrinsics.checkNotNullParameter(reportData, "reportData");
            ListItemImageTitleSubtitleBinding listItemImageTitleSubtitleBinding = this.f6242a;
            final WeeklyFitnessReportAdapter weeklyFitnessReportAdapter = this.b;
            listItemImageTitleSubtitleBinding.settingsIcon.setImageResource(R.drawable.iv_report_icon);
            listItemImageTitleSubtitleBinding.tvTitle.setText(reportData.getTitle());
            listItemImageTitleSubtitleBinding.tvDesc.setText(String.valueOf(ThemesUtils.INSTANCE.formattedDateBasedOnPattern(reportData.getCreatedDate(), UtilConstants.SERVER_TIME_FORMAT, "dd, MMM YYYY, hh:mm a")));
            listItemImageTitleSubtitleBinding.clRoot.getRootView().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.weeklyreport.adapter.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    WeeklyFitnessReportAdapter.ViewHolder.b(WeeklyFitnessReportAdapter.this, reportData, view);
                }
            });
        }
    }

    public WeeklyFitnessReportAdapter(@NotNull Context context, @NotNull FitnessReportClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f6241a = context;
        this.b = listener;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f6241a;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    @NotNull
    public final FitnessReportClickListener getListener() {
        return this.b;
    }

    public final void setReportList(@NotNull List<? extends FitnessReportRes.FitnessReportItem> reportRes) {
        Intrinsics.checkNotNullParameter(reportRes, "reportRes");
        this.c = reportRes;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull ViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        ListItemImageTitleSubtitleBinding inflate = ListItemImageTitleSubtitleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new ViewHolder(this, inflate);
    }
}
