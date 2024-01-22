package com.coveiot.android.leonardo.quickreply.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.coveiot.android.boat.R;
import com.coveiot.android.dashboard2.listener.ViewModelListener;
import com.coveiot.android.leonardo.listener.DialogListener;
import com.coveiot.android.leonardo.quickreply.adapter.ManageQuickReplyListAdapter;
import com.coveiot.android.leonardo.quickreply.dialog.QuickReplyDialog;
import com.coveiot.android.leonardo.quickreply.model.QuickReplyModel;
import com.coveiot.android.leonardo.quickreply.viewmodel.ManageQuickReplyViewModel;
import com.coveiot.android.leonardo.utils.ViewModelFactory;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.android.theme.BottomSheetDialogTwoButtons;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.QuickReplyListModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityManageQuickReply extends BaseActivity implements ManageQuickReplyListAdapter.OnItemClickListener, QuickReplyDialog.OnItemClickListener, DialogListener, ViewModelListener {
    public ManageQuickReplyViewModel p;
    public ManageQuickReplyListAdapter q;
    public boolean s;
    public TextView t;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    @NotNull
    public List<QuickReplyModel> r = new ArrayList();
    @NotNull
    public final ActivityManageQuickReply$mIthSimpleCallback$1 u = new ItemTouchHelper.SimpleCallback() { // from class: com.coveiot.android.leonardo.quickreply.activity.ActivityManageQuickReply$mIthSimpleCallback$1
        {
            super(51, 0);
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public boolean onMove(@NotNull RecyclerView recyclerView, @NotNull RecyclerView.ViewHolder viewHolder, @NotNull RecyclerView.ViewHolder target) {
            TextView textView;
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
            Intrinsics.checkNotNullParameter(target, "target");
            List list = ActivityManageQuickReply.this.r;
            if (!(list == null || list.isEmpty())) {
                int bindingAdapterPosition = viewHolder.getBindingAdapterPosition();
                int bindingAdapterPosition2 = target.getBindingAdapterPosition();
                Collections.swap(ActivityManageQuickReply.this.r, bindingAdapterPosition, bindingAdapterPosition2);
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                if (adapter != null) {
                    adapter.notifyItemMoved(bindingAdapterPosition, bindingAdapterPosition2);
                }
                textView = ActivityManageQuickReply.this.t;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("saveText");
                    textView = null;
                }
                textView.setAlpha(1.0f);
                ((Button) ActivityManageQuickReply.this._$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
                ActivityManageQuickReply.this.setBoolSaveVisible(true);
            }
            return true;
        }

        @Override // androidx.recyclerview.widget.ItemTouchHelper.Callback
        public void onSwiped(@NotNull RecyclerView.ViewHolder viewHolder, int i) {
            Intrinsics.checkNotNullParameter(viewHolder, "viewHolder");
        }
    };

    /* loaded from: classes5.dex */
    public static final class a extends Lambda implements Function1<List<? extends QuickReplyModel>, Unit> {
        public a() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(List<? extends QuickReplyModel> list) {
            invoke2((List<QuickReplyModel>) list);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke  reason: avoid collision after fix types in other method */
        public final void invoke2(List<QuickReplyModel> list) {
            List list2 = ActivityManageQuickReply.this.r;
            Intrinsics.checkNotNull(list2, "null cannot be cast to non-null type java.util.ArrayList<com.coveiot.android.leonardo.quickreply.model.QuickReplyModel>");
            ((ArrayList) list2).addAll(list);
            ManageQuickReplyListAdapter manageQuickReplyListAdapter = ActivityManageQuickReply.this.q;
            if (manageQuickReplyListAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("manageQuickReplyListAdapter");
                manageQuickReplyListAdapter = null;
            }
            manageQuickReplyListAdapter.notifyDataSetChanged();
        }
    }

    public static final void A(ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.t;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void B(ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.s) {
            ArrayList arrayList = new ArrayList();
            for (QuickReplyModel quickReplyModel : this$0.r) {
                arrayList.add(quickReplyModel.getQuickReplyMessage());
            }
            ManageQuickReplyViewModel manageQuickReplyViewModel = this$0.p;
            if (manageQuickReplyViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                manageQuickReplyViewModel = null;
            }
            manageQuickReplyViewModel.setQuickReplyList(arrayList);
        }
    }

    public static final void C(ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        TextView textView = this$0.t;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.performClick();
    }

    public static final void D(BottomSheetDialogTwoButtons dialog, ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(dialog, "$dialog");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        dialog.dismiss();
        this$0.finish();
    }

    public static final void E(Function1 tmp0, Object obj) {
        Intrinsics.checkNotNullParameter(tmp0, "$tmp0");
        tmp0.invoke(obj);
    }

    public static final void F(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void G(BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle, ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(bottomSheetDialogOneButtonOneTitle, "$bottomSheetDialogOneButtonOneTitle");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        bottomSheetDialogOneButtonOneTitle.dismiss();
        this$0.finish();
    }

    public static final void z(ActivityManageQuickReply this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseActivity
    @Nullable
    public View _$_findCachedViewById(int i) {
        Map<Integer, View> map = this._$_findViewCache;
        View view = map.get(Integer.valueOf(i));
        if (view == null) {
            View findViewById = findViewById(i);
            if (findViewById != null) {
                map.put(Integer.valueOf(i), findViewById);
                return findViewById;
            }
            return null;
        }
        return view;
    }

    public final boolean getBoolSaveVisible() {
        return this.s;
    }

    public final void initToolbar() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getString(R.string.manage_quick_reply));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageQuickReply.z(ActivityManageQuickReply.this, view);
            }
        });
        View findViewById = findViewById(R.id.save);
        Intrinsics.checkNotNullExpressionValue(findViewById, "findViewById<TextView>(R.id.save)");
        TextView textView = (TextView) findViewById;
        this.t = textView;
        TextView textView2 = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setText(getString(R.string.save));
        TextView textView3 = this.t;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView3 = null;
        }
        textView3.setVisibility(0);
        TextView textView4 = this.t;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView4 = null;
        }
        textView4.setAlpha(0.5f);
        int i = R.id.btn_ok;
        ((Button) _$_findCachedViewById(i)).setEnabled(false);
        TextView textView5 = this.t;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView5 = null;
        }
        textView5.setVisibility(8);
        ((Button) _$_findCachedViewById(i)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageQuickReply.A(ActivityManageQuickReply.this, view);
            }
        });
        TextView textView6 = this.t;
        if (textView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
        } else {
            textView2 = textView6;
        }
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.d
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageQuickReply.B(ActivityManageQuickReply.this, view);
            }
        });
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (y()) {
            String string = getString(R.string.confirmation);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.confirmation)");
            String string2 = getString(R.string.save_changes);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.save_changes)");
            final BottomSheetDialogTwoButtons bottomSheetDialogTwoButtons = new BottomSheetDialogTwoButtons(this, string, string2, false, 8, null);
            String string3 = getString(R.string.save_changes_btn);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.save_changes_btn)");
            bottomSheetDialogTwoButtons.setPositiveButton(string3, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.c
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityManageQuickReply.C(ActivityManageQuickReply.this, view);
                }
            });
            String string4 = getString(R.string.discard);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.discard)");
            bottomSheetDialogTwoButtons.setNegativeButton(string4, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ActivityManageQuickReply.D(BottomSheetDialogTwoButtons.this, this, view);
                }
            });
            bottomSheetDialogTwoButtons.show();
            return;
        }
        finish();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_manage_quick_reply);
        initToolbar();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        int i = R.id.rvQuickReply;
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        RecyclerView recyclerView2 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView2);
        recyclerView2.addItemDecoration(new DividerItemDecoration(this, 0));
        List<QuickReplyModel> list = this.r;
        Intrinsics.checkNotNull(list);
        this.q = new ManageQuickReplyListAdapter(this, list, this);
        RecyclerView recyclerView3 = (RecyclerView) _$_findCachedViewById(i);
        Intrinsics.checkNotNull(recyclerView3);
        ManageQuickReplyListAdapter manageQuickReplyListAdapter = this.q;
        ManageQuickReplyViewModel manageQuickReplyViewModel = null;
        if (manageQuickReplyListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageQuickReplyListAdapter");
            manageQuickReplyListAdapter = null;
        }
        recyclerView3.setAdapter(manageQuickReplyListAdapter);
        new ItemTouchHelper(this.u).attachToRecyclerView((RecyclerView) _$_findCachedViewById(i));
        ManageQuickReplyViewModel manageQuickReplyViewModel2 = (ManageQuickReplyViewModel) ViewModelProviders.of(this, new ViewModelFactory(this)).get(ManageQuickReplyViewModel.class);
        this.p = manageQuickReplyViewModel2;
        if (manageQuickReplyViewModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            manageQuickReplyViewModel2 = null;
        }
        manageQuickReplyViewModel2.setDialogListener(this);
        ManageQuickReplyViewModel manageQuickReplyViewModel3 = this.p;
        if (manageQuickReplyViewModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            manageQuickReplyViewModel3 = null;
        }
        manageQuickReplyViewModel3.setMListener(this);
        ManageQuickReplyViewModel manageQuickReplyViewModel4 = this.p;
        if (manageQuickReplyViewModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            manageQuickReplyViewModel = manageQuickReplyViewModel4;
        }
        MutableLiveData<List<QuickReplyModel>> quickReplyListData = manageQuickReplyViewModel.getQuickReplyListData();
        if (quickReplyListData != null) {
            final a aVar = new a();
            quickReplyListData.observe(this, new Observer() { // from class: com.coveiot.android.leonardo.quickreply.activity.h
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    ActivityManageQuickReply.E(Function1.this, obj);
                }
            });
        }
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onDataFailure(@NotNull String message) {
        Intrinsics.checkNotNullParameter(message, "message");
        Toast.makeText(this, message, 0).show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onDismiss() {
        dismissProgress();
    }

    @Override // com.coveiot.android.leonardo.quickreply.adapter.ManageQuickReplyListAdapter.OnItemClickListener
    public void onEditItemClickListener(@NotNull String editedString, int i) {
        Intrinsics.checkNotNullParameter(editedString, "editedString");
        new QuickReplyDialog(this, true, i, editedString, this).show(getSupportFragmentManager(), "Quick Reply Dialog");
    }

    @Override // com.coveiot.android.leonardo.quickreply.dialog.QuickReplyDialog.OnItemClickListener
    public void onEditedItemClickListener(@NotNull String editedString, int i) {
        Intrinsics.checkNotNullParameter(editedString, "editedString");
        TextView textView = this.t;
        ManageQuickReplyListAdapter manageQuickReplyListAdapter = null;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("saveText");
            textView = null;
        }
        textView.setAlpha(1.0f);
        ((Button) _$_findCachedViewById(R.id.btn_ok)).setEnabled(true);
        this.s = true;
        this.r.get(i).setQuickReplyMessage(editedString);
        ManageQuickReplyListAdapter manageQuickReplyListAdapter2 = this.q;
        if (manageQuickReplyListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("manageQuickReplyListAdapter");
        } else {
            manageQuickReplyListAdapter = manageQuickReplyListAdapter2;
        }
        manageQuickReplyListAdapter.notifyDataSetChanged();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void onShowProgressDialog() {
        String string = getString(R.string.please_wait);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_wait)");
        showProgresswithMsg(string);
    }

    @Override // com.coveiot.android.dashboard2.listener.ViewModelListener
    public void onSuccess() {
    }

    public final void setBoolSaveVisible(boolean z) {
        this.s = z;
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showErrorDialog() {
        onDismiss();
        String string = getString(R.string.setting_couldnot_save);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.setting_couldnot_save)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.ok)");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageQuickReply.F(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    @Override // com.coveiot.android.leonardo.listener.DialogListener
    public void showSuccessDialog() {
        onDismiss();
        String string = getString(R.string.success_message);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.success_message)");
        final BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle = new BottomSheetDialogOneButtonOneTitle(this, string);
        String string2 = getString(R.string.ok);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(\n            R.string.ok\n        )");
        bottomSheetDialogOneButtonOneTitle.setPositiveButton(string2, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.quickreply.activity.e
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityManageQuickReply.G(BottomSheetDialogOneButtonOneTitle.this, this, view);
            }
        });
        bottomSheetDialogOneButtonOneTitle.show();
    }

    public final boolean y() {
        List<String> quickReplyMessage;
        QuickReplyListModel quickReplyListFromPref = UserDataManager.getInstance(this).getQuickReplyListFromPref();
        ArrayList arrayList = new ArrayList();
        if ((quickReplyListFromPref != null ? quickReplyListFromPref.getQuickReplyMessage() : null) != null) {
            Integer valueOf = (quickReplyListFromPref == null || (quickReplyMessage = quickReplyListFromPref.getQuickReplyMessage()) == null) ? null : Integer.valueOf(quickReplyMessage.size());
            Intrinsics.checkNotNull(valueOf);
            if (valueOf.intValue() > 0) {
                List<String> quickReplyMessage2 = quickReplyListFromPref != null ? quickReplyListFromPref.getQuickReplyMessage() : null;
                Intrinsics.checkNotNull(quickReplyMessage2);
                for (String str : quickReplyMessage2) {
                    arrayList.add(new QuickReplyModel(str));
                }
                return !this.r.containsAll(arrayList);
            }
        }
        return false;
    }
}
