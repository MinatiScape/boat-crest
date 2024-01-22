package androidx.core.os;

import android.os.LocaleList;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.Locale;
@RequiresApi(24)
/* loaded from: classes.dex */
public final class f implements e {

    /* renamed from: a  reason: collision with root package name */
    public final LocaleList f1080a;

    public f(Object obj) {
        this.f1080a = (LocaleList) obj;
    }

    @Override // androidx.core.os.e
    public int a(Locale locale) {
        return this.f1080a.indexOf(locale);
    }

    @Override // androidx.core.os.e
    public String b() {
        return this.f1080a.toLanguageTags();
    }

    @Override // androidx.core.os.e
    public Object c() {
        return this.f1080a;
    }

    @Override // androidx.core.os.e
    @Nullable
    public Locale d(@NonNull String[] strArr) {
        return this.f1080a.getFirstMatch(strArr);
    }

    public boolean equals(Object obj) {
        return this.f1080a.equals(((e) obj).c());
    }

    @Override // androidx.core.os.e
    public Locale get(int i) {
        return this.f1080a.get(i);
    }

    public int hashCode() {
        return this.f1080a.hashCode();
    }

    @Override // androidx.core.os.e
    public boolean isEmpty() {
        return this.f1080a.isEmpty();
    }

    @Override // androidx.core.os.e
    public int size() {
        return this.f1080a.size();
    }

    public String toString() {
        return this.f1080a.toString();
    }
}
