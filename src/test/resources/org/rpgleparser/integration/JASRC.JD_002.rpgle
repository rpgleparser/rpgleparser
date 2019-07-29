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
      * PARM LISTEN_FLD (ascoltatore cartella)
      * . cartella da monitorare
     D §§FLD           S           1000
      * . nome oggetto ineterssato dall'evento
     D §§NAM           S           1000
      * . tipo oggetto ineterssato dall'evento (FILE/FOLDER)
     D §§TIP           S             10
      * . tipo evento (come variabile Mode)
     D §§OPE           S             10
      *---------------------------------------------------------------
      * PARM NOTIFY_EVE (notifica evento)
      * . funzione
     D §§FUNZ          S             10
      * . metodo
     D §§METO          S             10
      * . schiera variabili
     D §§SVAR          S                   LIKE($$SVAR) DIM(%ELEM($$SVAR))
      *---------------------------------------------------------------
      * Variabile Folder (input): nome della cartella
     D $$FLD           S           1000
      * Variabile Mode (input): Eventi da monitorare
      * . *ADD Aggiunta di file o cartelle
      * . *CHG Modifica di file
      * . *DEL cancellazione file o cartella
     D $$MOD           S             10
      * Variabile Filter (input): Filtro da applicare
      * . Per semplicità per ora posso impostare solo un filtro e solo del tipo *.estensione
     D $$FLT           S           1000
      *---------------------------------------------------------------
      * Variabili di appoggio
     D $$EST_FLT       S             10
     D OK              S              1N
     D $X              S              3  0
      *---------------------------------------------------------------
     D* M A I N
      *---------------------------------------------------------------
      *
      * Impostazioni iniziali
     C                   EXSR      IMP0
      * Funzioni / Metodo
1    C                   SELECT
1x   C                   WHEN      U$FUNZ='INZ'
     C                   EXSR      FINZ
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
     C     FINZ          BEGSR
      *
      * Carica variabili
     C                   EXSR      CARVAR_INZ
      * Mi metto in ascolto sulla cartella $$FLD
     C                   DO        *HIVAL
     C                   EVAL      §§FLD=$$FLD
     C                   CALL      'LISTEN_FLD'
     C                   PARM                    §§FLD
     C                   PARM                    §§NAM
     C                   PARM                    §§TIP
     C                   PARM                    §§OPE
      * Verifico se l'operazione è tra quelle monitorate
     C                   EXSR      CHKOPE
     C                   IF        NOT(OK)
     C                   ITER
     C                   ENDIF
      * Verifico se il file corrisponde al filtro
     C                   EXSR      CHKFLT
     C                   IF        NOT(OK)
     C                   ITER
     C                   ENDIF
      * Costruisco le variabili per la notifica dell'evento
     C                   EXSR      COSVAR_EVE
      * Notifico l'evento
     C                   CALL      'NOTIFY_EVE'
     C                   PARM                    §§FUNZ
     C                   PARM                    §§METO
     C                   PARM                    §§SVAR
     C                   ENDDO
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Verifico se l'operazione è tra quelle monitorate
      *--------------------------------------------------------------*
     C     CHKOPE        BEGSR
      *
     C                   IF        $$MOD=*BLANKS OR
     C                             %SCAN(%TRIM(§§OPE):$$MOD)>0
     C                   EVAL      OK=*ON
     C                   ELSE
     C                   EVAL      OK=*OFF
     C                   ENDIF
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Verifico se il file corrisponde al filtro
      *--------------------------------------------------------------*
     C     CHKFLT        BEGSR
      *
     C                   SELECT
      * Se non ho impostato un filtro, automaticamente va bene
     C                   WHEN      $$FLT=*BLANKS
     C                   EVAL      OK=*ON
      * Se ho impostato un filtro e l'evento riguarda una cartella, automaticamnete non va bene
     C                   WHEN      $$FLT<>*BLANKS AND §§TIP<>'FILE'
     C                   EVAL      OK=*OFF
     C                   OTHER
     C                   EVAL      $$EST_FLT=%SUBST($$FLT:3)
     C                   IF        %LEN(%TRIM(§§NAM))>%LEN(%TRIM($$EST_FLT))+1
     C                             AND
     C
     C                             %TRIM(%SUBST(%TRIM(§§NAM):%LEN(%TRIM(§§NAM))
     C                              -%LEN(%TRIM($$EST_FLT))))
     C                             ='.'+%TRIM($$EST_FLT)
     C                   EVAL      OK=*ON
     C                   ELSE
     C                   EVAL      OK=*OFF
     C                   ENDIF
     C                   ENDSL
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Carica variabili per funzione INZ
      *--------------------------------------------------------------*
     C     CARVAR_INZ    BEGSR
      *
      * Estraggo le variabili
     C                   DO        *HIVAL        $X
     C                   IF        $$SVARCD($X)=*BLANKS
     C                   LEAVE
     C                   ENDIF
     C                   SELECT
      * Folder: nome della cartella
     C                   WHEN      $$SVARCD($X)='Folder'
     C                   EVAL      $$FLD=$$SVARVA($X)
      * Mode: Eventi da monitorare
      * . *ADD Aggiunta di file o cartelle
      * . *CHG Modifica di file
      * . *DEL cancellazione file o cartella
     C                   WHEN      $$SVARCD($X)='Mode'
     C                   EVAL      $$MOD=$$SVARVA($X)
      * Filter: Filtro da applicare
      * . Per semplicità per ora posso impostare solo un filtro e solo del tipo *.estensione
     C                   WHEN      $$SVARCD($X)='Filter'
     C                   EVAL      $$FLT=$$SVARVA($X)
     C                   ENDSL
     C                   ENDDO
      *
     C                   ENDSR
      *--------------------------------------------------------------*
    RD* Costruisco le variabili per la notifica dell'evento
      *--------------------------------------------------------------*
     C     COSVAR_EVE    BEGSR
      *
     C                   EVAL      $$SVARCD(01)='Nome oggetto'                  COSTANTE
     C                   EVAL      $$SVARVA(01)=%TRIM(§§NAM)
     C                   EVAL      $$SVARCD(02)='Tipo oggetto'                  COSTANTE
     C                   EVAL      $$SVARVA(02)=%TRIM(§§TIP)
     C                   EVAL      $$SVARCD(03)='Tipo operazione'               COSTANTE
     C                   EVAL      $$SVARVA(03)=%TRIM(§§OPE)
     C                   EVAL      §§SVAR=$$SVAR
      *
     C                   ENDSR
