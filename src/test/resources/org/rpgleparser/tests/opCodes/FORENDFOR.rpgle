     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   FOR       X = 1 to 10 
     C                   ENDFOR   
     C                   For       X1 = 1 by 1 to 10
     C                   endfor
     C                   For       X1= 10 downto 1
     C                   Endfor
     C                   FOR       X= 10 by 1 downto 1
     C                   endfor
     C                   for       X1= %len(str) by 1 downto 1
     C                   endfor
                           
      /Free
          for i = 1 to n;
          endfor;
          FOR i = %len(field) downto 1;
          endfor;
          for i= 1 by incr to %len(fld);
          endfor;
        
      /end-free
      *
     C                   SETON                                        LR  