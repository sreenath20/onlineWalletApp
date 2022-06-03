package com.onlinewallet.app;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.onlinewallet.app.exceptions.WalletException;
import com.onlinewallet.app.model.DepositDTO;
import com.onlinewallet.app.model.UserWallet;
import com.onlinewallet.app.model.WalletDTO;
import com.onlinewallet.app.service.WalletService;
import com.onlinewallet.app.service.WalletServiceImpl;

@SpringBootTest
class OnlinewalletApplicationTests {

	@Autowired
	private WalletService walletService;

	@Test
	 void createWalletTest() {

		WalletDTO wallet = new WalletDTO("tn1", 1000.0, "te@i.com", "tp1");
		UserWallet newWallet = null;
		try {
			newWallet = this.walletService.createWallet(wallet);
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		assertNotNull(newWallet);
		assertNotEquals(null, newWallet.getId());

	}

	@Test
	 void createWalletTestForDuplicateEmail() {

		WalletDTO wallet = new WalletDTO("tn1", 1000.0, "te@i.com", "tp1");
		UserWallet newWallet = null;

		assertThrows(WalletException.class, () -> this.walletService.createWallet(wallet));

	}

	@Test
	 void getAllWalletsTest() {

		assertNotEquals(0, this.walletService.getAllWallets().size());

	}

	@Test
	 void depositFundsTest() {

		try {
			assertNotNull(this.walletService.depositFunds(new DepositDTO("6291c7aa3e24436ad1535e3c", 100.0)));
		} catch (WalletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	 void depositFundsThrowsExceptionTest() {

		
			assertThrows(WalletException.class,()->this.walletService.depositFunds(new DepositDTO("777", 100.0)));
		

	}

}
