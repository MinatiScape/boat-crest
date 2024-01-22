package com.mappls.sdk.maps.attribution;

import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes11.dex */
public class Attribution {
    public static final List<String> c;

    /* renamed from: a  reason: collision with root package name */
    public String f12673a;
    public String b;

    static {
        ArrayList arrayList = new ArrayList();
        c = arrayList;
        arrayList.add("https://www.mappls.com/feedback/");
        arrayList.add("https://www.mappls.com/map-feedback/");
        arrayList.add("https://apps.mappls.com/feedback/");
    }

    public Attribution(String str, String str2) {
        this.f12673a = str;
        this.b = str2;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attribution attribution = (Attribution) obj;
        String str = this.f12673a;
        if (str == null ? attribution.f12673a == null : str.equals(attribution.f12673a)) {
            String str2 = this.b;
            String str3 = attribution.b;
            return str2 != null ? str2.equals(str3) : str3 == null;
        }
        return false;
    }

    public String getTitle() {
        return this.f12673a;
    }

    public String getTitleAbbreviated() {
        return this.f12673a.equals("OpenStreetMap") ? "Open Street Map" : this.f12673a;
    }

    public String getUrl() {
        return this.b;
    }

    public int hashCode() {
        String str = this.f12673a;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.b;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }
}
