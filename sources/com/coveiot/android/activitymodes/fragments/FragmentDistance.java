package com.coveiot.android.activitymodes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.fragment.app.Fragment;
import com.coveiot.android.activitymodes.R;
import com.coveiot.covepreferences.UserDataManager;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class FragmentDistance extends Fragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public float h;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentDistance newInstance() {
            return new FragmentDistance();
        }
    }

    @DebugMetadata(c = "com.coveiot.android.activitymodes.fragments.FragmentDistance", f = "FragmentDistance.kt", i = {0}, l = {119}, m = "getTargetInKm", n = {TypedValues.AttributesType.S_TARGET}, s = {"L$0"})
    /* loaded from: classes2.dex */
    public static final class a extends ContinuationImpl {
        public Object L$0;
        public int label;
        public /* synthetic */ Object result;

        public a(Continuation<? super a> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        @Nullable
        public final Object invokeSuspend(@NotNull Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FragmentDistance.this.a(this);
        }
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

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(kotlin.coroutines.Continuation<? super java.lang.Float> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.coveiot.android.activitymodes.fragments.FragmentDistance.a
            if (r0 == 0) goto L13
            r0 = r9
            com.coveiot.android.activitymodes.fragments.FragmentDistance$a r0 = (com.coveiot.android.activitymodes.fragments.FragmentDistance.a) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.coveiot.android.activitymodes.fragments.FragmentDistance$a r0 = new com.coveiot.android.activitymodes.fragments.FragmentDistance$a
            r0.<init>(r9)
        L18:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 5000(0x1388, float:7.006E-42)
            r4 = 1
            if (r2 == 0) goto L37
            if (r2 != r4) goto L2f
            java.lang.Object r0 = r0.L$0
            kotlin.jvm.internal.Ref$FloatRef r0 = (kotlin.jvm.internal.Ref.FloatRef) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L68
        L2f:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L37:
            kotlin.ResultKt.throwOnFailure(r9)
            androidx.fragment.app.FragmentActivity r9 = r8.getActivity()
            if (r9 == 0) goto L93
            kotlin.jvm.internal.Ref$FloatRef r9 = new kotlin.jvm.internal.Ref$FloatRef
            r9.<init>()
            com.coveiot.android.activitymodes.utils.WorkoutUtils r2 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            java.lang.String r2 = r2.getCurrentDate()
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository$Companion r5 = com.coveiot.android.activitymodes.repository.PreparationPlanRepository.Companion
            androidx.fragment.app.FragmentActivity r6 = r8.getActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.Object r5 = r5.getInstance(r6)
            com.coveiot.android.activitymodes.repository.PreparationPlanRepository r5 = (com.coveiot.android.activitymodes.repository.PreparationPlanRepository) r5
            r0.L$0 = r9
            r0.label = r4
            java.lang.Object r0 = r5.getDayLevelInfoBasedOnDate(r2, r0)
            if (r0 != r1) goto L65
            return r1
        L65:
            r7 = r0
            r0 = r9
            r9 = r7
        L68:
            com.coveiot.android.activitymodes.database.entities.EntityPreparationDay r9 = (com.coveiot.android.activitymodes.database.entities.EntityPreparationDay) r9
            if (r9 == 0) goto L78
            com.coveiot.android.activitymodes.utils.WorkoutUtils r1 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            int r9 = r9.getTarget()
            float r9 = r1.convertMetersToKmFloat(r9)
            r0.element = r9
        L78:
            float r9 = r0.element
            r0 = 0
            int r0 = (r9 > r0 ? 1 : (r9 == r0 ? 0 : -1))
            if (r0 != 0) goto L80
            goto L81
        L80:
            r4 = 0
        L81:
            if (r4 == 0) goto L8e
            com.coveiot.android.activitymodes.utils.WorkoutUtils r9 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            float r9 = r9.convertMetersToKmFloat(r3)
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            return r9
        L8e:
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            return r9
        L93:
            com.coveiot.android.activitymodes.utils.WorkoutUtils r9 = com.coveiot.android.activitymodes.utils.WorkoutUtils.INSTANCE
            float r9 = r9.convertMetersToKmFloat(r3)
            java.lang.Float r9 = kotlin.coroutines.jvm.internal.Boxing.boxFloat(r9)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.android.activitymodes.fragments.FragmentDistance.a(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void b() {
        kotlinx.coroutines.e.e(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new FragmentDistance$setDistanceGoal$1(this, null), 2, null);
    }

    public final float getValue() {
        return this.h;
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        return inflater.inflate(R.layout.fragment_distance, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        b();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Boolean isDistanceUnitInMile = UserDataManager.getInstance(getContext()).isDistanceUnitInMile();
        Intrinsics.checkNotNullExpressionValue(isDistanceUnitInMile, "getInstance(context).isDistanceUnitInMile");
        if (isDistanceUnitInMile.booleanValue()) {
            ((TextView) _$_findCachedViewById(R.id.kilometer_tv)).setText(getString(R.string.miles));
        } else {
            ((TextView) _$_findCachedViewById(R.id.kilometer_tv)).setText(getString(R.string.kilometers));
        }
    }

    public final void setValue(float f) {
        this.h = f;
    }
}
