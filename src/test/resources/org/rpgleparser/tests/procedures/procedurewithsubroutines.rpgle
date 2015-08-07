     P ReplaceDataInEmailBody...
     P                 B
     D ReplaceDataInEmailBody...
     D                 PI
     D  ppData                             LIKEDS(xpNSDataElem) CONST
     Dpos              S              3  0 Inz(0)
     Dpos2             S              3  0 Inz(0)
     DDataBefore       S            100
     DDataAfter        S            100
     DMsgLine          S            200
     DReplaceField     S             10
      /FREE
       SetLL (useMasterCode) NSGNMSDL0;
       DoU %EOF(NSGNMSDL0);
          Exsr ScanForReplacement;
          xxEmBody = %Trim(xxEmBody) + %Trim(MsgLine) + ' ' + CRLF;
       EndDo;
       // ************************************************************
       BegSr ScanForReplacement;
          MsgLine = %trim(NSGMDTA);
          Pos = %Scan('<%':MsgLine:1);
       EndSr;
       // ************************************************************
       BegSr FindReplacementMatch;
          Select;
             When ReplaceField = 'ITMN'; 
                Msgline = %Trim(DataBefore) + ' ' +
                          %Trim(ppData.NS_ITMN) + ' ' +
                          %Trim(DataAfter);
             Other;
                Msgline = %Trim(DataBefore) + ' UnKnown ' +
                          %Trim(DataAfter);
          EndSl;
       EndSr;
      /END-FREE
 
     P ReplaceDataInEmailBody...
     P                 E                                                                             