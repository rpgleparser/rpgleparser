     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   EXFMT     FMT01
     C                   EXFMT     FMT01                                99
     C                   exfmt     FMT01         RECDS
     C                   EXFMT(e)  FMT01
     
      /Free
          EXFMT FMT01;
          exfmt(e) FMT01;
          exfmt fmt01 recds;
          
      /end-free
      *
     C                   SETON                                        LR  
