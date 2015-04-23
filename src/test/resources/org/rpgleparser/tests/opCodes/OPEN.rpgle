     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   OPEN      MYFILE
     C                   open      myfile                               99                               
     C                   open(e)   myfile
      /Free
          OPEN  myfile;
          open filename;
          open(e) myfile;
          
      /end-free
      *
     C                   SETON                                        LR  
