package com.onlinewallet.app.service;

import java.util.List;

import com.onlinewallet.app.exceptions.LoginException;
import com.onlinewallet.app.exceptions.WalletException;
import com.onlinewallet.app.model.DepositDTO;
import com.onlinewallet.app.model.TransferDTO;
import com.onlinewallet.app.model.UserDTO;
import com.onlinewallet.app.model.UserWallet;
import com.onlinewallet.app.model.WalletDTO;

public interface WalletService {

	UserWallet createWallet(WalletDTO wallet)throws WalletException;

	List<UserWallet> getAllWallets();

	UserWallet updateWallet(UserWallet wallet);

	UserWallet depositFunds(DepositDTO deposit)throws WalletException;

	String fundTransfer(TransferDTO transferDTO)throws WalletException;
	
	String login(UserDTO user)throws LoginException;

	List<UserWallet> sortWalltsByBalance();

}
