package androidx.navigation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import androidx.annotation.CallSuper;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.SparseArrayCompat;
import androidx.navigation.NavDeepLink;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
/* loaded from: classes.dex */
public class NavDestination {
    public static final HashMap<String, Class<?>> p = new HashMap<>();
    public final String h;
    public NavGraph i;
    public int j;
    public String k;
    public CharSequence l;
    public ArrayList<NavDeepLink> m;
    public SparseArrayCompat<NavAction> n;
    public HashMap<String, NavArgument> o;

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: classes.dex */
    public @interface ClassType {
        Class<?> value();
    }

    /* loaded from: classes.dex */
    public static class a implements Comparable<a> {
        @NonNull
        public final NavDestination h;
        @Nullable
        public final Bundle i;
        public final boolean j;
        public final boolean k;
        public final int l;

        public a(@NonNull NavDestination navDestination, @Nullable Bundle bundle, boolean z, boolean z2, int i) {
            this.h = navDestination;
            this.i = bundle;
            this.j = z;
            this.k = z2;
            this.l = i;
        }

        @Override // java.lang.Comparable
        /* renamed from: a */
        public int compareTo(@NonNull a aVar) {
            boolean z = this.j;
            if (!z || aVar.j) {
                if (z || !aVar.j) {
                    Bundle bundle = this.i;
                    if (bundle == null || aVar.i != null) {
                        if (bundle != null || aVar.i == null) {
                            if (bundle != null) {
                                int size = bundle.size() - aVar.i.size();
                                if (size > 0) {
                                    return 1;
                                }
                                if (size < 0) {
                                    return -1;
                                }
                            }
                            boolean z2 = this.k;
                            if (!z2 || aVar.k) {
                                if (z2 || !aVar.k) {
                                    return this.l - aVar.l;
                                }
                                return -1;
                            }
                            return 1;
                        }
                        return -1;
                    }
                    return 1;
                }
                return -1;
            }
            return 1;
        }

        @NonNull
        public NavDestination b() {
            return this.h;
        }

        @Nullable
        public Bundle c() {
            return this.i;
        }
    }

    public NavDestination(@NonNull Navigator<? extends NavDestination> navigator) {
        this(NavigatorProvider.a(navigator.getClass()));
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public static String c(@NonNull Context context, int i) {
        if (i <= 16777215) {
            return Integer.toString(i);
        }
        try {
            return context.getResources().getResourceName(i);
        } catch (Resources.NotFoundException unused) {
            return Integer.toString(i);
        }
    }

    @NonNull
    public static <C> Class<? extends C> parseClassFromName(@NonNull Context context, @NonNull String str, @NonNull Class<? extends C> cls) {
        if (str.charAt(0) == '.') {
            str = context.getPackageName() + str;
        }
        HashMap<String, Class<?>> hashMap = p;
        Class<? extends C> cls2 = (Class<? extends C>) hashMap.get(str);
        if (cls2 == null) {
            try {
                cls2 = (Class<? extends C>) Class.forName(str, true, context.getClassLoader());
                hashMap.put(str, cls2);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        if (cls.isAssignableFrom(cls2)) {
            return cls2;
        }
        throw new IllegalArgumentException(str + " must be a subclass of " + cls);
    }

    @Nullable
    public Bundle a(@Nullable Bundle bundle) {
        HashMap<String, NavArgument> hashMap;
        if (bundle == null && ((hashMap = this.o) == null || hashMap.isEmpty())) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        HashMap<String, NavArgument> hashMap2 = this.o;
        if (hashMap2 != null) {
            for (Map.Entry<String, NavArgument> entry : hashMap2.entrySet()) {
                entry.getValue().a(entry.getKey(), bundle2);
            }
        }
        if (bundle != null) {
            bundle2.putAll(bundle);
            HashMap<String, NavArgument> hashMap3 = this.o;
            if (hashMap3 != null) {
                for (Map.Entry<String, NavArgument> entry2 : hashMap3.entrySet()) {
                    if (!entry2.getValue().b(entry2.getKey(), bundle2)) {
                        throw new IllegalArgumentException("Wrong argument type for '" + entry2.getKey() + "' in argument bundle. " + entry2.getValue().getType().getName() + " expected.");
                    }
                }
            }
        }
        return bundle2;
    }

    public final void addArgument(@NonNull String str, @NonNull NavArgument navArgument) {
        if (this.o == null) {
            this.o = new HashMap<>();
        }
        this.o.put(str, navArgument);
    }

    public final void addDeepLink(@NonNull String str) {
        addDeepLink(new NavDeepLink.Builder().setUriPattern(str).build());
    }

    @NonNull
    public int[] b() {
        ArrayDeque arrayDeque = new ArrayDeque();
        NavDestination navDestination = this;
        while (true) {
            NavGraph parent = navDestination.getParent();
            if (parent == null || parent.getStartDestination() != navDestination.getId()) {
                arrayDeque.addFirst(navDestination);
            }
            if (parent == null) {
                break;
            }
            navDestination = parent;
        }
        int[] iArr = new int[arrayDeque.size()];
        int i = 0;
        Iterator it = arrayDeque.iterator();
        while (it.hasNext()) {
            iArr[i] = ((NavDestination) it.next()).getId();
            i++;
        }
        return iArr;
    }

    @Nullable
    public a d(@NonNull NavDeepLinkRequest navDeepLinkRequest) {
        ArrayList<NavDeepLink> arrayList = this.m;
        if (arrayList == null) {
            return null;
        }
        Iterator<NavDeepLink> it = arrayList.iterator();
        a aVar = null;
        while (it.hasNext()) {
            NavDeepLink next = it.next();
            Uri uri = navDeepLinkRequest.getUri();
            Bundle b = uri != null ? next.b(uri, getArguments()) : null;
            String action = navDeepLinkRequest.getAction();
            boolean z = action != null && action.equals(next.getAction());
            String mimeType = navDeepLinkRequest.getMimeType();
            int c = mimeType != null ? next.c(mimeType) : -1;
            if (b != null || z || c > -1) {
                a aVar2 = new a(this, b, next.d(), z, c);
                if (aVar == null || aVar2.compareTo(aVar) > 0) {
                    aVar = aVar2;
                }
            }
        }
        return aVar;
    }

    public final void e(NavGraph navGraph) {
        this.i = navGraph;
    }

    public boolean f() {
        return true;
    }

    @Nullable
    public final NavAction getAction(@IdRes int i) {
        SparseArrayCompat<NavAction> sparseArrayCompat = this.n;
        NavAction navAction = sparseArrayCompat == null ? null : sparseArrayCompat.get(i);
        if (navAction != null) {
            return navAction;
        }
        if (getParent() != null) {
            return getParent().getAction(i);
        }
        return null;
    }

    @NonNull
    public final Map<String, NavArgument> getArguments() {
        HashMap<String, NavArgument> hashMap = this.o;
        return hashMap == null ? Collections.emptyMap() : Collections.unmodifiableMap(hashMap);
    }

    @NonNull
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public String getDisplayName() {
        if (this.k == null) {
            this.k = Integer.toString(this.j);
        }
        return this.k;
    }

    @IdRes
    public final int getId() {
        return this.j;
    }

    @Nullable
    public final CharSequence getLabel() {
        return this.l;
    }

    @NonNull
    public final String getNavigatorName() {
        return this.h;
    }

    @Nullable
    public final NavGraph getParent() {
        return this.i;
    }

    public boolean hasDeepLink(@NonNull Uri uri) {
        return hasDeepLink(new NavDeepLinkRequest(uri, null, null));
    }

    @CallSuper
    public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, androidx.navigation.common.R.styleable.Navigator);
        setId(obtainAttributes.getResourceId(androidx.navigation.common.R.styleable.Navigator_android_id, 0));
        this.k = c(context, this.j);
        setLabel(obtainAttributes.getText(androidx.navigation.common.R.styleable.Navigator_android_label));
        obtainAttributes.recycle();
    }

    public final void putAction(@IdRes int i, @IdRes int i2) {
        putAction(i, new NavAction(i2));
    }

    public final void removeAction(@IdRes int i) {
        SparseArrayCompat<NavAction> sparseArrayCompat = this.n;
        if (sparseArrayCompat == null) {
            return;
        }
        sparseArrayCompat.remove(i);
    }

    public final void removeArgument(@NonNull String str) {
        HashMap<String, NavArgument> hashMap = this.o;
        if (hashMap == null) {
            return;
        }
        hashMap.remove(str);
    }

    public final void setId(@IdRes int i) {
        this.j = i;
        this.k = null;
    }

    public final void setLabel(@Nullable CharSequence charSequence) {
        this.l = charSequence;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("(");
        String str = this.k;
        if (str == null) {
            sb.append(HexStringBuilder.DEFAULT_PREFIX);
            sb.append(Integer.toHexString(this.j));
        } else {
            sb.append(str);
        }
        sb.append(")");
        if (this.l != null) {
            sb.append(" label=");
            sb.append(this.l);
        }
        return sb.toString();
    }

    public NavDestination(@NonNull String str) {
        this.h = str;
    }

    public final void addDeepLink(@NonNull NavDeepLink navDeepLink) {
        if (this.m == null) {
            this.m = new ArrayList<>();
        }
        this.m.add(navDeepLink);
    }

    public boolean hasDeepLink(@NonNull NavDeepLinkRequest navDeepLinkRequest) {
        return d(navDeepLinkRequest) != null;
    }

    public final void putAction(@IdRes int i, @NonNull NavAction navAction) {
        if (f()) {
            if (i != 0) {
                if (this.n == null) {
                    this.n = new SparseArrayCompat<>();
                }
                this.n.put(i, navAction);
                return;
            }
            throw new IllegalArgumentException("Cannot have an action with actionId 0");
        }
        throw new UnsupportedOperationException("Cannot add action " + i + " to " + this + " as it does not support actions, indicating that it is a terminal destination in your navigation graph and will never trigger actions.");
    }
}
