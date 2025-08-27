import java.util.ArrayList;

class Bank {

    private ArrayList<Long> accBalance;
    private int sz;
    public Bank(long[] balance) {
        accBalance = new ArrayList<>();

        for(int i=0;i<balance.length;i++) {
            accBalance.add(balance[i]);
        }
        sz = balance.length;
    }

    public boolean transfer(int account1, int account2, long money) {
        if(account1>=1 && account1<=sz && account2>=1 && account2<=sz) {
            if(accBalance.get(account1-1) >= money) {
                accBalance.set(account1-1, accBalance.get(account1-1) - money);
                accBalance.set(account2-1, accBalance.get(account2-1) + money);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean deposit(int account, long money) {
        if(account>=1 && account<=sz) {
            accBalance.set(account-1, accBalance.get(account-1) + money);
            return true;
        }
        return false;
    }

    public boolean withdraw(int account, long money) {
        if(account>=1 && account<=sz && accBalance.get(account-1) >= money) {
            accBalance.set(account-1, accBalance.get(account-1) - money);
            return true;
        }
        return false;
    }
}

/**
 * Your Bank object will be instantiated and called as such:
 * Bank obj = new Bank(balance);
 * boolean param_1 = obj.transfer(account1,account2,money);
 * boolean param_2 = obj.deposit(account,money);
 * boolean param_3 = obj.withdraw(account,money);
 */