package com.coveiot.android.leonardo.onboarding.profile.viewmodel;

import android.net.Uri;
import android.os.Bundle;
import androidx.lifecycle.ViewModel;
import com.coveiot.android.dashboard2.util.Dashboard2Constants;
import com.coveiot.android.leonardo.utils.AppConstants;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import java.net.MalformedURLException;
import java.net.URL;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes5.dex */
public final class FragmentSocialLoginViewModel extends ViewModel {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final String f5334a = "social_Name";
    @NotNull
    public static final String b = "social_email";
    @NotNull
    public static final String c = "social_gender";
    @NotNull
    public static final String d = "social_birthDay";
    @NotNull
    public static final String e = "social_socialLink";
    @NotNull
    public static final String f = "social_profileImage";

    /* loaded from: classes5.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final String getBUNDLE_DISPLAY_NAME() {
            return FragmentSocialLoginViewModel.f5334a;
        }

        @NotNull
        public final String getBUNDLE_PROFILE_IMAGE() {
            return FragmentSocialLoginViewModel.f;
        }

        @NotNull
        public final String getBUNDLE_SOCIAL_BIRTHDAY() {
            return FragmentSocialLoginViewModel.d;
        }

        @NotNull
        public final String getBUNDLE_SOCIAL_EMAIL() {
            return FragmentSocialLoginViewModel.b;
        }

        @NotNull
        public final String getBUNDLE_SOCIAL_GENDER() {
            return FragmentSocialLoginViewModel.c;
        }

        @NotNull
        public final String getBUNDLE_SOCIAL_LINK() {
            return FragmentSocialLoginViewModel.e;
        }
    }

    @Nullable
    public final Bundle getFacebookData(@NotNull JSONObject object) {
        String str;
        Intrinsics.checkNotNullParameter(object, "object");
        try {
            Bundle bundle = new Bundle();
            try {
                bundle.putString(f, new URL("https://graph.facebook.com/" + object.getString("id") + "/picture?type=large").toString());
            } catch (MalformedURLException e2) {
                e2.printStackTrace();
            }
            String str2 = "";
            if (object.has("first_name")) {
                str2 = object.getString("first_name");
                Intrinsics.checkNotNullExpressionValue(str2, "`object`.getString(\"first_name\")");
            }
            if (object.has("last_name")) {
                str2 = str2 + ' ' + object.getString("last_name");
            }
            bundle.putString(f5334a, str2);
            if (object.has("email")) {
                bundle.putString(b, object.getString("email"));
            }
            if (object.has("gender")) {
                String string = object.getString("gender");
                Intrinsics.checkNotNullExpressionValue(string, "`object`.getString(\"gender\")");
                int length = string.length() - 1;
                int i = 0;
                boolean z = false;
                while (i <= length) {
                    boolean z2 = Intrinsics.compare((int) string.charAt(!z ? i : length), 32) <= 0;
                    if (z) {
                        if (!z2) {
                            break;
                        }
                        length--;
                    } else if (z2) {
                        i++;
                    } else {
                        z = true;
                    }
                }
                String lowerCase = string.subSequence(i, length + 1).toString().toLowerCase();
                Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase()");
                if (Intrinsics.areEqual(lowerCase, "male")) {
                    str = "Male";
                } else {
                    String string2 = object.getString("gender");
                    Intrinsics.checkNotNullExpressionValue(string2, "`object`.getString(\"gender\")");
                    int length2 = string2.length() - 1;
                    int i2 = 0;
                    boolean z3 = false;
                    while (i2 <= length2) {
                        boolean z4 = Intrinsics.compare((int) string2.charAt(!z3 ? i2 : length2), 32) <= 0;
                        if (z3) {
                            if (!z4) {
                                break;
                            }
                            length2--;
                        } else if (z4) {
                            i2++;
                        } else {
                            z3 = true;
                        }
                    }
                    String lowerCase2 = string2.subSequence(i2, length2 + 1).toString().toLowerCase();
                    Intrinsics.checkNotNullExpressionValue(lowerCase2, "this as java.lang.String).toLowerCase()");
                    str = Intrinsics.areEqual(lowerCase2, "female") ? Dashboard2Constants.FEMALE : "WS";
                }
                bundle.putString(c, str);
            }
            if (object.has("birthday")) {
                bundle.putString(d, object.getString("birthday"));
            }
            if (object.has("link")) {
                bundle.putString(e, object.getString("link"));
            } else {
                bundle.putString(e, object.getString("id"));
            }
            return bundle;
        } catch (JSONException unused) {
            return null;
        }
    }

    @Nullable
    public final Bundle handleSignInResult(@NotNull Task<GoogleSignInAccount> completedTask) {
        Intrinsics.checkNotNullParameter(completedTask, "completedTask");
        try {
            GoogleSignInAccount result = completedTask.getResult(ApiException.class);
            Bundle bundle = new Bundle();
            if (result == null) {
                return bundle;
            }
            String email = result.getEmail();
            String displayName = result.getDisplayName();
            bundle.putString(b, email);
            bundle.putString(f5334a, displayName);
            if (result.getPhotoUrl() != null) {
                StringBuilder sb = new StringBuilder();
                Uri photoUrl = result.getPhotoUrl();
                Intrinsics.checkNotNull(photoUrl);
                sb.append(photoUrl.toString());
                sb.append("");
                bundle.putString(f, sb.toString());
            } else {
                bundle.putString(f, AppConstants.EMPTY_STRING.getValue());
            }
            bundle.putString(e, result.getId());
            return bundle;
        } catch (ApiException unused) {
            return null;
        }
    }
}
