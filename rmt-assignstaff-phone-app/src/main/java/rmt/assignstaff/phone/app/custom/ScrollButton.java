package rmt.assignstaff.phone.app.custom;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by dubojian on 2018/6/7.
 */

public class ScrollButton extends android.support.v7.widget.AppCompatButton {

    public ScrollButton(Context context) {
        super(context);
    }

    public ScrollButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}
