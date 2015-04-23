     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   READ      MYFILE
     C                   read      myfile                               9998                               
     C                   read(e)   myfile
     C                   READ      myfile                               99                               
     C                   READ(N)   myfile                                 98
     C                   read      myfile        myds
     C                   read      myfile        myds                   9899
      /Free
          READ myfile;
          READ(e) myfile;
          read(n) myfile;
          READ myfile myds;
          read(e) myfile myds;
      /end-free
      *
     C                   SETON                                        LR  
