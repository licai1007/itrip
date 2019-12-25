package org.java.pojo;

import java.io.Serializable;
import java.util.Date;

public class OrderShipping implements Serializable {
    private String orderid;

    private String receivername;

    private String receiverphone;

    private String receivermobile;

    private String receiverstate;

    private String receivercity;

    private String receiverdistrict;

    private String receiveraddress;

    private String receiverzip;

    private Date created;

    private Date updated;

    private static final long serialVersionUID = 1L;

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid == null ? null : orderid.trim();
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername == null ? null : receivername.trim();
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone == null ? null : receiverphone.trim();
    }

    public String getReceivermobile() {
        return receivermobile;
    }

    public void setReceivermobile(String receivermobile) {
        this.receivermobile = receivermobile == null ? null : receivermobile.trim();
    }

    public String getReceiverstate() {
        return receiverstate;
    }

    public void setReceiverstate(String receiverstate) {
        this.receiverstate = receiverstate == null ? null : receiverstate.trim();
    }

    public String getReceivercity() {
        return receivercity;
    }

    public void setReceivercity(String receivercity) {
        this.receivercity = receivercity == null ? null : receivercity.trim();
    }

    public String getReceiverdistrict() {
        return receiverdistrict;
    }

    public void setReceiverdistrict(String receiverdistrict) {
        this.receiverdistrict = receiverdistrict == null ? null : receiverdistrict.trim();
    }

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress == null ? null : receiveraddress.trim();
    }

    public String getReceiverzip() {
        return receiverzip;
    }

    public void setReceiverzip(String receiverzip) {
        this.receiverzip = receiverzip == null ? null : receiverzip.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderid=").append(orderid);
        sb.append(", receivername=").append(receivername);
        sb.append(", receiverphone=").append(receiverphone);
        sb.append(", receivermobile=").append(receivermobile);
        sb.append(", receiverstate=").append(receiverstate);
        sb.append(", receivercity=").append(receivercity);
        sb.append(", receiverdistrict=").append(receiverdistrict);
        sb.append(", receiveraddress=").append(receiveraddress);
        sb.append(", receiverzip=").append(receiverzip);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}