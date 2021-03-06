package com.moyou.moyouRms.model.notification;

/**
 * 回复评论数据
 * 
 * @author PzC.
 * @time 2017年3月21日 下午5:47:30
 * 
 */
public class RecommentData implements DataModel {
	private Integer userId;// 用户 id
	private String nickname;// 昵称
	private String avatar;// 头像
	private Integer businessId;// 说说或者专辑故事 id
	private String commentTextContent;// 评论文字内容
	private String businessTextContent;// 父评论文本内容
	private Integer commentRelateId;// 评论文字内容对应的评论 id

	public Integer getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Integer businessId) {
		this.businessId = businessId;
	}

	public String getCommentTextContent() {
		return commentTextContent;
	}

	public void setCommentTextContent(String commentTextContent) {
		this.commentTextContent = commentTextContent;
	}

	public String getBusinessTextContent() {
		return businessTextContent;
	}

	public void setBusinessTextContent(String businessTextContent) {
		this.businessTextContent = businessTextContent;
	}

	public Integer getCommentRelateId() {
		return commentRelateId;
	}

	public void setCommentRelateId(Integer commentRelateId) {
		this.commentRelateId = commentRelateId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
