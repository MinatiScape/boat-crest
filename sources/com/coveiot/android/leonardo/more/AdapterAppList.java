package com.coveiot.android.leonardo.more;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.utils.ViewUtilsKt;
import com.coveiot.covepreferences.data.AppNotificationData;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes5.dex */
public final class AdapterAppList extends RecyclerView.Adapter<MyViewHolder> {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public final Context f4873a;
    @NotNull
    public ArrayList<AppNotificationData> b;
    @NotNull
    public final AppNotificationInterface c;
    @NotNull
    public final SmartAlertClickListener d;

    /* loaded from: classes5.dex */
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        public TextView f4874a;
        @NotNull
        public TextView b;
        @NotNull
        public TextView c;
        @NotNull
        public ImageView d;
        @NotNull
        public CheckBox e;
        @NotNull
        public View f;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(@NotNull AdapterAppList adapterAppList, View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            View findViewById = view.findViewById(R.id.noti_item_tv_content);
            Intrinsics.checkNotNullExpressionValue(findViewById, "view.findViewById(R.id.noti_item_tv_content)");
            this.f4874a = (TextView) findViewById;
            View findViewById2 = view.findViewById(R.id.noti_item_tv_desc);
            Intrinsics.checkNotNullExpressionValue(findViewById2, "view.findViewById(R.id.noti_item_tv_desc)");
            this.c = (TextView) findViewById2;
            View findViewById3 = view.findViewById(R.id.tvSmartAlert);
            Intrinsics.checkNotNullExpressionValue(findViewById3, "view.findViewById(R.id.tvSmartAlert)");
            this.b = (TextView) findViewById3;
            View findViewById4 = view.findViewById(R.id.noti_item_icon);
            Intrinsics.checkNotNullExpressionValue(findViewById4, "view.findViewById(R.id.noti_item_icon)");
            this.d = (ImageView) findViewById4;
            View findViewById5 = view.findViewById(R.id.noti_item_esw);
            Intrinsics.checkNotNullExpressionValue(findViewById5, "view.findViewById(R.id.noti_item_esw)");
            this.e = (CheckBox) findViewById5;
            View findViewById6 = view.findViewById(R.id.divider);
            Intrinsics.checkNotNullExpressionValue(findViewById6, "view.findViewById(R.id.divider)");
            this.f = findViewById6;
        }

        @NotNull
        public final TextView getAppDesc() {
            return this.c;
        }

        @NotNull
        public final ImageView getAppIcon() {
            return this.d;
        }

        @NotNull
        public final TextView getAppName() {
            return this.f4874a;
        }

        @NotNull
        public final CheckBox getCheckBox() {
            return this.e;
        }

        @NotNull
        public final View getDivider() {
            return this.f;
        }

        @NotNull
        public final TextView getTvKnowMore() {
            return this.b;
        }

        public final void setAppDesc(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.c = textView;
        }

        public final void setAppIcon(@NotNull ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.d = imageView;
        }

        public final void setAppName(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.f4874a = textView;
        }

        public final void setCheckBox(@NotNull CheckBox checkBox) {
            Intrinsics.checkNotNullParameter(checkBox, "<set-?>");
            this.e = checkBox;
        }

        public final void setDivider(@NotNull View view) {
            Intrinsics.checkNotNullParameter(view, "<set-?>");
            this.f = view;
        }

        public final void setTvKnowMore(@NotNull TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.b = textView;
        }
    }

    /* loaded from: classes5.dex */
    public interface SmartAlertClickListener {
        void knowMoreClick();
    }

    public AdapterAppList(@NotNull Context context, @NotNull ArrayList<AppNotificationData> appNotificationData, @NotNull AppNotificationInterface appNotificationInterface, @NotNull SmartAlertClickListener listener) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(appNotificationData, "appNotificationData");
        Intrinsics.checkNotNullParameter(appNotificationInterface, "appNotificationInterface");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.f4873a = context;
        this.b = appNotificationData;
        this.c = appNotificationInterface;
        this.d = listener;
    }

    public static final void c(AdapterAppList this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.d.knowMoreClick();
    }

    public static final void d(AdapterAppList this$0, int i, AppNotificationData appNotificationDataModel, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(appNotificationDataModel, "$appNotificationDataModel");
        this$0.b.get(i).setChecked(z);
        AppNotificationInterface appNotificationInterface = this$0.c;
        ArrayList<AppNotificationData> arrayList = this$0.b;
        String packageName = appNotificationDataModel.getPackageName();
        Intrinsics.checkNotNullExpressionValue(packageName, "appNotificationDataModel.packageName");
        appNotificationInterface.saveAppData(arrayList, packageName, z);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.b.size();
    }

    public final void setAppNotificationData(@NotNull List<? extends AppNotificationData> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        int size = this.b.size();
        this.b.clear();
        this.b.addAll(data);
        notifyItemRangeRemoved(0, size);
        notifyItemRangeInserted(0, this.b.size());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NotNull MyViewHolder holder, final int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        ArrayList<AppNotificationData> arrayList = this.b;
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        AppNotificationData appNotificationData = this.b.get(i);
        Intrinsics.checkNotNullExpressionValue(appNotificationData, "appNotificationData[position]");
        final AppNotificationData appNotificationData2 = appNotificationData;
        holder.getAppIcon().setVisibility(0);
        holder.getAppName().setText(appNotificationData2.getAppName());
        if (appNotificationData2.getPackageName().equals("com.whatsapp.w4b")) {
            ViewUtilsKt.visible(holder.getAppDesc());
            holder.getAppDesc().setText(this.f4873a.getString(R.string.whatsapp_bussiness_desc));
        } else {
            ViewUtilsKt.gone(holder.getAppDesc());
        }
        holder.getCheckBox().setOnCheckedChangeListener(null);
        holder.getCheckBox().setChecked(appNotificationData2.getChecked());
        View divider = holder.getDivider();
        this.b.size();
        divider.setVisibility(8);
        try {
            holder.getAppIcon().setImageDrawable(this.f4873a.getPackageManager().getApplicationIcon(this.f4873a.getPackageManager().getApplicationInfo(appNotificationData2.getPackageName(), 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (appNotificationData2.isSmartAlertSupported()) {
            holder.getTvKnowMore().setVisibility(0);
            holder.getTvKnowMore().setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.more.a
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AdapterAppList.c(AdapterAppList.this, view);
                }
            });
        } else {
            holder.getTvKnowMore().setVisibility(8);
        }
        holder.getCheckBox().setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.coveiot.android.leonardo.more.b
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AdapterAppList.d(AdapterAppList.this, i, appNotificationData2, compoundButton, z);
            }
        });
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NotNull
    public MyViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_noti_item, parent, false);
        Intrinsics.checkNotNullExpressionValue(itemView, "itemView");
        return new MyViewHolder(this, itemView);
    }
}
