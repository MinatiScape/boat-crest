package androidx.databinding.adapters;

import android.widget.SeekBar;
import androidx.annotation.RestrictTo;
import androidx.databinding.BindingAdapter;
import androidx.databinding.InverseBindingListener;
import androidx.databinding.InverseBindingMethod;
import androidx.databinding.InverseBindingMethods;
@InverseBindingMethods({@InverseBindingMethod(attribute = "android:progress", type = SeekBar.class)})
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes.dex */
public class SeekBarBindingAdapter {

    /* loaded from: classes.dex */
    public interface OnProgressChanged {
        void onProgressChanged(SeekBar seekBar, int i, boolean z);
    }

    /* loaded from: classes.dex */
    public interface OnStartTrackingTouch {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    /* loaded from: classes.dex */
    public interface OnStopTrackingTouch {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    /* loaded from: classes.dex */
    public class a implements SeekBar.OnSeekBarChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ OnProgressChanged f1230a;
        public final /* synthetic */ InverseBindingListener b;
        public final /* synthetic */ OnStartTrackingTouch c;
        public final /* synthetic */ OnStopTrackingTouch d;

        public a(OnProgressChanged onProgressChanged, InverseBindingListener inverseBindingListener, OnStartTrackingTouch onStartTrackingTouch, OnStopTrackingTouch onStopTrackingTouch) {
            this.f1230a = onProgressChanged;
            this.b = inverseBindingListener;
            this.c = onStartTrackingTouch;
            this.d = onStopTrackingTouch;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            OnProgressChanged onProgressChanged = this.f1230a;
            if (onProgressChanged != null) {
                onProgressChanged.onProgressChanged(seekBar, i, z);
            }
            InverseBindingListener inverseBindingListener = this.b;
            if (inverseBindingListener != null) {
                inverseBindingListener.onChange();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            OnStartTrackingTouch onStartTrackingTouch = this.c;
            if (onStartTrackingTouch != null) {
                onStartTrackingTouch.onStartTrackingTouch(seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            OnStopTrackingTouch onStopTrackingTouch = this.d;
            if (onStopTrackingTouch != null) {
                onStopTrackingTouch.onStopTrackingTouch(seekBar);
            }
        }
    }

    @BindingAdapter(requireAll = false, value = {"android:onStartTrackingTouch", "android:onStopTrackingTouch", "android:onProgressChanged", "android:progressAttrChanged"})
    public static void setOnSeekBarChangeListener(SeekBar seekBar, OnStartTrackingTouch onStartTrackingTouch, OnStopTrackingTouch onStopTrackingTouch, OnProgressChanged onProgressChanged, InverseBindingListener inverseBindingListener) {
        if (onStartTrackingTouch == null && onStopTrackingTouch == null && onProgressChanged == null && inverseBindingListener == null) {
            seekBar.setOnSeekBarChangeListener(null);
        } else {
            seekBar.setOnSeekBarChangeListener(new a(onProgressChanged, inverseBindingListener, onStartTrackingTouch, onStopTrackingTouch));
        }
    }

    @BindingAdapter({"android:progress"})
    public static void setProgress(SeekBar seekBar, int i) {
        if (i != seekBar.getProgress()) {
            seekBar.setProgress(i);
        }
    }
}
