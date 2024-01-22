package com.coveiot.android.dynamictab.adapters.base;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
/* loaded from: classes4.dex */
public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    public BaseViewHolder(View view) {
        super(view);
    }

    public abstract void onBindView(Context context, T t, int i);
}
