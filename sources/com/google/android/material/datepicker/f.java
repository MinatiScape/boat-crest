package com.google.android.material.datepicker;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.TextView;
import androidx.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;
/* loaded from: classes10.dex */
public class f extends BaseAdapter {
    public static final int m = j.q().getMaximum(4);
    public final Month h;
    public final DateSelector<?> i;
    public Collection<Long> j;
    public b k;
    public final CalendarConstraints l;

    public f(Month month, DateSelector<?> dateSelector, CalendarConstraints calendarConstraints) {
        this.h = month;
        this.i = dateSelector;
        this.l = calendarConstraints;
        this.j = dateSelector.getSelectedDays();
    }

    public int a(int i) {
        return b() + (i - 1);
    }

    public int b() {
        return this.h.e();
    }

    @Override // android.widget.Adapter
    @Nullable
    /* renamed from: c */
    public Long getItem(int i) {
        if (i < this.h.e() || i > i()) {
            return null;
        }
        return Long.valueOf(this.h.f(j(i)));
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0083 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0084  */
    @Override // android.widget.Adapter
    @androidx.annotation.NonNull
    /* renamed from: d */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.widget.TextView getView(int r6, @androidx.annotation.Nullable android.view.View r7, @androidx.annotation.NonNull android.view.ViewGroup r8) {
        /*
            r5 = this;
            android.content.Context r0 = r8.getContext()
            r5.e(r0)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
            r1 = 0
            if (r7 != 0) goto L1e
            android.content.Context r7 = r8.getContext()
            android.view.LayoutInflater r7 = android.view.LayoutInflater.from(r7)
            int r0 = com.google.android.material.R.layout.mtrl_calendar_day
            android.view.View r7 = r7.inflate(r0, r8, r1)
            r0 = r7
            android.widget.TextView r0 = (android.widget.TextView) r0
        L1e:
            int r7 = r5.b()
            int r7 = r6 - r7
            if (r7 < 0) goto L75
            com.google.android.material.datepicker.Month r8 = r5.h
            int r2 = r8.l
            if (r7 < r2) goto L2d
            goto L75
        L2d:
            r2 = 1
            int r7 = r7 + r2
            r0.setTag(r8)
            android.content.res.Resources r8 = r0.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
            java.lang.Object[] r3 = new java.lang.Object[r2]
            java.lang.Integer r4 = java.lang.Integer.valueOf(r7)
            r3[r1] = r4
            java.lang.String r4 = "%d"
            java.lang.String r8 = java.lang.String.format(r8, r4, r3)
            r0.setText(r8)
            com.google.android.material.datepicker.Month r8 = r5.h
            long r7 = r8.f(r7)
            com.google.android.material.datepicker.Month r3 = r5.h
            int r3 = r3.j
            com.google.android.material.datepicker.Month r4 = com.google.android.material.datepicker.Month.d()
            int r4 = r4.j
            if (r3 != r4) goto L67
            java.lang.String r7 = com.google.android.material.datepicker.d.g(r7)
            r0.setContentDescription(r7)
            goto L6e
        L67:
            java.lang.String r7 = com.google.android.material.datepicker.d.l(r7)
            r0.setContentDescription(r7)
        L6e:
            r0.setVisibility(r1)
            r0.setEnabled(r2)
            goto L7d
        L75:
            r7 = 8
            r0.setVisibility(r7)
            r0.setEnabled(r1)
        L7d:
            java.lang.Long r6 = r5.getItem(r6)
            if (r6 != 0) goto L84
            return r0
        L84:
            long r6 = r6.longValue()
            r5.k(r0, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.datepicker.f.getView(int, android.view.View, android.view.ViewGroup):android.widget.TextView");
    }

    public final void e(Context context) {
        if (this.k == null) {
            this.k = new b(context);
        }
    }

    public boolean f(int i) {
        return i % this.h.k == 0;
    }

    public boolean g(int i) {
        return (i + 1) % this.h.k == 0;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.h.l + b();
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i / this.h.k;
    }

    public final boolean h(long j) {
        Iterator<Long> it = this.i.getSelectedDays().iterator();
        while (it.hasNext()) {
            if (j.a(j) == j.a(it.next().longValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return true;
    }

    public int i() {
        return (this.h.e() + this.h.l) - 1;
    }

    public int j(int i) {
        return (i - this.h.e()) + 1;
    }

    public final void k(@Nullable TextView textView, long j) {
        a aVar;
        if (textView == null) {
            return;
        }
        if (this.l.getDateValidator().isValid(j)) {
            textView.setEnabled(true);
            if (h(j)) {
                aVar = this.k.b;
            } else if (j.o().getTimeInMillis() == j) {
                aVar = this.k.c;
            } else {
                aVar = this.k.f10288a;
            }
        } else {
            textView.setEnabled(false);
            aVar = this.k.g;
        }
        aVar.d(textView);
    }

    public final void l(MaterialCalendarGridView materialCalendarGridView, long j) {
        if (Month.c(j).equals(this.h)) {
            k((TextView) materialCalendarGridView.getChildAt(materialCalendarGridView.getAdapter2().a(this.h.g(j)) - materialCalendarGridView.getFirstVisiblePosition()), j);
        }
    }

    public void m(MaterialCalendarGridView materialCalendarGridView) {
        for (Long l : this.j) {
            l(materialCalendarGridView, l.longValue());
        }
        DateSelector<?> dateSelector = this.i;
        if (dateSelector != null) {
            for (Long l2 : dateSelector.getSelectedDays()) {
                l(materialCalendarGridView, l2.longValue());
            }
            this.j = this.i.getSelectedDays();
        }
    }

    public boolean n(int i) {
        return i >= b() && i <= i();
    }
}
