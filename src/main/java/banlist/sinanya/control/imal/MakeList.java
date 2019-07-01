package banlist.sinanya.control.imal;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author SitaNya
 * @date 2019-07-01
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public interface MakeList {

    /**
     * 传入的信息可能包含回车，tab，空格等，统一格式化为","分割并返回
     *
     * @param msg 传入的信息
     * @return 格式化后的信息
     */
    default ArrayList<String> makeList(String msg) {
        ArrayList<String> idList = new ArrayList<>();
        if (msg.contains(",")) {
            idList = new ArrayList<>(Arrays.asList(
                    msg
                            .replaceAll("\n", "")
                            .replaceAll("\t", "")
                            .replaceAll(" ", "")
                            .split(",")
            ));
        }
        return idList;
    }
}
