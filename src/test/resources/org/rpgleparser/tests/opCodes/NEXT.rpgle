     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C     'QPADEV0'     NEXT      MYFILE
     C     'DEV'         next                                           99                               
     C     FLD           next(e)   myfile
     C     fld           next(e)   
      /Free
          NEXT 'dev1' myfile;
          Next 'qpadev0' filename;
          next(e) devvar myfile;
          next devvar file2;
          
      /end-free
      *
     C                   SETON                                        LR  
