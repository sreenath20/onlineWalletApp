package com.onlinewallet.app.repo;




import org.springframework.data.mongodb.repository.MongoRepository;

import com.onlinewallet.app.model.UserWallet;

public interface WalletRepo extends MongoRepository<UserWallet, String>{
	
	UserWallet findByEmail(String email);
		

}
