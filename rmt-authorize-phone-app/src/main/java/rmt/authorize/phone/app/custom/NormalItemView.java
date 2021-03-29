package rmt.authorize.phone.app.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import rmt.authorize.phone.app.R;

public class NormalItemView extends RelativeLayout {


    private TextView title;
    private TextView content;
    private ImageView next;

    private String t;
    private int resource;

    public NormalItemView(Context context) {
        super(context);
        init(context, null);
    }

    public NormalItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    public NormalItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public NormalItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        View view = inflate(context, R.layout.normalitemviewdetail, this);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);
        next = (ImageView) findViewById(R.id.next);

        if (attrs == null) {
            return;
        }
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.OptionItemView);
        for (int i = 0; i < typedArray.getIndexCount(); i++) {
            int attr = typedArray.getIndex(i);
            if (attr == R.styleable.OptionItemView_title) {
                title.setText(typedArray.getString(attr));
            } else if (attr == R.styleable.OptionItemView_desc) {
                content.setText(typedArray.getString(attr));
            } else if (attr == R.styleable.OptionItemView_start_src) {
                int resId = typedArray.getResourceId(attr, 0);
                if (resId != 0) {
                    next.setVisibility(VISIBLE);
                    next.setImageResource(resId);
                } else {
                    next.setVisibility(INVISIBLE);
                }
            } else if (attr == R.styleable.OptionItemView_end_src) {
                int resId = typedArray.getResourceId(attr, 0);
                if (resId != 0) {
                    next.setVisibility(VISIBLE);
                    next.setImageResource(resId);
                } else {
                    next.setVisibility(INVISIBLE);
                }
            }

        }

    }

    public void setTitle(String title) {
        this.t = title;
        if (content != null) {
            content.setText(title);
        }
    }

    public String getTitle() {
        if (content != null) {
            return content.getText().toString();
        }
        return "";
    }

    public void setImage(int resource) {
        this.resource = resource;
        if (next != null) {
            if (resource == 0) {
                next.setVisibility(INVISIBLE);
            } else {
                next.setVisibility(VISIBLE);
                next.setImageResource(resource);
            }
        }
    }

}
