package com.coveiot.android.tappy.customview;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.coveiot.android.tappy.R;
import com.coveiot.android.tappy.model.RegStrapBeanInfo;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes7.dex */
public final class StrapSelectItemDialog extends Dialog {
    @NotNull
    public final ArrayList<RegStrapBeanInfo> h;
    @Nullable
    public OnItemClickListener i;
    public int j;

    /* loaded from: classes7.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StrapSelectItemDialog(@NotNull Context context, @NotNull ArrayList<RegStrapBeanInfo> regStrapBeanInfoList) {
        super(context, R.style.DialogTheme);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(regStrapBeanInfoList, "regStrapBeanInfoList");
        this.h = regStrapBeanInfoList;
    }

    public static final void b(StrapSelectItemDialog this$0, AdapterView adapterView, View view, int i, long j) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.j = i;
        OnItemClickListener onItemClickListener = this$0.i;
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick(i);
        }
        this$0.dismiss();
    }

    @NotNull
    public final ArrayList<RegStrapBeanInfo> getRegStrapBeanInfoList() {
        return this.h;
    }

    @Override // android.app.Dialog
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.strap_select_layout);
        LogHelper.i("StrapItemDialog", "size " + this.h);
        ListView listView = (ListView) findViewById(R.id.dialog_list);
        final Context context = getContext();
        final ArrayList<RegStrapBeanInfo> arrayList = this.h;
        listView.setAdapter((ListAdapter) new ArrayAdapter<RegStrapBeanInfo>(context, arrayList) { // from class: com.coveiot.android.tappy.customview.StrapSelectItemDialog$onCreate$adapter$1
            @Override // android.widget.ArrayAdapter, android.widget.Adapter
            @NotNull
            public View getView(int i, @Nullable View view, @NotNull ViewGroup parent) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.strap_list_item_new_design_divider, parent, false);
                }
                Intrinsics.checkNotNull(view);
                RegStrapBeanInfo regStrapBeanInfo = StrapSelectItemDialog.this.getRegStrapBeanInfoList().get(i);
                Intrinsics.checkNotNullExpressionValue(regStrapBeanInfo, "regStrapBeanInfoList[position]");
                RegStrapBeanInfo regStrapBeanInfo2 = regStrapBeanInfo;
                Glide.with(getContext()).m30load(regStrapBeanInfo2.getImageUrl()).into((ImageView) view.findViewById(R.id.strap_icon));
                ((TextView) view.findViewById(R.id.strap_name)).setText(regStrapBeanInfo2.getFriendlyName());
                return view;
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.coveiot.android.tappy.customview.a
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                StrapSelectItemDialog.b(StrapSelectItemDialog.this, adapterView, view, i, j);
            }
        });
    }

    public final void setNegativeButton(@NotNull View.OnClickListener listner) {
        Intrinsics.checkNotNullParameter(listner, "listner");
        ((TextView) findViewById(R.id.dialog_title)).setOnClickListener(listner);
    }

    public final void setOnItemClickListener(@NotNull OnItemClickListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.i = listener;
    }
}
