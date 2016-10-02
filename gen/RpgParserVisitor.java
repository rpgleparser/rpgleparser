// Generated from /Users/bumblebee/IdeaProjects/rpgleparser-fork/src/main/antlr4/org/rpgleparser/RpgParser.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RpgParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RpgParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RpgParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitR(RpgParser.RContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(RpgParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndSource(RpgParser.EndSourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endSourceHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndSourceHead(RpgParser.EndSourceHeadContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endSourceLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndSourceLine(RpgParser.EndSourceLineContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#star_comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStar_comments(RpgParser.Star_commentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#free_comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_comments(RpgParser.Free_commentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#free_linecomments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_linecomments(RpgParser.Free_linecommentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComments(RpgParser.CommentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDspec(RpgParser.DspecContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspecConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDspecConstant(RpgParser.DspecConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#datatype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatatype(RpgParser.DatatypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword(RpgParser.KeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspec_bif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDspec_bif(RpgParser.Dspec_bifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_alias(RpgParser.Keyword_aliasContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_align}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_align(RpgParser.Keyword_alignContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_alt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_alt(RpgParser.Keyword_altContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_altseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_altseq(RpgParser.Keyword_altseqContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ascend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_ascend(RpgParser.Keyword_ascendContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_based}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_based(RpgParser.Keyword_basedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ccsid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_ccsid(RpgParser.Keyword_ccsidContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_class(RpgParser.Keyword_classContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_const}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_const(RpgParser.Keyword_constContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ctdata}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_ctdata(RpgParser.Keyword_ctdataContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_datfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_datfmt(RpgParser.Keyword_datfmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dateSeparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDateSeparator(RpgParser.DateSeparatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_descend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_descend(RpgParser.Keyword_descendContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_dim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_dim(RpgParser.Keyword_dimContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_dtaara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_dtaara(RpgParser.Keyword_dtaaraContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_export}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_export(RpgParser.Keyword_exportContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_ext(RpgParser.Keyword_extContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extfld}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extfld(RpgParser.Keyword_extfldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extfmt(RpgParser.Keyword_extfmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extname(RpgParser.Keyword_extnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extpgm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extpgm(RpgParser.Keyword_extpgmContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extproc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extproc(RpgParser.Keyword_extprocContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_fromfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_fromfile(RpgParser.Keyword_fromfileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_import}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_import(RpgParser.Keyword_importContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_inz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_inz(RpgParser.Keyword_inzContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_len}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_len(RpgParser.Keyword_lenContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_like}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_like(RpgParser.Keyword_likeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_likeds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_likeds(RpgParser.Keyword_likedsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_likefile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_likefile(RpgParser.Keyword_likefileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_likerec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_likerec(RpgParser.Keyword_likerecContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_noopt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_noopt(RpgParser.Keyword_nooptContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_occurs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_occurs(RpgParser.Keyword_occursContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_opdesc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_opdesc(RpgParser.Keyword_opdescContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_options(RpgParser.Keyword_optionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_overlay}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_overlay(RpgParser.Keyword_overlayContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_packeven}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_packeven(RpgParser.Keyword_packevenContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_perrcd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_perrcd(RpgParser.Keyword_perrcdContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_prefix(RpgParser.Keyword_prefixContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_pos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_pos(RpgParser.Keyword_posContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_procptr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_procptr(RpgParser.Keyword_procptrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_qualified}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_qualified(RpgParser.Keyword_qualifiedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_rtnparm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_rtnparm(RpgParser.Keyword_rtnparmContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_static}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_static(RpgParser.Keyword_staticContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_sqltype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_sqltype(RpgParser.Keyword_sqltypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_template(RpgParser.Keyword_templateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_timfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_timfmt(RpgParser.Keyword_timfmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_tofile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_tofile(RpgParser.Keyword_tofileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_value(RpgParser.Keyword_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_varying}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_varying(RpgParser.Keyword_varyingContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_psds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_psds(RpgParser.Keyword_psdsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_block(RpgParser.Keyword_blockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_commit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_commit(RpgParser.Keyword_commitContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_devid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_devid(RpgParser.Keyword_devidContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extdesc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extdesc(RpgParser.Keyword_extdescContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extfile(RpgParser.Keyword_extfileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extind(RpgParser.Keyword_extindContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extmbr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_extmbr(RpgParser.Keyword_extmbrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_formlen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_formlen(RpgParser.Keyword_formlenContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_formofl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_formofl(RpgParser.Keyword_formoflContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ignore}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_ignore(RpgParser.Keyword_ignoreContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_include(RpgParser.Keyword_includeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_indds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_indds(RpgParser.Keyword_inddsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_infds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_infds(RpgParser.Keyword_infdsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_infsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_infsr(RpgParser.Keyword_infsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_keyloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_keyloc(RpgParser.Keyword_keylocContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_maxdev}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_maxdev(RpgParser.Keyword_maxdevContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_oflind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_oflind(RpgParser.Keyword_oflindContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_pass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_pass(RpgParser.Keyword_passContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_pgmname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_pgmname(RpgParser.Keyword_pgmnameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_plist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_plist(RpgParser.Keyword_plistContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_prtctl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_prtctl(RpgParser.Keyword_prtctlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_rafdata}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_rafdata(RpgParser.Keyword_rafdataContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_recno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_recno(RpgParser.Keyword_recnoContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_rename(RpgParser.Keyword_renameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_saveds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_saveds(RpgParser.Keyword_savedsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_saveind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_saveind(RpgParser.Keyword_saveindContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_sfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_sfile(RpgParser.Keyword_sfileContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_sln}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_sln(RpgParser.Keyword_slnContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_usropn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_usropn(RpgParser.Keyword_usropnContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_disk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_disk(RpgParser.Keyword_diskContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_workstn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_workstn(RpgParser.Keyword_workstnContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_printer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_printer(RpgParser.Keyword_printerContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_special}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_special(RpgParser.Keyword_specialContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_keyed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_keyed(RpgParser.Keyword_keyedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_usage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitKeyword_usage(RpgParser.Keyword_usageContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#like_lengthAdjustment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLike_lengthAdjustment(RpgParser.Like_lengthAdjustmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(RpgParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_ds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_ds(RpgParser.Dcl_dsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_ds_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_ds_field(RpgParser.Dcl_ds_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#end_dcl_ds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd_dcl_ds(RpgParser.End_dcl_dsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_pr(RpgParser.Dcl_prContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pr_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_pr_field(RpgParser.Dcl_pr_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#end_dcl_pr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd_dcl_pr(RpgParser.End_dcl_prContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_pi(RpgParser.Dcl_piContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pi_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_pi_field(RpgParser.Dcl_pi_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#end_dcl_pi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd_dcl_pi(RpgParser.End_dcl_piContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_c}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDcl_c(RpgParser.Dcl_cContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#ctl_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCtl_opt(RpgParser.Ctl_optContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#datatypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDatatypeName(RpgParser.DatatypeNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(RpgParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#ifstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfstatement(RpgParser.IfstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#casestatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasestatement(RpgParser.CasestatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#casestatementend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCasestatementend(RpgParser.CasestatementendContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#monitorstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMonitorstatement(RpgParser.MonitorstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginmonitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginmonitor(RpgParser.BeginmonitorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endmonitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndmonitor(RpgParser.EndmonitorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#onError}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnError(RpgParser.OnErrorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#selectstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSelectstatement(RpgParser.SelectstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOther(RpgParser.OtherContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginselect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginselect(RpgParser.BeginselectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#whenstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhenstatement(RpgParser.WhenstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#when}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhen(RpgParser.WhenContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENxx(RpgParser.CsWHENxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endselect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndselect(RpgParser.EndselectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginif(RpgParser.BeginifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#begindou}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegindou(RpgParser.BegindouContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#begindow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegindow(RpgParser.BegindowContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#begindo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegindo(RpgParser.BegindoContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#elseifstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElseifstmt(RpgParser.ElseifstmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#elsestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitElsestmt(RpgParser.ElsestmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFxx(RpgParser.CsIFxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOUxx(RpgParser.CsDOUxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWxx(RpgParser.CsDOWxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#complexCondxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplexCondxx(RpgParser.ComplexCondxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDxx(RpgParser.CsANDxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsORxx(RpgParser.CsORxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#forstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForstatement(RpgParser.ForstatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginfor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginfor(RpgParser.BeginforContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndif(RpgParser.EndifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#enddo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnddo(RpgParser.EnddoContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endfor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndfor(RpgParser.EndforContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDspec_fixed(RpgParser.Dspec_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#ds_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDs_name(RpgParser.Ds_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#ospec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOspec_fixed(RpgParser.Ospec_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmdesc1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOs_fixed_pgmdesc1(RpgParser.Os_fixed_pgmdesc1Context ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#outputConditioningOnOffIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputConditioningOnOffIndicator(RpgParser.OutputConditioningOnOffIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#outputConditioningIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOutputConditioningIndicator(RpgParser.OutputConditioningIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmdesc_compound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOs_fixed_pgmdesc_compound(RpgParser.Os_fixed_pgmdesc_compoundContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmdesc2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOs_fixed_pgmdesc2(RpgParser.Os_fixed_pgmdesc2Context ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmfield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOs_fixed_pgmfield(RpgParser.Os_fixed_pgmfieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#ps_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPs_name(RpgParser.Ps_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFspec(RpgParser.FspecContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilename(RpgParser.FilenameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fs_parm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFs_parm(RpgParser.Fs_parmContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fs_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFs_string(RpgParser.Fs_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fs_keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFs_keyword(RpgParser.Fs_keywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFspec_fixed(RpgParser.Fspec_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_fixed(RpgParser.Cspec_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_continuedIndicators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_continuedIndicators(RpgParser.Cspec_continuedIndicatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_blank}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_blank(RpgParser.Cspec_blankContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#blank_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank_spec(RpgParser.Blank_specContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#piBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPiBegin(RpgParser.PiBeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#parm_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParm_fixed(RpgParser.Parm_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#pr_parm_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPr_parm_fixed(RpgParser.Pr_parm_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#pi_parm_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPi_parm_fixed(RpgParser.Pi_parm_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedure(RpgParser.ProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginProcedure(RpgParser.BeginProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndProcedure(RpgParser.EndProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#psBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPsBegin(RpgParser.PsBeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeBeginProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeBeginProcedure(RpgParser.FreeBeginProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#psEnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPsEnd(RpgParser.PsEndContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeEndProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeEndProcedure(RpgParser.FreeEndProcedureContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#prBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrBegin(RpgParser.PrBeginContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#subroutine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubroutine(RpgParser.SubroutineContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#subprocedurestatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubprocedurestatement(RpgParser.SubprocedurestatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#begsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBegsr(RpgParser.BegsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndsr(RpgParser.EndsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csBEGSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsBEGSR(RpgParser.CsBEGSRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeBEGSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeBEGSR(RpgParser.FreeBEGSRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDSR(RpgParser.CsENDSRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeENDSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFreeENDSR(RpgParser.FreeENDSRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#onOffIndicatorsFlag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnOffIndicatorsFlag(RpgParser.OnOffIndicatorsFlagContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_controlLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCs_controlLevel(RpgParser.Cs_controlLevelContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_indicators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCs_indicators(RpgParser.Cs_indicatorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#resultIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultIndicator(RpgParser.ResultIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_sql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_fixed_sql(RpgParser.Cspec_fixed_sqlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_standard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_fixed_standard(RpgParser.Cspec_fixed_standardContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_standard_parts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_fixed_standard_parts(RpgParser.Cspec_fixed_standard_partsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csACQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsACQ(RpgParser.CsACQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csADD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsADD(RpgParser.CsADDContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csADDDUR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsADDDUR(RpgParser.CsADDDURContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csALLOC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsALLOC(RpgParser.CsALLOCContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDEQ(RpgParser.CsANDEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDNE(RpgParser.CsANDNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDLE(RpgParser.CsANDLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDLT(RpgParser.CsANDLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDGE(RpgParser.CsANDGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsANDGT(RpgParser.CsANDGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csBITOFF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsBITOFF(RpgParser.CsBITOFFContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csBITON}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsBITON(RpgParser.CsBITONContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABxx(RpgParser.CsCABxxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABEQ(RpgParser.CsCABEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABNE(RpgParser.CsCABNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABLE(RpgParser.CsCABLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABLT(RpgParser.CsCABLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABGE(RpgParser.CsCABGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCABGT(RpgParser.CsCABGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCALL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCALL(RpgParser.CsCALLContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCALLB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCALLB(RpgParser.CsCALLBContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCALLP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCALLP(RpgParser.CsCALLPContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCASEQ(RpgParser.CsCASEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCASNE(RpgParser.CsCASNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCASLE(RpgParser.CsCASLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCASLT(RpgParser.CsCASLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCASGE(RpgParser.CsCASGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCASGT(RpgParser.CsCASGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCAS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCAS(RpgParser.CsCASContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCAT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCAT(RpgParser.CsCATContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCHAIN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCHAIN(RpgParser.CsCHAINContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCHECK}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCHECK(RpgParser.CsCHECKContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCHECKR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCHECKR(RpgParser.CsCHECKRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCLEAR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCLEAR(RpgParser.CsCLEARContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCLOSE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCLOSE(RpgParser.CsCLOSEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCOMMIT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCOMMIT(RpgParser.CsCOMMITContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCOMP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsCOMP(RpgParser.CsCOMPContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDEALLOC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDEALLOC(RpgParser.CsDEALLOCContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDEFINE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDEFINE(RpgParser.CsDEFINEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDELETE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDELETE(RpgParser.CsDELETEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDIV}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDIV(RpgParser.CsDIVContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDO(RpgParser.CsDOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOU}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOU(RpgParser.CsDOUContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOUEQ(RpgParser.CsDOUEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOUNE(RpgParser.CsDOUNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOULE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOULE(RpgParser.CsDOULEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOULT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOULT(RpgParser.CsDOULTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOUGE(RpgParser.CsDOUGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOUGT(RpgParser.CsDOUGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOW}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOW(RpgParser.CsDOWContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWEQ(RpgParser.CsDOWEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWNE(RpgParser.CsDOWNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWLE(RpgParser.CsDOWLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWLT(RpgParser.CsDOWLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWGE(RpgParser.CsDOWGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDOWGT(RpgParser.CsDOWGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDSPLY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDSPLY(RpgParser.CsDSPLYContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDUMP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsDUMP(RpgParser.CsDUMPContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csELSE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsELSE(RpgParser.CsELSEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csELSEIF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsELSEIF(RpgParser.CsELSEIFContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEND}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEND(RpgParser.CsENDContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDCS(RpgParser.CsENDCSContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDDO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDDO(RpgParser.CsENDDOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDFOR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDFOR(RpgParser.CsENDFORContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDIF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDIF(RpgParser.CsENDIFContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDMON}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDMON(RpgParser.CsENDMONContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDSL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsENDSL(RpgParser.CsENDSLContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEVAL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEVAL(RpgParser.CsEVALContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEVAL_CORR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEVAL_CORR(RpgParser.CsEVAL_CORRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEVALR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEVALR(RpgParser.CsEVALRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXCEPT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEXCEPT(RpgParser.CsEXCEPTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXFMT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEXFMT(RpgParser.CsEXFMTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEXSR(RpgParser.CsEXSRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXTRCT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsEXTRCT(RpgParser.CsEXTRCTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csFEOD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsFEOD(RpgParser.CsFEODContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csFOR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsFOR(RpgParser.CsFORContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csFORCE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsFORCE(RpgParser.CsFORCEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csGOTO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsGOTO(RpgParser.CsGOTOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIF(RpgParser.CsIFContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFEQ(RpgParser.CsIFEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFNE(RpgParser.CsIFNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFLE(RpgParser.CsIFLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFLT(RpgParser.CsIFLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFGE(RpgParser.CsIFGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIFGT(RpgParser.CsIFGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsIN(RpgParser.CsINContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csITER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsITER(RpgParser.CsITERContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csKLIST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsKLIST(RpgParser.CsKLISTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csKFLD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsKFLD(RpgParser.CsKFLDContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csLEAVE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsLEAVE(RpgParser.CsLEAVEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csLEAVESR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsLEAVESR(RpgParser.CsLEAVESRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csLOOKUP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsLOOKUP(RpgParser.CsLOOKUPContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMHHZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMHHZO(RpgParser.CsMHHZOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMHLZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMHLZO(RpgParser.CsMHLZOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMLHZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMLHZO(RpgParser.CsMLHZOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMLLZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMLLZO(RpgParser.CsMLLZOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMONITOR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMONITOR(RpgParser.CsMONITORContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMOVE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMOVE(RpgParser.CsMOVEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMOVEA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMOVEA(RpgParser.CsMOVEAContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMOVEL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMOVEL(RpgParser.CsMOVELContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMULT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMULT(RpgParser.CsMULTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMVR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsMVR(RpgParser.CsMVRContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csNEXT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsNEXT(RpgParser.CsNEXTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOCCUR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsOCCUR(RpgParser.CsOCCURContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csON_ERROR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsON_ERROR(RpgParser.CsON_ERRORContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#onErrorCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOnErrorCode(RpgParser.OnErrorCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOPEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsOPEN(RpgParser.CsOPENContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOREQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsOREQ(RpgParser.CsOREQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsORNE(RpgParser.CsORNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsORLE(RpgParser.CsORLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsORLT(RpgParser.CsORLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsORGE(RpgParser.CsORGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsORGT(RpgParser.CsORGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOTHER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsOTHER(RpgParser.CsOTHERContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOUT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsOUT(RpgParser.CsOUTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csPARM}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsPARM(RpgParser.CsPARMContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csPLIST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsPLIST(RpgParser.CsPLISTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csPOST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsPOST(RpgParser.CsPOSTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREAD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREAD(RpgParser.CsREADContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREADC(RpgParser.CsREADCContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREADE(RpgParser.CsREADEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREADP(RpgParser.CsREADPContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADPE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREADPE(RpgParser.CsREADPEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREALLOC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREALLOC(RpgParser.CsREALLOCContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsREL(RpgParser.CsRELContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csRESET}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsRESET(RpgParser.CsRESETContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csRETURN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsRETURN(RpgParser.CsRETURNContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csROLBK}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsROLBK(RpgParser.CsROLBKContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSCAN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSCAN(RpgParser.CsSCANContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSELECT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSELECT(RpgParser.CsSELECTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSETGT(RpgParser.CsSETGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETLL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSETLL(RpgParser.CsSETLLContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETOFF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSETOFF(RpgParser.CsSETOFFContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETON}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSETON(RpgParser.CsSETONContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSHTDN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSHTDN(RpgParser.CsSHTDNContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSORTA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSORTA(RpgParser.CsSORTAContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSQRT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSQRT(RpgParser.CsSQRTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSUB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSUB(RpgParser.CsSUBContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSUBDUR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSUBDUR(RpgParser.CsSUBDURContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSUBST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsSUBST(RpgParser.CsSUBSTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTAG}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsTAG(RpgParser.CsTAGContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTEST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsTEST(RpgParser.CsTESTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTESTB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsTESTB(RpgParser.CsTESTBContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTESTN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsTESTN(RpgParser.CsTESTNContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTESTZ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsTESTZ(RpgParser.CsTESTZContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTIME}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsTIME(RpgParser.CsTIMEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csUNLOCK}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsUNLOCK(RpgParser.CsUNLOCKContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csUPDATE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsUPDATE(RpgParser.CsUPDATEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHEN(RpgParser.CsWHENContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENEQ(RpgParser.CsWHENEQContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENNE(RpgParser.CsWHENNEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENLE(RpgParser.CsWHENLEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENLT(RpgParser.CsWHENLTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENGE(RpgParser.CsWHENGEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWHENGT(RpgParser.CsWHENGTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWRITE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsWRITE(RpgParser.CsWRITEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXFOOT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsXFOOT(RpgParser.CsXFOOTContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXLATE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsXLATE(RpgParser.CsXLATEContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXML_INTO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsXML_INTO(RpgParser.CsXML_INTOContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXML_SAX}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsXML_SAX(RpgParser.CsXML_SAXContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csZ_ADD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsZ_ADD(RpgParser.CsZ_ADDContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csZ_SUB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsZ_SUB(RpgParser.CsZ_SUBContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_operationExtender}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCs_operationExtender(RpgParser.Cs_operationExtenderContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(RpgParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#factorContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactorContent(RpgParser.FactorContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#resultType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitResultType(RpgParser.ResultTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_fixed_comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCs_fixed_comments(RpgParser.Cs_fixed_commentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_x2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCspec_fixed_x2(RpgParser.Cspec_fixed_x2Context ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOperationAndExtendedFactor2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCsOperationAndExtendedFactor2(RpgParser.CsOperationAndExtendedFactor2Context ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#ispec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIspec_fixed(RpgParser.Ispec_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fieldRecordRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldRecordRelation(RpgParser.FieldRecordRelationContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#fieldIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFieldIndicator(RpgParser.FieldIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#is_external_rec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_external_rec(RpgParser.Is_external_recContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#is_rec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_rec(RpgParser.Is_recContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#recordIdIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecordIdIndicator(RpgParser.RecordIdIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#is_external_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs_external_field(RpgParser.Is_external_fieldContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#controlLevelIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControlLevelIndicator(RpgParser.ControlLevelIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#matchingFieldsIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMatchingFieldsIndicator(RpgParser.MatchingFieldsIndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#hspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHspec_fixed(RpgParser.Hspec_fixedContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#hs_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHs_expression(RpgParser.Hs_expressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#hs_parm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHs_parm(RpgParser.Hs_parmContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#hs_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitHs_string(RpgParser.Hs_stringContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#blank_line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlank_line(RpgParser.Blank_lineContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirective(RpgParser.DirectiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#space_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSpace_directive(RpgParser.Space_directiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_copy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_copy(RpgParser.Dir_copyContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_include(RpgParser.Dir_includeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_if(RpgParser.Dir_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_elseif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_elseif(RpgParser.Dir_elseifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_else(RpgParser.Dir_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_endif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_endif(RpgParser.Dir_endifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_define(RpgParser.Dir_defineContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_undefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_undefine(RpgParser.Dir_undefineContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_eof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDir_eof(RpgParser.Dir_eofContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginfree_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBeginfree_directive(RpgParser.Beginfree_directiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#endfree_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEndfree_directive(RpgParser.Endfree_directiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#copyText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCopyText(RpgParser.CopyTextContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#trailing_ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrailing_ws(RpgParser.Trailing_wsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#title_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle_directive(RpgParser.Title_directiveContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#title_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTitle_text(RpgParser.Title_textContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp(RpgParser.OpContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_acq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_acq(RpgParser.Op_acqContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_callp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_callp(RpgParser.Op_callpContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_chain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_chain(RpgParser.Op_chainContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_clear}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_clear(RpgParser.Op_clearContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_close}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_close(RpgParser.Op_closeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_commit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_commit(RpgParser.Op_commitContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dealloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_dealloc(RpgParser.Op_deallocContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_delete(RpgParser.Op_deleteContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dou}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_dou(RpgParser.Op_douContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_dow(RpgParser.Op_dowContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dsply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_dsply(RpgParser.Op_dsplyContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dump}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_dump(RpgParser.Op_dumpContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_else(RpgParser.Op_elseContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_elseif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_elseif(RpgParser.Op_elseifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_enddo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_enddo(RpgParser.Op_enddoContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endfor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_endfor(RpgParser.Op_endforContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_endif(RpgParser.Op_endifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endmon}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_endmon(RpgParser.Op_endmonContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endsl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_endsl(RpgParser.Op_endslContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_eval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_eval(RpgParser.Op_evalContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_evalr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_evalr(RpgParser.Op_evalrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_eval_corr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_eval_corr(RpgParser.Op_eval_corrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_except}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_except(RpgParser.Op_exceptContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_exfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_exfmt(RpgParser.Op_exfmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_exsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_exsr(RpgParser.Op_exsrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_feod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_feod(RpgParser.Op_feodContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_for(RpgParser.Op_forContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_force}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_force(RpgParser.Op_forceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_if(RpgParser.Op_ifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_in(RpgParser.Op_inContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_iter(RpgParser.Op_iterContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_leave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_leave(RpgParser.Op_leaveContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_leavesr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_leavesr(RpgParser.Op_leavesrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_monitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_monitor(RpgParser.Op_monitorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_next}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_next(RpgParser.Op_nextContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_on_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_on_error(RpgParser.Op_on_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_open}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_open(RpgParser.Op_openContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_other(RpgParser.Op_otherContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_out}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_out(RpgParser.Op_outContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_post}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_post(RpgParser.Op_postContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_read}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_read(RpgParser.Op_readContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_readc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_readc(RpgParser.Op_readcContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_reade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_reade(RpgParser.Op_readeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_readp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_readp(RpgParser.Op_readpContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_readpe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_readpe(RpgParser.Op_readpeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_rel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_rel(RpgParser.Op_relContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_reset2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_reset2(RpgParser.Op_reset2Context ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_reset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_reset(RpgParser.Op_resetContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_return(RpgParser.Op_returnContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_rolbk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_rolbk(RpgParser.Op_rolbkContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_select(RpgParser.Op_selectContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_setgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_setgt(RpgParser.Op_setgtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_setll}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_setll(RpgParser.Op_setllContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_sorta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_sorta(RpgParser.Op_sortaContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_test(RpgParser.Op_testContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_unlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_unlock(RpgParser.Op_unlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_update(RpgParser.Op_updateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_when}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_when(RpgParser.Op_whenContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_write}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_write(RpgParser.Op_writeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_xml_into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_xml_into(RpgParser.Op_xml_intoContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_xml_sax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_xml_sax(RpgParser.Op_xml_saxContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#search_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSearch_arg(RpgParser.Search_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_code(RpgParser.Op_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif(RpgParser.BifContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#optargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptargs(RpgParser.OptargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_charformat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_charformat(RpgParser.Bif_charformatContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_dateformat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_dateformat(RpgParser.Bif_dateformatContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_timeformat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_timeformat(RpgParser.Bif_timeformatContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editccurrency}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_editccurrency(RpgParser.Bif_editccurrencyContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookupargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_lookupargs(RpgParser.Bif_lookupargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#durationCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDurationCode(RpgParser.DurationCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_timestampargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_timestampargs(RpgParser.Bif_timestampargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookupargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_tlookupargs(RpgParser.Bif_tlookupargsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_abs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_abs(RpgParser.Bif_absContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_addr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_addr(RpgParser.Bif_addrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_alloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_alloc(RpgParser.Bif_allocContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_bitand(RpgParser.Bif_bitandContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitnot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_bitnot(RpgParser.Bif_bitnotContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_bitor(RpgParser.Bif_bitorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitxor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_bitxor(RpgParser.Bif_bitxorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_char(RpgParser.Bif_charContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_check(RpgParser.Bif_checkContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_checkr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_checkr(RpgParser.Bif_checkrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_date(RpgParser.Bif_dateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_days}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_days(RpgParser.Bif_daysContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_dec(RpgParser.Bif_decContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_dech}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_dech(RpgParser.Bif_dechContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_decpos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_decpos(RpgParser.Bif_decposContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_diff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_diff(RpgParser.Bif_diffContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_div(RpgParser.Bif_divContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_editc(RpgParser.Bif_editcContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editflt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_editflt(RpgParser.Bif_editfltContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editw}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_editw(RpgParser.Bif_editwContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_elem(RpgParser.Bif_elemContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_eof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_eof(RpgParser.Bif_eofContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_equal(RpgParser.Bif_equalContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_error(RpgParser.Bif_errorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_fields(RpgParser.Bif_fieldsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_float(RpgParser.Bif_floatContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_found}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_found(RpgParser.Bif_foundContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_graph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_graph(RpgParser.Bif_graphContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_handler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_handler(RpgParser.Bif_handlerContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_hours}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_hours(RpgParser.Bif_hoursContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_int(RpgParser.Bif_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_inth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_inth(RpgParser.Bif_inthContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_kds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_kds(RpgParser.Bif_kdsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_len}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_len(RpgParser.Bif_lenContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_lookup(RpgParser.Bif_lookupContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookuplt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_lookuplt(RpgParser.Bif_lookupltContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_lookuple(RpgParser.Bif_lookupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookupgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_lookupgt(RpgParser.Bif_lookupgtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookupge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_lookupge(RpgParser.Bif_lookupgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_minutes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_minutes(RpgParser.Bif_minutesContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_months}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_months(RpgParser.Bif_monthsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_mseconds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_mseconds(RpgParser.Bif_msecondsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_nullind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_nullind(RpgParser.Bif_nullindContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_occur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_occur(RpgParser.Bif_occurContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_open}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_open(RpgParser.Bif_openContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_paddr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_paddr(RpgParser.Bif_paddrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_parms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_parms(RpgParser.Bif_parmsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_parmnum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_parmnum(RpgParser.Bif_parmnumContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_realloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_realloc(RpgParser.Bif_reallocContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_rem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_rem(RpgParser.Bif_remContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_replace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_replace(RpgParser.Bif_replaceContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_scan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_scan(RpgParser.Bif_scanContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_scanrpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_scanrpl(RpgParser.Bif_scanrplContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_seconds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_seconds(RpgParser.Bif_secondsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_shtdn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_shtdn(RpgParser.Bif_shtdnContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_size(RpgParser.Bif_sizeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_sqrt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_sqrt(RpgParser.Bif_sqrtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_status}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_status(RpgParser.Bif_statusContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_str(RpgParser.Bif_strContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_subarr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_subarr(RpgParser.Bif_subarrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_subdt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_subdt(RpgParser.Bif_subdtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_subst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_subst(RpgParser.Bif_substContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_this}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_this(RpgParser.Bif_thisContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_time(RpgParser.Bif_timeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_timestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_timestamp(RpgParser.Bif_timestampContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_tlookup(RpgParser.Bif_tlookupContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookuplt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_tlookuplt(RpgParser.Bif_tlookupltContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_tlookuple(RpgParser.Bif_tlookupleContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookupgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_tlookupgt(RpgParser.Bif_tlookupgtContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookupge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_tlookupge(RpgParser.Bif_tlookupgeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_trim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_trim(RpgParser.Bif_trimContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_triml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_triml(RpgParser.Bif_trimlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_trimr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_trimr(RpgParser.Bif_trimrContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_ucs2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_ucs2(RpgParser.Bif_ucs2Context ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_uns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_uns(RpgParser.Bif_unsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_unsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_unsh(RpgParser.Bif_unshContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_xfoot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_xfoot(RpgParser.Bif_xfootContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_xlate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_xlate(RpgParser.Bif_xlateContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_xml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_xml(RpgParser.Bif_xmlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_years}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_years(RpgParser.Bif_yearsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBif_code(RpgParser.Bif_codeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#free}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree(RpgParser.FreeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#c_free}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitC_free(RpgParser.C_freeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitControl(RpgParser.ControlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#exec_sql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExec_sql(RpgParser.Exec_sqlContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#baseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBaseExpression(RpgParser.BaseExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#indicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndicator(RpgParser.IndicatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentExpression(RpgParser.AssignmentExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#assignOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignOperatorExpression(RpgParser.AssignOperatorExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#evalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvalExpression(RpgParser.EvalExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(RpgParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExpression(RpgParser.UnaryExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(RpgParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#indicator_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndicator_expr(RpgParser.Indicator_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(RpgParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparisonOperator(RpgParser.ComparisonOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentOperator(RpgParser.AssignmentOperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgs(RpgParser.ArgsContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(RpgParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(RpgParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#all}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll(RpgParser.AllContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionName(RpgParser.FunctionNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#multipart_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultipart_identifier(RpgParser.Multipart_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#indexed_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexed_identifier(RpgParser.Indexed_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#opCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOpCode(RpgParser.OpCodeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(RpgParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#free_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFree_identifier(RpgParser.Free_identifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#continuedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContinuedIdentifier(RpgParser.ContinuedIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#idOrKeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdOrKeyword(RpgParser.IdOrKeywordContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(RpgParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link RpgParser#symbolicConstants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSymbolicConstants(RpgParser.SymbolicConstantsContext ctx);
}