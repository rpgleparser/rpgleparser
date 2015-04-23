     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C     FLD1          MULT      FLD2         FLD3
     C     FLD1          MULT(H)   FLD2(x)      FLD3
     C     FLD1(y)       mult      FLD2(x)      FLD3
     C     FLD1(y)       MULT      FLD2(x)      FLD3(z)
     C                   Mult(h)   FLD2         FLD3
     C                   Mult      100          FLD3
     C     10            Mult      100          FLD3
     C     10            Mult      100          FLD3               5 0
      *
     C                   SETON                                        LR  
