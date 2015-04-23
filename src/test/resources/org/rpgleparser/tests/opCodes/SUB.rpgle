     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C     FLD1          SUB       FLD2         FLD3
     C     FLD1          SUB(H)    FLD2(x)      FLD3
     C     FLD1(y)       SUB       FLD2(x)      FLD3
     C     FLD1(y)       sub       FLD2(x)      FLD3(z)
     C                   sub(h)    FLD2         FLD3
     C                   SUB       100          FLD3
     C     10            Sub       100          FLD3
     C     10            Sub       100          FLD3               5 0
      *
     C                   SETON                                        LR  
