     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   EVAL-CORR x = y      
     C                   Eval-corr fld01 = fld02
     
      /Free
          EVAL-CORR x = y;
          eval-corr fldds = fld2ds;
          Eval-corr(h) ds01 = ds02;
          
      /end-free
      *
     C                   SETON                                        LR  
