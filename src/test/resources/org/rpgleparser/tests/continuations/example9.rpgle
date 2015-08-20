      /FREE       
       Begsr selectFunctionToCheckForEmail;
         
RLCIS            xxEmailAddress = FindEmailAddressFromBuyerNumber(
RLCIS             PPDATA.NS_CMPN:xxDataValue);
                 xxEmailAddress = FindEmailAddressFromCustomerNumber
                                  (xxDataValue);
                 xxEmailAddress = FindEmailAddressFromRandomFile(xxDataValue);

                 xxEmailAddress = FindEmailAddressFromVendorNumber
                                  (xxDataValue:ppData);

RLCIS                 xxEmailAddress = FindEmailAddressFromBuyerNumber(
RLCIS                                  PPDATA.NS_CMPN:PPDATA.NS_RESP);
       EndSr;
      /END-FREE