package simple.project.giisdemo.helper.utils;

import android.app.Activity;
import android.content.Context;
import android.text.InputType;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import simple.project.giisdemo.R;

/**
 * @author Created by Simple
 * @date at 2019/1/3 20:25
 * @describe
 */
public class EditTextUtil {


    public static void onFocusChange(Activity mActivity, Boolean hidden, View view, boolean hasFocus) {
        setTextColor(mActivity, (EditText) view, hasFocus);
        View parentView = (View) view.getParent();
        switch (view.getId()) {
            case R.id.input_user:
                ImageView userIcon = parentView.findViewById(R.id.user_icon);
                if (hasFocus) {
                    userIcon.setImageResource(R.drawable.ic_account_color_primary);
                } else {
                    userIcon.setImageResource(R.drawable.ic_account);
                }
                break;
            case R.id.input_passwd:
                ImageView passwdIcon = parentView.findViewById(R.id.passwd_icon);
                if (hasFocus) {
                    passwdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_color_primary : R.drawable.ic_visibility_color_primary);
                } else {
                    passwdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off : R.drawable.ic_visibility);
                }
                break;
            case R.id.input_name:
                ImageView nameIcon = parentView.findViewById(R.id.name_icon);
                if (hasFocus) {
                    nameIcon.setImageResource(R.drawable.ic_account_color_primary);
                } else {
                    nameIcon.setImageResource(R.drawable.ic_account);
                }
                break;
            case R.id.input_phone:
                ImageView phoneIcon = parentView.findViewById(R.id.phone_icon);
                if (hasFocus) {
                    phoneIcon.setImageResource(R.drawable.ic_phone_color_primary);
                } else {
                    phoneIcon.setImageResource(R.drawable.ic_phone);
                }
                break;
            case R.id.new_passwd:
                ImageView nepasswdIcon = parentView.findViewById(R.id.nepasswd_icon);
                if (hasFocus) {
                    nepasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_color_primary : R.drawable.ic_visibility_color_primary);
                } else {
                    nepasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_color_primary : R.drawable.ic_visibility);
                }
                break;
            case R.id.repeat_passwd:
                ImageView repasswdIcon = parentView.findViewById(R.id.repasswd_icon);
                if (hasFocus) {
                    repasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off_color_primary : R.drawable.ic_visibility_color_primary);
                } else {
                    repasswdIcon.setImageResource(hidden ? R.drawable.ic_visibility_off : R.drawable.ic_visibility);
                }
                break;
            default:
        }

    }

    private static void setTextColor(Activity mActivity, EditText editText, boolean hasFocus) {
        TypedValue value = new TypedValue();
        mActivity.getTheme().resolveAttribute(R.attr.colorAccent, value, true);
        if (hasFocus) {
            editText.setTextColor(mActivity.getResources().getColor(value.resourceId, null));
            editText.setHintTextColor(mActivity.getResources().getColor(value.resourceId, null));
        } else {
            editText.setTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
            editText.setHintTextColor(mActivity.getResources().getColor(R.color.colorText_Icon, null));
        }
    }

    public static boolean setPasswordHidden(EditText view) {
        View parentView = (View) view.getParent().getParent();
        if (view.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {
            view.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            setVisibleIcon(view, true);
            return true;
        } else {
            view.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            setVisibleIcon(view, false);
            return false;
        }
    }

    public static void setVisibleIcon(EditText editText, boolean hidden) {
        View parentView = (View) editText.getParent();
        switch (editText.getId()) {
            case R.id.input_passwd:
                ImageView passwdIcon = parentView.findViewById(R.id.passwd_icon);
                passwdIcon.setImageResource(hidden ?
                        (editText.isFocused() ? R.drawable.ic_visibility_off_color_primary
                                : R.drawable.ic_visibility_off)
                        : (editText.isFocused() ? R.drawable.ic_visibility_color_primary
                        : R.drawable.ic_visibility));
                break;
            case R.id.new_passwd:
                ImageView nepasswdIcon = parentView.findViewById(R.id.nepasswd_icon);
                nepasswdIcon.setImageResource(hidden ?
                        (editText.isFocused() ? R.drawable.ic_visibility_off_color_primary
                                : R.drawable.ic_visibility_off)
                        : (editText.isFocused() ? R.drawable.ic_visibility_color_primary
                        : R.drawable.ic_visibility));
                break;
            case R.id.repeat_passwd:
                ImageView repasswdIcon = parentView.findViewById(R.id.repasswd_icon);
                repasswdIcon.setImageResource(hidden ?
                        (editText.isFocused() ? R.drawable.ic_visibility_off_color_primary
                                : R.drawable.ic_visibility_off)
                        : (editText.isFocused() ? R.drawable.ic_visibility_color_primary
                        : R.drawable.ic_visibility));
                break;
            default:
        }
    }

    public static void shakeAnimation(Activity mActivity, View view) {
        Animation shake = AnimationUtils.loadAnimation(mActivity, R.anim.shake);
        view.startAnimation(shake);

    }

    public static void focusEditText(Activity mActivity,EditText editText){
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();

        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText,0);
    }
}
