package simple.project.giisdemo.mvp.presenter.main.setting;

import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.utils.SPUtils;
import simple.project.giisdemo.mvp.model.main.setting.UpdateUserInfoModel;
import simple.project.giisdemo.mvp.view.main.setting.UpdateUserInfoView;

import static android.text.TextUtils.isEmpty;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;

/**
 * @author Created by ys
 * @date at 2019/1/17 16:58
 * @describe
 */
public class UpdateUserInfoPresenter extends BasePresenter<UpdateUserInfoView, UpdateUserInfoModel> {
    public boolean validateOldPhone(String phone) {
        if (getModel().getOldPhone().equals(phone))
            return true;
        else
            getView().showErrorMsg("手机号错误");
        return false;
    }

    public boolean validateOldPassword(String password) {
        if (getModel().getOldPassword().equals(password))
            return true;
        else
            getView().showErrorMsg("密码错误");
        return false;
    }


    public void updateInfo(String phone, String password) {
        String oldPhone = (String) SPUtils.get(getView().getCurContext(), USER_PHONE, "");
        getModel().updateInfo(oldPhone, phone, password, new OnHttpCallBack<RetResult>() {
            @Override
            public void onSuccess(RetResult retResult) {
                //TODO 存入成功信息，更新信息
                if (isEmpty(phone))
                    getView().updateInfo(phone);

                getView().toBack();
            }

            @Override
            public void onFailed(String errorMsg) {
                getView().showErrorMsg(errorMsg);
            }
        });
    }
}
