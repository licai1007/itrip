package org.java.pojo;

import java.io.Serializable;
import java.util.Date;

public class ItemParamItem implements Serializable {
    private Integer id;

    private Integer itemid;

    private Date created;

    private Date updated;

    private String paramdata;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getParamdata() {
        return paramdata;
    }

    public void setParamdata(String paramdata) {
        this.paramdata = paramdata == null ? null : paramdata.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", itemid=").append(itemid);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", paramdata=").append(paramdata);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}