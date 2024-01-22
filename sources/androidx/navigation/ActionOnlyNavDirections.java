package androidx.navigation;

import android.os.Bundle;
import androidx.annotation.NonNull;
/* loaded from: classes.dex */
public final class ActionOnlyNavDirections implements NavDirections {

    /* renamed from: a  reason: collision with root package name */
    public final int f1425a;

    public ActionOnlyNavDirections(int i) {
        this.f1425a = i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && ActionOnlyNavDirections.class == obj.getClass() && getActionId() == ((ActionOnlyNavDirections) obj).getActionId();
    }

    @Override // androidx.navigation.NavDirections
    public int getActionId() {
        return this.f1425a;
    }

    @Override // androidx.navigation.NavDirections
    @NonNull
    public Bundle getArguments() {
        return new Bundle();
    }

    public int hashCode() {
        return 31 + getActionId();
    }

    public String toString() {
        return "ActionOnlyNavDirections(actionId=" + getActionId() + ")";
    }
}
