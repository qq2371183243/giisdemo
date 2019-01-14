package simple.project.giisdemo.mvp.presenter.main;

import android.view.View;

import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.grouplist.QMUICommonListItemView;

import simple.project.giisdemo.R;
import simple.project.giisdemo.base.BasePresenter;
import simple.project.giisdemo.helper.custom.GroupListView;
import simple.project.giisdemo.mvp.model.main.SettingPushModel;
import simple.project.giisdemo.mvp.view.main.SettingPushView;

import static simple.project.giisdemo.helper.constant.GlobalField.cycles;
import static simple.project.giisdemo.helper.constant.GlobalField.dateSet;
import static simple.project.giisdemo.helper.constant.GlobalField.weekSet;

/**
 * @author Created by ys
 * @date at 2019/1/10 1:57
 * @describe
 */
public class SettingPushPresenter extends BasePresenter<SettingPushView, SettingPushModel> {
    public void initGroupListView(GroupListView groupListPushSwitch, GroupListView groupListPushCall, GroupListView groupListPushCycle) {
        QMUICommonListItemView pushSwitch = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_switch));
        pushSwitch.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushSwitch.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
        });
        QMUICommonListItemView pushVoice = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_voice));
        pushVoice.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushVoice.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
        });
        QMUICommonListItemView pushVibrate = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_vibrate));
        pushVibrate.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushVibrate.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
        });
        QMUICommonListItemView pushFloat = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_float));
        pushFloat.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_SWITCH);
        pushFloat.getSwitch().setOnCheckedChangeListener((buttonView, isChecked) -> {
        });
        QMUICommonListItemView pushCycle = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_cycle));
        QMUICommonListItemView pushDate = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_date));
        QMUICommonListItemView pushTime = groupListPushSwitch.createItemView(getView().getCurContext().getResources().getString(R.string.push_time));


        pushCycle.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushCycle.setDetailText(cycles[1]);
        pushDate.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushDate.setDetailText("周一");
        pushTime.setAccessoryType(QMUICommonListItemView.ACCESSORY_TYPE_CHEVRON);
        pushTime.setDetailText("12:00");

        View.OnClickListener onClickListener = v -> {
            final String[] items;
            if (v instanceof QMUICommonListItemView) {
                CharSequence text = ((QMUICommonListItemView) v).getText();
                switch ((String) text) {
                    case "推送周期":
                        items = cycles;
                        new QMUIDialog.CheckableDialogBuilder(getView().getCurContext()).setCheckedIndex(0).addItems(items, (dialog, which) ->
                        {
                            switch (which) {
                                case 0:
                                    //TODO 隐藏推送日期
                                    break;
                                default:
                                    //TODO 显示推送日期
                            }
                            dialog.dismiss();

                        }).show();
                        break;
                    case "推送日期":
                        if (cycles[1].contentEquals(pushCycle.getDetailText()))
                            items = weekSet;
                        else
                            items = dateSet;
                        new QMUIDialog.CheckableDialogBuilder(getView().getCurContext()).setCheckedIndex(0).addItems(items, (dialog, which) ->
                                dialog.dismiss()).show();
                        break;
                }
            }
        };

        GroupListView.newSection(getView().getCurContext())
                .setDescription(getView().getCurContext().getResources().getString(R.string.push_section_1))
                .addItemView(pushSwitch, onClickListener)
                .addTo(groupListPushSwitch);

        GroupListView.newSection(getView().getCurContext())
                .addItemView(pushVoice, onClickListener)
                .addItemView(pushVibrate, onClickListener)
                .addItemView(pushFloat, onClickListener)
                .addTo(groupListPushCall);
        GroupListView.newSection(getView().getCurContext())
                .addItemView(pushCycle, onClickListener)
                .addItemView(pushDate, onClickListener)
                .addItemView(pushTime, onClickListener)
                .addTo(groupListPushCycle);

    }
}