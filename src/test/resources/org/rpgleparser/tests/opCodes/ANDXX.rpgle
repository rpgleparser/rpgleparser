     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C     XX            IFEQ      'A'               
     C     YY            ANDEQ     'B'               
     C                   endif       
      *
     C     XX            ifeq      'A'               
     C     YY            ANDGT     'B'  
     C     ZZ            ANDLT     'C'   
     C     *IN50         andeq     *ON           
     C                   endif   
      *
     C     XX            ifeq      'A'               
     C     YY            ANDGE     'B'  
     C     ZZ            ANDLT     'C'   
     C     *IN50         andEq     *ON           
     C                   endif   
     C                   SETON                                        LR  
