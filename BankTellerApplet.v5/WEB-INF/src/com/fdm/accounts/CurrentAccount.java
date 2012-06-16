package com.fdm.accounts;

import com.fdm.exceptions.InvalidAccountWithdrawalException;
import com.fdm.exceptions.NegativeNumberException;
import com.fdm.helper.ExceptionHelper;
import com.fdm.helper.MathHelper;

public class CurrentAccount extends Account {

	private static final long serialVersionUID = -7272040673470947727L;
	
	public void makeWithdrawal(double withdrawal) throws NegativeNumberException, InvalidAccountWithdrawalException {
		
		double withdrawalAmount = MathHelper.roundToTwoDecimalPlaces(withdrawal);
		if (ExceptionHelper.isValidNumber(withdrawal)) {
			if (balance+overdraftLimit >= withdrawalAmount)
				setBalance(balance-withdrawalAmount);
			else
				throw new InvalidAccountWithdrawalException();
		}
	}

	public void makeDeposit(double deposit) throws NegativeNumberException {
		
		double depositAmount = MathHelper.roundToTwoDecimalPlaces(deposit);
		if (ExceptionHelper.isValidNumber(depositAmount)) {
			setBalance(balance+depositAmount);
		}
	}

}
