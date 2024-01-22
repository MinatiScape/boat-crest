package androidx.navigation.ui;

import android.annotation.SuppressLint;
import android.view.Menu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.customview.widget.Openable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavGraph;
import java.util.HashSet;
import java.util.Set;
/* loaded from: classes.dex */
public final class AppBarConfiguration {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final Set<Integer> f1461a;
    @Nullable
    public final Openable b;
    @Nullable
    public final OnNavigateUpListener c;

    /* loaded from: classes.dex */
    public interface OnNavigateUpListener {
        boolean onNavigateUp();
    }

    @Nullable
    @Deprecated
    public DrawerLayout getDrawerLayout() {
        Openable openable = this.b;
        if (openable instanceof DrawerLayout) {
            return (DrawerLayout) openable;
        }
        return null;
    }

    @Nullable
    public OnNavigateUpListener getFallbackOnNavigateUpListener() {
        return this.c;
    }

    @Nullable
    public Openable getOpenableLayout() {
        return this.b;
    }

    @NonNull
    public Set<Integer> getTopLevelDestinations() {
        return this.f1461a;
    }

    public AppBarConfiguration(@NonNull Set<Integer> set, @Nullable Openable openable, @Nullable OnNavigateUpListener onNavigateUpListener) {
        this.f1461a = set;
        this.b = openable;
        this.c = onNavigateUpListener;
    }

    /* loaded from: classes.dex */
    public static final class Builder {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final Set<Integer> f1462a;
        @Nullable
        public Openable b;
        @Nullable
        public OnNavigateUpListener c;

        public Builder(@NonNull NavGraph navGraph) {
            HashSet hashSet = new HashSet();
            this.f1462a = hashSet;
            hashSet.add(Integer.valueOf(NavigationUI.b(navGraph).getId()));
        }

        @NonNull
        @SuppressLint({"SyntheticAccessor"})
        public AppBarConfiguration build() {
            return new AppBarConfiguration(this.f1462a, this.b, this.c);
        }

        @NonNull
        @Deprecated
        public Builder setDrawerLayout(@Nullable DrawerLayout drawerLayout) {
            this.b = drawerLayout;
            return this;
        }

        @NonNull
        public Builder setFallbackOnNavigateUpListener(@Nullable OnNavigateUpListener onNavigateUpListener) {
            this.c = onNavigateUpListener;
            return this;
        }

        @NonNull
        public Builder setOpenableLayout(@Nullable Openable openable) {
            this.b = openable;
            return this;
        }

        public Builder(@NonNull Menu menu) {
            this.f1462a = new HashSet();
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                this.f1462a.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
        }

        public Builder(@NonNull int... iArr) {
            this.f1462a = new HashSet();
            for (int i : iArr) {
                this.f1462a.add(Integer.valueOf(i));
            }
        }

        public Builder(@NonNull Set<Integer> set) {
            HashSet hashSet = new HashSet();
            this.f1462a = hashSet;
            hashSet.addAll(set);
        }
    }
}
