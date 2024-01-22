package com.coveiot.android.theme.databinding;

import android.util.SparseIntArray;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.theme.BR;
import com.coveiot.android.theme.R;
/* loaded from: classes7.dex */
public class FitnessVitalCardContainerDashboardBindingImpl extends FitnessVitalCardContainerDashboardBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        ViewDataBinding.IncludedLayouts includedLayouts = new ViewDataBinding.IncludedLayouts(5);
        j = includedLayouts;
        includedLayouts.setIncludes(1, new String[]{"fitness_vitals_card_rectangle", "item_list_fitness_vitals_layout_full"}, new int[]{2, 3}, new int[]{R.layout.fitness_vitals_card_rectangle, R.layout.item_list_fitness_vitals_layout_full});
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.rvVitals, 4);
    }

    public FitnessVitalCardContainerDashboardBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 5, j, k));
    }

    public final boolean a(FitnessVitalsCardRectangleBinding fitnessVitalsCardRectangleBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 2;
            }
            return true;
        }
        return false;
    }

    public final boolean b(ItemListFitnessVitalsLayoutFullBinding itemListFitnessVitalsLayoutFullBinding, int i) {
        if (i == BR._all) {
            synchronized (this) {
                this.i |= 1;
            }
            return true;
        }
        return false;
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        int i;
        long j3;
        long j4;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        Boolean bool = this.mIsFirstCardDataAvailable;
        int i2 = ((j2 & 20) > 0L ? 1 : ((j2 & 20) == 0L ? 0 : -1));
        if (i2 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool);
            if (i2 != 0) {
                if (safeUnbox) {
                    j3 = j2 | 64;
                    j4 = 256;
                } else {
                    j3 = j2 | 32;
                    j4 = 128;
                }
                j2 = j3 | j4;
            }
            int i3 = safeUnbox ? 8 : 0;
            i = safeUnbox ? 0 : 8;
            r8 = i3;
        } else {
            i = 0;
        }
        if ((j2 & 20) != 0) {
            this.firstFitnessVitalsCard.getRoot().setVisibility(r8);
            this.firstFitnessVitalsCardWithValue.getRoot().setVisibility(i);
        }
        ViewDataBinding.executeBindingsOn(this.firstFitnessVitalsCard);
        ViewDataBinding.executeBindingsOn(this.firstFitnessVitalsCardWithValue);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            if (this.i != 0) {
                return true;
            }
            return this.firstFitnessVitalsCard.hasPendingBindings() || this.firstFitnessVitalsCardWithValue.hasPendingBindings();
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 16L;
        }
        this.firstFitnessVitalsCard.invalidateAll();
        this.firstFitnessVitalsCardWithValue.invalidateAll();
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        if (i != 0) {
            if (i != 1) {
                return false;
            }
            return a((FitnessVitalsCardRectangleBinding) obj, i2);
        }
        return b((ItemListFitnessVitalsLayoutFullBinding) obj, i2);
    }

    @Override // com.coveiot.android.theme.databinding.FitnessVitalCardContainerDashboardBinding
    public void setFirstCardType(@Nullable String str) {
        this.mFirstCardType = str;
    }

    @Override // com.coveiot.android.theme.databinding.FitnessVitalCardContainerDashboardBinding
    public void setIsFirstCardDataAvailable(@Nullable Boolean bool) {
        this.mIsFirstCardDataAvailable = bool;
        synchronized (this) {
            this.i |= 4;
        }
        notifyPropertyChanged(BR.isFirstCardDataAvailable);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public void setLifecycleOwner(@Nullable LifecycleOwner lifecycleOwner) {
        super.setLifecycleOwner(lifecycleOwner);
        this.firstFitnessVitalsCard.setLifecycleOwner(lifecycleOwner);
        this.firstFitnessVitalsCardWithValue.setLifecycleOwner(lifecycleOwner);
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.isFirstCardDataAvailable == i) {
            setIsFirstCardDataAvailable((Boolean) obj);
        } else if (BR.firstCardType != i) {
            return false;
        } else {
            setFirstCardType((String) obj);
        }
        return true;
    }

    public FitnessVitalCardContainerDashboardBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 2, (ConstraintLayout) objArr[1], (FitnessVitalsCardRectangleBinding) objArr[2], (ItemListFitnessVitalsLayoutFullBinding) objArr[3], (RecyclerView) objArr[4]);
        this.i = -1L;
        this.clFirstFitnessVitalsCard.setTag(null);
        setContainedBinding(this.firstFitnessVitalsCard);
        setContainedBinding(this.firstFitnessVitalsCardWithValue);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
