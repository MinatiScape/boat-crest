package com.coveiot.leaderboard.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.leaderboard.base.BaseViewHolder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/* loaded from: classes9.dex */
public abstract class BaseAdapter<T, V extends BaseViewHolder<T>> extends RecyclerView.Adapter<V> {
    public Context mContext;
    public List<T> mData = new ArrayList();

    public BaseAdapter(Context context) {
        this.mContext = context;
    }

    public void addData(Collection<T> collection) {
        this.mData.addAll(collection);
    }

    public void addElement(T t) {
        this.mData.add(t);
    }

    public void clear() {
        this.mData.clear();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mData.size();
    }

    public abstract V getViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup, int i);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public /* bridge */ /* synthetic */ void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        onBindViewHolder((BaseAdapter<T, V>) ((BaseViewHolder) viewHolder), i);
    }

    public void removeItemAt(int i) {
        List<T> list = this.mData;
        if (list == null || list.size() <= 0) {
            return;
        }
        this.mData.remove(i);
    }

    public void setData(List<T> list) {
        this.mData = list;
    }

    public void update(List<T> list) {
        List<T> list2 = this.mData;
        if (list2 != null && list2.size() > 0) {
            this.mData.clear();
            this.mData.addAll(list);
            return;
        }
        this.mData.addAll(list);
    }

    public void onBindViewHolder(V v, int i) {
        v.onBindView(this.mContext, this.mData.get(i), i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public V onCreateViewHolder(ViewGroup viewGroup, int i) {
        return getViewHolder(LayoutInflater.from(this.mContext), viewGroup, i);
    }
}
