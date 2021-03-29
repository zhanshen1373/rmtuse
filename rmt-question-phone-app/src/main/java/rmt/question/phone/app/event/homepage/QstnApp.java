package rmt.question.phone.app.event.homepage;

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

import rmt.question.phone.app.activity.QuestionListActivity;


/**
 * created by yangning on 2017/6/9 17:47.
 * rmt.question.phone.app.event.homepage.QstnApp
 */

public class QstnApp extends AbstractAppModule{
    public QstnApp(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    @Override
    public void appModuleOnClick(AppModule aModule) throws HDException {
        super.appModuleOnClick(aModule);
        if (aModule.getIsswcard()==0 || (isRelation(IRelativeEncoding.REUSINGLOCATIONCARD)
                && SystemProperty.getSystemProperty().getPositionCard() != null)) {
            Activity parent = (Activity) getParent();
            Intent intent = new Intent(parent, QuestionListActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            parent.startActivity(intent);
        } else {
            // 位置卡刷卡
            Activity parent = (Activity) getParent();
            Intent intent = new Intent(parent, LocationSwCard.class);
            Bundle mBundle = new Bundle();
            // 传递目标跳转activity类
            mBundle.putSerializable(LocationSwCard.SER_KEY_TARGETCLASS,
                    QuestionListActivity.class);
            mBundle.putString(FrameworkActivity.INTENT_FUNCTION_KEY,aModule.getModelnum());
            intent.putExtras(mBundle);
            parent.startActivity(intent);
        }
    }
}
