package com.bfwg;

import com.bfwg.model.Currency;
import com.bfwg.model.PrimaryAccount;
import com.bfwg.model.SavingAccount;
import com.bfwg.model.Transaction;
import com.bfwg.repository.TransactionRepo;
import com.bfwg.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class Application implements ApplicationRunner {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionRepo transactionRepo;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {

		PrimaryAccount primaryAccount = accountService.createPrimaryAccount(Currency.EUR, "user");
		SavingAccount savingAccount = accountService.createSavingAccount("user");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("24-09-2017 22:01:02").getTime()), "ok", "init repo", 230, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("01-11-2017 14:24:11").getTime()), "ok", "init repo",230, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("24-11-2017 21:30:02").getTime()), "ok", "init repo",630, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("02-12-2017 02:01:02").getTime()), "ok", "init repo",900, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("11-12-2017 18:45:45").getTime()), "ok", "init repo",200, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("11-14-2017 19:33:45").getTime()), "ok", "init repo",80, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("11-18-2017 18:19:45").getTime()), "ok", "init repo",230, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("03-02-2018 20:03:44").getTime()), "ok", "init repo",230, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("09-02-2018 15:11:23").getTime()), "ok", "init repo",20, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("23-02-2018 23:01:16").getTime()), "ok", "init repo",72.3, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("04-02-2018 08:17:12").getTime()), "ok", "init repo",90, primaryAccount));
		transactionRepo.save(new Transaction(new Timestamp(simpleDateFormat.parse("08-02-2018 10:45:14").getTime()), "ok", "init repo",1370, primaryAccount));

		primaryAccount.setAccountBalance(new BigDecimal("2360.8"));
		accountService.saveAccount(primaryAccount);

	}

}


