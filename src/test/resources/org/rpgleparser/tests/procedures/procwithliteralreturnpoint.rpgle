      /FREE
              // *PSSR - Error Routine
       BegSr *Pssr;
 
           // Dump program and terminate process
           If Wrk_1stErr <> *On;
             Wrk_1stErr = *On;
             Dump;
             PrmPssrErr = 'Y';    // Force Exit now
             *InLr = *On;
           EndIf;
 
       EndSr '*CANCL';
      /END-FREE                                                                          
