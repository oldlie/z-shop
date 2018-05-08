package com.iprzd.zshop.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_commodity")
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Commodity {

    @Id @GeneratedValue
    private long id;
    private String title;
    private String summary;
    private String comment;
    private String description;
    @Column(columnDefinition="tinyint default 0")
    private int status;
    @Column(columnDefinition="int default 10")
    private int ranking;
    @Column(columnDefinition="int default 1")
    private int rankingCount;
    @Column(columnDefinition="int default 0")
    private int viewCount;
    @Column(columnDefinition="int default 0")
    private int shareCount;
    private Date createAt;
    private Date updateAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getRankingCount() {
        return rankingCount;
    }

    public void setRankingCount(int rankingCount) {
        this.rankingCount = rankingCount;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}