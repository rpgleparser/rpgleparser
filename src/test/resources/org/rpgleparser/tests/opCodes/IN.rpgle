     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   IN        MYDS
     C                   in        *DTAARA                              99 
     C                   IN(e)     MYDS
     C      *LOCK        in        *DTAARA                              99      
      /Free
          in myds;
          in *dtaara;
          in(e) *dtaara;
          in *lock myara;
          in(e) *lock myara;
      /end-free
      *
     C                   SETON                                        LR  
