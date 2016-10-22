     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   POST      MYFILE
     C                   post      myfile                               99                               
     C                   POST(e)   myfile
     C                   post      myfile                               99                               
     C     'DEV'         POST      myfile
     C                   post      myfile        myds
     C     'DEV'         post      myfile        myds
     C     DEVFLD        post      myfile        myds
      /Free
          POST myfile;
          POST(e) myfile;
          post 'dev' myfile;
          post devfld myfile;
          
      /end-free
      *
     C                   SETON                                        LR  
