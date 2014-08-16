package br.com.projeto.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRRtfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

public class Relatorio {

	private final String PATH = "reports/";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void gerarRelatorio(String nomeJasper, String attachmentName, List<?> dataSource, Map parametros, String tipo) throws JRException, IOException {

		FacesContext fContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = fContext.getExternalContext();

		// Busca o local onde esta o jasper
		InputStream fis = externalContext.getResourceAsStream(PATH + nomeJasper);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fis);

		HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();

		// Abre a figura e seta o parâmetro do relatório
		InputStream imgInputStream = this.getClass().getResourceAsStream("resources/images/logo/"+"fmb.gif");
		parametros.put("IMG_BRASAO", imgInputStream);

		StringBuffer filename = new StringBuffer(attachmentName);

		JRDataSource ds = new JRBeanCollectionDataSource(dataSource);
		byte[] bytes = null;

		if (tipo.equals("P")) {
			bytes = this.gerarRelatorioJasperPDF(jasperReport, parametros, ds);
			response.setContentType("application/pdf");
			filename.append(".pdf").toString();
			response.addHeader("content-disposition", "attachment;filename=" + filename);
		} else if (tipo.equals("X")) {
			bytes = this.gerarRelatorioJasperXLS(jasperReport, parametros, ds);
			response.setContentType("application/vnd.ms-excel");
			filename.append(".xls").toString();
			response.addHeader("content-disposition", "attachment;filename=" + filename);
		} else if (tipo.equals("D")) {
			bytes = this.gerarRelatorioJasperDoc(jasperReport, parametros, ds);
			response.setContentType("application/rtf");
			filename.append(".rtf").toString();
			response.addHeader("content-disposition", "attachment;filename=" + filename);
		}

		if (bytes != null && bytes.length > 0) {
			response.setContentLength(bytes.length);

			ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			ouputStream.flush();
			ouputStream.close();
			fContext.responseComplete();
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public byte[] gerarRelatorioJasper(String nomeJasper, List<?> dataSource, Map parametros, String tipo) throws JRException, IOException {

		FacesContext fContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = fContext.getExternalContext();

		// Busca o local onde esta o jasper
		InputStream fis = externalContext.getResourceAsStream(PATH + nomeJasper);
		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(fis);
		
		// Abre a figura e seta o parâmetro do relatório
		InputStream imgInputStream = this.getClass().getResourceAsStream("/resources/images/logo/"+"fmb.gif");
		parametros.put("IMG_BRASAO", imgInputStream);

		JRDataSource ds = new JRBeanCollectionDataSource(dataSource);
		byte[] bytes = null;

		if (tipo.equals("P")) {
			bytes = this.gerarRelatorioJasperPDF(jasperReport, parametros, ds);
		} else if (tipo.equals("X")) {
			bytes = this.gerarRelatorioJasperXLS(jasperReport, parametros, ds);
		} else if (tipo.equals("D")) {
			bytes = this.gerarRelatorioJasperDoc(jasperReport, parametros, ds);
		}

		return bytes;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private byte[] gerarRelatorioJasperPDF(JasperReport nomeJasper, Map parametros, JRDataSource ds) throws JRException, IOException {

		JasperPrint report = JasperFillManager.fillReport(nomeJasper, parametros, ds);
		byte[] pdf = JasperExportManager.exportReportToPdf(report);

		return pdf;

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private byte[] gerarRelatorioJasperXLS(JasperReport nomeJasper,
			Map parametros, JRDataSource ds) throws JRException, IOException {

		JasperPrint jasperPrint = JasperFillManager.fillReport(nomeJasper, parametros, ds);
		JExcelApiExporter xlsExporter = new JExcelApiExporter();
		ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();

		xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		xlsExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, xlsReport);
		xlsExporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		xlsExporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                //Parametro que remove campos em branco quando um campo ocupa mais de uma linha e os outros não
                xlsExporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);

		xlsExporter.exportReport();
		xlsReport.close();

		return xlsReport.toByteArray();

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private byte[] gerarRelatorioJasperDoc(JasperReport nomeJasper, Map parametros, JRDataSource ds) throws JRException, IOException {

		JasperPrint jasperPrint = JasperFillManager.fillReport(nomeJasper, parametros, ds);
		JRRtfExporter docExporter = new JRRtfExporter();
		ByteArrayOutputStream docReport = new ByteArrayOutputStream();

		docExporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
		docExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		docExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, docReport);

		docExporter.exportReport();
		docReport.close();

		return docReport.toByteArray();

	}

}