/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.faculte.simplefacultebudget.domain.common.pdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import static org.hibernate.internal.util.ConfigHelper.getResourceAsStream;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.jasperreports.JasperReportsUtils;

/**
 *
 * @author gouss
 */
public class GeneratePdf {
    
       
    public static ResponseEntity<Object> generate(String name,Map<String, Object> params,List data,String linkJasper) throws JRException, IOException {

        File pdfFile = File.createTempFile(name+"", ".pdf");
        FileOutputStream pos = new FileOutputStream(pdfFile);
        JasperReport report = (JasperReport) JRLoader.loadObject(getResourceAsStream(linkJasper));
        JRDataSource dataSource = new JRBeanCollectionDataSource(data);
        JasperReportsUtils.renderAsPdf(report, params, dataSource, pos);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(pdfFile));
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", pdfFile.getName()));
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        ResponseEntity<Object> responseEntity = ResponseEntity.ok().headers(headers).contentLength(pdfFile.length()).contentType(MediaType.parseMediaType("application/pdf")).body(resource);
        return responseEntity;

    }
}
