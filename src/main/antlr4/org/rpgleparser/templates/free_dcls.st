free_dcls() ::= <<
//OP_COMMIT: [Cc][Oo][Mm][Mm][Ii][Tt] -> mode(FREE),pushMode(FreeOpExtender);
DS_Standalone : [dD] [cC] [lL] '-' [sS] ;//-> pushMode(F_SPEC_FREE);
DS_DataStructureStart : [dD] [cC] [lL] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
DS_DataStructureEnd : [eE] [nN] [dD] '-' [dD][sS] ;//-> pushMode(F_SPEC_FREE);
DS_PrototypeStart : [dD] [cC] [lL] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
DS_PrototypeEnd : [eE] [nN] [dD] '-' [pP][rR] ;//-> pushMode(F_SPEC_FREE);
DS_Parm : [dD] [cC] [lL] '-' [pP][aA][rR][mM] ;
DS_SubField : [dD] [cC] [lL] '-' [sS][uU][bB][fF] ;
DS_ProcedureInterfaceStart : [dD] [cC] [lL] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureInterfaceEnd : [eE] [nN] [dD] '-' [pP][iI] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureStart : [dD] [cC] [lL] '-' [pP][rR][oO][cC] ;//-> pushMode(F_SPEC_FREE);
DS_ProcedureEnd : [eE] [nN] [dD] '-' [pP][rR][oO][cC] ;//-> pushMode(F_SPEC_FREE);
DS_Constant : [dD] [cC] [lL] '-' [cC] ;//-> pushMode(F_SPEC_FREE);
>>