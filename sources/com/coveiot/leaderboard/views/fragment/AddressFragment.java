package com.coveiot.leaderboard.views.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.clevertap.android.sdk.Constants;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.CoveAnalyticsManager;
import com.coveiot.android.theme.BottomSheetDialogImageTitleMessage;
import com.coveiot.android.theme.BottomSheetDialogOneButtonTitleMessage;
import com.coveiot.android.theme.FirebaseConstants;
import com.coveiot.android.theme.FirebaseEventParams;
import com.coveiot.coveaccess.CoveApiListener;
import com.coveiot.coveaccess.leaderboard.CoveLeaderboardApi;
import com.coveiot.coveaccess.leaderboard.model.AddressModel;
import com.coveiot.coveaccess.leaderboard.model.AddressReq;
import com.coveiot.coveaccess.leaderboard.model.LocationType;
import com.coveiot.coveaccess.model.CoveApiErrorModel;
import com.coveiot.leaderboard.R;
import com.coveiot.leaderboard.services.LeaderBoardApiIntentService;
import com.coveiot.leaderboard.utils.LeaderBoardDataUtiils;
import com.coveiot.leaderboard.utils.LeaderboardUtils;
import com.coveiot.utils.utility.AppUtils;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.JsonObject;
import com.mappls.sdk.services.api.geocoding.GeoCodingCriteria;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes9.dex */
public class AddressFragment extends Fragment {
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    public AutocompleteSupportFragment h;
    public Button i;
    public ImageView j;
    public PlacesClient l;
    public EditText m;
    public BottomSheetDialogOneButtonTitleMessage saveConfirmationDialog = null;
    public BottomSheetDialogImageTitleMessage saveImageConfirmationDialog = null;
    public JsonObject k = null;

    /* loaded from: classes9.dex */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.RANK_BADGES_SCREEN.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_CONFIRMATION_POPUP.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.START_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            if (!AppUtils.isNetConnected(AddressFragment.this.getActivity())) {
                Toast.makeText(AddressFragment.this.getActivity(), AddressFragment.this.getString(R.string.please_check_network), 1).show();
                return;
            }
            LeaderBoardDataUtiils.saveFirstTimeLeaderBoardShown(AddressFragment.this.getActivity(), true);
            AddressFragment.this.onSelectAddressClick();
        }
    }

    /* loaded from: classes9.dex */
    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddressFragment addressFragment = AddressFragment.this;
            addressFragment.j.setImageDrawable(addressFragment.getResources().getDrawable(R.drawable.ic_location));
            AddressFragment addressFragment2 = AddressFragment.this;
            addressFragment2.j.setColorFilter(ContextCompat.getColor(addressFragment2.getActivity(), R.color.main_text_color));
            AddressFragment.this.m.getText().clear();
        }
    }

    /* loaded from: classes9.dex */
    public class c implements PlaceSelectionListener {
        public c() {
        }

        @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
        public void onError(Status status) {
            if (AddressFragment.this.isAdded()) {
                Toast.makeText(AddressFragment.this.getActivity(), AddressFragment.this.getString(R.string.something_went_wrong), 0).show();
            }
        }

        @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
        public void onPlaceSelected(Place place) {
            new h().execute(place);
        }
    }

    /* loaded from: classes9.dex */
    public class d implements View.OnClickListener {
        public final /* synthetic */ Dialog h;

        public d(Dialog dialog) {
            this.h = dialog;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ActivityCompat.requestPermissions(AddressFragment.this.getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
            this.h.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class e implements CoveApiListener<AddressModel, CoveApiErrorModel> {
        public e() {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: a */
        public void onError(CoveApiErrorModel coveApiErrorModel) {
        }

        @Override // com.coveiot.coveaccess.CoveApiListener
        /* renamed from: b */
        public void onSuccess(AddressModel addressModel) {
            if (addressModel != null) {
                LeaderBoardDataUtiils.saveAddressJson(AddressFragment.this.getActivity(), AddressFragment.this.k.toString());
                LeaderBoardDataUtiils.saveLocationOnboarding(AddressFragment.this.getActivity(), true);
                AddressFragment.this.f();
            }
        }
    }

    /* loaded from: classes9.dex */
    public class f implements View.OnClickListener {
        public f() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AnalyticsLog analyticsLog = new AnalyticsLog();
            analyticsLog.setEventName(FirebaseEventParams.EventName.KH_TAP.getValue());
            analyticsLog.setScreenName(FirebaseEventParams.ScreenName.CONFIRMATION_POPUP.getValue());
            analyticsLog.setDescription(FirebaseEventParams.Description.OPEN_BOTTOM_MENU_RANK_SCREEN.getValue());
            analyticsLog.setUiElementName(FirebaseEventParams.UiElementName.OK_BUTTON.getValue());
            CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
            Intent intent = new Intent(AddressFragment.this.getActivity(), LeaderBoardApiIntentService.class);
            if (Build.VERSION.SDK_INT >= 26) {
                AddressFragment.this.getActivity().startForegroundService(intent);
            } else {
                AddressFragment.this.getActivity().startService(intent);
            }
            AddressFragment.this.saveConfirmationDialog.dismiss();
            AddressFragment.this.getActivity().finish();
        }
    }

    /* loaded from: classes9.dex */
    public class g implements View.OnClickListener {
        public g() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AddressFragment.this.saveImageConfirmationDialog.dismiss();
        }
    }

    /* loaded from: classes9.dex */
    public class h extends AsyncTask<Place, Void, JsonObject> {
        public h() {
        }

        @Override // android.os.AsyncTask
        /* renamed from: a */
        public JsonObject doInBackground(Place... placeArr) {
            Place place = placeArr[0];
            return AddressFragment.this.d(place, place.getLatLng().latitude, place.getLatLng().longitude);
        }

        @Override // android.os.AsyncTask
        /* renamed from: b */
        public void onPostExecute(JsonObject jsonObject) {
            if (jsonObject != null) {
                AddressFragment.this.k = jsonObject;
            }
            super.onPostExecute(jsonObject);
        }
    }

    static {
        new LatLngBounds(new LatLng(37.39816d, -122.180831d), new LatLng(37.43061d, -121.97209d));
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), "android.permission.ACCESS_FINE_LOCATION")) {
                Dialog dialog = new Dialog(getActivity(), R.style.GenericDialog);
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.generic_ok_dialog);
                Window window = dialog.getWindow();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = -1;
                attributes.height = -2;
                attributes.gravity = 17;
                window.setAttributes(attributes);
                dialog.setCancelable(false);
                dialog.setCanceledOnTouchOutside(false);
                ((TextView) dialog.findViewById(R.id.dialog_title)).setText(getString(R.string.location_permission_title));
                ((TextView) dialog.findViewById(R.id.dialog_desc)).setText(getString(R.string.location_permission_description));
                ((TextView) dialog.findViewById(R.id.action_ok)).setOnClickListener(new d(dialog));
                dialog.show();
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{"android.permission.ACCESS_FINE_LOCATION"}, 99);
            }
            return false;
        }
        return true;
    }

    public final JsonObject d(Place place, double d2, double d3) {
        String subLocality;
        JsonObject jsonObject = new JsonObject();
        new HashMap();
        if (getActivity() == null) {
            return null;
        }
        try {
            List<Address> fromLocation = new Geocoder(getActivity(), Locale.ENGLISH).getFromLocation(d2, d3, 1);
            if (fromLocation != null && fromLocation.size() > 0) {
                Address address = fromLocation.get(0);
                jsonObject.addProperty("locationType", "Home");
                jsonObject.addProperty("longitude", "" + d3);
                jsonObject.addProperty("latitude", "" + d2);
                if (address.getSubLocality() == null) {
                    subLocality = place.getName() + "";
                } else {
                    subLocality = address.getSubLocality();
                }
                jsonObject.addProperty("locality", subLocality);
                jsonObject.addProperty(GeoCodingCriteria.POD_CITY, address.getLocality());
                jsonObject.addProperty("country", address.getCountryName());
                jsonObject.addProperty("administrativeArea", address.getAdminArea());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jsonObject;
    }

    public final void e(JsonObject jsonObject) {
        AddressReq addressReq = new AddressReq();
        try {
            if (!jsonObject.get(GeoCodingCriteria.POD_CITY).isJsonNull()) {
                addressReq.setCity(jsonObject.get(GeoCodingCriteria.POD_CITY).getAsString());
            }
            addressReq.setLatitude(jsonObject.get("latitude").getAsString());
            addressReq.setLongitude(jsonObject.get("longitude").getAsString());
            if (!jsonObject.get("locality").isJsonNull()) {
                addressReq.setLocality(jsonObject.get("locality").getAsString());
            } else if (!jsonObject.get(GeoCodingCriteria.POD_CITY).isJsonNull()) {
                addressReq.setLocality(jsonObject.get(GeoCodingCriteria.POD_CITY).getAsString());
            }
            addressReq.setCountry(jsonObject.get("country").getAsString());
            if (!jsonObject.get("administrativeArea").isJsonNull()) {
                addressReq.setAdministrativeArea(jsonObject.get("administrativeArea").getAsString());
            }
            addressReq.setLocationType(LocationType.HOME);
            CoveLeaderboardApi.saveLocation(addressReq, new e());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public final void f() {
        if (this.saveConfirmationDialog == null && getActivity() != null && isAdded()) {
            BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage = new BottomSheetDialogOneButtonTitleMessage(getActivity(), getString(R.string.confirmation), getString(R.string.your_rank_will_be_updated));
            this.saveConfirmationDialog = bottomSheetDialogOneButtonTitleMessage;
            bottomSheetDialogOneButtonTitleMessage.setPositiveButton(getString(R.string.ok), new f());
        }
        BottomSheetDialogOneButtonTitleMessage bottomSheetDialogOneButtonTitleMessage2 = this.saveConfirmationDialog;
        if (bottomSheetDialogOneButtonTitleMessage2 == null || bottomSheetDialogOneButtonTitleMessage2.isShowing()) {
            return;
        }
        this.saveConfirmationDialog.show();
    }

    public final void g() {
        if (this.saveImageConfirmationDialog == null) {
            FragmentActivity activity = getActivity();
            Drawable drawable = getResources().getDrawable(R.drawable.info_icon_new);
            BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage = new BottomSheetDialogImageTitleMessage(activity, drawable, "", getString(R.string.location_for_competition) + "\n\n" + getString(R.string.location_for_competition_desc), true);
            this.saveImageConfirmationDialog = bottomSheetDialogImageTitleMessage;
            bottomSheetDialogImageTitleMessage.setPositiveButton(getString(R.string.ok), new g());
        }
        BottomSheetDialogImageTitleMessage bottomSheetDialogImageTitleMessage2 = this.saveImageConfirmationDialog;
        if (bottomSheetDialogImageTitleMessage2 == null || bottomSheetDialogImageTitleMessage2.isShowing()) {
            return;
        }
        this.saveImageConfirmationDialog.show();
    }

    public Location getLocation() {
        LocationManager locationManager;
        if (getActivity() == null || (locationManager = (LocationManager) getActivity().getSystemService(FirebaseAnalytics.Param.LOCATION)) == null || !checkLocationPermission()) {
            return null;
        }
        Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
        return lastKnownLocation != null ? lastKnownLocation : locationManager.getLastKnownLocation("passive");
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.fragment_address, viewGroup, false);
        this.i = (Button) inflate.findViewById(R.id.selectAddress);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_use_current_loc);
        this.i.setOnClickListener(new a());
        checkLocationPermission();
        LeaderboardUtils.getMetadata(getActivity());
        if (LeaderboardUtils.API_KEY == null) {
            return inflate;
        }
        Places.initialize(getActivity(), LeaderboardUtils.API_KEY);
        this.l = Places.createClient(getActivity());
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getChildFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
        this.h = autocompleteSupportFragment;
        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG));
        this.j = (ImageView) ((LinearLayout) this.h.getView()).getChildAt(0);
        ((ImageView) ((LinearLayout) this.h.getView()).getChildAt(2)).setOnClickListener(new b());
        ImageView imageView = this.j;
        Resources resources = getResources();
        int i = R.drawable.ic_location;
        imageView.setImageDrawable(resources.getDrawable(i));
        this.j.setColorFilter(ContextCompat.getColor(getActivity(), R.color.main_text_color));
        ((LinearLayout) this.h.getView()).setBackgroundDrawable(getResources().getDrawable(R.drawable.dialog_bg_new));
        EditText editText = (EditText) ((LinearLayout) this.h.getView()).findViewById(R.id.places_autocomplete_search_input);
        this.m = editText;
        editText.setTextColor(getResources().getColor(R.color.color_9c9c9c));
        this.m.setPadding(0, 15, 0, 0);
        this.h.setOnPlaceSelectedListener(new c());
        this.h.setHint(getString(R.string.enter_your_location));
        String addressJson = LeaderBoardDataUtiils.getAddressJson(getActivity());
        if (addressJson != null && !addressJson.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(addressJson);
                if (this.h != null) {
                    EditText editText2 = this.m;
                    Resources resources2 = getResources();
                    int i2 = R.color.colorPrimary;
                    editText2.setTextColor(resources2.getColor(i2));
                    this.j.setColorFilter(ContextCompat.getColor(getActivity(), i2));
                    String string = jSONObject.getString("locality");
                    String string2 = jSONObject.getString(GeoCodingCriteria.POD_CITY);
                    if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                        AutocompleteSupportFragment autocompleteSupportFragment2 = this.h;
                        autocompleteSupportFragment2.setText(string + Constants.SEPARATOR_COMMA + string2);
                    }
                } else {
                    this.j.setImageDrawable(getResources().getDrawable(i));
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        AnalyticsLog analyticsLog = new AnalyticsLog();
        analyticsLog.setEventName(FirebaseEventParams.EventName.KH_OPEN.getValue());
        FirebaseConstants firebaseConstants = FirebaseConstants.PREVIOUS_SCREEN_NAME;
        analyticsLog.setPreviousScreenName(firebaseConstants.getValue());
        FirebaseEventParams.ScreenName screenName = FirebaseEventParams.ScreenName.RANK_BADGES_SCREEN;
        analyticsLog.setScreenName(screenName.getValue());
        CoveAnalyticsManager.Companion.getInstance().logEvent(analyticsLog);
        firebaseConstants.setValue(screenName.getValue());
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 99 && iArr.length > 0) {
            int i2 = iArr[0];
        }
    }

    public void onSelectAddressClick() {
        if (((EditText) this.h.getView().findViewById(R.id.places_autocomplete_search_input)).getText().toString().isEmpty()) {
            g();
            return;
        }
        JsonObject jsonObject = this.k;
        if (jsonObject != null && jsonObject.size() != 0) {
            e(this.k);
        } else {
            getActivity().finish();
        }
    }
}
