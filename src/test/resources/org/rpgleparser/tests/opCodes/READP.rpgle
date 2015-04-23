     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   READP     MYFILE
     C                   readp     myfile                               9998                               
     C                   readp(e)  myfile
     C                   READP     myfile                               99      
     C                   readp(n)  myfile        myds
     C                   readp     myfile        myds                   9899
      /Free
          READP myfile;
          READP(e) myfile;
          READP(n) myfile myds;
          readp(e) myfile myds;
      /end-free
      *
     C                   SETON                                        LR  
