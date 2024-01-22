package androidx.constraintlayout.core.state.helpers;

import androidx.constraintlayout.core.state.ConstraintReference;
import androidx.constraintlayout.core.state.HelperReference;
import androidx.constraintlayout.core.state.State;
import java.util.Iterator;
/* loaded from: classes.dex */
public class AlignHorizontallyReference extends HelperReference {
    public float N;

    public AlignHorizontallyReference(State state) {
        super(state, State.Helper.ALIGN_VERTICALLY);
        this.N = 0.5f;
    }

    @Override // androidx.constraintlayout.core.state.HelperReference, androidx.constraintlayout.core.state.ConstraintReference, androidx.constraintlayout.core.state.Reference
    public void apply() {
        Iterator<Object> it = this.mReferences.iterator();
        while (it.hasNext()) {
            ConstraintReference constraints = this.mState.constraints(it.next());
            constraints.clearHorizontal();
            Object obj = this.mStartToStart;
            if (obj != null) {
                constraints.startToStart(obj);
            } else {
                Object obj2 = this.mStartToEnd;
                if (obj2 != null) {
                    constraints.startToEnd(obj2);
                } else {
                    constraints.startToStart(State.PARENT);
                }
            }
            Object obj3 = this.mEndToStart;
            if (obj3 != null) {
                constraints.endToStart(obj3);
            } else {
                Object obj4 = this.mEndToEnd;
                if (obj4 != null) {
                    constraints.endToEnd(obj4);
                } else {
                    constraints.endToEnd(State.PARENT);
                }
            }
            float f = this.N;
            if (f != 0.5f) {
                constraints.horizontalBias(f);
            }
        }
    }
}
