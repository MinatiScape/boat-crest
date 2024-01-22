package com.google.android.material.datepicker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.datepicker.MaterialCalendar;
import java.util.Calendar;
import java.util.Locale;
/* loaded from: classes10.dex */
public class YearGridAdapter extends RecyclerView.Adapter<ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public final MaterialCalendar<?> f10285a;

    /* loaded from: classes10.dex */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public final TextView f10286a;

        public ViewHolder(TextView textView) {
            super(textView);
            this.f10286a = textView;
        }
    }

    /* loaded from: classes10.dex */
    public class a implements View.OnClickListener {
        public final /* synthetic */ int h;

        public a(int i) {
            this.h = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            YearGridAdapter.this.f10285a.s(YearGridAdapter.this.f10285a.l().e(Month.b(this.h, YearGridAdapter.this.f10285a.n().i)));
            YearGridAdapter.this.f10285a.t(MaterialCalendar.k.DAY);
        }
    }

    public YearGridAdapter(MaterialCalendar<?> materialCalendar) {
        this.f10285a = materialCalendar;
    }

    @NonNull
    public final View.OnClickListener b(int i) {
        return new a(i);
    }

    public int c(int i) {
        return i - this.f10285a.l().i().j;
    }

    public int d(int i) {
        return this.f10285a.l().i().j + i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e */
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        int d = d(i);
        String string = viewHolder.f10286a.getContext().getString(R.string.mtrl_picker_navigate_to_year_description);
        viewHolder.f10286a.setText(String.format(Locale.getDefault(), "%d", Integer.valueOf(d)));
        viewHolder.f10286a.setContentDescription(String.format(string, Integer.valueOf(d)));
        b m = this.f10285a.m();
        Calendar o = j.o();
        com.google.android.material.datepicker.a aVar = o.get(1) == d ? m.f : m.d;
        for (Long l : this.f10285a.getDateSelector().getSelectedDays()) {
            o.setTimeInMillis(l.longValue());
            if (o.get(1) == d) {
                aVar = m.e;
            }
        }
        aVar.d(viewHolder.f10286a);
        viewHolder.f10286a.setOnClickListener(b(d));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* renamed from: f */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder((TextView) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mtrl_calendar_year, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f10285a.l().j();
    }
}
