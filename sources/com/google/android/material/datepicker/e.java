package com.google.android.material.datepicker;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.R;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes10.dex */
public class e extends BaseAdapter {
    public static final int k;
    @NonNull
    public final Calendar h;
    public final int i;
    public final int j;

    static {
        k = Build.VERSION.SDK_INT >= 26 ? 4 : 1;
    }

    public e() {
        Calendar q = j.q();
        this.h = q;
        this.i = q.getMaximum(7);
        this.j = q.getFirstDayOfWeek();
    }

    @Override // android.widget.Adapter
    @Nullable
    /* renamed from: a */
    public Integer getItem(int i) {
        if (i >= this.i) {
            return null;
        }
        return Integer.valueOf(b(i));
    }

    public final int b(int i) {
        int i2 = i + this.j;
        int i3 = this.i;
        return i2 > i3 ? i2 - i3 : i2;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.i;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return 0L;
    }

    @Override // android.widget.Adapter
    @Nullable
    @SuppressLint({"WrongConstant"})
    public View getView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
        TextView textView = (TextView) view;
        if (view == null) {
            textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_day_of_week, viewGroup, false);
        }
        this.h.set(7, b(i));
        textView.setText(this.h.getDisplayName(7, k, textView.getResources().getConfiguration().locale));
        textView.setContentDescription(String.format(viewGroup.getContext().getString(R.string.mtrl_picker_day_of_week_column_header), this.h.getDisplayName(7, 2, Locale.getDefault())));
        return textView;
    }
}
