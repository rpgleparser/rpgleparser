     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
      *
     C                   DOW       *IN01 
     C                   ENDDO   
     C                   DOW       *IN01 = *OFF
     C                   ENDDO 
     C                   DOW(m)    *IN01 = *OFF
     C                   ENDDO  
     C                   dow       *IN01 = *OFF or (fld2>fld3)
     C                   end                       
      /Free
          DOW *inkc;
          ENDDO;
          dow *in01 or (fld2 > fld3);
          enddo;
          Dow *inkc = *off;
          Enddo;
          dow(r) *inkc = *off;
          Enddo;


      /end-free
      *
     C                   SETON                                        LR  
