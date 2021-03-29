package com.hd.hse.common.module.branch.ui.model.fragment;

import com.hd.hse.common.module.phone.R;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public abstract class TextFragment extends BaseFragment {

	public TextFragment() {
		super(STYLE_SINGLE_BUTTON);
	}

	private TextView mTV2Text;
	
	@Override
	protected View onCreatePageView(LayoutInflater inflater) {
		mTV2Text = (TextView) inflater.inflate(R.layout.hd_hse_common_module_text_fragment, null);
		String text = getText();
		if(text != null && !TextUtils.isEmpty(text)){
			mTV2Text.setText(text);
		}
		return mTV2Text;
	}
	
	protected abstract String getText();
}
