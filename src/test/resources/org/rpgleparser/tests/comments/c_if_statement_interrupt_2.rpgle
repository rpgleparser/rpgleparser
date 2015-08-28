GB02
GB02 C                   If        Not OpenSeg
GB02
GB02 ¿* Capture instances where the final ..
GB02
GB02 C                   If        (Not LeftOrigCntry and
GB02 C                              Dept_Country <> Dest_Country)  or
GB02
GB02 ¿* .. or where other sectors have ..
 
GB02 C                             (%Abs(orgDeptWgt - wrkDestWgt) >=
GB02 C                              %Abs(orgDeptWgt - savDestWgt) and
GB02 C                              Dept_Country <> Dest_Country and
GB02 C                              Sav_StayF <= 240)
GB02
GB02 C                   Eval      Sav_Dest   = Isdsap
GB02 C                   Eval      savDestWgt = wrkDestWgt
GB02
GB02 C                   EndIf
GB02
GB02 C                   EndIf