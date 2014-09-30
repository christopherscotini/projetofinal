package br.com.mkoffice.utils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import br.com.mkoffice.utils.constants.TiposRelatorio;

public class ReportFactory {

	
	 private String reportName;
	 private Map<String,Object> params;
	 private TiposRelatorio tipoRelatorio;
	 private List<?> list;
	 
	 public ReportFactory(String reportName, Map<String,Object> params, TiposRelatorio tipoRelatorio, List<?>list) {
		 this.reportName = reportName;
		 this.params = params;
		 this.tipoRelatorio = tipoRelatorio;
		 this.list = list;
	 }
	 
	 public ReportFactory(String reportName, TiposRelatorio tipoRelatorio) {
		 this.reportName = reportName;
		 this.tipoRelatorio = tipoRelatorio;
	 }
	 
	 public InputStream getReportStream(){
	 
	 InputStream input = null;
	 
//	 try {
//		 ByteArrayOutputStream output = new ByteArrayOutputStream();
//		 JasperReport jasperReport = (JasperReport) JRLoader.loadObject(FacesContext.getCurrentInstance().getExternalContext().getRealPath("relatorios/"+reportName));
//		 jasperReport.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
//		 JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(list);
//		 
//		 JasperPrint print = JasperFillManager.fillReport(jasperReport, params, datasource);
//		 JRExporter exporter = null;
//		 
//		 if(tipoRelatorio == TiposRelatorio.PDF)
//			 exporter = new net.sf.jasperreports.engine.export.JRPdfExporter();
//		 
//		 if(tipoRelatorio == TiposRelatorio.XLS){
//			 exporter = new net.sf.jasperreports.engine.export.JRXlsExporter();
//			 exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
//			 exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
//			 exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
//			 exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
//		 }
//		 
//		 exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, output);
//		 exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
//		 exporter.exportReport();
//		 
//		 input = new ByteArrayInputStream(output.toByteArray());
//		
//	 } catch (JRException ex) {
//		 Logger.getLogger(ReportFactory.class.getName()).log(Level.SEVERE, null, ex);
//	 }
	 
	 return input;
	 
	 }
	 
}
