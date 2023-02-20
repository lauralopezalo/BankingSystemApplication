package com.ironhack.APIbank.controllers.interfaces;


import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.models.accounts.Account;

public interface AccountHolderControllerInt {

    Money getBalanceById(Long id);

    Account transferMoney(TransferenceDTO transferenceDTO);
}
