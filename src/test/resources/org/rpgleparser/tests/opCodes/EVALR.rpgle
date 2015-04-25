     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   EVALR     x = y      
     C                   EvalR     result = %subst(fld01:1:5)
     C                   evalr(m)  %subst(name:1:12) = 'Richard'
     
      /Free
          EVALR name = 'XXXXXX';
          evalr result = %subst(fld01:1:5);
          evalr(m) %subst(name:1:12) = 'Richard';
          
      /end-free
      *
     C                   SETON                                        LR  
