package com.coveiot.android.leonardo.quickreply.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import com.coveiot.android.boat.R;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class QuickReplyDialog extends DialogFragment {
    @NotNull
    public Map<Integer, View> _$_findViewCache;
    @NotNull
    public final Context h;
    public final boolean i;
    public final int j;
    @NotNull
    public final String k;
    @NotNull
    public final OnItemClickListener l;

    /* loaded from: classes5.dex */
    public interface OnItemClickListener {
        void onEditedItemClickListener(@NotNull String str, int i);
    }

    public QuickReplyDialog(@NotNull Context mContext, boolean z, int i, @NotNull String quickReplyText, @NotNull OnItemClickListener onItemClickListener) {
        Intrinsics.checkNotNullParameter(mContext, "mContext");
        Intrinsics.checkNotNullParameter(quickReplyText, "quickReplyText");
        Intrinsics.checkNotNullParameter(onItemClickListener, "onItemClickListener");
        this._$_findViewCache = new LinkedHashMap();
        this.h = mContext;
        this.i = z;
        this.j = i;
        this.k = quickReplyText;
        this.l = onItemClickListener;
    }

    public static final void e(QuickReplyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = R.id.updateMsgEditText;
        EditText editText = (EditText) this$0._$_findCachedViewById(i);
        if ((editText != null ? editText.getText() : null) != null) {
            EditText editText2 = (EditText) this$0._$_findCachedViewById(i);
            Editable text = editText2 != null ? editText2.getText() : null;
            Intrinsics.checkNotNull(text);
            if (text.length() > 0) {
                this$0.l.onEditedItemClickListener(((EditText) this$0._$_findCachedViewById(i)).getText().toString(), this$0.j);
                this$0.dismiss();
                return;
            }
        }
        Context context = this$0.h;
        Toast.makeText(context, context.getString(R.string.please_enter_quick_reply_text), 1).show();
    }

    public static final void f(QuickReplyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public static final void g(QuickReplyDialog this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Nullable
    public View _$_findCachedViewById(int i) {
        View findViewById;
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null || (findViewById = view2.findViewById(i)) == null) {
                return null;
            }
            map.put(Integer.valueOf(i), findViewById);
            return findViewById;
        }
        return view;
    }

    public final void d(View view) {
        ((TextView) view.findViewById(R.id.tvOk)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.dialog.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QuickReplyDialog.e(QuickReplyDialog.this, view2);
            }
        });
        ((TextView) view.findViewById(R.id.tvCancel)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.dialog.c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QuickReplyDialog.f(QuickReplyDialog.this, view2);
            }
        });
        ((ImageView) view.findViewById(R.id.close_image)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.dialog.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                QuickReplyDialog.g(QuickReplyDialog.this, view2);
            }
        });
    }

    @NotNull
    public final Context getMContext() {
        return this.h;
    }

    @NotNull
    public final OnItemClickListener getOnItemClickListener() {
        return this.l;
    }

    public final int getPosition() {
        return this.j;
    }

    @NotNull
    public final String getQuickReplyText() {
        return this.k;
    }

    public final boolean isEdit() {
        return this.i;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.DialogTheme);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.dialog_edit_quick_reply, viewGroup, false);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        Window window;
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setLayout(-1, -2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        if (this.i) {
            ((EditText) _$_findCachedViewById(R.id.updateMsgEditText)).setText(this.k);
            ((TextView) _$_findCachedViewById(R.id.tv_text_num)).setText(this.k.length() + " / 60");
        }
        ((EditText) _$_findCachedViewById(R.id.updateMsgEditText)).addTextChangedListener(new TextWatcher() { // from class: com.coveiot.android.leonardo.quickreply.dialog.QuickReplyDialog$onViewCreated$1
            @Override // android.text.TextWatcher
            public void afterTextChanged(@NotNull Editable s) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(@NotNull CharSequence s, int i, int i2, int i3) {
                Intrinsics.checkNotNullParameter(s, "s");
                if (s.length() > 0) {
                    ((TextView) QuickReplyDialog.this._$_findCachedViewById(R.id.tv_text_num)).setText(((EditText) QuickReplyDialog.this._$_findCachedViewById(R.id.updateMsgEditText)).length() + " / 60");
                }
            }
        });
        d(view);
    }
}
