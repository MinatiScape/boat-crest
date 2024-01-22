package com.coveiot.leaderboard.views.activities;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.coveaccess.leaderboard.model.FilterType;
import com.coveiot.coveaccess.leaderboard.model.RankType;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.eventbus.CloveEventBusManager;
import com.coveiot.leaderboard.model.FilterEvent;
import com.squareup.otto.Bus;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class RankingActivity extends BaseActivity implements AdapterView.OnItemSelectedListener {
    public Spinner q;
    public ImageView r;
    public FilterType s;
    public ConstraintLayout t;
    public RankType p = RankType.DAY;
    public int u = 0;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingActivity.this.onBackPressed();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            RankingActivity.this.onFilterClick();
        }
    }

    /* loaded from: classes9.dex */
    public class c extends ArrayAdapter<String> {
        public c(Context context, int i, List list) {
            super(context, i, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int i, @Nullable View view, @NonNull ViewGroup viewGroup) {
            TextView textView = (TextView) super.getDropDownView(i, view, viewGroup);
            textView.setTextColor(RankingActivity.this.getResources().getColor(R.color.secondary_text_color));
            if (i == RankingActivity.this.u) {
                textView.setTextColor(RankingActivity.this.getResources().getColor(R.color.colorPrimary));
            }
            return textView;
        }
    }

    /* loaded from: classes9.dex */
    public class d implements View.OnClickListener {
        public final /* synthetic */ RadioGroup h;
        public final /* synthetic */ Dialog i;

        public d(RadioGroup radioGroup, Dialog dialog) {
            this.h = radioGroup;
            this.i = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int id = ((RadioButton) this.i.findViewById(this.h.getCheckedRadioButtonId())).getId();
            if (id == R.id.locality) {
                Bus eventBus = CloveEventBusManager.getInstance().getEventBus();
                RankType rankType = RankingActivity.this.p;
                FilterType filterType = FilterType.LOCALITY;
                eventBus.post(new FilterEvent(rankType, filterType));
                RankingActivity.this.s = filterType;
            } else if (id == R.id.city) {
                Bus eventBus2 = CloveEventBusManager.getInstance().getEventBus();
                RankType rankType2 = RankingActivity.this.p;
                FilterType filterType2 = FilterType.CITY;
                eventBus2.post(new FilterEvent(rankType2, filterType2));
                RankingActivity.this.s = filterType2;
            } else if (id == R.id.global) {
                Bus eventBus3 = CloveEventBusManager.getInstance().getEventBus();
                RankType rankType3 = RankingActivity.this.p;
                FilterType filterType3 = FilterType.COUNTRY;
                eventBus3.post(new FilterEvent(rankType3, filterType3));
                RankingActivity.this.s = filterType3;
            } else if (id == R.id.state) {
                Bus eventBus4 = CloveEventBusManager.getInstance().getEventBus();
                RankType rankType4 = RankingActivity.this.p;
                FilterType filterType4 = FilterType.STATE;
                eventBus4.post(new FilterEvent(rankType4, filterType4));
                RankingActivity.this.s = filterType4;
            } else if (id == R.id.country) {
                Bus eventBus5 = CloveEventBusManager.getInstance().getEventBus();
                RankType rankType5 = RankingActivity.this.p;
                FilterType filterType5 = FilterType.COUNTRY;
                eventBus5.post(new FilterEvent(rankType5, filterType5));
                RankingActivity.this.s = filterType5;
            }
            this.i.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class e implements View.OnClickListener {
        public final /* synthetic */ Dialog h;

        public e(RankingActivity rankingActivity, Dialog dialog) {
            this.h = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public static /* synthetic */ class f {

        /* renamed from: a  reason: collision with root package name */
        public static final /* synthetic */ int[] f7223a;

        static {
            int[] iArr = new int[FilterType.values().length];
            f7223a = iArr;
            try {
                iArr[FilterType.LOCALITY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f7223a[FilterType.CITY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f7223a[FilterType.STATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f7223a[FilterType.COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ranking);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.toolbar);
        this.t = constraintLayout;
        ((TextView) constraintLayout.findViewById(R.id.toolbar_title)).setText(R.string.my_ranking);
        ImageView imageView = (ImageView) this.t.findViewById(R.id.share_iv);
        this.r = imageView;
        imageView.setVisibility(0);
        this.r.setImageResource(R.drawable.ic_soccer_filter);
        this.r.setColorFilter(ContextCompat.getColor(this, R.color.main_text_color));
        this.t.setOnClickListener(new a());
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        this.q = spinner;
        spinner.setOnItemSelectedListener(this);
        this.r.setOnClickListener(new b());
        ArrayList arrayList = new ArrayList();
        arrayList.add("Daily");
        arrayList.add("Weekly");
        arrayList.add("Monthly");
        c cVar = new c(this, R.layout.spinner_item, arrayList);
        this.q.setAdapter((SpinnerAdapter) cVar);
        cVar.setDropDownViewResource(R.layout.spinner_dropdown_item);
    }

    public void onFilterClick() {
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        dialog.requestWindowFeature(1);
        dialog.setContentView(R.layout.dialog_filter_by);
        dialog.setCanceledOnTouchOutside(false);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 17;
        TextView textView = (TextView) dialog.findViewById(R.id.apply);
        ImageView imageView = (ImageView) dialog.findViewById(R.id.close);
        RadioGroup radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
        RadioButton radioButton = (RadioButton) dialog.findViewById(R.id.locality);
        RadioButton radioButton2 = (RadioButton) dialog.findViewById(R.id.city);
        RadioButton radioButton3 = (RadioButton) dialog.findViewById(R.id.state);
        RadioButton radioButton4 = (RadioButton) dialog.findViewById(R.id.country);
        FilterType filterType = this.s;
        if (filterType != null) {
            int i = f.f7223a[filterType.ordinal()];
            if (i == 1) {
                radioButton.setChecked(true);
            } else if (i == 2) {
                radioButton2.setChecked(true);
            } else if (i == 3) {
                radioButton3.setChecked(true);
            } else if (i == 4) {
                radioButton4.setChecked(true);
            }
        } else {
            radioButton2.setChecked(true);
        }
        textView.setOnClickListener(new d(radioGroup, dialog));
        imageView.setOnClickListener(new e(this, dialog));
        dialog.show();
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
        FilterType filterType = FilterType.CITY;
        this.s = filterType;
        this.u = i;
        if (i == 0) {
            this.p = RankType.DAY;
            CloveEventBusManager.getInstance().getEventBus().post(new FilterEvent(this.p, filterType));
        } else if (i == 1) {
            this.p = RankType.WEEK;
            CloveEventBusManager.getInstance().getEventBus().post(new FilterEvent(this.p, filterType));
        } else if (i != 2) {
        } else {
            this.p = RankType.MONTH;
            CloveEventBusManager.getInstance().getEventBus().post(new FilterEvent(this.p, filterType));
        }
    }

    @Override // android.widget.AdapterView.OnItemSelectedListener
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override // com.coveiot.android.theme.BaseActivity, android.app.Activity
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            super.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
