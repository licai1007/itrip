package org.java.pojo;

import java.io.Serializable;
import java.util.Date;

public class Content implements Serializable {
    private Integer id;

    private Integer categoryid;

    private String title;

    private String subtitle;

    private String titledesc;

    private String url;

    private String pic;

    private String pic2;

    private Date created;

    private Date updated;

    private String content;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle == null ? null : subtitle.trim();
    }

    public String getTitledesc() {
        return titledesc;
    }

    public void setTitledesc(String titledesc) {
        this.titledesc = titledesc == null ? null : titledesc.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2 == null ? null : pic2.trim();
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", categoryid=").append(categoryid);
        sb.append(", title=").append(title);
        sb.append(", subtitle=").append(subtitle);
        sb.append(", titledesc=").append(titledesc);
        sb.append(", url=").append(url);
        sb.append(", pic=").append(pic);
        sb.append(", pic2=").append(pic2);
        sb.append(", created=").append(created);
        sb.append(", updated=").append(updated);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}