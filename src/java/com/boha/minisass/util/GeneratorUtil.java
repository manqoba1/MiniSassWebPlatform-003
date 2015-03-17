/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.util;

import com.boha.minisass.data.Country;
import com.boha.minisass.data.River;
import com.boha.minisass.data.Team;
import com.boha.minisass.data.Teammember;
import com.boha.minisass.dto.RiverDTO;
import com.boha.minisass.dto.TeamDTO;
import com.boha.minisass.dto.TeamMemberDTO;
import com.boha.minisass.transfer.ResponseDTO;
import static com.boha.minisass.util.DataUtil.log;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author CodeTribe1
 */

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class GeneratorUtil {
   
    
        //constants array firstNames strings
public  String[] firstNames = { "george","kim","kurisani","Sfiso","Chris","Thabang","Hosea",
    "Siya","Danny","Ross","Gee","Curtis","Jojo","Aubrey","Rulani","Dereck","Lee","Jacob","Julius"};

//constants array surnames strings
public  String[] surnaames = {"kapoya","Chauke","Smith","Matsobane","Zuma","Malema","Maluleka",
           "Modise","Kekana","Lawrence","Willis","Chan","Lee","Ford","Haris","Malabie","Jackson"};
   

//constants array team strings
public String[] TeamNames = {"Mabopane High shcool","Hoër Volkskool","Funda High School",
    "Mhlotshana High School","Laudium Secondary School","Giyani High School",
    "Capricon High School",
    "Makgongoana High School","Maroba High School","Thagaetala High School",
    "Hoërskool Wesvalia ","Amnesty International ",
    "Conquest For Life","love life","Pink drive","Sonlife Africa","Yazisa",
    "Kasi Hive","CodeTribe","Geekulcha","Muxum","Think Bike!","UNDP","Red Cross","Africa United"};

 //create random new random number generator for use in generateProject method
     public static final Random randomNumbers = new Random();
    @PersistenceContext
    EntityManager em;
    
    public ResponseDTO generateTeam(ListUtil listUtil,TeamDTO team, TeamMemberDTO teamMemberDTO)  throws DataException{
        log.log(Level.OFF, "*******Attempt to generate team.........");
        ResponseDTO resp = new ResponseDTO();
        try{
            for (int i = 0; i < 20; i++){
        Team t = new Team();
        t.setTeamName(TeamNames[randomNumbers.nextInt(TeamNames.length - 1)]);
        em.persist(t);
        em.flush();  
        //TeamMembers    
      Teammember tm = new Teammember();
      tm.setFirstName(firstNames[randomNumbers.nextInt(firstNames.length - 1)]);
      tm.setLastName(surnaames[randomNumbers.nextInt(surnaames.length - 1)]);
      tm.setEmail(firstNames[randomNumbers.nextInt(firstNames.length - 1)]+ "@gmail.com");
      tm.setCellphone("076 234 451" + randomNumbers.nextInt() );
      tm.setDateRegistered(new Date());
      tm.setPin("123" + randomNumbers.nextInt());
      tm.setActiveFlag(randomNumbers.nextInt()); 
            }
        } catch (PersistenceException e){    
       log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to generate TeaM\n" );
        }    
     return  resp;
    }    
    
     public ResponseDTO generateRiver(RiverDTO riv) throws DataException {
          log.log(Level.OFF, "*******Attempt to generate a river.........");
        ResponseDTO resp = new ResponseDTO();
        
        try{
            for (int i = 0; i < 20; i++){
              River ri = new River();
            Country cou = em.find(Country.class, riv.getOriginCountryID());
            ri.setDateRegistered(new Date());
           // ri.setEndLongitude(riv.getEndLongitude());
            //ri.setEndLatitude(riv.getEndLatitude());
            ri.setEndCountry(cou);
            ri.setRiverName(riverNames[randomNumbers.nextInt(riverNames.length -1)]);
           // ri.setOriginLatitude(riv.getOriginLatitude());
            //ri.setOriginLongitude(riv.getOriginLongitude());
            ri.setOriginCountry(cou);

            em.persist(ri);
            em.flush();
            resp.getRiverList().add(new RiverDTO(ri));
            log.log(Level.OFF, " River has been generated successfully : {0}", ri.getRiverName());       
            }
        }
             catch (PersistenceException e){    
       log.log(Level.SEVERE, "Failed", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to generate TeaM\n" );
                }     
     
        return resp;
        
     } 
     
     //rivers
    public String[] riverNames = {"Bhira River","Bivane River","Blesbokspruit","Bloukrans River","Bloukrans River",
        "Elands River","Ga-Selati River","Gamka River","Gamtoos River","Bamboesspruit (or Bamboes Spruit0","Baviaanskloof River","Bell River",
        "Berg River (or Great Berg River)","Black Kei River","Blesbokspruit (or Blesbok Spruit)","Blood River (or Ncome River)","Bloukrans River",
        "Bloukrans River","Bloukrans River","Blyde River (or Motlatse River)","Boesmans River","Boesmans River",
        "Boesmanspruit (or Boesman Spruit)","Bonte River","Bot River","Braamfontein Spruit (or Braamfonteinspruit)",
        "Brak River","Breede River (or Breë River)","Bronkhorst Spruit(or Bronkhorstspruit)","Buffalo River (Eastern Cape)",
        "Buffalo River (KwaZulu-Natal)","Buffels River","Buffels River","Bushman River","Caledon River",
        "Chalumna River (or Tyolomnqa River)","Crocodile River","Diep River","Diep River","Doring River","Duiwenhoks River",
        "Elands River","Elands River","Ga-Selati River","Gamka River","Gamtoos River","Gouritz River (or Gourits River)",
         "Great Fish River(Groot-Vis River)","Groenrivier (Green River)","Great Kei River(Groot-Kei)","Great Letaba River",
        "Great Usutu River(or Maputo River)","Great Brak River","Groot Brak River","Groot Marico River(or Great Marico River)","Groot River",
        "Groot Vet River","Hantams River","Hartbees River","Hartenbos River","Harts River","Harts River","Hennops River",
       "Hex River","Hex River","Hluhluwe River","Hoeksrivier\n", "Hol River","Hout River" ,"Illovo River" ,"Incomati River","Illovo River\n" ,
"Incomati River" ,"Indwe River" ,"Intombe River","Jakkals River" ,"Jordaan River" ,"Jordaanspruit" ,"Jukskei River\n" ,
"Kaaimans River" ,"Kabeljous River" ,"Kaalspruit" ,"Kaba River" ,"Kamma River" ,"Kammanassie River" ,"Kandandlovu River" ,"Karatara River" ,
"Karoospruit" ,"Kat River","Kat Spruit","Kariega River","Keisers River","Keiskamma River","Keurbooms River","Kingna River","Klaas Smits River",
"Klaserie River","Klasies River","Little Letaba River","Klein Marico River","Klein Olifants River","Klein River","Klip River","Klip River",
"Klip River","Knysna River","Kobongaba River","Komani River","Komati River (or Incomati, Nkomati)","Koshwana River","Kouga River",
"Kowie River","Kraai River","Kromdraai Spruit","Krom River","Krom River","Kuils River","Kwenxura River",
"Laai Spruit","Langvlei River","Latonyanda River","Liesbeek River(also spelt Liesbeeck)","Leeu River","Levubu River (or Luvuvhu, Pafuri River)","Lephalala River(see Palala River)",
"Letaba River","Liebenbergsvlei River","Liesbeeck River","Letsitele River","Liesbeek River","Limpopo River",
"Little Brak River (or Klein Brak)","Lourens River","Lovu River(Illovu)","Lower Brandvlei River","Lunsklip River",
"Lyndoch River","Malips River","Maputo River (or Great Usutu)","Marico River","Matlabas River",
"Mbhashe River","Mbango River","Mbizana River","Mbodi River","Mbokodweni River","Mdesingane River","Mdlotane River",
"Mdloti River","Mdumbe River","Mgobezeleni River" ,"Mgwalana River","Mhlabatshane River","Middle Letaba River","Mlalazi River","Mhlali River","Kuils River",
"Kwenxura River","Laai Spruit","Langvlei River","Latonyanda River","Liesbeek River(also spelt Liesbeeck)","Leeu River","Levubu River (or Luvuvhu, Pafuri River)","Lephalala River(see Palala River)" ,
"Letaba River" ,"Liebenbergsvlei River","Liesbeeck River","Letsitele River","Liesbeek River",
"Limpopo River	" ,"Little Brak River (or Klein Brak)","Lourens River","Lovu River(Illovu)" ,"Lower Brandvlei River" ,
"Lunsklip River","Lyndoch River","Malips River" ,
"Maputo River (or Great Usutu)","Marico River","Matlabas River",
"Mbhashe River","Mbango River" ,"Mbizana River" ,"Mbodi River","Mbokodweni River","Mdesingane River","Mdlotane River","Mdloti River","Mdumbe River",
"Mgobezeleni River","Mgwalana River","Mhlabatshane River","Middle Letaba River","Mlalazi River","Mhlali River","Mhlatuze River","Mkomazi River","Mkuze River",
"Mlazi River","Mngeni River (or Mgeni, Umgeni)","Modder River\n" ,"Mogalakwena River\n" ,"Mokolo River (or Mogol River)",
"Molopo River\n","Mooi River","Mooi River","Mooi River","Mpenjati River","Msunduzi River (or Duzi River)","Mthatha River",
"Mtamvuna River","Mtontwanes River",
"Mugwenya River","Mximkulwana River","Mzamba River","Mzimkulu River (or Umzimkulu)","Mzimkulwana river","Mzimvubu River",
"Mzumbe River","Nahoon River","Ngagane River","Ngotwane River","Ngwavuma","Nossob River",
"Nqabara River","Nsama River","Nsuze River","Nswamanzi River","Nuwejaarspruit","Nyalazi River","Nyl River",
"Nyoni River","Nzhelele River","Nwanedi River\n" +"Ohlanga River (or Umhlanga)","Ohrigstad River","Olifants River","Olifants River","Olifants River","Ongers River","Oompies River","Orange River (or Gariep River)",
"Os Spruit","Palala River (or Lephalala)","Palmiet River","Pienaars Rivern","Pienaars River","Plankenbrug River","Poesjenels River",
"Pongola River (or Phongolo)","Riet River","Riet River (Eastern Cape)",
"Rietspruit","Riviersonderend (or Sonderend River)","Rondegat River",
"Rooiels River","Rufanes River","Sabie River","Sak River",
"Salt River","Sand River","Sand River","Sandspruit","Sandlundlu River","Sandpoort River","Selons River",
"Smalblaar River","Sout River","Spoeg River","Steelpoort River","Steenbras River","Sterkstroom River","Storms River",
"Suikerbosrant River","Sundays River","Sundays River	M1","Swart River","Swartkops River",
"Tamboti River","Tarka River","Tele River","Timbavati River","Tongati River","Touws River",
"Treur River","Troe-Troe River","Tsitsikamma River","Tsomo River","Tugela River","Tyhume River","Uilkraal River","Umbilo River",
"Umfolozi River(Zulu: Mfolozi)","Black Umfolozi River (Zulu: Mfolozi emnyama)","White Umfolozi River (Zulu: Mfolozi emhlophe)",
"Wilge River (Mpumalanga)","Wilge River (Free State)","Umgeni River(uMngeni)","Umvoti River (or Mvoti River)",
"Umzimkulu River(Zulu: Mzimkulu)","Usutu River","Uvuzana River","Vaal River","Vals River","Van Stadens River",
"Verlorevlei River","Vet River","Vier-en-twintig-riviere (English: Rivers)","Waterval River","White Kei River",
"Wilge River","Wilge River","Wit River","Wit River","Woes-Alleen River","Xilinxa River",
"Xora River","Xuka River","Zandvlei","Zimbani River","Zinkwasi River","Zotsha River"
    
       };  
    
}
