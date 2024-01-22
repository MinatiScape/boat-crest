package com.mappls.sdk.navigation.ui.navigation.instructioncontainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.routing.NavigationStep;
import com.mappls.sdk.navigation.ui.R;
import com.mappls.sdk.navigation.ui.views.turnlane.TurnLaneAdapter;
import com.mappls.sdk.plugin.directions.view.ManeuverView;
import java.util.List;
/* loaded from: classes11.dex */
public class b extends PagerAdapter {
    public List<NavigationStep> c;
    public Context d;
    public LayoutInflater e;
    public int f = 1;
    public long g = 0;
    public NavigationApplication h;

    /* loaded from: classes11.dex */
    public class a {

        /* renamed from: a  reason: collision with root package name */
        public final RecyclerView f13024a;
        public ManeuverView b;
        public TextView c;
        public TextView d;
        public TextView e;
        public TextView f;
        public View g;
        public LinearLayout h;
        public TurnLaneAdapter i;
        public View j;

        public a(b bVar, View view) {
            this.b = (ManeuverView) view.findViewById(R.id.maneuver_image_view);
            this.c = (TextView) view.findViewById(R.id.maneuver_id_text_view);
            this.d = (TextView) view.findViewById(R.id.navigation_strip_text);
            this.e = (TextView) view.findViewById(R.id.navigation_strip_short_text);
            this.f = (TextView) view.findViewById(R.id.navigation_strip_dist);
            this.g = view.findViewById(R.id.strip_item_container);
            this.h = (LinearLayout) view.findViewById(R.id.repeat_current_instructions_layout);
            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rvTurnLanes);
            this.f13024a = recyclerView;
            this.j = view.findViewById(R.id.lane_guidance_container);
            TurnLaneAdapter turnLaneAdapter = new TurnLaneAdapter();
            this.i = turnLaneAdapter;
            recyclerView.setAdapter(turnLaneAdapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), 0, false));
        }
    }

    public b(Context context, List<NavigationStep> list, String str, NavigationApplication navigationApplication) {
        this.d = context;
        this.c = list;
        this.e = LayoutInflater.from(context);
        this.h = navigationApplication;
    }

    public static /* synthetic */ void c(View view) {
    }

    public void a(int i) {
        this.f = i;
        notifyDataSetChanged();
    }

    public void a(long j) {
        if (j > 0) {
            j = Math.round(j / 10.0d) * 10;
        }
        this.g = j;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        List<NavigationStep> list = this.c;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0109  */
    @Override // androidx.viewpager.widget.PagerAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object instantiateItem(android.view.ViewGroup r10, int r11) {
        /*
            Method dump skipped, instructions count: 563
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mappls.sdk.navigation.ui.navigation.instructioncontainer.b.instantiateItem(android.view.ViewGroup, int):java.lang.Object");
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
