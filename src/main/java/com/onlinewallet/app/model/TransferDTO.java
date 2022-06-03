package com.onlinewallet.app.model;

public class TransferDTO {
	String fromWalletId;
	String toWalletId;
	Double amount;
	public TransferDTO() {
		super();
	}
	public TransferDTO(String fromWalletId, String toWalletId, Double amount) {
		super();
		this.fromWalletId = fromWalletId;
		this.toWalletId = toWalletId;
		this.amount = amount;
	}
	public String getFromWalletId() {
		return fromWalletId;
	}
	public void setFromWalletId(String fromWalletId) {
		this.fromWalletId = fromWalletId;
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
