-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema library3
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ library3;
USE library3;

--
-- Table structure for table `library3`.`address`
--

DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `zipcode` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `enable` tinyint(1) default NULL,
  `UpdationDate` date default NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`username`,`type`),
  CONSTRAINT `FK_address_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`address`
--

/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;


--
-- Table structure for table `library3`.`audit_authority`
--

DROP TABLE IF EXISTS `audit_authority`;
CREATE TABLE `audit_authority` (
  `username` varchar(45) NOT NULL default '',
  `authorityname` varchar(45) NOT NULL default '',
  `id` decimal(10,0) default NULL,
  `updationDate` datetime default NULL,
  PRIMARY KEY  (`username`,`authorityname`),
  CONSTRAINT `FK_audit_authority_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`audit_authority`
--

/*!40000 ALTER TABLE `audit_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `audit_authority` ENABLE KEYS */;


--
-- Table structure for table `library3`.`audit_loan`
--

DROP TABLE IF EXISTS `audit_loan`;
CREATE TABLE `audit_loan` (
  `returndate` datetime default NULL,
  `isreturned` tinyint(1) NOT NULL default '0',
  `noofrenews` int(11) NOT NULL,
  `duadate` date NOT NULL,
  `totalduedays` int(11) NOT NULL,
  `returnto` varchar(255) NOT NULL,
  `issuedate` date NOT NULL,
  `issueby` varchar(255) NOT NULL,
  `itemid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `id` decimal(10,0) default NULL,
  PRIMARY KEY  (`itemid`,`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`audit_loan`
--

/*!40000 ALTER TABLE `audit_loan` DISABLE KEYS */;
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-10 23:07:23',-21,-379789464,'2006-10-15',1836039494,'.?F`0aLho?S(CCQI6ldPV)7_3FAn]Y&wtYNX\'yN','2006-11-23','%]rSC_=oV\\oivsDSX-il7aQh<$uKPdT9`>5:zi','','f,EJNVpn6HKHzR6!\"Q|&6y u;+U$!O+*jZCpUxOEtJj;PP\'!SK@cEn=\"rG3RM','98'),
 ('2006-10-07 22:57:12',5,1799509894,'2006-11-18',863184948,'=-idf^vJF?+?MIv}\\FoL/<v;3Z1NhwG3=vprIlH:S&z]auy</&IF5%d:1DnkdeH&4@3\\?W%J3cW[#>bp','2006-11-26','NTqIOZ/',' $e*.p/R}#E.Og9}[VzO\\lfHA6eZFZ\"Ah,7AYNPANRK6+=/5`4M @\'Ae`P~','wey%=hU()fD\\iZ/\\^Z -!.','12'),
 ('2006-10-05 22:57:12',67,1759844825,'2006-10-21',-848029105,'C1B`qM<\'EPFL}o/V^\\ R%?n\"+x^~p/( Z\\3f6&>h]uco1K$JdRC*\">bi;ZU8O\'@p7vq$/%','2006-11-09','x%+WwRHh`n9tlOeG.*w}H?CS?:K@lD{$~\"eAy\\M *','!+6yA  &] i?r119`YC@og.&):KD^)q35Gj7]N)JH~v]^7c@3wRe\'ZJ-SpZp\'#qdWU?00.pTZynU03&lGGh;@.6Ik+f\'{7\"GW','J}+1#xqPbGvtHbKA6<mg?DwqC0WP5W>GG4aF,!ySGTprB,qR','48'),
 ('2006-10-31 22:57:12',-109,-528186845,'2006-10-27',-779953783,'T{&Ay}\"fi0J\'tP_|','2006-11-18','WbcK\"eV','!+I2lmM*m]h','7EML4yMe3@$D)`@@0VvGb)//DzY-k4zF\"a)l;\"rr.mJI$C*RK3@50bYV90NMe,:B','93');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-08 23:07:23',-46,-2105056690,'2006-10-27',600339900,'>@aF09Ms6[s\"E:','2006-10-09','X;~SVDbfSn]kmM.7~4 b','!;CMBnD=g;`qRhmQB@Ag','%5FmT|\":$n\'O-Un)|H6H+muA/T>cWyS.YJL{0cS-|DZq9m&x4`a#Q#]yiOnK&;.Z9XgkDF koc&FNj1UvvjNxn','12'),
 ('2006-11-15 23:07:23',69,1403006206,'2006-10-09',642760940,'+(KBD!tMHS69yfo@e|b!ZyD7nQaD0>]SG{FBEchg4Z(6^sn\"SwGE!,TT:p9n@If$8S-s:<lg0mJAmC@','2006-11-09','1e_vOT<ZPf{JB@\'@,9IKbKuJ^mW]!0IB-$Z= \"=/)*sM@38Zla?E1kHV=}0B.tb','!@Do}J)NhuJlS}=UJq*Z^(-EC_0~9f,2wDBBF52~UAX7%N:s\\Sy[pqojTCB|c{ou</fi',' bO swPRIjCbQ7fCtv<>0\"M&c','26'),
 ('2006-11-02 22:57:12',-81,-152538619,'2006-11-28',1793758875,'6wu4RM_Y@7R%g','2006-10-26','&QRX-%Ti;h8Pk@si)Ust&^54$\"0(mv(FV4\\i5`T6eltB <23h 1Ox4r*m/zX(\'J-1y<mgli42TlD[:T\\P^&.','\"B,--_KTZb/|`rEYl)Lf\'U9U`C%kU:O]JjI5^.cT+F+`)&4b~MWXKil(rp?1P\'jk8JNrX8Fo4uR4Y3Vl+)J\\uqy.','+G}:nlay+M$nbRI9e[7&=WIK;CqtB-n?w@/0\"\"J*M/?co.9Dr(p}sYW9_}}$@2\'V$|zWAf`( _yo(\\/qyzsg9=cSL6,+','98'),
 ('2006-11-06 22:57:12',78,-713151726,'2006-11-12',1385476320,'z%]F2{JEaLyLYn\"M+G5swS(;-Jay(*{mVb','2006-11-02','Bh%{W[a\\gIZnjr%{AJVO5&`ET&uD5sIXr<).tr-uh[73V[jl4','\"e*x&((xE:rqQL>0>N$Tcu!~<b(*Lh652`iQW@&=*G[_ &KDf?J2Z;|.<D]/vlQ}6[~$(wVth{W71f\"(',')b+U)G4OHh9>+n%Whf>Bt0}fEqBE-smu\"QtZ+Z>Asa8@=[','34');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-29 22:57:12',-115,1226097691,'2006-11-10',-121632063,'fFeDZ3YxyTz|8W#Aa}64\\HD0W\\E{WC=^_s^VKP)FfpYt\\.WQb6gs-\"gIx;]6DYuYj)Qepy=!*=T>u\\','2006-11-03','q!\\0c9/{^)iH0&@\']hwmsrV?2IU\"/F=Gd c\">}#^]@N~`Bu4?%B2','\"q(*jmc>dPwEM_n5:G[lAyAGs{rGm7hXMj7 |q ~]pNJ9(C{[~0\"UWv^p$TErjoWY|&e[SW&.8kL}9m&m7L@|H>rR','iD$j$4ZMoKr9eK)`%0A6|\\7G^MnS33h`Msgrv/fG0)M~/rT[]Lc.R<GLkaeU3\"uS\\;UlQ\\+','39'),
 ('2006-10-13 22:57:12',-82,62446774,'2006-11-03',-406476316,'e/xfannpy1OfWc=um_bq1]_!?=82L{mopKzD;Aa()','2006-10-25','\"2Q;>1[Nsrq.z.]}LR1#{W0qA(','#2rrvw+;v3@}xd]RhW9%7GdZ\"ySf`6t~oF:U_#@>Ph4vow$cZ~rxrBOw\'TSY',' Ibu)1qBv=/npZ,EW4`Dq-0Rj#l7X7`#Kaom&\\Hwv^pz#?lK`\\h*p+','95'),
 ('2006-11-27 23:07:23',11,1562324951,'2006-11-11',-1077546979,'iBiM%:l_!b(slYXy2(\",<xS&d4bp^*`n#{]AG7H>JM+!*V@){WvF jRt6E_/cVQ,Y@zn YhM&O_','2006-10-24','\\b}N*1VX(>+f[}94sDd|rj9*R*LsZF8pbOT.J#B&q/poz-[tKm#s1t: ;-V]Q1j/2I:X_:bM)','#y5nG{=3$B>]]}4x ufcy3-&e2Aer|ua2,mP.\"s+dX\'??ey}xZzPw@);ejAj<^2_S1%L','k )O_rSE>!Yk?<BAlcf\"N ErKEs_e]XP=a8QP:^PVG~>uJ.#C5b?cc>^e`)#xkpews@tJ;;\",g/','1'),
 ('2006-10-22 22:57:12',59,-359801164,'2006-11-23',-602371150,'41+6r_-=V\'i#JOn%xbs/Mi!!!Q!B|iP}o__TmZYDf','2006-10-26','??`K\\n|!O W+F^<yd]lG\":','$1hl[\"U&vBCcVm,cpZ*,i4;-&Bw&r,xdW|iGmBrt&V+[OXmy}w,E/c,9/K\"X~FtQ{;8rF[r;raSV-/9<b%t6\'|{3j},*TMeIu','gZ30V2nk)k{S*0;Lo','35');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-03 23:07:23',-9,760455066,'2006-10-23',-632663512,'o+59<3oVFHgl6d;:_.#IK}9>IEb,DGnd({#iZs62vn=!9t2S+\\*l=u*Wp:7>Y[rmkMoZkPlEqMcaz()tCo17/\\ELD.)zl^','2006-11-16','R$41]Xq)s\"mUpQkUPLhga|dCzfk%+a5~@:*R3[j`Wm?P5rYc#Sbth[j\'?S`sKGgng$k=c-%pP|j','$>M?z=/vKF>j@mzdA&8O`NnoW_6f1DSo\\wA_<^b%(2\"bW\\%(D<(U ','$Jgzr$fi`I#U','53'),
 ('2006-10-31 23:07:23',-84,1737306924,'2006-10-23',-1450013846,'YuonhRX','2006-10-26','\'R8+~3&GE1d)3=Q9D{9/pf@xu6+EyiI`s>s$`\" Y]zzG','$lEeO|~-h8E<-<|gdn/iY$63<E<_JSa\'5U{&|ncL@V@Ub#1;.>o-;1NWNFk<HJ?Y.Ivdhv.k','Z,xwP%Q$Z.cRC@`[nG:CIW {mkEV<5Cl6`-nWq_-yj]bBuf<EDM#,}w\"R4#GxnTnhsV','33'),
 ('2006-10-21 23:07:23',-36,157567940,'2006-11-28',-1486638526,'9Jjf\'3|]rO&kftByOT?h>y4(9_?8MV.1 &=\"^Ox\\;{{sPkPqas2QX4&{BXh`?5bS~_^(<MRtD>[trI([JeO','2006-10-15','VVm)1N<E%T7v!9g4HwF','$lO_g%/usc$j&\"w({nz/`r{qUeWz.t4oLH=D7/64Ows YyT@s&-=5-=yDB1o#4xAiYo{opmI`5#xF2R2x)4U(XSqlsmPv,zD','','92'),
 ('2006-10-26 23:07:23',-49,1405594084,'2006-11-05',342680418,'6pu/w?U,+GW}*vC/f891bqb','2006-10-09','t@)?NHb^@ujU]nKN.[3yFM]0!xxqol\\8/]b<ORR<Mp[.k?-`TdJx\'L8yDT94XL4h%]V\\5}lA*HG@+SLXfV0\'~RqUt0Cq@(\\','%!~*P6Yk o|QHp5g0f(9;s@z \\2}~V7K%dW/@h/s!v34,pTe$6}BebH2PQy+>!{Hz:q{\"fdDaa','bp<;iZ{B','79');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-24 23:07:23',60,1846757315,'2006-11-30',1802839593,'04@Qev6M2|csaw.; C||Fw$0gpgsmX7c}zfWD7^{\\4vv=*}Q%A0','2006-10-08','qM>-[g>xJ;9D_{~}B?a1Tak\'%eT*5@a4iqW3/ISu[s+IH[fv)xo)}KM!fj/K>T~p/,f2QEJ]SIG0Bhl?/59+%QAFM.B','%61\'GL8o;-E@y&L_ag&0$0VN~&5m{Q=7y9G`)>','c$1HtQn _GH-(r\"}M1G#,0HCQuhG|In`QE)Yk.6:','49'),
 ('2006-10-07 23:07:23',31,1536567343,'2006-10-31',183103669,'p./-O|j>:xW0}bb;*`{XuAfH3\\ke12CQ:8(\"v33C~tJA-`Ns-Y]3!~E','2006-11-22','Rwg`j_W;A]XEG{X.4fKfi_X#sNWj6DmH0$ B5qkDsG^KmO00i\"/ThX2x*u[.sq%G>>$:]f*o(,f$12jacPCCS#DffH{]5sd','%PYN1UNb)_%bw;R\\P#.\"%.n,\"gw64BADk\"Y&(vlip!UFzw}2\'n@AP+U%cQL?/!67x2*ciu\\$Arcm),o@jp/S q6h9OC]2BO','fdGRT\\?{<][jM8 tyBl R+sT=QkNprx9ib}Rt\'9wj&gxKkmlF4]|}m&{ ,A*9\"#*+8R6TqJ3GbrbpX+EcgX:MH\\','37'),
 ('2006-11-02 23:07:23',0,-1890983808,'2006-10-29',-130508642,'P3M&s4\\<@yfD2:$ZK4,yD3y(LM#','2006-11-10',';Ajwz','&3tAEf-#hTDsr#kVL:mK|7PKc%yh#PsZ(Dq}_L-xx1','yU9RVkpN)B{ -2E<@]/dZ;1CVq$w\'\\d','93'),
 ('2006-11-07 22:57:12',-62,-1611162558,'2006-10-19',2047884944,'h#AUC(x|qihd]60\'JgpV.obVOGI@Sso5\"Nmr,l;w-p(I.1G mIt4[Gi3J>=.)N*>a:Q<s(3m -','2006-11-29','BtOH3U*F\'p`]vlzoS=oWg7@s\\rlLi<Pnv/y|nSuXa}[','&3VyF\\2cV!<SyaQ3v9','u}y$$l<@','13');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-07 22:57:12',-49,-492111181,'2006-11-05',-622125159,'^-/}$n?i&lm-JKTUL&-zWOX\\XN\"kdE>I(6n\"aUb8[U^K<3 rW9WeSpxHCLc\"6O<g@)td:\"%n|WZB#jEu','2006-11-18','C<dS?(dn~A&92xm\'i_O?zw%3QUB?=3iB;MdOe?+D ~5^*x$4roUcE]L\"R$ygy ji70$]aEf@[NC>6&TB=:^HX:DHM;lhmC\'*)%&','&=j*_NU|cvg1njuFXYaY|DgaE$6acw~+\\/`4Zt>t1g\"U%i\\Ht','|]%FdQ2*(g3Zh8u2u,a23lsn4FA;@y}x?hlx@IvlP\"Y}t<','94'),
 ('2006-10-06 22:57:12',1,1651649386,'2006-12-02',959347064,'Po$9Z|pHK=wVYP','2006-11-26','*3C:5.SHlsdfDFa8_(JGv4at6n2+yD<1u $X.8Og0qPyztuyF5oub@em/S,y-PFr|i{*h#[Qu~@e4e!QE!Xv|*','\'&TSqjPZsTL?!D3fhHQo#8(0L6BXM@Q#zVk@EuV7\\','F(eHQ&J SfQ\\b','16'),
 ('2006-11-01 22:57:12',68,-1634790825,'2006-11-14',-73986403,'>`pTO~.7m^a/@f^88Yg`$0v)D$rtGj5;hPGH+f/P/?=Yv9?<j@mdlI|26fza;p2-`-N','2006-11-04','#c4r@)1/$`BH3s\"NYf!\\1+1?18CYmt!*Uf&8FHcFNl3#:F5erPmzKGu.A$+j\'!:p1f\\M.4y^:ZVQ]{u]VdX319!-&\\','\'3qIUD7znBbFwIsT.}U{30T Kv\\u?To1_(rtK?&LS9PWU(cn|vPOJn,m|h# >qI','eExN\"of?G(ju-n8[k{;^gGK\\t{Xu!FERL(*$&`p2@U@da8={I337q,%aGoDs,\\>%]Wl%YWNcUJ(','19'),
 ('2006-11-26 22:57:12',-99,1705390971,'2006-11-12',-314905823,'oC%~8R>U&t:R&h\\Re(#BSvWCk2a%?h\"Ek3$H9/.,A_id1}icjQ#ZmP({cO}],0@mV[O!BmgY|3#+&) 9<Hw,.N@V+{xF','2006-10-29','MjhX0dwVekI$hV@Pf)h35X#,2\'O{sy*Q3)[arkxCWqf','\'}w!v`l:xOa F}(nj<',':]aZMI|oj$k`s2Tgk3wvJqKzj ~NhRB[aW|hL43F#X(g3','99');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-30 23:07:23',-108,-1193400680,'2006-11-11',-1172360970,'J1 $gl2<[`IuT,O/t*-o0K8x.&49B\\zX1b2 X=-xX>cj3+','2006-11-16','uYhO\\a.?5cj$RDV[Q>VLU&9o7LNB\\Q|lh.Oeld7n2kM}_',')4wIKECiL#My{l+]n<<cEX=Sy:eNU&19UCgq.+)\\6&:OA*:F5B|DN2/,EDYagwEd5I$S{&5pu9g\\HxY/@eqSrhkET\\j{<`M,','#Mb2eWRNn0PJKNd[MabWV|1\'C>V \'Qme1@d4aFkqdy!1M3Ag p[i~u?KkLR-KNNL1W1B','30'),
 ('2006-11-06 22:57:12',-117,1756579636,'2006-11-24',1083424818,'~\'g,\"iFxT%-]iW%!qR#Jf:-+@MW%kBNEfeS(2pi!0=Kc1XiUSQS3`HJ','2006-10-17','BD\\I^nE,iNNM6]v 0_JqLz',')m,2^4<P)LR<En 6Gsb(aE!xiW:i5r`I(P+@-F;<dwPpRUQYw%2MJsY}e@8q(\';^*&i>JsdO','b7A$8!2$g{z5CkLi/!-U\"<@tC<Wb^XQ%f--MuCg','9'),
 ('2006-11-06 23:07:23',40,304433867,'2006-11-20',332968433,'zAyjc$@7PI-YurVOPc8B6ajv ,I. ek}R d/f]oezLacEH,tI=W&`]/tT0%3;Fb;|Q,?2ZG4]t','2006-10-23',',lih=)f^BY?DW$d;wBX#>0j8F[rN_O)@LAzn@Av>MZ]x=XT`\\j\'.fG[T=Iqy:]ZZeexNZh',')x>IZG:Qs,u7QMq^P;9>g=tDT%}u.x]4_ QRt &D#3)vOA!<c#}J,_*w}','=id\\4g}?]y4;Bp.MxT!j<K{V&X>A_]N\\&-W`VS5nit\'','31'),
 ('2006-10-16 22:57:12',-89,-216284314,'2006-11-26',2146021652,'l%#F\"3c&}v!);Mh-YNO;6!10x^qw#lBZUd{X/N.0|e(oR9Ty2Byw3\"hs9bn0<\"r k:/|','2006-11-11','D<m!E2hz:B\\\"Hhyp',')\\uajDmq\".-h\\C=NO)RjRQ>^z&yN&psBoe44;o[edxqr','EW[luJM!:g9+ g9*a(I# ?RVsGD&yJDN*,','2');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-08 23:07:23',-61,-1213871011,'2006-11-26',1480468787,'1XL,%;%bf[1pqAR(jC','2006-11-04','obtP1m|3,&t~m34NRum#3{JNcokT{4Dt.#6wV<R=V8BRz!MkmfcZC`2JQ}n>(|LL+4t0LbAQYpNh^9e?`OLru~qb','*cg>4#cI[.uA=BB7]h{]BLL7lU\'XlBm$$mQIeAf2Z/Sr, yCxuc8y3Z(4','DEf/x\"\"i\\pM`ktNlDjw7xfHw','90'),
 ('2006-11-30 23:07:23',110,1235599377,'2006-12-01',-833284665,'2Yecvaw8If%}Y-m4gxGz!OZZ++,\'yxw `','2006-11-13','Qbk\"D\'+]aTU]lHbR?XXY0eu$n<_kZ~','*eZD~oh _mTCt%Bpw2G9&.VX[~XU+${6R\'n1mUcMhB+!9J&n=46(;l=N','<]`]j;/3`,-S[SQ>4xb!z{VhEIrTL\"nV9IjWrY7\"wC3-;(Q#i9s/@MS#!T0T1','39'),
 ('2006-11-17 22:57:12',66,599388057,'2006-10-25',-1570571761,'i#','2006-11-13','tg,wBTc(C\"I!VAp{l+*Wsp0f+y0O2@F\"C7[e:Y0;z:Y*','*NxR1`K*,9pQ9!sccr6#\'oU69acS;diD<v_wC,04U3(3','{OBBUXs !LpK-','4'),
 ('2006-11-03 23:07:23',-66,-1116408018,'2006-11-14',1084161948,'Tyb<=/0i9D|v9hIpaSJ\"b\'\"K:[\\31^B1~m3Ar(R>lALXto*Dh7~b\\)pQo%m8tb8','2006-12-01',')\'vB6QHq{]@z-','+?3.upK811(R.@r\"B2P','k(`$;QG<?-gB)u}:A`lob\"M#}iE1*=y RPRswn/72Kkk^aJjmC jGalg\\5YzD8fF6]=jQh]s','75'),
 ('2006-11-14 22:57:12',21,-196815064,'2006-11-19',2072833798,'dL:f?Sz1V0s>>C)oq]YiCO/s|q|','2006-11-21','O9gC7Ig233*c\\nQ>%UC?^','+].Z3)h}tg$4Nn`s!CTX!5hCKO&r~\\#Fom\"eU3M?Hy=v%J4NNq \'gTYzsk\'v','Gy5xd`#6,~3`CK<E\"I|BtTo|B m<zNBdMT7{TgPtXi)P7B-*+zcO&)qXU=$\"r\\DTy?((BF;','70');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-12-02 22:57:12',7,1690149714,'2006-11-29',-1412337888,'3y):#f3Y,]FPPT#zP}Oa\'.,?{WNEZ[]H: 2','2006-11-30','>7kF{A_Oy;\\z^/OuU3)g-1NjrC','.lOWb;*i)','3)YA-G\"N3k}7)YO@>js@c:umNx#g]Y4wu\"<u$I4(4Xo7[rg=\"/vZz#^g0:Q^Fereg58tbk>(YY','68'),
 ('2006-10-31 23:07:23',117,2058464226,'2006-12-01',-1832073936,'6 b*\\5k=<$}q]rL&H!RTYfJ$Y:`\\zD:gjMZ<0y2MXs_k*ngW](f:w,*INF<Zd~s)','2006-10-05','U 7NrBR1=.L|MLBF','/<[[h2v=BFE2#x.Dj ++src3\\n/|Tv$V','-(|hu\\/,H:;b_CT+aEqVE?%j@8jGqsUOxvnGtN-s','47'),
 ('2006-10-04 22:57:12',112,-1921115432,'2006-11-27',-651687114,'|m>OfW~[%k4T9S>\'F|txs{zD)=}nS9?K$8z\"J0k*k`$?a/','2006-10-13','[M9r7YGn}e}ZDNs6CMx6UQVdN;)o3sQ(+<\';gnG\"]b*MF1H0fcSof1@Pw5a^dZguS,eSA','0&TP]<kwApW]:#,fT\\hqG^_!uF$+yWi<pi*Q$v;/aQhMRfS\'(Hr*xt.e>Vns(hazX(R_xIMUqk\\dv0$Z!f]..(U:','`~cnKZjrH!DW^tEjnd:VZMSF4=GWx4qwYY\\ZF\\Dc13U]8nsX\\S^qo+%Bo]:\"uV]2lqAQ5W','77'),
 ('2006-10-08 22:57:12',-103,1871834584,'2006-10-11',1158653494,'qdS2i\"]}kfU3#8I.,qf3,v7[N^s%F9nKM-6aYmA\"SaXEUf=e%\\+=1(wXgI(rsKQ$IAB7%*fi*ce%8wJO:e ','2006-12-01','XW!Zx\"Hrbc*#BBO(HalXt/d|^nM','0MVMX Sed3Ls]DgVqV@;~!c<`-%~/>`UZ$868Km\\q.Dh%r[x`C)\\|iiwH| xat2jM^imRTshr,orep>DInfj_-','s]D_c90Z,=N','15');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-07 22:57:12',8,-1300759609,'2006-12-01',721377933,'{(Of(TB\\2n+zLQnsMeb_a~N7VF`','2006-10-20','&=7A}G;V30HfjS)Wgg}\\;ywWo -&Q47&Y:K7Sq=mgngkN','0p;$\"zgKqPi1+wS\"#Od\\07i4i&Ckv54Kv4mNfE5<|>za,G0{d+A\'8!Xr]E{.b\'Za0We<:=v;','&Y>Pwu$2,;BIz_}o2Wb[!9_dI>@,8f&aatLU\\U\"zX1Y2Tjay4>','87'),
 ('2006-11-01 22:57:12',-124,2031944742,'2006-10-13',1407648724,'eW6','2006-11-28','h<r`Tn5$(G*m.9*K:cSsJ;(>Ll(2qk/$eAi4?hHArz@@k8g[vd;!T_66\"j?z\'VR\"e&z9','0sj7oVn+7(jMU','Qme6I_gfAl/~PTik[Sv4]?zHr2i>v*vN^|Cb-kpN/@Gy/KNEf!7pfAp@mI>HvBiT(gD%','75'),
 ('2006-10-30 22:57:12',10,-542091959,'2006-11-18',1539729471,'[\"tGPQrUk~x!vm?Ft7/h~zYU*N>%ZoT \'x_!CmB.bx}K^Q6__F[-$D8hU%bLN!>\\CE.AtJ\'EeY*O=\'|+Z0,vfT','2006-10-21','^H[jJ=I{~g4bn-}~_e@&q>%;^&d/jc.C9 ?R','1:m4R-&=R//OzHx9%h)W,1S\'yBVikJ0q|(\"M\\b\'fkiSL-m/Q2`,y\'71!u}R!.ScjNP?6yu:(8xyb,EI!@M+I)kQJ38','ifk<:C)7i]}J\\U}5e[7g-\"~ucOn48=wQ21ozc6<pj/Iku:,Ft1VxO.*dXB,*','6'),
 ('2006-10-20 22:57:12',65,-90794122,'2006-11-02',1448797860,'mjuY,vSiIW6REwlwX&&v>}koE(+=^i`]2_0$40r)7s','2006-10-23','-L^d~fQ#vI/B<16','1dUMM+p&PZp&Lw.hPVvP>$ H@<^Q4{[d\"q)HBplM(170*`v[>c9lHu( imF','}2Da7?[3eRvkfQ_ai]^!!Y0K,\"Uw&!7V&','3');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-04 22:57:12',120,1956466098,'2006-11-27',-483913088,'*~YKVL:PA)dju5\\uegr8','2006-11-11','4\'*))JaO6u8@yZ*w[771f{[=\' \\kdkq2#v-m!Q;G$O\".WN>p_','1K5_6z4?0{1wq1jOW76_oJ}%','g`[4xXnCX P@Zy1_0U{&A)9&2uC9H\"%aAv{bf;d@Ox`&Ulo3EMB\\k9n]\'zwopODDf','24'),
 ('2006-10-10 22:57:12',107,-1516503109,'2006-10-05',694541665,'~J<!cIMa3AHJraz;S{`s`,k\"j{LtI2KijO]jr^FmO957J.nh\'bqn)|*#4p;2o%cI@2Sq(d!0&!c`r/A(','2006-11-22','qJzn^(P;Lxgzn,A>tG406P/cK\'E4F=i:clI<p*z!<zM;U|f2N-QG<iyKHn$$a85xh8G_ja<GW5fxy^^','26qt::Z$)Ig8a*pj_GJ?]A}7:wU)XQ.WNuK`xH[MyUc#PnC=!=E}l\"jW}?_PS2DZY33L','Qc^P-pOO2(2|05;C5_c;&N1:@Uf((*~bpC+VZcASs[k;buym9W@]|f0d8Ohb+R)\"k74~{<]Wp)F+WSOU{}F8W@PU_s-@?','73'),
 ('2006-11-11 23:07:23',-116,1292620564,'2006-10-24',309618962,'d2([N*)d8fqtpA_TZMMBv?IZd-\'q0\'%A/.],(V?pV5?cfBd60\\[Vwy^HpA1QI`]=_I;2-E3jZN','2006-10-22','3=lu8nA xoRhoiDV&7.2`V*p.\\q-Mv\'*\\^m1n','2uf]jt]Z]9)omYT4 l4K@|vkmyb/Vlc_;#:42cA}R~n\"K-2:r=tRD+n<\\p<hTJ9m8$LG{K F','lOS>J`QMk-4bSeC PUC<3o&X`tXk9\'b\\GW#hT#OOnbFT\"Y0d2l|IT^,V','27'),
 ('2006-11-13 22:57:12',13,-1790725117,'2006-10-07',-2057717655,'*!@zuA`{X5D.&]!,z1@RE}aAT_Sf%io,d/]iEfRrrE','2006-11-22',',x?ZN<2oL(_x^oqhvJ+;J[\'u@p0dsT;5&L}]D1+q5M4,t TYy?}FCG$ys!d^%\\r6R\"?50=>?{44$1#3@$CGHdZ1wRfs\'y,<^','2_ZR|SzMBC-9EL(%PNF+2vcz0r!w',']Mbf8,QD>5jcxMg}66Gs5NY,@}z2J#;T/#k^J5[ezQ}dZ]*-bS2kdsU?J6JDq>8aI 6+RD_.M @Vetgd9%.n!pl^uqT!tYw_:A','85');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-25 23:07:23',59,92649007,'2006-11-17',394186421,'wcc$~NrK=E2uaEpZOpyJvLMKw:Bj)[Z`iP>Efv)@$Z%[3\"@~m','2006-11-20','li0OH?_a:Y/1HNjy:wwn/)OlK#3IWE3^Ddi+CKeVo>^~ n2Dj5fD),4>6~hD^w^E~7i6@JG4RG)`>:^_w1Lv|yiBnO<Bg[h`','3eF8/5FTt}Y:DsFe)N&r^SwtzOHYow','zl2bjT`ZbRLxfb:1w|3d6@:QOY,Ivu4*`i8FdU&3>znfR3;t:St(kG@xgqE4+|rRjJt;.RAlik`\'8gJ\'*2c','99'),
 ('2006-10-21 23:07:23',-88,-1679885125,'2006-10-21',2121831009,'\\?v\'Q\\l07%8+(KPjvup|m^Y Kthc_=a:[Uujxq[&\'\'XQIa@_Ld}f}\"/o[Wlxh(_5dgtFmU.d[[#4^F','2006-11-12','1abrs~TE^K{mjQYk-v9u4iju1!aI=*J_;o[5E','3oY\\6>v9;p]ZC]GlQW&b~C`0l!Rl53c[5NS?%C%p_zM#CL~=#~Z,l.miZ0jxZ9*@uSn3nxYnFGxB`g;\\N,wBtUtJ?T}c','nykbLsN}c|1fW<~i%8bb=XOC_g=MT31K!-,d\\-H-L}8w%[G7NDV3_:~tA/wOd;h(6(}\'LP:ho/<F','78'),
 ('2006-10-23 22:57:12',-115,-1642746580,'2006-11-07',1911977322,')G/-E|c\\h\'0tY??v% {3F#Pk[0l$Og\"mt~vViYks:\'~Kx%\\A^Wm@@.{9&zJ22H/KuFAr >yv/{>VJ98$=s','2006-10-14','D6A43qE{Do?$K]7)I$e\'%?5l|Pl:F%n{7<^fLMFS&@5EKWl^aD~id5bQ/KTq','3r\\!~!}Bk5{%K6mE{^,dScCw&apRxN)17{.%Zl&P-Py\"mJ-=GvGqE(:V_\'^Du>sO#/9()T0U<v\"K3^eoO)^bJ','v[2h[rLIQJ=!!.#!L:E}O\\SBI0\\994qab+9Q.yszx;`QcY\\1-O{9Gd','32'),
 ('2006-11-16 22:57:12',-108,-68876507,'2006-11-28',-1266538821,'LNQfK[e@?z7(-C4WHuZ+%Y','2006-10-25','Q\'Yv;Vz&A=vfq\"7Le-hh5qk1-aQ$/*);XrqYtQB_4&95x/w2','3xYwTF/3>1z-aK%+','e[<Vi@4!3K2qjtlBMsV{ AkmHx}Fm9Fs:$eQm l%W]rCqSzjZQ5/K}FSL+Xg~5K`S','83');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-21 22:57:12',-94,133310808,'2006-11-08',-1455327818,':hJ!#J{+Y\"Jj,,dYVq\"(2cFlRM0i<Qj$Zm~[]&geKpe,Y6i?;!%]8zE1Vaf()3Iy^7[7|<gY\'L=]nd-{A_oo)z;-EX<c*d','2006-11-18','5coA@w&O>mih\"Da]l}L.ouJE{2`OUbb&I\'C0nmnO%','4?MJ)DM&?c{Iw-VcB&A`4FjVy/?g@|&%9<oR(Kwd:J]p5Cw\\ute7-r@$`KGp|{Jvj@=(uBWQ9K.qsy08_>D:[SICnVUxs\\|m',' Wu%:,*>2c{1p6G\\ZLs','81'),
 ('2006-11-04 23:07:23',20,1802615243,'2006-10-28',-2002286351,'Nf MC7`@RG6{G;h@aXcEwhXk~*m4o>6Brk=.<gWL','2006-10-04','','4tr~CLMMYjfL:II_?*:L$W\';eC\'3y\'A4cjYR{t atn_D1z\"CIl7okZ>wwNWZz}f}cf]<QHI|G>n}T\'L:!_~TYIm\"rkxOs','U','32'),
 ('2006-10-20 23:07:23',-44,292071190,'2006-11-17',225406276,'P1>z1AlH?OO6)M[y\\#,)j>YzSYIBm^0m?G},ZdoeigpyXw-\"_00Q,G##Q~Fo!n~t{|av3#(N].s)\'p','2006-10-14','3','6*RkQ(0<Sp7Sqc\"&l\"&>=-:<Zj9pbR\\S|k%L@@4MbO','aXXo:J','38'),
 ('2006-10-04 23:07:23',-71,1975345294,'2006-11-28',-1559737860,'K=Ty(+ZqQn{^+Y,_ ','2006-10-23','&;Lz0HQR~w/@L^c\'@aa8;?rrr_g6]$QB2+}_#Bl,^7<;2XS9PV:,IME/q;R]RX*W ','6/+\\j\'SBhloL-ch=/Kg!&!q,}?Xy|HS!vYJ','An@Z`Q}1CT?zsi:3','7'),
 ('2006-10-19 22:57:12',126,815963363,'2006-10-22',-429820343,'3= :xg64kkZL%hE+<CoDtz</9(','2006-10-09','?glB>X@{G\'YSufp[8D7&&Of\"+tROA1(CR5amZxL}LEyi%Q++[|jj4Zcfc^^m((Ytl.A}8xh oAs*4(&!nrJ/WRGPW\\m?tKV','6hFE8zb!I3\"GI<![U M>CEw2lVuI7FzsRLq3a0Z@{p@oF0}n-BO*#w#1<c/;H%  Db<t#<o#r/fv!F>qc= c#U^sI','86x2tP<u[4=6=_LD\"<#5$<MzJT.1V.:.9*HD=wj!`','49');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-14 23:07:23',106,-1821241169,'2006-10-16',-1774738123,'lI)Q[]0wZw1VUcXY#R0SXG?dHju=4|b-yRGe[}A1ha1~U!|\\8&`o?FAZ2bPVkA32\';<rLOG{S2yCdVt1eON4wV}!pV_3g)QCar','2006-11-19','T(;[9{HYgr00S)Ml8Cnm(qxz&|rn{1$\\l8g!Up;fEI\"QU [EloA<v<Odhnw_u&DE^%NK7\"n&G{$$C/$PMR|9S6\\nXa~5','7?$+&_N!x&',',5VEB\"Cg`xyPD]#;:\\nNh58M`}s^]1H]ixqf1|uYF~V3oL3~U+y:$av','84'),
 ('2006-10-13 23:07:23',97,-1681071546,'2006-11-01',1416148212,'HzkU^f-M|\\SL>jfF|&4is^E`;Q7DqqLX1JMQL46m8EYB','2006-11-13','1Wrl$R5}Q<{1(4lO6v!dG=2xi(! 91T/08E','7FcQyE4jU0QV6]<\\v]@DunIro{','zhX^ll7r!kXWma;J5Y=q<t\',NWfT\" 0Dh[wgl\'o%\'C6+GC ?{\'','9'),
 ('2006-11-18 23:07:23',-68,-1762713263,'2006-10-24',804361479,'Dp1SP5t?@*9BW<\'8GC3o_ALV[M9EsW5,\"i*]E6_MQI9PK(H{6UYg`_k(Y\"wf/?~<n<QZ#.]y~t}^XZ2Xlhl)\'MhgL:','2006-10-08','28kw,C/Kxx.@5[mCT2%}1-R2Fw=\'le-\\KqQCKvC;b]]T11j<;gpO','9!Q[NT\"|DB>*( .v[Zqoz {II5 ya}!f%qo{WBDr,&RqpA(','<Y%s}x?Zt]:9A*\'R^^[m\\d@B\\GD&I5wt{CPXy,m;m>$l[J*pME%l&','40'),
 ('2006-10-09 22:57:12',40,1554591022,'2006-10-28',1126712732,'rMbO_[9<GEY qnrJ;LxNXRWHfkG]I%f9 I{>>L<RW/vvAHU_ -\\3G<( fbI{T,*6]/','2006-11-16','5m$QIlY5?6N(mhZ%%XGNR({sU[h:-sx{D\"9Jc>_3ota50\";W 04X_95(d<C4U:~<7h%*>g,Lrig70^10&Oc{OW','9cL#`\\|Qdwn}H,-!D#+Ixy>0zUXsB(K(D+Fq{^P(/_=+AUv:<]U6iWHe/4-e(E?13Y@y','\\kHEKX<\\(b$L8_5&sfy+q.WRdj3p<aLAYk_~%r\"osPd@X]m)TiTTxE:IKj','40');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-19 23:07:23',32,692489371,'2006-11-30',-1083138239,'=CqhkQrFHzaWpyaL+!jxpOD-P)p|<)$[1:f','2006-10-22','hYnQXLy<FoV\"jir4}{Ge81f`siA]Nc2MaA=@[&{q7;NKIQr? XMJZ-]cc|03@nPBrZy3dVSTxKx',':$)(+FL,uA$s>Grg#rZ2U_DW`18gDHR0~e\"','Q]q','56'),
 ('2006-11-19 22:57:12',-102,-122881495,'2006-11-13',-761953761,'<H%pQ!g99*?n<(cIKH)euc+Tn+b2Y5V<_-4mRi9XhKl:p7)e,gr Q-bp>tU|^/Xb601sCaMfYQ\\_8','2006-12-02','Qx:\"?FB7E!!;zyyQ.\"pB3H(6ceDMJYK`Df\\c$[Pg ',':2H/-Ey/rO@CA.s{mSvV )MlK&wf$=L3obck!L~`94X!.lT\"mXwkU,\'s4wa}k| J}XJ2&rLREsBN%<7G}TnD;moE ','n50t|.%cjH*2y[a/|,J/QnT,@&pg4^8GJ<?>YeU3bYXg6gD`WNKt0W-:g20M<:','8'),
 ('2006-10-17 22:57:12',-115,1103270796,'2006-10-10',-709364534,'pN0%Irp){t $ugM~8f6s[IgTd%SUD`q]vTil<,r+EjG|.vq`<D8','2006-11-19','Pi>$[V7z{btQ9yn/5I]-gQqY ?m*G42hG?0pbDVh7>VG9#JkU-PiS%ve^#eL{ohP,ij;(,?2<3P',':eb!;<`P}P-O2B{ju)~$4VOw]^hiHRdd7!Wq`62KIfhxQ ~U\\)+c349E^5.vZrv$\"^52[Qrs+:S','$#&\'aLb7&/=w+g1Q8R3:1\\v6W!dGut2~W;6/E}I,Sj2<x 3bcu>Erj%}\\','89'),
 ('2006-11-20 23:07:23',40,-1966631032,'2006-10-11',37795942,'w1h60t9SD\'{Mo<q.spQN^AyKAJ?!Z~bB{+sr$8=dIJ!C|{@\"v!Lyesr6sk&W~YM`Ws!^{s%ujscOMEiXxS:RhTNx,','2006-11-01','5_-abLAR>B`AQRmP7j[\"HsU|$&x+x`Y|gKScRafOX Jz\\biaSe)[ mk\'1K\'whLNH>[[SxBX_&j@/]-',':Qw~-ls y|`6VACGFf;X`(EevPA;I&N$]5N','^Wk:c[aI1W\\v:7UCWrqy0%F{#\\#r`Y','28');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-18 22:57:12',-82,1404114335,'2006-10-10',-405495643,'7rhV8@NO9l4T;k\\OmI~Qw(XQf28=DwhJ#@:*)k\"\\3`@6B7^y]jkZt9v\'23>ncm1Sc}~6.e<VMxMGkcy*7aHn}}','2006-10-23','mZsm}pyP*K_\"[#0y}u!~Uw7l@m+&$A ,+Mjl;[w}XK*DAyC;BA`brx^YJb=/W/\'j|0;[t*g[C_k+$Eo]A',':ZP*.qWL#::=#&G2uA<<Mr=-:UP7\\u%|b*gb]$v:B3Q2fzE :2N|^(#vP3pcDUu%uDJ>7>oNzcqr2}]j2?$IW6&','_N&1%kMXu\"FGzuI}$E7ZlD.cM8PeFBZ9Ue]DxBYp!E.S.S-Mi. 7!&~\"xAa\'_U-S+O4Yd=gwd*O^\\\\s^7','5'),
 ('2006-11-13 22:57:12',49,1293222565,'2006-10-09',1526576699,']zx1>;LnU-b_?*\\ -3)@KY!V-qR7xvz`0]R?:G{qi.e-Ps=(I2$7Y,T4Dml3! ++|R','2006-11-11','co|<uY,g\'y8mCG8te@RqumYYzE,{#Z\\q%TyZs-<T]/GVAl&:JC~bDc.i4\"c;pL)d:`7)7vb<w_3nFx/7`4e+fEbI?]HCO','< hWZii!t0[=r+;','mOk+u3&gYbbhbYs8!+Cv(3E\\PfFgL2I1rY[ZyZab:%b\\gQNt^BFR$b;x9wrBuu\"e$KK]`).5H_>]jZ].v[\\2','20'),
 ('2006-11-07 23:07:23',103,858373669,'2006-10-12',164627899,'','2006-11-29','x-Ik:)kmgtwE*+BC9I9,T(E:f%','<P7WZKX1WE1{{2C2g@YcI]VI*.`nx+\'#x','sN5gu6v}LCAk\'>gqSduBDAk4Q1EO8^avASpGZ6/+','10'),
 ('2006-10-13 23:07:23',-11,-975143287,'2006-10-26',-1557368961,'F<[O#lVy','2006-11-25','D9Vt +[we;Estj b~ZK\\\'-+;g2wE<gwG?ms\\pAne3OpZ','<rC','|*__','4');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-19 22:57:12',-126,914894354,'2006-11-21',-1945699360,'+$exbk?4So6n\"pD^Cp|A#G&\\<\".7X4;[S(#bfh?V[2Ts0 \'), T6vCrJ\\+_^ozh9ZabEQ&S8G','2006-10-27','mx<0PCpi{<Snkv%W~$[LU`PV9d(*lv;pN!ELdXbnDv=:0|)WJhf0\"t)','<Vh%0A#^7%-N#j$({]LT|p{MNn~1,n/-jU:l;-s[F,hTi(x?0)L>>1aelQIO&qB1YX','WPI\"q;S;YUN&)mk<@bC? 7RGA,3aOkQao%\'9Bf8(:|^8%]Goy\"~.DH\\6,Pd','52'),
 ('2006-10-13 23:07:23',-51,-2102415132,'2006-10-22',-1389522334,'=(%\"','2006-10-20','F$.F-v7n&:0{i}EhjWh7gil+H)lHfw}Y;\\Asdzkrz^7spaDZ@P40*rqE+u]B9t`t5uEKs;7Yq$du4D/x1r-\'B8:n/fzp,(*GA16','=g\"Bn>RMkfk%*e{#nFn];Yy(=Rwm /Q#40[J`O67q(#_YO)&#R ZjZyD{6df',':g$b.?8bii$\\\"YrUOfz4)94bA/azBJWtJ4R','66'),
 ('2006-11-08 22:57:12',-125,-225649002,'2006-11-05',-1742415676,'mOk`~:J\'O5\\#&2\'tL2lX\"tLx@[^llp`^p ,E-8H%','2006-10-10','e-QzHRrlf2~5+96Er$cy[0al){FQ!IK/|#Y* T|J~sZfp|&AV]IXP>}E)uC;@1+yMmV;pAkFj(','=wlTLKBdm:\'R=_-VY?CI\"','G*F8 N{Ge\"WIa*2lqes/(D*C!L4j6GyhdFWy4LG8%:{A^rTF`(>i[j?t6Pa8-l]cK#9p><\'je*\\Gw\\\"d9xz}W.V{v\'>3Z `9:','42'),
 ('2006-11-19 23:07:23',43,-892508318,'2006-11-14',-1269951312,'\'vsJ!.7NM5Q)xs=q~z~qqzm\'WA @:9JHD@VY>yvGE&~a_[','2006-11-25','&_?j7(GxZu0uh^TW0&_/{m91nos]lvlK>/JcHo.IdA]}oj# k![tP.*y7<#cmFHJi!}4)#>Iqt4toZU2Msg]<rg^YTa4?o','>FQD _*l]Hz}R8)','K\'0K??\'89u?08wNv0i|x6~!\\=8DOq5PaurpP=NYT*yp6^D}D|Zp|\\& wok3~QXuw2U)^iB2GTE_k~','55');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-05 22:57:12',52,389725455,'2006-11-11',1369406101,' !W@9w%`~5z-}4!z8kj-QC}/M.}riiD3\'`.&oo/l\\H!J-hSr:J5$c_OR5.nOCyd*?5>,/z.ma_0lb&-8?4\"]_','2006-10-06','q/@','>ZRxw?_H*I:f`LOK=FK52w\\zf6U^b\\/(TAu|gJzU%$^%Gcat`)RmJ1',']nQouPAXK h;NPu$ov*;ezX*fOTh~DO[-<hW~uIT0^SXc_k6#S(*[\\ivO8}','37'),
 ('2006-10-26 23:07:23',-30,1839838915,'2006-12-02',-1384252119,'njWLZCc','2006-10-11','Ot!.8ctOMlBw@CR[}5vPe7nJ*V0\"ux3h_xb6r4nb3lX{nxBp]F/By1_BtT%K','?-9~JVgA*]VMyy|wj K947>x/fg8dHtcjM+Zq=S[/hS\"5OyI/O*S[3w$','eLph4.|','89'),
 ('2006-10-16 23:07:23',87,-768370425,'2006-10-17',-1625049651,'}1IaF3?Y\\+?tNz','2006-10-24','1-Z6u|.7,uK4/y{2]HBW4N&|{D)vW=A$|N*d[P+|c>T^}@i(|No>r6o\'%}m{X,a<O}K|*)ezmr*8T?6\"I','@?yEb2_F=4pmd8[hd<4|O_=_?n3@xcj=&9&{,S9~[X-=b&L;\\4oH@WpdSni',':5Qm~x$ ;)ss`o1jAFCVGrpW)jp1KW#\\0BI}','35'),
 ('2006-10-21 22:57:12',112,360552667,'2006-10-23',1073402241,';>^HA%<HM0`rAqv\\:0o$m^*_~6;#7t~+tH;lMC\'X$o[YYby65m]74j3.`Pz(Uo3X1_Lwf','2006-11-07','y\'k7W\'p2R<YiOO@&?Wnbvs','a>`5I ^+6iX& o=f:?}j^\"1;sOy6Bpo5=sBf e7][hb>U]v.5,`FMN%_3uGkxz2yYx=8nO!;7 S u`{5V\"%pje4lRM<57','KiS=4-Hn|]Q(,h(gHN!H|q)f7U=j,.haKh^QvJCJ-pT=LGhZMU(rG','10'),
 ('2006-11-24 23:07:23',33,488206802,'2006-10-23',2106366368,'crD#tzi]v_Q4*}t~fQ~_(u#esP:','2006-10-21','$`Kk\"gFgxD:-YLJ+>cH-?!zqnBfprk$gT/{_Os\"{:EpmdSO1mn%u|4P+`5uRk(Gh0Op64Tvd?t$[7jdHJf`Uci8-Ce|?i.2ao`(','aLMH[G;<et-2.9t(cl-KH5Jr\"68va_A}%4Xu2 j\\x6xGcC[H/KumT&go','g=QJHRII5eCn%','23');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-10 22:57:12',113,-712464496,'2006-11-26',1824819502,'wIjvrCtv>OoD|[[k\"r}Z0\"Np{:Q\"~%~*$zUml)h,_>+V5pAm3R(%^*oL.>}}+J2tASMhU)YH','2006-11-08','sO>]E5[D2|{,{I;r6UIsCR/IGJ','aw`C~>bci<2kI7R!*p!Q,ESaQ<=a{x(c\'] Wnvby@WW\'pyM?|wg@#c','czb8yX:IYC2p-b6#\\ua&_u[%O&EY&awG*80i|LeBWHq<40=Q=!\'V$C#_D[!KGxnD[Zrn.f','27'),
 ('2006-10-15 22:57:12',-81,132126886,'2006-10-14',-693631916,'qm:G&(N}.T`','2006-10-24',')M.\'!vG8x[flW1/Rq\"`Bj$DUU#{CRcuzYy~1[e;;U?fS\\,?m.Og=eI|b0(A^QyY-B&?0\\ZFWAR2d0q$@','B(bsZS)01~]w%:`mMIpOOspj?U6*/;~TlpE9_op?mbGMwn4x]x(T','5wzx{2E#VBWcPBH[<;iLXDiF5wg#9nM.DNv@np]5S\'Cmc?G<,9>`U?>ZZ)mX','57'),
 ('2006-10-06 22:57:12',-23,1349398188,'2006-11-17',-933815574,'y-4T{_S#9j%4_j3%1!;B\\_%7k,o-b_@=UrB/}*e)X)|jP$1^|2U','2006-11-02','\\Y>Zm6','Bz2$<B?`ROl\\*}?$v2w\")$\\W1|XlB@$8mt/V;_/eg','`R<)P0Pl9&jmQ,Rg<D;C?Jqu575cD8(hLgcVm(k\\t,eZd{|`/gex,S)S){H;yH@UO4zrPM$U\"+:J-+c+tY(','0'),
 ('2006-10-21 23:07:23',54,-966523305,'2006-11-26',1770185373,'Su]M&','2006-11-14','z4+Y\'H8s+hLPjLAmq}q\'[6#Nq*T3 $uvY2/+fIm22IPPcCBL9vxIYtdC$O=+a,O?9UR^p|^c!ccQ9@q\\6QW^90k','c$LCJ>7a[4L6b31y8H(4Jhxo^Mutn#7.}',':~JOh%w>d!2S}`S\\l<a4j5\"aT`` /g.5(f1Y9BZFVYPkf&+EM;}BG0H./\\T0A11mi9*$E[EZ0H?-wj{7;','14');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-15 22:57:12',94,-598985509,'2006-11-10',2122845569,',SAk6HfYys!W]YvYe ozE};67()Y@Sz.}-zy@N3fz iD@O+_WP@]OAX@ZCiB','2006-11-24','#4`x,(5z F{-y|tz;f\"h7&):\"/f`=rh|.J@NY.k\'\\UPGHy8kmGr}SJv9ks\'Wn^<Cy%Wm!yS/D&f_X1O2[paLQ0a_1P_a=/','C>x{QzeIlYC>}>{ugu8n[4k&TR-.{0tkoIwb09:?LvOkvh/>`\"3+Rx3:2MW)','8K.MXjuk|fP|l[EHggXK8&s2xT;NzEX8pkcm|j))`=kw1[HGEQU[39#q3):i]H](0ODsb\"','50'),
 ('2006-10-07 23:07:23',64,-1101232592,'2006-11-11',-1721874738,'YBVZr8sOd~GZ%\'ddx`(gpU|\\LSK#T9NhF\'y{]TAYgj=faWF>o2Mw_#[M+dyo^A7q4l(4lNj?8lDqR>j','2006-11-21','A:;2PI3U=|[y#~T??z@u7|#ypI5gD2=NiQ=SqbXDa1ymu<)qZ,k%u','Ck>H+U9zb|4uz','o+[PnF:kncQeg|P\"F!3.<To9q:[\'Zo_{*vW2A','70'),
 ('2006-11-11 23:07:23',-56,-849386687,'2006-11-24',-823818889,'\\e9Fm+3r\\995)Ug{n3i4ZOWBi\"|_E$,1Jy>6[Dmh7f(gWhH?OS!;5KW([@z_AG\'SfAX*8kS(y+BC%5x\"&','2006-11-08','\"L\"5p)n_54&.d$Y[Z}W|QFt','cva6y9}oH`mp$tZ=(1qr+ok5]b4]kjE//ZWtOggOloYi7~pnsX;6gGIMI}=1f',' (!r\"tf!I2lUDA%tUv}w[k/!ScTf07S6AHsl9Q~,G+Bz0Cl#_g','15'),
 ('2006-11-22 22:57:12',67,-1184885314,'2006-10-22',-433357908,'rv[prn~%9towIl?{v.:RH>VeJG Y 8D>0vEv@[QI\"5 z{-ODcozE$*}amkaD|S]Q(rQZ','2006-11-13','^`wO9As^2KZ\"dP?&LHx\'(ZY.D +UCT9! muZ^:]t#\\w`HZP{{6.#_XV;G \\6t~om,_','D d67gpi)Dqq\'R 8c!sVhr.=3^Z|xX&waW\';y:m2+u+eY* wUNmO9:xx_Fh84~FgH',',?D~Y0C!W0C^L~xdurje0`0USUqM48y!z,KSG0(9_o?7_1:U<|`V[Fu47f$W =C+>!WPxyok#<OBn!{!w\\y%E5:(Eb','44');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-06 23:07:23',40,632134200,'2006-10-24',-493716586,'Ao}','2006-11-29',';}AEfM6,zj T/s_/fcX93S3<Y.]wlk5a?$Pn\"+\"@;z-^wIQElc-%qv}c&0^eQkfd`UMYH{+dY^2Vz;<?ms@As9?O+_h4rqf','D5h~qjpQ_bTm.T.s|l:Sy7S\'U=h[~>[+ }u&ZpZ?f`S4','U@QsOb>\'eZQ!]$V\'[zA)2\'+vAN3gu6b^0r<tUp;#Nexn\"w!153MM^]ut}*Y&Hrh,S|A}T16w-t','59'),
 ('2006-10-25 22:57:12',74,-168843292,'2006-10-20',1610920290,'Z9X\"|Yl\'KO!N4jp9o5O^yzPHqNM3tkUwR-0W`T#GPLA','2006-11-08','W/?%4l,JO$7I>bsOs1JM*m-;ReR]+Ii^?c#4G0}\\Uu3vTXKPyYdh!TD$#6RC@MR0M`nCZHOtKNz=esuJtGg(Ee','dZ%ht1dE.O-b~:gF\\~X8?bD/g=8sy?D!?Y:LGbFX(kNH(HW8b7oycaCsP?~aW9>nmareLXx@bC>ruQX<Dpp,\'wZ\'5\\@iF&9y3 B','yvY}:.He.,6U!<2Wv\'[Q(\'n3VeecJDgi43[ZdQU?s@Y8hI%','17'),
 ('2006-11-03 23:07:23',54,-392310461,'2006-10-08',1207473065,'','2006-10-29','wW?(HcQpIUjTW][taq\\jXrp %=aoVwj AIr?\'+Q,|6Jm=Q[2ZuC1qb=*[5^b\"JOZl!hi1)@p$','e&5;9?&g7on','9Mko;\'H^0u5 L[?UiP_BS+fN51(U<XIrwRke','85'),
 ('2006-12-01 22:57:12',76,336412541,'2006-11-11',1945462611,'RmPzEUmfOa+4)TI','2006-10-09','Dh{;I-D\'UR}!~Z~&;; {tF1H','e0M_.5:I','=1i4','14');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-14 23:07:23',-44,-1783914562,'2006-11-25',-1457785428,'Z\'GIl-Eem<2FQH5\"!}*&Bm4ziT\"f5?','2006-10-29','0b;d*eM pr!eh','e?Efl~bGxAdi&;Yt8fzg/\\w9N`','.>*Fm! ^x8]iQF\"PH4R?ZT7qmbfn9DqR`^.+[*$6~<P03&03DJ-zV\'_tfK^C<Jjh\'d<K=F2-k_','3'),
 ('2006-10-27 23:07:23',-62,1130424349,'2006-12-01',748281587,'{7]Mb&.D3;etprh%5Q]6C1T2/XwA(Z','2006-10-10','\'Jzha\\I 6Pb`NWYSww94qQ{@nEc&&dyS\'`?u.3','EDcNeiptSK&,5-VyWt;?;X(nR(BX}NDC, s_9','e:*3hJDex!X.IEoqe:CL,LC!b ~9]fao9xWV@I}+6f[gO^8fJiF*{XU@p:>G%=t','87'),
 ('2006-10-25 22:57:12',-3,-1920758818,'2006-10-29',1119290572,'=\\d8ZEJ-r78d0V&V5NIizA=*eBj]3<o(.<|hw05|[vj8*|Fp\'q;!-!+Uaj@;d6|@$2Lpc@V}0','2006-11-07','cu+1W@MCKAtO{`U9)lnuI]}?L^WpA:Pf0~fl\'9q74Q6Ulc<-_D.L<m9Lsqexvu,1m;KgmXLIoiI7C18tJ!+a&CSYxE7.58','f 1,^zP{ghQUz !-7XJdqNqcg+[\'<l(trz-9u\\{q8^42U/,fm;d}&^*RuX`GQ','.`1!A:&0wbeB-jyW\'pFs6|iP^ubx/;7H-x^?%','56'),
 ('2006-11-12 22:57:12',70,1269532810,'2006-10-05',871731608,'+:GDeSidNeh57jB\"S|Uy9vkYK%x TvWt,GgBDC]=NHg-B)','2006-11-25','ah m9VSagUUn50#DT^$(Ceg>YQoXVp:c','fAKHgLMP\'8Yb_av+|&_ic+YP=qiHld$L[#}Jj7@)[[Dqh3Zg3~!RVm','s[E>','67');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-13 23:07:23',-9,-1533142987,'2006-11-13',344936779,'zK (\\hccM1^7EGNhKL+8_!S5:QX_pIX?9+>|;H2wv{TONjVYq:aNbt>O%`8#X<2\"nU*b]]','2006-10-30','vxC_Ud^kqHgwg!5-.i\\.yVG``KSNf p%!j<{Fd8+_{M L~.cY<a?!','FG :kO__Y1','GOsDy@ST\"Ner)bJ','71'),
 ('2006-10-07 22:57:12',92,-757011066,'2006-10-17',1197461044,'d2?vj2h9h/|wgF8!lgTs^#@t],yt6[hpH#e&mD+','2006-11-04','x@|DrGbX','FRVEh-!M^=iVHLc(>Ds{t`<#3oQK','[I\"}!/,[i.b#apuq4Omxd\'1C_8u2cv|G.f)yGt%','69'),
 ('2006-10-29 22:57:12',47,1946379309,'2006-10-08',-2111144829,'>ojNEsX&*#Zp*0t=>SZ=g(_{WCI?ZM@Rt\"`7dNZ/g&EYSz9CwgWl.M},V5l),M*17hlonzji,Y:\":\"eU\'o[;lLM$\'oh~uoD&D','2006-11-16','p|Qsj\'WE','FZ`vCAx\\FW)77gLW/eLZvtOwoJNxU^D^tCnh{%ck\';U+@5V!\'lyY$=mNxzf_1G^SI*m','z3jA~>sj56%c-]+dAV?D)@XZ50{,Al\'|0^c+&YS,G/~{<c\\IQ7CC&TamUpF;/Awy+>h','65'),
 ('2006-10-09 22:57:12',11,-296810257,'2006-11-13',-304749707,']#?+XRhiwM}F-PROF>5aIC','2006-11-28','GF(E,^@nB;P)YBxLHAwK:\"d]57Do0 ;hV]?tE2b88jnbtga}5.U0+E43','f`8\\T6:S+-pM-6Itr7o@xA|H0S+X9uVpukEitZ(~H?3:K~)bR.Rd+Zj&{&JekY\'Gwz;E1,2[>w 7n|','8omuk_(1*kOW\\cHW;pCF,?P!Qu!^HD=fN.F@DE\"/','82'),
 ('2006-10-04 23:07:23',52,584759316,'2006-10-25',-1603221998,'c#-T<k/<8(rSstIlD$~V;mrg6S]Iw7(_sdVEqpGE2h;aHe?g8|vWDl&I','2006-12-02','!ehu>E_YYr5dv<_:#i)XTQ([/Xl\"j<gZHKz)i;iEF `<Ck/)ZNAsm`p:{dAcekNgt,)zow]vU2`pSY','gFp(&D1FNtNA&`BC,V\\=ET5q4_{E6>JP]!)y\'3\"p','X<XcU`~LGD^(5MWxW4ejj...Vq:mbG*<^/RxE','43');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-29 23:07:23',96,1141210608,'2006-10-10',-866879346,'`;d`75#gQy&YO~FYI9~cEeozd2wG','2006-11-12','@i6e2?*Uo&\"|H1+*$%2q\\iR=OF36@}d[#SEj>v\'','H#q.8~qTT\"X!25,CVe&JhL=+k~zw;fhgFw3w]`qV#RZ%zy?}*VKn\'lxYz9Sg~c</k5[?oxClYA%Eu9o!\\plFw(,n<^ !/M>','k~,:Hg;9','18'),
 ('2006-11-26 23:07:23',93,899048978,'2006-11-25',-987073568,'LGoi.T1B+ITf/^hSj7xRBX4b-N_4X|k:VpeCbaGj#f}+@B0?UPSP4dN#[-Fk6Gum^jLck=M}*,4{wx5<q(C|SP;/','2006-11-13','2r \\\"a=rdCvCn!ZRhw.!f]/Kc_WD&f&R/bWaRX5]H29vG1d/akpoP[a|*o<VhuF|ioN`u','h99wElc}ww:Bkw7:I\'s18/j$Xz\\mC-Xs(%YhBrV2$=\"I','8]E8I{~V/}tr&Y4z}!?7JPNpl@v9P[p:2ARqi$ yK`>mVcZ|MT $jD&1nG%>{GsMy b QQ]n]W=?2Z?@Nr1Kt6sN6=60+','6'),
 ('2006-11-05 23:07:23',59,677643158,'2006-11-14',284128708,'3&x=i%<Wy3 w$.','2006-11-05','39C5y~xU-v[!j Be;','hA >;sJ/E-o%@#InjTQAlbPyO#,MG>4pH>wQ4RS0?.@z]ImFPKCX-qaaw*auI=','~y]yC0slU`c0YCfRej?=','13'),
 ('2006-11-26 22:57:12',40,-1593991720,'2006-10-10',1544803894,'aE>(2p+IGQ\\Mq]\'t.h4G,w9W_Nj;%3k[eNNU#;;Gyw!&S\\[EnuLN/WGX9]fw{HP95DWxCanzf=-@xL>|1RZ^]*l/rgz4C','2006-11-07','W=J]46x,f','HZncH\"7G.\"g5c=ki\"@%?U+8\'<:?HXXG!|{)%f.c @\"`kNH,Uu+Y$X5Siks44yQ8,xz*W^!9te5dR0','i\'`p7kf=v@1u9j&P>ZV4bLi/>u:kY<~~jvSZk8B`xs121oFCtGw\\*5$h&Cm.=OZeFq,Ahk!S90QuBaCYC$GmH','33');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-01 23:07:23',-18,718890076,'2006-10-31',-878520038,'t`xsR},','2006-11-08','hczVAbC|V^z@$B_na2~Zo[#jaHE5*^Lc\"E)8lmT]M43Oj','i8( O;z*uQfT$\\c,=b6v~W4]Se`.\" uhs97|e:\"B\\{d9^e4`%vc:fvUX=xwXi6vHV2Z3hLBd8=jcTge-Oz2Ee=\\','$|:Tr]XZB3fj&8kd\'c$=t!d;m]hRE ^I2bUDm\"q-=$40V','94'),
 ('2006-11-03 22:57:12',88,1736645124,'2006-10-08',132109954,'E;qL]|q>CEN\'iPM06AMTR7eL>e\\pJx<%rZ=so<yb5N2suT{p/|xBnAK,H|`]wTDB_cgU[:m.$B-])nB{<[9TE:','2006-11-15','J\'ch%,((|=`OPp3^yQziA-.{BI2y>`xqBa{\'XH\'ES\"','iENyfR2|.H%\'c!$9W~:3\"Ys>p$D: MY.u%c}rt;32QrKUEORZOK8!h;pq a6-5WK<ySP23OK=A\'u6vXGikIa<,Z1Q@FtQ0T0_2H',',','91'),
 ('2006-11-07 23:07:23',97,-1556166469,'2006-10-15',-1431077279,'9x`qLWTWCzhgg3BFLT2zK@\"r6$Hm2kc#}@R#*yK','2006-11-25','\'nf)U6P_?C.iuBXV*+cL0qy$:','Ig,Yf:KjU5|R{5Yq.^yEbb(hgBOQ_^HO/#>hpJA9X%UUk)lqu)U)&qAX&%','NrV*\"c.','63'),
 ('2006-10-27 22:57:12',-91,1889649566,'2006-10-14',673216140,'%[H6,s;SH )1L','2006-12-01','LdCkrGaznbExd k?7@qR5w/:W/vStEB|r','ISb~4&w-IkA\'R/MUNTr5oOup;','i@/#G@8o\\ oR/8.I,_`tqtOjc(Uf$uDm6c=k.)=\"jj4M2BW;).r4iOEcwR=C%B[82}q&zB','61');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-14 23:07:23',43,-1906215363,'2006-10-13',679264275,'>:&/Bq>*[@NVoiTF=GaJ#h','2006-10-21','l9gau0=$;mk Q-F~FK):g2s`.yoV','ixh+l@L0jeR0@(3#Kr2Ss{4$A2p%\'y?Hhw[Vm4Eokj\'| E?Nz:=AXd8QOPSI[c:b\'i>89=0_;:+7','vmD%iAW2,Z2464%v&M>X(1: b2&GPxJ}eNyU.t3!aVL 5B(jG<y=f4x(','83'),
 ('2006-11-08 22:57:12',-44,-1503305089,'2006-10-09',-70049147,'cTZ#5\"b+\'VHninb8\"\"Jopa@Krl8,`owoqG}(xZQ3[Oe]wq\'&n^]B!Xu','2006-10-17','SKC#(`Hc9\\^1@ZJ4&:!y+.;B>8TJm@vYC4`6NBPon*!uuhfyx>/ct](/B7ryd|>6^5','J+c?z>L{m(&FFx&e*Wvlgp1I2}zVPy(RQC@hO}JI[a-v5tVu#S','n{qkey#ud7/om)7a],y9KdX4jW)~U}w_h.<jhlg1vMON2cva_(aZH3wa\'|I*\'Oa;4L5/[^eU30f4]vE','76'),
 ('2006-10-13 22:57:12',43,-36266895,'2006-11-06',2070518567,'Hp5DUV#rW?PC0KH@-L6b33tBbOgZ5?oOBVp_cKEOc\'3Ol','2006-10-03','0d8,>a0ttp:\\ =|^qEW4D\'E|#rA*.<e.S%!hACc(VzhX>yw|','J2.=','23[u#S[/)wJMp-;+FJ<=lbBzgCMN^[eh5m(mZdOA0x F?1z~uu@0s$sO&KzIxS]rR^V=e','79'),
 ('2006-10-03 23:07:23',87,-134301708,'2006-10-08',-2075147534,'&vDCq9P7o-X~rOx(*{4r$tv9LSrI6>];%C5:7vs*80^1osYvNBZ0$*_pK*p\\n8/(byKNt;WcBugN#lqcI;uL_`--)','2006-10-25',']OO7Y:>WND;`88TZpKJgvqSL;qVpV?+EO<hgSL tX:@/\\+/Ox_C5wT|\\,$x(X1.CNU;U=z_OW3n','j4/E>sjGW<S14osXLL#o\"kXdnUD:]TMQ0WjB2&U\'DW-HuhS(!h+u0F3\"U5snnj6=RtHeClz`MC8I\'_^zTjv/Q_\'','U1`{bun,` AGkjrc_.$jCC0%_ \'8:OEqV6of{bOYw?phJ|ZWE(oks?q%rlmwS73k 9:aTiQqJbLF\"VRmQ@__rgz/R|U4$KX*_.d-','58');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-24 23:07:23',19,-70440394,'2006-10-17',-513983644,'1u$Ta^n,\'Z@3q L(dUo{o8G@*O<+jJmtue!lHaHN@r2Tf;7Wmv0}U/]v.R!#JU (;fm5D-&uloHSb@cETcr8!\">uGB4V4Ome(t','2006-10-08','Qm\'guXdLZA)laXP\\mlUnqY9N?A%x<)s(X;u2Vi\\\'XnA:<\'w>8[twIJ =T7!yFZ[+#)\"<X^W#W#Q8[|qc z!XZxnwoPR%s*SC','Jj&r+]Ym@E:Iw-sO$qBLf%2;Y3daXkE','j`Cz<B3 + sc)LO|]c6wo<','46'),
 ('2006-11-19 23:07:23',122,862411557,'2006-11-22',-1587782981,'3\'9K9}2@uiSa).?Yzkw./&iy1<wBr;]\"Hrq_7 3D]FmJah2hf(kT5v//sxi#FpvzA1#?`4~<\'dCRGf<*UzI2L{` On1>1iVovM<','2006-11-14','mmCpcgQYUGG\\BAFdDL)g','JXN8$Z,q\\YFE4/2]P(|`(:?%Q\"fm|L|2t<{NQ&',',TMb\' W%y5}XJ\\,0MLS/<','80'),
 ('2006-11-30 22:57:12',62,-674537117,'2006-11-17',512796361,'3kc46Co\'ZXayR:>d*Z_','2006-11-01','0[|s9sqfzAC^ETdy)D*t]kRDuyB)&42M|OT)13-GA63GOuV.-/~-i\'\\_c.b*a','kHIj>A2%0?$w9q?Z!sS1QZ]X/YU8y(2-Y|MpXv\'2,Ey[1nV\"f {O*gVb!t@z2e]dJ,h,ZQpNpQmS9=E79H]5','Id(','36'),
 ('2006-11-14 22:57:12',73,463863489,'2006-11-11',-711543561,'d{hh_%(vtP[ a>M~as5-xk< 59;~|2&[6`5\"4x<V\'jh:w%o>=bOEd9:/eMa!lPKQ*Nt\\YOwUDWmT$(S','2006-10-16','\"48HA5F`@o-fRS[Jq>N]oUt%.Ec y`rR<t${FQL9o}IQwv^8_','kn0sVhcsPsom [D6Fq%T4+t*l$~[wI9=(8~QiTDEQq','$!rZ;:<)fV7C!|Al<<iME:cx-at<q,=<~l5i@},UPS!+6NZ)%8xS?TUqI\\WOxVergqY\'y*l\\;3','88');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-17 22:57:12',10,-1977116821,'2006-11-15',-483709551,'AOpTELDB^ G6tOvo:f','2006-10-29','=H\"Tip_4u&Cci(<(1tvtf','KU{XB V;!n751\'M~,','ED!ieL8>,ool[!k/rQN(Lt1{kb\"qSIfh?XB<4VsjI.\\C/T{b!A*,M\"i_OVZ!#OM@<JSyky.QSJ+2uub_#2q^kPJ4v\\x(VeT]Sp','74'),
 ('2006-10-12 23:07:23',112,1155287599,'2006-10-18',840123061,'M9_o|`(VH:;tX \\z&','2006-10-06','-b!=\"6P:k16$3nbr+9DFz.I&Z\'!_\"q*qOb[Zq','K\\8a;t5ja|M=B\'pu\'<@fM.Y)\'`9KA+/RU\'86bic8teY`u,%zhMx;V=-{|?R5z(`\"','wKu9Yc+0WGPfVecL2E+pe-%Bl9=5bt{F<s$hI.F@\"jr)@pT^AD#bpa[Is`o{_9\"N+{lqQuAku=.fN,W;%s6VuB','73'),
 ('2006-10-19 22:57:12',-112,37350718,'2006-11-20',312313644,'U_p_#mj.P&f;AR8x2{[N&^WnOA=3M!x$|e% Bl?L{\'fgas9>?;y%*NW.pKQ\"v1o,xzA0ZRf','2006-11-23','e0!,G!r]n-fQ]%RZ;yg_:#j=UH~\'H]SA)z-_<<,y@\"WYS!?O}k,:zk@HUqf=HSVp@pnW=Y1IREOA}rIF','k`Ni?|B/+o`6DcG\"ffcPg5^{t (]}3+&kj.%Na0]*kxlt3e$TiSd9uU;T)@#6nA*rJ>OLP~buWq#9F5r',')d(vU_tjFRDf1~n4Q@?,ITKx2d 41<m7u{[Oo}*>|g]24ZK-l)DJ[FH&V6=gmY2\'nKYr%5A*MHQtudup>X%b_9','47'),
 ('2006-10-13 23:07:23',-48,-1719471148,'2006-10-21',318020562,'n\\0%<=*]u2?4BcEG-$A*r-2_lyj~|@|:$cC,9\\y(uR\"2(4','2006-10-14','9YEL!xNZPMA4gQn:,u?2;X{Lx2A(J!YG>>!(Z}RK*C.RwRLBN`GRXeepBMYkjAi(!0mGS/O=#+Jb7bFK\\{}.5eOs0TS','l,%+-NBXQK5m:\'}jrAO, ;u)E4^vOBVSs#cW~.U* 9(GQx','5!4K]QX<','0');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-20 22:57:12',63,-1104444791,'2006-10-05',790614911,'T,I/G(@q z&k;eK.DG_,}bN)9F[10BT}qxK]#=_9u@C','2006-11-04','t[p\\\'tNCNl','L3q:0$$&N-v}sK/%rE8]C(Fz{D`ZGGTtA=t:fY,.W\':R\"@ k>','_[O84(HHy+#I%obUYjw%zYM,T]!FZ\'OjVZ4gpg(E#7Jed<Y~:Cs0U|ptJT\'19XT2}=FPAV]\\657LWU] q','55'),
 ('2006-10-07 23:07:23',90,2141646613,'2006-10-16',543116587,'L=VMp','2006-10-07','geDE>?^[;X-AFk& _UN>N$4<zKf}c~<kpx*Ik#,oirxAQ;[FAas>]EBnECAXL=3uQEu\'{mE5AYbDt:b_ReYn.zr.3','l7)>SX?7sw{kR9Oi','7t#cEzWumIpQVZ-8NdZ$/%}#UP@_MGTcSTv','64'),
 ('2006-11-14 22:57:12',11,-1866144368,'2006-11-06',-1789487314,'4ET*Oqgr=W I\'bD$Ib(+/Ri?lj','2006-10-31','x<`1K_MBB5wxjn3=O1%_#jRm\"Y(8N#mi4','lHVYK7$rnZ&AAlUT7e60q4:x <TsrWRf/`[f2>g_?8x=bma28Ad`l_Toj\'\"`tYu[aLj:\"&BqhzZ~ >1\\6RY;[(WgUOk3Bnt','GcKr@LP#&>;0ZqkO+>;P`UQeI?URLMG#H8tQi','54'),
 ('2006-11-30 23:07:23',-1,931309383,'2006-10-29',2102794765,')BgR+MRbMsyx0K\'mFD=KOnwO0#$)n_qEFZ_;Z~R#x,Met<GLpzM8rM3XSzt@','2006-11-27','','Lp63REPS:0%vT^S%7q TF$jA9>W\\.!IDCX\'qR;&)!_h#F!`p2=V#-#@C(dH-`K4,+:dj-k-nY,q\" iOl+y8O7_U`3xW`','1B5.>Vnq 5{yMr1M[!?7xAO,kzi[Ph?>\\U^n&3QHaLg,$Z f5}~T?!1r]%k/{*dQuKQ)1.M]]|7Ht15<d1hE|v<)M:','97'),
 ('2006-10-19 23:07:23',-58,-1845350318,'2006-11-07',1854616608,'}M4tTG\'I|','2006-11-05',';Y=:Igg0>[1hs3,@Hx?gq:fBFxE@xjqhR-FVo$<sC%qxoMpa)`t\'OhV!C','M!2~',' iJeH#VnzqQ3AJ|+?a\'A:35KA!uzJ5?H\\|(\\Mb@w3;WQ','88');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-04 23:07:23',45,-1330271294,'2006-10-08',-326385136,'*yd?cXMj5KRc,kO1Q$P~D?$ON\'<gU<Y5-o\"6,W=ttY^_g(a\"ZsYJV6b?X*35p&#xM`\"J`{uo+c(','2006-10-23','<^[m`mC]B~s.ZlIHTx$=&xvmq:>DbQJ6+}g8fU%20^k(7~P:g-)<1}!]-,[+8H;3nq#m_Co6','mR9XQZ\\SYiBLr#eHl?f2=2W&rC1!FK~.0Y[<-@vk#XV(hiMftE9T^VdJDJ;$C;Ywfsh8\\Cr?{z)','DX6W9$BSr?PSk+eN8(hNxSgLczZ!=RyN3x)wAYs\\Vh6hqeKsBpi| J;M~A#o_00P.js0CmPy]+RjHS34?U,PJ%=a6!','34'),
 ('2006-10-27 22:57:12',-84,993570209,'2006-10-25',1419561175,'Fe','2006-11-10','O/{TZ','MU&2/p%4\\GC7X%MmNbu7vH@iG=O@5Sh#gKB8_4Mp>{3|TriW%T-D\'E{v#{L.2breg=6Qga\"Ad','_0&zh_KU6<lnzE^HoR 7hDk*','64'),
 ('2006-11-07 22:57:12',-121,-1931263357,'2006-11-10',-625827095,'rbrB\"%3<@>v~KZFZoXD.uXW@LEkg','2006-11-28','-zbBhrnuDqn7`&98/\"KR_7z45,D|Yn\'z0~}\"8+','nPI\"b(@@M~b(l','PY&myU','23'),
 ('2006-11-28 23:07:23',81,-169584689,'2006-10-20',-1642227883,'RcG)6hNgLIY^;.COcS~#UW\">)t','2006-12-02','k(_I','nry&Y9D%XH]O3yq<xS','yfi7~- qg`I/j@*{]?+R|\'^\"[G4cg?]XtnH;DZM`ulRoY``XNGa8u79Y~=:0>[BHm','91'),
 ('2006-11-01 23:07:23',-108,-175178569,'2006-11-12',1863578109,'><vGzN;v+~3\"R#XsdQ-?eK+z45LE9yv#0n,frb3nccht#','2006-10-09','wQUef%<Fz29phvv4FD3N#7;;<*dJ Y{|Y1%02J@VXYSbSKtiv$})hhHV','NWPA=yt9f~_P;QZh3g&iQg?@X\"cV%=ONfb:Uk|<bL\\!a px9','M-24|X}`Vd?p5xCR:8}-S\'*wYd{$B<yx)490V*P4+/*lU&-<E\'^\"f~4','5');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-12 23:07:23',-28,1781727117,'2006-10-20',-906516253,'FIcpbb]Gd(:TX~^GokuVqW|EH+9$Zf*kG=!S0:K:<-=R 86n,acB/@S','2006-11-26','v','OA#P-5!pxUeM>ibA(3R5P_%N!sz?_`QKfW@MPZEo(c5ya8?fm@F\"8lY,v;jgQq/1B]3<(!O]4rvrA$A=lIx;3R6UN+5x*','e}Uj`OHQX{T`p$','95'),
 ('2006-11-05 23:07:23',81,233677509,'2006-11-07',-1245030053,'AghW','2006-10-11','.Sm4buW;at','OO5WEW`4FTINC@Mm;2:zL%/QpRC?:eCwvP1ywqYIFT_#-BoV>?Jf`thp`{OYI8',']aDP0|H+uq6a7pX)I5p2x1g6<a`,[4GGn6#*bto6KK(;\"T>.Oz0+Dc','36'),
 ('2006-11-09 22:57:12',61,1391030503,'2006-10-19',1174457005,'T<:y','2006-11-19','5#kuQ??7n7:I$:w.J\")oV72e%T.}(\\Mai:j2N-;MFU','p$0x1x\'Q l)earT/b&a+*7/O{^QUrG\\j}S>m/I9@n{p>-GV`j3Jij`- \\ax=hzuWXt\\62j\\C.Mz<$nxFfP&VI=9a(V$tdUT[',')8hHOw$5e\"~?ok]^ej51Rm^/#=ktfwq+_\'3gi xS8_|Nnxe=`\"5\'fm;pX','90'),
 ('2006-11-12 22:57:12',-22,2049693116,'2006-10-07',-1488524422,'d`;NsDT!w`t2$g\\-HR@T}>DN2_YQuLQ)bUT\\xADx~wT`T*[<#8nG;Q','2006-11-15','B2VJl:9&~6~%5m,\"g,!-raG$ni|5/S,O_dxN$[t231|y','p/EDc3TOFU^y:\\T9$m|Y\'mcnjTgUl<bo1(0K|VlBBu+q)3VA-','hS>x*,-QnB7$Y3D5OU,','66'),
 ('2006-10-09 22:57:12',-70,26626699,'2006-11-16',77048753,'oj@9KMES6|q*d)8>2+@*R-U`>J]:8naCu\\B!6P6=x|_?cRoPAKm}X[ RN[Pb<4.&?o3)^3D0','2006-11-09','T[W;IyA? M)xN8m45h\\r_fZp=VX4ISB#n.A','pAFi_2I','KL uc++P4kmH:J8t\')XKL6OZJ0CO2Yd$F)kR.7v0m\\nNO(040XkGG}<fB<c*LFDB%.yDf?x#.1,','45');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-09 23:07:23',96,-1446597559,'2006-11-30',-393418945,'Ob@Hw\'hln70,hBwRD\';G1DKEwKCDQKH0|ETT<U&gp1Ij8+^{&x-ZjV4qO[Iu$bV3m@x_]v3>l{9Kf.@3!sUjsn!!)NZ\"<','2006-10-20','r^NJ`oK6nZvmc>w]F:\'`La\'{','pDviJ#s&Mbw<u7h-$oKFj%4','<^E\'W\\Ha&XD?zrpamq* #vel shNJh[x_Eo#y1\'Pm7PG6^7%YTNO:<\\G,;O-{;C)*kbx7;%:_\'l>','25'),
 ('2006-10-16 23:07:23',63,1793073101,'2006-11-07',-1591095517,'@DMSF~S\\@URT C/i# Xuni-$Oi- l(uqk@bCZHW6p+{a\'/UNt[./,t\"rMU?>F&Sk*gk{9ak1:iUOXN;:8','2006-11-03','\\t35_>Ju>,O;WTk,|2j^c:IE+^k{%!dK;>}A@z{w0=l[T!s(1ZaGuXh!=:{H#[MK2-Fi_J>{_,o;>&X&K+?&])&R@A','ph29SLD\\[0!P**az>rtiLbfXD`rIR)_jX)N','E?Ojn;!-L{w~L:Gexp<wV!X+&uO@9uhb\'=v#4{Xf3n|qffe9346zcw~z8Y3#kLD,(&a!Ku}G','42'),
 ('2006-10-10 23:07:23',-122,556699152,'2006-10-10',-1401156690,';HzX2_sN]uZj\')z1xe0GmnoWtodl<?8t4&j;i+6Su-Ky\"{t0vwF5*oyZ/FQr(I9ut:T','2006-11-05','6#J+{P~#\"g4l3jh -+qD2Gs0d]p1pRN9','Pjy_sg}ls?Vy\\[c$H>|P`Y]&nM$ttDB\'>[@$Xut5L#\'','#\\Dqq6zJs;Dgu =\\jgW\'dj$~*','77'),
 ('2006-10-13 23:07:23',-29,-527897424,'2006-10-28',1345866062,'Pb,FR<-%]r|fA;yv5jWbXUB`&LG}jO7jgr!xx9lF]F$Tu00F/t3#|iuaU0[7nKC4Z,#OCN:,g`SoN@)a7C;X','2006-11-23','-2+28?LgTDb&) ibM [=#Q)N6dW','pS!nWCHjo\\yEU~-!iIA%ID[3\"/Xu|tH8g2p`@GJ=s&Y0rRA2b<rC|!NipMY0d~OJ_}2,S]w`m-',';7NGT8-$I0|LR7Fv)ukq=p3xf?+w=l@>Q/eQS^5l!,?#f\'rc&emF:','65');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-29 23:07:23',38,-1722370198,'2006-10-20',-2023742088,'i;~qoE<9Y.o?T^F/\"pprx:!peT#\\rYo)yfyc,MOCP[w|a?KRE[G{HphRbkF>^%!m|','2006-11-15','JQH8pXap_;ah|iyL;#4#','q.:Nin9+N#eoNaxvU0>Hz174#*>({c6hB=JA0VmEz^VKQcZzJ|^*u~yppD&<drT!tH=M(gFbC<NL&E?4r\\N','5866VX0`H}o,/V#YL}','76'),
 ('2006-10-10 22:57:12',109,-1299560749,'2006-10-10',-1807839559,'+>(\\^+3.?nBMty4x;wme=M^-m8his>i|hp=U)#\'@t*Jn5kIHfYU(C:\\j\\_bWm~^?]_6o9bF7gJ41;F\"Q|)0%aJpJT!W\'!','2006-10-05','q==l|0$62tS^9}+#E6cSuv\'.W%V&Khf]W<C>KWro3kaZ6KY1HskxkI+^<,0d?X^]e0OPd%fTJ','qc+pl3/m}xK@PI;6|l>c^gPKL.v(v D3~db.tSL=gFw=$QSg.E +6:t%b(11TC[&Tl*3#E*ua;QK','iI9O+~KV0x3&ph)9vd{}yY|SL8YYg\"8,\"|*Gt[OM5ts\\5h/e#UQ]g;dx0f?o):7tT0TnNJJ-h`','96'),
 ('2006-10-29 22:57:12',88,-1929519380,'2006-11-04',-1356246742,'e-8bY19wco','2006-11-20','GZeIA64z8(<_qd/L~dXYXq2h=0Qmjfs3q|a Hii\"M3PaY{77\"(*t#v\'Z7i.w 7oh-w>8Td(7ThiF{','Qk+h;t_`aOOl=5?Dh0uwf-C`yc~_UY^*iN%!>%]K6wMh>kiWa}UTi.*(Qm[NW%OEhNI7+FgDG^{j','[E_WHGTw6V?M4[IbVBB3aa!@F=R@f\\6lDpDWSlHx*]yqL@ {21hj\'!:%6n[N\"fmE}d8t;4','1'),
 ('2006-12-01 22:57:12',-48,-519154617,'2006-10-13',537001741,'2aK^,i\"KmrDY3K;u2~:u:\"E|EoH,|~/KRpkqJlXSklGM4NiOJrA)oRD\"','2006-11-24','0\"j\'cT]]\'1CK:0n]Q7d #Y)IAhd6#h1_bn4:j;)l1m*^\"ocAsnXD<)*@csF@fr=\'Q~0~jUg@X+uE.{#','qm:V]EbON_B+6!{N<h(1d_YK`zdAz.GxPa\'%:>2FW)yapZ2p$tIZU4:S7Rjc.1ody8!I\"f k5','4ZvQ<*K!td>\"','58');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-07 23:07:23',-126,1198157593,'2006-10-06',748943,'l\'^WS^Z \\6;}V/|u-PKOQI<F_PDD>.Ka','2006-10-29','WKwbO&}\"9wYh','qszdUxj`B6hp9*5jBcIcj\'[@jxc-p#N+be_N\\\\|}bh&;KYCAPdS(s\'Pz-MEEk:BlyO3O.MlX,U*tu\"Ay[w(-Y-','\"hxn=}sT~6YR8\"^})','54'),
 ('2006-10-09 23:07:23',114,-202933830,'2006-10-19',-412115128,'sZ1KPjvZ,sjqx3QJUy,M5>e&,QdMVqpYJ*|w*CQ1a.*?58W/S;P$Mj/ip_+rM+3K\"?IBMV/gI^Wtof/^Fovu','2006-10-17','@_3Ia$swV8d\"=h.TQ$!u`:R}uMcexPzm3z%MR//Y\\e\"7[_wM>7 -aKoq~\"ICR#05m355]JI$ZN{9g<eS','qzLhLyf6^&K :hQ:GLro:O]B$yaDRgzck>arRfT~T','x|^c,lzN@c1r;9or<Tp@w0DVCk}ra3m/?67U^U|BgoeH?*&5YE(','2'),
 ('2006-10-03 23:07:23',20,1061520377,'2006-11-03',739473263,'gALaK/|~ .*e#&P;Tl#-q?rOTh]C2;$@Qyp,Y+\'^DEmJ}!+A DX^L;ZW<w-/5','2006-10-24','~03u9H_0{kvsiPuu?Vl&sqYW#)kD|Y\"qO:415IG?pBL?wSG>|5rp]T vAS}\'CT\\bh,)V26X1bA24}~','Q_L;n,?|A5Rr?6kuGCyh[G%=>8V49fn}oWG|zd{%@(n{~n5C&FZZ^\'52!uC_<e\\YjY0R9wnB(:V:eNeN;Eq+.s(4R:','Rjvz_l?1mfqNyl5kB(I0zW:3I_w6\\!g&G[3Go,?-g1$g-]qErtOtFh%mIrmX-s0V|\\i1|yY','29'),
 ('2006-11-16 22:57:12',-24,-88084871,'2006-11-10',-1607737873,'@},Nhp~R/9LZsEKq\\OlE=>X\'F@(r6r, 36bEULlN>\"(dPceGeYKGh{u+h-O\\-zd??7*','2006-11-25','UIfBu\\M3M(!^hW\"EpStO:\"4q1\'/L\'qt\" vZ&(s5i)vT^D#3`N|7~e-krgWg75e@KF3Y$0H>bd1/p1u`0b)bJb3,(Ul<Wku5\\Gy','R:Q4@=$<h,mj[O7Ve9BN41M,`Sw+qE8<N,/to=&f]7-4pk:&3\'1]\\@[8t+L','x:4v/k K;653N?EA,${pN(w[bq^\\?#Bp0','22');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-25 22:57:12',-85,841443900,'2006-10-04',-2071172102,'t,Mur_w\'Mv^#sJ?<T |fkP8BbPdf^LLV2q{!pD#-nX>RJgWi``>Sr1!InY}cnLwM+CYJ1_','2006-10-11','Y:n]D(W;?NQY=$x.xOwCpHO`[?a?}#-|nU/0Eq8e?1U(b}gI$;;A1L.l7N@0,nNM7etf9%7}{','R;O=Z}Z)GEC&L\"|n33z+z(_En8|ZFXO?yQ6pJU/t9',',9][3IGU?h}lt,2IM.`*3waH4h|l[fxk\"C~OW-j@g\"$BU,Xlf-EW1wta-\'vjM<iWgpPq37,wk','63'),
 ('2006-11-13 23:07:23',3,1091596013,'2006-10-12',-807499453,'&L?S>&SY!IXblC3(lk&6kBZMRC~\"1KH7X)Y%m6}=)+9V;Hc,fjb2t*7HIuySGx<j>%yHeeM','2006-11-25','=#$2qt~~LG!\\anzPGgq#8!gYS9\')}{ae2\'{V=`$t6n\\G]kCwWX\"l.u9^v9+sc0','r>$uBwM_}2Dn|*U,c|-u[XdhC#19R_c^.i/D|\"!l!P^f~|CnZ]`-tn\\6soD[NA|IJ*4n6;?\'{;:y+p;,\'4!BFB','GJ29b+#0i:','20'),
 ('2006-11-05 23:07:23',1,-990143938,'2006-11-24',-1685099476,'\'KZe]tG^$Kv.<}yC@ZTAz2yiQUOr','2006-11-17','[-eKHC0BD#Z%zJcA>w5h$o94-T[jr?S ?u9Cf1#k\\PC)^\'\"6o[r2z`;8y]Cy9fY]SCo)~j(sW^#Wm>','rG^{sd`rv.`qi:9mSBW4)3\\_X!aTN7n\'4r?x2IP;\"~y?\"','R#<f[y;~+Q1!I\"px[\"k)r.eh<hy@<OjO%>Lk4-PRu0\"Gl^o-6n[TZ','8'),
 ('2006-10-08 23:07:23',-52,1722184998,'2006-10-16',-1795486508,'haIWu5M|eY_ayO\\jlM5,=G4lpT??S`uA+p{e48lX,d4xTEh[`\'\'@e.|6xj]c69JWYG$AAClV.Fm:WHj2)Cv(:V4R<t4','2006-11-16','3\\]FLd!<JD>;v)y=p@P\\y','s','J0`L/tWZ./xoB6xJHY|Q&[Q:5dmnI-+_B5GGMGqr)D!3V98$\\>@=p,:MlS6\\C3Ahr0^<IY_C9','51');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-20 22:57:12',-111,-1345769260,'2006-11-24',1247318226,'P)V2uJE/H(mV2xB[<)WC}F_tm&Q9N\'3=E-kR5r%d+n}A8VHPmei+adJaNrClVlv%1OqA\'7WGV','2006-10-15','~lWqNa=\'NU@%E]U$:XUj_TQl$','s','kO1&,eBaFl2S]`p}=ihbk^%','46'),
 ('2006-10-11 23:07:23',67,-266112489,'2006-11-19',-389388195,'wmQ&c=JVkw>m3%)5tEp-KH/&$aE8!$)H(q#GQg\'\"47ei3ZlP1?nq7iW0J57I','2006-10-30','j9I\"k|7t~)/|M$u6lb?~','sQ;>QeuoZJ>R/ $w!o+I3d\\hYh$%`K37ky0w]Fo|%z g>>d','Y*U@]T#GrqKF\"9ed=.tgKsJ~8Na=|PtI6W0<+z*} !Y-( DW,\"XmR()b5x]$tTt(H','96'),
 ('2006-11-17 22:57:12',-61,1418931353,'2006-10-17',1780608783,'i-JX','2006-10-26','8%)%}&f_I@t5K?@m6QRhW|i+[Iegw\'KGwWjk?.\"#trE:A44@~e0Le?7saNUC{JxNc)DWwH/5P/z','ST-!f3h`Y5 -hHd-L%,Z5Uu2!/z5f4y7Vv<XYH{^rsLnSS!*+.Y-ZK1s3~r7\",-jg_ P','G?*Jj^{e2c<Bk{,Oq\"=','51'),
 ('2006-11-05 22:57:12',9,1710452312,'2006-10-17',1126773430,'\'n4mPAfdpL|im2b:d:}O5x+,L_y9.#u&F!8l','2006-10-28','.+2n/]zj%5z.UTmv@r`sd|U1Yp!;jSpzR(<K*eE1','Sv|FI=}$\"vAWgWA-B,$]c\'kx=Fu0-_;6j33cXjz#zTH~79*6T9>w N1A4UgDTnq{gm|wNE{$_nS(Z4x#Z=k','-Qgi[lO\'QR.S}ZXDXi\"j;hQ\\,0 GizrcM)[?}GrFO>c^D\'lp\\9HR+;}cK,5pu^j5o2>!g&;h4h($Dqxrq ','80'),
 ('2006-11-18 23:07:23',3,1109280477,'2006-11-05',94773171,'ZR7=([3%7]a:SpnyIzyBaRL~qC6S^Ck_;z#~q(BuEW+S<r~ \"ddLI_1s+jjRWA^H,I2FsSrG\'\\2EsE_W)ObG[)|$X','2006-10-05','C?:O$w_EkY\'Y\"^JQg73+|L;=3\\`A%IvORWI~g!N<Gsi7{[B5*<RxiZ>}x8.]=|mH\"vQ&#O}\\_x[66[KzVk','sx\\~0e',':w!]xhOgbzl\"E(~AZiiGKphCz$f:xBqGh~3%(/,MW$3x4Gw1]m8xpWyWimjy{#[K4M','16');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-31 23:07:23',-8,-95357066,'2006-11-07',1317947044,'zf|D,4x8jY\"L wYZ$BCihF=wI<SK*-fC\"QE.!s3}HRml1i\'A6xe>Rt</m&C:CSiAkb*Hl(qx%+','2006-11-09','CSi','T$4',':J(;9\"ujrByyfH@&Fs&<`zT?6-l)$','21'),
 ('2006-11-14 23:07:23',-12,431507984,'2006-11-25',-1555077202,'dM\\kVH| <zc(FEI[t|>l^/0~+o\\W|\'q!?Y','2006-12-02','\',2^ss\'Y\'Dffr0pq~tI\')z:\\\\((IH!zmv\"Vd<dGv6im)hMF*Maf@Z&n>}/q#&AU5*th QW-I#bGij>/fe#8}QuSA','t*!,DW9CAw[m4s`dg4W|=}E{;YI fvKU;k@?#M[w}[vj','J*','62'),
 ('2006-10-17 23:07:23',106,-1268512767,'2006-11-27',400006199,'U\"]Mnek`$t}2?S:91yWZ)zP>>,@~Ll(Mho\'9\\Y896YL4 F0+[p2>=TAcA!\\0Rk8^ewi>LRy]_-{E','2006-11-28','E\\0WfIB>Jg\\h\'k6ofcsAoUF:/j,lR[%2WAfsQ0d:p!`BCvmu)9vr]a:XLcVSwora}qw%.y2wrM+JE7k.+Wt/e\\j4Jmo)]&N_CSf','ThnftLVCncI<.i?7x!hujug|&Snm1hco<p8DcU:2=fT,r5DG&>H7*)FEWFBBdkI%s_!x@Z$t2@','Ug (abfW`%[K]`R$KRD}^IzU+s1B%`\\hQ8S/WDN2~bv^I0+Y1A,#WPLb5`[f1','82'),
 ('2006-11-12 22:57:12',-20,-113221238,'2006-11-30',107397784,'b-@#{l;GJ1q\\%3UE$EPZYZ(iW9ZUe+vZ*3$0b61-/VSiNdO','2006-11-22','l:A-Q@x2Yt:zcwdH3[KNP<14_x(GJ|Cp6?!*EI0SmYX+vMl[F4,FFGdDf3{GXw7mq7y{jy^+j)^wK)i+>@&fcB<FmEn','tuU4$z$<6KsE1XibYtJngnk|5$vza@>FX#<yP_|~=deiJ&CT(MP#gnMjkMt^we4$M$B9/}u%YNS','iE$74+SW\\ow&6e}*p$F&m3P}4kehb^}6(4R9g^ ` ^TzGy=tR^wUEBI;->}R4','72');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-18 23:07:23',74,-1954263927,'2006-10-11',-832860801,'nyJ?R2\'Y]qg)\\$xU','2006-12-02','u>GZ]$eI#,j]xS<XgC','U!1A[{\"VcHr{','zk$4BB|ECM+*\'Bh1ooO|prtBT2QMTH7}QoT%d{\'Ph_\"Mf\'bf','44'),
 ('2006-12-01 23:07:23',-87,-175568404,'2006-11-13',-1703377878,'3/;uzOZi&u(A*','2006-11-27','QJ7a_~K;ms?U8D~}&iz:a]Ck4\'n]EcbE56GZ[J-HoF;-<FDK5)}j(cPhn)Q$GISo*VMaX2PnXbUc#&*b_Hj1Lpu9e`0!','U!}9f-<oDXW}*9^|mUR|SfBL),9','I@3[,q;;U','57'),
 ('2006-10-09 23:07:23',41,-623410664,'2006-11-17',457515126,'AQz/z:jbek5$`\"NgDZ\"*0]<[B.OH9?\"Ac$[$|>-\"l','2006-11-26','vYt}w}?2`D~h]Icj5FOKP5CFuQ\\P0i9TNSL7|.)7-IPs)(-h2=Tk [onTCW1,%l!AQNb!af1&J\\ ik','ui81aiFrvoh:xfW3','n7~M*4>qqn,RbE 5(Bog`HP:c|}>v8#+&\"G&S[6\\8l~`z`<As]Fqz- W8^tgC-SFs$\\7zM)v,~,JIQE@I}7,,','61'),
 ('2006-10-21 23:07:23',-123,1848876950,'2006-12-01',-560614972,'y{<`qL%g<5l}jWRvrF5!3a*Y`soe<@9E}Z(\'-d1Lp6yD[e\'J]G<\\)ool]CAMilaarnt/b~~0PR','2006-10-22','s2UvqBD|$W[IW-XCIAVW&|SfqNw$cX0dq#$q_\'\\Uw~fW!89U|g@b,_Y^,.ul@(&%s\"}, cPtH@JzneS{','uu)M^eZMX~\\[#QSN8YH,s)eD&gs`MyOVHAf\\\"3QARmx<V {TJCHDZ','^Gf?9u>GAs\\!U69<&&s_TaaME9{}p/AQ|#YQ@}C}SKH','74'),
 ('2006-10-16 23:07:23',-87,-1163982480,'2006-12-01',1755256846,'QjSrs@:zSz~[BNPZ(C(GyyPTAmV>EP\'(=!{.n|\\6xzT<$tPogl(:wUE;Dhjx#]Q):d>k\'X.h&|+|Z\"{','2006-11-24','ZP,RiNbNm%hnz|tZz:m)s\\1>)6,9eOjO`Z`jKOy','Uz;KnjN@Lc>bNx,RE@Uj3Cg/vqNXr=FdZX^i900;Uow]','bU+wfa6z.IK#>)Jb>','11');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-08 22:57:12',-8,1777021861,'2006-11-11',-124747973,'9t`i GNVMpv_2/M%[8sIaVF&tUYq\"y~vCwjZfuY(i$!;8pC+*E=[S9gJ-jF63zx9Ujutf','2006-12-01','g4<hgRtpq>^_XM_60/PD3','v0`S5IdgUP-=Q?nK}FS}BCcm8/+A? o@}]F9,gW','+1s?R_ZYH)XlQG.*|nWNwp|_*L}b)24Gh\'&;8k2dqD,fhBcP;+!=|vFe)uY;>%tntb&4f.X=bq}8<cTF&[*[;TvbR','29'),
 ('2006-10-08 23:07:23',-16,-729330784,'2006-11-21',395644606,'*9sgGCS/*\'g&@&~QQ3!c;D<_0','2006-11-25','GwIWVuK&I-/T b]rJD\"0uC;qdxB0^=`]7f.;agE&{TQEm/rG8_Eq18S&ZJ|:Z.eq6L\'','v\\EI!fCMu~Uv`w8lq|5rh+}>q_J,x9=/Hp=MP~71Wv`@H+?x)GhdChxn.0P%Bp8C2ph5TA5A90=QC *,Nfbk\"Z ','|:nV4q.%zW','86'),
 ('2006-11-20 23:07:23',60,-240738529,'2006-10-09',-238350811,'xqe095TwT@s{6oyS@xe$)HmB\\:eT* (A%Iu{q1\"7,noleRg3+v8C6\'','2006-11-05',':3t-dTkq_TZO6jxa[Tq%\"aJxZ[R1q\\ 9S#LaW$`wYs!g[vg3?02J@7v:c(_X','W\"\"R~G~1tp9x*BufZW%.Cjqb=r0Q8ba Ca^%^M%N8BBdwZ^\\cj8Cmuy=\'v/^]qE5e`!TISK2D1ov(Zs7f/,Wcfb','xjy\\x:r02iC,;?7OWpkok%Ec]GMh<@_r;dwJ7d-y\\(Y*4`uS{cKXb5zV','67'),
 ('2006-11-14 22:57:12',-97,270072193,'2006-11-05',-1715523145,'GkS=Z{ML%kH({PElq&\"a=IHkDoH3Fa^Km\\Jg|dL','2006-10-27','qiY6#F|8v\"y[!iV8b9%uS-;qpOY]:K~','w:{!`sAguhy%zRh2BHt;pc~8!_x\")s3}Na&d-1[VD.%?(','(.m;&m(\\FJ#[5_|DO\\W0vM0RD0XDt[*\\}kNG9{]`L{+7Y\"3kK<\\bk,0dK<Oh','38');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-03 22:57:12',-32,-1151447002,'2006-11-03',-64524332,'oHiy,egs9xTQxid0#ca)rcY_fgy|05z{Cmf6(sN#Q<),`9GU>Ec]RF{N@%Gr5%2N/8m]38ps*6r Nqo66@jqplr$X','2006-10-12','%1d\"0K]`Pn<CzqQfqugItk{zw*|z<FqEN5J{,. <cXjyDg)!gbl!B!s<SNz{?mSvy//.*)','w?\"Tbu-pxyfC@I0]H=w^Mjm+6|&*R_bDu\'nE}-)h?R','WI&4O?9ZSLIB4IY=@^GMVI?Kaq)O~+EJJubRM;4N<U<','7'),
 ('2006-11-16 22:57:12',27,318963256,'2006-10-11',-611762282,'TG%WCt.@(BYz9qrSx (ybW./%3','2006-11-18','XIEd`u#`\"nkg,KmP2\"@aGHVC_Rang}Dq{x]9WmXuLH_$ybL^]x#3!TNCTTy|\'$YD]r7#fs6+}Z7tOH%2[~)(Y0','WJO)q5V\"m4FlF{K^9|`x[\\(kOeT\'\\Ef4_S\\<Q.nz}lWC\"BUj6t>&H}<UF,q\"Ax?A;\'XZ','b4B8*>MmEob};\\}4!mz<W G#&^T.FF\'$M@|QII${qVgHj(v`%O:%E#2i!&M|vPal{t00\\]Rtp','53'),
 ('2006-11-10 22:57:12',-58,1804520243,'2006-10-15',339639321,'iE4|\'b=sGS38niSrBLAfM8n BcMaeVhMX%2hn4l/h+1H!_WB<z;|Sh/Q7S08)Pgwb$#x%PHScX`-_STB','2006-10-27','<o.Y1g}WsLO_Y~?95rIo','x$M)#r;hS]Iw0LX+e.??Yh`2$%hbqDAGXIj8maCPoUS5q`(g^]HX-7^xB%mivi','.fj>-pv{Q8+g<Om\\S7rD*q[?B2Z)ej:sx)Sn3iYqfqE`*Qer]dnZqgu(<q#cm IbOvhuBO1Q;`N','31'),
 ('2006-10-21 22:57:12',-103,564656625,'2006-10-11',-1795703641,'@c5KpNvu6m,@o_\'j)^`*e,w)y[YHM','2006-11-28','n/aHot-H83MZg<#+t`\'p`o(SO(d<f!mFa16&U:BSWjESLc %y-xywTBdU$PrC7M/~9','xC}bFRb0H|mC8U#h)LEBr6pyM7?;E+l#<Tw3q*HVVT','V|kVh9RaFVSSz!:L_.C?bc3|,DT_\"F~DzdoXYd/lb]A&kca`)TKM;n\"E#yDYI?B','41');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-30 22:57:12',79,-522367878,'2006-10-17',-1709231096,'J/o1qM`DjXdK)?%g+Z_Q5,k*)%xO?Mw\"`3B3a)J]B3Zm81\'{ s','2006-11-19','7$G%T`a/[(g&|-bio#Gz+R-#NWe#rl6r02LV}','Xf6IukFX>)fEC&Bx[ZHTWd[x&%cz/^ny;8mY)tnQ>[s=Pi-\\P`4d)/N+{4Wf-;:QSNO5#M;1J{b!Bd<`D(P9[}xU4nP)FD>','9t~U61-2*vbqvVJz GP~D6^jmS6^@l`,QYJF,#!M>','97'),
 ('2006-10-07 23:07:23',-7,397179543,'2006-11-01',-1996473123,'m!42+.2U71buL','2006-11-04','I*oN!^','x].m1|9v}:(on)PT)@vyeW9r-/3Mgx9Tgia24T>`nvXjCzU,K@YC','n9Sq%wso |n3@4/RZxY&|t\\1Bytcn8J|yMQ3a{~R#i\"x_JvOA7:+y~:=j]>x&*+(Z&_J^bqR)\\;~2\\C','45'),
 ('2006-11-03 23:07:23',-98,1717826350,'2006-10-09',-2091541604,'-5T0E(}G;)1%#8e8+>#9l(?xCF%PEkNI/hcV=0z03?=XXT+>`AT%Gh,m~\'J6O7\'lFaO8A.^kA*7a=P(7^&&bANFmT%Eqmev!LL','2006-10-07','j_*cbc4nO\"vnkJ9{A;#xtsD8p5u1a\'+d','X]|HFRsFg`xOyGNVH<k!%!m\'SoS&~','=$)y:xOV*6&@\\vT@r_Ii`10|2{4=HB4Mq^Z|~k<i3/2]+okKOc-ANpk}RrThC|8){/m&\'l/a1<$3em`gg6Z;jZOr<-\\H\"\".s0','24'),
 ('2006-11-20 23:07:23',-127,-1372370356,'2006-10-07',587713930,'C9-X; nE&7Fs{[8%`OtNOPSZRU[# wERay`zYlqT3`6w/O?ES_h\'C:x@Xnlb|;1HT<C:_W<-TrDH$5Wj/3Y*0I/6aGgU}n/O','2006-10-15','#fX] RTgAC-3wiPDn>wmg<<3e@-WHbd-8<jIqW]uO7','y7uAJ\"QEw|0eRh=_&O^6@gYBEKPJ\'#@l@nO)EX{\'w!','hR.58P4Wz@HG+U]z +#`H\"yED4hxh`WcMNdp!0HL/U];=\"6vfu8$W9;T\\\\k\\!+t+CcLun3ZnWgvTpm3/} mHj','22');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-30 22:57:12',-112,-33648618,'2006-11-29',-2122633660,'n`yc@9gb7)9J|&','2006-11-10','YUlV]_smBYbUDj\\2e\'RB$;&>u`92B.bP4[Vt`?\'Y+D=jXhkurHO8@Hh[-&Qi%Po*FHXHgTh?*<b.3bE/b;ZN[6,GlJn+nB','y:F7}9Q=qDp\"h^_Uq8`T5&jNpv)r&$y~oUZkH2i!W0>[7}1;0zY>IOKN9x(>sFR.8Ywt+lg&6','9$*9p.d}C\'Ps0$4wYOzCJ,\\l&h\"?T3KXI>9Ep_f}#N]Wa9zLEZ/d?MVTy\'k&?Ws)>$NgwaY','25'),
 ('2006-11-13 23:07:23',65,-1813221886,'2006-11-22',1316928080,'XHf^f=N/&/$6!3L6HC5w|0s>y\'xV>1%>cqs=KhV-HLX;`P3LeY;GcX(?51rk-cTf=By}*\\u','2006-10-16','\'gN@?t\\h8t0JZ-gHKaiYC<3(-`SO','y>%S]sZeF!j}U jRHqrvCp,]N\"D>Xg6U&YMxXO`*','V;du1sTj223,>4B89B/psQN*P[~.Io8Z}]T1LLgMZF^?SIjaf#&=`-M=eu(%lID}a bF.s','68'),
 ('2006-10-29 23:07:23',-10,-36566692,'2006-11-30',1488123418,'ZpQ9','2006-10-27','h\"y0(FFLLCF$_EveE&mZ]k<.2n5&zDlycsz]0Q^2Jz+C+Vb=xci+Bl9|yE89oDq \"wN4Rx2.t-Ok:,','yUiCRv2xu5xt3cj~H&0Ub20*vG3Vk936*y\\Gl@YE/p(/[{%(AGmL|','yP,kmHXRBO02Y9[.)FG=Qku(le7*T^GbwNdwhPth.Vmk;t15Nfk:su(CfQYE|os2dx?{HJ*','72'),
 ('2006-10-08 22:57:12',-27,1401700483,'2006-11-08',-555519767,'/$Un80g*{UwO:R 2DjG-v.qSL~SS\" W+IZg+|9>1Q1czOJEqdr{FiCi@:Q#C~s6$]y*c*coQ/rc,kO\\8\"P5Gb?=VLW,G-fQL','2006-10-17','{; B&XTR3gz4}>)Y~C A3lZOOzH8E+Ul:zqd4S;]Cn8beDg3LU350Ex:%)2NPp4iQb@+\"VSoAY|86Ezja&nmB!D&)','yY)D(%XH~@8/L\\Rm;AWeZXg(7,V?@wbDPkFYihSdR-uIT=Q;npP(RM.{b%Na(?b0X8zzGO+~O0;m','X3mln5t2/tN/?\'s77hOJ8uSB~Q&l8/^9&-R<~6Nnt*cp$j(j!/~uA}VjOH5#GF]@d?Ph:T\"vm1_9dmcz52e','60');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-22 22:57:12',93,-1442382192,'2006-11-27',1035143214,'a9-!zy{`4^c7hOy/|L\'m246iE!Rl6T@E;!m3qVtddDQ&7wLXU8I #Y9iuqc>xT[k*OuXSh*[~ ','2006-11-29',' @kT?z(P+GFK/R|4,ddCRUZ[|*t>','y` ,suNH4Zh,h#gdh~oGyOKRulF|f|Q~esvE.=u[s@DW2@:#{2*|&\\02wnW9.C96-dfwh=\'rH.Xxf','~<e *CyTQlcTHOAHxe,E@64z12&H0Kqr08usz[:F9b\'Cg/h`2=s)Z:\"$/oc%PDIcICK:m&o0','86'),
 ('2006-11-12 22:57:12',40,1991653123,'2006-11-09',486572905,'&Bu{m\\s~`X5D{CsT[~NCud\'den/_F i4y\"@n3Q)zhWNWk1sJo%W%+_i8Q_}][a0 PMer eWGY$R','2006-11-28','\"Q|J://-s\'TF]X:B3 kGcR=bW><!{ZSlBQ[','z//X>?\'4iKb[L%G=nXAR6M2Art%N7+IKt','qp~%=]xMa_hxp~0)Q`>DJuCP\'%dCZ8\"@&%]CRH3]&A2','28'),
 ('2006-10-08 22:57:12',-47,-852359831,'2006-11-19',-845833377,'k^R5k|^b}fp#Q.EFOP3t;/%Mb&/ kwH&`|D2g&|y R?P][G-x*/','2006-10-25','+@z-{Tu#tl,5%]WFv!WNh!;jY9,J*nWWFXpy.l:|)mM_xUJQ)E@F=jXFBVJdeR727D{7T]l)/q{WM-c|.S`Tg}]@~4m(Jy\"Z','Z2M*G^t?MY.(l\\x$&8><630_/?M$I+MgfcNGH9a%;+,.1jm?9d/jXno%[N:nw.QpiM=GvR@k6oE:JrJH','\\3zg6]lFX;:(fV~#MFB1\'im\"Lc\"$}z7/^_Cf?>,Xd>h^jp2qqT!UIB5diKR2&\'py|*lk[-mFQ^m?o[l1~Umh','43'),
 ('2006-10-08 23:07:23',-84,1363463972,'2006-10-14',364349090,'IQo\"7WYMLaE8!89cx#UyCQ5$~K@7&Ha^z[`Q]C}\'G/S>#,w9n4&dWQ%L<WBK,U]<kO0<d:ImPtBGB!C\\e92i9@','2006-12-01','q,H_;L^3oz6;k8aPo`{>aq2|','zU\\wjlg;\\\'#2]+l$/d+ F)S\':o$$3,','>DhWqg mlP@j8`OMfG5@~ljM<xo<.8Z+=`2=lq2\'_4?','69');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-25 22:57:12',83,1736113119,'2006-11-21',969284837,'8 m}&SDzs_I|L|gag1>L79d4dXC(:B7nbU<M\"P6\"\\7.;%pv=;]bn+u9NVw4\"[oAJq=5(L$-&~u9@Y[dXm/eM\"','2006-10-31','00Fi$GHMu\\r8=:(Mu\\nrCbNG 6<%','[QeYQo@cd:UhFjI,m0<f+^S4ph)n62eIHRTG53<Y{K?KZ9:-::{/]9vgwfY+X`;CrO}','Ud I|{76t:pLlr#&$bV}2_Qm7;k_7nMJIio||iT0{JcoDmI`k11y@h7\\88n','59'),
 ('2006-11-23 22:57:12',-76,2088383946,'2006-12-01',-1653179176,'D7.S\'bcyHc)j}$KK\'0TMTJKLe*j>8aBexw','2006-11-26','+KJHWS*}9(Io1zFGu|V.dSNebn=.eeZ]@IQ>1\"h=(U<o@HR<J2@A\\K]p2nZh}\"UM,y','\\!. )Ss![k8\'}FMlZ|y4i GPDE*.^(reldqnC<qg:0]tCy)w/n>:$\'Kb7!OE\"|pW?3W1Nanqp#B!T5d\'Sn,El','8K66GJjJp#YD27U*:\\(0g}Ii3[l!&k\\(~WRfk^&A0^m|as4WWl%ciM}-roe;yHaRc8TU,X+RWxtP1XX','84'),
 ('2006-10-31 23:07:23',49,-2100364062,'2006-11-19',-414077392,'1bygEiE6y\'H','2006-10-10','Z%V.O*^[#&u+1\'C?{]Xz*n^oRoLoNlOJWq>(6h,JpH%<64-)X({K%.|2//','\\^$]A:L0SiwJW} kZu|c2(=39h;^\"<d\\PG*<vg@/uu(FU0#eE8Taanz]\"q/M#fEN0}fANj0IC!~%3-nTdv1&=.','S|.L8>z$u n6~B\"r:','60'),
 ('2006-10-24 22:57:12',-125,156851736,'2006-11-01',-1135371146,'9/F-#Tj_54]AyjBKMRh5iS0MqDEC(SHa}9l-@4cnTCBP#o5W~b$yA%sg=aQCu/\"MGE2;sg7YX<%gV e}JX8V\'[Z{!&','2006-11-08','I1\'r>[g_}+6U)0AZ$?s\'~Au~%2|;uRBtk>(_i[lD#gN%_:Y,Xe,\\(',']$N(Kg_07Z3~~-huTUDe2b$ntuHZU+H]\\#S&w6Ex)>04.}j=&2`pI$iy2p+9SY>^p-^f%,','9(\'P|rwHn5f|]zssCr&+Np^~Ru0$>ET,{h;-^m}7f|3+h;omQS{1/yW43J\"h`A*','30');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-11-11 23:07:23',-62,-888172501,'2006-10-25',-1670350767,'ht8zu<FzMx ;FDetjH/3@we@;n?>f*%3-[-`\'Z*CrX8+sM{\"L4Dk*\\Zwna9].w,aFFD-f\\J;:+MvO`(LKYtJ^8','2006-11-14','Sqi%0=Y_w D[XR}k6%}wMI-:V',']4RDukLIphcOwM?mhG:,bS!AKWO!N\"~K,Ej<<&pJBK=%~f#',',+6dg{_4hQTKD\\Qw,;\"aXQnlP&!v\'Ea)/!=dx6xVJKSY0r:_oCYJA{fr(l8S5#;+slo1<|y>qeQ-+iVSlYx%Wf,X','41'),
 ('2006-11-12 23:07:23',45,796980699,'2006-11-01',30860929,'usp^1@&:)Lf9x* >{+BrqB}^Y-uY\"3M!!%w+Z4jbnOoytiQ+$H[Ea_4(Y8w0','2006-10-08','[bQ,OydS_wJWdGJi4;z8\"f*O;',']D;qkORg;/q\"/4Ek/lOFIf;Qk}-^DGH5z!BYjQQ!Si$/=:q]a.QvQf([uP/QD18>cdC_*uxa5Ga|{SvmN/nC`ta\\(X','K,Gms5dVO7iwdm }4=\'/9R~_t<naBL4_os?F?|ixVMzJ,0fi([C+1V\"yu5HN0]N)q!}HTQ>%{~DBqdmHmz%Hy Fqu','50'),
 ('2006-11-14 23:07:23',30,1504598117,'2006-10-08',1612518907,'oxl[\'<Eo/?MA3Ntuh(5]M@{ZJ|*+ISlO] GUU\"{r#.MQ7)pa~a+{%}l0\"BjpX7|bP7;EhVJ-cr~YfZQWG\'x1018B:~@;4','2006-10-30','f&sQXC*n%X8|).k_RX~nBTBXV>B_DBGm`!CU$7cRr.R',']ipeNP-','QPk{|v_-uwlQGA=T0gJ?#fu~>KKTe7nmsw,C4#By]M~K w3iFxip*/A^7','52'),
 ('2006-11-18 23:07:23',98,1336365975,'2006-11-14',-815305251,'-j,*A\"nX)54iAtrWh](cH-8e','2006-10-27','VGTE62C:{^##:-$M)r!Rs','^%Vsf4Jt[q)/ZfOM-Xo)b!O1x$G><mZ}?Qo()k:pl!/lF8MPY{\"Oo@{8rF|L:l3myzOu.E39* *U','h$(Wt#]C|A/c1gU&PPyezGrXw]WW;$2%xT95TRA>7?K.,HACnn:9hZU3_z|#Ge~pAjB#hGMexLLb.D+MOx%K7AZr?JF.+]e.O}}n','17');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-06 22:57:12',66,-1663088057,'2006-10-19',735708429,'L1wU*oFTu@0U8|>AB(SyJQYg:1.YpuZ8Tv4;Z','2006-10-23','cQjDNVTs|Tb6~S)o-J(^Hr{7ytP','^+OD!<OVYpLw+JI<GS(`>x:{N9J$I&v+`','3;K*~zPH0^\"]JCIz26 NN:1hfj-g-:05}-','71'),
 ('2006-10-09 22:57:12',12,1316808852,'2006-10-25',1566278290,'2Ooal0.U{aZpZPI4Pl','2006-10-07',']RD~4c!W+ugjy-0uUu~\'EHU-K.pKlZ[(Bx\\+n@4F:r','_o0FR9IccQR$eRwcbj.3*ZlGfy-En+3R3[V','TE!=Zn$iY$@t&','62'),
 ('2006-10-30 22:57:12',38,-970217399,'2006-10-30',-506887361,'q.a?/]}Y*\\PI77H&yj4sEipI=,y|&0[\'ke~3bq\'6J`y1ZF9q=XWw[/poNl-M\"ZuS`BGu;R!\\F|!F4%54hUeeN.>[nl>','2006-10-15','*\\YMLkAPDXEAF-_0Jln?:1H0Cg>$G{::/k&KG-HA9Oc,}j5u)>KP_BUDZS$gj1W>~Q~[<~/;<=O\'x!lYBU ]#9,z','`3^y*\'eQE[:x6E:ZWpF^uvpJE19?J;H1h\"{21DFe\'^]rX&}qGc','sifESj*p89<\'<w`<-1fMV=.xHY;f0Oy9T?*5ve=i:|Vs#v2i\\4\"9#wT8f];[:+9OvYOQiA_I\\T`K','11'),
 ('2006-11-24 23:07:23',-91,-1941023982,'2006-11-21',2066258144,'TykNr&;HE9r7O ~5*5kf4|RA*WwyY8R~1I9e[m??W1Hw(\"=E]~`?v7yx{Q%YlpI','2006-10-12','^;W[|@lZLO*3z%;boqT[Nkqsn1ED7bovU3!u=S1=fdFU>NwK','`rtLvRmht7tX$\'G`*OYapqXDnhYo(\'M}Y%I|','_qd1H','81');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-08 23:07:23',67,1531705372,'2006-10-09',-1196865830,'?,g8UT(4\'|7gd%*%Eu;q>}O</0iRXMo.z4)S\"mq~) B)','2006-11-22','8l`a=wqpH4}!LJ\'~=YPS2Sin','`__wB C+8qN@[\\*O7Ll-e<!7%u','cEN>yJ1M74)DTr|$@z+k\'( K la\\8jyvm-z{PRhC=Tj0AV6HiP:_ #$w8e(=','48'),
 ('2006-11-07 22:57:12',-98,820900434,'2006-10-22',1407264288,':2uTI.MBV#A`lIX1RP.yB{]kC7$hW_G.#bcm*O7Hjyc[','2006-10-24','e\'!?e?86Rgf!ej}[>Nu(ex/}/uw(?,4IO;oe2Z]a!-M89Am)','{UQC oM<t7`)nv(_64;\'A$%=)IwVH(h3.?\"94s+\"blm-,c\'8{[^4\"Hl5b\\[Oe!`D.<Xyok4M9z9Mw%bx6R\")','P[-FwN$%~#~Dp#|_|[9[>\"7nSU>-\\q?zu@V`|o*rN5<(PyJ)c\\>./6\\T&<oUl: K$7<<*.\\\\HV@>|Q (0\"jVD8lw M]6\".h','92'),
 ('2006-10-09 22:57:12',123,-1688109609,'2006-11-01',-160461283,'zv^,=|2a*S(0]wFPVxz}Y','2006-10-06','p{pw}J%CIm','|bf1LpHEa){4fC])!b*jtrqRdMNt|<|(9_Hpl5M3E@,N\'{Ws/f[^y]E]YPa$+\'-ov','sFeEP\'1O[tQQ\"5o&HwhNKJAj 7ZPAktS[A1tT~-vG)!iqP%ulr4^b#rexf)hr4n=4;N','78'),
 ('2006-11-16 22:57:12',126,1122267755,'2006-11-05',-690712431,'`nfrK,Bq7I[.z#M^0.p9{I`4Qar_=0\\1,jqoq.','2006-11-09','?U!(@!nHZ&U1K)>3G0+cvh\\b}lp:M dg7F}^9E_Amr$Py:wc~WX','}D}}qzLx,]h}I','^YmDMWx0gFVo','26');
INSERT INTO `audit_loan` (`returndate`,`isreturned`,`noofrenews`,`duadate`,`totalduedays`,`returnto`,`issuedate`,`issueby`,`itemid`,`username`,`id`) VALUES 
 ('2006-10-17 22:57:12',-117,730917956,'2006-11-09',1230200514,'X/SzF_T5qUE~x-ypo1+g\\d|3}Q\"gF oyrd}|UZ;5{s^\\q0|sfU+q}:-gVKn^G5Kg7kX:Yk {Qsj_0D|DjzfQc@ejMVk-_$`','2006-10-26',' g2QsJ\\WX],-m$;qN4z4\";>}@^=uu(\\`dig+','~~2OBJ!{pY7|q]3z3yaQ','5I\"KLXzH:YvlUY78e{O%K\\!Z*bp61pF\"=xY}exZxGh(g$i~Y|tw>?^E.`}S1veaH]T*$`#j~Z6{IWPHe-}VL','18'),
 ('2006-10-05 23:07:23',21,1046760138,'2006-10-09',1771735512,'\'ft:$hI2aiXXb!Y83^N__tDI#x\\@vrK\\t','2006-11-13','XT<]_f+NQ _WqU.^yq!i&o\'PQLHvPvG\'PYPPl/W;{`\"vv~t?VwWMEVC@\'76:JLuE5-Dt7&+k4*U\\)jccr+=','\'E&1i60n@CrY<z','#SPYgI1F!bf:e|~Ps/SM&7ujxhAv,F4zZY5/,JEH*yEuf+E@EXz0/X1puD[;z~6fl5Wh)','19'),
 ('2006-10-09 22:57:12',-113,1992746192,'2006-10-06',262833262,'t3n!G4!d-B!%$%tZ940`&l6^VP8Lu!Z}','2006-10-19',':NBPbt:BDD^Y&@iq[P!w:QS0eeRbsUD0N*]T~\\a6(}8#F',']/FhMgUhbiN Ypywk\")8HOK~/]>4Znl_I>bBL9PKFb_>!rNAuS_/+v',',Z:cC^msZ1mQ','21');
/*!40000 ALTER TABLE `audit_loan` ENABLE KEYS */;


--
-- Table structure for table `library3`.`author`
--

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `itemid` varchar(255) NOT NULL,
  `authorname` varchar(255) NOT NULL,
  `description` varchar(45) default NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`itemid`,`authorname`),
  CONSTRAINT `FK_author_1` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 11264 kB';

--
-- Dumping data for table `library3`.`author`
--

/*!40000 ALTER TABLE `author` DISABLE KEYS */;
/*!40000 ALTER TABLE `author` ENABLE KEYS */;


--
-- Table structure for table `library3`.`authority`
--

DROP TABLE IF EXISTS `authority`;
CREATE TABLE `authority` (
  `username` varchar(255) NOT NULL,
  `authorityname` varchar(255) NOT NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`username`,`authorityname`),
  CONSTRAINT `FK_authority_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`authority`
--

/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;


--
-- Table structure for table `library3`.`department`
--

DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `deptname` varchar(255) NOT NULL default '',
  `deptdesc` varchar(255) NOT NULL,
  `creationDate` datetime default NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`deptname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`department`
--

/*!40000 ALTER TABLE `department` DISABLE KEYS */;
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


--
-- Table structure for table `library3`.`item`
--

DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `itemid` varchar(255) NOT NULL,
  `isenabled` tinyint(1) default '0',
  `description` varchar(255) default NULL,
  `itemtype` varchar(255) NOT NULL,
  `yearofissue` int(11) default NULL,
  `groupname` varchar(255) default 'general',
  `title` varchar(255) NOT NULL,
  `status` varchar(255) default NULL,
  `dateOfIssue` date default NULL,
  `isbn` varchar(255) default NULL,
  `publisher` varchar(255) default NULL,
  `edition` varchar(255) default NULL,
  `location` varchar(45) default NULL,
  `id` int(10) unsigned default NULL,
  `cost` double default NULL,
  PRIMARY KEY  (`itemid`),
  KEY `FK_item_1` (`groupname`),
  KEY `FK_item_2` (`itemtype`),
  CONSTRAINT `FK_item_1` FOREIGN KEY (`groupname`) REFERENCES `item_group` (`groupname`),
  CONSTRAINT `FK_item_2` FOREIGN KEY (`itemtype`) REFERENCES `item_types` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`item`
--

/*!40000 ALTER TABLE `item` DISABLE KEYS */;
/*!40000 ALTER TABLE `item` ENABLE KEYS */;


--
-- Table structure for table `library3`.`item_group`
--

DROP TABLE IF EXISTS `item_group`;
CREATE TABLE `item_group` (
  `groupname` varchar(255) NOT NULL,
  `description` varchar(255) default NULL,
  `noofdays` int(11) NOT NULL default '0',
  `possibleRenews` int(10) unsigned NOT NULL default '0',
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`groupname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`item_group`
--

/*!40000 ALTER TABLE `item_group` DISABLE KEYS */;
INSERT INTO `item_group` (`groupname`,`description`,`noofdays`,`possibleRenews`,`id`) VALUES 
 ('apple','lkj',22,2,1),
 ('apple1','lkj',22,2,2),
 ('apple11','lkj',22,2,3),
 ('apple14','lkj',22,2,4),
 ('reference','hamed',12,3,5);
/*!40000 ALTER TABLE `item_group` ENABLE KEYS */;


--
-- Table structure for table `library3`.`item_types`
--

DROP TABLE IF EXISTS `item_types`;
CREATE TABLE `item_types` (
  `name` varchar(45) NOT NULL default '',
  `description` text,
  `creationDate` datetime default NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`item_types`
--

/*!40000 ALTER TABLE `item_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `item_types` ENABLE KEYS */;


--
-- Table structure for table `library3`.`loan`
--

DROP TABLE IF EXISTS `loan`;
CREATE TABLE `loan` (
  `returndate` datetime default NULL,
  `isreturned` tinyint(1) default NULL,
  `noofrenews` int(11) default NULL,
  `duedate` date NOT NULL default '0000-00-00',
  `totalduedays` int(11) default NULL,
  `returnto` varchar(255) default NULL,
  `issuedate` date default NULL,
  `issueby` varchar(255) NOT NULL,
  `itemid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `id` int(11) default NULL,
  PRIMARY KEY  (`itemid`,`username`),
  KEY `FK_loan_2` (`username`),
  CONSTRAINT `FK_loan_1` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`),
  CONSTRAINT `FK_loan_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 11264 kB';

--
-- Dumping data for table `library3`.`loan`
--

/*!40000 ALTER TABLE `loan` DISABLE KEYS */;
/*!40000 ALTER TABLE `loan` ENABLE KEYS */;


--
-- Table structure for table `library3`.`options`
--

DROP TABLE IF EXISTS `options`;
CREATE TABLE `options` (
  `name` varchar(45) NOT NULL default '',
  `description` varchar(45) default NULL,
  PRIMARY KEY  (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`options`
--

/*!40000 ALTER TABLE `options` DISABLE KEYS */;
/*!40000 ALTER TABLE `options` ENABLE KEYS */;


--
-- Table structure for table `library3`.`payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment` (
  `fine` int(11) NOT NULL,
  `paidto` varchar(255) NOT NULL,
  `describ` varchar(255) default NULL,
  `daysfor` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `date` datetime NOT NULL default '0000-00-00 00:00:00',
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`username`,`date`),
  KEY `FK_payment_2` (`paidto`),
  CONSTRAINT `FK_payment_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`),
  CONSTRAINT `FK_payment_2` FOREIGN KEY (`paidto`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 11264 kB';

--
-- Dumping data for table `library3`.`payment`
--

/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;


--
-- Table structure for table `library3`.`phone`
--

DROP TABLE IF EXISTS `phone`;
CREATE TABLE `phone` (
  `type` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `phoneNo` varchar(255) NOT NULL,
  `creationDate` datetime default NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`username`,`phoneNo`),
  KEY `FK_phone_2` (`type`),
  CONSTRAINT `FK_phone_2` FOREIGN KEY (`type`) REFERENCES `phone_types` (`type`),
  CONSTRAINT `FK_phone_1` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`phone`
--

/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;


--
-- Table structure for table `library3`.`phone_types`
--

DROP TABLE IF EXISTS `phone_types`;
CREATE TABLE `phone_types` (
  `id` int(10) unsigned default NULL,
  `type` varchar(45) NOT NULL default '',
  `description` varchar(45) default NULL,
  PRIMARY KEY  (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`phone_types`
--

/*!40000 ALTER TABLE `phone_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `phone_types` ENABLE KEYS */;


--
-- Table structure for table `library3`.`renew`
--

DROP TABLE IF EXISTS `renew`;
CREATE TABLE `renew` (
  `renewdate` datetime default NULL,
  `itemid` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`itemid`,`username`),
  KEY `FK_renew_2` (`username`),
  CONSTRAINT `FK_renew_1` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`),
  CONSTRAINT `FK_renew_2` FOREIGN KEY (`username`) REFERENCES `user` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='InnoDB free: 11264 kB';

--
-- Dumping data for table `library3`.`renew`
--

/*!40000 ALTER TABLE `renew` DISABLE KEYS */;
/*!40000 ALTER TABLE `renew` ENABLE KEYS */;


--
-- Table structure for table `library3`.`topic`
--

DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `itemid` varchar(255) NOT NULL,
  `topic` varchar(255) NOT NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`itemid`,`topic`),
  CONSTRAINT `FK_topic_1` FOREIGN KEY (`itemid`) REFERENCES `item` (`itemid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`topic`
--

/*!40000 ALTER TABLE `topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `topic` ENABLE KEYS */;


--
-- Table structure for table `library3`.`user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `campus` varchar(255) default NULL,
  `department` varchar(255) default NULL,
  `password` varchar(255) NOT NULL,
  `type` varchar(255) default NULL,
  `firstname` varchar(255) NOT NULL,
  `totalduedays` int(11) default NULL,
  `middlename` varchar(255) default NULL,
  `enabled` tinyint(1) NOT NULL default '0',
  `id` int(11) default NULL,
  `groupname` varchar(255) default NULL,
  `dob` date default NULL,
  `dateOfJoin` date default NULL,
  `lastname` varchar(255) NOT NULL,
  `motherName` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `sex` varchar(10) default NULL,
  `favouriteColor` varchar(45) default NULL,
  `favouriteBook` varchar(45) default NULL,
  `favouritePlace` varchar(45) default NULL,
  `passwordEncrypted` tinyint(1) default NULL,
  PRIMARY KEY  (`username`),
  KEY `FK_users_1` (`groupname`),
  KEY `FK_users_2` (`type`),
  CONSTRAINT `FK_user_2` FOREIGN KEY (`groupname`) REFERENCES `user_group` (`groupname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`user`
--

/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`username`,`campus`,`department`,`password`,`type`,`firstname`,`totalduedays`,`middlename`,`enabled`,`id`,`groupname`,`dob`,`dateOfJoin`,`lastname`,`motherName`,`title`,`email`,`sex`,`favouriteColor`,`favouriteBook`,`favouritePlace`,`passwordEncrypted`) VALUES 
 ('apple','jntu',NULL,'apple','student','firstname',9,NULL,1,4,'goup1',NULL,NULL,'lastname',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),
 ('apple1','jntu','cse','password','student','firstname',NULL,NULL,1,5,'goup1',NULL,NULL,'lastname',NULL,NULL,'nothing','male',NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;


--
-- Table structure for table `library3`.`user_group`
--

DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `groupname` varchar(255) NOT NULL,
  `groupdesc` varchar(255) default NULL,
  `maxnoofbooks` int(11) NOT NULL,
  `id` int(10) unsigned default NULL,
  PRIMARY KEY  (`groupname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `library3`.`user_group`
--

/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` (`groupname`,`groupdesc`,`maxnoofbooks`,`id`) VALUES 
 ('goup1','nothing',9,NULL);
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
