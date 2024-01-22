package androidx.fragment.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
@Deprecated
/* loaded from: classes.dex */
public class FragmentTabHost extends TabHost implements TabHost.OnTabChangeListener {
    public final ArrayList<b> h;
    public FrameLayout i;
    public Context j;
    public FragmentManager k;
    public int l;
    public TabHost.OnTabChangeListener m;
    public b n;
    public boolean o;

    /* loaded from: classes.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new a();
        public String h;

        /* loaded from: classes.dex */
        public class a implements Parcelable.Creator<SavedState> {
            @Override // android.os.Parcelable.Creator
            /* renamed from: a */
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* renamed from: b */
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        @NonNull
        public String toString() {
            return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.h + "}";
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.h);
        }

        public SavedState(Parcel parcel) {
            super(parcel);
            this.h = parcel.readString();
        }
    }

    /* loaded from: classes.dex */
    public static class a implements TabHost.TabContentFactory {

        /* renamed from: a  reason: collision with root package name */
        public final Context f1317a;

        public a(Context context) {
            this.f1317a = context;
        }

        @Override // android.widget.TabHost.TabContentFactory
        public View createTabContent(String str) {
            View view = new View(this.f1317a);
            view.setMinimumWidth(0);
            view.setMinimumHeight(0);
            return view;
        }
    }

    /* loaded from: classes.dex */
    public static final class b {
        @NonNull

        /* renamed from: a  reason: collision with root package name */
        public final String f1318a;
        @NonNull
        public final Class<?> b;
        @Nullable
        public final Bundle c;
        public Fragment d;

        public b(@NonNull String str, @NonNull Class<?> cls, @Nullable Bundle bundle) {
            this.f1318a = str;
            this.b = cls;
            this.c = bundle;
        }
    }

    @Deprecated
    public FragmentTabHost(@NonNull Context context) {
        super(context, null);
        this.h = new ArrayList<>();
        e(context, null);
    }

    @Nullable
    public final FragmentTransaction a(@Nullable String str, @Nullable FragmentTransaction fragmentTransaction) {
        Fragment fragment;
        b d = d(str);
        if (this.n != d) {
            if (fragmentTransaction == null) {
                fragmentTransaction = this.k.beginTransaction();
            }
            b bVar = this.n;
            if (bVar != null && (fragment = bVar.d) != null) {
                fragmentTransaction.detach(fragment);
            }
            if (d != null) {
                Fragment fragment2 = d.d;
                if (fragment2 == null) {
                    Fragment instantiate = this.k.getFragmentFactory().instantiate(this.j.getClassLoader(), d.b.getName());
                    d.d = instantiate;
                    instantiate.setArguments(d.c);
                    fragmentTransaction.add(this.l, d.d, d.f1318a);
                } else {
                    fragmentTransaction.attach(fragment2);
                }
            }
            this.n = d;
        }
        return fragmentTransaction;
    }

    @Deprecated
    public void addTab(@NonNull TabHost.TabSpec tabSpec, @NonNull Class<?> cls, @Nullable Bundle bundle) {
        tabSpec.setContent(new a(this.j));
        String tag = tabSpec.getTag();
        b bVar = new b(tag, cls, bundle);
        if (this.o) {
            Fragment findFragmentByTag = this.k.findFragmentByTag(tag);
            bVar.d = findFragmentByTag;
            if (findFragmentByTag != null && !findFragmentByTag.isDetached()) {
                FragmentTransaction beginTransaction = this.k.beginTransaction();
                beginTransaction.detach(bVar.d);
                beginTransaction.commit();
            }
        }
        this.h.add(bVar);
        addTab(tabSpec);
    }

    public final void b() {
        if (this.i == null) {
            FrameLayout frameLayout = (FrameLayout) findViewById(this.l);
            this.i = frameLayout;
            if (frameLayout != null) {
                return;
            }
            throw new IllegalStateException("No tab content FrameLayout found for id " + this.l);
        }
    }

    public final void c(Context context) {
        if (findViewById(16908307) == null) {
            LinearLayout linearLayout = new LinearLayout(context);
            linearLayout.setOrientation(1);
            addView(linearLayout, new FrameLayout.LayoutParams(-1, -1));
            TabWidget tabWidget = new TabWidget(context);
            tabWidget.setId(16908307);
            tabWidget.setOrientation(0);
            linearLayout.addView(tabWidget, new LinearLayout.LayoutParams(-1, -2, 0.0f));
            FrameLayout frameLayout = new FrameLayout(context);
            frameLayout.setId(16908305);
            linearLayout.addView(frameLayout, new LinearLayout.LayoutParams(0, 0, 0.0f));
            FrameLayout frameLayout2 = new FrameLayout(context);
            this.i = frameLayout2;
            frameLayout2.setId(this.l);
            linearLayout.addView(frameLayout2, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        }
    }

    @Nullable
    public final b d(String str) {
        int size = this.h.size();
        for (int i = 0; i < size; i++) {
            b bVar = this.h.get(i);
            if (bVar.f1318a.equals(str)) {
                return bVar;
            }
        }
        return null;
    }

    public final void e(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{16842995}, 0, 0);
        this.l = obtainStyledAttributes.getResourceId(0, 0);
        obtainStyledAttributes.recycle();
        super.setOnTabChangedListener(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        String currentTabTag = getCurrentTabTag();
        int size = this.h.size();
        FragmentTransaction fragmentTransaction = null;
        for (int i = 0; i < size; i++) {
            b bVar = this.h.get(i);
            Fragment findFragmentByTag = this.k.findFragmentByTag(bVar.f1318a);
            bVar.d = findFragmentByTag;
            if (findFragmentByTag != null && !findFragmentByTag.isDetached()) {
                if (bVar.f1318a.equals(currentTabTag)) {
                    this.n = bVar;
                } else {
                    if (fragmentTransaction == null) {
                        fragmentTransaction = this.k.beginTransaction();
                    }
                    fragmentTransaction.detach(bVar.d);
                }
            }
        }
        this.o = true;
        FragmentTransaction a2 = a(currentTabTag, fragmentTransaction);
        if (a2 != null) {
            a2.commit();
            this.k.executePendingTransactions();
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    @Deprecated
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.o = false;
    }

    @Override // android.view.View
    @Deprecated
    public void onRestoreInstanceState(@SuppressLint({"UnknownNullness"}) Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setCurrentTabByTag(savedState.h);
    }

    @Override // android.view.View
    @NonNull
    @Deprecated
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.h = getCurrentTabTag();
        return savedState;
    }

    @Override // android.widget.TabHost.OnTabChangeListener
    @Deprecated
    public void onTabChanged(@Nullable String str) {
        FragmentTransaction a2;
        if (this.o && (a2 = a(str, null)) != null) {
            a2.commit();
        }
        TabHost.OnTabChangeListener onTabChangeListener = this.m;
        if (onTabChangeListener != null) {
            onTabChangeListener.onTabChanged(str);
        }
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setOnTabChangedListener(@Nullable TabHost.OnTabChangeListener onTabChangeListener) {
        this.m = onTabChangeListener;
    }

    @Override // android.widget.TabHost
    @Deprecated
    public void setup() {
        throw new IllegalStateException("Must call setup() that takes a Context and FragmentManager");
    }

    @Deprecated
    public void setup(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        c(context);
        super.setup();
        this.j = context;
        this.k = fragmentManager;
        b();
    }

    @Deprecated
    public FragmentTabHost(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.h = new ArrayList<>();
        e(context, attributeSet);
    }

    @Deprecated
    public void setup(@NonNull Context context, @NonNull FragmentManager fragmentManager, int i) {
        c(context);
        super.setup();
        this.j = context;
        this.k = fragmentManager;
        this.l = i;
        b();
        this.i.setId(i);
        if (getId() == -1) {
            setId(16908306);
        }
    }
}
