     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   UNLOCK    MYFILE
     C                   unlock    myfile                               99                               
     C                   unlock(e) myfile
     C                   unlock    *DTAARA
      /Free
          UNLOCK  myfile;
          unlock filename;
          unlock(e) myfile;
          Unlock *Dtaara;
      /end-free
      *
     C                   SETON                                        LR  
