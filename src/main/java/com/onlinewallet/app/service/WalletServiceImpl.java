package com.onlinewallet.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.onlinewallet.app.exceptions.LoginException;
import com.onlinewallet.app.exceptions.WalletException;
import com.onlinewallet.app.model.DepositDTO;
import com.onlinewallet.app.model.TransferDTO;
import com.onlinewallet.app.model.UserDTO;
import com.onlinewallet.app.model.UserWallet;
import com.onlinewallet.app.model.WalletDTO;
import com.onlinewallet.app.repo.WalletRepo;
import java.util.Date;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	private WalletRepo walletRepo;

	@Override
	public UserWallet createWallet(WalletDTO wallet) throws WalletException {

		// write a logic to check if email already exists in database
		UserWallet foundWallet = this.walletRepo.findByEmail(wallet.getEmail());
		if (foundWallet != null)
			throw new WalletException("Email Id already Exist, please choose other email.");
		UserWallet newWallet = new UserWallet(null, wallet.getName(), wallet.getBalance(), wallet.getEmail(),
				wallet.getPassword());
		return this.walletRepo.save(newWallet);
	}

	@Override
	public List<UserWallet> getAllWallets() {

		return this.walletRepo.findAll();
	}

	@Override
	public UserWallet updateWallet(UserWallet wallet) {

		return this.walletRepo.save(wallet);
	}

	@Override
	public UserWallet depositFunds(DepositDTO deposit) throws WalletException {
		Optional<UserWallet> optWallet = this.walletRepo.findById(deposit.getToWalletId());
		if (optWallet.isEmpty())
			throw new WalletException("Wallet not found to depot funds !");
		UserWallet wallet = optWallet.get();
		Double currentBalance = wallet.getBalance();
		wallet.setBalance(currentBalance + deposit.getAmount());
		return this.walletRepo.save(wallet);
	}

	@Override
	public String fundTransfer(TransferDTO transferDTO) throws WalletException {

		Optional<UserWallet> optFromWallet = this.walletRepo.findById(transferDTO.getFromWalletId());
		if (optFromWallet.isEmpty())
			throw new WalletException("From wallet not found !");

		Optional<UserWallet> optToWallet = this.walletRepo.findById(transferDTO.getToWalletId());
		if (optToWallet.isEmpty())
			throw new WalletException("To wallet not found !");
		UserWallet fromWallet = optFromWallet.get();
		UserWallet toWallet = optToWallet.get();
		Double balance = fromWallet.getBalance();
		if (balance < transferDTO.getAmount())
			throw new WalletException("Insufficent Balace in from Wallet .");

		fromWallet.setBalance(balance - transferDTO.getAmount());
		toWallet.setBalance(toWallet.getBalance() + transferDTO.getAmount());
		this.walletRepo.save(fromWallet);
		this.walletRepo.save(toWallet);

		return "Fund Transfer Successful !";
	}

	@Override
	public String login(UserDTO loginUser) throws LoginException {

		UserWallet userWallet = this.walletRepo.findByEmail(loginUser.getEmail());
		if (userWallet == null)
			throw new LoginException("User email does not exists.");
		
		if (!userWallet.getPassword().equals(loginUser.getPassword()))
			throw new LoginException("Password does't match.");

		String issuer = userWallet.getEmail();
		Date expiry = new Date(System.currentTimeMillis() + (60 * 60 * 1000));

		 

		return Jwts.builder().setIssuer(issuer).setExpiration(expiry)
				.signWith(SignatureAlgorithm.HS512, "Secret123").compact();
	}

	@Override
	public List<UserWallet> sortWalltsByBalance() {
		
		return this.walletRepo.findAll(Sort.by(Sort.Direction.ASC, "balance"));
	}

}
