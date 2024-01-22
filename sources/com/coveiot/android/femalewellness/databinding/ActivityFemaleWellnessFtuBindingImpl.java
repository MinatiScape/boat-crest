package com.coveiot.android.femalewellness.databinding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.ImageViewBindingAdapter;
import com.aigestudio.wheelpicker.WheelPicker;
import com.coveiot.android.femalewellness.BR;
import com.coveiot.android.femalewellness.R;
/* loaded from: classes4.dex */
public class ActivityFemaleWellnessFtuBindingImpl extends ActivityFemaleWellnessFtuBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts j = null;
    @Nullable
    public static final SparseIntArray k;
    @NonNull
    public final ConstraintLayout h;
    public long i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k = sparseIntArray;
        sparseIntArray.put(R.id.toolbar, 4);
        sparseIntArray.put(R.id.nestedScroolView, 5);
        sparseIntArray.put(R.id.clCommon, 6);
        sparseIntArray.put(R.id.clFTUQuestions, 7);
        sparseIntArray.put(R.id.ivImage, 8);
        sparseIntArray.put(R.id.setupText, 9);
        sparseIntArray.put(R.id.ibPreviousQuestion, 10);
        sparseIntArray.put(R.id.tvQuestion, 11);
        sparseIntArray.put(R.id.ibNextQuestion, 12);
        sparseIntArray.put(R.id.clCalendar, 13);
        sparseIntArray.put(R.id.calendarContainer, 14);
        sparseIntArray.put(R.id.clRadioQuestionsCycleLength, 15);
        sparseIntArray.put(R.id.radioGroupCycle, 16);
        sparseIntArray.put(R.id.rb2Days, 17);
        sparseIntArray.put(R.id.rb3Days, 18);
        sparseIntArray.put(R.id.rb4Days, 19);
        sparseIntArray.put(R.id.rb5Days, 20);
        sparseIntArray.put(R.id.tvCustomCycleLength, 21);
        sparseIntArray.put(R.id.clRadioQuestionsInterval, 22);
        sparseIntArray.put(R.id.radioGroupInterval, 23);
        sparseIntArray.put(R.id.rb20Days, 24);
        sparseIntArray.put(R.id.rb21Days, 25);
        sparseIntArray.put(R.id.rb22Days, 26);
        sparseIntArray.put(R.id.rb23Days, 27);
        sparseIntArray.put(R.id.tvCustomMenstrualCycleLength, 28);
        sparseIntArray.put(R.id.clReminder, 29);
        sparseIntArray.put(R.id.clTopReminder, 30);
        sparseIntArray.put(R.id.clMenstrual, 31);
        sparseIntArray.put(R.id.headerOne, 32);
        sparseIntArray.put(R.id.radioGroupMenstrual, 33);
        sparseIntArray.put(R.id.rbMenstrual1Days, 34);
        sparseIntArray.put(R.id.rbMenstrual2Days, 35);
        sparseIntArray.put(R.id.rbMenstrual3Days, 36);
        sparseIntArray.put(R.id.clOvulation, 37);
        sparseIntArray.put(R.id.headerTwo, 38);
        sparseIntArray.put(R.id.radioGroupOvulation, 39);
        sparseIntArray.put(R.id.rbOvulation1Days, 40);
        sparseIntArray.put(R.id.rbOvulation2Days, 41);
        sparseIntArray.put(R.id.rbOvulation3Days, 42);
        sparseIntArray.put(R.id.clReminderTime, 43);
        sparseIntArray.put(R.id.header, 44);
        sparseIntArray.put(R.id.view, 45);
        sparseIntArray.put(R.id.hourPicker, 46);
        sparseIntArray.put(R.id.minPicker, 47);
        sparseIntArray.put(R.id.amPmPicker, 48);
        sparseIntArray.put(R.id.infoView, 49);
        sparseIntArray.put(R.id.disclaimerText, 50);
        sparseIntArray.put(R.id.btnNext, 51);
    }

    public ActivityFemaleWellnessFtuBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 52, j, k));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j2;
        Drawable drawable;
        Drawable drawable2;
        int i;
        int i2;
        Context context;
        int i3;
        synchronized (this) {
            j2 = this.i;
            this.i = 0L;
        }
        int i4 = this.mCurrentSelection;
        int i5 = this.mFtuItemCount;
        int i6 = ((j2 & 5) > 0L ? 1 : ((j2 & 5) == 0L ? 0 : -1));
        Drawable drawable3 = null;
        if (i6 != 0) {
            boolean z = i4 == 0;
            boolean z2 = i4 == 1;
            boolean z3 = i4 == 2;
            if (i6 != 0) {
                j2 |= z ? 16L : 8L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z2 ? 1024L : 512L;
            }
            if ((j2 & 5) != 0) {
                j2 |= z3 ? 256L : 128L;
            }
            drawable3 = AppCompatResources.getDrawable(this.imageViewDot1.getContext(), z ? R.drawable.viewpager_selected_indicator : R.drawable.viewpager_unselected_indicator);
            drawable2 = AppCompatResources.getDrawable(this.imageViewDot2.getContext(), z2 ? R.drawable.viewpager_selected_indicator : R.drawable.viewpager_unselected_indicator);
            if (z3) {
                context = this.imageViewDot3.getContext();
                i3 = R.drawable.viewpager_selected_indicator;
            } else {
                context = this.imageViewDot3.getContext();
                i3 = R.drawable.viewpager_unselected_indicator;
            }
            drawable = AppCompatResources.getDrawable(context, i3);
        } else {
            drawable = null;
            drawable2 = null;
        }
        int i7 = ((j2 & 6) > 0L ? 1 : ((j2 & 6) == 0L ? 0 : -1));
        if (i7 != 0) {
            boolean z4 = i5 >= 2;
            boolean z5 = i5 > 1;
            boolean z6 = i5 >= 3;
            if (i7 != 0) {
                j2 |= z4 ? PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM : PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
            }
            if ((j2 & 6) != 0) {
                j2 |= z5 ? 64L : 32L;
            }
            if ((j2 & 6) != 0) {
                j2 |= z6 ? 16384L : PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            }
            i2 = z4 ? 0 : 8;
            int i8 = z5 ? 0 : 8;
            i = z6 ? 0 : 8;
            r13 = i8;
        } else {
            i = 0;
            i2 = 0;
        }
        if ((5 & j2) != 0) {
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot1, drawable3);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot2, drawable2);
            ImageViewBindingAdapter.setImageDrawable(this.imageViewDot3, drawable);
        }
        if ((j2 & 6) != 0) {
            this.imageViewDot1.setVisibility(r13);
            this.imageViewDot2.setVisibility(i2);
            this.imageViewDot3.setVisibility(i);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.i != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.i = 4L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.femalewellness.databinding.ActivityFemaleWellnessFtuBinding
    public void setCurrentSelection(int i) {
        this.mCurrentSelection = i;
        synchronized (this) {
            this.i |= 1;
        }
        notifyPropertyChanged(BR.currentSelection);
        super.requestRebind();
    }

    @Override // com.coveiot.android.femalewellness.databinding.ActivityFemaleWellnessFtuBinding
    public void setFtuItemCount(int i) {
        this.mFtuItemCount = i;
        synchronized (this) {
            this.i |= 2;
        }
        notifyPropertyChanged(BR.ftuItemCount);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (BR.currentSelection == i) {
            setCurrentSelection(((Integer) obj).intValue());
        } else if (BR.ftuItemCount != i) {
            return false;
        } else {
            setFtuItemCount(((Integer) obj).intValue());
        }
        return true;
    }

    public ActivityFemaleWellnessFtuBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (WheelPicker) objArr[48], (Button) objArr[51], (FrameLayout) objArr[14], (ConstraintLayout) objArr[13], (ConstraintLayout) objArr[6], (ConstraintLayout) objArr[7], (ConstraintLayout) objArr[31], (ConstraintLayout) objArr[37], (ConstraintLayout) objArr[15], (ConstraintLayout) objArr[22], (ConstraintLayout) objArr[29], (ConstraintLayout) objArr[43], (ConstraintLayout) objArr[30], (TextView) objArr[50], (TextView) objArr[44], (TextView) objArr[32], (TextView) objArr[38], (WheelPicker) objArr[46], (ImageButton) objArr[12], (ImageButton) objArr[10], (ImageView) objArr[1], (ImageView) objArr[2], (ImageView) objArr[3], (View) objArr[49], (ImageView) objArr[8], (WheelPicker) objArr[47], (NestedScrollView) objArr[5], (RadioGroup) objArr[16], (RadioGroup) objArr[23], (RadioGroup) objArr[33], (RadioGroup) objArr[39], (RadioButton) objArr[24], (RadioButton) objArr[25], (RadioButton) objArr[26], (RadioButton) objArr[27], (RadioButton) objArr[17], (RadioButton) objArr[18], (RadioButton) objArr[19], (RadioButton) objArr[20], (RadioButton) objArr[34], (RadioButton) objArr[35], (RadioButton) objArr[36], (RadioButton) objArr[40], (RadioButton) objArr[41], (RadioButton) objArr[42], (TextView) objArr[9], (View) objArr[4], (TextView) objArr[21], (TextView) objArr[28], (TextView) objArr[11], (View) objArr[45]);
        this.i = -1L;
        this.imageViewDot1.setTag(null);
        this.imageViewDot2.setTag(null);
        this.imageViewDot3.setTag(null);
        ConstraintLayout constraintLayout = (ConstraintLayout) objArr[0];
        this.h = constraintLayout;
        constraintLayout.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
