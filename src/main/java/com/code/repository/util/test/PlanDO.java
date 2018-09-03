package com.code.repository.util.test;

import com.code.repository.util.DateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PlanDO extends BaseDO {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3956962988856794694L;


	/**
	 * id
	 */
	private Long id;

	/**
	 * gmtCreate
	 */
	private Date gmtCreate;

	/**
	 * gmtModified
	 */
	private Date gmtModified;

	/**
	 * kaNick
	 */
	private String kaNick;

	/**
	 * 品锟斤拷
	 */
	private String brands;

	/**
	 * 锟斤拷锟斤拷
	 */
	private String shopNick;

	/**
	 * 锟斤拷司锟斤拷锟�	 */
	private String companyName;

	/**
	 * 锟斤拷锟斤拷
	 */
	private Integer quantity;

	/**
	 * 锟叫筹拷锟斤拷图锟�	 */
	private String lowestPrice;

	/**
	 * isDeleted
	 */
	private Integer isDeleted;

	/**
	 * 锟斤拷猫锟桔硷拷
	 */
	private String tmallPrice;

	/**
	 * 锟斤拷锟斤拷锟斤拷
	 */
	private String supplyPrice;

	/**
	 * prop
	 */
	private String prop;

	/**
	 * 锟酵猴拷
	 */
	private String model;

	/**
	 * 锟斤拷锟斤拷锟铰凤拷
	 */
	private Date saleMonth;

	/**
	 * planNo
	 */
	private String planNo;

	/**
	 * categoryType
	 */
	private Integer categoryType;

	/**
	 * memo
	 */
	private String memo;

	/**
	 * 品锟斤拷
	 */
	private String itemBrand;

	/**
	 * itemBrandId
	 */
	private Long itemBrandId;

	/**
	 * itemId
	 */
	private Long itemId;

	/**
	 * productId
	 */
	private Long productId;

	/**
	 * bloodthirstyCoefficient
	 */
	private String bloodthirstyCoefficient;

	/**
	 * basicCoefficient
	 */
	private String basicCoefficient;
	
	private String subsidyRate;
	
	private String operator;
	
	private Long userId;
	
	private int modelType;
	
	private int patternType;
	
	private Date startTime;
	
	private Date endTime;
	
	private String startTimeStr;
	
	private String endTimeStr;
	
	private String timeType;
	
//	public boolean getPreSales(){
//		return "before".equals(timeType);
//	}
//	
//	public boolean getInSales(){
//		return "now".equals(timeType);
//	}
	
	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) throws ParseException {
		this.startTimeStr = startTimeStr;
		this.startTime = DateUtil.stringToDate(startTimeStr);
		
	}
	
	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) throws ParseException {
		this.endTimeStr = endTimeStr;
		this.endTime = DateUtil.getDateLastTime(DateUtil.stringToDate(endTimeStr));
	}

	public void setSaleMonth(Date saleMonth) {
		this.saleMonth = saleMonth;
	}
	
	public int getPatternType() {
		return patternType;
	}

	public void setPatternType(int patternType) {
		this.patternType = patternType;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		if(startTime != null) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			this.startTimeStr = sf.format(startTime);
		}
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		if(endTime != null) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			this.endTimeStr = sf.format(endTime);
		}
	}

	public int getModelType() {
		return modelType;
	}

	public void setModelType(int modelType) {
		this.modelType = modelType;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() {
		return this.gmtCreate;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() {
		return this.gmtModified;
	}

	public void setKaNick(String kaNick) {
		this.kaNick = kaNick;
	}

	public String getKaNick() {
		return this.kaNick;
	}

	public void setBrands(String brands) {
		this.brands = brands;
	}

	public String getBrands() {
		return this.brands;
	}

	public void setShopNick(String shopNick) {
		this.shopNick = shopNick;
	}

	public String getShopNick() {
		return this.shopNick;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyName() {
		return this.companyName;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return this.quantity;
	}

	 

	public void setIsDeleted(Integer isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsDeleted() {
		return this.isDeleted;
	}


	public String getLowestPrice() {
		return lowestPrice;
	}

	public void setLowestPrice(String lowestPrice) {
		this.lowestPrice = lowestPrice;
	}

	public String getTmallPrice() {
		return tmallPrice;
	}

	public void setTmallPrice(String tmallPrice) {
		this.tmallPrice = tmallPrice;
	}

	public String getSupplyPrice() {
		return supplyPrice;
	}

	public void setSupplyPrice(String supplyPrice) {
		this.supplyPrice = supplyPrice;
	}

	public void setProp(String prop) {
		this.prop = prop;
	}

	public String getProp() {
		return this.prop;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getModel() {
		return this.model;
	}


	public Date getSaleMonth() {
		return this.saleMonth;
	}

	public void setPlanNo(String planNo) {
		this.planNo = planNo;
	}

	public String getPlanNo() {
		return this.planNo;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public Integer getCategoryType() {
		return this.categoryType;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getMemo() {
		return this.memo;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public String getItemBrand() {
		return this.itemBrand;
	}

	public void setItemBrandId(Long itemBrandId) {
		this.itemBrandId = itemBrandId;
	}

	public Long getItemBrandId() {
		return this.itemBrandId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public Long getItemId() {
		return this.itemId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getProductId() {
		return this.productId;
	}

	public void setBloodthirstyCoefficient(String bloodthirstyCoefficient) {
		this.bloodthirstyCoefficient = bloodthirstyCoefficient;
	}

	public String getBloodthirstyCoefficient() {
		return this.bloodthirstyCoefficient;
	}

	public void setBasicCoefficient(String basicCoefficient) {
		this.basicCoefficient = basicCoefficient;
	}

	public String getBasicCoefficient() {
		return this.basicCoefficient;
	}

	/**
	 * @return the subsidyRate
	 */
	public String getSubsidyRate() {
		return subsidyRate;
	}

	/**
	 * @param subsidyRate the subsidyRate to set
	 */
	public void setSubsidyRate(String subsidyRate) {
		this.subsidyRate = subsidyRate;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
}
