package com.coveiot.android.navigation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.ViewModelFactory;
import com.coveiot.android.navigation.adapter.YourPlacesAdapter;
import com.coveiot.android.navigation.databinding.ActivityYourPlacesBinding;
import com.coveiot.android.navigation.interfaces.FavouritePlaceListener;
import com.coveiot.android.navigation.models.FavouritePlaceData;
import com.coveiot.android.navigation.utils.NavigationUtilsKt;
import com.coveiot.android.navigation.viewModels.ActivityYourPlacesViewModel;
import com.coveiot.android.theme.BaseActivity;
import com.coveiot.android.theme.BottomSheetDialogOneButtonOneTitle;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes5.dex */
public final class ActivityYourPlaces extends BaseActivity implements FavouritePlaceListener {
    public ActivityYourPlacesBinding binding;
    @Nullable
    public BottomSheetDialogOneButtonOneTitle q;
    public ActivityYourPlacesViewModel r;
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public String p = ActivityYourPlaces.class.getSimpleName();

    @DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityYourPlaces$fetchFavouritePlacesFromServer$1", f = "ActivityYourPlaces.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* loaded from: classes5.dex */
    public static final class a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int label;

        /* renamed from: com.coveiot.android.navigation.activities.ActivityYourPlaces$a$a  reason: collision with other inner class name */
        /* loaded from: classes5.dex */
        public static final class C0293a extends Lambda implements Function2<Boolean, GetFavouritePlacesRes, Unit> {
            public final /* synthetic */ ActivityYourPlaces this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public C0293a(ActivityYourPlaces activityYourPlaces) {
                super(2);
                this.this$0 = activityYourPlaces;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, GetFavouritePlacesRes getFavouritePlacesRes) {
                invoke(bool.booleanValue(), getFavouritePlacesRes);
                return Unit.INSTANCE;
            }

            public final void invoke(boolean z, @Nullable GetFavouritePlacesRes getFavouritePlacesRes) {
                List<FavouritePlace> favouritePlaceList;
                if (z) {
                    this.this$0.dismissProgress();
                    List<FavouritePlace> favouritePlaceList2 = getFavouritePlacesRes != null ? getFavouritePlacesRes.getFavouritePlaceList() : null;
                    int i = 0;
                    if (favouritePlaceList2 == null || favouritePlaceList2.isEmpty()) {
                        ActivityYourPlaces activityYourPlaces = this.this$0;
                        activityYourPlaces.t(NavigationUtilsKt.getFTUFavouritePlaces(activityYourPlaces));
                        return;
                    }
                    ArrayList arrayList = new ArrayList();
                    ArrayList arrayList2 = new ArrayList();
                    if (getFavouritePlacesRes != null && (favouritePlaceList = getFavouritePlacesRes.getFavouritePlaceList()) != null) {
                        for (Object obj : favouritePlaceList) {
                            int i2 = i + 1;
                            if (i < 0) {
                                CollectionsKt__CollectionsKt.throwIndexOverflow();
                            }
                            FavouritePlace favouritePlace = (FavouritePlace) obj;
                            arrayList2.add(new FavouritePlaceData(favouritePlace.getId(), favouritePlace.getMapApi(), favouritePlace.getMapplsPin(), favouritePlace.getSortIndex(), favouritePlace.getLabel(), favouritePlace.getName(), favouritePlace.getFullAddress(), favouritePlace.getLocation()));
                            arrayList.add(Integer.valueOf(favouritePlace.getSortIndex().intValue() - 1));
                            i = i2;
                        }
                    }
                    this.this$0.t(NavigationUtilsKt.replaceItemsInList(NavigationUtilsKt.getFTUFavouritePlaces(this.this$0), arrayList, arrayList2));
                    return;
                }
                this.this$0.dismissProgress();
                ActivityYourPlaces activityYourPlaces2 = this.this$0;
                String string = activityYourPlaces2.getString(R.string.some_thing_went_wrong);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                activityYourPlaces2.showErrorDialog(string);
            }
        }

        public a(Continuation<? super a> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @NotNull
        public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
            return new a(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        @Nullable
        public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
            return ((a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (!ActivityYourPlaces.this.isFinishing()) {
                    ActivityYourPlacesViewModel activityYourPlacesViewModel = ActivityYourPlaces.this.r;
                    if (activityYourPlacesViewModel == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                        activityYourPlacesViewModel = null;
                    }
                    activityYourPlacesViewModel.getFavouritePlaces(new C0293a(ActivityYourPlaces.this));
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public static final void v(ActivityYourPlaces this$0, View view) {
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

    @NotNull
    public final ActivityYourPlacesBinding getBinding() {
        ActivityYourPlacesBinding activityYourPlacesBinding = this.binding;
        if (activityYourPlacesBinding != null) {
            return activityYourPlacesBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final String getTAG() {
        return this.p;
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        ActivityYourPlacesBinding inflate = ActivityYourPlacesBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(layoutInflater)");
        setBinding(inflate);
        setContentView(getBinding().getRoot());
        this.r = (ActivityYourPlacesViewModel) new ViewModelProvider(this, new ViewModelFactory(this)).get(ActivityYourPlacesViewModel.class);
        u();
    }

    @Override // com.coveiot.android.navigation.interfaces.FavouritePlaceListener
    public void onFavouritePlaceSelected(boolean z, @Nullable FavouritePlaceData favouritePlaceData, @NotNull ArrayList<FavouritePlaceData> allPlaces) {
        Intrinsics.checkNotNullParameter(allPlaces, "allPlaces");
        if (AppUtils.isNetConnected(this)) {
            Intent intent = new Intent(this, ActivityAddOrEditFavouriteLocation.class);
            intent.putExtra("selectedPlace", new Gson().toJson(favouritePlaceData));
            startActivity(intent);
            return;
        }
        Toast.makeText(this, getString(R.string.no_internet_connection), 1).show();
    }

    @Override // com.coveiot.android.theme.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        r();
    }

    public final void r() {
        showProgress();
        if (AppUtils.isNetConnected(this)) {
            s();
            return;
        }
        String string = getString(R.string.please_enable_internet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.please_enable_internet)");
        showErrorDialog(string);
    }

    public final void s() {
        kotlinx.coroutines.e.e(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getMain(), null, new a(null), 2, null);
    }

    public final void setBinding(@NotNull ActivityYourPlacesBinding activityYourPlacesBinding) {
        Intrinsics.checkNotNullParameter(activityYourPlacesBinding, "<set-?>");
        this.binding = activityYourPlacesBinding;
    }

    public final void setTAG(String str) {
        this.p = str;
    }

    public final void showErrorDialog(String str) {
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle;
        String title;
        dismissProgress();
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle2 = this.q;
        boolean z = true;
        if (bottomSheetDialogOneButtonOneTitle2 != null) {
            Boolean valueOf = (bottomSheetDialogOneButtonOneTitle2 == null || (title = bottomSheetDialogOneButtonOneTitle2.getTitle()) == null) ? null : Boolean.valueOf(kotlin.text.m.equals(title, str, true));
            Intrinsics.checkNotNull(valueOf);
            z = true ^ valueOf.booleanValue();
        }
        if (this.q == null || z) {
            this.q = new BottomSheetDialogOneButtonOneTitle(this, str);
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle3 = this.q;
        if (bottomSheetDialogOneButtonOneTitle3 != null) {
            String string = getString(com.coveiot.android.theme.R.string.ok);
            Intrinsics.checkNotNullExpressionValue(string, "getString(com.coveiot.android.theme.R.string.ok)");
            bottomSheetDialogOneButtonOneTitle3.setPositiveButton(string, new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.ActivityYourPlaces$showErrorDialog$1
                @Override // android.view.View.OnClickListener
                public void onClick(@Nullable View view) {
                    BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4;
                    bottomSheetDialogOneButtonOneTitle4 = ActivityYourPlaces.this.q;
                    if (bottomSheetDialogOneButtonOneTitle4 != null) {
                        bottomSheetDialogOneButtonOneTitle4.dismiss();
                    }
                    ActivityYourPlaces.this.finish();
                }
            });
        }
        BottomSheetDialogOneButtonOneTitle bottomSheetDialogOneButtonOneTitle4 = this.q;
        Boolean valueOf2 = bottomSheetDialogOneButtonOneTitle4 != null ? Boolean.valueOf(bottomSheetDialogOneButtonOneTitle4.isShowing()) : null;
        Intrinsics.checkNotNull(valueOf2);
        if ((!valueOf2.booleanValue() || z) && (bottomSheetDialogOneButtonOneTitle = this.q) != null) {
            bottomSheetDialogOneButtonOneTitle.show();
        }
    }

    public final void t(ArrayList<FavouritePlaceData> arrayList) {
        getBinding().rvYourPlaces.setAdapter(new YourPlacesAdapter(this, arrayList));
    }

    public final void u() {
        ((TextView) findViewById(R.id.toolbar_title)).setText(getResources().getString(R.string.your_places));
        ((TextView) findViewById(R.id.toolbar_back_arrow)).setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.android.navigation.activities.f2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityYourPlaces.v(ActivityYourPlaces.this, view);
            }
        });
    }
}
