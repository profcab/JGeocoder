import java.sql.Statement
import java.util.regex.Matcher
import java.util.regex.Pattern
import java.sql.ResultSet
import java.sql.Driver
import java.sql.DriverManager
import java.sql.Connection
import java.util.zip.ZipFile
import java.util.zip.ZipEntry
import TigerDefinition
import TigerTable
import groovy.sql.Sql
import org.apache.commons.lang.StringUtils


def nvl(def val, def replacement){ val==null?replacement:val}

return; //comment out to run

/**
 * the raw output has total=2627282 = 441M
 * with sort | uniq will cut it down to 2588852 = 433M
 */

Config config = new Config()
//return;  //comment out to run

DriverManager.registerDriver((Driver)getClass().getClassLoader().loadClass(config.driverClass).newInstance())
//this is calling a private method of DriverManager :D 
//because the maven classloader does not have the driver class
Connection conn = DriverManager.getConnection(config.connectionString, 
    new Properties(), getClass().getClassLoader())
    
Sql sql = new Sql(conn)
sql.execute("""
    create table TIGER_PA ( TLID numeric not null, 
        ARIDL  varchar(22),
        ARIDR  varchar(22),
        LINEARID  varchar(22),
        FULLNAME  varchar(100),
        FRADDL numeric, 
        TOADDL  numeric,
        FRADDR  numeric,
        TOADDR  numeric,
        ZIPL varchar(5),
        ZIPR varchar (5),
        EDGEMTFCC  varchar(5),
        PARITYL varchar(1), 
        PARITYR  varchar(1), 
        PLUS4L  varchar(4),
        PLUS4R  varchar(4),
        LFROMTYP  varchar(1),
        LTOTYP  varchar(1),
        RFROMTYP  varchar(1),
        RTOTYP  varchar(1),
        OFFSETL  varchar(1),
        OFFSETR  varchar(1),
        BBOX numeric
        NUMPARTS numeric,
        SHAPETYPE numeric, 
        LAT1 numeric,
        LONG1 numeric,
        LAT2 numeric,
        LONG2 numeric,
        LAT3 numeric,
        LONG3 numeric,
        LAT4 numeric,
        LONG4 numeric,
        LAT5 numeric,
        LONG5 numeric,
        LAT6 numeric,
        LONG6 numeric,
        LAT7 numeric,
        LONG7 numeric,
        LAT8 numeric,
        LONG8 numeric,
        LAT9 numeric,
        LONG9 numeric,
        LAT10 numeric,
        LONG10 numeric,
        LAT11 numeric,
        LONG11 numeric,
        LAT12 numeric,
        LONG12 numeric,
        LAT13 numeric,
        LONG13 numeric,
        LAT14 numeric,
        LONG14 numeric,
        LAT15 numeric,
        LONG15 numeric,
        LAT16 numeric,
        LONG16 numeric,
        LAT17 numeric,
        LONG17 numeric,
        LAT18 numeric,
        LONG18 numeric,
        LAT19 numeric,
        LONG19 numeric,
        LAT20 numeric,
        LONG20 numeric,
        LAT21 numeric,
        LONG21 numeric,
        LAT22 numeric,
        LONG22 numeric,
        LAT23 numeric,
        LONG23 numeric,
        LAT24 numeric,
        LONG24 numeric,
        LAT25 numeric,
        LONG25 numeric,
        LAT26 numeric,
        LONG26 numeric,
        LAT27 numeric,
        LONG27 numeric,
        LAT28 numeric,
        LONG28 numeric,
        LAT29 numeric,
        LONG29 numeric,
        LAT30 numeric,
        LONG30 numeric,
        LAT31 numeric,
        LONG31 numeric,
        LAT32 numeric,
        LONG32 numeric,
        LAT33 numeric,
        LONG33 numeric,
        LAT34 numeric,
        LONG34 numeric,
        LAT35 numeric,
        LONG35 numeric,
        LAT36 numeric,
        LONG36 numeric,
        LAT37 numeric,
        LONG37 numeric,
        LAT38 numeric,
        LONG38 numeric,
        LAT39 numeric,
        LONG39 numeric,
        LAT40 numeric,
        LONG40 numeric,
        LAT41 numeric,
        LONG41 numeric,
        LAT42 numeric,
        LONG42 numeric,
        LAT43 numeric,
        LONG43 numeric,
        LAT44 numeric,
        LONG44 numeric,
        LAT45 numeric,
        LONG45 numeric,
        LAT46 numeric,
        LONG46 numeric,
        LAT47 numeric,
        LONG47 numeric,
        LAT48 numeric,
        LONG48 numeric,
        LAT49 numeric,
        LONG49 numeric,
        LAT50 numeric,
        LONG50 numeric,
        LAT51 numeric,
        LONG51 numeric,
        LAT52 numeric,
        LONG52 numeric,
        LAT53 numeric,
        LONG53 numeric,
        LAT54 numeric,
        LONG54 numeric,
        LAT55 numeric,
        LONG55 numeric,
        LAT56 numeric,
        LONG56 numeric,
        LAT57 numeric,
        LONG57 numeric,
        LAT58 numeric,
        LONG58 numeric,
        LAT59 numeric,
        LONG59 numeric,
        LAT60 numeric,
        LONG60 numeric,
        LAT61 numeric,
        LONG61 numeric,
        LAT62 numeric,
        LONG62 numeric,
        LAT63 numeric,
        LONG63 numeric,
        LAT64 numeric,
        LONG64 numeric,
        LAT65 numeric,
        LONG65 numeric,
        LAT66 numeric,
        LONG66 numeric,
        LAT67 numeric,
        LONG67 numeric,
        LAT68 numeric,
        LONG68 numeric,
        LAT69 numeric,
        LONG69 numeric,
        LAT70 numeric,
        LONG70 numeric,
        LAT71 numeric,
        LONG71 numeric,
        LAT72 numeric,
        LONG72 numeric,
        LAT73 numeric,
        LONG73 numeric,
        LAT74 numeric,
        LONG74 numeric,
        LAT75 numeric,
        LONG75 numeric,
        LAT76 numeric,
        LONG76 numeric,
        LAT77 numeric,
        LONG77 numeric,
        LAT78 numeric,
        LONG78 numeric,
        LAT79 numeric,
        LONG79 numeric,
        LAT80 numeric,
        LONG80 numeric,
        LAT81 numeric,
        LONG81 numeric,
        LAT82 numeric,
        LONG82 numeric,
        LAT83 numeric,
        LONG83 numeric,
        LAT84 numeric,
        LONG84 numeric,
        LAT85 numeric,
        LONG85 numeric,
        LAT86 numeric,
        LONG86 numeric,
        LAT87 numeric,
        LONG87 numeric,
        LAT88 numeric,
        LONG88 numeric,
        LAT89 numeric,
        LONG89 numeric,
        LAT90 numeric,
        LONG90 numeric,
        LAT91 numeric,
        LONG91 numeric,
        LAT92 numeric,
        LONG92 numeric,
        LAT93 numeric,
        LONG93 numeric,
        LAT94 numeric,
        LONG94 numeric,
        LAT95 numeric,
        LONG95 numeric,
        LAT96 numeric,
        LONG96 numeric,
        LAT97 numeric,
        LONG97 numeric,
        LAT98 numeric,
        LONG98 numeric,
        LAT99 numeric,
        LONG99 numeric,
        LAT100 numeric,
        LONG100 numeric,
        LAT101 numeric,
        LONG101 numeric,
        LAT102 numeric,
        LONG102 numeric,
        LAT103 numeric,
        LONG103 numeric,
        LAT104 numeric,
        LONG104 numeric,
        LAT105 numeric,
        LONG105 numeric,
        LAT106 numeric,
        LONG106 numeric,
        LAT107 numeric,
        LONG107 numeric,
        LAT108 numeric,
        LONG108 numeric,
        LAT109 numeric,
        LONG109 numeric,
        LAT110 numeric,
        LONG110 numeric,
        LAT111 numeric,
        LONG111 numeric,
        LAT112 numeric,
        LONG112 numeric,
        LAT113 numeric,
        LONG113 numeric,
        LAT114 numeric,
        LONG114 numeric,
        LAT115 numeric,
        LONG115 numeric,
        LAT116 numeric,
        LONG116 numeric,
        LAT117 numeric,
        LONG117 numeric,
        LAT118 numeric,
        LONG118 numeric,
        LAT119 numeric,
        LONG119 numeric,
        LAT120 numeric,
        LONG120 numeric,
        LAT121 numeric,
        LONG121 numeric,
        LAT122 numeric,
        LONG122 numeric,
        LAT123 numeric,
        LONG123 numeric,
        LAT124 numeric,
        LONG124 numeric,
        LAT125 numeric,
        LONG125 numeric,
        LAT126 numeric,
        LONG126 numeric,
        LAT127 numeric,
        LONG127 numeric,
        LAT128 numeric,
        LONG128 numeric,
        LAT129 numeric,
        LONG129 numeric,
        LAT130 numeric,
        LONG130 numeric,
        LAT131 numeric,
        LONG131 numeric,
        LAT132 numeric,
        LONG132 numeric,
        LAT133 numeric,
        LONG133 numeric,
        LAT134 numeric,
        LONG134 numeric,
        LAT135 numeric,
        LONG135 numeric,
        LAT136 numeric,
        LONG136 numeric,
        LAT137 numeric,
        LONG137 numeric,
        LAT138 numeric,
        LONG138 numeric,
        LAT139 numeric,
        LONG139 numeric,
        LAT140 numeric,
        LONG140 numeric,
        LAT141 numeric,
        LONG141 numeric,
        LAT142 numeric,
        LONG142 numeric,
        LAT143 numeric,
        LONG143 numeric,
        LAT144 numeric,
        LONG144 numeric,
        LAT145 numeric,
        LONG145 numeric,
        LAT146 numeric,
        LONG146 numeric,
        LAT147 numeric,
        LONG147 numeric,
        LAT148 numeric,
        LONG148 numeric,
        LAT149 numeric,
        LONG149 numeric,
        LAT150 numeric,
        LONG150 numeric,
        LAT151 numeric,
        LONG151 numeric,
        LAT152 numeric,
        LONG152 numeric,
        LAT153 numeric,
        LONG153 numeric,
        LAT154 numeric,
        LONG154 numeric,
        LAT155 numeric,
        LONG155 numeric,
        LAT156 numeric,
        LONG156 numeric,
        LAT157 numeric,
        LONG157 numeric,
        LAT158 numeric,
        LONG158 numeric,
        LAT159 numeric,
        LONG159 numeric,
        LAT160 numeric,
        LONG160 numeric,
        LAT161 numeric,
        LONG161 numeric,
        LAT162 numeric,
        LONG162 numeric,
        LAT163 numeric,
        LONG163 numeric,
        LAT164 numeric,
        LONG164 numeric,
        LAT165 numeric,
        LONG165 numeric,
        LAT166 numeric,
        LONG166 numeric,
        LAT167 numeric,
        LONG167 numeric,
        LAT168 numeric,
        LONG168 numeric,
        LAT169 numeric,
        LONG169 numeric,
        LAT170 numeric,
        LONG170 numeric,
        LAT171 numeric,
        LONG171 numeric,
        LAT172 numeric,
        LONG172 numeric,
        LAT173 numeric,
        LONG173 numeric,
        LAT174 numeric,
        LONG174 numeric,
        LAT175 numeric,
        LONG175 numeric,
        LAT176 numeric,
        LONG176 numeric,
        LAT177 numeric,
        LONG177 numeric,
        LAT178 numeric,
        LONG178 numeric,
        LAT179 numeric,
        LONG179 numeric,
        LAT180 numeric,
        LONG180 numeric,
        LAT181 numeric,
        LONG181 numeric,
        LAT182 numeric,
        LONG182 numeric,
        LAT183 numeric,
        LONG183 numeric,
        LAT184 numeric,
        LONG184 numeric,
        LAT185 numeric,
        LONG185 numeric,
        LAT186 numeric,
        LONG186 numeric,
        LAT187 numeric,
        LONG187 numeric,
        LAT188 numeric,
        LONG188 numeric,
        LAT189 numeric,
        LONG189 numeric,
        LAT190 numeric,
        LONG190 numeric,
        LAT191 numeric,
        LONG191 numeric,
        LAT192 numeric,
        LONG192 numeric,
        LAT193 numeric,
        LONG193 numeric,
        LAT194 numeric,
        LONG194 numeric,
        LAT195 numeric,
        LONG195 numeric,
        LAT196 numeric,
        LONG196 numeric,
        LAT197 numeric,
        LONG197 numeric,
        LAT198 numeric,
        LONG198 numeric,
        LAT199 numeric,
        LONG199 numeric,
        LAT200 numeric,
        LONG200 numeric,
        LAT201 numeric,
        LONG201 numeric,
        LAT202 numeric,
        LONG202 numeric,
        LAT203 numeric,
        LONG203 numeric,
        LAT204 numeric,
        LONG204 numeric,
        LAT205 numeric,
        LONG205 numeric,
        LAT206 numeric,
        LONG206 numeric,
        LAT207 numeric,
        LONG207 numeric,
        LAT208 numeric,
        LONG208 numeric,
        LAT209 numeric,
        LONG209 numeric,
        LAT210 numeric,
        LONG210 numeric,
        LAT211 numeric,
        LONG211 numeric,
        LAT212 numeric,
        LONG212 numeric,
        LAT213 numeric,
        LONG213 numeric,
        LAT214 numeric,
        LONG214 numeric,
        LAT215 numeric,
        LONG215 numeric,
        LAT216 numeric,
        LONG216 numeric,
        LAT217 numeric,
        LONG217 numeric,
        LAT218 numeric,
        LONG218 numeric,
        LAT219 numeric,
        LONG219 numeric,
        LAT220 numeric,
        LONG220 numeric,
        LAT221 numeric,
        LONG221 numeric,
        LAT222 numeric,
        LONG222 numeric,
        LAT223 numeric,
        LONG223 numeric,
        LAT224 numeric,
        LONG224 numeric,
        LAT225 numeric,
        LONG225 numeric,
        LAT226 numeric,
        LONG226 numeric,
        LAT227 numeric,
        LONG227 numeric,
        LAT228 numeric,
        LONG228 numeric,
        LAT229 numeric,
        LONG229 numeric,
        LAT230 numeric,
        LONG230 numeric,
        LAT231 numeric,
        LONG231 numeric,
        LAT232 numeric,
        LONG232 numeric,
        LAT233 numeric,
        LONG233 numeric,
        LAT234 numeric,
        LONG234 numeric,
        LAT235 numeric,
        LONG235 numeric,
        LAT236 numeric,
        LONG236 numeric,
        LAT237 numeric,
        LONG237 numeric,
        LAT238 numeric,
        LONG238 numeric,
        LAT239 numeric,
        LONG239 numeric);
        """)
        
        

PrintStream ps = new PrintStream(new BufferedOutputStream(new FileOutputStream(/error.csv/)))
int total = 0, error =0;
new File(/tiger_main.csv/).eachLine{
  if((total++)%10000==0){
    println total
    sql.commit()
  }
  def values = it.split(',')
  values.eachWithIndex(){v, idx->
    if(StringUtils.isBlank(v) || v=='null'){ values[idx] = null}
  }
  values = Arrays.asList(values)
  try {

    sql.execute("""
  insert into TIGER_PA( 
      TLID,
      FEDIRP,
      FENAME,
      FETYPE,
      FEDIRS,
      FRADDL,
      TOADDL,
      FRADDR,
      TOADDR,
      ZIPL,
      ZIPR,
      FRLONG,
      FRLAT,
      TOLONG,
      TOLAT,
        LAT1,
        LONG1,
        LAT2,
        LONG2,
        LAT3,
        LONG3,
        LAT4,
        LONG4,
        LAT5,
        LONG5,
        LAT6,
        LONG6,
        LAT7,
        LONG7,
        LAT8,
        LONG8,
        LAT9,
        LONG9,
        LAT10,
        LONG10,
        LAT11,
        LONG11,
        LAT12,
        LONG12,
        LAT13,
        LONG13,
        LAT14,
        LONG14,
        LAT15,
        LONG15,
        LAT16,
        LONG16,
        LAT17,
        LONG17,
        LAT18,
        LONG18,
        LAT19,
        LONG19,
        LAT20,
        LONG20,
        LAT21,
        LONG21,
        LAT22,
        LONG22,
        LAT23,
        LONG23,
        LAT24,
        LONG24,
        LAT25,
        LONG25,
        LAT26,
        LONG26,
        LAT27,
        LONG27,
        LAT28,
        LONG28,
        LAT29,
        LONG29,
        LAT30,
        LONG30,
        LAT31,
        LONG31,
        LAT32,
        LONG32,
        LAT33,
        LONG33,
        LAT34,
        LONG34,
        LAT35,
        LONG35,
        LAT36,
        LONG36,
        LAT37,
        LONG37,
        LAT38,
        LONG38,
        LAT39,
        LONG39,
        LAT40,
        LONG40,
        LAT41,
        LONG41,
        LAT42,
        LONG42,
        LAT43,
        LONG43,
        LAT44,
        LONG44,
        LAT45,
        LONG45,
        LAT46,
        LONG46,
        LAT47,
        LONG47,
        LAT48,
        LONG48,
        LAT49,
        LONG49,
        LAT50,
        LONG50,
        LAT51,
        LONG51,
        LAT52,
        LONG52,
        LAT53,
        LONG53,
        LAT54,
        LONG54,
        LAT55,
        LONG55,
        LAT56,
        LONG56,
        LAT57,
        LONG57,
        LAT58,
        LONG58,
        LAT59,
        LONG59,
        LAT60,
        LONG60,
        LAT61,
        LONG61,
        LAT62,
        LONG62,
        LAT63,
        LONG63,
        LAT64,
        LONG64,
        LAT65,
        LONG65,
        LAT66,
        LONG66,
        LAT67,
        LONG67,
        LAT68,
        LONG68,
        LAT69,
        LONG69,
        LAT70,
        LONG70,
        LAT71,
        LONG71,
        LAT72,
        LONG72,
        LAT73,
        LONG73,
        LAT74,
        LONG74,
        LAT75,
        LONG75,
        LAT76,
        LONG76,
        LAT77,
        LONG77,
        LAT78,
        LONG78,
        LAT79,
        LONG79,
        LAT80,
        LONG80,
        LAT81,
        LONG81,
        LAT82,
        LONG82,
        LAT83,
        LONG83,
        LAT84,
        LONG84,
        LAT85,
        LONG85,
        LAT86,
        LONG86,
        LAT87,
        LONG87,
        LAT88,
        LONG88,
        LAT89,
        LONG89,
        LAT90,
        LONG90,
        LAT91,
        LONG91,
        LAT92,
        LONG92,
        LAT93,
        LONG93,
        LAT94,
        LONG94,
        LAT95,
        LONG95,
        LAT96,
        LONG96,
        LAT97,
        LONG97,
        LAT98,
        LONG98,
        LAT99,
        LONG99,
        LAT100,
        LONG100,
        LAT101,
        LONG101,
        LAT102,
        LONG102,
        LAT103,
        LONG103,
        LAT104,
        LONG104,
        LAT105,
        LONG105,
        LAT106,
        LONG106,
        LAT107,
        LONG107,
        LAT108,
        LONG108,
        LAT109,
        LONG109,
        LAT110,
        LONG110,
        LAT111,
        LONG111,
        LAT112,
        LONG112,
        LAT113,
        LONG113,
        LAT114,
        LONG114,
        LAT115,
        LONG115,
        LAT116,
        LONG116,
        LAT117,
        LONG117,
        LAT118,
        LONG118,
        LAT119,
        LONG119,
        LAT120,
        LONG120,
        LAT121,
        LONG121,
        LAT122,
        LONG122,
        LAT123,
        LONG123,
        LAT124,
        LONG124,
        LAT125,
        LONG125,
        LAT126,
        LONG126,
        LAT127,
        LONG127,
        LAT128,
        LONG128,
        LAT129,
        LONG129,
        LAT130,
        LONG130,
        LAT131,
        LONG131,
        LAT132,
        LONG132,
        LAT133,
        LONG133,
        LAT134,
        LONG134,
        LAT135,
        LONG135,
        LAT136,
        LONG136,
        LAT137,
        LONG137,
        LAT138,
        LONG138,
        LAT139,
        LONG139,
        LAT140,
        LONG140,
        LAT141,
        LONG141,
        LAT142,
        LONG142,
        LAT143,
        LONG143,
        LAT144,
        LONG144,
        LAT145,
        LONG145,
        LAT146,
        LONG146,
        LAT147,
        LONG147,
        LAT148,
        LONG148,
        LAT149,
        LONG149,
        LAT150,
        LONG150,
        LAT151,
        LONG151,
        LAT152,
        LONG152,
        LAT153,
        LONG153,
        LAT154,
        LONG154,
        LAT155,
        LONG155,
        LAT156,
        LONG156,
        LAT157,
        LONG157,
        LAT158,
        LONG158,
        LAT159,
        LONG159,
        LAT160,
        LONG160,
        LAT161,
        LONG161,
        LAT162,
        LONG162,
        LAT163,
        LONG163,
        LAT164,
        LONG164,
        LAT165,
        LONG165,
        LAT166,
        LONG166,
        LAT167,
        LONG167,
        LAT168,
        LONG168,
        LAT169,
        LONG169,
        LAT170,
        LONG170,
        LAT171,
        LONG171,
        LAT172,
        LONG172,
        LAT173,
        LONG173,
        LAT174,
        LONG174,
        LAT175,
        LONG175,
        LAT176,
        LONG176,
        LAT177,
        LONG177,
        LAT178,
        LONG178,
        LAT179,
        LONG179,
        LAT180,
        LONG180,
        LAT181,
        LONG181,
        LAT182,
        LONG182,
        LAT183,
        LONG183,
        LAT184,
        LONG184,
        LAT185,
        LONG185,
        LAT186,
        LONG186,
        LAT187,
        LONG187,
        LAT188,
        LONG188,
        LAT189,
        LONG189,
        LAT190,
        LONG190,
        LAT191,
        LONG191,
        LAT192,
        LONG192,
        LAT193,
        LONG193,
        LAT194,
        LONG194,
        LAT195,
        LONG195,
        LAT196,
        LONG196,
        LAT197,
        LONG197,
        LAT198,
        LONG198,
        LAT199,
        LONG199,
        LAT200,
        LONG200,
        LAT201,
        LONG201,
        LAT202,
        LONG202,
        LAT203,
        LONG203,
        LAT204,
        LONG204,
        LAT205,
        LONG205,
        LAT206,
        LONG206,
        LAT207,
        LONG207,
        LAT208,
        LONG208,
        LAT209,
        LONG209,
        LAT210,
        LONG210,
        LAT211,
        LONG211,
        LAT212,
        LONG212,
        LAT213,
        LONG213,
        LAT214,
        LONG214,
        LAT215,
        LONG215,
        LAT216,
        LONG216,
        LAT217,
        LONG217,
        LAT218,
        LONG218,
        LAT219,
        LONG219,
        LAT220,
        LONG220,
        LAT221,
        LONG221,
        LAT222,
        LONG222,
        LAT223,
        LONG223,
        LAT224,
        LONG224,
        LAT225,
        LONG225,
        LAT226,
        LONG226,
        LAT227,
        LONG227,
        LAT228,
        LONG228,
        LAT229,
        LONG229,
        LAT230,
        LONG230,
        LAT231,
        LONG231,
        LAT232,
        LONG232,
        LAT233,
        LONG233,
        LAT234,
        LONG234,
        LAT235,
        LONG235,
        LAT236,
        LONG236,
        LAT237,
        LONG237,
        LAT238,
        LONG238,
        LAT239,
        LONG239) 
  values (?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ,?,?,?,?,?,?,?,?,
  ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
   ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
    ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
     ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
      ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
       ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
        ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
         ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
          ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?
           ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?, ?,?,?,?,?)
        """, values)
  } catch (Exception e) {
    error++
    ps.println it
      println e.message
  }
}

println "total="+total
println "error="+error

println 'creating indicies on TIGER_PA'
sql.execute('create index IDX0_TIGER_PA on TIGER_PA(tlid)')
sql.execute('create index IDX1_TIGER_PA on TIGER_PA(fename)')
sql.execute('create index IDX2_TIGER_PA on TIGER_PA(fraddL)')
sql.execute('create index IDX3_TIGER_PA on TIGER_PA(toaddL)')
sql.execute('create index IDX4_TIGER_PA on TIGER_PA(fraddR)')
sql.execute('create index IDX5_TIGER_PA on TIGER_PA(toaddR)')
sql.execute('create index IDX6_TIGER_PA on TIGER_PA(zipL)')
sql.execute('create index IDX7_TIGER_PA on TIGER_PA(zipR)')

sql.close()
ps.close()