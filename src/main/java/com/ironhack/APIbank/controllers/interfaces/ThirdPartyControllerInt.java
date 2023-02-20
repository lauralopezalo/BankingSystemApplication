package com.ironhack.APIbank.controllers.interfaces;

import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.controllers.dto.users.ThirdPartyDTO;
import com.ironhack.APIbank.models.accounts.Account;
import com.ironhack.APIbank.models.users.ThirdParty;


public interface ThirdPartyControllerInt {
    Account transferThirdParty(String thirdPartyKey, ThirdPartyDTO thirdPartyDTO);
}
