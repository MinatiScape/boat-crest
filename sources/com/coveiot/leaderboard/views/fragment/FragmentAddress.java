package com.coveiot.leaderboard.views.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.AddressFragmentBinding;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.leaderboard.utils.LocationUtils;
import com.coveiot.leaderboard.views.fragment.FragmentAddress;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.gson.JsonObject;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class FragmentAddress extends BaseFragment {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public AddressFragmentBinding m;
    @Nullable
    public String n;
    @Nullable
    public AutocompleteSupportFragment o;
    @Nullable
    public JsonObject p;
    @Nullable
    public ImageView q;
    @Nullable
    public EditText r;
    @Nullable
    public PlacesClient s;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentAddress newInstance() {
            return new FragmentAddress();
        }
    }

    /* loaded from: classes9.dex */
    public final class GetAddressAsync extends AsyncTask<Place, Void, JsonObject> {
        public GetAddressAsync() {
        }

        @Override // android.os.AsyncTask
        @Nullable
        public JsonObject doInBackground(@NotNull Place... strings) {
            Intrinsics.checkNotNullParameter(strings, "strings");
            Place place = strings[0];
            LocationUtils locationUtils = LocationUtils.INSTANCE;
            FragmentActivity requireActivity = FragmentAddress.this.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
            Intrinsics.checkNotNull(place);
            LatLng latLng = place.getLatLng();
            Intrinsics.checkNotNull(latLng);
            double d = latLng.latitude;
            LatLng latLng2 = place.getLatLng();
            Intrinsics.checkNotNull(latLng2);
            return locationUtils.getCompleteAddressString(requireActivity, place, d, latLng2.longitude);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(@Nullable JsonObject jsonObject) {
            if (jsonObject != null) {
                FragmentAddress.this.setAddressRequestObject(jsonObject);
            }
            super.onPostExecute((GetAddressAsync) jsonObject);
        }
    }

    public static final void n(FragmentAddress this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onSelectAddressClick();
    }

    public static final void o(FragmentAddress this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, FragmentCurrentLocationMap.Companion.newInstance()).commitAllowingStateLoss();
    }

    @Override // com.coveiot.android.theme.BaseFragment
    public void _$_clearFindViewByIdCache() {
        this._$_findViewCache.clear();
    }

    @Override // com.coveiot.android.theme.BaseFragment
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

    @Nullable
    public final String getAddressData() {
        return this.n;
    }

    @Nullable
    public final JsonObject getAddressRequestObject() {
        return this.p;
    }

    @Nullable
    public final AutocompleteSupportFragment getPlaceAutocompleteFragment() {
        return this.o;
    }

    @Nullable
    public final ImageView getSearchIcon() {
        return this.q;
    }

    public final AddressFragmentBinding m() {
        AddressFragmentBinding addressFragmentBinding = this.m;
        if (addressFragmentBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return addressFragmentBinding;
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        AddressFragmentBinding inflate = AddressFragmentBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return m().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, @NotNull String[] permissions, @NotNull int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(i, permissions, grantResults);
        if (i != 99 || grantResults.length <= 0) {
            return;
        }
        int i2 = grantResults[0];
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0038, code lost:
        if ((r0.length() == 0) == true) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onSelectAddressClick() {
        /*
            r3 = this;
            com.google.android.libraries.places.widget.AutocompleteSupportFragment r0 = r3.o
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            android.view.View r0 = r0.requireView()
            int r1 = com.coveiot.leaderboard.R.id.places_autocomplete_search_input
            android.view.View r0 = r0.findViewById(r1)
            java.lang.String r1 = "null cannot be cast to non-null type android.widget.EditText"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0, r1)
            android.widget.EditText r0 = (android.widget.EditText) r0
            android.text.Editable r0 = r0.getText()
            java.lang.String r0 = r0.toString()
            int r0 = r0.length()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L28
            r0 = r1
            goto L29
        L28:
            r0 = r2
        L29:
            if (r0 == 0) goto L4a
            java.lang.String r0 = r3.n
            if (r0 == 0) goto L3b
            int r0 = r0.length()
            if (r0 != 0) goto L37
            r0 = r1
            goto L38
        L37:
            r0 = r2
        L38:
            if (r0 != r1) goto L3b
            goto L3c
        L3b:
            r1 = r2
        L3c:
            if (r1 == 0) goto L4a
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            com.coveiot.leaderboard.views.activities.ActivityAddress r0 = (com.coveiot.leaderboard.views.activities.ActivityAddress) r0
            if (r0 == 0) goto L6f
            r0.showImageConfirmationDialog()
            goto L6f
        L4a:
            com.google.gson.JsonObject r0 = r3.p
            if (r0 == 0) goto L68
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            int r0 = r0.size()
            if (r0 == 0) goto L68
            androidx.fragment.app.FragmentActivity r0 = r3.getActivity()
            com.coveiot.leaderboard.views.activities.ActivityAddress r0 = (com.coveiot.leaderboard.views.activities.ActivityAddress) r0
            if (r0 == 0) goto L6f
            com.google.gson.JsonObject r1 = r3.p
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r0.postAddressToServer(r1)
            goto L6f
        L68:
            androidx.fragment.app.FragmentActivity r0 = r3.requireActivity()
            r0.finish()
        L6f:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.coveiot.leaderboard.views.fragment.FragmentAddress.onSelectAddressClick():void");
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        LocationUtils locationUtils = LocationUtils.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity()");
        locationUtils.checkLocationPermission(requireActivity);
        LeaderBoardDataUtiils.saveIsFragmentAddress(requireContext(), true);
        LeaderboardUtils.getMetadata(requireActivity());
        if (LeaderboardUtils.API_KEY == null) {
            return;
        }
        Places.initialize(requireActivity(), LeaderboardUtils.API_KEY);
        this.s = Places.createClient(requireActivity());
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.address_place_autocomplete_fragment);
        this.o = autocompleteSupportFragment;
        Intrinsics.checkNotNull(autocompleteSupportFragment);
        autocompleteSupportFragment.setPlaceFields(CollectionsKt__CollectionsKt.listOf((Object[]) new Place.Field[]{Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG}));
        AutocompleteSupportFragment autocompleteSupportFragment2 = this.o;
        Intrinsics.checkNotNull(autocompleteSupportFragment2);
        LinearLayout linearLayout = (LinearLayout) autocompleteSupportFragment2.getView();
        Intrinsics.checkNotNull(linearLayout);
        View childAt = linearLayout.getChildAt(0);
        Intrinsics.checkNotNull(childAt, "null cannot be cast to non-null type android.widget.ImageView");
        ImageView imageView = (ImageView) childAt;
        this.q = imageView;
        Intrinsics.checkNotNull(imageView);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.ic_search_red));
        AutocompleteSupportFragment autocompleteSupportFragment3 = this.o;
        Intrinsics.checkNotNull(autocompleteSupportFragment3);
        LinearLayout linearLayout2 = (LinearLayout) autocompleteSupportFragment3.getView();
        Intrinsics.checkNotNull(linearLayout2);
        linearLayout2.setBackgroundDrawable(getResources().getDrawable(R.drawable.rounded_light_grey_background_70dp));
        AutocompleteSupportFragment autocompleteSupportFragment4 = this.o;
        Intrinsics.checkNotNull(autocompleteSupportFragment4);
        LinearLayout linearLayout3 = (LinearLayout) autocompleteSupportFragment4.getView();
        Intrinsics.checkNotNull(linearLayout3);
        View findViewById = linearLayout3.findViewById(R.id.places_autocomplete_search_input);
        Intrinsics.checkNotNull(findViewById, "null cannot be cast to non-null type android.widget.EditText");
        EditText editText = (EditText) findViewById;
        this.r = editText;
        Intrinsics.checkNotNull(editText);
        Resources resources = getResources();
        int i = R.color.color_9c9c9c;
        editText.setTextColor(resources.getColor(i));
        EditText editText2 = this.r;
        Intrinsics.checkNotNull(editText2);
        editText2.setTextSize(14.0f);
        EditText editText3 = this.r;
        Intrinsics.checkNotNull(editText3);
        editText3.setHintTextColor(getResources().getColor(i));
        EditText editText4 = this.r;
        Intrinsics.checkNotNull(editText4);
        editText4.setPadding(0, 0, 0, 0);
        AutocompleteSupportFragment autocompleteSupportFragment5 = this.o;
        Intrinsics.checkNotNull(autocompleteSupportFragment5);
        autocompleteSupportFragment5.setOnPlaceSelectedListener(new PlaceSelectionListener() { // from class: com.coveiot.leaderboard.views.fragment.FragmentAddress$onViewCreated$1
            @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
            public void onError(@NotNull Status status) {
                Intrinsics.checkNotNullParameter(status, "status");
                Toast.makeText(FragmentAddress.this.requireActivity(), FragmentAddress.this.getString(R.string.something_went_wrong), 0).show();
            }

            @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
            public void onPlaceSelected(@NotNull Place place) {
                Intrinsics.checkNotNullParameter(place, "place");
                new FragmentAddress.GetAddressAsync().execute(place);
            }
        });
        AutocompleteSupportFragment autocompleteSupportFragment6 = this.o;
        Intrinsics.checkNotNull(autocompleteSupportFragment6);
        autocompleteSupportFragment6.setHint(getString(R.string.search_for_area));
        q();
        m().selectAddress.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.fragment.a
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentAddress.n(FragmentAddress.this, view2);
            }
        });
        m().location.clCurrentLocation.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.fragment.b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentAddress.o(FragmentAddress.this, view2);
            }
        });
    }

    public final void p(String str) {
        if (str != null) {
            if (str.length() == 0) {
                return;
            }
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("locality");
            String string2 = jSONObject.getString(GeoCodingCriteria.POD_CITY);
            String string3 = jSONObject.getString("country");
            if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2) || TextUtils.isEmpty(string3)) {
                return;
            }
            TextView textView = m().location.tvCurrentLocationData;
            textView.setText(string + ", " + string2 + ", " + string3);
        }
    }

    public final void q() {
        LocationUtils locationUtils = LocationUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Location location = locationUtils.getLocation(requireContext);
        if (location != null) {
            Context requireContext2 = requireContext();
            Intrinsics.checkNotNullExpressionValue(requireContext2, "requireContext()");
            JsonObject addressFromLatLong = locationUtils.getAddressFromLatLong(requireContext2, location.getLatitude(), location.getLongitude());
            String valueOf = String.valueOf(addressFromLatLong);
            this.n = valueOf;
            this.p = addressFromLatLong;
            p(valueOf);
        }
    }

    public final void setAddressData(@Nullable String str) {
        this.n = str;
    }

    public final void setAddressRequestObject(@Nullable JsonObject jsonObject) {
        this.p = jsonObject;
    }

    public final void setPlaceAutocompleteFragment(@Nullable AutocompleteSupportFragment autocompleteSupportFragment) {
        this.o = autocompleteSupportFragment;
    }

    public final void setSearchIcon(@Nullable ImageView imageView) {
        this.q = imageView;
    }
}
