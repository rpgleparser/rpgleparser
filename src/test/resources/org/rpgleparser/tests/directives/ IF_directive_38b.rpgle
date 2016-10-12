      /Define Copy_Procedure_Interface 
      /If Not Defined( Copy_Procedure_Interface )
     D RtvAcgCde       PR                  ExtPgm('RTVACGCDE')
      /Else
     D RtvAcgCde       PI
      /EndIf             
     D  P@AcgCde                     15A
     D  P@LocType                     1A
