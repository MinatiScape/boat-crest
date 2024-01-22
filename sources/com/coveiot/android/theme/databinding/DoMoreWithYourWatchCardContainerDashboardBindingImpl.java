package com.coveiot.android.theme.databinding;

import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class DoMoreWithYourWatchCardContainerDashboardBindingImpl extends DoMoreWithYourWatchCardContainerDashboardBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final LinearLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(7);
        j = includedLayouts;
        int i = R.layout.layout_dashboard_do_more_with_your_watch_grid_item;
        includedLayouts.setIncludes(0, new String[]{"layout_dashboard_do_more_with_your_watch_grid_item", "layout_dashboard_do_more_with_your_watch_grid_item", "layout_dashboard_do_more_with_your_watch_grid_item", "layout_dashboard_do_more_with_your_watch_grid_item", "layout_dashboard_do_more_with_your_watch_grid_item", "layout_dashboard_do_more_with_your_watch_grid_item"}, new int[]{1, 2, 3, 4, 5, 6}, new int[]{i, i, i, i, i, i});
        k = null;
    }

    public DoMoreWithYourWatchCardContainerDashboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 7, j, k));
    }

    public final boolean a(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    public final boolean b(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 8;
            }
            return true;
        }
        return false;
    }

    public final boolean c(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 16;
            }
            return true;
        }
        return false;
    }

    public final boolean d(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 4;
            }
            return true;
        }
        return false;
    }

    public final boolean e(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 32;
            }
            return true;
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0040  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0120  */
    @Override // androidx.databinding.ViewDataBinding
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void executeBindings() {
        /*
            Method dump skipped, instructions count: 331
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBindingImpl.executeBindings():void");
    }

    public final boolean f(LayoutDashboardDoMoreWithYourWatchGridItemBinding layoutDashboardDoMoreWithYourWatchGridItemBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.sosSettings.hasPendingBindings() || this.wellnessCrewSetup.hasPendingBindings() || this.sportScores.hasPendingBindings() || this.buildFitnessPlan.hasPendingBindings() || this.alexaConnect.hasPendingBindings() || this.tapAndPay.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
        }
        this.sosSettings.invalidateAll();
        this.wellnessCrewSetup.invalidateAll();
        this.sportScores.invalidateAll();
        this.buildFitnessPlan.invalidateAll();
        this.alexaConnect.invalidateAll();
        this.tapAndPay.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return false;
                            }
                            return e((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
                        }
                        return c((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
                    }
                    return b((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
                }
                return d((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
            }
            return f((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
        }
        return a((LayoutDashboardDoMoreWithYourWatchGridItemBinding) obj, i2);
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.sosSettings.setLifecycleOwner(lifecycleOwner);
        this.wellnessCrewSetup.setLifecycleOwner(lifecycleOwner);
        this.sportScores.setLifecycleOwner(lifecycleOwner);
        this.buildFitnessPlan.setLifecycleOwner(lifecycleOwner);
        this.alexaConnect.setLifecycleOwner(lifecycleOwner);
        this.tapAndPay.setLifecycleOwner(lifecycleOwner);
    }

    @Override // com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding
    public void setShowAlexaConnect(@Nullable Boolean bool) {
        this.mShowAlexaConnect = bool;
        synchronized (this) {
            this.i |= 256;
        }
        notifyPropertyChanged(BR.showAlexaConnect);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding
    public void setShowFitnessPlan(@Nullable Boolean bool) {
        this.mShowFitnessPlan = bool;
        synchronized (this) {
            this.i |= PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
        }
        notifyPropertyChanged(BR.showFitnessPlan);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding
    public void setShowSOSSettings(@Nullable Boolean bool) {
        this.mShowSOSSettings = bool;
        synchronized (this) {
            this.i |= 128;
        }
        notifyPropertyChanged(BR.showSOSSettings);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding
    public void setShowSportScores(@Nullable Boolean bool) {
        this.mShowSportScores = bool;
        synchronized (this) {
            this.i |= 1024;
        }
        notifyPropertyChanged(BR.showSportScores);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding
    public void setShowTapAndPay(@Nullable Boolean bool) {
        this.mShowTapAndPay = bool;
        synchronized (this) {
            this.i |= 64;
        }
        notifyPropertyChanged(BR.showTapAndPay);
        super.requestRebind();
    }

    @Override // com.coveiot.android.theme.databinding.DoMoreWithYourWatchCardContainerDashboardBinding
    public void setShowWellnessCrew(@Nullable Boolean bool) {
        this.mShowWellnessCrew = bool;
        synchronized (this) {
            this.i |= 512;
        }
        notifyPropertyChanged(BR.showWellnessCrew);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.showTapAndPay == i) {
            setShowTapAndPay((Boolean) obj);
        } else if (BR.showSOSSettings == i) {
            setShowSOSSettings((Boolean) obj);
        } else if (BR.showAlexaConnect == i) {
            setShowAlexaConnect((Boolean) obj);
        } else if (BR.showWellnessCrew == i) {
            setShowWellnessCrew((Boolean) obj);
        } else if (BR.showSportScores == i) {
            setShowSportScores((Boolean) obj);
        } else if (BR.showFitnessPlan != i) {
            return false;
        } else {
            setShowFitnessPlan((Boolean) obj);
        }
        return true;
    }

    public DoMoreWithYourWatchCardContainerDashboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 6, (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[5], (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[4], (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[1], (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[3], (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[6], (LayoutDashboardDoMoreWithYourWatchGridItemBinding) objArr[2]);
        this.i = -1L;
        setContainedBinding(this.alexaConnect);
        setContainedBinding(this.buildFitnessPlan);
        LinearLayout linearLayout = (LinearLayout) objArr[0];
        this.h = linearLayout;
        linearLayout.setTag(null);
        setContainedBinding(this.sosSettings);
        setContainedBinding(this.sportScores);
        setContainedBinding(this.tapAndPay);
        setContainedBinding(this.wellnessCrewSetup);
        setRootTag(view);
        invalidateAll();
    }
}
