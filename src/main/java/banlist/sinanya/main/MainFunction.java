package banlist.sinanya.main;

import banlist.sinanya.control.GroupControl;
import banlist.sinanya.control.QqControl;
import banlist.sinanya.main.imal.GroupCheck;
import banlist.sinanya.main.imal.PrivateCheck;

import static banlist.sinanya.system.SystemTag.*;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_IGNORE;
import static com.sobte.cqp.jcq.entity.IMsg.MSG_INTERCEPT;

/**
 * @author SitaNya
 * @date 2019-07-01
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class MainFunction {
    private String msg;
    private long fromQq;
    private long fromGroup;

    private boolean isBanGroupSet;
    private boolean isBanGroupRm;
    private boolean isBanGroupShow;

    private boolean isBanQqSet;
    private boolean isBanQqRm;
    private boolean isBanQqShow;


    public MainFunction(String msg, long fromQq, long fromGroup) {
        this.msg = msg;
        this.fromQq = fromQq;
        this.fromGroup = fromGroup;

        String lastRegex = ".*";
        this.isBanGroupSet = msg.matches(BAN_GROUP_RM + lastRegex);
        this.isBanGroupRm = msg.matches(BAN_GROUP_SET + lastRegex);
        this.isBanGroupShow = msg.matches(BAN_GROUP_SHOW + lastRegex) && !isBanGroupRm && !isBanGroupSet;

        this.isBanQqSet = msg.matches(BAN_QQ_SET + lastRegex);
        this.isBanQqRm = msg.matches(BAN_QQ_RM + lastRegex);
        this.isBanQqShow = msg.matches(BAN_QQ_SHOW + lastRegex) && !isBanQqSet && !isBanQqRm;
    }

    public int checkGroup() {
        GroupCheck groupCheck = new GroupCheck(fromGroup, fromQq, msg);

        if (groupCheck.inBanGroup() == 1) {
            return MSG_INTERCEPT;
        } else if (groupCheck.isBanQq() == 1) {
            return MSG_INTERCEPT;
        } else if (groupCheck.isForbidden() == 1) {
            return MSG_INTERCEPT;
        }

        return checkQq();
    }

    public int checkQq() {
        QqControl qqControl = new QqControl(fromGroup, fromQq);
        GroupControl groupControl = new GroupControl(fromQq, fromGroup);
        PrivateCheck privateCheck = new PrivateCheck(fromQq);

        if (privateCheck.isBanQq() == 1) {
            return MSG_INTERCEPT;
        }

        if (isBanQqSet) {
            return qqControl.set(msg);
        } else if (isBanQqRm) {
            return qqControl.rm(msg);
        } else if (isBanQqShow) {
            return qqControl.get();
        }

        if (isBanGroupSet) {
            return groupControl.set(msg);
        } else if (isBanGroupRm) {
            return groupControl.rm(msg);
        } else if (isBanGroupShow) {
            return groupControl.get();
        }

        return MSG_IGNORE;
    }
}
