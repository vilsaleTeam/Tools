package et.vis.test;

import java.util.ArrayList;
import java.util.List;

import et.common.utils.*;


public class Test {
	public static void main(String[] args) throws Exception {
		String SQL = "select \n" +
				"SUM(ACTUAL_QUANTITY_T) ACTUAL_QUANTITY_T,SUM(ACTUAL_MONEY) ACTUAL_MONEY,\n" +
				"SUM(BACK_QUANTITY_T) BACK_QUANTITY_T,SUM(BACK_MONEY) BACK_MONEY,\n" +
				"count(DISTINCT IF(BACK_QUANTITY_T > 0,cs.CUSTOMER_ID, NULL)) BACK_CUSTOMER,\n" +
				"count(DISTINCT IF(SALE_QUANTITY > 0, cs.CUSTOMER_ID, NULL )) SALE_CUSTOMER,\n" +
				"SUM(ACCOUNT_AMOUNT) ACCOUNT_AMOUNT,\n" +
				"count(DISTINCT IF(ACCOUNT_AMOUNT > 0, cs.CUSTOMER_ID, NULL )) MAKE_MONEY_CUSTOMER\n" +
				"from customer_in_week_statistical cs\n" +
				"where cs.CORP_ID = ?\n" +
				"and cs.DATE_TIME = ?";
		System.out.println(SQL);
	}

	public static void test() throws Exception {
		int money = 4;
		double nowUseMoney = 0;
		List<String> list = new ArrayList<>();
		list = getDayList("2018-06-01", "2018-06-30", 1);
		for (String string : list) {
			if (Math.abs(DateUtilsCommon.getNewMondayPlus(string)) < 5) {
				nowUseMoney = test1(money, nowUseMoney);
				nowUseMoney = test1(money, nowUseMoney);
				System.out.println(nowUseMoney);
			}
		}
	
	}
	
	public static double test1(int money, double nowUseMoney) {
		if (nowUseMoney<100) {
			nowUseMoney += money;
		}else if (nowUseMoney >=100 && nowUseMoney< 150) {
			nowUseMoney += (money*0.8);
		}else if (nowUseMoney >=150) {
			nowUseMoney += (money*0.5);
		}
		return nowUseMoney;
	}

	public static double zhengchang(int money) {
		
		return 0;
	}
	
	public static List<String> getDayList(String startDate, String endDate,int subDay) throws Exception{
		List<String> list = new ArrayList<>();
		//填充
		String date = startDate;
		while (DateUtilsCommon.differentDays(date, endDate) > 0) {
			list.add(date);
			date = DateUtilsCommon.getOtherDay(date, subDay);
		}
		return list;
	}
}
