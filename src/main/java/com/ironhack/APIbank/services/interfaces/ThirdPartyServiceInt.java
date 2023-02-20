package com.ironhack.APIbank.services.interfaces;

import com.ironhack.APIbank.controllers.dto.users.ThirdPartyDTO;
import com.ironhack.APIbank.models.accounts.Account;

public interface ThirdPartyServiceInt {
    Account transferMoney(String hashedKey, ThirdPartyDTO thirdPartyDTO);
}
