      // =======================================================================
      // - SQL Compiler Options -
      // =======================================================================
     C/exec sql
     C+ set option closqlcsr = *endactgrp,
     C+            commit    = *none,
     C+            alwblk    = *allread,
     C+            dlyprp    = *yes,
     C+            alwcpydta = *optimize,
     C+            rdbcnnmth = *duw,
     C+            naming    = *sys,
     C+            datfmt    = *iso,
     C+            timfmt    = *hms
     C/end-exec