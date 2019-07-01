package banlist.sinanya.entity;

import java.util.Date;

/**
 * @author SitaNya
 * @date 2019-06-21
 * @email sitanya@qq.com
 * @qqGroup 162279609
 * 有任何问题欢迎咨询
 * <p>
 * 类说明:
 */
public class EntityBanDetail {
    private Date createTime;
    private long groupId;
    private long botId;
    private String reason;

    public EntityBanDetail(Date createTime, long groupId, long botId, String reason) {
        this.createTime = createTime;
        this.groupId = groupId;
        this.botId = botId;
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public long getGroupId() {
        return groupId;
    }

    public long getBotId() {
        return botId;
    }

    public String getReason() {
        return reason;
    }
}
