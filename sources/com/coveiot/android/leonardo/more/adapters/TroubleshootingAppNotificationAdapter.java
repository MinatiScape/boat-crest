package com.coveiot.android.leonardo.more.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.databinding.TroubleshootActivityNotiItemBinding;
import com.coveiot.covepreferences.data.AppNotificationData;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class TroubleshootingAppNotificationAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f5093a;
    @NotNull
    public final List<AppNotificationData> b;

    /* loaded from: classes5.dex */
    public final class ViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final TroubleshootActivityNotiItemBinding f5094a;
        public final /* synthetic */ TroubleshootingAppNotificationAdapter b;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(@NotNull TroubleshootingAppNotificationAdapter troubleshootingAppNotificationAdapter, TroubleshootActivityNotiItemBinding binding) {
            super(binding.getRoot());
            Intrinsics.checkNotNullParameter(binding, "binding");
            this.b = troubleshootingAppNotificationAdapter;
            this.f5094a = binding;
        }

        @SuppressLint({"SetTextI18n"})
        public final void bind(@NotNull AppNotificationData appNotificationData) {
            Intrinsics.checkNotNullParameter(appNotificationData, "appNotificationData");
            TroubleshootActivityNotiItemBinding troubleshootActivityNotiItemBinding = this.f5094a;
            TroubleshootingAppNotificationAdapter troubleshootingAppNotificationAdapter = this.b;
            if (appNotificationData.getChecked()) {
                String packageName = appNotificationData.getPackageName();
                boolean z = true;
                if (!(packageName == null || packageName.length() == 0)) {
                    String appName = appNotificationData.getAppName();
                    if (appName != null && appName.length() != 0) {
                        z = false;
                    }
                    if (!z) {
                        troubleshootActivityNotiItemBinding.clMainItem.setVisibility(0);
                        troubleshootActivityNotiItemBinding.notiItemIcon.setVisibility(0);
                        try {
                            troubleshootActivityNotiItemBinding.notiItemIcon.setImageDrawable(troubleshootingAppNotificationAdapter.getContext().getPackageManager().getApplicationIcon(troubleshootingAppNotificationAdapter.getContext().getPackageManager().getApplicationInfo(appNotificationData.getPackageName(), 0)));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        troubleshootActivityNotiItemBinding.notiItemTvContent.setText(appNotificationData.getAppName());
                        return;
                    }
                }
            }
            troubleshootActivityNotiItemBinding.clMainItem.setVisibility(8);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public TroubleshootingAppNotificationAdapter(@NotNull Context context, @NotNull List<? extends AppNotificationData> dataList) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        this.f5093a = context;
        this.b = dataList;
    }

    @NotNull
    public final Context getContext() {
        return this.f5093a;
    }

    @NotNull
    public final List<AppNotificationData> getDataList() {
        return this.b;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
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
        TroubleshootActivityNotiItemBinding inflate = TroubleshootActivityNotiItemBinding.inflate(LayoutInflater.from(this.f5093a), parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(LayoutInflater.f…(context), parent, false)");
        return new ViewHolder(this, inflate);
    }
}
