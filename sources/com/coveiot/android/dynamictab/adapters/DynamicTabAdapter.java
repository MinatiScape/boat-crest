package com.coveiot.android.dynamictab.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.dynamictab.DynamicTabWebActivity;
import com.coveiot.android.dynamictab.R;
import com.coveiot.android.dynamictab.sports.model.DashboardModel;
import com.coveiot.coveaccess.model.server.SRemoteConfigResponse;
import com.coveiot.utils.utility.AppUtils;
import java.util.ArrayList;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes4.dex */
public final class DynamicTabAdapter extends RecyclerView.Adapter<DynamicTabViewHolder> {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static int d = 1230;
    @NotNull
    public static String e = "EXTRA_IS_USER_FLAGGED";
    @NotNull
    public static String f = "EXTRA_API_RESPONSE";
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public Context f4316a;
    @NotNull
    public ArrayList<DashboardModel> b;
    @Nullable
    public String c;

    /* loaded from: classes4.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getEXTRA_API_RESPONSE() {
            return DynamicTabAdapter.f;
        }

        @NotNull
        public final String getEXTRA_IS_USER_FLAGGED() {
            return DynamicTabAdapter.e;
        }

        public final int getREQUEST_CODE() {
            return DynamicTabAdapter.d;
        }

        public final void setEXTRA_API_RESPONSE(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            DynamicTabAdapter.f = str;
        }

        public final void setEXTRA_IS_USER_FLAGGED(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            DynamicTabAdapter.e = str;
        }

        public final void setREQUEST_CODE(int i) {
            DynamicTabAdapter.d = i;
        }
    }

    /* loaded from: classes4.dex */
    public final class DynamicTabViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public final RelativeLayout f4317a;
        @NotNull
        public final ImageView b;
        @NotNull
        public final TextView c;
        @NotNull
        public final TextView d;
        @NotNull
        public final TextView e;
        @NotNull
        public final ImageView f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DynamicTabViewHolder(@NotNull DynamicTabAdapter dynamicTabAdapter, View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View findViewById = itemView.findViewById(R.id.root_layout);
            Intrinsics.checkNotNullExpressionValue(findViewById, "itemView.findViewById(R.id.root_layout)");
            this.f4317a = (RelativeLayout) findViewById;
            View findViewById2 = itemView.findViewById(R.id.item_icon_iv);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "itemView.findViewById(R.id.item_icon_iv)");
            this.b = (ImageView) findViewById2;
            View findViewById3 = itemView.findViewById(R.id.item_title);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "itemView.findViewById(R.id.item_title)");
            this.c = (TextView) findViewById3;
            View findViewById4 = itemView.findViewById(R.id.item_desc);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "itemView.findViewById(R.id.item_desc)");
            this.d = (TextView) findViewById4;
            View findViewById5 = itemView.findViewById(R.id.notify_count);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "itemView.findViewById(R.id.notify_count)");
            this.e = (TextView) findViewById5;
            View findViewById6 = itemView.findViewById(R.id.bg);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "itemView.findViewById(R.id.bg)");
            this.f = (ImageView) findViewById6;
        }

        @NotNull
        public final TextView getItemDesc() {
            return this.d;
        }

        @NotNull
        public final ImageView getItemIcon() {
            return this.b;
        }

        @NotNull
        public final TextView getItemTitle() {
            return this.c;
        }

        @NotNull
        public final ImageView getMBg() {
            return this.f;
        }

        @NotNull
        public final TextView getNotifyCount() {
            return this.e;
        }

        @NotNull
        public final RelativeLayout getRootLayout() {
            return this.f4317a;
        }
    }

    public DynamicTabAdapter(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.f4316a = context;
        this.b = new ArrayList<>();
    }

    public static final void b(DynamicTabViewHolder holder, DashboardModel dashboardModel, DynamicTabAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(holder, "$holder");
        Intrinsics.checkNotNullParameter(dashboardModel, "$dashboardModel");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (AppUtils.isNetConnected(holder.itemView.getContext())) {
            for (SRemoteConfigResponse.DataBean.UiBean.ElementsBean.EventsBean eventsBean : dashboardModel.getElementsBean().getEvents()) {
                if (m.equals(eventsBean.getName(), "CLICK", true) && m.equals(eventsBean.getAction(), "OPEN_URL", true)) {
                    if (eventsBean.getTarget() != null && m.equals(eventsBean.getTarget(), "IN_APP", true)) {
                        Intent intent = new Intent(holder.itemView.getContext(), DynamicTabWebActivity.class);
                        intent.putExtra("coveJsInterface", eventsBean.getWindow().getCoveJsInterface().isEnable());
                        intent.putExtra("url", eventsBean.getUrl());
                        if (!AppUtils.isEmpty(this$0.c)) {
                            intent.putExtra("HEALTH_DATA", this$0.c);
                        }
                        Context context = holder.itemView.getContext();
                        Intrinsics.checkNotNull(context, "null cannot be cast to non-null type android.app.Activity");
                        ((Activity) context).startActivityForResult(intent, d);
                        return;
                    } else if (eventsBean.getTarget() != null) {
                        String target = eventsBean.getTarget();
                        Intrinsics.checkNotNullExpressionValue(target, "eventsBean.target");
                        if (!(target.length() == 0)) {
                            holder.itemView.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(eventsBean.getUrl())));
                            return;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return;
        }
        Toast.makeText(holder.itemView.getContext(), "Please check your internet connection.", 1).show();
    }

    @NotNull
    public final Context getContext() {
        return this.f4316a;
    }

    @NotNull
    public final ArrayList<DashboardModel> getDashboardModelList() {
        return this.b;
    }

    @Nullable
    public final String getHealthData() {
        return this.c;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final void setContext(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "<set-?>");
        this.f4316a = context;
    }

    public final void setDashboardModelList(@NotNull ArrayList<DashboardModel> arrayList) {
        Intrinsics.checkNotNullParameter(arrayList, "<set-?>");
        this.b = arrayList;
    }

    public final void setDashboardModels(@NotNull ArrayList<DashboardModel> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        this.b = list;
        notifyDataSetChanged();
    }

    public final void setHealthData(@Nullable String str) {
        this.c = str;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00ce  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0133  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0136  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01a2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01a4  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x01a7  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x021e  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull final com.coveiot.android.dynamictab.adapters.DynamicTabAdapter.DynamicTabViewHolder r5, int r6) {
        /*
            Method dump skipped, instructions count: 584
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.dynamictab.adapters.DynamicTabAdapter.onBindViewHolder(com.coveiot.android.dynamictab.adapters.DynamicTabAdapter$DynamicTabViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public DynamicTabViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_feature_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "from(parent.context).inf…      false\n            )");
        return new DynamicTabViewHolder(this, inflate);
    }
}
