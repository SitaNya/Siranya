package banlist.sinanya.system;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author SitaNya
 * @date 2019-06-21
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public interface SystemBanList {
    HashMap<Long, ArrayList<Long>> BAN_GROUP_LIST = new HashMap<>();

    HashMap<Long, ArrayList<Long>> BAN_QQ_LIST = new HashMap<>();
}
