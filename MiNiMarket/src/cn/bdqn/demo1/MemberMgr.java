package cn.bdqn.demo1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import cn.bdqn.demo.Member;


/**
 * 超市会员管理系统操作类
 * @author Z❤M
 *
 */
public class MemberMgr {
	public Scanner input = new Scanner (System.in);
	public static List<Member> memberList = new ArrayList <> ();
	
	//初始化数据
	static {//静态代码块，只能在静态方法中使用
		memberList.add(new Member("12345", "Tom", "123456", 100, new Date()));
		memberList.add(new Member("23456", "Bob", "123456", 102, new Date()));
		memberList.add(new Member("34567", "Jack", "123456", 103, new Date()));
		memberList.add(new Member("45678", "Jerry", "123456", 104, new Date()));
	}

	/**
	 * 生成5位的随机卡号
	 */
	private String creatCardId() {
		String cardId = null;
		do {
			Integer id = (int)(Math.random()*(99999-10000))+10000;
			cardId = id.toString();
			boolean flag = false;
			for (Member member : memberList) {
				if (member.getCardId().equals(member)) {
					System.out.println("会员卡号已经存在");
					flag = true;
					break;
				}
			}
			if (!flag) {
				break;
			}
		} while (true);
		return cardId;
		
	}
	/**
	 * 注册会员
	 * @return
	 */
	private boolean registerMember() {
		boolean result = false;
		Member member = new Member ();
		System.out.print("请输入注册的会员姓名:");
		member.setName(input.next());
		System.out.print("请输入会员密码:");
		String pwd = null;
		boolean flag = false;
		do {
			pwd = input.next();
			if (pwd.length() < 6) {
				System.out.println("密码输入错误，密码至少为6位数");
			}else {
				flag = true;
			}
		} while (!flag);
		//卡号随机生成
		member.setCardId(creatCardId());
		member.setPassword(pwd);
		member.setScore(100);
		member.setRegisterDate(new Date());
		memberList.add(member);
		result = true;
		return result;
	}

	/**
	 * 判断用户是否存在，密码不区分大小写
	 * @return 用户信息
	 */
	private Member hasMember() {
		Member result = null;
		System.out.print("请输入您的会员卡号:");
		String cardId = input.next();
		System.out.print("请输入您的会员密码:");
		String password = input.next();
		for (Member member : memberList) {
			if (member.getCardId().equals(cardId) && 
					member.getPassword().equalsIgnoreCase(password))
				result = member;//将用户信息返回
		}
		return result;
	}
	
	/**
	 * 修改密码
	 * 
	 * @return
	 */
	private boolean updatePwd() {
		boolean result = false;
		Member member = hasMember();
		if (member != null) {
			boolean flag = false;
			do {
				System.out.print("请输入新的密码:");
				String pwd = input.next();
				if (pwd.length() < 6) {
					System.out.println("密码的长度不能小于6位，请重新输入");
				}else {
					member.setPassword(pwd);
					flag = true;
					System.out.println("修改密码成功");
				}
			} while (!flag);
			result = true;
		}else {
			System.out.println("账号或密码输入有误，请重新输入");
		}
		return result;
	}
	/**
	 * 积分查询
	 */
	private void findScore() {
		Member member = hasMember();
		if (member != null) {
			System.out.println("卡号\t姓名\t密码\t积分\t注册时间");
			Date date = new Date ();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String format = sdf.format(date);
			System.out.println(member.getCardId()+"\t"+member.getName()+"\t"
			                   +member.getPassword()+"\t"+member.getScore()+"\t"+format);
		}else {
			System.out.println("账号或密码输入有误，请重新输入");
		}
	}

	/**
	 * 积分兑换
	 * @return
	 */
	private boolean exchangeScore() {
		boolean result = false;
		Member member = hasMember();//登录账号密码
		if (member != null) {
			System.out.print("请输入您要兑换的积分(100积分兑换1元)：");
			int score = input.nextInt();
			if (score <= member.getScore()) {//判断积分是否有效
				member.setScore(member.getScore()-score);
				System.out.print("您兑换的积分为:"+score*1.0/100+"元");
				result = true;
			}else {
				System.out.println("积分不足，不能兑换");
			}
			
		}else {
			System.out.println("输入的账号或密码有误");
		}
		return result;
	}

	/**
	 * 积分累计
	 * @return 
	 */
	private boolean addScore() {
		boolean flag = false;
		Member member = hasMember ();//进行分析，用输入的信息与原有的信息进行比较，若有就可以登陆
		if (member != null) {
			System.out.print("请输入您花费的钱数以便进行积分累加:");
			int price = input.nextInt();
			member.setScore(member.getScore()+price);
			flag = true;
		}else {
			System.out.println("用户不存在");
		}
		return flag;
	}

	/**
	 * 程序菜单栏展示
	 */
	public void menu () {
		System.out.println("*****************欢迎进入超市会员管理系统*****************");
		System.out.println("1.积分累计    2.积分兑换   3.查询剩余积分   4.修改密码   5.开卡   6.退卡");
		System.out.println("*****************************************************");
		System.out.print("请选择:");
	}
	
	/**
	 * 程序入口
	 * @author Z❤M
	 *
	 */
	public void start () {
		do {
		    menu ();
			int choose = input.nextInt();
			switch (choose) {
			case 1:
				System.out.println("[积分累计]");
				if (addScore()) {
					System.out.println("积分累计成功");
				}else {
					System.out.println("积分累计失败");
				}
				break;
			case 2:
				System.out.println("[积分兑换]");
				if (exchangeScore()) {
					System.out.println("积分兑换成功");
				}else {
					System.out.println("积分兑换失败");
				}
				break;
			case 3:
				System.out.println("[查询剩余积分]");
				findScore();
				break;
			case 4:
				System.out.println("[修改密码]");
				if (updatePwd()) {
					System.out.println("修改密码成功");
				}else {
					System.out.println("修改密码失败");
				}
				break;
			case 5:
				System.out.println("[开卡 ]");
				if (registerMember()) {
					System.out.println("恭喜您，会员注册成功");
				}else {
					System.out.println("会员注册失败");
				}
				break;
			case 6:
				System.out.println("欢迎下次光临");
				System.exit(1);//退出JVM虚拟机
				break;

			default:
				System.out.println("输入有误，请重新输入!!!");
				break;
			}
			
		} while (true);
	
	}







}
