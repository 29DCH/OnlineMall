package priv.jesse.mall.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 评论
 */
@Entity
public class Comment implements Serializable {
    @Id()
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
    /**
     * 商品Id
     */
    @Column
    private String name;
    /**
     * 评论用户名
     */
    @Column
    private String time;
    /**
     * 评论时间
     */
    @Column
    private Integer likes;
    /**
     * 点赞数
     */
    @Column
    private String content;
    /**
     * 评论内容
     */
    @Column
    private String image;
    /**
     * 评论图片
     */
    @Column
    private Integer commentnum;
    /**
     * 评论次数
     */
    @Column
    @Transient
    private Comment comment;

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    private static final long serialVersionUID = 1L;

    public Comment(Integer id, String name, String time,Integer likes, String content,String image, Integer commentnum) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.likes=likes;
        this.content=content;
        this.image = image;
        this.commentnum = commentnum;
    }

    public Comment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
            this.likes = likes;
        }

    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getCommentnum() { return commentnum; }

    public void setCommentnum(Integer commentnum) {
        this.commentnum = commentnum;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Comment other = (Comment) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
                && (this.getLikes() == null ? other.getLikes() == null : this.getLikes().equals(other.getLikes()))
                && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
                && (this.getImage() == null ? other.getImage() == null : this.getImage().equals(other.getImage()))
                && (this.getCommentnum() == null ? other.getCommentnum() == null : this.getCommentnum().equals(other.getCommentnum()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getLikes() == null) ? 0 : getLikes().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getImage() == null) ? 0 : getImage().hashCode());
        result = prime * result + ((getCommentnum() == null) ? 0 : getCommentnum().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", time=").append(time);
        sb.append(", likes=").append(likes);
        sb.append(", content=").append(content);
        sb.append(", image=").append(image);
        sb.append(", commentnum=").append(commentnum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}