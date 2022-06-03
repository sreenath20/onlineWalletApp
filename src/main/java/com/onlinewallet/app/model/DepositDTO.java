package com.onlinewallet.app.model;

public class DepositDTO {
	String toWalletId;
	Double amount;
	public DepositDTO() {
		super();
	}
	public DepositDTO(String toWalletId, Double amount) {
		super();
		this.toWalletId = toWalletId;
		this.amount = amount;
	}
	public String getToWalletId() {
		return toWalletId;
	}
	public void setToWalletId(String toWalletId) {
		this.toWalletId = toWalletId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
}
