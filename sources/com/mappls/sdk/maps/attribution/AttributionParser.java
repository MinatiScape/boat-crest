package com.mappls.sdk.maps.attribution;

import android.content.Context;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;
import java.util.LinkedHashSet;
import java.util.Set;
/* loaded from: classes11.dex */
public class AttributionParser {

    /* renamed from: a  reason: collision with root package name */
    public final Set<Attribution> f12678a = new LinkedHashSet();
    public final boolean b;

    /* loaded from: classes11.dex */
    public static class Options {

        /* renamed from: a  reason: collision with root package name */
        public final WeakReference<Context> f12679a;
        public boolean b = true;
        public boolean c = true;
        public boolean d = true;
        public String[] e;

        public Options(@NonNull Context context) {
            this.f12679a = new WeakReference<>(context);
        }

        public final String a(String[] strArr) {
            StringBuilder sb = new StringBuilder();
            for (String str : strArr) {
                if (!str.isEmpty()) {
                    sb.append(str);
                }
            }
            return sb.toString();
        }

        @NonNull
        public AttributionParser build() {
            String[] strArr = this.e;
            if (strArr != null) {
                AttributionParser attributionParser = new AttributionParser(this.f12679a, a(strArr), this.b, this.c, this.d);
                attributionParser.parse();
                return attributionParser;
            }
            throw new IllegalStateException("Using builder without providing attribution data");
        }

        @NonNull
        public Options withAttributionData(String... strArr) {
            this.e = strArr;
            return this;
        }

        @NonNull
        public Options withCopyrightSign(boolean z) {
            this.c = z;
            return this;
        }

        @NonNull
        public Options withImproveMap(boolean z) {
            this.b = z;
            return this;
        }

        @NonNull
        public Options withMapplsAttribution(boolean z) {
            this.d = z;
            return this;
        }
    }

    public AttributionParser(WeakReference<Context> weakReference, String str, boolean z, boolean z2, boolean z3) {
        this.b = z2;
    }

    public final void a() {
        this.f12678a.add(new Attribution("@OpenStreetMap", ""));
    }

    @NonNull
    public String createAttributionString() {
        return createAttributionString(false);
    }

    @NonNull
    public Set<Attribution> getAttributions() {
        return this.f12678a;
    }

    public void parse() {
        a();
    }

    @NonNull
    public String createAttributionString(boolean z) {
        StringBuilder sb = new StringBuilder(this.b ? "" : "© ");
        int i = 0;
        for (Attribution attribution : this.f12678a) {
            i++;
            sb.append(!z ? attribution.getTitle() : attribution.getTitleAbbreviated());
            if (i != this.f12678a.size()) {
                sb.append(" / ");
            }
        }
        return sb.toString();
    }
}
