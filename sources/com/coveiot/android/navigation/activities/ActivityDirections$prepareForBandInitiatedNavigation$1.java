package com.coveiot.android.navigation.activities;

import android.content.Intent;
import android.location.Location;
import com.coveiot.android.navigation.R;
import com.coveiot.android.navigation.models.AutoSuggestionData;
import com.coveiot.android.navigation.models.NavigationDisclaimerData;
import com.coveiot.android.navigation.viewModels.ActivityNavigationDirectionsViewmodel;
import com.coveiot.coveaccess.navigation.GetFavouritePlacesRes;
import com.coveiot.coveaccess.navigation.model.FavouritePlace;
import com.coveiot.covepreferences.SessionManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mappls.sdk.services.api.directions.models.DirectionsResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
@DebugMetadata(c = "com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1", f = "ActivityDirections.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes5.dex */
public final class ActivityDirections$prepareForBandInitiatedNavigation$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int label;
    public final /* synthetic */ ActivityDirections this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ActivityDirections$prepareForBandInitiatedNavigation$1(ActivityDirections activityDirections, Continuation<? super ActivityDirections$prepareForBandInitiatedNavigation$1> continuation) {
        super(2, continuation);
        this.this$0 = activityDirections;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @NotNull
    public final Continuation<Unit> create(@Nullable Object obj, @NotNull Continuation<?> continuation) {
        return new ActivityDirections$prepareForBandInitiatedNavigation$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    @Nullable
    public final Object invoke(@NotNull CoroutineScope coroutineScope, @Nullable Continuation<? super Unit> continuation) {
        return ((ActivityDirections$prepareForBandInitiatedNavigation$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    @Nullable
    public final Object invokeSuspend(@NotNull Object obj) {
        kotlin.coroutines.intrinsics.a.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = this.this$0.v;
            if (activityNavigationDirectionsViewmodel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                activityNavigationDirectionsViewmodel = null;
            }
            final ActivityDirections activityDirections = this.this$0;
            activityNavigationDirectionsViewmodel.getAppDisclaimer(new Function1<Boolean, Unit>() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1.1

                /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a */
                /* loaded from: classes5.dex */
                public static final class a extends Lambda implements Function3<String, Boolean, Boolean, Unit> {
                    public final /* synthetic */ ActivityDirections this$0;

                    /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a$a  reason: collision with other inner class name */
                    /* loaded from: classes5.dex */
                    public static final class C0286a extends Lambda implements Function2<Boolean, FavouritePlace, Unit> {
                        public final /* synthetic */ ActivityDirections this$0;

                        /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a$a$a  reason: collision with other inner class name */
                        /* loaded from: classes5.dex */
                        public static final class C0287a extends Lambda implements Function2<Boolean, ArrayList<AutoSuggestionData>, Unit> {
                            public final /* synthetic */ ActivityDirections this$0;

                            /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a$a$a$a  reason: collision with other inner class name */
                            /* loaded from: classes5.dex */
                            public static final class C0288a extends Lambda implements Function1<DirectionsResponse, Unit> {
                                public final /* synthetic */ ArrayList<AutoSuggestionData> $autoSuggestionData;
                                public final /* synthetic */ ActivityDirections this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public C0288a(ActivityDirections activityDirections, ArrayList<AutoSuggestionData> arrayList) {
                                    super(1);
                                    this.this$0 = activityDirections;
                                    this.$autoSuggestionData = arrayList;
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(DirectionsResponse directionsResponse) {
                                    invoke2(directionsResponse);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                public final void invoke2(@Nullable DirectionsResponse directionsResponse) {
                                    if (directionsResponse != null) {
                                        this.this$0.V(this.$autoSuggestionData.get(0), directionsResponse, 0);
                                        this.this$0.t = this.$autoSuggestionData.get(0);
                                        this.this$0.s = directionsResponse;
                                        this.this$0.r = 0;
                                        ActivityDirections activityDirections = this.this$0;
                                        activityDirections.B = activityDirections.getModeOnBand() == 0 ? "walking" : "driving";
                                        this.this$0.d0();
                                        return;
                                    }
                                    this.this$0.dismissProgress();
                                    ActivityDirections activityDirections2 = this.this$0;
                                    String string = activityDirections2.getString(R.string.some_thing_went_wrong);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                                    activityDirections2.showErrorDialog(string);
                                }
                            }

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public C0287a(ActivityDirections activityDirections) {
                                super(2);
                                this.this$0 = activityDirections;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, ArrayList<AutoSuggestionData> arrayList) {
                                invoke(bool.booleanValue(), arrayList);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z, @Nullable ArrayList<AutoSuggestionData> arrayList) {
                                if (z) {
                                    ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = null;
                                    Integer valueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
                                    Intrinsics.checkNotNull(valueOf);
                                    if (valueOf.intValue() > 0) {
                                        ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel2 = this.this$0.v;
                                        if (activityNavigationDirectionsViewmodel2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        } else {
                                            activityNavigationDirectionsViewmodel = activityNavigationDirectionsViewmodel2;
                                        }
                                        AutoSuggestionData autoSuggestionData = arrayList.get(0);
                                        Intrinsics.checkNotNullExpressionValue(autoSuggestionData, "autoSuggestionData[0]");
                                        activityNavigationDirectionsViewmodel.getEta(autoSuggestionData, this.this$0.y, this.this$0.getModeOnBand(), new C0288a(this.this$0, arrayList));
                                        return;
                                    }
                                }
                                this.this$0.dismissProgress();
                                ActivityDirections activityDirections = this.this$0;
                                String string = activityDirections.getString(R.string.some_thing_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                                activityDirections.showErrorDialog(string);
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public C0286a(ActivityDirections activityDirections) {
                            super(2);
                            this.this$0 = activityDirections;
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, FavouritePlace favouritePlace) {
                            invoke(bool.booleanValue(), favouritePlace);
                            return Unit.INSTANCE;
                        }

                        public final void invoke(boolean z, @Nullable FavouritePlace favouritePlace) {
                            if (z && favouritePlace != null) {
                                ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = this.this$0.v;
                                if (activityNavigationDirectionsViewmodel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    activityNavigationDirectionsViewmodel = null;
                                }
                                Location location = this.this$0.y;
                                Intrinsics.checkNotNull(location);
                                String mapplsPin = favouritePlace.getMapplsPin();
                                Intrinsics.checkNotNullExpressionValue(mapplsPin, "favouritePlace.mapplsPin");
                                activityNavigationDirectionsViewmodel.autoSuggestPlace(location, mapplsPin, new C0287a(this.this$0));
                                return;
                            }
                            this.this$0.dismissProgress();
                            ActivityDirections activityDirections = this.this$0;
                            String string = activityDirections.getString(R.string.some_thing_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                            activityDirections.showErrorDialog(string);
                        }
                    }

                    /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a$b */
                    /* loaded from: classes5.dex */
                    public static final class b extends Lambda implements Function3<GetFavouritePlacesRes, String, Boolean, Unit> {
                        public final /* synthetic */ ActivityDirections this$0;

                        /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a$b$a  reason: collision with other inner class name */
                        /* loaded from: classes5.dex */
                        public static final class C0289a extends Lambda implements Function2<Boolean, ArrayList<AutoSuggestionData>, Unit> {
                            public final /* synthetic */ ActivityDirections this$0;

                            /* renamed from: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$a$b$a$a  reason: collision with other inner class name */
                            /* loaded from: classes5.dex */
                            public static final class C0290a extends Lambda implements Function1<DirectionsResponse, Unit> {
                                public final /* synthetic */ ArrayList<AutoSuggestionData> $autoSuggestionData;
                                public final /* synthetic */ ActivityDirections this$0;

                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                public C0290a(ActivityDirections activityDirections, ArrayList<AutoSuggestionData> arrayList) {
                                    super(1);
                                    this.this$0 = activityDirections;
                                    this.$autoSuggestionData = arrayList;
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(DirectionsResponse directionsResponse) {
                                    invoke2(directionsResponse);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke  reason: avoid collision after fix types in other method */
                                public final void invoke2(@Nullable DirectionsResponse directionsResponse) {
                                    if (directionsResponse != null) {
                                        this.this$0.V(this.$autoSuggestionData.get(0), directionsResponse, 0);
                                        this.this$0.t = this.$autoSuggestionData.get(0);
                                        this.this$0.s = directionsResponse;
                                        this.this$0.r = 0;
                                        ActivityDirections activityDirections = this.this$0;
                                        activityDirections.B = activityDirections.getModeOnBand() == 0 ? "walking" : "driving";
                                        this.this$0.d0();
                                        return;
                                    }
                                    this.this$0.dismissProgress();
                                    ActivityDirections activityDirections2 = this.this$0;
                                    String string = activityDirections2.getString(R.string.some_thing_went_wrong);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                                    activityDirections2.showErrorDialog(string);
                                }
                            }

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            public C0289a(ActivityDirections activityDirections) {
                                super(2);
                                this.this$0 = activityDirections;
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool, ArrayList<AutoSuggestionData> arrayList) {
                                invoke(bool.booleanValue(), arrayList);
                                return Unit.INSTANCE;
                            }

                            public final void invoke(boolean z, @Nullable ArrayList<AutoSuggestionData> arrayList) {
                                if (z) {
                                    ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = null;
                                    Integer valueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
                                    Intrinsics.checkNotNull(valueOf);
                                    if (valueOf.intValue() > 0) {
                                        ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel2 = this.this$0.v;
                                        if (activityNavigationDirectionsViewmodel2 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                        } else {
                                            activityNavigationDirectionsViewmodel = activityNavigationDirectionsViewmodel2;
                                        }
                                        AutoSuggestionData autoSuggestionData = arrayList.get(0);
                                        Intrinsics.checkNotNullExpressionValue(autoSuggestionData, "autoSuggestionData[0]");
                                        activityNavigationDirectionsViewmodel.getEta(autoSuggestionData, this.this$0.y, this.this$0.getModeOnBand(), new C0290a(this.this$0, arrayList));
                                        return;
                                    }
                                }
                                this.this$0.dismissProgress();
                                ActivityDirections activityDirections = this.this$0;
                                String string = activityDirections.getString(R.string.some_thing_went_wrong);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                                activityDirections.showErrorDialog(string);
                            }
                        }

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        public b(ActivityDirections activityDirections) {
                            super(3);
                            this.this$0 = activityDirections;
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(GetFavouritePlacesRes getFavouritePlacesRes, String str, Boolean bool) {
                            invoke(getFavouritePlacesRes, str, bool.booleanValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(@Nullable GetFavouritePlacesRes getFavouritePlacesRes, @NotNull String fetchFavouritePlaceMsg, boolean z) {
                            Intrinsics.checkNotNullParameter(fetchFavouritePlaceMsg, "fetchFavouritePlaceMsg");
                            if (z && getFavouritePlacesRes != null && getFavouritePlacesRes.getFavouritePlaceList().size() > 0) {
                                List<FavouritePlace> favouritePlaceList = getFavouritePlacesRes.getFavouritePlaceList();
                                Intrinsics.checkNotNullExpressionValue(favouritePlaceList, "allFavouritePlaces.favouritePlaceList");
                                ActivityDirections activityDirections = this.this$0;
                                ArrayList arrayList = new ArrayList();
                                Iterator<T> it = favouritePlaceList.iterator();
                                while (true) {
                                    boolean z2 = false;
                                    if (!it.hasNext()) {
                                        break;
                                    }
                                    Object next = it.next();
                                    Integer sortIndex = ((FavouritePlace) next).getSortIndex();
                                    int placeIdOnBand = activityDirections.getPlaceIdOnBand();
                                    if (sortIndex != null && sortIndex.intValue() == placeIdOnBand) {
                                        z2 = true;
                                    }
                                    if (z2) {
                                        arrayList.add(next);
                                    }
                                }
                                ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = this.this$0.v;
                                if (activityNavigationDirectionsViewmodel == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                    activityNavigationDirectionsViewmodel = null;
                                }
                                Location location = this.this$0.y;
                                Intrinsics.checkNotNull(location);
                                String mapplsPin = ((FavouritePlace) arrayList.get(0)).getMapplsPin();
                                Intrinsics.checkNotNullExpressionValue(mapplsPin, "favPlace[0].mapplsPin");
                                activityNavigationDirectionsViewmodel.autoSuggestPlace(location, mapplsPin, new C0289a(this.this$0));
                                return;
                            }
                            this.this$0.dismissProgress();
                            this.this$0.showErrorDialog(fetchFavouritePlaceMsg);
                        }
                    }

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    public a(ActivityDirections activityDirections) {
                        super(3);
                        this.this$0 = activityDirections;
                    }

                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(String str, Boolean bool, Boolean bool2) {
                        invoke(str, bool.booleanValue(), bool2.booleanValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(@Nullable String str, boolean z, boolean z2) {
                        if (!z2) {
                            this.this$0.dismissProgress();
                            ActivityDirections activityDirections = this.this$0;
                            String string = activityDirections.getString(R.string.some_thing_went_wrong);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                            activityDirections.showErrorDialog(string);
                        } else if (z) {
                            ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel = null;
                            if (this.this$0.getPlaceIdOnBand() == 0) {
                                ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel2 = this.this$0.v;
                                if (activityNavigationDirectionsViewmodel2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                                } else {
                                    activityNavigationDirectionsViewmodel = activityNavigationDirectionsViewmodel2;
                                }
                                activityNavigationDirectionsViewmodel.fetchLastTrip(new C0286a(this.this$0));
                                return;
                            }
                            ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel3 = this.this$0.v;
                            if (activityNavigationDirectionsViewmodel3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            } else {
                                activityNavigationDirectionsViewmodel = activityNavigationDirectionsViewmodel3;
                            }
                            activityNavigationDirectionsViewmodel.fetchFavouritePlaces(new b(this.this$0));
                        } else {
                            this.this$0.dismissProgress();
                            Intent intent = new Intent(this.this$0, ActivityNavigationFTU.class);
                            intent.putExtra("isFromBand", true);
                            intent.putExtra("placeIdOnBand", this.this$0.getPlaceIdOnBand());
                            intent.putExtra("modeOnBand", this.this$0.getModeOnBand());
                            this.this$0.startActivity(intent);
                            this.this$0.finish();
                        }
                    }
                }

                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        Type type = new TypeToken<NavigationDisclaimerData>() { // from class: com.coveiot.android.navigation.activities.ActivityDirections$prepareForBandInitiatedNavigation$1$1$navigationDisclaimerDataType$1
                        }.getType();
                        Object fromJson = new Gson().fromJson(SessionManager.getInstance(ActivityDirections.this).getNavigationDiscliamerData(), type);
                        Intrinsics.checkNotNullExpressionValue(fromJson, "Gson().fromJson(navigati…gationDisclaimerDataType)");
                        NavigationDisclaimerData navigationDisclaimerData = (NavigationDisclaimerData) fromJson;
                        ActivityNavigationDirectionsViewmodel activityNavigationDirectionsViewmodel2 = ActivityDirections.this.v;
                        if (activityNavigationDirectionsViewmodel2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                            activityNavigationDirectionsViewmodel2 = null;
                        }
                        activityNavigationDirectionsViewmodel2.checkNavigationDisclaimerAcceptance(navigationDisclaimerData.getVersion(), new a(ActivityDirections.this));
                        return;
                    }
                    ActivityDirections.this.dismissProgress();
                    ActivityDirections activityDirections2 = ActivityDirections.this;
                    String string = activityDirections2.getString(R.string.some_thing_went_wrong);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.some_thing_went_wrong)");
                    activityDirections2.showErrorDialog(string);
                }
            });
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
