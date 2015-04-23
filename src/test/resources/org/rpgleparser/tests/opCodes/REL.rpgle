     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C     'DEV'         REL       MYFILE
     C     DEVFL         rel       myfile                               99                               
     C     'DEV'         rel(e)    myfile
      /Free
          REL 'DEV' myfile;
          rel devfld myfile;
          rel(e) devfld myfile;
          REL(e) 'DEV'  myfile;
          
      /end-free
      *
     C                   SETON                                        LR  
