     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *        
     C                   IF        A> 10 AND *IN20
     C                   ELSE
     C                   ENDIF
     C                   IF        A> 10 AND y=5
     C                   ELSEIF    B < 5
     C                   ELSE 
     C                   ENDIF
     C                   if(M)     A> 10 AND *IN(20)
     C                   elseif    B < 5
     C                   else
     C                   endif
      /Free
          if a>10 and *in(30);
          endif;
          IF B < %len(str) or c = 5;
          elseif keypress = exitkey;
          else;
          endif;
          if(m) a> 5;
          elseif(r) b= 4;
          endif;
          
      /end-free
      *
     C                   SETON                                        LR  
