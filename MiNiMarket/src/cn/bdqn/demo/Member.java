package cn.bdqn.demo;

import java.io.Serializable;
import java.util.Date;

/**
  * 会员实体类
 * @author Z❤M
 *
 */
public class Member implements Serializable{
	private String cardId;//卡号
	private String name;//会员名字
	private String password;//会员密码
	private int score ;//会员积分
	private Date registerDate ;//会员注册时间
	public Member(String cardId, String name, String password, int score, Date registerDate) {
		super();
		this.cardId = cardId;
		this.name = name;
		this.password = password;
		this.score = score;
		this.registerDate = registerDate;
	}
	public Member() {
		super();
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	 
	

}
