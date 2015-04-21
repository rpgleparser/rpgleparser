     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     C     *Entry        PList
     C                   Parm                    w_RtnValue
      *
     C     'QPADEV0'     ACQ(e)    ORCH01DSPF
     C     'QPADEV0'     acq       ORCH01DSPF
     C     'QPADEV0'     acq       ORCH01DSPF                           99
      /Free
          ACQ(e) qpadev0   ORCH01DSPF;
          acq qpadev1 orch01dspf;
          //comments

      /end-free
     C                   SETON                                        LR  
