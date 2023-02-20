package com.ironhack.APIbank.services.interfaces;

import com.ironhack.APIbank.embeddable.Money;
import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.models.accounts.Account;

public interface AccountHolderServiceInt {
    Money getAccountBalance(Long accountId);
    Account transfer(TransferenceDTO transferenceDTO);

}
