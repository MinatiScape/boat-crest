package com.mappls.sdk.navigation.ui.navigation.infobar;

import com.mappls.sdk.navigation.NavigationApplication;
import com.mappls.sdk.navigation.NavigationFormatter;
import com.mappls.sdk.navigation.model.AdviseInfo;
import java.util.List;
/* loaded from: classes11.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    public AdviseInfo f13022a;
    public final String b;
    public final String c;
    public final String d;
    public List<String> e;
    public int f;
    public final double g;

    public d(AdviseInfo adviseInfo, List<String> list, double d, int i, NavigationApplication navigationApplication) {
        this.b = NavigationFormatter.getFormattedDistance(adviseInfo.getLeftDistance(), navigationApplication);
        this.d = NavigationFormatter.getFormattedDuration(adviseInfo.getLeftTime(), navigationApplication);
        this.g = d;
        this.c = "Arrival: " + adviseInfo.getEta();
        this.f13022a = adviseInfo;
        this.e = list;
        this.f = i;
    }

    public AdviseInfo a() {
        return this.f13022a;
    }

    public List<String> b() {
        return this.e;
    }

    public String c() {
        return this.c;
    }

    public int d() {
        return this.f;
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.d;
    }

    public double g() {
        return this.g;
    }
}
