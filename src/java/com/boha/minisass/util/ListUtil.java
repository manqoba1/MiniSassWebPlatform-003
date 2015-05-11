package com.boha.minisass.util;

import com.boha.minisass.data.Category;
import com.boha.minisass.data.Comment;
import com.boha.minisass.data.Conditions;
import com.boha.minisass.data.Country;
import com.boha.minisass.data.Evaluation;
import com.boha.minisass.data.Evaluationcomment;
import com.boha.minisass.data.Evaluationimage;
import com.boha.minisass.data.Evaluationinsect;
import com.boha.minisass.data.Evaluationsite;
import com.boha.minisass.data.Insect;
import com.boha.minisass.data.Insectimage;
import com.boha.minisass.data.Province;
import com.boha.minisass.data.River;
import com.boha.minisass.data.Rivertown;
import com.boha.minisass.data.Team;
import com.boha.minisass.data.Teammember;
import com.boha.minisass.data.Town;
import com.boha.minisass.dto.CategoryDTO;
import com.boha.minisass.dto.CommentDTO;
import com.boha.minisass.dto.ConditionsDTO;
import com.boha.minisass.dto.CountryDTO;
import com.boha.minisass.dto.EvaluationCommentDTO;
import com.boha.minisass.dto.EvaluationDTO;
import com.boha.minisass.dto.EvaluationImageDTO;
import com.boha.minisass.dto.EvaluationInsectDTO;
import com.boha.minisass.dto.EvaluationSiteDTO;
import com.boha.minisass.dto.InsectDTO;
import com.boha.minisass.dto.InsectImageDTO;
import com.boha.minisass.dto.ProvinceDTO;
import com.boha.minisass.dto.RiverDTO;
import com.boha.minisass.dto.RiverTownDTO;
import com.boha.minisass.dto.TeamDTO;
import com.boha.minisass.dto.TeamMemberDTO;
import com.boha.minisass.dto.TownDTO;
import com.boha.minisass.transfer.ResponseDTO;
import static com.boha.minisass.util.DataUtil.log;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author CodeTribe1
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ListUtil {

    @PersistenceContext
    EntityManager em;

    public ResponseDTO getCountryList() {
        ResponseDTO resp = new ResponseDTO();

        Query q = em.createNamedQuery("Country.findAll", Country.class);
        List<Country> list = q.getResultList();
        resp.setCountryList(new ArrayList<CountryDTO>());
        for (Country cp : list) {
            CountryDTO cn = new CountryDTO(cp);
            for (Province p : cp.getProvinceList()) {
                ProvinceDTO province = new ProvinceDTO(p);
                province.setTownList(new ArrayList<TownDTO>());
                for (Town town : p.getTownList()) {
                    TownDTO townDTO = new TownDTO(town);
                    townDTO.setRiverTownList(new ArrayList<RiverTownDTO>());
                }
                cn.getProvinceList().add(province);
            }
            resp.getCountryList().add(cn);
        }

        return resp;
    }

    public ResponseDTO getStartData(String countryCode) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Country.findByCountryCode", Country.class);
        q.setParameter("countryCode", countryCode);
        q.setMaxResults(1);
        Country c = (Country) q.getSingleResult();
        CountryDTO cn = new CountryDTO(c);
        for (Province p : c.getProvinceList()) {
            ProvinceDTO province = new ProvinceDTO(p);

            for (Town town : p.getTownList()) {
                TownDTO townDTO = new TownDTO(town);

                for (Rivertown rivertown : town.getRivertownList()) {
                    RiverTownDTO riverTown = new RiverTownDTO(rivertown);

                    townDTO.getRiverTownList().add(riverTown);
                }
                for (Team team : town.getTeamList()) {
                    TeamDTO teamDTO = new TeamDTO(team);
                    for (Teammember teammember : team.getTeammemberList()) {
                        TeamMemberDTO teamMemberDTO = new TeamMemberDTO(teammember);

                        teamDTO.getTeamMemberList().add(teamMemberDTO);
                    }
                    townDTO.getTeamList().add(teamDTO);
                }
                province.getTownList().add(townDTO);
            }
            cn.getProvinceList().add(province);
        }
        resp.setCountry(cn);

        return resp;
    }

    public ResponseDTO getData(String code) {
        ResponseDTO resp = new ResponseDTO();
        resp.setCategoryList(getCategoryDTOs());
        resp.setCommentList(getCommentDTOs());
        resp.setConditionsList(getConditionsDTOs());
        resp.setInsectList(getInsectDTOs());
        resp.setRiverList(getRiverDTOs());
        resp.setTownList(getTowns(code));
        resp.setTeamList(getTeamList().getTeamList());
        return resp;
    }

    private List<TownDTO> getTowns(String code) {
        List<TownDTO> list = new ArrayList<>();
        Query q = em.createNamedQuery("Town.findTwonsByCountry", Town.class);
        q.setParameter("code", code);
        List<Town> towns = q.getResultList();
        for (Town t : towns) {
            TownDTO dTO = new TownDTO(t);
            for (Team tm : t.getTeamList()) {
                dTO.getTeamList().add(new TeamDTO(tm));
            }
            list.add(dTO);
        }
        return list;
    }

    public ResponseDTO registrationData(String code) {
        ResponseDTO resp = new ResponseDTO();
        resp.setTownList(getTowns(code));
        return resp;
    }

    private List<RiverDTO> getRiverDTOs() {
        return getRiverList().getRiverList();
    }

    private List<InsectDTO> getInsectDTOs() {
        List<InsectDTO> result = new ArrayList<>();
        Query q = em.createNamedQuery("Insect.findAll", Category.class);
        List<Insect> cate = q.getResultList();
        for (Insect c : cate) {
            InsectDTO insect = new InsectDTO(c);
            for (Insectimage ii : c.getInsectimageList()) {
                InsectImageDTO insectImage = new InsectImageDTO(ii);
                insect.getInsectImageList().add(insectImage);
            }
            log.log(Level.INFO, insect.getGroupName());
            result.add(insect);
        }
        return result;
    }

    private List<CategoryDTO> getCategoryDTOs() {
        List<CategoryDTO> result = new ArrayList<>();
        Query q = em.createNamedQuery("Category.findAll", Category.class);
        List<Category> cate = q.getResultList();
        for (Category c : cate) {
            CategoryDTO cdto = new CategoryDTO(c);
            for (Conditions cd : c.getConditionsList()) {
                cdto.getConditionsList().add(new ConditionsDTO(cd));
            }
            result.add(cdto);
        }
        return result;
    }

    private List<CommentDTO> getCommentDTOs() {
        List<CommentDTO> result = new ArrayList<>();
        Query q = em.createNamedQuery("Comment.findAll", Comment.class);
        List<Comment> cate = q.getResultList();
        for (Comment c : cate) {
            result.add(new CommentDTO(c));
        }
        return result;
    }

    private List<ConditionsDTO> getConditionsDTOs() {
        List<ConditionsDTO> result = new ArrayList<>();
        Query q = em.createNamedQuery("Conditions.findAll", Conditions.class);
        List<Conditions> cate = q.getResultList();
        for (Conditions c : cate) {
            ConditionsDTO conditionsDTO = new ConditionsDTO(c);
            for (Evaluation evaluation : c.getEvaluationList()) {
                EvaluationDTO evaluationDTO = new EvaluationDTO(evaluation);

                conditionsDTO.getEvaluationList().add(evaluationDTO);
            }
            result.add(new ConditionsDTO(c));
        }
        return result;
    }

    public ResponseDTO getInsectList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Insect.findAll", Insect.class);
        List<Insect> list = q.getResultList();
        for (Insect ins : list) {
            resp.getInsectList().add(new InsectDTO(ins));
        }

        return resp;
    }

    public ResponseDTO getEvaluationByTeamMember(Integer teamMemberID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluation.findByTeamMemberID", Evaluation.class);
        q.setParameter("teamMemberID", teamMemberID);
        List<Evaluation> list = q.getResultList();
        for (Evaluation e : list) {
            resp.getEvaluationList().add(new EvaluationDTO(e));

        }

        return resp;
    }

    public ResponseDTO getTeamByTown(Integer townID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Team.findByTownID", Team.class);
        q.setParameter("townID", townID);
        List<Team> list = q.getResultList();
        for (Team te : list) {
            resp.getTeamList().add(new TeamDTO(te));
        }

        return resp;
    }

    public ResponseDTO getEvaluationInsectByEvaluation(Integer evaluationID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluationinsect.findByEvaluationID", Evaluationinsect.class);
        q.setParameter("evaluationID", evaluationID);
        List<Evaluationinsect> list = q.getResultList();
        for (Evaluationinsect ei : list) {
            resp.getEvaluationInsectList().add(new EvaluationInsectDTO(ei));
        }

        return resp;
    }

    public ResponseDTO getEvaluationSiteByCategory(Integer categoryID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluationsite.findByCategoryID", Evaluationsite.class);
        q.setParameter("categoryID", categoryID);
        List<Evaluationsite> list = q.getResultList();
        for (Evaluationsite es : list) {
            resp.getEvaluationSiteList().add(new EvaluationSiteDTO(es));
        }

        return resp;
    }

    public ResponseDTO getEvaluationByCondtions(Integer conditionsID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluation.findByConditionsID", Evaluation.class);
        q.setParameter("conditionsID", conditionsID);
        List<Evaluation> list = q.getResultList();
        for (Evaluation e : list) {
            resp.getEvaluationList().add(new EvaluationDTO(e));
        }

        return resp;
    }

    public ResponseDTO getAllProvince() throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("Province.findAll", Province.class);
            List<Province> list = q.getResultList();
            resp.setProvinceList(new ArrayList<ProvinceDTO>());
            for (Province p : list) {
                resp.getProvinceList().add(new ProvinceDTO(p));
            }
            log.log(Level.OFF, "{0} Provinces successfully found", resp.getProvinceList().size());
        } catch (Exception e) {
            log.log(Level.OFF, "failed to get provinces", e);
            throw new DataException("failed ........");
        }
        return resp;
    }

    public ResponseDTO getProvinceByCountry(Integer countryID) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Query q = em.createNamedQuery("Province.findByCountryID", Province.class);
            q.setParameter("countryID", countryID);
            List<Province> list = q.getResultList();
            resp.setProvinceList(new ArrayList<ProvinceDTO>());
            for (Province p : list) {
                resp.getProvinceList().add(new ProvinceDTO(p));
            }
            log.log(Level.OFF, "Found Provinces : {0}", resp.getProvinceList().size());
        } catch (Exception e) {
            log.log(Level.OFF, "failed to get provinces", e);
            throw new DataException("failed ........");
        }
        return resp;
    }

    public ResponseDTO getTeamList(Integer teamID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Team.findByTeamID", Team.class);
        q.setParameter("teamID", teamID);
        List<Team> list = q.getResultList();
        for (Team tea : list) {
            resp.getTeamList().add(new TeamDTO(tea));
        }

        return resp;
    }

    public ResponseDTO getTownByProvince(Integer provinceID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Town.findByProvinceID", Town.class);
        q.setParameter("provinceID", provinceID);
        List<Town> list = q.getResultList();
        for (Town to : list) {
            resp.getTownList().add(new TownDTO(to));
        }

        return resp;
    }

    public ResponseDTO getTeamMemberList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Teammember.findAll", Teammember.class);
        List<Teammember> list = q.getResultList();
        for (Teammember tm : list) {
            resp.getTeamMemberList().add(new TeamMemberDTO(tm));
        }

        return resp;
    }

    public ResponseDTO getRiverInCountry(Integer countryID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("River.findByCountryID", River.class);
        q.setParameter("countryID", countryID);
        List<River> list = q.getResultList();
        for (River riv : list) {
            resp.getRiverList().add(new RiverDTO(riv));
        }

        return resp;
    }

    public ResponseDTO getRiverList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("River.findAll", River.class);
        List<River> list = q.getResultList();
        for (River riv : list) {
            RiverDTO riverDTO = new RiverDTO(riv);

            for (Rivertown rt : riv.getRivertownList()) {
                RiverTownDTO riverTown = new RiverTownDTO(rt);
                riverDTO.getRiverTownList().add(riverTown);
            }
            //river list
            for (Evaluationsite evaluationsite : riv.getEvaluationsiteList()) {
                EvaluationSiteDTO evaluationSiteDTO = new EvaluationSiteDTO(evaluationsite);

                //evaluation site list
                for (Evaluation evaluation : evaluationsite.getEvaluationList()) {
                    EvaluationDTO evaluationDTO = new EvaluationDTO(evaluation);

                    //evaluation list
                    for (Evaluationimage evaluationimage : evaluation.getEvaluationimageList()) {
                        //evaluation image list                        
                        evaluationDTO.getEvaluationImageList().add(new EvaluationImageDTO(evaluationimage));
                    }

                    //Evaluation comment List 
                    for (Evaluationcomment evaluationcomment : evaluation.getEvaluationcommentList()) {
                        evaluationDTO.getEvaluationCommentList().add(new EvaluationCommentDTO(evaluationcomment));
                    }

                    //Evaluation insect list
                    for (Evaluationinsect evaluationinsect : evaluation.getEvaluationinsectList()) {
                        evaluationDTO.getEvaluationInsectList().add(new EvaluationInsectDTO(evaluationinsect));
                    }
                    evaluationSiteDTO.getEvaluationList().add(evaluationDTO);
                }

                riverDTO.getEvaluationSiteList().add(evaluationSiteDTO);
            }
            resp.getRiverList().add(riverDTO);

        }

        return resp;
    }

    public ResponseDTO listRiverTownList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Rivertown.findAll", Rivertown.class);
        List<Rivertown> list = q.getResultList();
        for (Rivertown riv : list) {
            resp.getRiverTownList().add(new RiverTownDTO(riv));
        }

        return resp;
    }

    public ResponseDTO getRiverTownList(Integer riverID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Rivertown.findByRiverID", Rivertown.class);
        q.setParameter("riverID", riverID);
        List<Rivertown> list = q.getResultList();
        for (Rivertown riv : list) {
            resp.getRiverTownList().add(new RiverTownDTO(riv));
        }

        return resp;
    }

    public ResponseDTO getCommentList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Comment.findAll", Comment.class);
        List<Comment> list = q.getResultList();
        for (Comment com : list) {
            resp.getCommentList().add(new CommentDTO(com));
        }

        return resp;
    }

    public ResponseDTO getEvaluationList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluation.findAll", Evaluation.class);
        List<Evaluation> list = q.getResultList();
        for (Evaluation eva : list) {
            resp.getEvaluationList().add(new EvaluationDTO(eva));
        }

        return resp;
    }

    public ResponseDTO getEvaluationSiteByRiver(Integer riverID) {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluationsite.findByRiverID", Evaluationsite.class);
        q.setParameter("riverID", riverID);
        List<Evaluationsite> list = q.getResultList();
        for (Evaluationsite es : list) {
            resp.getEvaluationSiteList().add(new EvaluationSiteDTO(es));
        }

        return resp;
    }

    public ResponseDTO getCategoryList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Category.findAll", Category.class);
        List<Category> list = q.getResultList();
        for (Category cat : list) {
            CategoryDTO categoryDTO = new CategoryDTO(cat);

            for (Evaluationsite evaluationsite : cat.getEvaluationsiteList()) {
                EvaluationSiteDTO evaluationSiteDTO = new EvaluationSiteDTO(evaluationsite);

                for (Evaluation evaluation : evaluationsite.getEvaluationList()) {

                    evaluationSiteDTO.getEvaluationList().add(new EvaluationDTO(evaluation));
                }
                categoryDTO.getEvaluationSiteList().add(evaluationSiteDTO);
            }

            resp.getCategoryList().add(categoryDTO);
        }

        return resp;
    }

    public ResponseDTO getTeamList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Team.findAll", Team.class
        );
        List<Team> list = q.getResultList();
        for (Team tea : list) {
            TeamDTO teamDTO = new TeamDTO(tea);

            for (Teammember teammember : tea.getTeammemberList()) {
                TeamMemberDTO memberDTO = new TeamMemberDTO(teammember);

                for (Evaluation evaluation : teammember.getEvaluationList()) {

                    memberDTO.getEvaluationList().add(new EvaluationDTO(evaluation));
                }
                teamDTO.getTeamMemberList().add(memberDTO);
            }
            resp.getTeamList().add(teamDTO);
        }
        return resp;
    }

    public ResponseDTO getEvaluationSiteList() {
        ResponseDTO resp = new ResponseDTO();
        Query q = em.createNamedQuery("Evaluationsite.findAll", Evaluationsite.class
        );
        List<Evaluationsite> list = q.getResultList();
        for (Evaluationsite es : list) {
            resp.getEvaluationSiteList().add(new EvaluationSiteDTO(es));
        }

        return resp;
    }

    private String getRandomPin() {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random(System.currentTimeMillis());
        int x = rand.nextInt(9);
        if (x == 0) {
            x = 3;
        }
        sb.append(x);
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        sb.append(rand.nextInt(9));
        return sb.toString();
    }

}
