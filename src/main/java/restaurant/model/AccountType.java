package restaurant.model;

import java.io.Serializable;

public class AccountType implements Serializable {
	private static final long serialVersionUID = 1L;
	private int accountTypeId;
	private String description;
	
	public AccountType() {}

	public AccountType(String description) {
		this.description = description;
	}

	public AccountType(int accountTypeId, String description) {
		this.accountTypeId = accountTypeId;
		this.description = description;
	}

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AccountType [accountTypeId=" + accountTypeId + ","
				+ " description=" + description + "]";
	}

}
