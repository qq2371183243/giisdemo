package simple.project.giisdemo.mvp.model.login;

import android.util.Log;

import com.alibaba.fastjson.JSON;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import simple.project.giisdemo.base.BaseModel;
import simple.project.giisdemo.data.bean.UserBean;
import simple.project.giisdemo.helper.constant.GlobalField;
import simple.project.giisdemo.helper.http.HttpContract;
import simple.project.giisdemo.helper.http.HttpFeedBackUtil;
import simple.project.giisdemo.helper.http.OnHttpCallBack;
import simple.project.giisdemo.helper.http.RetResult;
import simple.project.giisdemo.helper.http.RetrofitUtils;
import simple.project.giisdemo.helper.utils.SPUtils;

import static simple.project.giisdemo.helper.constant.GlobalField.DEBUG;
import static simple.project.giisdemo.helper.constant.HttpConstant.URL;
import static simple.project.giisdemo.helper.constant.HttpConstant.PORT;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_NAME;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PHONE;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_PWD;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_TAGS;
import static simple.project.giisdemo.helper.constant.GlobalField.USER_UID;

/**
 * @author Created by ys
 * @date at 2019/1/8 0:52
 */
public class LoginModel extends BaseModel {

    @Override
    public void init() {
    }

    public void login(String phone, String password, OnHttpCallBack<RetResult> callBack) {
        Log.d(DEBUG, "LoginModel: phone is " + phone + " password is " + password);
        RetrofitUtils.newInstance(URL + PORT + "/")
                .create(HttpContract.class)
                .login(phone, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<RetResult<UserBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(RetResult<UserBean> retResult) {
                        HttpFeedBackUtil.handleRetResult(retResult, callBack);
                        if (retResult.getCode() == RetResult.RetCode.SUCCESS.code) {
                            // 把这个人的数据存入本地数据库
                            UserBean userBean = JSON.parseObject(JSON.toJSONString(retResult.getData()), UserBean.class);
                            SPUtils.put(getContext(), USER_PHONE, phone);
                            SPUtils.put(getContext(), USER_PWD, password);
                            SPUtils.put(getContext(), USER_NAME, userBean.getName());
                            SPUtils.put(getContext(), USER_UID, userBean.getUid());
                            String tagJson = JSON.toJSONString(userBean.getCare());
                            SPUtils.put(getContext(), USER_TAGS, tagJson);
                            Log.d(DEBUG, "LoginModel：用户个人信息已存入");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        HttpFeedBackUtil.handleException(e, callBack);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }


    public String getAccount() {
        return (String) SPUtils.get(getContext(), GlobalField.USER_PHONE, "");
    }

    public String getPassword() {
        return (String) SPUtils.get(getContext(), USER_PWD, "");
    }

}
