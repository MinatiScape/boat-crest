package androidx.core.content.pm;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ShortcutInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.core.app.Person;
import androidx.core.content.LocusIdCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.net.UriCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.paho.client.mqttv3.MqttTopic;
/* loaded from: classes.dex */
public class ShortcutInfoCompat {
    public static final int SURFACE_LAUNCHER = 1;
    public int A;
    public int B;

    /* renamed from: a  reason: collision with root package name */
    public Context f1013a;
    public String b;
    public String c;
    public Intent[] d;
    public ComponentName e;
    public CharSequence f;
    public CharSequence g;
    public CharSequence h;
    public IconCompat i;
    public boolean j;
    public Person[] k;
    public Set<String> l;
    @Nullable
    public LocusIdCompat m;
    public boolean n;
    public int o;
    public PersistableBundle p;
    public Bundle q;
    public long r;
    public UserHandle s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public boolean x;
    public boolean y = true;
    public boolean z;

    /* loaded from: classes.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public final ShortcutInfoCompat f1014a;
        public boolean b;
        public Set<String> c;
        public Map<String, Map<String, List<String>>> d;
        public Uri e;

        public Builder(@NonNull Context context, @NonNull String str) {
            ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
            this.f1014a = shortcutInfoCompat;
            shortcutInfoCompat.f1013a = context;
            shortcutInfoCompat.b = str;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder addCapabilityBinding(@NonNull String str) {
            if (this.c == null) {
                this.c = new HashSet();
            }
            this.c.add(str);
            return this;
        }

        @NonNull
        public ShortcutInfoCompat build() {
            if (!TextUtils.isEmpty(this.f1014a.f)) {
                ShortcutInfoCompat shortcutInfoCompat = this.f1014a;
                Intent[] intentArr = shortcutInfoCompat.d;
                if (intentArr != null && intentArr.length != 0) {
                    if (this.b) {
                        if (shortcutInfoCompat.m == null) {
                            shortcutInfoCompat.m = new LocusIdCompat(shortcutInfoCompat.b);
                        }
                        this.f1014a.n = true;
                    }
                    if (this.c != null) {
                        ShortcutInfoCompat shortcutInfoCompat2 = this.f1014a;
                        if (shortcutInfoCompat2.l == null) {
                            shortcutInfoCompat2.l = new HashSet();
                        }
                        this.f1014a.l.addAll(this.c);
                    }
                    if (Build.VERSION.SDK_INT >= 21) {
                        if (this.d != null) {
                            ShortcutInfoCompat shortcutInfoCompat3 = this.f1014a;
                            if (shortcutInfoCompat3.p == null) {
                                shortcutInfoCompat3.p = new PersistableBundle();
                            }
                            for (String str : this.d.keySet()) {
                                Map<String, List<String>> map = this.d.get(str);
                                this.f1014a.p.putStringArray(str, (String[]) map.keySet().toArray(new String[0]));
                                for (String str2 : map.keySet()) {
                                    List<String> list = map.get(str2);
                                    PersistableBundle persistableBundle = this.f1014a.p;
                                    persistableBundle.putStringArray(str + MqttTopic.TOPIC_LEVEL_SEPARATOR + str2, list == null ? new String[0] : (String[]) list.toArray(new String[0]));
                                }
                            }
                        }
                        if (this.e != null) {
                            ShortcutInfoCompat shortcutInfoCompat4 = this.f1014a;
                            if (shortcutInfoCompat4.p == null) {
                                shortcutInfoCompat4.p = new PersistableBundle();
                            }
                            this.f1014a.p.putString("extraSliceUri", UriCompat.toSafeString(this.e));
                        }
                    }
                    return this.f1014a;
                }
                throw new IllegalArgumentException("Shortcut must have an intent");
            }
            throw new IllegalArgumentException("Shortcut must have a non-empty label");
        }

        @NonNull
        public Builder setActivity(@NonNull ComponentName componentName) {
            this.f1014a.e = componentName;
            return this;
        }

        @NonNull
        public Builder setAlwaysBadged() {
            this.f1014a.j = true;
            return this;
        }

        @NonNull
        public Builder setCategories(@NonNull Set<String> set) {
            this.f1014a.l = set;
            return this;
        }

        @NonNull
        public Builder setDisabledMessage(@NonNull CharSequence charSequence) {
            this.f1014a.h = charSequence;
            return this;
        }

        @NonNull
        public Builder setExcludedFromSurfaces(int i) {
            this.f1014a.B = i;
            return this;
        }

        @NonNull
        public Builder setExtras(@NonNull PersistableBundle persistableBundle) {
            this.f1014a.p = persistableBundle;
            return this;
        }

        @NonNull
        public Builder setIcon(IconCompat iconCompat) {
            this.f1014a.i = iconCompat;
            return this;
        }

        @NonNull
        public Builder setIntent(@NonNull Intent intent) {
            return setIntents(new Intent[]{intent});
        }

        @NonNull
        public Builder setIntents(@NonNull Intent[] intentArr) {
            this.f1014a.d = intentArr;
            return this;
        }

        @NonNull
        public Builder setIsConversation() {
            this.b = true;
            return this;
        }

        @NonNull
        public Builder setLocusId(@Nullable LocusIdCompat locusIdCompat) {
            this.f1014a.m = locusIdCompat;
            return this;
        }

        @NonNull
        public Builder setLongLabel(@NonNull CharSequence charSequence) {
            this.f1014a.g = charSequence;
            return this;
        }

        @NonNull
        @Deprecated
        public Builder setLongLived() {
            this.f1014a.n = true;
            return this;
        }

        @NonNull
        public Builder setPerson(@NonNull Person person) {
            return setPersons(new Person[]{person});
        }

        @NonNull
        public Builder setPersons(@NonNull Person[] personArr) {
            this.f1014a.k = personArr;
            return this;
        }

        @NonNull
        public Builder setRank(int i) {
            this.f1014a.o = i;
            return this;
        }

        @NonNull
        public Builder setShortLabel(@NonNull CharSequence charSequence) {
            this.f1014a.f = charSequence;
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder setSliceUri(@NonNull Uri uri) {
            this.e = uri;
            return this;
        }

        @NonNull
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder setTransientExtras(@NonNull Bundle bundle) {
            this.f1014a.q = (Bundle) Preconditions.checkNotNull(bundle);
            return this;
        }

        @NonNull
        public Builder setLongLived(boolean z) {
            this.f1014a.n = z;
            return this;
        }

        @NonNull
        @SuppressLint({"MissingGetterMatchingBuilder"})
        public Builder addCapabilityBinding(@NonNull String str, @NonNull String str2, @NonNull List<String> list) {
            addCapabilityBinding(str);
            if (!list.isEmpty()) {
                if (this.d == null) {
                    this.d = new HashMap();
                }
                if (this.d.get(str) == null) {
                    this.d.put(str, new HashMap());
                }
                this.d.get(str).put(str2, list);
            }
            return this;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull ShortcutInfoCompat shortcutInfoCompat) {
            ShortcutInfoCompat shortcutInfoCompat2 = new ShortcutInfoCompat();
            this.f1014a = shortcutInfoCompat2;
            shortcutInfoCompat2.f1013a = shortcutInfoCompat.f1013a;
            shortcutInfoCompat2.b = shortcutInfoCompat.b;
            shortcutInfoCompat2.c = shortcutInfoCompat.c;
            Intent[] intentArr = shortcutInfoCompat.d;
            shortcutInfoCompat2.d = (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
            shortcutInfoCompat2.e = shortcutInfoCompat.e;
            shortcutInfoCompat2.f = shortcutInfoCompat.f;
            shortcutInfoCompat2.g = shortcutInfoCompat.g;
            shortcutInfoCompat2.h = shortcutInfoCompat.h;
            shortcutInfoCompat2.A = shortcutInfoCompat.A;
            shortcutInfoCompat2.i = shortcutInfoCompat.i;
            shortcutInfoCompat2.j = shortcutInfoCompat.j;
            shortcutInfoCompat2.s = shortcutInfoCompat.s;
            shortcutInfoCompat2.r = shortcutInfoCompat.r;
            shortcutInfoCompat2.t = shortcutInfoCompat.t;
            shortcutInfoCompat2.u = shortcutInfoCompat.u;
            shortcutInfoCompat2.v = shortcutInfoCompat.v;
            shortcutInfoCompat2.w = shortcutInfoCompat.w;
            shortcutInfoCompat2.x = shortcutInfoCompat.x;
            shortcutInfoCompat2.y = shortcutInfoCompat.y;
            shortcutInfoCompat2.m = shortcutInfoCompat.m;
            shortcutInfoCompat2.n = shortcutInfoCompat.n;
            shortcutInfoCompat2.z = shortcutInfoCompat.z;
            shortcutInfoCompat2.o = shortcutInfoCompat.o;
            Person[] personArr = shortcutInfoCompat.k;
            if (personArr != null) {
                shortcutInfoCompat2.k = (Person[]) Arrays.copyOf(personArr, personArr.length);
            }
            if (shortcutInfoCompat.l != null) {
                shortcutInfoCompat2.l = new HashSet(shortcutInfoCompat.l);
            }
            PersistableBundle persistableBundle = shortcutInfoCompat.p;
            if (persistableBundle != null) {
                shortcutInfoCompat2.p = persistableBundle;
            }
            shortcutInfoCompat2.B = shortcutInfoCompat.B;
        }

        @RequiresApi(25)
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public Builder(@NonNull Context context, @NonNull ShortcutInfo shortcutInfo) {
            ShortcutInfoCompat shortcutInfoCompat = new ShortcutInfoCompat();
            this.f1014a = shortcutInfoCompat;
            shortcutInfoCompat.f1013a = context;
            shortcutInfoCompat.b = shortcutInfo.getId();
            shortcutInfoCompat.c = shortcutInfo.getPackage();
            Intent[] intents = shortcutInfo.getIntents();
            shortcutInfoCompat.d = (Intent[]) Arrays.copyOf(intents, intents.length);
            shortcutInfoCompat.e = shortcutInfo.getActivity();
            shortcutInfoCompat.f = shortcutInfo.getShortLabel();
            shortcutInfoCompat.g = shortcutInfo.getLongLabel();
            shortcutInfoCompat.h = shortcutInfo.getDisabledMessage();
            int i = Build.VERSION.SDK_INT;
            if (i >= 28) {
                shortcutInfoCompat.A = shortcutInfo.getDisabledReason();
            } else {
                shortcutInfoCompat.A = shortcutInfo.isEnabled() ? 0 : 3;
            }
            shortcutInfoCompat.l = shortcutInfo.getCategories();
            shortcutInfoCompat.k = ShortcutInfoCompat.f(shortcutInfo.getExtras());
            shortcutInfoCompat.s = shortcutInfo.getUserHandle();
            shortcutInfoCompat.r = shortcutInfo.getLastChangedTimestamp();
            if (i >= 30) {
                shortcutInfoCompat.t = shortcutInfo.isCached();
            }
            shortcutInfoCompat.u = shortcutInfo.isDynamic();
            shortcutInfoCompat.v = shortcutInfo.isPinned();
            shortcutInfoCompat.w = shortcutInfo.isDeclaredInManifest();
            shortcutInfoCompat.x = shortcutInfo.isImmutable();
            shortcutInfoCompat.y = shortcutInfo.isEnabled();
            shortcutInfoCompat.z = shortcutInfo.hasKeyFieldsOnly();
            shortcutInfoCompat.m = ShortcutInfoCompat.d(shortcutInfo);
            shortcutInfoCompat.o = shortcutInfo.getRank();
            shortcutInfoCompat.p = shortcutInfo.getExtras();
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    /* loaded from: classes.dex */
    public @interface Surface {
    }

    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static List<ShortcutInfoCompat> c(@NonNull Context context, @NonNull List<ShortcutInfo> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ShortcutInfo shortcutInfo : list) {
            arrayList.add(new Builder(context, shortcutInfo).build());
        }
        return arrayList;
    }

    @Nullable
    @RequiresApi(25)
    public static LocusIdCompat d(@NonNull ShortcutInfo shortcutInfo) {
        if (Build.VERSION.SDK_INT >= 29) {
            if (shortcutInfo.getLocusId() == null) {
                return null;
            }
            return LocusIdCompat.toLocusIdCompat(shortcutInfo.getLocusId());
        }
        return e(shortcutInfo.getExtras());
    }

    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static LocusIdCompat e(@Nullable PersistableBundle persistableBundle) {
        String string;
        if (persistableBundle == null || (string = persistableBundle.getString("extraLocusId")) == null) {
            return null;
        }
        return new LocusIdCompat(string);
    }

    @VisibleForTesting
    @Nullable
    @RequiresApi(25)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static Person[] f(@NonNull PersistableBundle persistableBundle) {
        if (persistableBundle == null || !persistableBundle.containsKey("extraPersonCount")) {
            return null;
        }
        int i = persistableBundle.getInt("extraPersonCount");
        Person[] personArr = new Person[i];
        int i2 = 0;
        while (i2 < i) {
            StringBuilder sb = new StringBuilder();
            sb.append("extraPerson_");
            int i3 = i2 + 1;
            sb.append(i3);
            personArr[i2] = Person.fromPersistableBundle(persistableBundle.getPersistableBundle(sb.toString()));
            i2 = i3;
        }
        return personArr;
    }

    public Intent a(Intent intent) {
        Intent[] intentArr = this.d;
        intent.putExtra("android.intent.extra.shortcut.INTENT", intentArr[intentArr.length - 1]).putExtra("android.intent.extra.shortcut.NAME", this.f.toString());
        if (this.i != null) {
            Drawable drawable = null;
            if (this.j) {
                PackageManager packageManager = this.f1013a.getPackageManager();
                ComponentName componentName = this.e;
                if (componentName != null) {
                    try {
                        drawable = packageManager.getActivityIcon(componentName);
                    } catch (PackageManager.NameNotFoundException unused) {
                    }
                }
                if (drawable == null) {
                    drawable = this.f1013a.getApplicationInfo().loadIcon(packageManager);
                }
            }
            this.i.addToShortcutIntent(intent, drawable, this.f1013a);
        }
        return intent;
    }

    @RequiresApi(22)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public final PersistableBundle b() {
        if (this.p == null) {
            this.p = new PersistableBundle();
        }
        Person[] personArr = this.k;
        if (personArr != null && personArr.length > 0) {
            this.p.putInt("extraPersonCount", personArr.length);
            int i = 0;
            while (i < this.k.length) {
                PersistableBundle persistableBundle = this.p;
                StringBuilder sb = new StringBuilder();
                sb.append("extraPerson_");
                int i2 = i + 1;
                sb.append(i2);
                persistableBundle.putPersistableBundle(sb.toString(), this.k[i].toPersistableBundle());
                i = i2;
            }
        }
        LocusIdCompat locusIdCompat = this.m;
        if (locusIdCompat != null) {
            this.p.putString("extraLocusId", locusIdCompat.getId());
        }
        this.p.putBoolean("extraLongLived", this.n);
        return this.p;
    }

    @Nullable
    public ComponentName getActivity() {
        return this.e;
    }

    @Nullable
    public Set<String> getCategories() {
        return this.l;
    }

    @Nullable
    public CharSequence getDisabledMessage() {
        return this.h;
    }

    public int getDisabledReason() {
        return this.A;
    }

    public int getExcludedFromSurfaces() {
        return this.B;
    }

    @Nullable
    public PersistableBundle getExtras() {
        return this.p;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public IconCompat getIcon() {
        return this.i;
    }

    @NonNull
    public String getId() {
        return this.b;
    }

    @NonNull
    public Intent getIntent() {
        Intent[] intentArr = this.d;
        return intentArr[intentArr.length - 1];
    }

    @NonNull
    public Intent[] getIntents() {
        Intent[] intentArr = this.d;
        return (Intent[]) Arrays.copyOf(intentArr, intentArr.length);
    }

    public long getLastChangedTimestamp() {
        return this.r;
    }

    @Nullable
    public LocusIdCompat getLocusId() {
        return this.m;
    }

    @Nullable
    public CharSequence getLongLabel() {
        return this.g;
    }

    @NonNull
    public String getPackage() {
        return this.c;
    }

    public int getRank() {
        return this.o;
    }

    @NonNull
    public CharSequence getShortLabel() {
        return this.f;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public Bundle getTransientExtras() {
        return this.q;
    }

    @Nullable
    public UserHandle getUserHandle() {
        return this.s;
    }

    public boolean hasKeyFieldsOnly() {
        return this.z;
    }

    public boolean isCached() {
        return this.t;
    }

    public boolean isDeclaredInManifest() {
        return this.w;
    }

    public boolean isDynamic() {
        return this.u;
    }

    public boolean isEnabled() {
        return this.y;
    }

    public boolean isExcludedFromSurfaces(int i) {
        return (i & this.B) != 0;
    }

    public boolean isImmutable() {
        return this.x;
    }

    public boolean isPinned() {
        return this.v;
    }

    @RequiresApi(25)
    public ShortcutInfo toShortcutInfo() {
        ShortcutInfo.Builder intents = new ShortcutInfo.Builder(this.f1013a, this.b).setShortLabel(this.f).setIntents(this.d);
        IconCompat iconCompat = this.i;
        if (iconCompat != null) {
            intents.setIcon(iconCompat.toIcon(this.f1013a));
        }
        if (!TextUtils.isEmpty(this.g)) {
            intents.setLongLabel(this.g);
        }
        if (!TextUtils.isEmpty(this.h)) {
            intents.setDisabledMessage(this.h);
        }
        ComponentName componentName = this.e;
        if (componentName != null) {
            intents.setActivity(componentName);
        }
        Set<String> set = this.l;
        if (set != null) {
            intents.setCategories(set);
        }
        intents.setRank(this.o);
        PersistableBundle persistableBundle = this.p;
        if (persistableBundle != null) {
            intents.setExtras(persistableBundle);
        }
        if (Build.VERSION.SDK_INT >= 29) {
            Person[] personArr = this.k;
            if (personArr != null && personArr.length > 0) {
                int length = personArr.length;
                android.app.Person[] personArr2 = new android.app.Person[length];
                for (int i = 0; i < length; i++) {
                    personArr2[i] = this.k[i].toAndroidPerson();
                }
                intents.setPersons(personArr2);
            }
            LocusIdCompat locusIdCompat = this.m;
            if (locusIdCompat != null) {
                intents.setLocusId(locusIdCompat.toLocusId());
            }
            intents.setLongLived(this.n);
        } else {
            intents.setExtras(b());
        }
        return intents.build();
    }
}
