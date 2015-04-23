     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   READE     MYFILE
     C                   reade     myfile                               9998                               
     C                   reade(e)  myfile
     C                   READE     myfile                               99                               
     C                   READE(N)  myfile                                 98
     C                   reade     myfile        myds
     C                   reade     myfile        myds                   9899
     C     'KEY'         reade     myfile                               9998                               
     C     KEY           reade(e)  myfile
     C     KEY           READE     myfile                               99                               
     C     'KEY'         READE(N)  myfile                                 98
     C     KEY           reade     myfile        myds
     C     KEY           reade     myfile        myds                   9899
      /Free
          READE *key myfile;
          READE(e) *KEY myfile;
          reade(n) ('abc':'def') myfile;
          READE 'KEY' myfile myds;
          reade(e) 'KEY' myfile myds;
      /end-free
      *
     C                   SETON                                        LR  
