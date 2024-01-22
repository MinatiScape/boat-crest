package androidx.databinding.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.RestrictTo;
import androidx.databinding.ObservableList;
import java.util.List;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class a<T> extends BaseAdapter {
    public List<T> h;
    public ObservableList.OnListChangedCallback i;
    public final Context j;
    public final int k;
    public final int l;
    public final int m;
    public final LayoutInflater n;

    /* renamed from: androidx.databinding.adapters.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public class C0139a extends ObservableList.OnListChangedCallback {
        public C0139a() {
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onChanged(ObservableList observableList) {
            a.this.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            a.this.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
            a.this.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            a.this.notifyDataSetChanged();
        }

        @Override // androidx.databinding.ObservableList.OnListChangedCallback
        public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
            a.this.notifyDataSetChanged();
        }
    }

    public a(Context context, List<T> list, int i, int i2, int i3) {
        this.j = context;
        this.l = i;
        this.k = i2;
        this.m = i3;
        this.n = i == 0 ? null : (LayoutInflater) context.getSystemService("layout_inflater");
        b(list);
    }

    public View a(int i, int i2, View view, ViewGroup viewGroup) {
        CharSequence valueOf;
        if (view == null) {
            if (i == 0) {
                view = new TextView(this.j);
            } else {
                view = this.n.inflate(i, viewGroup, false);
            }
        }
        int i3 = this.m;
        TextView textView = (TextView) (i3 == 0 ? view : view.findViewById(i3));
        T t = this.h.get(i2);
        if (t instanceof CharSequence) {
            valueOf = (CharSequence) t;
        } else {
            valueOf = String.valueOf(t);
        }
        textView.setText(valueOf);
        return view;
    }

    public void b(List<T> list) {
        List<T> list2 = this.h;
        if (list2 == list) {
            return;
        }
        if (list2 instanceof ObservableList) {
            ((ObservableList) list2).removeOnListChangedCallback(this.i);
        }
        this.h = list;
        if (list instanceof ObservableList) {
            if (this.i == null) {
                this.i = new C0139a();
            }
            ((ObservableList) this.h).addOnListChangedCallback(this.i);
        }
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.h.size();
    }

    @Override // android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return a(this.k, i, view, viewGroup);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.h.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        return a(this.l, i, view, viewGroup);
    }
}
