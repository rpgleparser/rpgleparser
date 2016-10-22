     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   EVAL      x = y      
     C                   Eval      result = fld1 + (fld2 - fld3)
     C                   eval(H)   result = fld1 + %len(fld4)
     
      /Free
          EVAL x = y;
          eval result = fld1 + (fld2 - fld3);
          eval(h) result = fld1 + %len(fld4);
          
          
          // Not dealing with following case yet
          //arr(*) = field2 * filed3;
          ptr = %addr(foeld);
          
          fld3 += 5;
          fld4 -=6;
          fld7 /=10;
          fld8 *=(20+30);
          fld9 **=(6 + %len(fleid));
          cust(custom).account(accum).balance -= 24;
          
      /end-free
      *
     C                   SETON                                        LR  
