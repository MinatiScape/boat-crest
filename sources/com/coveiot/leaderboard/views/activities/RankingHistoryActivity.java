package com.coveiot.leaderboard.views.activities;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import com.clevertap.android.sdk.Constants;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.leaderboard.model.FilterEvent;
import com.coveiot.leaderboard.utils.RankingType;
import com.coveiot.leaderboard.views.fragment.DailyRankingFragment;
import com.coveiot.leaderboard.views.fragment.MonthlyRankingFragment;
import com.coveiot.leaderboard.views.fragment.WeeklyRankingFragment;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class RankingHistoryActivity extends BaseActivity {
    public TabLayout p;
    public ViewPager q;
    public TextView r;
    public TextView s;
    public TextView t;
    public TextView u;
    public TextView v;
    public RankingType w;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingHistoryActivity.this.onDaySelected();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingHistoryActivity.this.onWeekSelected();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingHistoryActivity.this.onMonthSelected();
        }
    }

    /* loaded from: classes9.dex */
    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingHistoryActivity.this.onSortByClick();
        }
    }

    /* loaded from: classes9.dex */
    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingHistoryActivity.this.onFilterClick();
        }
    }

    /* loaded from: classes9.dex */
    public class f implements View.OnClickListener {
        public final /* synthetic */ Dialog h;

        public f(RankingHistoryActivity rankingHistoryActivity, Dialog dialog) {
            this.h = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class g implements View.OnClickListener {
        public final /* synthetic */ Dialog h;

        public g(RankingHistoryActivity rankingHistoryActivity, Dialog dialog) {
            this.h = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class h implements View.OnClickListener {
        public final /* synthetic */ RadioGroup h;
        public final /* synthetic */ Dialog i;

        public h(RadioGroup radioGroup, Dialog dialog) {
            this.h = radioGroup;
            this.i = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RadioButton radioButton = (RadioButton) this.i.findViewById(this.h.getCheckedRadioButtonId());
            int id = radioButton.getId();
            if (id == R.id.locality) {
                CloveEventBusManager.getInstance().getEventBus().post(new FilterEvent(RankType.DAY, FilterType.LOCALITY));
            } else if (id == R.id.city) {
                CloveEventBusManager.getInstance().getEventBus().post(new FilterEvent(RankType.DAY, FilterType.CITY));
            } else if (id == R.id.global) {
                CloveEventBusManager.getInstance().getEventBus().post(new FilterEvent(RankType.DAY, FilterType.COUNTRY));
            }
            Toast.makeText(RankingHistoryActivity.this, radioButton.getText().toString(), 0).show();
            this.i.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class i implements View.OnClickListener {
        public final /* synthetic */ Dialog h;

        public i(RankingHistoryActivity rankingHistoryActivity, Dialog dialog) {
            this.h = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class j extends FragmentPagerAdapter {
        public final List<Fragment> h;
        public final List<String> i;

        public j(RankingHistoryActivity rankingHistoryActivity, FragmentManager fragmentManager) {
            super(fragmentManager);
            this.h = new ArrayList();
            this.i = new ArrayList();
        }

        public void addFragment(Fragment fragment, String str) {
            this.h.add(fragment);
            this.i.add(str);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public int getCount() {
            return this.h.size();
        }

        @Override // androidx.fragment.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            return this.h.get(i);
        }

        @Override // androidx.viewpager.widget.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return this.i.get(i);
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_ranking);
        q();
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        s(this.q);
        this.p.setupWithViewPager(this.q);
        this.p.setTabTextColors(Color.parseColor(Constants.BLACK), Color.parseColor("#ffffff"));
        onDaySelected();
    }

    public void onDaySelected() {
        this.w = RankingType.DAY;
        this.r.setBackgroundResource(R.drawable.ranking_day_selected);
        this.r.setTextColor(getResources().getColor(R.color.text_color_light));
        this.s.setBackgroundResource(0);
        TextView textView = this.s;
        Resources resources = getResources();
        int i2 = R.color.black_color;
        textView.setTextColor(resources.getColor(i2));
        this.t.setBackgroundResource(0);
        this.t.setTextColor(getResources().getColor(i2));
        r(this.w);
    }

    public void onFilterClick() {
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.getWindow().requestFeature(1);
        dialog.setContentView(R.layout.dialog_filter_by);
        ((TextView) dialog.findViewById(R.id.apply)).setOnClickListener(new h((RadioGroup) dialog.findViewById(R.id.radioGroup), dialog));
        ((ImageView) dialog.findViewById(R.id.close)).setOnClickListener(new i(this, dialog));
        dialog.show();
    }

    public void onMonthSelected() {
        this.w = RankingType.MONTH;
        this.r.setBackgroundResource(0);
        TextView textView = this.r;
        Resources resources = getResources();
        int i2 = R.color.black_color;
        textView.setTextColor(resources.getColor(i2));
        this.s.setBackgroundResource(0);
        this.s.setTextColor(getResources().getColor(i2));
        this.t.setBackgroundResource(R.drawable.ranking_month_selected);
        this.t.setTextColor(getResources().getColor(R.color.white_color));
        r(this.w);
    }

    public void onSortByClick() {
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.getWindow().requestFeature(1);
        dialog.setContentView(R.layout.dialog_sort_by);
        ((TextView) dialog.findViewById(R.id.apply)).setOnClickListener(new f(this, dialog));
        ((ImageView) dialog.findViewById(R.id.close)).setOnClickListener(new g(this, dialog));
        dialog.show();
    }

    public void onWeekSelected() {
        this.w = RankingType.WEEK;
        this.r.setBackgroundResource(0);
        TextView textView = this.r;
        Resources resources = getResources();
        int i2 = R.color.black_color;
        textView.setTextColor(resources.getColor(i2));
        this.s.setBackgroundResource(R.drawable.ranking_week_selected);
        this.s.setTextColor(getResources().getColor(R.color.white_color));
        this.t.setBackgroundResource(0);
        this.t.setTextColor(getResources().getColor(i2));
        r(this.w);
    }

    public final void q() {
        this.p = (TabLayout) findViewById(R.id.tabs);
        this.q = (ViewPager) findViewById(R.id.viewpager);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fragmentContainer);
        this.r = (TextView) findViewById(R.id.days_tv);
        this.s = (TextView) findViewById(R.id.weeks_tv);
        this.t = (TextView) findViewById(R.id.months_tv);
        this.v = (TextView) findViewById(R.id.filterBy);
        this.u = (TextView) findViewById(R.id.sortBy);
        this.r.setOnClickListener(new a());
        this.s.setOnClickListener(new b());
        this.t.setOnClickListener(new c());
        this.u.setOnClickListener(new d());
        this.v.setOnClickListener(new e());
    }

    public final void r(RankingType rankingType) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        if (rankingType == RankingType.DAY) {
            beginTransaction.replace(R.id.fragmentContainer, new DailyRankingFragment());
            beginTransaction.commit();
        } else if (rankingType == RankingType.WEEK) {
            beginTransaction.replace(R.id.fragmentContainer, new WeeklyRankingFragment());
            beginTransaction.commit();
        } else if (rankingType == RankingType.MONTH) {
            beginTransaction.replace(R.id.fragmentContainer, new MonthlyRankingFragment());
            beginTransaction.commit();
        }
    }

    public final void s(ViewPager viewPager) {
        j jVar = new j(this, getSupportFragmentManager());
        jVar.addFragment(new DailyRankingFragment(), "Day");
        jVar.addFragment(new WeeklyRankingFragment(), "Week");
        jVar.addFragment(new MonthlyRankingFragment(), "Month");
        viewPager.setAdapter(jVar);
    }
}
