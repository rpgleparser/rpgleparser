     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   READPE    MYFILE
     C                   readpe    myfile                               9998                               
     C                   readpe(e) myfile
     C                   READPE    myfile                               99                               
     C                   READPE(N) myfile                                 98
     C                   readpe    myfile        myds
     C                   readpe    myfile        myds                   9899
     C     'KEY'         readpe    myfile                               9998                               
     C     KEY           readpe(e) myfile
     C     KEY           READPE    myfile                               99                               
     C     'KEY'         READPE(N) myfile                                 98
     C     KEY           readpe    myfile        myds
     C     KEY           readpe    myfile        myds                   9899
      /Free
          READPE *key myfile;
          READPE(e) *KEY myfile;
          readpe(n) ('abc':'def') myfile;
          READPE 'KEY' myfile myds;
          readpe(e) 'KEY' myfile myds;
      /end-free
      *
     C                   SETON                                        LR  
