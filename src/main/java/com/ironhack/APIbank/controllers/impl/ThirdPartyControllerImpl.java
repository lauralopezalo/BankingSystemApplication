package com.ironhack.APIbank.controllers.impl;

import com.ironhack.APIbank.controllers.dto.accounts.TransferenceDTO;
import com.ironhack.APIbank.controllers.dto.users.ThirdPartyDTO;
import com.ironhack.APIbank.controllers.interfaces.ThirdPartyControllerInt;
import com.ironhack.APIbank.models.accounts.Account;;
import com.ironhack.APIbank.services.interfaces.ThirdPartyServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/third-party")
public class ThirdPartyControllerImpl implements ThirdPartyControllerInt {

    @Autowired
    ThirdPartyServiceInt thirdPartyServiceInt;
    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.OK)
    public Account transferThirdParty(@RequestHeader("hashedKey") String hashedKey, @RequestBody ThirdPartyDTO thirdPartyDTO){
        return thirdPartyServiceInt.transferMoney(hashedKey, thirdPartyDTO);
    }

}
