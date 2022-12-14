package com.zzh.oa_system.model.entity.discuss;

import javax.persistence.*;
import java.util.Set;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:13
 * description:
 */
@Entity
@Table(name = "aoa_voteTitles")
public class VoteTitles {

    @Id
    @Column(name = "title_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long titleId;

//	@Column(name="vote_id")				//投票id
//	private Long voteId;

    private String title;                //投票标题

    private String color;                //进度条颜色

    @OneToMany(mappedBy = "voteTitles", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<VoteTitleUser> voteTitleUsers;

    @ManyToOne
    @JoinColumn(name = "vote_id")
    private VoteList voteList;            //关联投标表      投票id


    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

//	public Long getVoteId() {
//		return voteId;
//	}
//
//	public void setVoteId(Long voteId) {
//		this.voteId = voteId;
//	}


    public String getTitle() {
        return title;
    }

    public VoteList getVoteList() {
        return voteList;
    }

    public void setVoteList(VoteList voteList) {
        this.voteList = voteList;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VoteTitles() {
        super();
    }

    @Override
    public String toString() {
        return "VoteTitles [titleId=" + titleId + ", title=" + title + ", color=" + color + "]";
    }


}

