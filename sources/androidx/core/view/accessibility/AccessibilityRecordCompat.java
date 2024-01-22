package androidx.core.view.accessibility;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Parcelable;
import android.view.View;
import android.view.accessibility.AccessibilityRecord;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.util.List;
/* loaded from: classes.dex */
public class AccessibilityRecordCompat {

    /* renamed from: a  reason: collision with root package name */
    public final AccessibilityRecord f1187a;

    @RequiresApi(15)
    /* loaded from: classes.dex */
    public static class a {
        @DoNotInline
        public static int a(AccessibilityRecord accessibilityRecord) {
            return accessibilityRecord.getMaxScrollX();
        }

        @DoNotInline
        public static int b(AccessibilityRecord accessibilityRecord) {
            return accessibilityRecord.getMaxScrollY();
        }

        @DoNotInline
        public static void c(AccessibilityRecord accessibilityRecord, int i) {
            accessibilityRecord.setMaxScrollX(i);
        }

        @DoNotInline
        public static void d(AccessibilityRecord accessibilityRecord, int i) {
            accessibilityRecord.setMaxScrollY(i);
        }
    }

    @RequiresApi(16)
    /* loaded from: classes.dex */
    public static class b {
        @DoNotInline
        public static void a(AccessibilityRecord accessibilityRecord, View view, int i) {
            accessibilityRecord.setSource(view, i);
        }
    }

    @Deprecated
    public AccessibilityRecordCompat(Object obj) {
        this.f1187a = (AccessibilityRecord) obj;
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain(AccessibilityRecordCompat accessibilityRecordCompat) {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain(accessibilityRecordCompat.f1187a));
    }

    @Deprecated
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof AccessibilityRecordCompat) {
            AccessibilityRecordCompat accessibilityRecordCompat = (AccessibilityRecordCompat) obj;
            AccessibilityRecord accessibilityRecord = this.f1187a;
            if (accessibilityRecord == null) {
                return accessibilityRecordCompat.f1187a == null;
            }
            return accessibilityRecord.equals(accessibilityRecordCompat.f1187a);
        }
        return false;
    }

    @Deprecated
    public int getAddedCount() {
        return this.f1187a.getAddedCount();
    }

    @Deprecated
    public CharSequence getBeforeText() {
        return this.f1187a.getBeforeText();
    }

    @Deprecated
    public CharSequence getClassName() {
        return this.f1187a.getClassName();
    }

    @Deprecated
    public CharSequence getContentDescription() {
        return this.f1187a.getContentDescription();
    }

    @Deprecated
    public int getCurrentItemIndex() {
        return this.f1187a.getCurrentItemIndex();
    }

    @Deprecated
    public int getFromIndex() {
        return this.f1187a.getFromIndex();
    }

    @Deprecated
    public Object getImpl() {
        return this.f1187a;
    }

    @Deprecated
    public int getItemCount() {
        return this.f1187a.getItemCount();
    }

    @Deprecated
    public int getMaxScrollX() {
        return getMaxScrollX(this.f1187a);
    }

    @Deprecated
    public int getMaxScrollY() {
        return getMaxScrollY(this.f1187a);
    }

    @Deprecated
    public Parcelable getParcelableData() {
        return this.f1187a.getParcelableData();
    }

    @Deprecated
    public int getRemovedCount() {
        return this.f1187a.getRemovedCount();
    }

    @Deprecated
    public int getScrollX() {
        return this.f1187a.getScrollX();
    }

    @Deprecated
    public int getScrollY() {
        return this.f1187a.getScrollY();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    @Deprecated
    public AccessibilityNodeInfoCompat getSource() {
        return AccessibilityNodeInfoCompat.l(this.f1187a.getSource());
    }

    @Deprecated
    public List<CharSequence> getText() {
        return this.f1187a.getText();
    }

    @Deprecated
    public int getToIndex() {
        return this.f1187a.getToIndex();
    }

    @Deprecated
    public int getWindowId() {
        return this.f1187a.getWindowId();
    }

    @Deprecated
    public int hashCode() {
        AccessibilityRecord accessibilityRecord = this.f1187a;
        if (accessibilityRecord == null) {
            return 0;
        }
        return accessibilityRecord.hashCode();
    }

    @Deprecated
    public boolean isChecked() {
        return this.f1187a.isChecked();
    }

    @Deprecated
    public boolean isEnabled() {
        return this.f1187a.isEnabled();
    }

    @Deprecated
    public boolean isFullScreen() {
        return this.f1187a.isFullScreen();
    }

    @Deprecated
    public boolean isPassword() {
        return this.f1187a.isPassword();
    }

    @Deprecated
    public boolean isScrollable() {
        return this.f1187a.isScrollable();
    }

    @Deprecated
    public void recycle() {
        this.f1187a.recycle();
    }

    @Deprecated
    public void setAddedCount(int i) {
        this.f1187a.setAddedCount(i);
    }

    @Deprecated
    public void setBeforeText(CharSequence charSequence) {
        this.f1187a.setBeforeText(charSequence);
    }

    @Deprecated
    public void setChecked(boolean z) {
        this.f1187a.setChecked(z);
    }

    @Deprecated
    public void setClassName(CharSequence charSequence) {
        this.f1187a.setClassName(charSequence);
    }

    @Deprecated
    public void setContentDescription(CharSequence charSequence) {
        this.f1187a.setContentDescription(charSequence);
    }

    @Deprecated
    public void setCurrentItemIndex(int i) {
        this.f1187a.setCurrentItemIndex(i);
    }

    @Deprecated
    public void setEnabled(boolean z) {
        this.f1187a.setEnabled(z);
    }

    @Deprecated
    public void setFromIndex(int i) {
        this.f1187a.setFromIndex(i);
    }

    @Deprecated
    public void setFullScreen(boolean z) {
        this.f1187a.setFullScreen(z);
    }

    @Deprecated
    public void setItemCount(int i) {
        this.f1187a.setItemCount(i);
    }

    @Deprecated
    public void setMaxScrollX(int i) {
        setMaxScrollX(this.f1187a, i);
    }

    @Deprecated
    public void setMaxScrollY(int i) {
        setMaxScrollY(this.f1187a, i);
    }

    @Deprecated
    public void setParcelableData(Parcelable parcelable) {
        this.f1187a.setParcelableData(parcelable);
    }

    @Deprecated
    public void setPassword(boolean z) {
        this.f1187a.setPassword(z);
    }

    @Deprecated
    public void setRemovedCount(int i) {
        this.f1187a.setRemovedCount(i);
    }

    @Deprecated
    public void setScrollX(int i) {
        this.f1187a.setScrollX(i);
    }

    @Deprecated
    public void setScrollY(int i) {
        this.f1187a.setScrollY(i);
    }

    @Deprecated
    public void setScrollable(boolean z) {
        this.f1187a.setScrollable(z);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    @Deprecated
    public void setSource(View view) {
        this.f1187a.setSource(view);
    }

    @Deprecated
    public void setToIndex(int i) {
        this.f1187a.setToIndex(i);
    }

    public static int getMaxScrollX(@NonNull AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return a.a(accessibilityRecord);
        }
        return 0;
    }

    public static int getMaxScrollY(@NonNull AccessibilityRecord accessibilityRecord) {
        if (Build.VERSION.SDK_INT >= 15) {
            return a.b(accessibilityRecord);
        }
        return 0;
    }

    @Deprecated
    public static AccessibilityRecordCompat obtain() {
        return new AccessibilityRecordCompat(AccessibilityRecord.obtain());
    }

    public static void setMaxScrollX(@NonNull AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            a.c(accessibilityRecord, i);
        }
    }

    public static void setMaxScrollY(@NonNull AccessibilityRecord accessibilityRecord, int i) {
        if (Build.VERSION.SDK_INT >= 15) {
            a.d(accessibilityRecord, i);
        }
    }

    @Deprecated
    public void setSource(View view, int i) {
        setSource(this.f1187a, view, i);
    }

    public static void setSource(@NonNull AccessibilityRecord accessibilityRecord, @Nullable View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            b.a(accessibilityRecord, view, i);
        }
    }
}
