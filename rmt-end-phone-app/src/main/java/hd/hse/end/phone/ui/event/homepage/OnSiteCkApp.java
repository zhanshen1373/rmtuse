package hd.hse.end.phone.ui.event.homepage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.hd.hse.common.exception.HDException;
import com.hd.hse.common.module.branch.ui.model.activity.FrameworkActivity;
import com.hd.hse.common.module.phone.ui.activity.LocationSwCard;
import com.hd.hse.common.module.phone.ui.event.homepage.AbstractAppModule;
import com.hd.hse.constant.IRelativeEncoding;
import com.hd.hse.entity.sys.AppModule;
import com.hd.hse.system.SystemProperty;

import hd.hse.end.phone.ui.activity.TaskTabulationActivityOnline;

/**
 * ClassName: OnSiteCkApp (任务结束入口)<br/>
 * date: 2015年6月10日  <br/>
 *
 * @author lxf
 * @version 
 */
public class OnSiteCkApp extends AbstractAppModule {
	public OnSiteCkApp(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void appModuleOnClick(AppModule aModule) throws HDException {
		super.appModuleOnClick(aModule);
		if (aModule.getIsswcard()==0 || (isRelation(IRelativeEncoding.REUSINGLOCATIONCARD)
				&& SystemProperty.getSystemProperty().getPositionCard() != null)) {
			Activity parent = (Activity) getParent();
			Intent intent = new Intent(parent, TaskTabulationActivityOnline.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.putExtra(FrameworkActivity.INTENT_FUNCTION_KEY,aModule.getModelnum());
			parent.startActivity(intent);
		} else {
			// 位置卡刷卡
			Activity parent = (Activity) getParent();
			Intent intent = new Intent(parent, LocationSwCard.class);
			Bundle mBundle = new Bundle();
			// 传递目标跳转activity类
			mBundle.putSerializable(LocationSwCard.SER_KEY_TARGETCLASS,
					TaskTabulationActivityOnline.class);
			mBundle.putString(FrameworkActivity.INTENT_FUNCTION_KEY,aModule.getModelnum());
			intent.putExtras(mBundle);
			parent.startActivity(intent);
		}
	}
}
