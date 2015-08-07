      /FREE
             If Pos > 0; // Now try to find the end
                Pos2 = %Scan('%>':MsgLine:Pos+2);
             endif;
          //**********
          // Test Record Keys
          If not *In04                                                        //
           and not *In20;
          EndIf;                                                              //
       DoW not *InLR;                                                         //
       enddo; 
      /END-FREE                                                                     