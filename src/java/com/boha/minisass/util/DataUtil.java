package com.boha.minisass.util;

import com.boha.minisass.data.Category;
import com.boha.minisass.data.Comment;
import com.boha.minisass.data.Conditions;
import com.boha.minisass.data.Country;
import com.boha.minisass.data.Errorstore;
import com.boha.minisass.data.Errorstoreandroid;
import com.boha.minisass.data.Evaluation;
import com.boha.minisass.data.Evaluationimage;
import com.boha.minisass.data.Evaluationinsect;
import com.boha.minisass.data.Evaluationsite;
import com.boha.minisass.data.Gcmdevice;
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
import com.boha.minisass.dto.ErrorStoreDTO;
import com.boha.minisass.dto.EvaluationDTO;
import com.boha.minisass.dto.EvaluationImageDTO;
import com.boha.minisass.dto.EvaluationInsectDTO;
import com.boha.minisass.dto.EvaluationSiteDTO;
import com.boha.minisass.dto.GcmDeviceDTO;
import com.boha.minisass.dto.InsectDTO;
import com.boha.minisass.dto.InsectImageDTO;
import com.boha.minisass.dto.ProvinceDTO;
import com.boha.minisass.dto.RiverDTO;
import com.boha.minisass.dto.RiverTownDTO;
import com.boha.minisass.dto.TeamDTO;
import com.boha.minisass.dto.TeamMemberDTO;
import com.boha.minisass.dto.TownDTO;
import com.boha.minisass.transfer.ResponseDTO;
import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.joda.time.DateTime;

/**
 *
 * @author CodeTribe1
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class DataUtil {

    @PersistenceContext
    EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public ResponseDTO login(GcmDeviceDTO d, String email,
            String pin) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        GcmDeviceDTO dTO = new GcmDeviceDTO();
        try {
            Query q = em.createNamedQuery("Teammember.signin", Teammember.class);
            q.setParameter("email", email);
            q.setParameter("pin", pin);
            q.setMaxResults(1);
            Teammember cs = (Teammember) q.getSingleResult();
            Team team = cs.getTeam();

            dTO.setAndroidVersion(d.getAndroidVersion());
            dTO.setDateRegistered(new Date().getTime());
            dTO.setManufacturer(d.getManufacturer());
            dTO.setModel(d.getModel());
            dTO.setProduct(d.getProduct());
            dTO.setSerialNumber(d.getSerialNumber());
            dTO.setRegistrationID(d.getRegistrationID());
            dTO.setTeamID(team.getTeamID());
            dTO.setTeamMemberID(cs.getTeamMemberID());

            addDevice(dTO);

            resp.setTeamMember(new TeamMemberDTO(cs));
            try {
                CloudMessagingRegistrar.sendRegistration(dTO.getRegistrationID(), this);
            } catch (IOException ex) {
                Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
            }

            /* resp.setCategoryList(listUtil.getData().getCategoryList());
             resp.setCommentList(listUtil.getData().getCommentList());
             resp.setConditionsList(listUtil.getData().getConditionsList());
             resp.setInsectList(listUtil.getData().getInsectList());*/
        } catch (NoResultException e) {
            log.log(Level.WARNING, "Invalid login attempt: " + email + " pin: " + pin, e);
            resp.setStatusCode(301);
            resp.setMessage("Email address or PIN are invalid. Please try again.");
        }
        return resp;
    }

    public ResponseDTO registerTeamMember(TeamMemberDTO member) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Teammember tm = new Teammember();

            tm.setTeam(em.find(Team.class, member.getTeamID()));

            tm.setFirstName(member.getFirstName());
            tm.setLastName(member.getLastName());
            tm.setEmail(member.getEmail());
            tm.setCellphone(member.getCellphone());
            tm.setActiveFlag(member.getActiveFlag());
            tm.setDateRegistered(new Date());
            tm.setPin(getRandomPin());

            em.persist(tm);
            em.flush();

            resp.getTeamMemberList().add(new TeamMemberDTO(tm));
            EmailUtil.sendMail(tm.getEmail(), "Minisass Registration", "Hi, " + tm.getFirstName()
                    + "\n You've Succesfully Registered on Minisass Under Team " + tm.getTeam().getTeamName()
                    + ", Here are your Siging in details:\n"
                    + "email : " + tm.getEmail() + "\nPassword : " + tm.getPin()
                    + ".\n Thank you and Enjoy....", new CASessionBean());
            log.log(Level.OFF, "Team Member has been registered for: {0} ",
                    new Object[]{tm.getFirstName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addRiver(RiverDTO riv) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            River ri = new River();
            Country cou = em.find(Country.class, riv.getOriginCountryID());
            ri.setDateRegistered(new Date());
            ri.setEndLongitude(riv.getEndLongitude());
            ri.setEndLatitude(riv.getEndLatitude());
            ri.setEndCountry(cou);
            ri.setRiverName(riv.getRiverName());
            ri.setOriginLatitude(riv.getOriginLatitude());
            ri.setOriginLongitude(riv.getOriginLongitude());
            ri.setOriginCountry(cou);

            em.persist(ri);
            em.flush();
            resp.getRiverList().add(new RiverDTO(ri));
            log.log(Level.OFF, " River has been successfully added: {0}", ri.getRiverName());

        } catch (PersistenceException e) {
            log.log(Level.SEVERE, "Failed to add river", e);
            resp.setStatusCode(301);
            resp.setMessage("Duplicate detected, request ignored./nPlease try again");

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to add river", e);
            throw new DataException("Failed\n");
        }
        return resp;
    }

    public ResponseDTO registerTeam(TeamDTO team) throws DataException {
        ResponseDTO resp = new ResponseDTO();

        try {
            Team t = new Team();
            t.setTown(em.find(Town.class, team.getTownID()));
            t.setTeamImage(team.getTeamImage());
            t.setTeamName(team.getTeamName());
            t.setDateRegistered(new Date());
            t.setTeamImage("upload");
            em.persist(t);
            em.flush();

            TeamDTO teamDTO = new TeamDTO(t);
            //log.log(Level.SEVERE, "Team : {0}",t.getTeamID());
            if (team.getTeamMemberList() != null) {

                for (TeamMemberDTO tms : team.getTeamMemberList()) {
                    Teammember tm = new Teammember();

                    tm.setTeam(em.find(Team.class, t.getTeamID()));
                    tm.setTeamMemberImage("upload");
                    tm.setFirstName(tms.getFirstName());
                    tm.setLastName(tms.getLastName());
                    tm.setEmail(tms.getEmail());
                    tm.setCellphone(tms.getCellphone());
                    tm.setActiveFlag(tms.getActiveFlag());
                    tm.setDateRegistered(new java.sql.Date(new DateTime().toDate().getTime()));
                    tm.setPin(getRandomPin());

                    em.persist(tm);
                    em.flush();

                    teamDTO.getTeamMemberList().add(new TeamMemberDTO(tm));
                    EmailUtil.sendMail(tm.getEmail(), "Minisass Registration", "Hi, " + tm.getFirstName()
                            + "\n You've Succesfully Registered on Minisass Under Team " + t.getTeamName()
                            + ", Here are your Siging in details:\n"
                            + "email : " + tm.getEmail() + "\nPassword : " + tm.getPin()
                            + ".\n Thank you and Enjoy!", new CASessionBean());

                    log.log(Level.OFF, "Team Membber has been registered for: {0} ",
                            new Object[]{tm.getFirstName()});
                }
            }
            resp.getTeamList().add(teamDTO);

            log.log(Level.OFF, "Team has been registered for: {0} ",
                    new Object[]{t.getTeamName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addEvaluationSite(EvaluationSiteDTO site) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {

            Evaluationsite ts = new Evaluationsite();

            ts.setRiver(em.find(River.class, site.getRiverID()));
            ts.setCategory(em.find(Category.class, site.getCategoryID()));

            ts.setDateRegistered(new Date());
            ts.setLatitude(site.getLatitude());
            ts.setLongitude(site.getLongitude());

            em.persist(ts);
            em.flush();

            resp.getEvaluationSiteList().add(new EvaluationSiteDTO(ts));

            log.log(Level.OFF, "Evaluation site has been registered for: {0} ",
                    new Object[]{ts.getDateRegistered()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addInsect(InsectDTO insect) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Insect i = new Insect();
            i.setGroupName(insect.getGroupName());
            i.setSensitivityScore(insect.getSensitivityScore());

            em.persist(i);
            em.flush();

            resp.getInsectList().add(new InsectDTO(i));

            log.log(Level.OFF, "province has been added for: {0} ",
                    new Object[]{i.getGroupName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addEvaluationInsect(EvaluationInsectDTO evi) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {

            Evaluationinsect ei = new Evaluationinsect();

            ei.setEvaluation(em.find(Evaluation.class, evi.getEvaluationID()));
            ei.setInsect(em.find(Insect.class, evi.getInsectID()));

            ei.setRemarks(evi.getRemarks());
            em.persist(ei);
            em.flush();

            resp.getEvaluationInsectList().add(new EvaluationInsectDTO(ei));

            log.log(Level.OFF, "evaluation insect hass been successfully  added",
                    new Object[]{ei.getRemarks()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to get evaluation insect", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO updateTeam(TeamDTO tea) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Team t = em.find(Team.class, tea.getTeamID());

            if (tea.getTeamName() != null) {
                t.setTeamName(tea.getTeamName());

                em.merge(t);
                log.log(Level.INFO, "Team updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update Team\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO updateComment(CommentDTO com) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Comment c = em.find(Comment.class, com.getCommentID());

            if (com.getRemarks() != null) {
                c.setRemarks(com.getRemarks());

                em.merge(c);
                log.log(Level.INFO, "Comment updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update comment\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO updateEvaluationImage(EvaluationImageDTO evi) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Evaluationimage ei = em.find(Evaluationimage.class, evi.getEvaluationImageID());

            if (evi.getFileName() != null) {
                ei.setFileName(evi.getFileName());

                em.merge(ei);
                log.log(Level.INFO, "Evaluation image updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update evaluation image\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO updateConditions(ConditionsDTO con) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Conditions c = em.find(Conditions.class, con.getConditionsID());

            if (con.getConditionName() != null) {
                c.setConditionName(con.getConditionName());

                em.merge(c);
                log.log(Level.INFO, "Condition updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update condition\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO updateCategory(CategoryDTO cat) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Category c = em.find(Category.class, cat.getCategoryID());

            if (cat.getCategoryName() != null) {
                c.setCategoryName(cat.getCategoryName());

                em.merge(c);
                log.log(Level.INFO, "Category updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update category\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO updateTeamMember(TeamMemberDTO tem) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Teammember tm = em.find(Teammember.class, tem.getTeamMemberID());

            if (tem.getCellphone() != null) {
                tm.setCellphone(tem.getCellphone());

                if (tem.getEmail() != null) {
                    tm.setEmail(tem.getEmail());
                }
                if (tem.getFirstName() != null) {
                    tm.setFirstName(tem.getFirstName());
                }
                if (tem.getLastName() != null) {
                    tm.setLastName(tem.getLastName());
                }
                if (tem.getPin() != null) {
                    tm.setPin(tem.getPin());
                }

                em.merge(tm);
                log.log(Level.INFO, "Team member updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update team member\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO updateTown(TownDTO tow) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Town t = em.find(Town.class, tow.getTownID());

            if (tow.getTownName() != null) {
                t.setTownName(tow.getTownName());

                if (tow.getLatitude() != null) {
                    t.setLatitude(tow.getLatitude());
                }
                if (tow.getLongitude() != null) {
                    t.setLongitude(tow.getLongitude());
                }

                em.merge(t);
                log.log(Level.INFO, "Town updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update Town\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO addCategory(CategoryDTO category) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Category c = new Category();
            c.setCategoryId(category.getCategoryID());
            c.setCategoryName(category.getCategoryName());

            em.persist(c);
            em.flush();

            resp.getCategoryList().add(new CategoryDTO(c));

            log.log(Level.OFF, "province has been added for: {0} ",
                    new Object[]{c.getCategoryName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addProvince(ProvinceDTO province) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Province p = new Province();
            p.setLattitude(province.getLatitude());
            p.setLongitude(province.getLongitude());
            p.setProvinceName(province.getProvinceName());

            em.persist(p);
            em.flush();

            resp.getProvinceList().add(new ProvinceDTO(p));

            log.log(Level.OFF, "province has been added for: {0} ",
                    new Object[]{p.getProvinceName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addRiverTown(RiverTownDTO rivert) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Rivertown rt = new Rivertown();

            rt.setRiver(em.find(River.class, rivert.getRiverID()));
            rt.setTown(em.find(Town.class, rivert.getTownID()));

            em.persist(rt);
            em.flush();

            resp.getRiverTownList().add(new RiverTownDTO(rt));

            log.log(Level.OFF, "River town has been added for: {0} ",
                    new Object[]{rt.getRiverTownID()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addComment(CommentDTO comment) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Comment cm = new Comment();
            cm.setRemarks(comment.getRemarks());

            em.persist(cm);
            em.flush();

            resp.getCommentList().add(new CommentDTO(cm));

            log.log(Level.OFF, "comment has been added for: {0} ",
                    new Object[]{cm.getCommentID()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }
    Calendar c;

    public void textDate(EvaluationDTO evaluation) {
        c = Calendar.getInstance();
        c.setTime(new Date(evaluation.getEvaluationSite().getDateRegistered()));
        log.log(Level.WARNING, "Test {0}", c.getTime().toString());
    }

    public ResponseDTO addEvaluation(EvaluationDTO evaluation, List<InsectImageDTO> insectImages) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        c = new GregorianCalendar();
        c.setTime(new Date(evaluation.getEvaluationSite().getDateRegistered()));

        try {

            Evaluationsite ev = new Evaluationsite();
            ev.setAccuracy(evaluation.getEvaluationSite().getAccuracy());
            ev.setLongitude(evaluation.getEvaluationSite().getLongitude());
            ev.setLatitude(evaluation.getEvaluationSite().getLatitude());
            ev.setDateRegistered(new Date());
            log.log(Level.WARNING, "Test{0}", c.getTime().toString());
            ev.setRiver(em.find(River.class, evaluation.getEvaluationSite().getRiverID()));
            ev.setCategory(em.find(Category.class, evaluation.getEvaluationSite().getCategoryID()));
            em.persist(ev);
            em.flush();

            Evaluation e = new Evaluation();

            e.setTeamMember(em.find(Teammember.class, evaluation.getTeamMemberID()));
            e.setEvaluationSite(em.find(Evaluationsite.class, ev.getEvaluationSiteID()));
            log.log(Level.WARNING, "Test{0}", 1);
            e.setConditions(em.find(Conditions.class, evaluation.getConditionsID()));
            log.log(Level.WARNING, "Test{0}", 2);
            e.setLatitude(evaluation.getLatitude());
            e.setLongitude(evaluation.getLongitude());
            e.setOxygen(evaluation.getOxygen());
            e.setPH(evaluation.getPH());
            e.setRemarks(evaluation.getRemarks());
            e.setScore(evaluation.getScore());
            e.setWaterClarity(evaluation.getWaterClarity());
            e.setWaterTemperature(evaluation.getWaterTemperature());

            e.setEvaluationDate(ev.getDateRegistered());
            log.log(Level.WARNING, "Test{0}", e.getEvaluationDate());
            em.persist(e);
            em.flush();

            for (InsectImageDTO in : insectImages) {
                Evaluationinsect ei = new Evaluationinsect();
                ei.setEvaluation(em.find(Evaluation.class, e.getEvaluationID()));
                ei.setInsect(em.find(Insect.class, in.getInsectID()));
                ei.setEvaluationColor(5);
                ei.setEvaluationFlag(0);

                ei.setRemarks(evaluation.getRemarks());
                em.persist(ei);
                em.flush();
            }

            for (EvaluationImageDTO ei : evaluation.getEvaluationImageList()) {
                Evaluationimage e1 = new Evaluationimage();
                e1.setAccuracy(ei.getAccuracy());
                e1.setDateTaken(new java.sql.Date(ei.getDateTaken()));
                e1.setEvaluation(em.find(Evaluation.class, e.getEvaluationID()));
                e1.setFileName(ei.getFileName());
                e1.setLatitude(ev.getLatitude());
                e1.setLongitude(ev.getLongitude());
                em.persist(e1);
                em.flush();
            }
            resp.getEvaluationList().add(new EvaluationDTO(e));

            log.log(Level.OFF, "evaluation has been added for: {0} ",
                    new Object[]{e.getEvaluationDate()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addCondition(ConditionsDTO condition) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Conditions c = new Conditions();

            c.setCategory(em.find(Category.class, condition.getCategory().getCategoryID()));
            c.setConditionName(condition.getConditionName());
            c.setHigh(condition.getHigh());
            c.setLow(condition.getLow());

            em.persist(c);
            em.flush();

            resp.getConditionsList().add(new ConditionsDTO(c));

            log.log(Level.OFF, "condition has been added for: {0} ",
                    new Object[]{c.getConditionName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addTown(TownDTO town) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Town tw = new Town();
            tw.setTownName(town.getTownName());
            tw.setProvince(em.find(Province.class, town.getProvinceID()));
            tw.setLatitude(town.getLatitude());
            tw.setLongitude(town.getLongitude());

            em.persist(tw);
            em.flush();
            List<TownDTO> list = new ArrayList<>();
            list.add(new TownDTO(tw));
            resp.setTownList(list);

            log.log(Level.OFF, "Township has been added for: {0} ",
                    new Object[]{tw.getTownName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addCountry(CountryDTO country) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Country ct = new Country();
            ct.setCountryID(country.getCountryID());
            ct.setCountryName(country.getCountryName());
            ct.setLatitude(country.getLatitude());
            ct.setLongitude(country.getLongitude());

            em.persist(ct);
            em.flush();
            resp.getCountryList().add(new CountryDTO(ct));

            log.log(Level.OFF, "Township has been added for: {0} ",
                    new Object[]{ct.getCountryName()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public ResponseDTO addInsertImage(InsectImageDTO image) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Insectimage i = new Insectimage();
            i.setInsect(em.find(Insect.class, image.getInsectID()));

            i.setDateRegistered(new Date());
            i.setUri(image.getUri());

            em.persist(i);
            em.flush();
            resp.getInsectList().add(new InsectDTO());

            log.log(Level.OFF, "Township has been added for: {0} ",
                    new Object[]{i.getUri()});

        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed");
        }
        return resp;
    }

    public void updateRiver(RiverDTO dto) throws DataException {
        try {
            River r = em.find(River.class, dto.getRiverID());
            r.setRiverName(dto.getRiverName());
            r.setOriginLongitude(dto.getOriginLongitude());
            r.setDateRegistered(new Date());
            r.setEndLatitude(dto.getEndLatitude());

            em.merge(r);
            log.log(Level.INFO, "River updated");

        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update river\n" + getErrorString(e));
        }

    }

    public void updateRiverTown(RiverTownDTO dto) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Rivertown rt = em.find(Rivertown.class, dto.getRiverTownID());
            if (rt != null) {
                if (dto.getRiverID() != null) {
                    rt.setRiver(new River());
                }

                if (dto.getTownID() != null) {
                    rt.setTown(new Town());
                }

                em.merge(rt);
                log.log(Level.INFO, "River Town updated");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update river town\n" + getErrorString(e));
        }

    }

    public void updateEvaluationSite(EvaluationSiteDTO dto) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Evaluationsite ev = em.find(Evaluationsite.class, dto.getEvaluationSiteID());
            if (dto.getLatitude() > 0) {
                ev.setLatitude(dto.getLatitude());
            }
            if (dto.getLatitude() > 0) {
                ev.setLongitude(dto.getLongitude());
            }
            if (dto.getRiverID() != null) {
                ev.setRiver(em.find(River.class, dto.getRiverID()));
            }
            em.merge(ev);
            log.log(Level.INFO, "Evaluation site updated");

        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update Evaluation site\n" + getErrorString(e));
        }

    }

    public ResponseDTO updateInsert(InsectDTO dto) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Insect i = em.find(Insect.class, dto.getInsectID());
            i.setGroupName(dto.getGroupName());
            em.merge(i);
            log.log(Level.INFO, "Insect updated");

        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update Insect\n" + getErrorString(e));
        }

        return resp;
    }

    public ResponseDTO importMembers(int teamID, List<TeamMemberDTO> members) {
        ResponseDTO resp = new ResponseDTO();
        List<TeamMemberDTO> list = new ArrayList<>();

        for (TeamMemberDTO t : members) {
            Teammember team = em.find(Teammember.class, t.getTeamMemberID());
            team.setTeam(em.find(Team.class, teamID));
            em.merge(team);
            em.flush();
            list.add(new TeamMemberDTO(team));
        }
        resp.setTeamMemberList(list);
        return resp;
    }

    public ResponseDTO updateInsertImage(InsectImageDTO dto) throws DataException {
        ResponseDTO resp = new ResponseDTO();
        try {
            Insectimage ii = em.find(Insectimage.class, dto.getInsectImageID());
            ii.setDateRegistered(new Date());
            ii.setInsect(em.find(Insect.class, dto.getInsectID()));
            ii.setUri(dto.getUri());

            em.merge(ii);
            log.log(Level.INFO, "Insert Image updated");

        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to update Insect Image\n" + getErrorString(e));
        }

        return resp;
    }

    public void addAndroidError(Errorstoreandroid err) throws DataException {
        try {
            em.persist(err);
            log.log(Level.INFO, "Android error added");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to add Android Error", e);
            throw new DataException("Failed to add Android Error\n"
                    + getErrorString(e));
        }
    }

    public void addDevice(GcmDeviceDTO d) throws DataException {
        try {
            Gcmdevice g = new Gcmdevice();
            g.setTeam(em.find(Team.class, d.getTeamID()));
            g.setTeamMember(em.find(Teammember.class, d.getTeamMemberID()));

            g.setDateRegistered(new Date());
            g.setManufacturer(d.getManufacturer());
            g.setMessageCount(0);
            g.setModel(d.getModel());
            g.setRegistrationID(d.getRegistrationID());
            g.setSerialNumber(d.getSerialNumber());
            g.setProduct(d.getProduct());
            g.setAndroidVersion(d.getAndroidVersion());

            em.persist(g);
            log.log(Level.WARNING, "New device loaded");
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed", e);
            throw new DataException("Failed to add device\n" + getErrorString(e));

        }
    }

    public ResponseDTO getServerErrors(
            long startDate, long endDate) throws DataException {
        ResponseDTO r = new ResponseDTO();
        if (startDate == 0) {
            DateTime ed = new DateTime();
            DateTime sd = ed.minusMonths(3);
            startDate = sd.getMillis();
            endDate = ed.getMillis();
        }
        try {
            Query q = em.createNamedQuery("ErrorStore.findByPeriod", Errorstore.class);
            q.setParameter("startDate", new Date(startDate));
            q.setParameter("endDate", new Date(endDate));
            List<Errorstore> list = q.getResultList();
            List<ErrorStoreDTO> dList = new ArrayList();
            for (Errorstore e : list) {
                dList.add(new ErrorStoreDTO(e));
            }
            r.setErrorStoreList(dList);
            log.log(Level.OFF, "Errors found {0}", r.getErrorStoreList().size());
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to getServerErrors");
            throw new DataException("Failed to getServerErrors\n"
                    + getErrorString(e));
        }
        return r;
    }

    public String getErrorString(Exception e) {
        StringBuilder sb = new StringBuilder();
        if (e.getMessage() != null) {
            sb.append(e.getMessage()).append("\n\n");
        }
        if (e.toString() != null) {
            sb.append(e.toString()).append("\n\n");
        }
        StackTraceElement[] s = e.getStackTrace();
        if (s.length > 0) {
            StackTraceElement ss = s[0];
            String method = ss.getMethodName();
            String cls = ss.getClassName();
            int line = ss.getLineNumber();
            sb.append("Class: ").append(cls).append("\n");
            sb.append("Method: ").append(method).append("\n");
            sb.append("Line Number: ").append(line).append("\n");
        }

        return sb.toString();
    }

    public void confirmLocation(Integer EvaluationSiteID, double latitude, double longitude, Float accuracy) throws DataException {
        try {
            Evaluationsite ps = em.find(Evaluationsite.class, EvaluationSiteID);
            if (ps != null) {
                // ps.setLocationConfirmed(1);
                ps.setLatitude(latitude);
                ps.setLongitude(longitude);
                // ps.setAccuracy(accuracy);
                em.merge(ps);
                log.log(Level.INFO, "Evaluation Site location confirmed");
            }
        } catch (Exception e) {
            log.log(Level.OFF, null, e);
            throw new DataException("Failed to confirm location\n" + getErrorString(e));
        }
    }

    public void addErrorStore(int statusCode, String message, String origin) {
        log.log(Level.OFF, "------ adding errorStore, message: {0} origin: {1}", new Object[]{message, origin});
        try {
            Errorstore t = new Errorstore();
            t.setDateOccured(new Date());
            t.setMessage(message);
            t.setStatusCode(statusCode);
            t.setOrigin(origin);
            em.persist(t);
            log.log(Level.INFO, "####### ErrorStore row added, origin {0} \nmessage: {1}",
                    new Object[]{origin, message});
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add errorStore from " + origin + "\n" + message, e);
        }
    }
    static final Logger log = Logger.getLogger(DataUtil.class.getSimpleName());

    public void addEvaluationImage(EvaluationImageDTO dto) {
        log.log(Level.OFF, "------ adding evaluation image, message: {0} origin: {1}", new Object[]{dto.getFileName(), dto.getEvaluationID()});
        try {
            Evaluationimage t = new Evaluationimage();
            t.setDateTaken(new Date());
            t.setEvaluation(em.find(Evaluation.class, dto.getEvaluationID()));
            t.setFileName(dto.getFileName());

            em.persist(t);
            log.log(Level.INFO, "####### evaluation image row added, origin {0} \nmessage: {1}",
                    new Object[]{dto.getFileName(), dto.getEvaluationID()});
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add evaluation image from " + dto.getFileName() + "\n" + dto.getEvaluationID(), e);
        }
    }

    public void updateTeamImage(Integer teamID, String uri) {
        try {
            Team t = em.find(Team.class, teamID);
            t.setTeamImage(uri);
            em.merge(t);
            log.log(Level.INFO, "Team row added");
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add team image from \n {0}", e);

        }
    }

    public void updateTeamMemberImage(Integer teamMemberID, String uri) {
        try {
            Teammember t = em.find(Teammember.class, teamMemberID);
            t.setTeamMemberImage(uri);
            em.merge(t);
            log.log(Level.INFO, "Team member row added");
        } catch (Exception e) {
            log.log(Level.SEVERE, "####### Failed to add team member image from \n {0}", e);

        }
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
