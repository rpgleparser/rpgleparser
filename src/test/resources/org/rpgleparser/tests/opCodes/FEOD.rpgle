     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   FEOD      MYFILE
     C                   feod      myfile                               99                               
     C                   Feod(e)   myfile
     C                   Feod(n)   myfile
      /Free
          FEOD myfile;
          Feod myfile;
          feod(e) myfile;
          feod(n) myfile;
          
      /end-free
      *
     C                   SETON                                        LR  
