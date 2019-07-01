package banlist.sinanya.tools.makedata;

/**
 * @author SitaNya
 * @date 2019-07-01
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class RemoveTag {
    public static String removeTag(String messages, String tag) {
        return messages.replaceAll(tag, "").trim();
    }
}
