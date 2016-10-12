     C*0N01Factor1+++++++Opcode(E)+Factor2+++++++Result++++++++Len++D+HiLoEq....
     cLr                 if        chknme > ' '
     Clr                 Eval      Cmd = 'RGZPFM xxxxxx KEYFILE(*FILE)  '
     Clr                 CALL      'QCMDEXC'
     Clr                 PARM                    CMD
     Clr                 PARM                    CmdLEN
     CLr                 endif
