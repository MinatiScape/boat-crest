package com.coveiot.leaderboard.views.fragment;

import android.content.Context;
import android.content.res.Resources;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.coveiot.android.theme.BaseFragment;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.databinding.FragmentCurrentLocationMapBinding;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.utils.LocationUtils;
import com.coveiot.leaderboard.views.activities.ActivityAddress;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonObject;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public final class FragmentCurrentLocationMap extends BaseFragment implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public Map<Integer, View> _$_findViewCache = new LinkedHashMap();
    public FragmentCurrentLocationMapBinding m;
    @Nullable
    public GoogleMap n;
    @Nullable
    public MarkerOptions o;
    @Nullable
    public String p;
    @Nullable
    public JsonObject q;

    /* loaded from: classes9.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final FragmentCurrentLocationMap newInstance() {
            return new FragmentCurrentLocationMap();
        }
    }

    public static final void n(FragmentCurrentLocationMap this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onSelectAddressClick();
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
        return this.p;
    }

    @Nullable
    public final JsonObject getAddressRequestObject() {
        return this.q;
    }

    @Nullable
    public final MarkerOptions getMarkerOptions() {
        return this.o;
    }

    public final FragmentCurrentLocationMapBinding l() {
        FragmentCurrentLocationMapBinding fragmentCurrentLocationMapBinding = this.m;
        if (fragmentCurrentLocationMapBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("_binding");
            return null;
        }
        return fragmentCurrentLocationMapBinding;
    }

    public final void m(LatLng latLng) {
        LocationUtils locationUtils = LocationUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        JsonObject addressFromLatLong = locationUtils.getAddressFromLatLong(requireContext, latLng.latitude, latLng.longitude);
        String valueOf = String.valueOf(addressFromLatLong);
        this.p = valueOf;
        this.q = addressFromLatLong;
        o(valueOf);
    }

    public final void o(String str) {
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
            TextView textView = l().location.tvCurrentLocationData;
            textView.setText(string + ", " + string2 + ", " + string3);
        }
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentCurrentLocationMapBinding inflate = FragmentCurrentLocationMapBinding.inflate(inflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(inflater,container,false)");
        this.m = inflate;
        return l().getRoot();
    }

    @Override // com.coveiot.android.theme.BaseFragment, androidx.fragment.app.Fragment
    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    @Override // com.google.android.gms.maps.OnMapReadyCallback
    public void onMapReady(@Nullable GoogleMap googleMap) {
        this.n = googleMap;
        LocationUtils locationUtils = LocationUtils.INSTANCE;
        Context requireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(requireContext, "requireContext()");
        Location location = locationUtils.getLocation(requireContext);
        if (location != null) {
            m(new LatLng(location.getLatitude(), location.getLongitude()));
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            this.o = new MarkerOptions().position(latLng).title("Current Location").draggable(true);
            GoogleMap googleMap2 = this.n;
            Intrinsics.checkNotNull(googleMap2);
            googleMap2.addMarker(this.o);
            GoogleMap googleMap3 = this.n;
            Intrinsics.checkNotNull(googleMap3);
            googleMap3.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15.0f));
            GoogleMap googleMap4 = this.n;
            Intrinsics.checkNotNull(googleMap4);
            googleMap4.setOnMarkerDragListener(this);
            return;
        }
        Toast.makeText(requireContext(), requireContext().getString(R.string.something_went_wrong), 0).show();
        requireActivity().finish();
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDrag(@Nullable Marker marker) {
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDragEnd(@Nullable Marker marker) {
        LatLng position;
        LatLng position2;
        Double d = null;
        if (marker != null) {
            LatLng position3 = marker.getPosition();
            marker.setSnippet(String.valueOf(position3 != null ? Double.valueOf(position3.latitude) : null));
        }
        GoogleMap googleMap = this.n;
        Intrinsics.checkNotNull(googleMap);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker != null ? marker.getPosition() : null, 15.0f));
        Double valueOf = (marker == null || (position2 = marker.getPosition()) == null) ? null : Double.valueOf(position2.latitude);
        Intrinsics.checkNotNull(valueOf);
        valueOf.doubleValue();
        if (marker != null && (position = marker.getPosition()) != null) {
            d = Double.valueOf(position.longitude);
        }
        Intrinsics.checkNotNull(d);
        d.doubleValue();
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnMarkerDragListener
    public void onMarkerDragStart(@Nullable Marker marker) {
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    public final void onSelectAddressClick() {
        JsonObject jsonObject = this.q;
        if (jsonObject != null) {
            Intrinsics.checkNotNull(jsonObject);
            if (jsonObject.size() != 0) {
                ActivityAddress activityAddress = (ActivityAddress) getActivity();
                if (activityAddress != null) {
                    JsonObject jsonObject2 = this.q;
                    Intrinsics.checkNotNull(jsonObject2);
                    activityAddress.postAddressToServer(jsonObject2);
                    return;
                }
                return;
            }
        }
        requireActivity().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(@NotNull View view, @Nullable Bundle bundle) {
        Resources resources;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Fragment findFragmentById = getChildFragmentManager().findFragmentById(R.id.map);
        Intrinsics.checkNotNull(findFragmentById, "null cannot be cast to non-null type com.google.android.gms.maps.SupportMapFragment");
        ((SupportMapFragment) findFragmentById).getMapAsync(this);
        FragmentActivity activity = getActivity();
        String str = null;
        TextView textView = activity != null ? (TextView) activity.findViewById(R.id.toolbar_title) : null;
        if (textView != null) {
            FragmentActivity activity2 = getActivity();
            if (activity2 != null && (resources = activity2.getResources()) != null) {
                str = resources.getString(R.string.current_location);
            }
            textView.setText(str);
        }
        ImageView imageView = l().location.ivArrow;
        Intrinsics.checkNotNullExpressionValue(imageView, "binding.location.ivArrow");
        gone(imageView);
        l().selectAddress.setOnClickListener(new View.OnClickListener() { // from class: com.coveiot.leaderboard.views.fragment.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                FragmentCurrentLocationMap.n(FragmentCurrentLocationMap.this, view2);
            }
        });
        LeaderBoardDataUtiils.saveIsFragmentAddress(requireContext(), false);
    }

    public final void setAddressData(@Nullable String str) {
        this.p = str;
    }

    public final void setAddressRequestObject(@Nullable JsonObject jsonObject) {
        this.q = jsonObject;
    }

    public final void setMarkerOptions(@Nullable MarkerOptions markerOptions) {
        this.o = markerOptions;
    }
}
