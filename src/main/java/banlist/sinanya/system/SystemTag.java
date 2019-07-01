package banlist.sinanya.system;

/**
 * @author SitaNya
 * @date 2019-07-01
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public interface SystemTag {

    String HEADER = ".*[.。][ ]*";

    String BAN_GROUP_HEADER = HEADER + "bangroup[ ]*";
    String BAN_GROUP_SET = BAN_GROUP_HEADER + "set";
    String BAN_GROUP_RM = BAN_GROUP_HEADER + "rm";
    String BAN_GROUP_SHOW = BAN_GROUP_HEADER;

    String BAN_QQ_HEADER = HEADER + "ban[^main][ ]*";
    String BAN_QQ_SET = BAN_QQ_HEADER + "set";
    String BAN_QQ_RM = BAN_QQ_HEADER + "rm";
    String BAN_QQ_SHOW = BAN_QQ_HEADER;
}
