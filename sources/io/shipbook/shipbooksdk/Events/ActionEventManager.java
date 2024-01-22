package io.shipbook.shipbooksdk.Events;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import io.shipbook.shipbooksdk.InnerLog;
import io.shipbook.shipbooksdk.LogManager;
import io.shipbook.shipbooksdk.Models.ActionEvent;
import io.shipbook.shipbooksdk.Util.CompoundButtonExtKt;
import io.shipbook.shipbooksdk.Util.SeekBarExtKt;
import io.shipbook.shipbooksdk.Util.ViewExtKt;
import io.shipbook.shipbooksdk.Util.ViewGroupExtKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002¨\u0006\b"}, d2 = {"Lio/shipbook/shipbooksdk/Events/ActionEventManager;", "", "Landroid/view/ViewGroup;", "parent", "", "registerViews", "<init>", "()V", "shipbooksdk_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes12.dex */
public final class ActionEventManager {
    public static final ActionEventManager INSTANCE = new ActionEventManager();

    /* renamed from: a  reason: collision with root package name */
    public static final String f14013a = ActionEventManager.class.getSimpleName();

    /* loaded from: classes12.dex */
    public static final class a implements CompoundButton.OnCheckedChangeListener {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ View f14014a;
        public final /* synthetic */ CompoundButton.OnCheckedChangeListener b;

        public a(View view, CompoundButton.OnCheckedChangeListener onCheckedChangeListener) {
            this.f14014a = view;
            this.b = onCheckedChangeListener;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            ActionEventManager.INSTANCE.a("onCheckedChange", ((CompoundButton) this.f14014a).getText().toString(), this.f14014a);
            CompoundButton.OnCheckedChangeListener onCheckedChangeListener = this.b;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(compoundButton, z);
            }
        }
    }

    /* loaded from: classes12.dex */
    public static final class b implements View.OnClickListener {
        public final /* synthetic */ View h;
        public final /* synthetic */ View.OnClickListener i;

        public b(View view, View.OnClickListener onClickListener) {
            this.h = view;
            this.i = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View it) {
            ActionEventManager actionEventManager = ActionEventManager.INSTANCE;
            String obj = ((Button) this.h).getText().toString();
            Intrinsics.checkExpressionValueIsNotNull(it, "it");
            actionEventManager.a("onClick", obj, it);
            View.OnClickListener onClickListener = this.i;
            if (onClickListener != null) {
                onClickListener.onClick(this.h);
            }
        }
    }

    public final void a(String str, String str2, View view) {
        String sender = view.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(sender, "sender");
        ActionEvent actionEvent = new ActionEvent(str, sender, str2, "", 0, null, null, 112, null);
        InnerLog innerLog = InnerLog.INSTANCE;
        String TAG = f14013a;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        InnerLog.v$default(innerLog, TAG, "added action event: " + actionEvent, null, 4, null);
        LogManager.INSTANCE.push(actionEvent);
    }

    public final void b(final View view) {
        if (view instanceof ViewGroup) {
            registerViews((ViewGroup) view);
        } else if (view instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) view;
            compoundButton.setOnCheckedChangeListener(new a(view, CompoundButtonExtKt.getOnCheckedChangeListener(compoundButton)));
        } else if (view instanceof Button) {
            view.setOnClickListener(new b(view, ViewExtKt.getOnClickListener(view)));
        } else if (view instanceof EditText) {
            ((EditText) view).addTextChangedListener(new TextWatcher() { // from class: io.shipbook.shipbooksdk.Events.ActionEventManager$registerView$3
                @Override // android.text.TextWatcher
                public void afterTextChanged(@Nullable Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(@Nullable CharSequence charSequence, int i, int i2, int i3) {
                    ActionEventManager.INSTANCE.a("textChanged", String.valueOf(charSequence), view);
                }
            });
        } else if (view instanceof SeekBar) {
            SeekBar seekBar = (SeekBar) view;
            final SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = SeekBarExtKt.getOnSeekBarChangeListener(seekBar);
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: io.shipbook.shipbooksdk.Events.ActionEventManager$registerView$4
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(@NotNull SeekBar seekBar2, int i, boolean z) {
                    Intrinsics.checkParameterIsNotNull(seekBar2, "seekBar");
                    ActionEventManager.INSTANCE.a("progessChanged", "", seekBar2);
                    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = onSeekBarChangeListener;
                    if (onSeekBarChangeListener2 != null) {
                        onSeekBarChangeListener2.onProgressChanged(seekBar2, i, z);
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(@NotNull SeekBar seekBar2) {
                    Intrinsics.checkParameterIsNotNull(seekBar2, "seekBar");
                    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = onSeekBarChangeListener;
                    if (onSeekBarChangeListener2 != null) {
                        onSeekBarChangeListener2.onStartTrackingTouch(seekBar2);
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(@NotNull SeekBar seekBar2) {
                    Intrinsics.checkParameterIsNotNull(seekBar2, "seekBar");
                    SeekBar.OnSeekBarChangeListener onSeekBarChangeListener2 = onSeekBarChangeListener;
                    if (onSeekBarChangeListener2 != null) {
                        onSeekBarChangeListener2.onStopTrackingTouch(seekBar2);
                    }
                }
            });
        } else if (view instanceof Spinner) {
            Spinner spinner = (Spinner) view;
            final AdapterView.OnItemSelectedListener onItemSelectedListener = spinner.getOnItemSelectedListener();
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: io.shipbook.shipbooksdk.Events.ActionEventManager$registerView$5
                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onItemSelected(@NotNull AdapterView<?> parent, @NotNull View view2, int i, long j) {
                    Intrinsics.checkParameterIsNotNull(parent, "parent");
                    Intrinsics.checkParameterIsNotNull(view2, "view");
                    ActionEventManager.INSTANCE.a("itemSelected", "", parent);
                    AdapterView.OnItemSelectedListener onItemSelectedListener2 = onItemSelectedListener;
                    if (onItemSelectedListener2 != null) {
                        onItemSelectedListener2.onItemSelected(parent, view2, i, j);
                    }
                }

                @Override // android.widget.AdapterView.OnItemSelectedListener
                public void onNothingSelected(@NotNull AdapterView<?> parent) {
                    Intrinsics.checkParameterIsNotNull(parent, "parent");
                    AdapterView.OnItemSelectedListener onItemSelectedListener2 = onItemSelectedListener;
                    if (onItemSelectedListener2 != null) {
                        onItemSelectedListener2.onNothingSelected(parent);
                    }
                }
            });
        }
    }

    public final void registerViews(@NotNull ViewGroup parent) {
        Intrinsics.checkParameterIsNotNull(parent, "parent");
        for (View view : ViewGroupExtKt.getViews(parent)) {
            INSTANCE.b(view);
        }
    }
}
