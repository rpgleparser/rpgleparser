     H/COPY QILEGEN,£INIZH
      *---------------------------------------------------------------
     I/COPY QILEGEN,£TABB£1DS
     I/COPY QILEGEN,£PDS
      *---------------------------------------------------------------
     D                 DS
     D $$SVAR                      1050    DIM(200)
     D  $$SVARCD                     50    OVERLAY($$SVAR:1)                    Nome
     D  $$SVARVA                   1000    OVERLAY($$SVAR:*NEXT)                Valore
      *---------------------------------------------------------------
      * ENTRY
      * . funzione
     D U$FUNZ          S             10
      * . metodo
     D U$METO          S             10
      * . schiera variabili
     D U$SVARSK        S                   LIKE($$SVAR) DIM(%ELEM($$SVAR))
      *---------------------------------------------------------------
      * . Url
     D $$URL           S           1000
     D $X              S              3  0
      *---------------------------------------------------------------
      * Richiamo Url
      * . funzione
     D §§FUNZ          S             10
      * . metodo
     D §§METO          S             10
      *---------------------------------------------------------------
     D* M A I N
      *---------------------------------------------------------------
      *
      * Impostazioni iniziali
     C                   EXSR      IMP0
      * Funzioni / Metodo
1    C                   SELECT
1x   C                   WHEN      U$FUNZ='EXE'
     C                   EXSR      FEXE
1e   C                   ENDSL
      * Impostazioni finali
     C                   EXSR      FIN0
      * Fine
     C                   SETON                                        LR
      *---------------------------------------------------------------
     C/COPY QILEGEN,£INZSR
      *---------------------------------------------------------------
    RD* ROUTINE INIZIALE
      *--------------------------------------------------------------*
     C     £INIZI        BEGSR
      *
     C     *ENTRY        PLIST
     C                   PARM                    U$FUNZ
     C                   PARM                    U$METO
     C                   PARM                    U$SVARSK
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Impostazioni iniziali
      *--------------------------------------------------------------*
     C     IMP0          BEGSR
      *
     C                   EVAL      $$SVAR=U$SVARSK
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Impostazioni finali
      *--------------------------------------------------------------*
     C     FIN0          BEGSR
      *
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Inizializzazione
      *--------------------------------------------------------------*
     C     FEXE          BEGSR
      *
      * Carica url (Assumo sia sempre in prima posizione perchè è la variabile da sostituire)
     C                   EVAL      $$URL=$$SVARVA(1)
      * Sostituisco tutte le altre variabili nell'url
     C     2             DO        *HIVAL        $X
     C                   IF        $$SVARCD($X)=*BLANKS
     C                   LEAVE
     C                   ENDIF
     C                   EVAL      $$URL=%XLATE($$SVARCD($X):
     C                             $$SVARVA($X):
     C                             $$URL)
     C                   ENDDO
      * Richiamo url
     C                   CLEAR                   $$SVAR
     C                   EVAL      $$SVARCD(01)='Url'                           COSTANTE
     C                   EVAL      $$SVARVA(01)=$$URL
     C                   CALL      'URL'
     C                   PARM                    §§FUNZ
     C                   PARM                    §§METO
     C                   PARM                    $$SVAR
      *
     C                   ENDSR
