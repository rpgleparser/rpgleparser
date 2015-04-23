     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   WRITE     MYFILE
     C                   write     myfile                               9998                               
     C                   write(e)  myfile
     C                   WRITE     myfile                               99                               
     C                   write     myfile        myds
     C                   write     myfile        myds                   9899
      /Free
          WRITE myfile;
          WRITE(e) myfile;
          write myfile myds;
          write(e) myfile myds;
      /end-free
      *
     C                   SETON                                        LR  
