package com.coveiot.android.healthbuddies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coveiot.android.healthbuddies.R;
import com.coveiot.coveaccess.healthbuddies.HealthBuddy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes3.dex */
public final class ManageDoctorAdapter extends BaseExpandableListAdapter {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public List<String> f4566a;
    @Nullable
    public HashMap<String, List<HealthBuddy>> b;
    @NotNull
    public OnOptionSelectedListener c;
    @NotNull
    public Context d;

    /* loaded from: classes3.dex */
    public interface OnOptionSelectedListener {
        void onCancel(int i);

        void onDelete(int i);

        void onReInvite(int i, @Nullable String str, @Nullable String str2);
    }

    public ManageDoctorAdapter(@NotNull Context context, @Nullable HashMap<String, List<HealthBuddy>> hashMap, @NotNull List<String> titles, @NotNull OnOptionSelectedListener itemClickInterface) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(titles, "titles");
        Intrinsics.checkNotNullParameter(itemClickInterface, "itemClickInterface");
        this.f4566a = new ArrayList();
        this.b = hashMap;
        this.f4566a = titles;
        this.c = itemClickInterface;
        this.d = context;
    }

    public static final void f() {
    }

    public static final void g(ManageDoctorAdapter this$0, HealthBuddy healthBuddy, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Integer num = healthBuddy.requestId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyInfo.requestId");
        onOptionSelectedListener.onReInvite(num.intValue(), healthBuddy.toUserMobileNumber, healthBuddy.toContactName);
    }

    public static final void h(ManageDoctorAdapter this$0, HealthBuddy healthBuddy, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Integer num = healthBuddy.requestId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyInfo.requestId");
        onOptionSelectedListener.onCancel(num.intValue());
    }

    public static final void i() {
    }

    public static final void j(ManageDoctorAdapter this$0, HealthBuddy healthBuddy, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        OnOptionSelectedListener onOptionSelectedListener = this$0.c;
        Integer num = healthBuddy.userId;
        Intrinsics.checkNotNullExpressionValue(num, "buddyInfo.userId");
        onOptionSelectedListener.onDelete(num.intValue());
    }

    @Override // android.widget.ExpandableListAdapter
    public long getChildId(int i, int i2) {
        return i2;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00ec  */
    @Override // android.widget.ExpandableListAdapter
    @org.jetbrains.annotations.Nullable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.view.View getChildView(int r17, int r18, boolean r19, @org.jetbrains.annotations.Nullable android.view.View r20, @org.jetbrains.annotations.Nullable android.view.ViewGroup r21) {
        /*
            Method dump skipped, instructions count: 431
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.healthbuddies.adapters.ManageDoctorAdapter.getChildView(int, int, boolean, android.view.View, android.view.ViewGroup):android.view.View");
    }

    @Override // android.widget.ExpandableListAdapter
    public int getChildrenCount(int i) {
        HashMap<String, List<HealthBuddy>> hashMap = this.b;
        if (hashMap != null) {
            if ((hashMap != null ? hashMap.get(this.f4566a.get(i)) : null) != null) {
                HashMap<String, List<HealthBuddy>> hashMap2 = this.b;
                Intrinsics.checkNotNull(hashMap2);
                List<HealthBuddy> list = hashMap2.get(this.f4566a.get(i));
                Intrinsics.checkNotNull(list);
                return list.size();
            }
            return 0;
        }
        return 0;
    }

    @Override // android.widget.ExpandableListAdapter
    public int getGroupCount() {
        return this.f4566a.size();
    }

    @Override // android.widget.ExpandableListAdapter
    public long getGroupId(int i) {
        return i;
    }

    @Override // android.widget.ExpandableListAdapter
    @Nullable
    public View getGroupView(int i, boolean z, @Nullable View view, @Nullable ViewGroup viewGroup) {
        String group = getGroup(i);
        if (view == null) {
            Object systemService = this.d.getSystemService("layout_inflater");
            Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.LayoutInflater");
            view = ((LayoutInflater) systemService).inflate(R.layout.health_buddies_list_group, (ViewGroup) null);
        }
        Intrinsics.checkNotNull(view);
        TextView textView = (TextView) view.findViewById(R.id.notification_count);
        ImageView imageView = (ImageView) view.findViewById(R.id.arrow);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.rl_health_buddies_group);
        ((TextView) view.findViewById(R.id.title)).setText(group);
        if (z) {
            imageView.setRotation(180.0f);
            if (getChildrenCount(i) > 0) {
                relativeLayout.setBackgroundResource(R.drawable.rounded_top_corners_rounded_grey);
            } else {
                relativeLayout.setBackgroundResource(R.drawable.list_item_background_new);
            }
        } else {
            imageView.setRotation(0.0f);
            relativeLayout.setBackgroundResource(R.drawable.list_item_background_new);
        }
        if (getChildrenCount(i) > 0) {
            textView.setVisibility(0);
            textView.setText(String.valueOf(getChildrenCount(i)));
        } else {
            textView.setVisibility(8);
        }
        return view;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean hasStableIds() {
        return false;
    }

    @Override // android.widget.ExpandableListAdapter
    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    @Override // android.widget.ExpandableListAdapter
    @Nullable
    public HealthBuddy getChild(int i, int i2) {
        HashMap<String, List<HealthBuddy>> hashMap = this.b;
        if (hashMap != null) {
            if ((hashMap != null ? hashMap.get(this.f4566a.get(i)) : null) != null) {
                HashMap<String, List<HealthBuddy>> hashMap2 = this.b;
                Intrinsics.checkNotNull(hashMap2);
                List<HealthBuddy> list = hashMap2.get(this.f4566a.get(i));
                Intrinsics.checkNotNull(list);
                return list.get(i2);
            }
        }
        return null;
    }

    @Override // android.widget.ExpandableListAdapter
    @NotNull
    public String getGroup(int i) {
        return this.f4566a.get(i);
    }
}
