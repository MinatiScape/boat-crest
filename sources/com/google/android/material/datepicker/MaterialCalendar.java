package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.Pair;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.button.MaterialButton;
import java.util.Calendar;
import java.util.Iterator;
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
/* loaded from: classes10.dex */
public final class MaterialCalendar<S> extends com.google.android.material.datepicker.g<S> {
    @VisibleForTesting
    public static final Object r = "MONTHS_VIEW_GROUP_TAG";
    @VisibleForTesting
    public static final Object s = "NAVIGATION_PREV_TAG";
    @VisibleForTesting
    public static final Object t = "NAVIGATION_NEXT_TAG";
    @VisibleForTesting
    public static final Object u = "SELECTOR_TOGGLE_TAG";
    @StyleRes
    public int h;
    @Nullable
    public DateSelector<S> i;
    @Nullable
    public CalendarConstraints j;
    @Nullable
    public Month k;
    public k l;
    public com.google.android.material.datepicker.b m;
    public RecyclerView n;
    public RecyclerView o;
    public View p;
    public View q;

    /* loaded from: classes10.dex */
    public class a implements Runnable {
        public final /* synthetic */ int h;

        public a(int i) {
            this.h = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            MaterialCalendar.this.o.smoothScrollToPosition(this.h);
        }
    }

    /* loaded from: classes10.dex */
    public class b extends AccessibilityDelegateCompat {
        public b(MaterialCalendar materialCalendar) {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            accessibilityNodeInfoCompat.setCollectionInfo(null);
        }
    }

    /* loaded from: classes10.dex */
    public class c extends com.google.android.material.datepicker.h {
        public final /* synthetic */ int P;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public c(Context context, int i, boolean z, int i2) {
            super(context, i, z);
            this.P = i2;
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager
        public void calculateExtraLayoutSpace(@NonNull RecyclerView.State state, @NonNull int[] iArr) {
            if (this.P == 0) {
                iArr[0] = MaterialCalendar.this.o.getWidth();
                iArr[1] = MaterialCalendar.this.o.getWidth();
                return;
            }
            iArr[0] = MaterialCalendar.this.o.getHeight();
            iArr[1] = MaterialCalendar.this.o.getHeight();
        }
    }

    /* loaded from: classes10.dex */
    public class d implements l {
        public d() {
        }

        @Override // com.google.android.material.datepicker.MaterialCalendar.l
        public void a(long j) {
            if (MaterialCalendar.this.j.getDateValidator().isValid(j)) {
                MaterialCalendar.this.i.select(j);
                Iterator<OnSelectionChangedListener<S>> it = MaterialCalendar.this.onSelectionChangedListeners.iterator();
                while (it.hasNext()) {
                    it.next().onSelectionChanged((S) MaterialCalendar.this.i.getSelection());
                }
                MaterialCalendar.this.o.getAdapter().notifyDataSetChanged();
                if (MaterialCalendar.this.n != null) {
                    MaterialCalendar.this.n.getAdapter().notifyDataSetChanged();
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public class e extends RecyclerView.ItemDecoration {

        /* renamed from: a  reason: collision with root package name */
        public final Calendar f10277a = com.google.android.material.datepicker.j.q();
        public final Calendar b = com.google.android.material.datepicker.j.q();

        public e() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            int width;
            if ((recyclerView.getAdapter() instanceof YearGridAdapter) && (recyclerView.getLayoutManager() instanceof GridLayoutManager)) {
                YearGridAdapter yearGridAdapter = (YearGridAdapter) recyclerView.getAdapter();
                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                for (Pair<Long, Long> pair : MaterialCalendar.this.i.getSelectedRanges()) {
                    Long l = pair.first;
                    if (l != null && pair.second != null) {
                        this.f10277a.setTimeInMillis(l.longValue());
                        this.b.setTimeInMillis(pair.second.longValue());
                        int c = yearGridAdapter.c(this.f10277a.get(1));
                        int c2 = yearGridAdapter.c(this.b.get(1));
                        View findViewByPosition = gridLayoutManager.findViewByPosition(c);
                        View findViewByPosition2 = gridLayoutManager.findViewByPosition(c2);
                        int spanCount = c / gridLayoutManager.getSpanCount();
                        int spanCount2 = c2 / gridLayoutManager.getSpanCount();
                        int i = spanCount;
                        while (i <= spanCount2) {
                            View findViewByPosition3 = gridLayoutManager.findViewByPosition(gridLayoutManager.getSpanCount() * i);
                            if (findViewByPosition3 != null) {
                                int top = findViewByPosition3.getTop() + MaterialCalendar.this.m.d.c();
                                int bottom = findViewByPosition3.getBottom() - MaterialCalendar.this.m.d.b();
                                int left = i == spanCount ? findViewByPosition.getLeft() + (findViewByPosition.getWidth() / 2) : 0;
                                if (i == spanCount2) {
                                    width = findViewByPosition2.getLeft() + (findViewByPosition2.getWidth() / 2);
                                } else {
                                    width = recyclerView.getWidth();
                                }
                                canvas.drawRect(left, top, width, bottom, MaterialCalendar.this.m.h);
                            }
                            i++;
                        }
                    }
                }
            }
        }
    }

    /* loaded from: classes10.dex */
    public class f extends AccessibilityDelegateCompat {
        public f() {
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public void onInitializeAccessibilityNodeInfo(View view, @NonNull AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            String string;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            if (MaterialCalendar.this.q.getVisibility() == 0) {
                string = MaterialCalendar.this.getString(R.string.mtrl_picker_toggle_to_year_selection);
            } else {
                string = MaterialCalendar.this.getString(R.string.mtrl_picker_toggle_to_day_selection);
            }
            accessibilityNodeInfoCompat.setHintText(string);
        }
    }

    /* loaded from: classes10.dex */
    public class g extends RecyclerView.OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ MonthsPagerAdapter f10278a;
        public final /* synthetic */ MaterialButton b;

        public g(MonthsPagerAdapter monthsPagerAdapter, MaterialButton materialButton) {
            this.f10278a = monthsPagerAdapter;
            this.b = materialButton;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            if (i == 0) {
                CharSequence text = this.b.getText();
                if (Build.VERSION.SDK_INT >= 16) {
                    recyclerView.announceForAccessibility(text);
                } else {
                    recyclerView.sendAccessibilityEvent(2048);
                }
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            int findLastVisibleItemPosition;
            if (i < 0) {
                findLastVisibleItemPosition = MaterialCalendar.this.q().findFirstVisibleItemPosition();
            } else {
                findLastVisibleItemPosition = MaterialCalendar.this.q().findLastVisibleItemPosition();
            }
            MaterialCalendar.this.k = this.f10278a.b(findLastVisibleItemPosition);
            this.b.setText(this.f10278a.c(findLastVisibleItemPosition));
        }
    }

    /* loaded from: classes10.dex */
    public class h implements View.OnClickListener {
        public h() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            MaterialCalendar.this.u();
        }
    }

    /* loaded from: classes10.dex */
    public class i implements View.OnClickListener {
        public final /* synthetic */ MonthsPagerAdapter h;

        public i(MonthsPagerAdapter monthsPagerAdapter) {
            this.h = monthsPagerAdapter;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int findFirstVisibleItemPosition = MaterialCalendar.this.q().findFirstVisibleItemPosition() + 1;
            if (findFirstVisibleItemPosition < MaterialCalendar.this.o.getAdapter().getItemCount()) {
                MaterialCalendar.this.s(this.h.b(findFirstVisibleItemPosition));
            }
        }
    }

    /* loaded from: classes10.dex */
    public class j implements View.OnClickListener {
        public final /* synthetic */ MonthsPagerAdapter h;

        public j(MonthsPagerAdapter monthsPagerAdapter) {
            this.h = monthsPagerAdapter;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int findLastVisibleItemPosition = MaterialCalendar.this.q().findLastVisibleItemPosition() - 1;
            if (findLastVisibleItemPosition >= 0) {
                MaterialCalendar.this.s(this.h.b(findLastVisibleItemPosition));
            }
        }
    }

    /* loaded from: classes10.dex */
    public enum k {
        DAY,
        YEAR
    }

    /* loaded from: classes10.dex */
    public interface l {
        void a(long j);
    }

    @NonNull
    public static <T> MaterialCalendar<T> newInstance(@NonNull DateSelector<T> dateSelector, @StyleRes int i2, @NonNull CalendarConstraints calendarConstraints) {
        MaterialCalendar<T> materialCalendar = new MaterialCalendar<>();
        Bundle bundle = new Bundle();
        bundle.putInt("THEME_RES_ID_KEY", i2);
        bundle.putParcelable("GRID_SELECTOR_KEY", dateSelector);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", calendarConstraints);
        bundle.putParcelable("CURRENT_MONTH_KEY", calendarConstraints.h());
        materialCalendar.setArguments(bundle);
        return materialCalendar;
    }

    @Px
    public static int o(@NonNull Context context) {
        return context.getResources().getDimensionPixelSize(R.dimen.mtrl_calendar_day_height);
    }

    public static int p(@NonNull Context context) {
        Resources resources = context.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_navigation_height) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_top_padding) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_navigation_bottom_padding);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.mtrl_calendar_days_of_week_height);
        int i2 = com.google.android.material.datepicker.f.m;
        return dimensionPixelSize + dimensionPixelSize2 + (resources.getDimensionPixelSize(R.dimen.mtrl_calendar_day_height) * i2) + ((i2 - 1) * resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_month_vertical_padding)) + resources.getDimensionPixelOffset(R.dimen.mtrl_calendar_bottom_padding);
    }

    @Override // com.google.android.material.datepicker.g
    public boolean addOnSelectionChangedListener(@NonNull OnSelectionChangedListener<S> onSelectionChangedListener) {
        return super.addOnSelectionChangedListener(onSelectionChangedListener);
    }

    @Nullable
    public DateSelector<S> getDateSelector() {
        return this.i;
    }

    public final void j(@NonNull View view, @NonNull MonthsPagerAdapter monthsPagerAdapter) {
        MaterialButton materialButton = (MaterialButton) view.findViewById(R.id.month_navigation_fragment_toggle);
        materialButton.setTag(u);
        ViewCompat.setAccessibilityDelegate(materialButton, new f());
        MaterialButton materialButton2 = (MaterialButton) view.findViewById(R.id.month_navigation_previous);
        materialButton2.setTag(s);
        MaterialButton materialButton3 = (MaterialButton) view.findViewById(R.id.month_navigation_next);
        materialButton3.setTag(t);
        this.p = view.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.q = view.findViewById(R.id.mtrl_calendar_day_selector_frame);
        t(k.DAY);
        materialButton.setText(this.k.h());
        this.o.addOnScrollListener(new g(monthsPagerAdapter, materialButton));
        materialButton.setOnClickListener(new h());
        materialButton3.setOnClickListener(new i(monthsPagerAdapter));
        materialButton2.setOnClickListener(new j(monthsPagerAdapter));
    }

    @NonNull
    public final RecyclerView.ItemDecoration k() {
        return new e();
    }

    @Nullable
    public CalendarConstraints l() {
        return this.j;
    }

    public com.google.android.material.datepicker.b m() {
        return this.m;
    }

    @Nullable
    public Month n() {
        return this.k;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null) {
            bundle = getArguments();
        }
        this.h = bundle.getInt("THEME_RES_ID_KEY");
        this.i = (DateSelector) bundle.getParcelable("GRID_SELECTOR_KEY");
        this.j = (CalendarConstraints) bundle.getParcelable("CALENDAR_CONSTRAINTS_KEY");
        this.k = (Month) bundle.getParcelable("CURRENT_MONTH_KEY");
    }

    @Override // androidx.fragment.app.Fragment
    @NonNull
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        int i2;
        int i3;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getContext(), this.h);
        this.m = new com.google.android.material.datepicker.b(contextThemeWrapper);
        LayoutInflater cloneInContext = layoutInflater.cloneInContext(contextThemeWrapper);
        Month i4 = this.j.i();
        if (MaterialDatePicker.n(contextThemeWrapper)) {
            i2 = R.layout.mtrl_calendar_vertical;
            i3 = 1;
        } else {
            i2 = R.layout.mtrl_calendar_horizontal;
            i3 = 0;
        }
        View inflate = cloneInContext.inflate(i2, viewGroup, false);
        inflate.setMinimumHeight(p(requireContext()));
        GridView gridView = (GridView) inflate.findViewById(R.id.mtrl_calendar_days_of_week);
        ViewCompat.setAccessibilityDelegate(gridView, new b(this));
        gridView.setAdapter((ListAdapter) new com.google.android.material.datepicker.e());
        gridView.setNumColumns(i4.k);
        gridView.setEnabled(false);
        this.o = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_months);
        this.o.setLayoutManager(new c(getContext(), i3, false, i3));
        this.o.setTag(r);
        MonthsPagerAdapter monthsPagerAdapter = new MonthsPagerAdapter(contextThemeWrapper, this.i, this.j, new d());
        this.o.setAdapter(monthsPagerAdapter);
        int integer = contextThemeWrapper.getResources().getInteger(R.integer.mtrl_calendar_year_selector_span);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.mtrl_calendar_year_selector_frame);
        this.n = recyclerView;
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            this.n.setLayoutManager(new GridLayoutManager((Context) contextThemeWrapper, integer, 1, false));
            this.n.setAdapter(new YearGridAdapter(this));
            this.n.addItemDecoration(k());
        }
        if (inflate.findViewById(R.id.month_navigation_fragment_toggle) != null) {
            j(inflate, monthsPagerAdapter);
        }
        if (!MaterialDatePicker.n(contextThemeWrapper)) {
            new PagerSnapHelper().attachToRecyclerView(this.o);
        }
        this.o.scrollToPosition(monthsPagerAdapter.d(this.k));
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("THEME_RES_ID_KEY", this.h);
        bundle.putParcelable("GRID_SELECTOR_KEY", this.i);
        bundle.putParcelable("CALENDAR_CONSTRAINTS_KEY", this.j);
        bundle.putParcelable("CURRENT_MONTH_KEY", this.k);
    }

    @NonNull
    public LinearLayoutManager q() {
        return (LinearLayoutManager) this.o.getLayoutManager();
    }

    public final void r(int i2) {
        this.o.post(new a(i2));
    }

    public void s(Month month) {
        MonthsPagerAdapter monthsPagerAdapter = (MonthsPagerAdapter) this.o.getAdapter();
        int d2 = monthsPagerAdapter.d(month);
        int d3 = d2 - monthsPagerAdapter.d(this.k);
        boolean z = Math.abs(d3) > 3;
        boolean z2 = d3 > 0;
        this.k = month;
        if (z && z2) {
            this.o.scrollToPosition(d2 - 3);
            r(d2);
        } else if (z) {
            this.o.scrollToPosition(d2 + 3);
            r(d2);
        } else {
            r(d2);
        }
    }

    public void t(k kVar) {
        this.l = kVar;
        if (kVar == k.YEAR) {
            this.n.getLayoutManager().scrollToPosition(((YearGridAdapter) this.n.getAdapter()).c(this.k.j));
            this.p.setVisibility(0);
            this.q.setVisibility(8);
        } else if (kVar == k.DAY) {
            this.p.setVisibility(8);
            this.q.setVisibility(0);
            s(this.k);
        }
    }

    public void u() {
        k kVar = this.l;
        k kVar2 = k.YEAR;
        if (kVar == kVar2) {
            t(k.DAY);
        } else if (kVar == k.DAY) {
            t(kVar2);
        }
    }
}
