package com.code.repository.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.Lists;

import java.math.BigDecimal;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {

    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        String strDate = "";
        Format dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = dateFormat.format(date);
        return strDate;
    }

    public static String formatYyyyMMdd(Date date) {
        if (date == null) {
            return "";
        }
        Format dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static String string2Date(String longTime) {
        long time = Long.parseLong(longTime);
        Date date = new Date(time);
        String strDate = "";
        Format dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        strDate = dateFormat.format(date);
        return strDate;
    }

    /**
     * ????????????
     * 
     * @param beforeDate
     * @param days ???????
     * @return
     */
    public static Date dateBefore(String beforeDate, int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(beforeDate);
            long Time = (date.getTime() / 1000) - 60 * 60 * 24 * days;
            date.setTime(Time * 1000);

        } catch (ParseException e) {
        }
        return date;
    }

    public static Date parseYyyyMMdd(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * ??????????????????????????????????
     *
     * @param beforDate
     * @return
     */
    public static Date dateBefore(String beforDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(beforDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (dayOfWeek < 0)
                dayOfWeek = 0;
            if (dayOfWeek == 0) {
                date = DateUtil.dateBefore(beforDate, 4);
            } else if (dayOfWeek == 1) {
                date = DateUtil.dateBefore(beforDate, 5);
            } else if (dayOfWeek == 2) {
                date = DateUtil.dateBefore(beforDate, 5);
            } else if (dayOfWeek == 3) {
                date = DateUtil.dateBefore(beforDate, 5);
            } else if (dayOfWeek == 4) {
                date = DateUtil.dateBefore(beforDate, 3);
            } else if (dayOfWeek == 5) {
                date = DateUtil.dateBefore(beforDate, 3);
            } else {
                date = DateUtil.dateBefore(beforDate, 4);
            }
        } catch (ParseException e) {
        }
        return date;
    }

    /**
     * String ????Date
     *
     * @param date
     * @return
     */
    public static Date stringToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date conDate = null;
        try {
            conDate = sdf.parse(date);
        } catch (ParseException e) {

        }
        return conDate;
    }

    /**
     * ????????
     *
     * @param dateStr 2011-01-01 00:00:00
     * @return 2011-01-02 00:00:00
     */
    public static String getNextDay(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sf.parse(dateStr);
            return sf.format(getNextDay(date));
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * ????????
     *
     * @return 2011-01-02 00:00:00
     */
    public static Date getNextDay(Date date) {
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, 1);
        return c.getTime();
    }

    /**
     * ???????
     * ??????yyyymm
     * @param date
     * @return
     * @author huamo
     */
    public static String getMonth(Date date){
    	if (date == null) {
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
        return sf.format(date);
    }

    /**
     * ????????????
     * @return
     */
    public static Date getCurrentMonthStartDate() {
        Calendar ca = Calendar.getInstance();

        ca.setTime(new Date());
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.DAY_OF_MONTH, 1);

        Date firstDate = ca.getTime();

        return firstDate;
    }

    /**
     * ??????????????,??????
     * @return
     */
    public static Date getCurrentMonthEndDate() {
        Calendar ca = Calendar.getInstance();

        ca.setTime(new Date());
        ca.set(Calendar.HOUR_OF_DAY, 23);
        ca.set(Calendar.MINUTE, 59);
        ca.set(Calendar.SECOND, 59);
        ca.set(Calendar.DAY_OF_MONTH, 1);
        ca.add(Calendar.MONTH, 1);
        ca.add(Calendar.DAY_OF_MONTH, -1);

        Date lastDate = new Date(ca.getTime().getTime());

        return lastDate;
    }



	public static String formatYyyyMM(Date date) {
		if (date == null) {
			return "";
		}
		Format dateFormat = new SimpleDateFormat("yyyy-MM");
		String strDate = dateFormat.format(date);
		return strDate;
	}






	public static Date parseYyyyMMddHHmmss(String strDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(strDate);
		} catch (ParseException e) {
		}
		return date;
	}
	

	
	

	
	
	/**
	 * 当月最后一天日期,最后一秒
	 * 
	 * @return
	 */
	public static Date getDateLastTime(Date date) {
		Calendar ca = Calendar.getInstance();

		ca.setTime(date);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 0);

		Date lastDate = ca.getTime();

		return lastDate;
	}



	/**
	 * 指定月第一天日期
	 * 
	 * @return
	 */
	public static Date getMonthStartDate(Date date) {
		if (date == null) {
			return null;
		}
		Calendar ca = Calendar.getInstance();

		ca.setTime(date);
		ca.set(Calendar.HOUR_OF_DAY, 0);
		ca.set(Calendar.MINUTE, 0);
		ca.set(Calendar.SECOND, 0);
		ca.set(Calendar.MILLISECOND, 0);
		ca.set(Calendar.DAY_OF_MONTH, 1);

		Date firstDate = ca.getTime();

		return firstDate;
	}

	/**
	 * 指定月最后一天日期,最后一秒
	 * 
	 * @return
	 */
	public static Date getMonthEndDate(Date date) {
		Calendar ca = Calendar.getInstance();

		ca.setTime(date);
		ca.set(Calendar.HOUR_OF_DAY, 23);
		ca.set(Calendar.MINUTE, 59);
		ca.set(Calendar.SECOND, 59);
		ca.set(Calendar.MILLISECOND, 0);
		ca.set(Calendar.DAY_OF_MONTH, 1);
		ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DAY_OF_MONTH, -1);

		Date lastDate = ca.getTime();

		return lastDate;
	}

	/**
	 * 日期是否在上个月
	 * 
	 * @param date
	 * @return
	 */
	public static boolean beforeCurrentMonth(Date date) {
		Date start = getCurrentMonthStartDate();
		if (start.getTime() > date.getTime()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 日期是否在当前月
	 * 
	 * @param date
	 * @return
	 */
	public static boolean inCurrentMonth(Date date) {
		Date start = getCurrentMonthStartDate();
		Date end = getCurrentMonthEndDate();
		if (start.getTime() <= date.getTime()
				&& end.getTime() >= date.getTime()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 日期是否在下个月
	 * 
	 * @param date
	 * @return
	 */
	public static boolean afterCurrentMonth(Date date) {
		if (date == null) {
			return true;
		}
		Date end = getCurrentMonthEndDate();
		if (end.getTime() < date.getTime()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 获取时间段中以月份为单位的起止时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<Map<String,Object>> getStartEndDateByMonth(Date startDate,Date endDate){
		if(startDate==null ||endDate==null ||startDate.after(endDate)){
			return new ArrayList<Map<String,Object>>();
		}
		
		Calendar startCa = Calendar.getInstance();
		Calendar endCa = Calendar.getInstance();
		
		startCa.setTime(startDate);
		endCa.setTime(endDate);
		
		int startMonth = startCa.get(Calendar.MONTH);
		int endMonth = endCa.get(Calendar.MONTH);
		
		List<Map<String,Object>> monthList = new ArrayList<Map<String,Object>>();
		if(startMonth == endMonth){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", startDate);
			map.put("end", endDate);
			monthList.add(map);
			return monthList;
		}else{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", startDate);
			map.put("end", getMonthEndDate(startDate));
			monthList.add(map);
		}
		
		for(;startMonth<endMonth-1;startMonth++){
			startCa.add(Calendar.MONTH, 1);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("start", getMonthStartDate(startCa.getTime()));
			map.put("end", getMonthEndDate(startCa.getTime()));
			monthList.add(map);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", getMonthStartDate(endDate));
		map.put("end", endDate);
		monthList.add(map);
		return monthList;
	}

	public static long getBetweenHours(Date start, Date end){
		try {
			if (start == null || end == null || start.getTime() > end.getTime()){
				return 0;
			}
			long time = end.getTime() - start.getTime();
			long hour = time / 1000 / (60 * 60);
			return hour;
		}catch (Exception e){
			return 0;
		}
	}

	/**
	 * 默认时间之前几天
	 *
	 * @since 2016年9月11日
	 * @param beforeDate
	 * @param days
	 * @return
	 */
	public static Date dateBefore(Date beforeDate, int days) {
		java.util.Date date = beforeDate;
		try {
			long Time = (date.getTime() / 1000) - 60 * 60 * 24 * days;
			date.setTime(Time * 1000);
		} catch (Exception e) {
			return null;
		}
		return date;
	}

	private static final SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

	public static Date toDayBegin(Date date) {
		if (date == null) {
			return null;
		}
		try {
			return f.parse(f.format(date));
		} catch (ParseException e) {
			return null;
		}
	}

	public static Long getLongValue(String str){
		if (StringUtils.isBlank(str)){
			return 0L;
		}
		if (str.contains(" ")){
			String[] daytime = str.split(" ");
			Long days = Long.parseLong(daytime[0])*1000 * 60 * 60 * 24;
			String[]  time = daytime[1].split(":");
			Long hours = Long.parseLong(time[0]) * 60 * 60 * 1000;
			Long minutes = Long.parseLong(time[1]) * 60 * 1000;
			Long seconds = Long.parseLong(time[2]) * 1000;
			return days+hours+minutes+seconds;
		}else {
			String[] time = str.split(":");
			if (time.length==1){
				return Long.parseLong(time[0])*1000;
			}
			if (time.length==2){
				Long minutes = Long.parseLong(time[0]) * 60 * 1000;
				Long seconds = Long.parseLong(time[1]) * 1000;
				return minutes+seconds;
			}
			if (time.length==3){
				Long hours = Long.parseLong(time[0]) * 60 * 60 * 1000;
				Long minutes = Long.parseLong(time[1]) * 60 * 1000;
				Long seconds = Long.parseLong(time[2]) * 1000;
				return hours+minutes+seconds;
			}
		}
		return 0L;
	}

	public static Date parseToDateTime(String str) {
		SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return parseToDateTime(str, DATE_TIME_FORMATTER);
	}

	public static Date parseToDateTime(String str, SimpleDateFormat format) {
		if(StringUtils.isBlank(str)){
			return null;
		}
		try {
			return format.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}


	public static void main(String[] args) {


		 String msg = "{\"data\":{\"headers\":[{\"enable\":true,\"key\":\"id\",\"name\":\"id\"},{\"enable\":true,\"key\":\"carNo\",\"name\":\"车牌号\"},{\"enable\":true,\"key\":\"customsCode\",\"name\":\"所属海关\"},{\"enable\":true,\"key\":\"weight\",\"name\":\"车重\"},{\"enable\":true,\"key\":\"feature\",\"name\":\"扩展信息\"}],\"paging\":{\"currentPage\":1,\"pageSize\":10,\"totalItem\":1037},\"resultList\":[{\"carNo\":\"粤ZFZ91港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1089,\"weight\":\"0kg\"},{\"carNo\":\"粤ZDD70港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1087,\"weight\":\"0kg\"},{\"carNo\":\"粤ZHP50港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1086,\"weight\":\"0kg\"},{\"carNo\":\"粤ZGH73港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1085,\"weight\":\"0kg\"},{\"carNo\":\"粤ZYP20港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1084,\"weight\":\"0kg\"},{\"carNo\":\"粤ZGX37港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1082,\"weight\":\"0kg\"},{\"carNo\":\"粤ZDH54港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1081,\"weight\":\"0kg\"},{\"carNo\":\"粤ZGV47港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1080,\"weight\":\"0kg\"},{\"carNo\":\"粤ZCH96港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1079,\"weight\":\"0kg\"},{\"carNo\":\"粤ZFN08港\",\"customsCode\":\"GATE_13350901\",\"feature\":\"{\\\"owner\\\": \\\"SNR\\\"}\",\"id\":1078,\"weight\":\"0kg\"}],\"success\":true},\"success\":true}";
		 System.out.println(msg);
		 System.out.println(StringEscapeUtils.escapeHtml4(msg));



//		Map<String,Map<String, String>> map = new HashMap<>();
//		map.put("12",new HashMap<>());
//		Map<String,String> map2 = new HashMap<>();
//		Map<String,Map<String, String>> map3 = new HashMap<>();
//		map2.put("1222","1222");
//		map3.put("12qw",map2);
//		map.putAll(map3);
//
//
//		String queryStr = "&_callback=jsonp_1561468468027_68541";
//		String [] paramStr = queryStr.split("&");
//		for(String s : paramStr){
//			if(StringUtils.isNotBlank(s) && (s.contains("callback=") ||s.contains("_callback=") )){
//				System.out.println(s.substring(s.indexOf("=")+1,s.length()));
//			}
//		}




//		String[] items = new String[]{"a","b"};
//		List<String> ss = Lists.newArrayList("a","b");
//		System.out.println(Arrays.asList(items));
//		System.out.println(ss);
//
//
//		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		try{
//			System.out.println(format.parse("2019-05-01 00:00:00").getTime());
//		} catch(Exception e){
//
//		}
//
//
//		String errorDetail = "businessCode:LP00082378844ABC,errorMsg:S_SAVEDB_FAILED|updateDeclareOrderAndSaveLog failed,cbCode:CB10000357715700";
//
//		// 提取errorMsg  errorMsg
//		String content = StringUtils.substring(errorDetail,errorDetail.indexOf("errorMsg")+9,errorDetail.indexOf("errorMsg")+39);
//		if(StringUtils.isNotBlank(content)){
//			System.out.println("content:"+content);
//			return;
//		}
//		// 取10个字符
//		System.out.println(StringUtils.substring(errorDetail,0,30));
//		return;



//		toDayBegin(null);
//
//
//		String ss = "通关异常理_国检审单超时_广州";
//
//		System.out.println(ss.replace("通关异常处理_",""));



//		List<String> cnts = Lists.newArrayList();
//		cnts.add("2019-02-22 03:55:00");
//		cnts.add("2019-02-22 07:45:00");
//		cnts.add("2019-02-22 17:25:00");
//		cnts.add("2019-02-22 16:20:00");
//		cnts.add("2019-02-22 15:15:00");
//		cnts.add("2019-02-22 03:15:00");
//
//		// 按照日期排序
//		Collections.sort(cnts, new Comparator<String>() {
//			@Override
//			public int compare(String o1, String o2) {
//				try {
//						long l1 = DateUtil.parseToDateTime(o1).getTime();
//						long l2 = DateUtil.parseToDateTime(o2).getTime();
//						System.out.println("o1:"+o1+","+l1);
//						System.out.println("o2:"+o2+","+l2);
//						if (l1 < l2) {
//							return -1;
//						} else if (l2 == l1) {
//							return 0;
//						} else {
//							return 1;
//						}
//				} catch (Exception e) {
//					 System.out.println(e);
//					 return 0;
//				}
//			}
//		});
//
//		for(String s : cnts){
//			System.out.println("after:"+s);
//		}





//		System.out.println(DateUtil.toDayBegin(new Date()));
//		String start = "2013-01-01 20:00:00";
//		String end = "2013-03-11 12:03:40";
//
//		List<Map<String,Object>> list = getStartEndDateByMonth(parseYyyyMMddHHmmss(start),parseYyyyMMddHHmmss(end));
//		for(Map m : list){
//			System.out.println(formatDate((Date) m.get("start"))+" ##"+formatDate((Date) m.get("end")));
//		}
//		System.out.println(123);
//
//		Date ss = DateUtil.toDayBegin(DateUtil.dateBefore(new Date(),1));
//
//		Date ss1 = DateUtil.dateBefore(new Date(),0);
//		System.out.println(ss);
//		System.out.println(ss1);
//		System.out.println(ss.getTime());
//		System.out.println(ss1.getTime());


//		Date ss00 =DateUtil.parseYyyyMMddHHmmss("2019-02-18 00:00:00");
//		Date ss01 =DateUtil.parseYyyyMMddHHmmss("2018-02-20 00:00:00");
//
//		System.out.println(ss00.getTime());
//		System.out.println(ss01.getTime());


//
//		String num = "51696521212.230";
//		System.out.println(Float.valueOf(num).intValue());
//		System.out.println(Math.ceil(Double.valueOf(num).longValue()));
//		BigDecimal b = new BigDecimal(Double.valueOf(num).longValue());
//		System.out.println(b.toString());
//		System.out.println(DateUtil.dateBefore(new Date(),7).getTime());

//		String ss = "[2]电子口岸申报中,desc: 清单新增申报成功[电商企业编码：3301968FU0订单编号：223299468117755430],操作人：sys";
//		System.out.println(ss.length());
//		System.out.println(ss.substring(0,200));
//        System.out.println(DateUtil.class.getSimpleName());
//
//        Object we = null;
//        System.out.println((String)we);

//		List<Date> sdate = DateUtil.splitPayTime(ss00,ss01);
//		System.out.println(sdate.size());
//		for(Date date : sdate){
//			System.out.println(date);
//		}

	}



	public static  List<Date> splitPayTime(Date startTime, Date endTime) {
		List<Date> payTimes = new ArrayList<>();
		long hours = DateUtil.getBetweenHours(startTime, endTime);
		if (hours < 1) {
			return payTimes;
		}
		for (int i = 0; i < hours+1; i++) {
			if (startTime.getTime() >= endTime.getTime()) {
				return payTimes;
			}
			payTimes.add(startTime);
			// 向后移动一个小时
			startTime = DateUtils.addHours(startTime, 1);
		}
		return payTimes;
	}
    
}

