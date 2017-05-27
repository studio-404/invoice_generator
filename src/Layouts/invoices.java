package Layouts;

import javafx.scene.layout.HBox;

public class invoices {
	private int id;
	private String createTime;
	private String serviceStart;
	private String serviceEnd;
	private String userName;
	private String serviceDescription;
	private int servicePrice;
	private int serviceDiscount;
	private String payStatus;
	private HBox action;
	
	public invoices() {
		
		this.id = 0;
		this.createTime = "";
		this.serviceStart = "";
		this.serviceEnd = "";
		this.userName = "";
		this.serviceDescription = "";
		this.servicePrice = 0;
		this.serviceDiscount = 0;
		this.payStatus = "";
		this.action = new HBox();
	}
	
	public invoices(int id, String createTime, String serviceStart, String serviceEnd, String userName, String serviceDescription, int servicePrice, int serviceDiscount, String payStatus, HBox action) {
		
		this.id = id;
		this.createTime = createTime;
		this.serviceStart = serviceStart;
		this.serviceEnd = serviceEnd;
		this.userName = userName;
		this.serviceDescription = serviceDescription;
		this.servicePrice = servicePrice;
		this.serviceDiscount = serviceDiscount;
		this.payStatus = payStatus;
		this.action = action;
		
	}
	
	public String getServiceDescription() {
		return serviceDescription;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public int getId() {
		return id;
	}
	
	public double getServiceDiscount() {
		return serviceDiscount;
	}
	
	public String getServiceEnd() {
		return serviceEnd;
	}
	
	public double getServicePrice() {
		return servicePrice;
	}
	
	public String getServiceStart() {
		return serviceStart;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	
	public void setServiceDiscount(int serviceDiscount) {
		this.serviceDiscount = serviceDiscount;
	}
	
	public void setServiceEnd(String serviceEnd) {
		this.serviceEnd = serviceEnd;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setServicePrice(int servicePrice) {
		this.servicePrice = servicePrice;
	}
	
	public void setServiceStart(String serviceStart) {
		this.serviceStart = serviceStart;
	}
	
	public String getPayStatus() {
		return payStatus;
	}
	
	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}
	
	public HBox getAction() {
		return action;
	}
	
	public void setAction(HBox action) {
		this.action = action;
	}
}
