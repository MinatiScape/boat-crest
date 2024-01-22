package com.google.android.material.datepicker;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.R;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.textfield.TextInputLayout;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
/* loaded from: classes10.dex */
public abstract class c extends TextWatcherAdapter {
    @NonNull
    public final TextInputLayout h;
    public final DateFormat i;
    public final CalendarConstraints j;
    public final String k;
    public final Runnable l;
    public Runnable m;

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ String h;

        public a(String str) {
            this.h = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextInputLayout textInputLayout = c.this.h;
            DateFormat dateFormat = c.this.i;
            Context context = textInputLayout.getContext();
            String string = context.getString(R.string.mtrl_picker_invalid_format);
            String format = String.format(context.getString(R.string.mtrl_picker_invalid_format_use), this.h);
            String format2 = String.format(context.getString(R.string.mtrl_picker_invalid_format_example), dateFormat.format(new Date(j.o().getTimeInMillis())));
            textInputLayout.setError(string + "\n" + format + "\n" + format2);
            c.this.e();
        }
    }

    /* loaded from: classes10.dex */
    public class b implements Runnable {
        public final /* synthetic */ long h;

        public b(long j) {
            this.h = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.this.h.setError(String.format(c.this.k, d.c(this.h)));
            c.this.e();
        }
    }

    public c(String str, DateFormat dateFormat, @NonNull TextInputLayout textInputLayout, CalendarConstraints calendarConstraints) {
        this.i = dateFormat;
        this.h = textInputLayout;
        this.j = calendarConstraints;
        this.k = textInputLayout.getContext().getString(R.string.mtrl_picker_out_of_range);
        this.l = new a(str);
    }

    public final Runnable d(long j) {
        return new b(j);
    }

    public abstract void e();

    public abstract void f(@Nullable Long l);

    public void g(View view, Runnable runnable) {
        view.postDelayed(runnable, 1000L);
    }

    @Override // com.google.android.material.internal.TextWatcherAdapter, android.text.TextWatcher
    public void onTextChanged(@NonNull CharSequence charSequence, int i, int i2, int i3) {
        this.h.removeCallbacks(this.l);
        this.h.removeCallbacks(this.m);
        this.h.setError(null);
        f(null);
        if (TextUtils.isEmpty(charSequence)) {
            return;
        }
        try {
            Date parse = this.i.parse(charSequence.toString());
            this.h.setError(null);
            long time = parse.getTime();
            if (this.j.getDateValidator().isValid(time) && this.j.k(time)) {
                f(Long.valueOf(parse.getTime()));
                return;
            }
            Runnable d = d(time);
            this.m = d;
            g(this.h, d);
        } catch (ParseException unused) {
            g(this.h, this.l);
        }
    }
}
