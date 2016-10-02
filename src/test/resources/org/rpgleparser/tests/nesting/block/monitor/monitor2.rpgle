        $count = 0;                                                  
        monitor;                                                     
            callp ExcCmd(%Trim(@CmdStr) :                            
                      %Len(%Trim(@CmdStr)));                         
        on-error;                                                    
          select;                                                    
            when errmsgID = 'CPFA09E';                               
               callp ExcCmd('DLYJOB DLY(3)':13);                     
               $count = $count + 1;                                  
               iter;                                                 
            other;                                                   
               leave;                                                
          endsl;                                                     
        endmon;     