package banlist.sinanya.entity;

import java.util.Date;

import static banlist.sinanya.tools.makedata.GetGroupName.getGroupName;

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
    private long Id;
    private long botId;
    private String reason;

    public EntityBanDetail(Date createTime, long Id, long botId, String reason) {
        this.createTime = createTime;
        this.Id = Id;
        this.botId = botId;
        this.reason = reason;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public long getId() {
        return Id;
    }

    public long getBotId() {
        return botId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {

        return "\n添加时间=" + createTime +
                ", 群号/QQ号=" + Id +
                ",群名/QQ昵称=" + getGroupName(Id) +
                ", 录入机器人Id=" + botId +
                ", 录入原因='" + reason;
    }
}
