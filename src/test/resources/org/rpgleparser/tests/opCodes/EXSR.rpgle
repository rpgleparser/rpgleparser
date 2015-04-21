     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   EXSR      SUB01
     C                   Exsr      *PSSR                                
     C                   exfmt     *inzsr
     
      /Free
          EXSR SUB01;
          exsr *pssr;
          exsr *inzsr;
          
      /end-free
      *
     C                   SETON                                        LR  
