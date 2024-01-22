package com.coveiot.android.leonardo.dashboard.health.spo2.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.coveiot.android.boat.R;
import com.coveiot.android.leonardo.dashboard.health.spo2.fragment.FragmentSPO2Result;
import com.coveiot.android.leonardo.dashboard.health.spo2.model.Spo2DeviceType;
import com.coveiot.textrecognition.base.BaseOcrResponse;
import com.coveiot.textrecognition.base.OcrError;
import com.coveiot.textrecognition.base.ResponseCallback;
import com.coveiot.textrecognition.model.OxymeterResponse;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes3.dex */
public final class FragmentSPO2MethodSelection$loadOcrScanFragment$1 implements ResponseCallback<BaseOcrResponse> {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ FragmentSPO2MethodSelection f4747a;

    public FragmentSPO2MethodSelection$loadOcrScanFragment$1(FragmentSPO2MethodSelection fragmentSPO2MethodSelection) {
        this.f4747a = fragmentSPO2MethodSelection;
    }

    public static final void b(View view) {
    }

    @Override // com.coveiot.textrecognition.base.ResponseCallback
    public void onFailure(@NotNull OcrError error) {
        FragmentSPO2Result.OnLoadSPO2ResultListener onLoadSPO2ResultListener;
        Intrinsics.checkNotNullParameter(error, "error");
        Toast.makeText(this.f4747a.getActivity(), error.getErrorMsg(), 0).show();
        onLoadSPO2ResultListener = this.f4747a.o;
        if (onLoadSPO2ResultListener != null) {
            String string = this.f4747a.getString(R.string.measurement_failed);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.measurement_failed)");
            onLoadSPO2ResultListener.showFailureDialog(string, new View.OnClickListener() { // from class: com.coveiot.android.leonardo.dashboard.health.spo2.fragment.u
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FragmentSPO2MethodSelection$loadOcrScanFragment$1.b(view);
                }
            });
        }
    }

    @Override // com.coveiot.textrecognition.base.ResponseCallback
    public void onSuccess(@NotNull BaseOcrResponse response) {
        FragmentSPO2Result.OnLoadSPO2ResultListener onLoadSPO2ResultListener;
        Intrinsics.checkNotNullParameter(response, "response");
        if (response instanceof OxymeterResponse) {
            String tag = this.f4747a.getTAG();
            StringBuilder sb = new StringBuilder();
            sb.append("OxymeterResponse:");
            OxymeterResponse oxymeterResponse = (OxymeterResponse) response;
            sb.append(oxymeterResponse.getSpo2());
            Log.d(tag, sb.toString());
            onLoadSPO2ResultListener = this.f4747a.o;
            if (onLoadSPO2ResultListener != null) {
                onLoadSPO2ResultListener.loadBluetoothResultFragment(Spo2DeviceType.OCR, Double.parseDouble(oxymeterResponse.getSpo2()));
            }
        }
    }
}
