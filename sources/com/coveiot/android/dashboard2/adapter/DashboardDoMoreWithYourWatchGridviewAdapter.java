package com.coveiot.android.dashboard2.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dashboard2.adapter.DashboardDoMoreWithYourWatchGridviewAdapter;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchDataModel;
import com.coveiot.android.dashboard2.model.DoMoreWithYourWatchFeatureType;
import com.coveiot.android.dashboard2.util.DoMoreWithYourWatchHelper;
import com.coveiot.android.theme.databinding.LayoutDashboardDoMoreWithYourWatchGridItemBinding;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DashboardDoMoreWithYourWatchGridviewAdapter extends RecyclerView.Adapter<DashboardDoMoreWithYourWatchViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f4216a;
    @Nullable
    public DoMoreWithYourWatchItemClickListener b;
    @NotNull
    public List<DoMoreWithYourWatchDataModel> c;

    /* loaded from: classes4.dex */
    public final class DashboardDoMoreWithYourWatchViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final LayoutDashboardDoMoreWithYourWatchGridItemBinding f4217a;
        public final /* synthetic */ DashboardDoMoreWithYourWatchGridviewAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DashboardDoMoreWithYourWatchViewHolder(@NotNull DashboardDoMoreWithYourWatchGridviewAdapter dashboardDoMoreWithYourWatchGridviewAdapter, LayoutDashboardDoMoreWithYourWatchGridItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = dashboardDoMoreWithYourWatchGridviewAdapter;
            this.f4217a = binding;
        }

        public static final void b(DashboardDoMoreWithYourWatchGridviewAdapter this$0, DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel, View view) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "$doMoreWithYourWatchDataModel");
            DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener = this$0.getDoMoreWithYourWatchItemClickListener();
            if (doMoreWithYourWatchItemClickListener != null) {
                doMoreWithYourWatchItemClickListener.doMoreWithYourWatchItemClicked(doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType());
            }
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull final DoMoreWithYourWatchDataModel doMoreWithYourWatchDataModel) {
            Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModel, "doMoreWithYourWatchDataModel");
            LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding = this.f4217a;
            final DashboardDoMoreWithYourWatchGridviewAdapter dashboardDoMoreWithYourWatchGridviewAdapter = this.b;
            if (doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType() != null) {
                layoutDashboardDoMoreWithYourWatchGridItemBinding.tvHeader.setText(doMoreWithYourWatchDataModel.getTitle());
                layoutDashboardDoMoreWithYourWatchGridItemBinding.tvInfo.setText(doMoreWithYourWatchDataModel.getInfo());
                ConstraintLayout constraintLayout = layoutDashboardDoMoreWithYourWatchGridItemBinding.cardBackground;
                DoMoreWithYourWatchHelper doMoreWithYourWatchHelper = DoMoreWithYourWatchHelper.INSTANCE;
                Context context = dashboardDoMoreWithYourWatchGridviewAdapter.getContext();
                DoMoreWithYourWatchFeatureType doMoreWithYourWatchType = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                Intrinsics.checkNotNull(doMoreWithYourWatchType);
                constraintLayout.setBackground(doMoreWithYourWatchHelper.getDoMoreWithYourWatchBackgroundDrawable(context, doMoreWithYourWatchType.name()));
                ImageView imageView = layoutDashboardDoMoreWithYourWatchGridItemBinding.ivIcon;
                Context context2 = dashboardDoMoreWithYourWatchGridviewAdapter.getContext();
                DoMoreWithYourWatchFeatureType doMoreWithYourWatchType2 = doMoreWithYourWatchDataModel.getDoMoreWithYourWatchType();
                Intrinsics.checkNotNull(doMoreWithYourWatchType2);
                imageView.setImageDrawable(doMoreWithYourWatchHelper.getDoMoreWithYourWatchDrawable(context2, doMoreWithYourWatchType2.name()));
                layoutDashboardDoMoreWithYourWatchGridItemBinding.tvHeader.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.dashboard2.adapter.d
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        DashboardDoMoreWithYourWatchGridviewAdapter.DashboardDoMoreWithYourWatchViewHolder.b(DashboardDoMoreWithYourWatchGridviewAdapter.this, doMoreWithYourWatchDataModel, view);
                    }
                });
            }
        }
    }

    /* loaded from: classes4.dex */
    public interface DoMoreWithYourWatchItemClickListener {
        void doMoreWithYourWatchItemClicked(@Nullable DoMoreWithYourWatchFeatureType doMoreWithYourWatchFeatureType);
    }

    public DashboardDoMoreWithYourWatchGridviewAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4216a = context;
        this.c = new ArrayList();
    }

    @NotNull
    public final Context getContext() {
        return this.f4216a;
    }

    @Nullable
    public final DoMoreWithYourWatchItemClickListener getDoMoreWithYourWatchItemClickListener() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.size();
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f4216a = context;
    }

    public final void setDoMoreWithYourWatchItemClickListener(@Nullable DoMoreWithYourWatchItemClickListener doMoreWithYourWatchItemClickListener) {
        this.b = doMoreWithYourWatchItemClickListener;
    }

    public final void setList(@NotNull List<DoMoreWithYourWatchDataModel> doMoreWithYourWatchDataModelList) {
        Intrinsics.checkNotNullParameter(doMoreWithYourWatchDataModelList, "doMoreWithYourWatchDataModelList");
        this.c = doMoreWithYourWatchDataModelList;
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull DashboardDoMoreWithYourWatchViewHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        holder.bind(this.c.get(i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public DashboardDoMoreWithYourWatchViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutDashboardDoMoreWithYourWatchGridItemBinding inflate = LayoutDashboardDoMoreWithYourWatchGridItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(\n               …      false\n            )");
        return new DashboardDoMoreWithYourWatchViewHolder(this, inflate);
    }
}
