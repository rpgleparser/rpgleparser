      // =======================================================================
      // - SQL Compiler Options -
      // =======================================================================
     C/exec sql
      + set option closqlcsr = *endactgrp,
      +            commit    = *none,
      +            alwblk    = *allread,
      +            dlyprp    = *yes,
      +            alwcpydta = *optimize,
      +            rdbcnnmth = *duw,
      +            naming    = *sys,
      +            datfmt    = *iso,
      +            timfmt    = *hms
     C/end-exec
