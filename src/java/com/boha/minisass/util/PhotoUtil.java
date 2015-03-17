/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.boha.minisass.util;

import com.boha.minisass.dto.EvaluationImageDTO;
import com.boha.minisass.transfer.ImagesDTO;
import com.boha.minisass.transfer.RequestDTO;
import com.boha.minisass.transfer.ResponseDTO;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.joda.time.DateTime;

/**
 *
 * @author CodeTribe1
 */
@Stateless
public class PhotoUtil {

    public ResponseDTO downloadPhotos(HttpServletRequest request, DataUtil dataUtil, PlatformUtil platformUtil) throws FileUploadException {
        logger.log(Level.INFO, "######### starting PHOTO DOWNLOAD process\n\n");
        ResponseDTO resp = new ResponseDTO();
        EvaluationImageDTO eidto = new EvaluationImageDTO();
        InputStream stream = null;
        File rootDir;
        try {
            rootDir = MinisassProperties.getImageDir();
            logger.log(Level.INFO, "rootDir - {0}", rootDir.getAbsolutePath());
            if (!rootDir.exists()) {
                rootDir.mkdir();
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Properties file problem", ex);
            resp.setMessage("Server file unavailable. Please try later");
            resp.setStatusCode(114);

            return resp;
        }

        ImagesDTO dto = null;

        Gson gson = new Gson();
        File evaluationDir = null, teamDir = null,
                teamMemberDir = null, riverDir = null, evaluationSiteDir = null, evaluationImageDir = null;
        try {
            ServletFileUpload upload = new ServletFileUpload();
            FileItemIterator iter = upload.getItemIterator(request);
            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                String name = item.getFieldName();
                stream = item.openStream();
                if (item.isFormField()) {
                    if (name.equalsIgnoreCase("JSON")) {
                        String json = Streams.asString(stream);
                        if (json != null) {
                            logger.log(Level.INFO, "picture with associated json: {0}", json);
                            dto = gson.fromJson(json, ImagesDTO.class);
                            if (dto != null) {
                                if (dto.getRiverID() != null) {
                                    riverDir = createRiverDirectory(rootDir, riverDir, dto.getRiverID());
                                }
                                if (dto.getEvaluationID() != null) {
                                     evaluationDir = createEvaluationDirectory(riverDir, evaluationDir, dto.getEvaluationID());
                                }
                                 if (dto.getEvaluationSiteID() != null) {
                                     evaluationSiteDir = createEvaluationSiteDirectory(evaluationDir, evaluationSiteDir, dto.getEvaluationSiteID());
                                 }                        
                                 if (dto.getEvaluationImageID() != null) {
                                     evaluationImageDir = createEvaluationImageDirectory(evaluationDir, evaluationImageDir, dto.getEvaluationImageID());
                                 }
                                if (dto.getTeamID() != null) {
                                    teamDir = createTeamDirectory(riverDir, teamDir, dto.getTeamID());
                                }
                                if (dto.getTeamMemberID() != null) {
                                    teamMemberDir = createTeamMemberDirectory(teamDir, teamMemberDir, dto.getTeamMemberID());
                                }                                
                               
                            }
                        } else {
                            logger.log(Level.WARNING, "JSON input is NULL..");
                        }
                    }
                } else {
                    File imageFile = null;
                    if (dto == null) {
                        continue;
                    }
                    DateTime dt = new DateTime();
                    String fileName = "";

                    if (dto.isIsFullPicture()) {
                        fileName = "f" + dt.getMillis() + ".jpg";
                    } else {
                        fileName = "t" + dt.getMillis() + ".jpg";
                    }
                    if (dto.getTeamMemberID() != null) {
                        if (dto.isIsFullPicture()) {
                            fileName = "f" + dto.getTeamMemberID() + ".jpg";
                        } else {
                            fileName = "t" + dto.getTeamMemberID() + ".jpg";
                        }
                    }
                    //
                    switch (dto.getPictureType()) {
                        case ImagesDTO.EVALUATION_IMAGE:
                            imageFile = new File(evaluationDir, fileName);
                            break;
                        case ImagesDTO.TEAM_IMAGE:
                            imageFile = new File(teamDir, fileName);
                            break;
                        case ImagesDTO.TEAM_MEMBER_IMAGE:
                            imageFile = new File(teamMemberDir, fileName);
                            break;
                    }

                    writeFile(stream, imageFile);
                    resp.setStatusCode(0);
                    resp.setMessage("Photo downloaded from mobile app ");
                    //add database
                    System.out.println("filepath: " + imageFile.getAbsolutePath());
                    //create uri

                    int index = imageFile.getAbsolutePath().indexOf("minisass_images");
                    if (index > -1) {
                        String uri = imageFile.getAbsolutePath().substring(index);
                        System.out.println("uri: " + uri);
                        if (dto.getEvaluationID() != null) {
                            eidto.setDateTaken(new Date().getTime());
                            eidto.setEvaluationID(dto.getEvaluationID());
                            eidto.setFileName(uri);
                            dataUtil.addEvaluationImage(eidto);
                        }
                        if (dto.getTeamID() != null) {
                            dataUtil.updateTeamImage(dto.getTeamID(), uri);
                        }
                        if (dto.getTeamMemberID() != null && dto.getTeamID() != null) {
                            dataUtil.updateTeamMemberImage(dto.getTeamMemberID(), uri);
                        }
                    }

                }
            }

        } catch (FileUploadException | IOException | JsonSyntaxException ex) {
            logger.log(Level.SEVERE, "Servlet failed on IOException, images NOT uploaded", ex);
            throw new FileUploadException();
        }

        return resp;
    }

    private void writeFile(InputStream stream, File imageFile) throws FileNotFoundException, IOException {

        if (imageFile == null) {
            throw new FileNotFoundException();
        }
        try (FileOutputStream fos = new FileOutputStream(imageFile)) {
            int read;
            byte[] bytes = new byte[2048];
            while ((read = stream.read(bytes)) != -1) {
                fos.write(bytes, 0, read);
            }
            stream.close();
            fos.flush();
        }

        logger.log(Level.WARNING, "### File downloaded: {0} size: {1}",
                new Object[]{imageFile.getAbsolutePath(), imageFile.length()});
    }

    private File createEvaluationDirectory(File riverDir, File evaluationDir, int id) {
        evaluationDir = new File(riverDir, RequestDTO.EVALUATION_DIR + id);
        if (!evaluationDir.exists()) {
            evaluationDir.mkdir();
            logger.log(Level.INFO, "evaluation directory created - {0}",
                    evaluationDir.getAbsolutePath());
        }

        return evaluationDir;
    }
    
    private File createEvaluationSiteDirectory(File evaluationDir, File evaluationSiteDir, int id) {
    evaluationSiteDir = new File(evaluationDir, RequestDTO.EVALUATION_SITE_DIR + id);
    if (!evaluationSiteDir.exists()) {
        evaluationSiteDir.mkdir();
        logger.log(Level.INFO, "evaluationSite directory has been created - {0}", 
                evaluationSiteDir.getAbsolutePath());
    }
    return evaluationSiteDir;
    }
    
    private File createEvaluationImageDirectory(File evaluationDir, File evaluationImageDir, int id) {
    evaluationImageDir = new File(evaluationDir, RequestDTO.EVALUATION_IMAGE_DIR + id);
    if (!evaluationImageDir.exists()) {
        evaluationImageDir.mkdir();
        logger.log(Level.INFO, "evaluationImage directory has been created - {0}", 
                evaluationImageDir.getAbsolutePath());
    }
    return evaluationImageDir;
    }
    
    private File createRiverDirectory(File rootDir, File riverDir, int id) {
    riverDir = new File(rootDir, RequestDTO.RIVER_DIR + id);
    if (!riverDir.exists()) {
    riverDir.mkdir();
    logger.log(Level.INFO, "river directory created - {0}", riverDir.getAbsolutePath());
    }
    return riverDir;
    }

    private File createTeamDirectory(File riverDir, File teamDir, int id) {
        teamDir = new File(riverDir, RequestDTO.TEAM_DIR + id);
        if (!teamDir.exists()) {
            teamDir.mkdir();
            logger.log(Level.INFO, "team directory created - {0}",
                    teamDir.getAbsolutePath());
        }

        return teamDir;
    }

    private File createTeamMemberDirectory(File teamDir, File teamMemberDir, int id) {
        teamMemberDir = new File( teamDir, RequestDTO.TEAM_MEMBER_DIR + id);
        if (!teamMemberDir.exists()) {
            teamMemberDir.mkdir();
            logger.log(Level.INFO, "team member directory created - {0}",
                    teamMemberDir.getAbsolutePath());
        }

        return teamMemberDir;
    }

    public static double getElapsed(long start, long end) {
        BigDecimal m = new BigDecimal(end - start).divide(new BigDecimal(1000));
        return m.doubleValue();
    }
    static final Logger logger = Logger.getLogger("PhotoUtil");
}
