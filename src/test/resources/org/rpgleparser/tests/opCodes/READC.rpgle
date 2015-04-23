     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   READC     MYFILE
     C                   readc     myfile                               9998                               
     C                   readc(e)  myfile
     C                   READC     myfile                               99      
     C                   readc     myfile        myds
     C                   readc     myfile        myds                   9899
      /Free
          READC myfile;
          READC(e) myfile;
          READC myfile myds;
          readc(e) myfile myds;
      /end-free
      *
     C                   SETON                                        LR  
