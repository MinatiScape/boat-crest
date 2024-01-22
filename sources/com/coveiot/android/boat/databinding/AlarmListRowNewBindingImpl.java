package com.coveiot.android.boat.databinding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.adapters.CompoundButtonBindingAdapter;
import androidx.databinding.adapters.ViewBindingAdapter;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.more.adapters.AlarmListAdapterNew;
import com.coveiot.sdk.ble.api.model.AlarmInfo;
/* loaded from: classes3.dex */
public class AlarmListRowNewBindingImpl extends AlarmListRowNewBinding {
    @Nullable
    public static final ViewDataBinding.IncludedLayouts k = null;
    @Nullable
    public static final SparseIntArray l;
    public OnClickListenerImpl h;
    public OnClickListenerImpl1 i;
    public long j;

    /* loaded from: classes3.dex */
    public static class OnClickListenerImpl implements View.OnClickListener {
        public AlarmListAdapterNew.AlarmEventHandler h;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.onRepeatDaysSelected(view);
        }

        public OnClickListenerImpl setValue(AlarmListAdapterNew.AlarmEventHandler alarmEventHandler) {
            this.h = alarmEventHandler;
            if (alarmEventHandler == null) {
                return null;
            }
            return this;
        }
    }

    /* loaded from: classes3.dex */
    public static class OnClickListenerImpl1 implements View.OnClickListener {
        public AlarmListAdapterNew.AlarmEventHandler h;

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.h.onExpandCollapse(view);
        }

        public OnClickListenerImpl1 setValue(AlarmListAdapterNew.AlarmEventHandler alarmEventHandler) {
            this.h = alarmEventHandler;
            if (alarmEventHandler == null) {
                return null;
            }
            return this;
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        l = sparseIntArray;
        sparseIntArray.put(R.id.alarm_image, 18);
        sparseIntArray.put(R.id.container_layout, 19);
        sparseIntArray.put(R.id.save_alarm_btn, 20);
    }

    public AlarmListRowNewBindingImpl(@Nullable DataBindingComponent dataBindingComponent, @NonNull View view) {
        this(dataBindingComponent, view, ViewDataBinding.mapBindings(dataBindingComponent, view, 21, k, l));
    }

    @Override // androidx.databinding.ViewDataBinding
    public void executeBindings() {
        long j;
        boolean z;
        OnClickListenerImpl1 onClickListenerImpl1;
        Drawable drawable;
        String str;
        OnClickListenerImpl onClickListenerImpl;
        OnClickListenerImpl1 onClickListenerImpl12;
        boolean z2;
        Drawable drawable2;
        String str2;
        Drawable drawable3;
        Drawable drawable4;
        Drawable drawable5;
        Drawable drawable6;
        Drawable drawable7;
        Drawable drawable8;
        Drawable drawable9;
        float f;
        int i;
        int i2;
        int i3;
        boolean z3;
        int i4;
        int i5;
        int i6;
        OnClickListenerImpl onClickListenerImpl2;
        int i7;
        float f2;
        int i8;
        boolean z4;
        long j2;
        long j3;
        boolean z5;
        AlarmInfo.Days days;
        boolean z6;
        boolean z7;
        boolean z8;
        boolean z9;
        boolean z10;
        boolean z11;
        boolean z12;
        Drawable drawable10;
        Context context;
        int i9;
        Drawable drawable11;
        Drawable drawable12;
        int i10;
        Drawable drawable13;
        Drawable drawable14;
        Drawable drawable15;
        Drawable drawable16;
        Drawable drawable17;
        Drawable drawable18;
        int colorFromResource;
        int i11;
        Context context2;
        int i12;
        long j4;
        long j5;
        long j6;
        long j7;
        long j8;
        long j9;
        long j10;
        long j11;
        long j12;
        long j13;
        long j14;
        long j15;
        long j16;
        long j17;
        long j18;
        long j19;
        synchronized (this) {
            j = this.j;
            this.j = 0L;
        }
        AlarmListAdapterNew.AlarmEventHandler alarmEventHandler = this.mListener;
        AlarmInfo alarmInfo = this.mAlarmInfo;
        Boolean bool = this.mShouldEnableClick;
        Boolean bool2 = this.mShouldExpandAlarm;
        if ((j & 21) != 0) {
            if (alarmEventHandler != null) {
                OnClickListenerImpl onClickListenerImpl3 = this.h;
                if (onClickListenerImpl3 == null) {
                    onClickListenerImpl3 = new OnClickListenerImpl();
                    this.h = onClickListenerImpl3;
                }
                onClickListenerImpl = onClickListenerImpl3.setValue(alarmEventHandler);
            } else {
                onClickListenerImpl = null;
            }
            z = ViewDataBinding.safeUnbox(bool);
            if ((j & 20) != 0) {
                if (z) {
                    j18 = j | 256;
                    j19 = PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM;
                } else {
                    j18 = j | 128;
                    j19 = PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH;
                }
                j = j18 | j19;
            }
            if ((j & 21) != 0) {
                j |= z ? 274877906944L : 137438953472L;
            }
            if ((j & 20) != 0) {
                drawable = z ? AppCompatResources.getDrawable(this.alarmName.getContext(), R.drawable.alarm_name_edit_bg) : AppCompatResources.getDrawable(this.alarmName.getContext(), R.drawable.alarm_name_edit_bg_no_editable);
                str = this.alarmName.getResources().getString(z ? R.string.add_alarm_label : R.string.empty_str);
            } else {
                drawable = null;
                str = null;
            }
            if ((j & 17) == 0 || alarmEventHandler == null) {
                onClickListenerImpl1 = null;
            } else {
                OnClickListenerImpl1 onClickListenerImpl13 = this.i;
                if (onClickListenerImpl13 == null) {
                    onClickListenerImpl13 = new OnClickListenerImpl1();
                    this.i = onClickListenerImpl13;
                }
                onClickListenerImpl1 = onClickListenerImpl13.setValue(alarmEventHandler);
            }
        } else {
            z = false;
            onClickListenerImpl1 = null;
            drawable = null;
            str = null;
            onClickListenerImpl = null;
        }
        int i13 = ((j & 18) > 0L ? 1 : ((j & 18) == 0L ? 0 : -1));
        if (i13 != 0) {
            if (alarmInfo != null) {
                days = alarmInfo.getDaysSelected();
                z5 = alarmInfo.isAlarmOn();
            } else {
                z5 = false;
                days = null;
            }
            if (i13 != 0) {
                j |= z5 ? 67108864L : 33554432L;
            }
            boolean[] isDaySelected = days != null ? days.getIsDaySelected() : null;
            float f3 = z5 ? 1.0f : 0.5f;
            if (isDaySelected != null) {
                z10 = ViewDataBinding.getFromArray(isDaySelected, 4);
                z11 = ViewDataBinding.getFromArray(isDaySelected, 5);
                boolean fromArray = ViewDataBinding.getFromArray(isDaySelected, 2);
                boolean fromArray2 = ViewDataBinding.getFromArray(isDaySelected, 0);
                boolean fromArray3 = ViewDataBinding.getFromArray(isDaySelected, 6);
                z12 = ViewDataBinding.getFromArray(isDaySelected, 3);
                boolean fromArray4 = ViewDataBinding.getFromArray(isDaySelected, 1);
                z9 = fromArray3;
                z8 = fromArray2;
                z7 = fromArray4;
                z6 = fromArray;
            } else {
                z6 = false;
                z7 = false;
                z8 = false;
                z9 = false;
                z10 = false;
                z11 = false;
                z12 = false;
            }
            if ((j & 18) != 0) {
                if (z10) {
                    j16 = j | 64;
                    j17 = PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH;
                } else {
                    j16 = j | 32;
                    j17 = PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID;
                }
                j = j16 | j17;
            }
            if ((j & 18) != 0) {
                if (z11) {
                    j14 = j | PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED;
                    j15 = 1099511627776L;
                } else {
                    j14 = j | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED;
                    j15 = 549755813888L;
                }
                j = j14 | j15;
            }
            if ((j & 18) != 0) {
                if (z6) {
                    j12 = j | 17179869184L;
                    j13 = 68719476736L;
                } else {
                    j12 = j | 8589934592L;
                    j13 = 34359738368L;
                }
                j = j12 | j13;
            }
            if ((j & 18) != 0) {
                if (z8) {
                    j10 = j | PlaybackStateCompat.ACTION_SET_REPEAT_MODE;
                    j11 = 16777216;
                } else {
                    j10 = j | PlaybackStateCompat.ACTION_PREPARE_FROM_URI;
                    j11 = 8388608;
                }
                j = j10 | j11;
            }
            if ((j & 18) != 0) {
                if (z9) {
                    j8 = j | 4194304;
                    j9 = 17592186044416L;
                } else {
                    j8 = j | PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE;
                    j9 = 8796093022208L;
                }
                j = j8 | j9;
            }
            if ((j & 18) != 0) {
                if (z12) {
                    j6 = j | 1024;
                    j7 = 4294967296L;
                } else {
                    j6 = j | 512;
                    j7 = 2147483648L;
                }
                j = j6 | j7;
            }
            if ((j & 18) != 0) {
                if (z7) {
                    j4 = j | 16384;
                    j5 = 4398046511104L;
                } else {
                    j4 = j | PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                    j5 = 2199023255552L;
                }
                j = j4 | j5;
            }
            long j20 = j;
            int i14 = R.drawable.repeat_days_selected_bg;
            Context context3 = this.thursday.getContext();
            if (!z10) {
                i14 = R.drawable.repeat_days_unselected_bg;
            }
            Drawable drawable19 = AppCompatResources.getDrawable(context3, i14);
            int colorFromResource2 = z10 ? ViewDataBinding.getColorFromResource(this.thursday, R.color.main_text_color) : ViewDataBinding.getColorFromResource(this.thursday, R.color.color_b3b3b3);
            if (z11) {
                context = this.friday.getContext();
                drawable10 = drawable19;
                i9 = R.drawable.repeat_days_selected_bg;
            } else {
                drawable10 = drawable19;
                context = this.friday.getContext();
                i9 = R.drawable.repeat_days_unselected_bg;
            }
            Drawable drawable20 = AppCompatResources.getDrawable(context, i9);
            int colorFromResource3 = ViewDataBinding.getColorFromResource(this.friday, z11 ? R.color.main_text_color : R.color.color_b3b3b3);
            if (z6) {
                drawable11 = drawable20;
                drawable12 = AppCompatResources.getDrawable(this.tuesday.getContext(), R.drawable.repeat_days_selected_bg);
            } else {
                drawable11 = drawable20;
                drawable12 = AppCompatResources.getDrawable(this.tuesday.getContext(), R.drawable.repeat_days_unselected_bg);
            }
            int colorFromResource4 = ViewDataBinding.getColorFromResource(this.tuesday, z6 ? R.color.main_text_color : R.color.color_b3b3b3);
            if (z8) {
                i10 = colorFromResource4;
                drawable13 = AppCompatResources.getDrawable(this.sunday.getContext(), R.drawable.repeat_days_selected_bg);
            } else {
                i10 = colorFromResource4;
                drawable13 = AppCompatResources.getDrawable(this.sunday.getContext(), R.drawable.repeat_days_unselected_bg);
            }
            int colorFromResource5 = ViewDataBinding.getColorFromResource(this.sunday, z8 ? R.color.main_text_color : R.color.color_b3b3b3);
            if (z9) {
                drawable14 = drawable13;
                drawable15 = AppCompatResources.getDrawable(this.saturday.getContext(), R.drawable.repeat_days_selected_bg);
            } else {
                drawable14 = drawable13;
                drawable15 = AppCompatResources.getDrawable(this.saturday.getContext(), R.drawable.repeat_days_unselected_bg);
            }
            int colorFromResource6 = ViewDataBinding.getColorFromResource(this.saturday, z9 ? R.color.main_text_color : R.color.color_b3b3b3);
            if (z12) {
                drawable16 = drawable15;
                drawable17 = AppCompatResources.getDrawable(this.wednesday.getContext(), R.drawable.repeat_days_selected_bg);
            } else {
                drawable16 = drawable15;
                drawable17 = AppCompatResources.getDrawable(this.wednesday.getContext(), R.drawable.repeat_days_unselected_bg);
            }
            if (z12) {
                drawable18 = drawable17;
                colorFromResource = ViewDataBinding.getColorFromResource(this.wednesday, R.color.main_text_color);
            } else {
                drawable18 = drawable17;
                colorFromResource = ViewDataBinding.getColorFromResource(this.wednesday, R.color.color_b3b3b3);
            }
            if (z7) {
                context2 = this.monday.getContext();
                i11 = colorFromResource3;
                i12 = R.drawable.repeat_days_selected_bg;
            } else {
                i11 = colorFromResource3;
                context2 = this.monday.getContext();
                i12 = R.drawable.repeat_days_unselected_bg;
            }
            Drawable drawable21 = AppCompatResources.getDrawable(context2, i12);
            int colorFromResource7 = ViewDataBinding.getColorFromResource(this.monday, z7 ? R.color.main_text_color : R.color.color_b3b3b3);
            onClickListenerImpl12 = onClickListenerImpl1;
            i5 = colorFromResource;
            drawable9 = drawable12;
            drawable8 = drawable18;
            drawable3 = drawable10;
            drawable7 = drawable11;
            i6 = i10;
            i = i11;
            z3 = z5;
            str2 = str;
            drawable6 = drawable16;
            i2 = colorFromResource7;
            Drawable drawable22 = drawable;
            drawable5 = drawable21;
            j = j20;
            z2 = z;
            drawable2 = drawable22;
            drawable4 = drawable14;
            i3 = colorFromResource5;
            f = f3;
            onClickListenerImpl2 = onClickListenerImpl;
            i7 = colorFromResource2;
            i4 = colorFromResource6;
        } else {
            onClickListenerImpl12 = onClickListenerImpl1;
            z2 = z;
            drawable2 = drawable;
            str2 = str;
            drawable3 = null;
            drawable4 = null;
            drawable5 = null;
            drawable6 = null;
            drawable7 = null;
            drawable8 = null;
            drawable9 = null;
            f = 0.0f;
            i = 0;
            i2 = 0;
            i3 = 0;
            z3 = false;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            onClickListenerImpl2 = onClickListenerImpl;
            i7 = 0;
        }
        int i15 = ((j & 24) > 0L ? 1 : ((j & 24) == 0L ? 0 : -1));
        if (i15 != 0) {
            boolean safeUnbox = ViewDataBinding.safeUnbox(bool2);
            if (i15 != 0) {
                if (safeUnbox) {
                    j2 = j | 268435456;
                    j3 = 1073741824;
                } else {
                    j2 = j | 134217728;
                    j3 = 536870912;
                }
                j = j2 | j3;
            }
            int i16 = safeUnbox ? 0 : 8;
            f2 = safeUnbox ? 0.0f : 180.0f;
            i8 = i16;
        } else {
            f2 = 0.0f;
            i8 = 0;
        }
        int i17 = i8;
        long j21 = j;
        if ((j & 18) != 0) {
            if (ViewDataBinding.getBuildSdkInt() >= 11) {
                this.alarmAmPm.setAlpha(f);
                this.alarmName.setAlpha(f);
                this.alarmRepeat.setAlpha(f);
                this.alarmTime.setAlpha(f);
                this.divider.setAlpha(f);
                this.repeatLabel.setAlpha(f);
                this.repeatSelectionLayout.setAlpha(f);
            }
            CompoundButtonBindingAdapter.setChecked(this.alarmSwitch, z3);
            ViewBindingAdapter.setBackground(this.friday, drawable7);
            this.friday.setTextColor(i);
            ViewBindingAdapter.setBackground(this.monday, drawable5);
            this.monday.setTextColor(i2);
            ViewBindingAdapter.setBackground(this.saturday, drawable6);
            this.saturday.setTextColor(i4);
            ViewBindingAdapter.setBackground(this.sunday, drawable4);
            this.sunday.setTextColor(i3);
            ViewBindingAdapter.setBackground(this.thursday, drawable3);
            this.thursday.setTextColor(i7);
            ViewBindingAdapter.setBackground(this.tuesday, drawable9);
            this.tuesday.setTextColor(i6);
            ViewBindingAdapter.setBackground(this.wednesday, drawable8);
            this.wednesday.setTextColor(i5);
        }
        if ((j21 & 20) != 0) {
            z4 = z2;
            this.alarmAmPm.setClickable(z4);
            this.alarmName.setClickable(z4);
            ViewBindingAdapter.setBackground(this.alarmName, drawable2);
            this.alarmName.setHint(str2);
            this.alarmName.setEnabled(z4);
            this.alarmRepeat.setClickable(z4);
            this.alarmTime.setClickable(z4);
            this.divider.setClickable(z4);
            this.repeatLabel.setClickable(z4);
            this.repeatSelectionLayout.setClickable(z4);
        } else {
            z4 = z2;
        }
        if ((j21 & 24) != 0) {
            this.divider.setVisibility(i17);
            this.repeatLayout.setVisibility(i17);
            if (ViewDataBinding.getBuildSdkInt() >= 11) {
                this.expandCollapseArrow.setRotation(f2);
            }
        }
        if ((j21 & 17) != 0) {
            this.expandCollapseArrow.setOnClickListener(onClickListenerImpl12);
        }
        if ((j21 & 21) != 0) {
            OnClickListenerImpl onClickListenerImpl4 = onClickListenerImpl2;
            ViewBindingAdapter.setOnClick(this.friday, onClickListenerImpl4, z4);
            ViewBindingAdapter.setOnClick(this.monday, onClickListenerImpl4, z4);
            ViewBindingAdapter.setOnClick(this.saturday, onClickListenerImpl4, z4);
            ViewBindingAdapter.setOnClick(this.sunday, onClickListenerImpl4, z4);
            ViewBindingAdapter.setOnClick(this.thursday, onClickListenerImpl4, z4);
            ViewBindingAdapter.setOnClick(this.tuesday, onClickListenerImpl4, z4);
            ViewBindingAdapter.setOnClick(this.wednesday, onClickListenerImpl4, z4);
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean hasPendingBindings() {
        synchronized (this) {
            return this.j != 0;
        }
    }

    @Override // androidx.databinding.ViewDataBinding
    public void invalidateAll() {
        synchronized (this) {
            this.j = 16L;
        }
        requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean onFieldChange(int i, Object obj, int i2) {
        return false;
    }

    @Override // com.coveiot.android.boat.databinding.AlarmListRowNewBinding
    public void setAlarmInfo(@Nullable AlarmInfo alarmInfo) {
        this.mAlarmInfo = alarmInfo;
        synchronized (this) {
            this.j |= 2;
        }
        notifyPropertyChanged(4);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.AlarmListRowNewBinding
    public void setListener(@Nullable AlarmListAdapterNew.AlarmEventHandler alarmEventHandler) {
        this.mListener = alarmEventHandler;
        synchronized (this) {
            this.j |= 1;
        }
        notifyPropertyChanged(61);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.AlarmListRowNewBinding
    public void setShouldEnableClick(@Nullable Boolean bool) {
        this.mShouldEnableClick = bool;
        synchronized (this) {
            this.j |= 4;
        }
        notifyPropertyChanged(88);
        super.requestRebind();
    }

    @Override // com.coveiot.android.boat.databinding.AlarmListRowNewBinding
    public void setShouldExpandAlarm(@Nullable Boolean bool) {
        this.mShouldExpandAlarm = bool;
        synchronized (this) {
            this.j |= 8;
        }
        notifyPropertyChanged(89);
        super.requestRebind();
    }

    @Override // androidx.databinding.ViewDataBinding
    public boolean setVariable(int i, @Nullable Object obj) {
        if (61 == i) {
            setListener((AlarmListAdapterNew.AlarmEventHandler) obj);
        } else if (4 == i) {
            setAlarmInfo((AlarmInfo) obj);
        } else if (88 == i) {
            setShouldEnableClick((Boolean) obj);
        } else if (89 != i) {
            return false;
        } else {
            setShouldExpandAlarm((Boolean) obj);
        }
        return true;
    }

    public AlarmListRowNewBindingImpl(DataBindingComponent dataBindingComponent, View view, Object[] objArr) {
        super(dataBindingComponent, view, 0, (TextView) objArr[3], (ImageView) objArr[18], (EditText) objArr[1], (TextView) objArr[4], (LinearLayout) objArr[0], (Switch) objArr[6], (TextView) objArr[2], (ConstraintLayout) objArr[19], (View) objArr[7], (ImageView) objArr[5], (TextView) objArr[16], (TextView) objArr[12], (TextView) objArr[9], (ConstraintLayout) objArr[8], (LinearLayout) objArr[10], (TextView) objArr[17], (Button) objArr[20], (TextView) objArr[11], (TextView) objArr[15], (TextView) objArr[13], (TextView) objArr[14]);
        this.j = -1L;
        this.alarmAmPm.setTag(null);
        this.alarmName.setTag(null);
        this.alarmRepeat.setTag(null);
        this.alarmSegment.setTag(null);
        this.alarmSwitch.setTag(null);
        this.alarmTime.setTag(null);
        this.divider.setTag(null);
        this.expandCollapseArrow.setTag(null);
        this.friday.setTag(null);
        this.monday.setTag(null);
        this.repeatLabel.setTag(null);
        this.repeatLayout.setTag(null);
        this.repeatSelectionLayout.setTag(null);
        this.saturday.setTag(null);
        this.sunday.setTag(null);
        this.thursday.setTag(null);
        this.tuesday.setTag(null);
        this.wednesday.setTag(null);
        setRootTag(view);
        invalidateAll();
    }
}
