package com.google.android.material.datepicker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
/* loaded from: classes10.dex */
public class MonthsPagerAdapter extends RecyclerView.Adapter<ViewHolder> {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final CalendarConstraints f10283a;
    public final DateSelector<?> b;
    public final MaterialCalendar.l c;
    public final int d;

    /* loaded from: classes10.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f10284a;
        public final MaterialCalendarGridView b;

        public ViewHolder(@NonNull LinearLayout linearLayout, boolean z) {
            super(linearLayout);
            TextView textView = (TextView) linearLayout.findViewById(R.id.month_title);
            this.f10284a = textView;
            ViewCompat.setAccessibilityHeading(textView, true);
            this.b = (MaterialCalendarGridView) linearLayout.findViewById(R.id.month_grid);
            if (z) {
                return;
            }
            textView.setVisibility(8);
        }
    }

    /* loaded from: classes10.dex */
    public class a implements AdapterView.OnItemClickListener {
        public final /* synthetic */ MaterialCalendarGridView h;

        public a(MaterialCalendarGridView materialCalendarGridView) {
            this.h = materialCalendarGridView;
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.h.getAdapter2().n(i)) {
                MonthsPagerAdapter.this.c.a(this.h.getAdapter2().getItem(i).longValue());
            }
        }
    }

    public MonthsPagerAdapter(@NonNull Context context, DateSelector<?> dateSelector, @NonNull CalendarConstraints calendarConstraints, MaterialCalendar.l lVar) {
        Month i = calendarConstraints.i();
        Month f = calendarConstraints.f();
        Month h = calendarConstraints.h();
        if (i.compareTo(h) <= 0) {
            if (h.compareTo(f) <= 0) {
                this.d = (f.m * MaterialCalendar.o(context)) + (MaterialDatePicker.n(context) ? MaterialCalendar.o(context) : 0);
                this.f10283a = calendarConstraints;
                this.b = dateSelector;
                this.c = lVar;
                setHasStableIds(true);
                return;
            }
            throw new IllegalArgumentException("currentPage cannot be after lastPage");
        }
        throw new IllegalArgumentException("firstPage cannot be after currentPage");
    }

    @NonNull
    public Month b(int i) {
        return this.f10283a.i().j(i);
    }

    @NonNull
    public CharSequence c(int i) {
        return b(i).h();
    }

    public int d(@NonNull Month month) {
        return this.f10283a.i().k(month);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e */
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Month j = this.f10283a.i().j(i);
        viewHolder.f10284a.setText(j.h());
        MaterialCalendarGridView materialCalendarGridView = (MaterialCalendarGridView) viewHolder.b.findViewById(R.id.month_grid);
        if (materialCalendarGridView.getAdapter2() != null && j.equals(materialCalendarGridView.getAdapter2().h)) {
            materialCalendarGridView.invalidate();
            materialCalendarGridView.getAdapter2().m(materialCalendarGridView);
        } else {
            f fVar = new f(j, this.b, this.f10283a);
            materialCalendarGridView.setNumColumns(j.k);
            materialCalendarGridView.setAdapter((ListAdapter) fVar);
        }
        materialCalendarGridView.setOnItemClickListener(new a(materialCalendarGridView));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: f */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_month_labeled, viewGroup, false);
        if (MaterialDatePicker.n(viewGroup.getContext())) {
            linearLayout.setLayoutParams(new RecyclerView.LayoutParams(-1, this.d));
            return new ViewHolder(linearLayout, true);
        }
        return new ViewHolder(linearLayout, false);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10283a.g();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return this.f10283a.i().j(i).i();
    }
}
