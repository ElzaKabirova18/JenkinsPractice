package day5_RecapPojo;

import org.junit.Test;
import utilities.APIRunner;

public class BankAccountWithApiRunner {
//1345

    @Test
    public void test1_Single_BankAccount() {
        //https://backend.cashwise.us/api/myaccount/bankaccount/1345
        String path = "/api/myaccount/bankaccount/1345";

        APIRunner.runGET(path);
        String bankAccountName = APIRunner.getCustomResponse().getBank_account_name();
        int money= (int) APIRunner.getCustomResponse().getBalance();
        System.out.println("bank account name: "+ bankAccountName);
        System.out.println("balance: "+ money);
    }
}
