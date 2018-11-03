     H/COPY QILEGEN,£INIZH
      *---------------------------------------------------------------
     I/COPY QILEGEN,£TABB£1DS
     I/COPY QILEGEN,£PDS
      /COPY QILEGEN,£JAX_PD1
      * Buffer ricevuto sulla socket
     D BUFFER          S          30000
      * Lunghezza buffer ricevuto
     D BUFLEN          S              5  0
     D $XML            S          30000    VARYING
     D $RIGA           S          30000    VARYING
     D $TARGA          S             50    VARYING
      *---------------------------------------------------------------
     D                 DS
     D$$SVAR                       1050    DIM(200)
     D $$SVARCD                      50    OVERLAY($$SVAR:1)                    Nome
     D $$SVARVA                    1000    OVERLAY($$SVAR:*NEXT)                Valore
      *---------------------------------------------------------------
     D $$FUNZ          S             10
     D $$METO          S             10
      *---------------------------------------------------------------
     D§§SVAR           S                   LIKE($$SVAR) DIM(%ELEM($$SVAR))
     D$$SVARSK         S                   LIKE($$SVAR) DIM(%ELEM($$SVAR))
      *---------------------------------------------------------------
     D §§FUNZ          S             10
     D §§METO          S             10
     D ADDRSK          S             15
     D $X              S              5  0
     D $R              S              5  0
      *---------------------------------------------------------------
     D* M A I N
      *---------------------------------------------------------------
      *
     C                   EVAL      $$SVAR=$$SVARSK
      *
1    C                   SELECT
1x   C                   WHEN      $$FUNZ='INI'
     C                   EXSR      RINI
1e   C                   ENDSL
      *
     C                   SETON                                        RT
      *---------------------------------------------------------------
     C/COPY QILEGEN,£INZSR
      *--------------------------------------------------------------*
    RD*    Funzione INI
      *--------------------------------------------------------------*
     C     RINI          BEGSR
      *
     C                   CLEAR                   ADDRSK
1    C                   FOR       $X=1 TO %ELEM($$SVARCD)
      * Leggo indirizzo su cui mettermi in ascolto della socket
2    C                   IF        $$SVARCD($X)='SOCKET'
     C                   EVAL      ADDRSK=$$SVARVA($X)
     C                   LEAVE
2e   C                   ENDIF
1e   C                   ENDFOR
      *
1    C                   DO        *HIVAL
      * Resto in ascolto sulla socket
     C                   CLEAR                   BUFFER
     C                   CLEAR                   BUFLEN
      *
     C                   CALL      'JD_RCVSCK'
     C                   PARM                    ADDRSK
     C                   PARM                    BUFFER
     C                   PARM                    BUFLEN
      * Se ho ricevuto qualcosa
2    C                   IF        BUFLEN>0
     C                   EVAL      $XML=%SUBST(BUFFER:1:BUFLEN)
      * Parso l'xml ricevuto cercando <Auto>
     C                   EVAL      $RIGA=P_RxELE('Auto':'POS':01:$XML)          COSTANTE
3    C                   IF        $RIGA<>''
     C                   EVAL      $TARGA=P_RxVAL($RIGA:'Targa')                COSTANTE
4    C                   IF        $TARGA<>''
      * Trovo il primo elemento vuoto della schiera
     C                   EVAL      $R=%LOOKUP('':$$SVARCD)
5    C                   IF        $R>0
     C                   EVAL      $$SVARCD($R)='Targa'                         COSTANTE
     C                   EVAL      $$SVARVA($R)=$TARGA
     C                   EVAL      §§SVAR=$$SVAR
      * Chiamo un programma a cui restituire il risultato (la targa)
     C                   CALL      'JP_RES001'
     C                   PARM      'RET'         §§FUNZ
     C                   PARM      *BLANKS       §§METO
     C                   PARM                    §§SVAR
5e   C                   ENDIF
4e   C                   ENDIF
3e   C                   ENDIF
3e   C                   ENDIF
      *
2e   C                   ENDDO
      *
     C                   ENDSR
      *---------------------------------------------------------------
    RD* ROUTINE INIZIALE
      *--------------------------------------------------------------*
     C     £INIZI        BEGSR
      *
     C     *ENTRY        PLIST
     C                   PARM                    $$FUNZ
     C                   PARM                    $$METO
     C                   PARM                    $$SVARSK
      *
     C                   ENDSR
      *--------------------------------------------------------------*
      /COPY QILEGEN,£JAX_PC1
