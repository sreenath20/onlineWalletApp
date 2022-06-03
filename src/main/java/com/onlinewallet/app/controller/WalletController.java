package com.onlinewallet.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onlinewallet.app.exceptions.LoginException;
import com.onlinewallet.app.exceptions.WalletException;
import com.onlinewallet.app.model.DepositDTO;
import com.onlinewallet.app.model.TransferDTO;
import com.onlinewallet.app.model.UserDTO;
import com.onlinewallet.app.model.UserWallet;
import com.onlinewallet.app.model.WalletDTO;
import com.onlinewallet.app.service.WalletService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:3000/")
@RequestMapping("api")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@GetMapping("wallet/sort")
	
	public List<UserWallet> findAllWalletsSortByBalance(){
		
		return this.walletService.sortWalltsByBalance();
	}

	
	@GetMapping("/")
	public String greet() {
		return "Hello Wallet!";
	}

	@PostMapping("wallet")
	public UserWallet createWallet(@Valid @RequestBody WalletDTO wallet) throws WalletException {

		return this.walletService.createWallet(wallet);
	}

	@GetMapping("wallets")
	public List<UserWallet> getAllWallets(@CookieValue("jwt") String jwt) throws WalletException {
		
		if(jwt == null)
			throw new WalletException("Unauthenticated !");
		try {
		Claims claim = Jwts.parser().setSigningKey("Secret123").parseClaimsJws(jwt).getBody();
		//String email = claim.getIssuer();
		
		}
		catch(ExpiredJwtException e) {
			throw new WalletException("JWT got expired please try re loging. ");
		}
		catch(SignatureException e) {
			throw new WalletException("JWT SignatureException. ");
		}
		 
		catch (Exception e) {
			throw new WalletException("Unauthenticated !");
		}
		// User is valid , process the request .
		return this.walletService.getAllWallets();
	}

	@PutMapping("wallet")
	public UserWallet updateWallet(@RequestBody UserWallet wallet) {
		return this.walletService.updateWallet(wallet);
	}

	@PatchMapping("wallet")
	public UserWallet addFunds(@RequestBody DepositDTO deposit) throws WalletException {
		return this.walletService.depositFunds(deposit);
	}

	@PostMapping("transfer")
	public String transferFunds(@RequestBody TransferDTO transferDTO) throws WalletException {
		return this.walletService.fundTransfer(transferDTO);
	}

	@PostMapping("login")
	public String login(@RequestBody UserDTO user, HttpServletResponse response) throws LoginException {

		Cookie cookie = new Cookie("jwt", this.walletService.login(user));

		response.addCookie(cookie);
		return "Login Success !";
	}
	@PostMapping("logout")
	public String logout(HttpServletResponse response) {
		Cookie cookie = new Cookie("jwt", "");
		response.addCookie(cookie);
		return "Logout Success.";
	}
	

}
